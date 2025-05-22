package hajimeapi4j.experimental;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class RateLimit {

  private final Semaphore semaphore;
  private final int maxRequestsPerSecond;
  private final int maxRequestsPerHour;
  private final Queue<Runnable> delayedQueue = new ConcurrentLinkedQueue<>();
  private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
  private final List<Instant> requestTimestampsPerHour = Collections.synchronizedList(
    new ArrayList<>());

  public RateLimit(int maxConcurrentRequests, int maxRequestsPerSecond, int maxRequestsPerHour) {
    this.semaphore = new Semaphore(maxConcurrentRequests);
    this.maxRequestsPerSecond = maxRequestsPerSecond;
    this.maxRequestsPerHour = maxRequestsPerHour;
    startDelayedQueueProcessor();
    startHourlyRequestCounterCleaner();
  }

  public <T> CompletableFuture<T> submit(Callable<T> task) throws InterruptedException {
    CompletableFuture<T> future = new CompletableFuture<>();
    submit(() -> {
      try {
        T result = task.call();
        future.complete(result);
      } catch (Exception e) {
        future.completeExceptionally(e);
      }
    });
    return future;
  }

  public void submit(Runnable task) throws InterruptedException {
    // 1時間あたりのリクエスト数制限のチェック
    Instant now = Instant.now();
    requestTimestampsPerHour.add(now);
    requestTimestampsPerHour.removeIf(
      timestamp -> timestamp.isBefore(now.minus(1, TimeUnit.HOURS.toChronoUnit())));
    if (requestTimestampsPerHour.size() > maxRequestsPerHour) {
      throw new RateLimitExceededException("Hourly rate limit exceeded.");
    }

    // 短時間のリクエストレート制御
    if (semaphore.tryAcquire(0, TimeUnit.MILLISECONDS)) {
      try {
        task.run();
      } finally {
        semaphore.release();
      }
    } else {
      delayedQueue.offer(() -> {
        try {
          semaphore.acquire();
          try {
            task.run();
          } finally {
            semaphore.release();
          }
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      });
    }
  }

  private void startDelayedQueueProcessor() {
    scheduler.scheduleAtFixedRate(() -> {
      Runnable delayedTask = delayedQueue.poll();
      if (delayedTask != null) {
        try {
          submit(delayedTask); // 再度 submit することでレートリミットのチェックを通す
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        } catch (RateLimitExceededException e) {
          // 遅延キューに入っていたタスクが時間制限に引っかかる可能性があるので、適切に処理する
          System.err.println("Delayed task rejected due to hourly rate limit: " + e.getMessage());
        }
      }
    }, 0, 1000 / maxRequestsPerSecond, TimeUnit.MILLISECONDS); // 短時間レートに基づいて処理を試みる
  }

  private void startHourlyRequestCounterCleaner() {
    scheduler.scheduleAtFixedRate(() -> {
      Instant now = Instant.now();
      requestTimestampsPerHour.removeIf(
        timestamp -> timestamp.isBefore(now.minus(1, TimeUnit.HOURS.toChronoUnit())));
    }, 0, 1, TimeUnit.MINUTES); // 1分ごとに古いタイムスタンプを削除
  }

  public void shutdown() {
    scheduler.shutdown();
  }
}
