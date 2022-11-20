package hajimeapi4j.internal.request;

import com.fasterxml.jackson.databind.JsonNode;
import hajimeapi4j.api.endpoint.ListEndPoint;
import hajimeapi4j.api.endpoint.MusicEndPoint;
import hajimeapi4j.api.endpoint.TaxEndPoint;
import hajimeapi4j.api.request.RestAction;
import hajimeapi4j.util.InternalUtils;
import hajimeapi4j.util.ParseUtil;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.NonNull;

public class RestActionImpl<T> implements RestAction<T> {

  private static final ExecutorService serv = Executors.newCachedThreadPool(
      InternalUtils.createInternalThreadFactory(
          "Rest action handshaking thread"
      ));
  protected Route route;
  protected LinkedHashMap<String, String> queryMap;

  public RestActionImpl(Route route, Map<String, String> queryMap) {
    this.route = route;
    this.queryMap = (LinkedHashMap<String, String>) queryMap;
  }

  @NonNull
  @Override
  public T complete() {
    return submit().join();
  }

  @Override
  @SuppressWarnings("unchecked")
  public CompletableFuture<T> submit() {
    return CompletableFuture.supplyAsync(() -> {
      CompiledRoute cRoute = this.constructRoute(this.queryMap);
      Requester requester = new Requester();
      requester.setRoute(cRoute);
      try {
        JsonNode rawResponse = requester.sendRequest();
        switch (this.route.getMethod()) {
          case LIST:
            List<ListEndPoint> parsedListResponse = ParseUtil.createListResponse(rawResponse);
            return (T) parsedListResponse;
          case TAX:
            TaxEndPoint parsedTaxResponse = ParseUtil.createTaxResponse(rawResponse);
            return (T) parsedTaxResponse;
          case MUSIC:
            MusicEndPoint parsedMusicResponse = ParseUtil.createMusicResponse(rawResponse);
            return (T) parsedMusicResponse;
        }
      } catch (IOException e) {
        throw new CompletionException(e);
      }
      throw new IllegalStateException("Cannot handle api invoking");
    }, serv);
  }

  @Override
  public CompiledRoute constructRoute(LinkedHashMap<String, String> params) {
    return new CompiledRoute(this.route, this.route.toString()).withQueryParams(params);
  }
}
