package hajimeapi4j.util;

import com.fasterxml.jackson.databind.JsonNode;
import hajimeapi4j.api.endpoint.EndPoint;
import hajimeapi4j.api.endpoint.ListEndPoint;
import hajimeapi4j.api.request.RestAction;
import hajimeapi4j.internal.endpoint.ListEndPointImpl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InternalUtils {

  private static final String MESSAGE_METHOD_NOT_ALLOWED = "You cannot invoke this method from Lyrics class";

  private InternalUtils() { /* do nothing */}

  public static String getMethodNotAllowedMessage() {
    return MESSAGE_METHOD_NOT_ALLOWED;
  }

  public static boolean checkEmpty(EndPoint testee) {
    // String vars will need to check only whether empty or not since we contract this as @Nonnull
    return testee.getName().isEmpty()
        && ((testee.getSongId() == -1) || (testee.getTaxId() == -1))
        && testee.getType().isEmpty()
        && testee.getLink().isEmpty()
        && testee.getApi().isEmpty();
  }

  @Nonnull
  public static RestAction<EndPoint> parseFromUriString(String uri) {
    // parse uri
    // example: https://api.fujiwarahaji.me/v2/music?id=3525
    String shortened = uri.substring(28); // example: v2/music?id=3525
    String[] splitWithSlash = shortened.split("/"); // example: [v2] [music?id=3525]
    log.info("used api version: {}", splitWithSlash[0]);
    String[] splitWithQuestionMark = splitWithSlash[1].split("\\?"); // example: [music] [id=3525]
    log.info("used api endpoint: {}", splitWithQuestionMark[0]);
    Map<String, String> queries = mapFromPlainText(splitWithQuestionMark[1]);
    // TODO: we will need more implementation after completing implementation for builder classes
    return EndPoint.createEmpty(); // FIXME: replace this temporal return to applicable one
  }

  @Nonnull
  static Map<String, String> mapFromPlainText(String plain) {
    Map<String, String> result = new LinkedHashMap<>();
    String[] splitWithAmpersand = plain.split("&");
    Arrays.stream(splitWithAmpersand)
        .forEachOrdered(str -> {
          String[] splitWithEqual = str.split("=");
          result.put(splitWithEqual[0], splitWithEqual[1]);
        });
    return result;
  }

  @Nonnull
  @CheckReturnValue
  public static List<ListEndPoint> generateListEndPointResponse(JsonNode rawResponse) {
    // check for potentially null access
    if (rawResponse == null) {
      throw new IllegalArgumentException("raw response must not be null");
    }
    List<ListEndPoint> result = new ArrayList<>();
    Iterator<JsonNode> iteration = rawResponse.elements();
    // adding elements
    while (iteration.hasNext()) {
      JsonNode tmp = iteration.next();
      boolean isMusicType = false;
      boolean isIdolType = false;
      // with idol type query it doesn't have the type parameter in the response,
      // so we locally check whether type parameter exists or not
      if (tmp.get("type") != null) {
        isMusicType = tmp.get("type").asText().equals("music");
      } else {
        isIdolType = true;
      }
      ListEndPoint eachInstance = ListEndPointImpl.createInstance(
          tmp.get("name").asText(),
          isIdolType ? "idol" : tmp.get("type").asText(),
          tmp.get(isMusicType ? "song_id" : "tax_id").asInt(),
          tmp.get("link").asText(),
          tmp.get("api").asText(),
          // music_type will appear when type is music
          isMusicType ? tmp.get("music_type").asText() : null,
          // live will appear when type is live
          (!isIdolType && tmp.get("type").asText().equals("live")) ? tmp.get("date").asText()
              : null,
          // these four params will appear when type is idol
          isIdolType ? tmp.get("production").asText() : null,
          isIdolType ? tmp.get("kana").asText() : null,
          isIdolType ? tmp.get("cv").asText() : null,
          isIdolType ? tmp.get("cvkana").asText() : null
      );
      log.debug("listEndPoint response instance has been created. : {}", eachInstance);
      result.add(eachInstance);
    }
    return result;
  }
}
