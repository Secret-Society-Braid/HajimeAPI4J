package hajimeapi4j.internal.endpoint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hajimeapi4j.api.endpoint.EndPoint;
import hajimeapi4j.api.endpoint.MusicEndPoint;
import hajimeapi4j.internal.datatype.Member;
import hajimeapi4j.internal.datatype.utilizations.Disc;
import hajimeapi4j.internal.datatype.utilizations.Live;
import java.util.List;
import java.util.Optional;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MusicEndPointImpl extends EndPointImpl implements MusicEndPoint {

  private static MusicEndPoint emptyInstance;

  protected List<EndPoint> remix;
  protected List<EndPoint> original;
  protected List<EndPoint> lyrics;
  protected List<EndPoint> composer;
  protected List<EndPoint> arrange;
  protected String lyricsUrl;
  protected List<Member> member;
  protected boolean digital;
  protected List<Disc> disc;
  protected List<Live> live;

  protected MusicEndPointImpl(
      String name,
      String type,
      int taxId,
      int songId,
      String link,
      String api,
      List<EndPoint> remix,
      List<EndPoint> original,
      List<EndPoint> lyrics,
      List<EndPoint> composer,
      List<EndPoint> arrange,
      String lyricsUrl,
      List<Member> member,
      boolean digital,
      List<Disc> disc,
      List<Live> live) {
    super(name, type, taxId, songId, link, api);
    this.remix = remix;
    this.original = original;
    this.lyrics = lyrics;
    this.composer = composer;
    this.arrange = arrange;
    this.lyricsUrl = lyricsUrl;
    this.member = member;
    this.digital = digital;
    this.disc = disc;
    this.live = live;
  }

  public static MusicEndPoint createInstance(
      String name,
      String type,
      int songId,
      String link,
      String api,
      List<EndPoint> remix,
      List<EndPoint> original,
      List<EndPoint> lyrics,
      List<EndPoint> composer,
      List<EndPoint> arrange,
      String lyricsUrl,
      List<Member> member,
      boolean digital,
      List<Disc> disc,
      List<Live> live
  ) {
    return new MusicEndPointImpl(name, type, songId, songId, link, api, remix, original, lyrics,
        composer, arrange, lyricsUrl, member, digital, disc, live);
  }

  @Override
  @CheckReturnValue
  public Optional<List<EndPoint>> getRemix() {
    return Optional.ofNullable(this.remix);
  }

  @Override
  @CheckReturnValue
  public Optional<List<EndPoint>> getOriginal() {
    return Optional.ofNullable(this.original);
  }

  @Override
  @CheckReturnValue
  public Optional<List<EndPoint>> getLyrics() {
    return Optional.ofNullable(this.lyrics);
  }

  @Override
  @CheckReturnValue
  public Optional<List<EndPoint>> getComposer() {
    return Optional.ofNullable(this.composer);
  }

  @Override
  @CheckReturnValue
  public Optional<List<EndPoint>> getArrange() {
    return Optional.ofNullable(this.arrange);
  }

  @Override
  @CheckReturnValue
  public Optional<String> getLyricsUrl() {
    return Optional.ofNullable(this.lyricsUrl);
  }

  @Override
  @Nonnull
  public List<Member> getMember() {
    return this.member;
  }

  @Override
  public boolean getDigitalReleaseExists() {
    return this.digital;
  }

  @Override
  @CheckReturnValue
  public Optional<List<Disc>> getDisc() {
    return Optional.ofNullable(this.disc);
  }

  @Override
  @CheckReturnValue
  public Optional<List<Live>> getLive() {
    return Optional.ofNullable(this.live);
  }

}
