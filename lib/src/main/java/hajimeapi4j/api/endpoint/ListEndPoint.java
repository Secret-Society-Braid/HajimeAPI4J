package hajimeapi4j.api.endpoint;

import java.util.Optional;
import hajimeapi4j.api.request.RestAction;

/**
 * エンドポイント「/list」にて取得できる情報を定義したインターフェースです
 * <p>
 *   {@code list}エンドポイントには出力モードが複数規定されていますが、リクエスト送信時に指定された出力モードでは取得できない情報を取得しようとした場合、{@link Optional#empty()}が返却されます。
 * </p>
 *
 * @since 5.0.0
 * @author Ranfa
 * @see RestAction
 * @see EndPoint
 */
public interface ListEndPoint extends EndPoint {

  /**
   * 楽曲のタイプ（cg, ml, sc. asなど）を返却します。
   * @return 取得した楽曲のタイプ。エラーなどで情報が取得できなかった場合、対応していない出力モードの場合は{@link Optional#empty()}。
   */
  Optional<String> getMusicType();

  /**
   * 楽曲のタイプ（cg, ml, sc, asなど）を返却します。
   * <p>
   *   このメソッドは{@link Optional#orElse(Object)}と同等の処理を行います。
   * </p>
   * @param nullValue 情報が取得出来ていない、もしくは対応していない出力モードの場合などに返却するデフォルト値
   * @return 取得した楽曲のタイプ。エラーなどで取得できなかった場合はパラメータで指定された値
   */
  Optional<String> getMusicType(String nullValue);

  Optional<String> getDate();

  Optional<String> getDate(String nullValue);

  Optional<String> getProduction();

  Optional<String> getProduction(String nullValue);

  Optional<String> getKana();

  Optional<String> getKana(String nullValue);

  Optional<String> getCv();

  Optional<String> getCv(String nullValue);

  Optional<String> getCvKana();

  Optional<String> getCvKana(String nullValue);

}
