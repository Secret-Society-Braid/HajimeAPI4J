package hajimeapi4j.internal.request;

import com.google.common.base.Joiner;
import com.google.common.base.Joiner.MapJoiner;
import hajimeapi4j.util.InternalUtils;
import jakarta.annotation.Nonnull;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

@Slf4j
public class RestActionImpl<T> extends AbstractRestAction<T> {

  private static final ExecutorService serv = Executors.newSingleThreadExecutor(
      InternalUtils.createInternalThreadFactory(
          "Rest action handshaking thread"
      ));


  protected final String pathParam;
  protected final Map<String, String> queryParam;

  public RestActionImpl(@NotNull String pathParam, @NotNull Map<String, String> queryParam,
      @NotNull Class<T> clazz) {
    super(clazz);
    this.pathParam = pathParam;
    this.queryParam = queryParam;
  }

  public RestActionImpl(@Nonnull String pathParam, @NotNull Class<T> clazz) {
    super(clazz);
    this.pathParam = pathParam;
    this.queryParam = Collections.emptyMap();
  }

  public RestActionImpl(@Nonnull String pathParam, @Nonnull Map<String, String> queryParam) {
    super(null);
    this.pathParam = pathParam;
    this.queryParam = queryParam;
  }

  @Nonnull
  @Override
  protected String constructUrl() {
    String url = BASE_URL + this.pathParam;
    if (!this.queryParam.isEmpty()) {
      final MapJoiner mapJoiner = Joiner.on("&").withKeyValueSeparator("=");
      url = Joiner.on("").join(url, "?", mapJoiner.join(this.queryParam));
    }
    return url;
  }
}
