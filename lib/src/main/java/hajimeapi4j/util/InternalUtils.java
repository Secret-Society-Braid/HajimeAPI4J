package hajimeapi4j.util;

import com.google.common.base.Joiner;
import hajimeapi4j.api.endpoint.EndPoint;
import hajimeapi4j.api.request.RestAction;
import hajimeapi4j.internal.request.RestActionImpl;
import hajimeapi4j.internal.request.Route;
import hajimeapi4j.util.enums.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadFactory;
import javax.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InternalUtils {

  private static final String MESSAGE_METHOD_NOT_ALLOWED = "You cannot invoke this method from Lyrics class";
  private static final String MESSAGE_NULL_RAW_RESPONSE_NOT_ALLOWED = "raw response must not be null";
  private static final String MESSAGE_TOOK_TIME_LOGGING = "Parsing completed. took {} ms";
  private static final String MESSAGE_GENERATED_DATA_LOGGING = "generated data: {}";

  private InternalUtils() { /* do nothing */}

  public static String getMethodNotAllowedMessage() {
    return MESSAGE_METHOD_NOT_ALLOWED;
  }

  public static String getMessageNullRawResponseNotAllowedMessage() {
    return MESSAGE_NULL_RAW_RESPONSE_NOT_ALLOWED;
  }

  public static String getMessageTookTimeLogging() {
    return MESSAGE_TOOK_TIME_LOGGING;
  }

  public static String getMessageGeneratedDataLogging() {
    return MESSAGE_GENERATED_DATA_LOGGING;
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
  public static <T> RestAction<T> parseFromUriString(String uri) {
    // parse uri
    // example: https://api.fujiwarahaji.me/v2/music?id=3525
    String shortened = uri.substring(28); // example: v2/music?id=3525
    String[] splitWithSlash = shortened.split("/"); // example: [v2] [music?id=3525]
    log.info("used api version: {}", splitWithSlash[0]);
    String[] splitWithQuestionMark = splitWithSlash[1].split("\\?"); // example: [music] [id=3525]
    log.info("used api endpoint: {}", splitWithQuestionMark[0]);
    Map<String, String> queries = mapFromPlainText(splitWithQuestionMark[1]);
    Route apiRoute = Route.custom(Method.valueOf(splitWithQuestionMark[0]));
    RestAction<T> result = new RestActionImpl<>(apiRoute, queries);
    log.info("parsed RestAction instance: {}", result);
    return result;
  }

  @Nonnull
  static Map<String, String> mapFromPlainText(String plain) {
    Map<String, String> result = new HashMap<>();
    String[] splitWithAmpersand = plain.split("&");
    Arrays.stream(splitWithAmpersand)
        .forEachOrdered(str -> {
          String[] splitWithEqual = str.split("=");
          result.put(splitWithEqual[0], splitWithEqual[1]);
        });
    return result;
  }

  @Nonnull
  public static ThreadFactory createInternalThreadFactory(String identifier) {
    return new CountingThreadFactory(identifier);
  }

  @Nonnull
  public static String concatWithSeparators(List<String> list, final String separator) {
    if (list.size() == 1) {
      return list.get(0);
    }
    final Joiner joiner = Joiner.on(separator).skipNulls();
    return joiner.join(list);
  }
}
