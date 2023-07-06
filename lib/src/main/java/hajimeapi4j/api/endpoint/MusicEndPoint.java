package hajimeapi4j.api.endpoint;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import hajimeapi4j.api.request.RestAction;
import hajimeapi4j.internal.datatype.Member;
import hajimeapi4j.internal.datatype.utilizations.Live;
import hajimeapi4j.internal.endpoint.MusicEndPointImpl;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;

/**
 * エンドポイント「/music」にて取得できる情報を定義したインターフェースです
 * <p>
 * {@code music}エンドポイントではエンドユーザーの指定によって出力される情報が変化するため、特に理由付けがなされていないかぎり{@link Optional}でラップされています。
 *
 * @author Ranfa
 * @see RestAction
 * @see EndPoint
 * @see MusicEndPointImpl
 * @since 5.0.0.0-Alpha.1
 */
@JsonDeserialize(as = MusicEndPointImpl.class)
public interface MusicEndPoint extends EndPoint {

  /**
   * 指定された楽曲IDがリミックス楽曲である場合に、原曲の情報を出力します。
   * <p>
   * その他のリミックス情報は出力しませんのでお気をつけください。
   *
   * @return リミックス元楽曲の、原曲情報
   */
  @CheckReturnValue
  Optional<List<EndPoint>> getRemix();

  /**
   * 指定された楽曲IDがオリジナル楽曲である場合に、リミックスバージョン一覧を出力します。
   * <p>
   * {@link #getRemix()}メソッドとの混同にお気をつけください。
   *
   * @return リミックスバージョンの一覧
   */
  @CheckReturnValue
  Optional<List<EndPoint>> getOriginal();

  /**
   * 作詞者の情報を出力します。
   * <p>
   * 1人での作業の場合も{@link List}形式となります。
   *
   * @return 楽曲に携わった作詞者情報
   */
  @CheckReturnValue
  Optional<List<EndPoint>> getLyrics();

  /**
   * リアレンジ楽曲を含む、作曲者情報を出力します。
   * <p>
   * 一人での作業の場合も{@link List}形式となります。
   *
   * @return 楽曲に携わった作曲者情報
   */
  @CheckReturnValue
  Optional<List<EndPoint>> getComposer();

  /**
   * リアレンジ楽曲のアレンジメント含む、編曲者情報を出力します。
   * <p>
   * 一人での作業の場合も{@link List}形式となります。
   *
   * @return 楽曲に携わった編曲者情報
   */
  @CheckReturnValue
  Optional<List<EndPoint>> getArrange();

  /**
   * 歌詞が存在する曲について、歌詞サイトのURLを取得します。
   * <p>
   * リミックス楽曲では出力されないため、適宜原曲情報から参照する必要があります。
   *
   * @return 歌詞サイトへのリンクURL
   */
  @CheckReturnValue
  Optional<String> getLyricsUrl();

  /**
   * CD、配信、ゲーム内参加メンバーを取得します。
   * <p>
   * この情報はnullになりえません。
   *
   * @return 参加メンバー一覧
   */
  @Nonnull
  List<Member> getMember();

  /**
   * 指定された楽曲が、デジタル配信されているかどうかを取得します。
   *
   * @return デジタル配信が存在する場合は {@code true}、存在しない場合は{@code false}
   */
  boolean getDigitalReleaseExists();

  /**
   * 楽曲が収録されたCD情報を全て返します。
   *
   * @return {@link List}に詰められたCD収録情報
   */
  @CheckReturnValue
  Optional<List<EndPoint>> getDisc();

  /**
   * 指定された楽曲が披露されたライブの情報を出力します。
   *
   * @return 披露された全てのライブの情報
   */
  @CheckReturnValue
  Optional<List<Live>> getLive();

}
