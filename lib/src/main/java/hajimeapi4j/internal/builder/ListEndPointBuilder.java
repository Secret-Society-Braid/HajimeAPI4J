package hajimeapi4j.internal.builder;

import com.google.common.base.Joiner;
import hajimeapi4j.api.endpoint.ListEndPoint;
import hajimeapi4j.api.request.RestAction;
import hajimeapi4j.internal.request.RestActionImpl;
import hajimeapi4j.internal.request.Route;
import hajimeapi4j.util.Checks;
import hajimeapi4j.util.enums.ListParameter;
import hajimeapi4j.util.enums.ListParameter.Type;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuppressWarnings("unused")
public class ListEndPointBuilder {

  private final Map<String, String> parameters;
  private final boolean musicTypeSelected;
  private static final String inapplicableQueryExceptionString = "you cannot set this parameter!";

  public static ListEndPointBuilder createFor(String type) {
    return new ListEndPointBuilder(type);
  }

  public static ListEndPointBuilder createFor(ListParameter.Type type) {
    return createFor(type.toString());
  }

  private ListEndPointBuilder(String type) {
    this.parameters = new HashMap<>();
    this.musicTypeSelected = type.equals(Type.MUSIC.toString());
    this.parameters.put("type", type);
  }

  public ListEndPointBuilder setLimit(int amount) {
    if (!this.musicTypeSelected) {
      throw new UnsupportedOperationException(inapplicableQueryExceptionString);
    }

    Checks.validateInteger(amount);
    this.parameters.put("limit", String.valueOf(amount));
    return this;
  }

  public ListEndPointBuilder setMusicType(ListParameter.MusicType... types) {
    if (!this.musicTypeSelected) {
      throw new UnsupportedOperationException(inapplicableQueryExceptionString);
    }

    final Joiner joiner = Joiner.on("%2C").skipNulls();
    String concatenated = joiner.join(types);
    this.parameters.put("music_type", concatenated);
    return this;
  }

  public ListEndPointBuilder setOrder(ListParameter.Order order) {
    this.parameters.put("order", order.toString());
    return this;
  }

  public ListEndPointBuilder setOrderBy(ListParameter.OrderBy orderBy) {
    if (((this.musicTypeSelected) && (!orderBy.isApplicableForMusic())) || (
        (!this.musicTypeSelected) && (orderBy.isApplicableForMusic()))) {
      throw new UnsupportedOperationException(inapplicableQueryExceptionString);
    }
    this.parameters.put("orderby", orderBy.toString());
    return this;
  }

  public ListEndPointBuilder setSearch(String search) {
    final String encoded = URLEncoder.encode(search, StandardCharsets.UTF_8);
    this.parameters.put("search", encoded);
    return this;
  }

  public ListEndPointBuilder setProduction(ListParameter.Production... production) {
    if (this.musicTypeSelected) {
      throw new UnsupportedOperationException(inapplicableQueryExceptionString);
    }
    final Joiner joiner = Joiner.on("%2C").skipNulls();
    String concatenated = joiner.join(production);
    this.parameters.put("production", concatenated);
    return this;
  }

  public RestAction<List<ListEndPoint>> build() {
    log.debug("set parameters: {}", this.parameters);
    log.info("constructing action instance...");
    RestAction<List<ListEndPoint>> result = new RestActionImpl<>(Route.listRoute(),
        this.parameters);
    log.debug("complete. information: {}", result);
    return result;
  }
}
