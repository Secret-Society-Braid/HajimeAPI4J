package hajimeapi4j.api.endpoint;

import hajimeapi4j.api.request.RestAction;
import hajimeapi4j.internal.datatype.Member;
import hajimeapi4j.internal.datatype.utilizations.Music;
import hajimeapi4j.internal.endpoint.TaxEndPointImpl;
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
   * ライブの開催日時情報を取得します。「yyyy-mm-dd」形式。
   * <p>
   * 取得可能カテゴリタイプ
   * <ul>
   *   <li>live</li>
   * </ul>
   *
   * @return ライブの開催日時情報
   */
  Optional<String> getDate();

  /**
   * ライブの会場情報を取得します。
   * <p>
   * 取得可能カテゴリタイプ
   * <ul>
   *   <li>live</li>
   * </ul>
   *
   * @return ライブの会場情報
   */
  Optional<String> getPlace();

  /**
   * 歌唱曲、もしくはライブ披露時の参加メンバーを取得します。
   * <p>
   * 取得可能カテゴリタイプ
   * <ul>
   *   <li>live</li>
   *   <li>unit</li>
   * </ul>
   *
   * @return 参加メンバー、ユニットメンバー情報
   */
  Optional<List<? extends Member>> getMember();

  /**
   * ライブなどで披露した際のセットリスト情報がデータベースに存在するかどうかを取得します。
   * <p>
   * 取得可能カテゴリタイプ
   * <ul>
   *   <li>live</li>
   * </ul>
   *
   * @return セットリストの有無
   */
  Optional<Boolean> getSetListExists();

  /**
   * 同じ名義などで作業した楽曲一覧を表示するためのカテゴリ情報を出力します。
   * <p>
   * 同じ名義で作詞を行った情報を返却します
   * <p>
   * 取得可能カテゴリタイプ
   * <ul>
   *   <li>lyrics</li>
   * </ul>
   *
   * @return 同じ名義で作詞を行った楽曲情報
   */
  Optional<EndPoint> getLyrics();

  /**
   * 同じ名義などで作業した楽曲一覧を表示するためのカテゴリ情報を出力します。
   * <p>
   * 同じ名義で作曲を行った情報を返却します
   * <p>
   * 取得可能カテゴリタイプ
   * <ul>
   *   <li>composer</li>
   * </ul>
   *
   * @return 同じ名義で作曲を行った楽曲情報
   */
  Optional<EndPoint> getComposer();

  /**
   * 同じ名義などで作業した楽曲一覧を表示するためのカテゴリ情報を出力します。
   * <p>
   * 同じ名義で編曲を行った情報を返却します
   * <p>
   * 取得可能カテゴリタイプ
   * <ul>
   *   <li>arrange</li>
   * </ul>
   *
   * @return 同じ編曲で作詞を行った楽曲情報
   */
  Optional<EndPoint> getArrange();

  /**
   * 楽曲情報の出力を行います
   *
   * @return 指定された楽曲情報
   */
  @Nonnull
  List<Music> getMusic();

}
