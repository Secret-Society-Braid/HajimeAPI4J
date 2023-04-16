package hajimeapi4j.internal.request;

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Slf4j
public class ArrayResponseRestActionImpl<T> extends RestActionImpl<List<T>> {

  protected final TypeReference<List<T>> typeRef;

  public ArrayResponseRestActionImpl(@Nonnull String pathParam, Map<String, String> queryParam) {
    super(pathParam, queryParam);
    this.typeRef = new TypeReference<>() {
    };
  }

  @Nullable
  @Override
  protected List<T> handleResponse(@NotNull Response r) {
    if (r.body() == null) {
      throw new NullPointerException("The response body is null.");
    }
    try {
      return MAPPER.readValue(r.body().string(), this.typeRef);
    } catch (IOException e) {
      log.error("An error occurred while handling the response.", e);
    }
    return null;
  }
}