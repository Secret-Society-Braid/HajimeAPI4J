package hajimeapi4j.util;

import hajimeapi4j.api.endpoint.EndPoint;
import hajimeapi4j.api.request.RestAction;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InternalUtils {

  private static final String MESSAGE_METHOD_NOT_ALLOWED = "You cannot invoke this method from Lyrics class";
  private static final String MESSAGE_NULL_RAW_RESPONSE_NOT_ALLOWED = "raw response must not be null";

  private InternalUtils() { /* do nothing */}

  public static String getMethodNotAllowedMessage() {
    return MESSAGE_METHOD_NOT_ALLOWED;
  }

  public static String getMessageNullRawResponseNotAllowedMessage() {
    return MESSAGE_NULL_RAW_RESPONSE_NOT_ALLOWED;
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

}
