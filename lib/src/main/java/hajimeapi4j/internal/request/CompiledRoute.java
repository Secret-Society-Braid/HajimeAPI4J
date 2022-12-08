package hajimeapi4j.internal.request;

import com.google.common.base.Joiner;
import hajimeapi4j.util.enums.Method;
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
  public CompiledRoute withQueryParams(Map<String, String> queryMap) {
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

}
