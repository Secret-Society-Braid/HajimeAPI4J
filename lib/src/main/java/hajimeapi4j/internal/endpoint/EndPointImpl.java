package hajimeapi4j.internal.endpoint;

import hajimeapi4j.api.endpoint.EndPoint;
import hajimeapi4j.api.request.RestAction;
import hajimeapi4j.internal.request.CompiledRoute;
import hajimeapi4j.util.InternalUtils;
import java.util.concurrent.CompletableFuture;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

/**
 * APIにおける、全てに共通しているパラメータ情報やリクエスト情報を定義します。
 * <p>
 * 一部パラメータ情報は情報が他パラメータ情報と共通している場合があります。ご注意ください。
 * </p>
 *
 * @author Ranfa
 * @see EndPoint
 * @since 5.0.0
 */
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@ToString
public class EndPointImpl implements EndPoint {

  protected String name;
  protected String type;
  protected int taxId;
  protected int songId;
  protected String link;
  protected String api;
  private static EndPoint emptyInstance;

  @CheckReturnValue
  public static EndPoint createInstance(String name, String type, int id, String link, String api) {
    return new EndPointImpl(name, type, id, id, link, api);
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

  /**
   * 取得したURI情報を基に、新しいリクエストを送信するためのインスタンスを作成します。
   *
   * @return URI情報による新しいインスタンス
   */
  @NotNull
  @Override
  @CheckReturnValue
  public RestAction<EndPoint> fromApi() {
    // TODO: implement uri parse function
    return this;
  }

  /**
   * @return リクエスト送信用のメソッドです。使用できません。
   */
  @Override
  public EndPoint complete() {
    throw new UnsupportedOperationException(InternalUtils.getMethodNotAllowedMessage());
  }

  /**
   * @return リクエスト送信用のメソッドです。使用できません。
   */
  @Override
  public CompletableFuture<EndPoint> submit() {
    throw new UnsupportedOperationException(InternalUtils.getMethodNotAllowedMessage());
  }

  /**
   * @param params パラメータ情報
   * @return リクエスト送信用のメソッドです。使用できません。
   */
  @Override
  public CompiledRoute constructRoute(String... params) {
    throw new UnsupportedOperationException(InternalUtils.getMethodNotAllowedMessage());
  }

  @Override
  public boolean checkEmpty() {
    return InternalUtils.checkEmpty(this);
  }

  /**
   * 空の{@link EndPoint}インスタンスを返します。
   *
   * @return 空のインスタンス
   */
  @CheckReturnValue
  public static EndPoint createEmpty() {
    if (emptyInstance == null) {
      emptyInstance = new EndPointImpl("", "", -1, -1, "", "");
    }
    return emptyInstance;
  }
}
