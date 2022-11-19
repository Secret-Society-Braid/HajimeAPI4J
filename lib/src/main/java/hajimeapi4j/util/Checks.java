package hajimeapi4j.util;

import com.google.common.base.Strings;
import java.util.Arrays;
import java.util.List;
import org.jetbrains.annotations.Contract;

public class Checks {

  private static final String NULL_OR_EMPTY_EXCEPTION_MESSAGE = "%s must not be null or empty";
  private static final String ILLEGAL_ARGS_EXCEPTION_MESSAGE = "%s is not applicable for %s endpoint";

  private static final List<String> APPLICABLE_METHODS_FOR_LIST_ENDPOINT = List.of(
      "songs",
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
    if(amount < 0)
      throw new IllegalArgumentException(String.format(ILLEGAL_ARGS_EXCEPTION_MESSAGE, amount, "limit amount in list"));
  }

  @Contract("null, _ -> fail")
  public static void nonNullOrEmpty(String[] args,String varName) {
    if(Arrays.stream(args).anyMatch(Strings::isNullOrEmpty))
      throw new IllegalArgumentException(String.format(NULL_OR_EMPTY_EXCEPTION_MESSAGE, varName));
  }
}
