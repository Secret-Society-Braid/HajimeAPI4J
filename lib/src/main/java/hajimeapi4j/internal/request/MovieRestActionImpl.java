package hajimeapi4j.internal.request;

import com.google.common.base.Strings;
import hajimeapi4j.api.endpoint.MovieEndPoint;
import hajimeapi4j.internal.endpoint.MovieEndPointImpl;
import java.io.IOException;
import java.util.Map;
import javax.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;

@Slf4j
public class MovieRestActionImpl extends RestActionImpl<MovieEndPoint> {

  public MovieRestActionImpl(@Nonnull Map<String, String> queryParam) {
    super("movie", queryParam);
  }

  @Override
  protected MovieEndPoint handleResponse(@Nonnull Response r) {
    final int code = r.code();
    if (code != 200) {
      return new MovieEndPointImpl(null, code);
    }

    try {
      return new MovieEndPointImpl(r.body() == null ? null : r.body().string(), code);
    } catch (IOException e) {
      log.error("Encountered an io exception while handling the response.", e);
      log.error("Please report this to the github issue tracker.");
      return new MovieEndPointImpl(null, code);
    }
  }

  protected String getRequestedReturnType() {
    return this.queryParam.compute("plain", (k, v) -> {
      if (Strings.isNullOrEmpty(v)) {
        return "http";
      }
      return v.equalsIgnoreCase("true") ? "plain" : "http";
    });
  }
}
