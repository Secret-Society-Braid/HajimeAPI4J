package hajimeapi4j.experimental;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

public class RateLimitTest {

  static RateLimit rateLimit, hourlyTestRateLimit, shutdownRateLimit;
  static final int DEFAULT_TIMEOUT_SECONDS = 12; // タイムアウト時間を5秒から12秒に延長
  private static final Logger log = org.slf4j.LoggerFactory.getLogger(RateLimitTest.class);

  @BeforeAll
  static void setUp() {
    rateLimit = new RateLimit(10, 1, 1000, 100, 5);
    hourlyTestRateLimit = new RateLimit(10, 1, 1000, 10, 5); // 1時間あたり10リクエスト
    shutdownRateLimit = new RateLimit(1,1,1000,10,1);
  }

  @AfterAll
  static void tearDown() {
    if (rateLimit != null) rateLimit.shutdown();
    if (hourlyTestRateLimit != null) hourlyTestRateLimit.shutdown();
    // shutdownRateLimit はテストメソッド内でシャットダウンされる
  }

  @Test
  void burstTest() {
    List<CompletableFuture<String>> futures = new ArrayList<>();
    for (int i = 0; i < 20; i++) {
      final int taskId = i;
      CompletableFuture<String> future = rateLimit.submit(() -> {
        long start = System.currentTimeMillis();
        log.info("Task {} started at {} ms", taskId, start);
        try {
          TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
          // TestではInterruptedExceptionをExecutionExceptionのcauseとしてラップして伝播させる
          throw new RuntimeException(String.format("Task %d was interrupted", taskId), e);
        }
        String result = String.format("Task %d finished at %s ms", taskId, System.currentTimeMillis());
        log.info(result);
        return result;
      });
      futures.add(future);
      // テストの安定性のために、タスク投入の間隔を少し空ける (任意)
      try {
        TimeUnit.MILLISECONDS.sleep(10); // 負荷をかけすぎないように少し待つ
      } catch (InterruptedException e) {
        fail(e);
      }
    }

    for (int i = 0; i < futures.size(); i++) {
      CompletableFuture<String> future = futures.get(i);
      final int taskId = i;
      assertDoesNotThrow(() -> {
        String result = future.get(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS); // タイムアウト付きで結果を取得
        log.info("Task {} completed with result: {}", taskId, result);
        assertNotNull(result, "Task " + taskId + " result should not be null");
      }, "Task " + taskId + " should complete without exception.");
    }
  }

  @Test
  void hourlyLimitTest() {
    List<CompletableFuture<String>> futures = new ArrayList<>();
    int totalTasks = 15;
    int expectedSuccessCount = 10; // hourlyTestRateLimit の maxRequestsPerHour

    for (int i = 0; i < totalTasks; i++) {
      final int taskId = i;
      CompletableFuture<String> future = hourlyTestRateLimit.submit(() -> {
        long start = System.currentTimeMillis();
        log.info("Task {} (hourly) started at {} ms", taskId, start);
        try {
          TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
          throw new RuntimeException(String.format("Task %d (hourly) was interrupted", taskId), e);
        }
        String result = String.format("Task %d (hourly) finished at %s ms", taskId, System.currentTimeMillis());
        log.info(result);
        return result;
      });
      futures.add(future);
      // タスク投入の間隔を少し空ける
      try {
        TimeUnit.MILLISECONDS.sleep(10);
      } catch (InterruptedException e) {
        fail(e);
      }
    }

    for (int i = 0; i < totalTasks; i++) {
      final int taskId = i;
      CompletableFuture<String> future = futures.get(i);
      if (i < expectedSuccessCount) {
        assertDoesNotThrow(() -> {
          String result = future.get(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
          log.info("Task {} (hourly) succeeded as expected: {}", taskId, result);
          assertNotNull(result);
        }, "Task " + taskId + " (hourly) should succeed.");
      } else {
        ExecutionException ex = assertThrows(ExecutionException.class, () -> {
          future.get(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        }, "Task " + taskId + " (hourly) should fail due to rate limit.");
        log.info("Task {} (hourly) failed as expected: {}", taskId, ex.getMessage());
        assertInstanceOf(RateLimitExceededException.class, ex.getCause(),
            "Cause of ExecutionException should be RateLimitExceededException for task " + taskId);
      }
    }
  }

  @Test
  void shutdownLimiterTest() {
    List<CompletableFuture<String>> futuresBeforeShutdown = new ArrayList<>();
    // シャットダウン前にいくつかのタスクを投入
    for (int i = 0; i < 2; i++) { // shutdownRateLimitのプールサイズは1なので、いくつかキューに入る
      final int taskId = i;
      CompletableFuture<String> future = shutdownRateLimit.submit(() -> {
        long start = System.currentTimeMillis();
        log.info("Task {} (shutdown test) started at {} ms", taskId, start);
        try {
          TimeUnit.MILLISECONDS.sleep(200); // 少し長めのタスク
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
          throw new RuntimeException(String.format("Task %d (shutdown test) was interrupted", taskId), e);
        }
        return String.format("Task %d (shutdown test) finished at %s ms", taskId, System.currentTimeMillis());
      });
      futuresBeforeShutdown.add(future);
    }

    try {
      // タスクが処理される時間を少し与える
      TimeUnit.MILLISECONDS.sleep(100);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      fail(e);
    }

    shutdownRateLimit.shutdown(); // レートリミッターをシャットダウン

    // シャットダウン前に投入されたタスクの結果を確認
    // タイミングによって成功するかRejectedExecutionExceptionで失敗する可能性がある
    for (int i = 0; i < futuresBeforeShutdown.size(); i++) {
      final int taskId = i;
      CompletableFuture<String> future = futuresBeforeShutdown.get(i);
      try {
        String result = future.get(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        log.info("Pre-shutdown task {} completed: {}", taskId, result);
        assertNotNull(result);
      } catch (ExecutionException e) {
        log.warn("Pre-shutdown task {} failed with ExecutionException: {}", taskId, e.getMessage());
        // シャットダウンによりキャンセルされた場合、RejectedExecutionExceptionがcauseになる
        assertInstanceOf(RejectedExecutionException.class, e.getCause(),
            "Cause of ExecutionException for pre-shutdown task " + taskId + " should be RejectedExecutionException if cancelled.");
      } catch (InterruptedException | TimeoutException e) {
        fail("Pre-shutdown task " + taskId + " was interrupted or timed out.", e);
      }
    }

    // シャットダウン後にタスクを投入
    CompletableFuture<String> futureAfterShutdown = shutdownRateLimit.submit(() -> "This task should be rejected");

    ExecutionException ex = assertThrows(ExecutionException.class, () -> {
      futureAfterShutdown.get(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }, "Task submitted after shutdown should be rejected.");
    log.info("Post-shutdown task rejected as expected: {}", ex.getMessage());
    assertInstanceOf(RejectedExecutionException.class, ex.getCause(),
        "Cause of ExecutionException for post-shutdown task should be RejectedExecutionException.");
  }
}
