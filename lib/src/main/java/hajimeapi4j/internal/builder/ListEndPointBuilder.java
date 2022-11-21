package hajimeapi4j.internal.builder;

import com.google.common.base.Strings;
import hajimeapi4j.api.endpoint.ListEndPoint;
import hajimeapi4j.api.request.RestAction;
import hajimeapi4j.exception.IllegalParameterException;
import hajimeapi4j.util.Checks;
import hajimeapi4j.util.InternalUtils;
import hajimeapi4j.util.ParseUtil;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
public class ListEndPointBuilder {

  private String type;
  private int limit;
  private List<String> musicType;
  private String orderBy;
  private String order;
  private String search;
  private String production;

  public static ListEndPointBuilder createFor(String type) {
    return new ListEndPointBuilder(type);
  }

  private ListEndPointBuilder(String type) {
    Checks.validateListType(type);
    this.type = type;
  }

  public ListEndPointBuilder setLimit(int amount) {
    Checks.validateInteger(amount);
    this.limit = amount;
    return this;
  }

  public ListEndPointBuilder setMusicType(String... args) {
    this.musicType = args == null ? Collections.emptyList() : Arrays.asList(args);
    return this;
  }

  public ListEndPointBuilder setOrder(String order) {
    Checks.validateOrderParamString(order);
    this.order = order;
    return this;
  }

  public ListEndPointBuilder setOrderBy(String orderBy) {
    Checks.validateOrderByParamStringWithListEndPoint(orderBy, this.type.equals("music"));
    this.orderBy = orderBy;
    return this;
  }

  public ListEndPointBuilder setSearch(String search) {
    this.search = URLEncoder.encode(search, StandardCharsets.UTF_8);
    return this;
  }

  public ListEndPointBuilder setProduction(String production) {
    this.production = production;
    return this;
  }

  public RestAction<List<ListEndPoint>> build() {
    log.debug("checking whether valid...");
    if ("music".equals(this.type)) {
      if (!Strings.isNullOrEmpty(this.production)) {
        throw new IllegalParameterException(
            String.format(InternalUtils.getMessageNotAllowedParameter(), "type", "list")
        );
      }
    } else {
      if (this.limit != 0 || !this.musicType.isEmpty()) {
        throw new IllegalParameterException(
            String.format(InternalUtils.getMessageNotAllowedParameter(), "limit or music_type",
                "list")
        );
      }
    }
    log.info("Attempt to connect with list endpoint and folloowing parameters");
    log.info("type parameter: {}", InternalUtils.checkEmptyForParameterLogging(this.type));
    log.info("limit parameter: {}",
        InternalUtils.checkEmptyForParameterLogging(String.valueOf(this.limit)));
    log.info("orderBy parameter: {}", InternalUtils.checkEmptyForParameterLogging(this.orderBy));
    log.info("order parameter: {}", InternalUtils.checkEmptyForParameterLogging(this.order));
    log.info("search parameter (URL encoded): {}",
        InternalUtils.checkEmptyForParameterLogging(this.search));
    log.info("musicType parameter: {}", this.musicType.toString());
    log.info("production parameter: {}",
        InternalUtils.checkEmptyForParameterLogging(this.production));
    Map<String, String> queryMap = ParseUtil.createParameterMap(
        ""
    );
    return null;
  }


}
