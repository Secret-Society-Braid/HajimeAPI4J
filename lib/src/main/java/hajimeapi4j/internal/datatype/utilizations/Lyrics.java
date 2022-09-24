package hajimeapi4j.internal.datatype.utilizations;

import hajimeapi4j.api.endpoint.EndPoint;
import hajimeapi4j.internal.request.CompiledRoute;
import java.util.concurrent.CompletableFuture;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * ふじわらはじめAPI内で使用されるレスポンスのうち、{@code lyrics}パラメータで取得できる情報を定義したデータクラスです。
 * <p></p>
 * エンドポイントによって他パラメータ情報と同じ情報を返すものや、全く異なる情報を保持している場合もあります。ご注意ください。
 *
 * @author Ranfa
 * @see EndPoint
 * @since 5.0.0
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public class Lyrics implements EndPoint {

  private static final String MESSAGE = "You cannot invoke this method from Lyrics class";
  private final String name;
  private final String type;
  private final int taxId;
  private final String link;
  private final String api;

  @CheckReturnValue
  public static Lyrics createInstance(
      final String name,
      final String type,
      final int taxId,
      final String link,
      final String api
  ) {
    return new Lyrics(name, type, taxId, link, api);
  }

  @CheckReturnValue
  public static Lyrics createEmpty() {
    return new Lyrics("", "", -1, "", "");
  }

  /**
   * 名称、名前、ユニット名などとして登録されている情報を返します
   *
   * @return 名前、ユニット名など
   */
  @Nonnull
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * 出力された情報の出力モード（カテゴリ）を返します。
   *
   * @return 出力モード名 (lyrics) を返します。
   * @apiNote この情報は常に {@code lyrics}
   * となることが要求されます。これを満たさない場合、内部解析エラーが発生したとみなし、{@link IllegalStateException}をスローします。
   */
  @Nonnull
  @Override
  public String getType() {
    if (this.type.equals("lyrics")) {
      return this.type;
    } else {
      throw new IllegalStateException(
          "Internal wrapping exception had been occurred. but we couldn't indicate when this occurred.");
    }
  }

  /**
   * ふじわらはじめ楽曲DB内の内部管理IDを返します。
   *
   * @return 内部管理ID。存在しない場合は-1が返されます。
   */
  @Override
  public int getTaxId() {
    return this.taxId;
  }

  /**
   * ふじわらはじめDB内の内部管理IDを返します。 本来は情報が格納されていませんが、他データクラスとの混同防止、利便性向上のために用意されています。
   *
   * @return {@link Lyrics#getTaxId()}と同じ内容のID
   */
  @Override
  public int getSongId() {
    return getTaxId();
  }

  /**
   * ふじわらはじめDBページ内のリンクを返します。
   *
   * @return 楽曲のカテゴリページURL
   */
  @Nonnull
  @Override
  public String getLink() {
    return this.link;
  }

  /**
   * カテゴリ情報のJSON APIへアクセスするURIを返します
   * <p>
   * 直ぐに情報を取得する場合は{@link Lyrics#fromApi()}も使用することができます。
   * </p>
   *
   * @return JSON URI
   */
  @Nonnull
  @Override
  public String getApi() {
    return this.api;
  }

  /**
   * 取得したJSON API情報からそのままリクエストを送信するためのインスタンスを返却します
   *
   * @return JSON API から取得した情報を基に生成したリクエスト用インスタンス
   */
  @Nonnull
  @Override
  public EndPoint fromApi() {
    // TODO: implement uri parse function
    return this;
  }

  /**
   * リクエスト送信用のメソッドです。使用できません
   *
   * @return 返り値はありません。
   */
  @Override
  public EndPoint complete() {
    throw new UnsupportedOperationException(MESSAGE);
  }

  /**
   * リクエスト送信用のメソッドです。使用できません
   *
   * @return 返り値はありません。
   */
  @Override
  public CompletableFuture<EndPoint> submit() {
    throw new UnsupportedOperationException(MESSAGE);
  }

  /**
   * リクエスト送信用のメソッドです。使用できません
   *
   * @return 返り値はありません。
   */
  @Override
  public CompiledRoute constructRoute(String... params) {
    throw new UnsupportedOperationException(MESSAGE);
  }

  /**
   * 空のインスタンスを返します。存在するデータがなかったなどの場合に返される可能性があります。
   *
   * @return 空のインスタンス
   */
  public static Lyrics getEmptyInstance() {
    return new Lyrics("", "", -1, "", "");
  }

  /**
   * インスタンスが完全に空データであるかを検証します。
   *
   * @return 空の状態であれば {@code true}、そうでなければ {@code false}
   */
  @Override
  public boolean checkEmpty() {
    return this.equals(getEmptyInstance());
  }
}
