package hajimeapi4j.util;

import com.google.common.base.Joiner;
import hajimeapi4j.api.endpoint.EndPoint;
import java.util.List;
import java.util.concurrent.ThreadFactory;
import javax.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InternalUtils {

  private static final String MESSAGE_NULL_RAW_RESPONSE_NOT_ALLOWED = "raw response must not be null";
  private static final String MESSAGE_TOOK_TIME_LOGGING = "Parsing completed. took {} ms";
  private static final String MESSAGE_GENERATED_DATA_LOGGING = "generated data: {}";

  private InternalUtils() { /* do nothing */}

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
