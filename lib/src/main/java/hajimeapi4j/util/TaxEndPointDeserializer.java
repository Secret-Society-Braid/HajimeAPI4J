package hajimeapi4j.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import hajimeapi4j.api.endpoint.TaxEndPoint;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TaxEndPointDeserializer extends StdDeserializer<TaxEndPoint> {

  protected TaxEndPointDeserializer() {
    super(TaxEndPoint.class);
  }

  @Override
  public TaxEndPoint deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    while (p.nextToken() != null) {
      log.error("----------------");
      log.error("CURRENT_TOKEN: {}", p.getCurrentToken());
      log.error("CURRENT_NAME: {}", p.getCurrentName());
      log.error("CURRENT_VALUE: {}", p.getValueAsString());
      log.error("CURRENT_TEXT: {}", p.getText());
      log.error("CURRENT_LOCATION: {}", p.getCurrentLocation());
      log.error("----------------");
    }
    return null;
  }
}
