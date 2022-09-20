package hajimeapi4j.internal.request;

import com.google.common.base.Joiner;
import hajimeapi4j.util.enums.Method;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EqualsAndHashCode
public class CompiledRoute {

  private final Route baseRoute;
  private final String compiledRouteString;

  CompiledRoute(Route baseRoute, String compiledRouteString) {
    this.baseRoute = baseRoute;
    this.compiledRouteString = compiledRouteString;
  }

  @Nonnull
  @CheckReturnValue
  public CompiledRoute withQueryParams(String... params) {
    if(params.length < 2)
      throw new IllegalArgumentException("Query parameters must have more than 2 elements");
    if(params.length % 2 != 0)
      throw new IllegalArgumentException("Query parameters must be a multiple of 2");

    Map<String, String> queryMap = convertToMap(params);
    Joiner.MapJoiner mapJoiner = Joiner.on("&").withKeyValueSeparator("=").useForNull("");
    String newRoute = Joiner.on("").join(baseRoute.toString(), "?", mapJoiner.join(queryMap));
    return new CompiledRoute(baseRoute, newRoute);
  }

  @Nonnull
  public String getCompiledRouteString() {
    return this.compiledRouteString;
  }

  @Nonnull
  public Route getBaseRoute() {
    return this.baseRoute;
  }

  @Nonnull
  public Method getMethod() {
    return baseRoute.getMethod();
  }

  @Nonnull
  private static Map<String, String> convertToMap(String[] arr) {
    Map<String, String> res = new HashMap<>();
    for(int i = 0; i < arr.length; i++)
      res.put(arr[i], arr[++i]);
    return res;
  }

}
