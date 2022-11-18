package hajimeapi4j.util.parse;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import hajimeapi4j.api.endpoint.ListEndPoint;
import hajimeapi4j.internal.endpoint.ListEndPointImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ListParseUtil {

  private static final ObjectMapper mapper;

  static {
    mapper = new ObjectMapper();
    mapper.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);
    mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
  }

  private ListParseUtil() { /* util class */}

  public static List<ListEndPoint> createResponse(JsonNode rawResponse) throws IOException {
    if (rawResponse == null) {
      throw new IllegalArgumentException("raw response must not be null");
    }
    long start = System.currentTimeMillis();
    log.debug("attempt to parse list data");
    List<ListEndPoint> result = new ArrayList<>();
    Iterator<JsonNode> iterator = rawResponse.elements();
    // adding elements
    while (iterator.hasNext()) {
      JsonNode tmpNode = iterator.next();
      ListEndPoint instance = mapper.readValue(tmpNode.traverse(), ListEndPointImpl.class);
      log.debug("ListEndPoint instance created: {}", instance);
      result.add(instance);
    }
    log.debug("Parsing completed. took {} ms", (System.currentTimeMillis() - start));
    return result;
  }
}
