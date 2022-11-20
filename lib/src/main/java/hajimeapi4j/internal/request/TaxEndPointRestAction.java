package hajimeapi4j.internal.request;

import com.fasterxml.jackson.databind.JsonNode;
import hajimeapi4j.api.endpoint.EndPoint;
import hajimeapi4j.api.endpoint.TaxEndPoint;
import hajimeapi4j.api.request.RestAction;
import hajimeapi4j.util.InternalUtils;
import hajimeapi4j.util.ParseUtil;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.annotation.Nonnull;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class TaxEndPointRestAction implements RestAction<TaxEndPoint> {

  private static final ExecutorService concurrentThreadExecutor = Executors.newCachedThreadPool(
      InternalUtils.createInternalThreadFactory("ListEndPoint handshaking thread"));
  private final Requester requester = new Requester();
  @Setter
  private Map<String, String> queryParam = new LinkedHashMap<>();
  private CompiledRoute route;

  public TaxEndPointRestAction(LinkedHashMap<String, String> queryParam) {
    this.queryParam = queryParam;
    this.route = Route.listRoute().compile();
  }

  @Nonnull
  @Override
  public TaxEndPoint complete() {
    if (this.queryParam.isEmpty()) {
      throw new IllegalArgumentException(
          "query param has not been set yet!\nIf this exception persists, please report this exception to the developer.");
    }
    this.route = this.constructRoute((LinkedHashMap<String, String>) this.queryParam);
    this.requester.setRoute(this.route);
    log.debug("set route data: {}", this.route);
    TaxEndPoint parsed = (TaxEndPoint) EndPoint.createEmpty();
    try {
      JsonNode result = this.requester.sendRequest();
      parsed = ParseUtil.createTaxResponse(result);
    } catch (IOException e) {
      log.error("There was a problem during handshaking with the API", e);
    }
    return parsed;
  }

  @Override
  public CompletableFuture<TaxEndPoint> submit() {
    return CompletableFuture.supplyAsync(this::complete, concurrentThreadExecutor);
  }

  @Override
  public CompiledRoute constructRoute(LinkedHashMap<String, String> params) {
    return Route.listRoute().compile().withQueryParams(params);
  }
}