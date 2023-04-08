package hajimeapi4j.internal.request;

import hajimeapi4j.api.request.RestAction;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;
import okhttp3.Response;

@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public abstract class AbstractRestAction<T> implements RestAction<T> {

  protected final Class<T> clazz;

  @Override
  @Nonnull
  public CompletableFuture<T> handleAsync() {
    return CompletableFuture.supplyAsync(
        () -> {
          try (Response r = queueRequest()) {
            return handleResponse(r);
          } catch (IOException e) {
            log.error("An error occurred while handling the request.", e);
            return null;
          } catch (InterruptedException interrupt) {
            log.error("The interaction thread has been interrupted by someone.", interrupt);
            throw new RuntimeException(interrupt);
          }
        }
    );
  }

  @Override
  @Nonnull
  public CompletableFuture<Void> handleAsync(Consumer<T> consumer) {
    return handleAsync().thenAccept(consumer);
  }

  @Nonnull
  public abstract Request createRequest(@Nonnull String url);

  @Nonnull
  protected abstract Response queueRequest() throws IOException, InterruptedException;

  @Nullable
  protected abstract T handleResponse(@Nonnull Response r);

  @Nonnull
  protected String constructUrl() {
    throw new UnsupportedOperationException("This method cannot be used in your use-case.");
  }
}
