package hajimeapi4j.util;

public class Checks {

  private static final String ILLEGAL_ARGS_EXCEPTION_MESSAGE = "%s is not applicable for %s endpoint";

  private Checks() { /* do nothing */ }

  public static void validateInteger(int amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException(
          String.format(ILLEGAL_ARGS_EXCEPTION_MESSAGE, amount, "limit amount in list"));
    }
  }

}
