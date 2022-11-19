package hajimeapi4j.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import hajimeapi4j.api.endpoint.ListEndPoint;
import hajimeapi4j.api.endpoint.MusicEndPoint;
import hajimeapi4j.api.endpoint.TaxEndPoint;
import hajimeapi4j.internal.endpoint.MusicEndPointImpl;
import hajimeapi4j.internal.endpoint.TaxEndPointImpl;
import java.io.IOException;
import java.util.List;
import javax.annotation.CheckReturnValue;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParseUtil {

  private static final ObjectMapper mapper;

  static {
    mapper = new ObjectMapper();
    mapper.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);
    mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
  }

  private ParseUtil() { /* util class */}

  @CheckReturnValue
  public static List<ListEndPoint> createListResponse(JsonNode rawResponse) throws IOException {
    if (rawResponse == null) {
      throw new IllegalArgumentException(
          InternalUtils.getMessageNullRawResponseNotAllowedMessage());
    }
    long start = System.currentTimeMillis();
    log.debug("attempt to parse list data");
    // adding elements
    List<ListEndPoint> result = mapper.readValue(rawResponse.traverse(),
        new TypeReference<>() {
        });
    log.debug(InternalUtils.getMessageTookTimeLogging(), (System.currentTimeMillis() - start));
    log.debug(InternalUtils.getMessageGeneratedDataLogging(), result);
    return result;
  }

  @CheckReturnValue
  public static TaxEndPoint createTaxResponse(JsonNode rawResponse) throws IOException {
    if (rawResponse == null) {
      throw new IllegalArgumentException(
          InternalUtils.getMessageNullRawResponseNotAllowedMessage());
    }
    long start = System.currentTimeMillis();
    log.debug("attempt to parse tax data");
    // adding elements
    TaxEndPoint result = mapper.readValue(rawResponse.traverse(), TaxEndPointImpl.class);
    log.debug(InternalUtils.getMessageTookTimeLogging(), (System.currentTimeMillis() - start));
    log.debug(InternalUtils.getMessageGeneratedDataLogging(), result);
    return result;
  }

  @CheckReturnValue
  public static MusicEndPoint createMusicResponse(JsonNode rawResponse) throws IOException {
    if (rawResponse == null) {
      throw new IllegalArgumentException(
          InternalUtils.getMessageNullRawResponseNotAllowedMessage());
    }
    long start = System.currentTimeMillis();
    log.debug("attempt to parse songs data");
    // adding elements
    MusicEndPoint result = mapper.readValue(rawResponse.traverse(), MusicEndPointImpl.class);
    log.debug(InternalUtils.getMessageTookTimeLogging(), (System.currentTimeMillis() - start));
    log.debug(InternalUtils.getMessageGeneratedDataLogging(), result);
    return result;
  }
}
