package hajimeapi4j.experimental;

public class RateLimitExceededException extends RuntimeException {

  public RateLimitExceededException(String message) {
    super(message);
  }
}
