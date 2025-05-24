package hajimeapi4j.api.endpoint;

import jakarta.annotation.Nonnull;
import java.util.Optional;

/**
 * エンドポイント「/movie」にて取得できる情報を定義したインターフェースです
 * <p>
 * {@code movie} エンドポイントではクエリパラメータの設定によってプレーンテキスト、もしくはHTML形式で情報が出力されます。このラッパーではどちらも {@link String}
 * として取得することができます。
 * <p>
 * 詳しいエンドポイント仕様については <a href="https://api.fujiwarahaji.me/v3/doc">APIv3ドキュメント</a> を参照してください。
 *
 * @author Ranfa
 * @see EndPoint
 * @see <a href="https://api.fujiwarahaji.me/v3/doc">APIv3ドキュメント</a>
 * @since v6.0.0.0-Alpha.1
 */
public interface MovieEndPoint extends EndPoint {

  /**
   * クエリパラメータにて設定したIDに紐づけられている動画情報を取得します。
   *
   * @return 動画情報。何も紐づけされていない（APIが 204 を返した）場合は空の {@link Optional} が返されます。
   * @see #getReturnStatusCode()
   */
  @Nonnull
  Optional<String> get();

  /**
   * APIより返却されたステータスコードを取得します。 返却される可能性があるステータスコードは以下の通りです。
   *
   * <ul>
   *   <li>200 - ID正常、動画情報あり</li>
   *   <li>204 - ID正常、動画情報なし</li>
   *   <li>400 - パラメータ（ID）不正</li>
   * </ul>
   *
   * @return APIより返却されたステータスコード
   */
  int getReturnStatusCode();
}
