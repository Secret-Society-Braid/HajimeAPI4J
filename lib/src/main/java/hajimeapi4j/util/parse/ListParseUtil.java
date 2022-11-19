package hajimeapi4j.util.parse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import hajimeapi4j.api.endpoint.ListEndPoint;
import java.io.IOException;
import java.util.ArrayList;
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
    // adding elements
    result = mapper.readValue(rawResponse.traverse(), new TypeReference<>() {
    });
    log.debug("Parsing completed. took {} ms", (System.currentTimeMillis() - start));
    return result;
  }
}
