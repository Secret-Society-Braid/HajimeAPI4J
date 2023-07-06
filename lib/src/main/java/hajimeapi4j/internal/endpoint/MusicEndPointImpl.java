package hajimeapi4j.internal.endpoint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import hajimeapi4j.api.endpoint.EndPoint;
import hajimeapi4j.api.endpoint.MusicEndPoint;
import hajimeapi4j.internal.datatype.Member;
import hajimeapi4j.internal.datatype.utilizations.Live;
import lombok.*;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MusicEndPointImpl extends EndPointImpl implements MusicEndPoint {

  protected List<EndPoint> remix;
  protected List<EndPoint> original;
  protected List<EndPoint> lyrics;
  protected List<EndPoint> composer;
  protected List<EndPoint> arrange;
  @JsonProperty("lyrics_url")
  protected String lyricsUrl;
  protected List<Member> member;
  protected boolean digital;
  protected List<EndPoint> disc;
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
    List<EndPoint> disc,
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
  public Optional<List<EndPoint>> getDisc() {
    return Optional.ofNullable(this.disc);
  }

  @Override
  @CheckReturnValue
  public Optional<List<Live>> getLive() {
    return Optional.ofNullable(this.live);
  }

}
