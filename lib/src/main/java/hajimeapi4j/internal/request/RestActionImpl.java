package hajimeapi4j.internal.request;

import com.google.common.base.Joiner;
import com.google.common.base.Joiner.MapJoiner;
import hajimeapi4j.util.InternalUtils;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

  @Nonnull
  @Override
  public Request createRequest(@NotNull String url) {
    return new Request.Builder()
        .url(url)
        .get()
        .addHeader("Accept", "application/json")
        .addHeader("User-Agent", "HajimeAPI4J java wrapper developed by @hizumiaoba")
        .build();
  }

  @Nonnull
  @Override
  protected Response queueRequest() throws IOException, InterruptedException {
    // forcibly wait for 1 second to avoid massive requests
    TimeUnit.SECONDS.sleep(1);
    final String url = constructUrl();
    log.debug("Requesting to {}", url);
    return CLIENT.newCall(createRequest(url)).execute();
  }

  @Nullable
  @Override
  protected T handleResponse(@NotNull Response r) {
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
