package hajimeapi4j.internal.request;

import hajimeapi4j.experimental.RateLimit;
import java.util.concurrent.CompletableFuture;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Response;

@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonPayload {

  private static final RateLimit DEFAULT_RATE_LIMITER;
  private static final RequestAdapter DEFAULT_REQUESTER;
  private static CommonPayload instance;

  static {
    DEFAULT_RATE_LIMITER = new RateLimit(1, 1, 1000, 50, 2);
    DEFAULT_REQUESTER = RequestAdapter.getInstance(new OkHttpClient());
  }

  @Getter(AccessLevel.PACKAGE)
  protected final RateLimit rateLimit;
  @Getter(AccessLevel.PRIVATE)
  protected final RequestAdapter requester;
  @Setter(AccessLevel.PACKAGE)
  protected String uri;

  private CommonPayload() {
    this(DEFAULT_RATE_LIMITER, DEFAULT_REQUESTER);
  }

  static CommonPayload create() {
    if (instance == null) {
      instance = new CommonPayload();
    }
    return instance;
  }

  static CommonPayload create(RateLimit rateLimit, RequestAdapter requester) {
    if (instance == null) {
      instance = new CommonPayload(rateLimit, requester);
    } else if (!instance.getRateLimit().equals(rateLimit) || !instance.getRequester()
      .equals(requester)) {
      throw new IllegalStateException(
        "CommonPayload instance cannot be re-created with different rate limit or requester.");
    }
    return instance;
  }

  CompletableFuture<Response> send() {
    log.debug("Finished preparing to send request to: {}", uri);
    return this.requester.queueRequest(uri, this.rateLimit);
  }
}
