package hajimeapi4j.util;

import com.google.common.base.Strings;
import hajimeapi4j.exception.NoSuchParameterException;
import java.util.Arrays;
import java.util.List;
import org.jetbrains.annotations.Contract;

public class Checks {

  private static final String NULL_OR_EMPTY_EXCEPTION_MESSAGE = "%s must not be null or empty";
  private static final String ILLEGAL_ARGS_EXCEPTION_MESSAGE = "%s is not applicable for %s endpoint";

  private static final List<String> APPLICABLE_METHODS_FOR_LIST_ENDPOINT = List.of(
      "song",
      "live",
      "idol",
      "lyrics",
      "composer",
      "arrange",
      "disc",
      "cv"
  );

  private Checks() { /* do nothing */ }

  @Contract("null -> fail")
  public static void validateListType(String attempt) {
    if(!APPLICABLE_METHODS_FOR_LIST_ENDPOINT.contains(attempt))
      throw new IllegalArgumentException(String.format(ILLEGAL_ARGS_EXCEPTION_MESSAGE, attempt, "list"));
  }

  public static void validateInteger(int amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException(
          String.format(ILLEGAL_ARGS_EXCEPTION_MESSAGE, amount, "limit amount in list"));
    }
  }

  @Contract("null, _ -> fail")
  public static void nonNullOrEmpty(String[] args, String varName) {
    if (Arrays.stream(args).anyMatch(Strings::isNullOrEmpty)) {
      throw new IllegalArgumentException(String.format(NULL_OR_EMPTY_EXCEPTION_MESSAGE, varName));
    }
  }

  @Contract("null -> fail")
  public static void validateOrderParamString(String attempt) {
    if (Strings.isNullOrEmpty(attempt)) {
      throw new IllegalArgumentException(
          String.format(NULL_OR_EMPTY_EXCEPTION_MESSAGE, "order parameter"));
    }
    if (!(attempt.equals("asc") || attempt.equals("desc"))) {
      throw new NoSuchParameterException(
          String.format(ILLEGAL_ARGS_EXCEPTION_MESSAGE, attempt, "order parameter"));
    }
  }

  @Contract("null, _ -> fail")
  public static void validateOrderByParamStringWithListEndPoint(String attempt,
      boolean isTypeMusic) {
    if (Strings.isNullOrEmpty(attempt)) {
      throw new IllegalArgumentException(
          String.format(NULL_OR_EMPTY_EXCEPTION_MESSAGE, "orderBy parameter"));
    }
    if (isTypeMusic) {
      if (attempt.equals("title") || attempt.equals("date") || attempt.equals("rand")) {
        return;
      }
    } else if (attempt.equals("name") || attempt.equals("count")) {
      return;
    }
    throw new NoSuchParameterException(
        String.format(ILLEGAL_ARGS_EXCEPTION_MESSAGE, attempt, "orderBy parameter"));
  }
}
