package hajimeapi4j.api.endpoint;

import hajimeapi4j.api.request.RestAction;
import hajimeapi4j.internal.TaxEndPointImpl;
import hajimeapi4j.internal.datatype.Member;
import hajimeapi4j.internal.datatype.utilizations.Music;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nonnull;

/**
 * エンドポイント「/tax」にて取得できる情報を定義したインターフェースです
 * <p>
 * {@code tax}エンドポイントでは要求するデータタイプによって取得できる情報が多岐にわたるため、それぞれのGetterメソッドのドキュメントで取得できるtype情報などを羅列しています。
 *
 * @author Ranfa
 * @see RestAction
 * @see EndPoint
 * @see TaxEndPointImpl
 * @since 5.0.0
 */
public interface TaxEndPoint extends EndPoint {

  /**
   * 読み仮名情報を取得します。
   * <p>
   * 取得可能カテゴリタイプ
   * <ul>
   *   <li>idol</li>
   *   <li>unit</li>
   * </ul>
   * @return 読み仮名情報。エラーなどで情報が取得できなかった場合、対応していない出力モードの場合は {@link Optional#empty()}
   */
  Optional<String> getKana();

  /**
   * 声優名情報を取得します。
   * <p>
   * 取得可能カテゴリタイプ
   * <ul>
   *   <li>idol</li>
   * </ul>
   * @return 声優名情報
   */
  Optional<String> getCv();

  /**
   * 声優名の読み仮名情報を取得します。
   * <p>
   * 取得可能カテゴリタイプ
   * <ul>
   *   <li>idol</li>
   * </ul>
   * @return 声優名の読み仮名情報
   */
  Optional<String> getCvKana();

  /**
   * アイドルの所属プロダクション情報を取得します。
   * <p>
   * 取得可能カテゴリタイプ
   * <ul>
   *   <li>idol</li>
   * </ul>
   * @return アイドルの所属プロダクション情報
   */
  Optional<String> getProduction();

  /**
   * ライブの開催日時情報を取得します。
   * <p>
   * 取得可能カテゴリタイプ
   * <ul>
   *   <li>live</li>
   * </ul>
   * @return ライブの開催日時情報
   */
  Optional<String> getDate();

  Optional<String> getPlace();

  Optional<List<? extends Member>> getMember();

  Optional<Boolean> getSetListExists();

  Optional<EndPoint> getLyrics();

  Optional<EndPoint> getComposer();

  Optional<EndPoint> getArrange();

  @Nonnull
  List<Music> getMusic();

}
