package hajimeapi4j.internal.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hajimeapi4j.api.request.RestAction;
import hajimeapi4j.experimental.RateLimit;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Response;

@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public abstract class AbstractRestAction<T> implements RestAction<T> {

  protected static final OkHttpClient CLIENT = new OkHttpClient();
  protected static final ObjectMapper MAPPER = new ObjectMapper();
  protected static final String BASE_URL = "https://api.fujiwarahaji.me/v3/";

  protected final Class<T> clazz;
  protected final RateLimit rateLimit;

  @Override
  @Nonnull
  public CompletableFuture<T> handleAsync() {
    final CommonPayload payload = CommonPayload.create(rateLimit,
      RequestAdapter.getInstance(CLIENT));
    payload.setUri(constructUrl());
    return payload.send().thenApply(this::handleResponse);
  }

  @Override
  @Nonnull
  public CompletableFuture<Void> handleAsync(Consumer<T> consumer) {
    return handleAsync().thenAccept(consumer);
  }

  @Override
  @Nonnull
  public <U> CompletableFuture<U> handleAsync(Function<T, U> handler) {
    return handleAsync().thenApply(handler);
  }

  @Nullable
  protected T handleResponse(@Nonnull Response r) {
    if (r.body() == null) {
      throw new NullPointerException("The response body is null.");
    }
    try {
      return MAPPER.readValue(r.body().string(), clazz);
    } catch (IOException e) {
      log.error("An error occurred while handling the response.", e);
    }
    return null;
  }

  @Nonnull
  protected String constructUrl() {
    throw new UnsupportedOperationException("This method cannot be used in your use-case.");
  }
}
