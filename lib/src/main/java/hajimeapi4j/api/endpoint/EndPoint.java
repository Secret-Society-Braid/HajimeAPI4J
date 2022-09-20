package hajimeapi4j.api.endpoint;

import hajimeapi4j.api.request.RestAction;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

public interface EndPoint extends RestAction<EndPoint> {

  @Nonnull
  String getName();

  @Nonnull
  String getType();

  int getTaxId();

  int getSongId();

  @Nonnull
  String getLink();

  @Nonnull
  String getApi();

  @Nonnull
  @CheckReturnValue
  EndPoint fromApi();
}
