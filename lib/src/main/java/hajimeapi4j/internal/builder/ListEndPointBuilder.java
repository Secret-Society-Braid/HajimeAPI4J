package hajimeapi4j.internal.builder;

import HajimeAPI4J.util.Checks;
import hajimeapi4j.api.endpoint.ListEndPoint;
import hajimeapi4j.api.request.RestAction;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
    this.limit = amount;
    return this;
  }

  public ListEndPointBuilder setMusicType(String... args) {
    this.musicType = Arrays.asList(args);
    return this;
  }

  public RestAction<List<ListEndPoint>> build() {
    Checks.validateListType(type);
    return null;
  }


}
