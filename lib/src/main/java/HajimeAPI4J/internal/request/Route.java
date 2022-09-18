package hajimeapi4j.internal.request;

import hajimeapi4j.util.enums.Method;
import java.util.Objects;
import javax.annotation.Nonnull;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EqualsAndHashCode
public class Route {

  private final Method method;

  public static Route custom(Method method) {
    return new Route(Objects.requireNonNull(method));
  }

  public static Route listRoute() {
    return custom(Method.LIST);
  }

  public static Route taxRoute() {
    return custom(Method.TAX);
  }

  public static Route musicRoute() {
    return custom(Method.MUSIC);
  }

  private Route(Method method) {
    this.method = method;
  }

  @Nonnull
  public CompiledRoute compile() {
    return new CompiledRoute(this, this.toString());
  }

  @Nonnull
  public Method getMethod() {
    return this.method;
  }

  @Override
  public String toString() {
    return "/" + this.method.toString();
  }
}
