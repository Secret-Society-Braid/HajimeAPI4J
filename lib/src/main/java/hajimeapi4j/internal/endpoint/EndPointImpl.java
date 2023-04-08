package hajimeapi4j.internal.endpoint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hajimeapi4j.api.endpoint.EndPoint;
import hajimeapi4j.api.request.RestAction;
import hajimeapi4j.util.InternalUtils;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * APIにおける、全てに共通しているパラメータ情報やリクエスト情報を定義します。
 * <p>
 * 一部パラメータ情報は情報が他パラメータ情報と共通している場合があります。ご注意ください。
 * </p>
 *
 * @author Ranfa
 * @see EndPoint
 * @since 5.0.0.0-Alpha.1
 */
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@ToString
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class EndPointImpl implements EndPoint {

  protected String name;
  protected String type;
  protected int taxId;
  protected int songId;
  protected String link;
  protected String api;
  private static EndPoint emptyInstance;

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
  public boolean checkEmpty() {
    return InternalUtils.checkEmpty(this);
  }

  @Override
  @Nonnull
  public RestAction<EndPoint> fromApi() {
    return null;
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
