package hajimeapi4j.util;

public class InternalUtils {

  private static final String MESSAGE_METHOD_NOT_ALLOWED = "You cannot invoke this method from Lyrics class";

  private InternalUtils() { /* do nothing */}

  public static String getMethodNotAllowedMessage() {
    return MESSAGE_METHOD_NOT_ALLOWED;
  }
}
