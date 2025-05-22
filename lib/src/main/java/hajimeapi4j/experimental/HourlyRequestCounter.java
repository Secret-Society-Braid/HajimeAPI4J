package hajimeapi4j.experimental;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.Queue;

public class HourlyRequestCounter {

  private final long maxRequestsPerHour;
  private final Queue<Instant> timestamps;

  public HourlyRequestCounter(final long maxRequestsPerHour) {
    // extract this condition check to another utility method
    if (maxRequestsPerHour <= 0) {
      throw new IllegalArgumentException("Max requests per hour must be positive");
    }
    this.maxRequestsPerHour = maxRequestsPerHour;
    this.timestamps = new LinkedList<>();
  }

  public synchronized boolean checkAndIncrement() {
    Instant now = Instant.now();
    pollingQueue(now);
    if (timestamps.size() < maxRequestsPerHour) {
      timestamps.offer(now);
      return true;
    } else {
      return false;
    }
  }

  public synchronized long getCurrentCount() {
    Instant now = Instant.now();
    pollingQueue(now);
    return timestamps.size();
  }

  private void pollingQueue(Instant now) {
    while (!timestamps.isEmpty() && timestamps.peek().isBefore(now.minus(1, ChronoUnit.HOURS))) {
      timestamps.poll();
    }
  }
}
