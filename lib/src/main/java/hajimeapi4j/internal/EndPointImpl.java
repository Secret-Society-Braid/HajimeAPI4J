package hajimeapi4j.internal;

import hajimeapi4j.api.endpoint.EndPoint;
import hajimeapi4j.internal.request.CompiledRoute;
import hajimeapi4j.util.InternalUtils;
import java.util.concurrent.CompletableFuture;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public class EndPointImpl implements EndPoint {

  private String name;
  private String type;
  private int taxId;
  private int songId;
  private String link;
  private String api;

  @CheckReturnValue
  public static EndPoint createInstance(String name, String type, int id, String link, String api) {
    return new EndPointImpl(name, type, id, id, link, api);
  }

  @CheckReturnValue
  public static EndPoint createEmpty() {
    return new EndPointImpl("", "", -1, -1, "", "");
  }

  @Override
  @Nonnull
  public String getName() {
    return this.name;
  }

  @Override
  @Nonnull
  public String getType() {
    return this.type;
  }

  @Override
  public int getTaxId() {
    return this.taxId;
  }

  @Override
  public int getSongId() {
    return this.songId;
  }

  @Nonnull
  @Override
  public String getLink() {
    return this.link;
  }

  @Nonnull
  @Override
  public String getApi() {
    return this.api;
  }

  @Override
  @CheckReturnValue
  public EndPoint fromApi() {
    // TODO: implement uri parse function
    return this;
  }

  @Override
  public EndPoint complete() {
    throw new UnsupportedOperationException(InternalUtils.getMethodNotAllowedMessage());
  }

  @Override
  public CompletableFuture<EndPoint> submit() {
    throw new UnsupportedOperationException(InternalUtils.getMethodNotAllowedMessage());
  }

  @Override
  public CompiledRoute constructRoute(String... params) {
    throw new UnsupportedOperationException(InternalUtils.getMethodNotAllowedMessage());
  }

  @Override
  public boolean checkEmpty() {
    return this.equals(createEmpty());
  }
}
