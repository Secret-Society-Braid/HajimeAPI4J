package hajimeapi4j.internal.builder;

import hajimeapi4j.api.endpoint.ListEndPoint;
import hajimeapi4j.api.request.RestAction;
import hajimeapi4j.util.Checks;
import java.util.Arrays;
import java.util.List;
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
    this.musicType = Arrays.asList(args);
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
    this.search = search;
    return this;
  }

  public RestAction<List<ListEndPoint>> build() {
    return null;
  }


}
