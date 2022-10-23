package hajimeapi4j.api.endpoint;

import hajimeapi4j.api.request.RestAction;
import hajimeapi4j.internal.ListEndPointImpl;
import java.util.Optional;
import javax.annotation.CheckReturnValue;

/**
 * エンドポイント「/list」にて取得できる情報を定義したインターフェースです
 * <p>
 * {@code list}エンドポイントには出力モードが複数規定されていますが、リクエスト送信時に指定された出力モードでは取得できない情報を取得しようとした場合、{@link
 * Optional#empty()}が返却されます。
 * </p>
 *
 * @author Ranfa
 * @see RestAction
 * @see EndPoint
 * @see ListEndPointImpl
 * @since 5.0.0
 */
public interface ListEndPoint extends EndPoint {

  /**
   * 楽曲のタイプ（cg, ml, sc. asなど）を返却します。
   *
   * @return 取得した楽曲のタイプ。エラーなどで情報が取得できなかった場合、対応していない出力モードの場合は{@link Optional#empty()}
   */
  @CheckReturnValue
  Optional<String> getMusicType();

  /**
   * ライブの開催日時を「yyyy-mm-dd」形式で返却します
   *
   * @return ライブの開催日時。エラーなどで情報が取得できなかった場合、対応していない出力モードの場合は {@link Optional#empty()}
   */
  @CheckReturnValue
  Optional<String> getDate();

  /**
   * アイドルの所属プロダクションを返却します
   *
   * @return アイドルの所属プロダクション。エラーなどで情報が取得できなかった場合、対応していない出力モードの場合は {@link Optional#empty()}
   */
  @CheckReturnValue
  Optional<String> getProduction();

  /**
   * アイドル名の読み仮名を返却します
   *
   * @return アイドル名の読み仮名。エラーなどで情報が取得できなかった場合、対応していない出力モードの場合は {@link Optional#empty()}
   */
  @CheckReturnValue
  Optional<String> getKana();

  /**
   * アイドルのCV声優名を返却します
   *
   * @return CV声優名。エラーなどで情報が取得できなかった場合、対応していない出力モードの場合は {@link Optional#empty()}
   */
  @CheckReturnValue
  Optional<String> getCv();

  /**
   * CV声優名の読み仮名を返却します
   *
   * @return 声優名の読み仮名。エラーなどで情報が取得できなかった場合、対応していない出力モードの場合は {@link Optional#empty()}
   */
  @CheckReturnValue
  Optional<String> getCvKana();
}
