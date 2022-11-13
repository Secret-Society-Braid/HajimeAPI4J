package hajimeapi4j.internal.request;

import com.fasterxml.jackson.databind.JsonNode;
import hajimeapi4j.api.endpoint.EndPoint;
import hajimeapi4j.api.request.RestAction;
import hajimeapi4j.util.InternalUtils;
import java.io.IOException;
import javax.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class RestActionImpl<T> implements RestAction<T> {

  private static final EndPoint failedInteractionEndPoint = EndPoint.createEmpty();
  private String[] params;

  @Override
  @Nonnull
  @SuppressWarnings("unchecked")
  public T complete() {
    CompiledRoute cRoute = this.constructRoute(this.params);
    final Requester r = new Requester();
    log.info("Attempt to construct requester for {} route", cRoute);
    r.setRoute(cRoute);
    try {
      JsonNode result = r.sendRequest();
      return InternalUtils.compileToEndPoint(result);
    } catch (IOException e) {
      log.error("we cannot handshake with api.", e);
      return (T) failedInteractionEndPoint;
    }

  }

}
