package hajimeapi4j.experimental;

import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RateLimit {

  private final TokenBucket tokenBucket;
  private final HourlyRequestCounter hourlyCounter;

  private final Queue<CompletableFutureWrapper<?>> delayedQueue = new ConcurrentLinkedQueue<>();
  private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
  private final ExecutorService taskExecutor; // 実際のタスクを実行するためのExecutorService

  private final AtomicBoolean isShuttingDown = new AtomicBoolean(false);

  /**
   * RateLimiterの新しいインスタンスを構築します。
   *
   * @param tokenBucketCapacity           トークンバケットの最大容量。
   * @param tokenBucketRefillRate         トークンの補充レート。
   * @param tokenBucketRefillPeriodMillis トークンの補充周期。
   * @param maxRequestsPerHour            1時間あたりの最大リクエスト数。
   * @param taskExecutorPoolSize          タスクを実行するためのスレッドプールサイズ。
   */
  public RateLimit(
    long tokenBucketCapacity,
    long tokenBucketRefillRate,
    long tokenBucketRefillPeriodMillis,
    long maxRequestsPerHour,
    int taskExecutorPoolSize) {

    this(
      new TokenBucket(tokenBucketCapacity, tokenBucketRefillRate, tokenBucketRefillPeriodMillis),
      new HourlyRequestCounter(maxRequestsPerHour),
      taskExecutorPoolSize);
  }

  public RateLimit(TokenBucket tokenBucket, HourlyRequestCounter hourlyRequestCounter,
    int taskExecutorPoolSize) {
    this.tokenBucket = tokenBucket;
    this.hourlyCounter = hourlyRequestCounter;
    this.taskExecutor = Executors.newFixedThreadPool(taskExecutorPoolSize); // 固定スレッドプール

    this.scheduler.scheduleAtFixedRate(this::processDelayedQueue, 0,
      tokenBucket.getRefillPeriodMillis() / 2, TimeUnit.MILLISECONDS);
  }

  /**
   * APIリクエストに相当するタスクをRateLimiterにサブミットします。 レート制限に達している場合は、タスクを遅延させるか、例外を CompletableFuture に設定します。
   *
   * @param task 実行するAPIリクエストのCallableタスク
   * @return タスクの完了を待機するためのCompletableFutureオブジェクト
   */
  public <V> CompletableFuture<V> submit(Callable<V> task) {
    CompletableFuture<V> future = new CompletableFuture<>();
    CompletableFutureWrapper<V> wrapper = new CompletableFutureWrapper<>(task, future);

    if (isShuttingDown.get()) {
      future.completeExceptionally(
        new RejectedExecutionException("RateLimit is shutting down, no new tasks are accepted."));
      return future;
    }

    // 1. まず1時間あたりのリクエスト制限をチェック
    if (!hourlyCounter.checkAndIncrement()) {
      future.completeExceptionally(new RateLimitExceededException(
        "Hourly rate limit exceeded. Current count: " + hourlyCounter.getCurrentCount()));
      return future;
    }

    // 2. 次にトークンバケットで短時間レートをチェック
    if (tokenBucket.tryConsume(1)) {
      // トークンが取得できた場合、即座にタスクを実行
      executeTask(wrapper);
    } else {
      // トークンが取得できなかった場合、タスクを遅延キューに退避
      if (isShuttingDown.get()) {
        future.completeExceptionally(new RejectedExecutionException(
          "RateLimit is shutting down, task rejected during delay queue submission."));
        return future;
      }
      delayedQueue.offer(wrapper);
      log.debug("Task delayed. Queue size: {} Current tokens: {}",
        delayedQueue.size(), tokenBucket.getTokens());
    }
    return future;
  }

  /**
   * 遅延キューからタスクを取り出して実行を試みます。 このメソッドはスケジューラーによって定期的に呼び出されます。
   */
  private void processDelayedQueue() {
    if (isShuttingDown.get()) {
      // シャットダウン中は新しいタスクの処理を停止
      return;
    }

    CompletableFutureWrapper<?> wrapper = delayedQueue.poll();
    if (wrapper != null) {
      // Futureが既に完了（complete/completeExceptionally/cancel）されていないか確認
      if (wrapper.future.isDone()) {
        return;
      }

      try {
        if (tokenBucket.tryConsume(1)) {
          executeTask(wrapper);
        } else {
          // まだトークンがない場合はキューに戻す
          delayedQueue.offer(wrapper);
        }
      } catch (Exception e) {
        // 遅延タスク処理中の予期せぬエラー
        wrapper.future.completeExceptionally(e);
        log.error("Error processing delayed task from queue: {}", e.getMessage());
      }
    }
  }

  /**
   * 実際にタスクを実行するプライベートメソッド。 taskExecutor を使用してタスクを非同期に実行します。
   *
   * @param wrapper 実行するタスクをラップしたCompletableFutureWrapper
   */
  private <V> void executeTask(CompletableFutureWrapper<V> wrapper) {
    if (isShuttingDown.get()) { // 実行直前に再度シャットダウンをチェック
      wrapper.future.completeExceptionally(new RejectedExecutionException(
        "RateLimit is shutting down, task rejected just before execution."));
      return;
    }
    if (wrapper.future.isDone()) { // 既に完了済みの場合も実行しない
      return;
    }

    // taskExecutor にサブミット
    taskExecutor.submit(() -> {
      try {
        V result = wrapper.callable.call();
        wrapper.future.complete(result); // 結果を CompletableFuture に設定
      } catch (Exception e) {
        wrapper.future.completeExceptionally(e); // 例外を CompletableFuture に設定
      }
    });
  }

  /**
   * RateLimiterをシャットダウンし、リソースを解放します。 未処理の遅延タスクは破棄され、そのCompletableFutureはキャンセルされた状態になります。
   * シャットダウン後に行われた submit() は RejectedExecutionException を CompletableFuture に設定します。
   */
  public void shutdown() {
    isShuttingDown.set(true);

    // スケジューラーを停止
    scheduler.shutdown();
    try {
      if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
        scheduler.shutdownNow(); // 強制シャットダウン
      }
    } catch (InterruptedException e) {
      scheduler.shutdownNow();
      Thread.currentThread().interrupt();
    }

    // タスク実行用のExecutorServiceもシャットダウン
    taskExecutor.shutdown();
    try {
      if (!taskExecutor.awaitTermination(5, TimeUnit.SECONDS)) {
        taskExecutor.shutdownNow();
      }
    } catch (InterruptedException e) {
      taskExecutor.shutdownNow();
      Thread.currentThread().interrupt();
    }

    // 遅延キューに残っているタスクをすべてキャンセル
    int cancelledCount = 0;
    CompletableFutureWrapper<?> remainingWrapper;
    while ((remainingWrapper = delayedQueue.poll()) != null) {
      if (!remainingWrapper.future.isDone()) { // 既に完了済みでないFutureのみキャンセル
        // completeExceptionallyでキャンセルを通知
        remainingWrapper.future.completeExceptionally(
          new RejectedExecutionException("Task was cancelled due to RateLimit shutdown."));
        log.error("Task in delayed queue cancelled due to shutdown: {}",
          remainingWrapper.callable.toString());
        cancelledCount++;
      }
    }
    log.info("RateLimit shut down. Total tasks cancelled from delayed queue: {}", cancelledCount);
  }

  /**
   * CompletableFuture をラップして、オリジナルの Callable と CompletableFuture 自体を保持する内部クラス。 これにより、キューに
   * CompletableFuture オブジェクトとその実行ロジックをまとめて格納できます。
   */
  private static class CompletableFutureWrapper<V> {

    final Callable<V> callable;
    final CompletableFuture<V> future;

    CompletableFutureWrapper(Callable<V> callable, CompletableFuture<V> future) {
      this.callable = callable;
      this.future = future;
    }
  }

}
