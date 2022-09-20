package hajimeapi4j.api.request;

import hajimeapi4j.internal.request.CompiledRoute;
import java.util.concurrent.CompletableFuture;

public interface RestAction<T> {

  T complete();

  CompletableFuture<T> submit();

  CompiledRoute constructRoute(String... params);

}
