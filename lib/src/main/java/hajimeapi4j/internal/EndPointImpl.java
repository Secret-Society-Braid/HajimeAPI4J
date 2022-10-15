package hajimeapi4j.internal;

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

  /**
   * 名称、名前、ユニット名などとして登録されている情報を取得します
   *
   * @return 名前の情報
   */
  @Override
  @Nonnull
  public String getName() {
    return this.name;
  }

  /**
   * 出力しているカテゴリ名（出力モード）を取得します。
   *
   * @return カテゴリ名
   * @apiNote 一部のカテゴリではここの情報が常に一定となる場合があります
   */
  @Override
  @Nonnull
  public String getType() {
    return this.type;
  }

  /**
   * カテゴリID（楽曲データベースID）を返します。
   *
   * @return カテゴリID
   */
  @Override
  public int getTaxId() {
    return this.taxId;
  }

  /**
   * 内部楽曲ID（楽曲データベースID）を返します
   *
   * @return 楽曲ID
   */
  @Override
  public int getSongId() {
    return this.songId;
  }

  /**
   * ふじわらはじめ楽曲DB内のページURLを取得します。
   *
   * @return ページURL
   */
  @Nonnull
  @Override
  public String getLink() {
    return this.link;
  }

  /**
   * そのカテゴリ情報のJSON URIを取得します。
   *
   * @return URI
   * @apiNote そのまま情報を取得する場合は {@link EndPoint#fromApi()}メソッドにてそのまま情報を取得することができます
   */
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

  /**
   * インスタンスが空かどうかを判定します。 空である基準は
   * <li>
   *   <ul> {@link #name}が空文字</ul>
   *   <ul> {@link #type}が空文字</ul>
   *   <ul> {@link #taxId}が {@code -1}</ul>
   *   <ul> {@link #songId}が {@code -1}</ul>
   *   <ul> {@link #link}が空文字</ul>
   *   <ul> {@link #api}が空文字</ul>
   * </li>
   * となります。
   *
   * @return このインスタンスが空である場合は {@code true}、そうでない場合は{@code false}
   */
  @Override
  public boolean checkEmpty() {
    return InternalUtils.checkEmpty(this);
  }

  @CheckReturnValue
  public static EndPoint createEmpty() {
    if (emptyInstance == null) {
      emptyInstance = new EndPointImpl("", "", -1, -1, "", "");
    }
    return emptyInstance;
  }
}
