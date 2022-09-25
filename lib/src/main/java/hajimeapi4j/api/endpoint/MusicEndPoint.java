package hajimeapi4j.api.endpoint;

import hajimeapi4j.api.request.RestAction;
import hajimeapi4j.internal.datatype.Member;
import hajimeapi4j.internal.datatype.utilizations.Disc;
import hajimeapi4j.internal.datatype.utilizations.Live;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nonnull;

public interface MusicEndPoint extends RestAction<MusicEndPoint> {

  @Nonnull
  List<EndPoint> getRemix();

  @Nonnull
  List<EndPoint> getOriginal();

  @Nonnull
  List<EndPoint> getLyrics();

  @Nonnull
  List<EndPoint> getComposer();

  @Nonnull
  List<EndPoint> getArrange();

  @Nonnull
  String getLyricsUrl();

  @Nonnull
  List<Member> getMember();

  boolean getDigitalReleaseExists();

  /**
   * 楽曲が収録されたCD情報を全て返します。
   * @return {@link List}に詰められたCD収録情報
   */
  Optional<List<Disc>> getDisc();

  Optional<List<Live>> getLive();

}
