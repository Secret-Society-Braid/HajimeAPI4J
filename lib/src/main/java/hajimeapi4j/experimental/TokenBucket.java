package hajimeapi4j.experimental;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import lombok.Getter;

public class TokenBucket {

  private final long capacity;
  private final long refillRate;
  @Getter
  private final long refillPeriodMillis;

  private final AtomicLong tokens;
  private volatile long lastRefillTimestamp;

  public TokenBucket(long capacity, long refillRate, long refillPeriodMillis) {
    // extract this condition check to another utility method
    if (capacity <= 0 || refillRate <= 0 || refillPeriodMillis <= 0) {
      throw new IllegalArgumentException(
        "Capacity, refill rate, and refill period must be positive");
    }
    this.capacity = capacity;
    this.refillRate = refillRate;
    this.refillPeriodMillis = refillPeriodMillis;
    this.tokens = new AtomicLong(capacity);
    this.lastRefillTimestamp = System.currentTimeMillis();
  }

  public TokenBucket(long capacity, long refillRate, long refillPeriod, TimeUnit refillPeriodUnit) {
    this(capacity, refillRate, refillPeriodUnit.toMillis(refillPeriod));
  }

  public boolean tryConsume(long tokensToConsume) {
    // extract this condition check to another utility method
    if (tokensToConsume <= 0) {
      throw new IllegalArgumentException("Tokens to consume must be positive");
    }
    refillTokens();

    while (true) {
      long currentTokens = tokens.get();
      if (currentTokens >= tokensToConsume) {
        if (tokens.compareAndSet(currentTokens, currentTokens - tokensToConsume)) {
          return true;
        }
      } else {
        return false;
      }
    }
  }

  private void refillTokens() {
    long now = System.currentTimeMillis();
    long timePassed = now - lastRefillTimestamp;
    if (timePassed < refillPeriodMillis) {
      return;
    }
    long numPeriods = timePassed / refillPeriodMillis;
    long tokensToAdd = numPeriods * refillRate;
    if (tokensToAdd > 0) {
      long currentTokens = tokens.get();
      long newTokens = Math.min(currentTokens + tokensToAdd, capacity);
      if (tokens.compareAndSet(currentTokens, newTokens)) {
        lastRefillTimestamp = now;
      }
    }
  }

  public long getTokens() {
    refillTokens();
    return tokens.get();
  }
}
