package hajimeapi4j.api.request;

import java.util.concurrent.CompletableFuture;

public interface RestAction<T> {

  T complete();

  CompletableFuture<T> submit();

}
