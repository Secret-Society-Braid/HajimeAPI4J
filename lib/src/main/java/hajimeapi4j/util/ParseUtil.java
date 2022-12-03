package hajimeapi4j.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import hajimeapi4j.api.endpoint.ListEndPoint;
import hajimeapi4j.api.endpoint.MusicEndPoint;
import hajimeapi4j.api.endpoint.TaxEndPoint;
import hajimeapi4j.exception.NoSuchParameterException;
import hajimeapi4j.internal.endpoint.MusicEndPointImpl;
import hajimeapi4j.internal.endpoint.TaxEndPointImpl;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
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

  @Nonnull
  public static Map<String, String> createParameterMap(String... values) {
    long start = System.currentTimeMillis();
    if (values == null) {
      throw new IllegalArgumentException("cannot create map instance from null!");
    }
    if (values.length < 2) {
      throw new NoSuchParameterException("cannot create parameter map with less than 2 node!");
    }
    if (values.length % 2 != 0) {
      throw new NoSuchParameterException("cannot create parameter map with odd length of nodes!");
    }
    Map<String, String> result = new HashMap<>();
    Iterator<String> stringIterator = Arrays.asList(values).iterator();
    while (stringIterator.hasNext()) {
      result.put(stringIterator.next(), stringIterator.next());
    }
    log.debug("constructing map instance completed. took {} ms",
        (System.currentTimeMillis() - start));
    log.debug("construct map instance: {}", result);
    return result;
  }
}
