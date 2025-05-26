package hajimeapi4j.internal.request;

import hajimeapi4j.api.endpoint.ListEndPoint;
import hajimeapi4j.experimental.RateLimit;
import jakarta.annotation.Nonnull;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Slf4j
public class ArrayResponseRestActionImpl<T> extends RestActionImpl<List<T>> {

  public ArrayResponseRestActionImpl(@Nonnull String pathParam, Map<String, String> queryParam,
    @Nonnull
    RateLimit rateLimit) {
    super(pathParam, queryParam, rateLimit);
  }

  @Nullable
  @Override
  protected List<T> handleResponse(@NotNull Response r) {
    if (r.body() == null) {
      throw new NullPointerException("The response body is null.");
    }
    try {
      return MAPPER.readValue(r.body().string(),
        MAPPER.getTypeFactory().constructCollectionLikeType(List.class, ListEndPoint.class));
    } catch (IOException e) {
      log.error("An error occurred while handling the response.", e);
    }
    return null;
  }
}