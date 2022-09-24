package hajimeapi4j.api.endpoint;

import hajimeapi4j.api.request.RestAction;
import java.util.Optional;

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

  Optional<String> getDate();

  Optional<String> getProduction();

  Optional<String> getKana();

  Optional<String> getCv();

  Optional<String> getCvKana();

}
