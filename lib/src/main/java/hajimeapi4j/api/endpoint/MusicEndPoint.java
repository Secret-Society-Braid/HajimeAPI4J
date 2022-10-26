package hajimeapi4j.api.endpoint;

import hajimeapi4j.internal.datatype.Member;
import hajimeapi4j.internal.datatype.utilizations.Disc;
import hajimeapi4j.internal.datatype.utilizations.Live;
import java.util.List;
import java.util.Optional;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

/**
 * エンドポイント「/music」にて取得できる情報を定義したインターフェースです
 * <p>
 * {@code music}エンドポイントではエンドユーザーの指定によって出力される情報が変化するため、特に理由付けがなされていないかぎり{@link Optional}でラップされています。
 */
public interface MusicEndPoint extends EndPoint {

  @CheckReturnValue
  Optional<List<EndPoint>> getRemix();

  @CheckReturnValue
  Optional<List<EndPoint>> getOriginal();

  @CheckReturnValue
  Optional<List<EndPoint>> getLyrics();

  @CheckReturnValue
  Optional<List<EndPoint>> getComposer();

  @CheckReturnValue
  Optional<List<EndPoint>> getArrange();

  @CheckReturnValue
  Optional<String> getLyricsUrl();

  @Nonnull
  List<Member> getMember();

  boolean getDigitalReleaseExists();

  /**
   * 楽曲が収録されたCD情報を全て返します。
   *
   * @return {@link List}に詰められたCD収録情報
   */
  @CheckReturnValue
  Optional<List<Disc>> getDisc();

  @CheckReturnValue
  Optional<List<Live>> getLive();

}
