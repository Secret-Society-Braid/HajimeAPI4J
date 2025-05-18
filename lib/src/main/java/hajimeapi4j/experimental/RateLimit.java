package hajimeapi4j.experimental;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import lombok.Setter;

public class RateLimit {

  private final Semaphore semaphore;
  @Setter
  private volatile long rateLimitInterval;

  public RateLimit(int permits, long rateLimitIntervalMillis) {
    this.semaphore = new Semaphore(permits);
    this.rateLimitInterval = rateLimitIntervalMillis;
  }

  public void acquire() throws InterruptedException {
    if (!semaphore.tryAcquire(rateLimitInterval, TimeUnit.MILLISECONDS)) {
      throw new IllegalStateException("Rate limit exceeded. Please try again later.");
    }
  }

  public void release() {
    semaphore.release();
  }
}
