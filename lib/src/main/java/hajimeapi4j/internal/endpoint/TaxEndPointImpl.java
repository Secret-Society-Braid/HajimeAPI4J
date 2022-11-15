package hajimeapi4j.internal.endpoint;

import hajimeapi4j.api.endpoint.EndPoint;
import hajimeapi4j.api.endpoint.TaxEndPoint;
import hajimeapi4j.internal.datatype.Member;
import hajimeapi4j.internal.datatype.utilizations.Music;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nonnull;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TaxEndPointImpl extends EndPointImpl implements TaxEndPoint {

  protected String kana;
  protected String cv;
  protected String cvKana;
  protected String production;
  protected String date;
  protected String place;
  protected List<Member> member;
  protected Boolean setList;
  protected EndPoint lyrics;
  protected EndPoint composer;
  protected EndPoint arrange;
  protected List<Music> music;

  protected TaxEndPointImpl(
      String name,
      String type,
      int id,
      String link,
      String api,
      String kana,
      String cv,
      String cvKana,
      String production,
      String date,
      String place,
      List<Member> member,
      boolean setList,
      EndPoint lyrics,
      EndPoint composer,
      EndPoint arrange,
      List<Music> music) {
    super(name, type, id, id, link, api);
    this.kana = kana;
    this.cv = cv;
    this.cvKana = cvKana;
    this.production = production;
    this.date = date;
    this.place = place;
    this.member = member;
    this.arrange = arrange;
    this.music = music;
    this.setList = setList;
    this.lyrics = lyrics;
    this.composer = composer;
  }

  public static TaxEndPoint createInstance(
      String name,
      String type,
      int id,
      String link,
      String api,
      String kana,
      String cv,
      String cvKana,
      String production,
      String date,
      String place,
      List<Member> member,
      boolean setList,
      EndPoint lyrics,
      EndPoint composer,
      EndPoint arrange,
      List<Music> music) {
    return new TaxEndPointImpl(name, type, id, link, api, kana, cv, cvKana, production, date, place,
        member, setList, lyrics, composer, arrange, music);
  }

  @Override
  public Optional<String> getKana() {
    return Optional.ofNullable(this.kana);
  }

  @Override
  public Optional<String> getCv() {
    return Optional.ofNullable(this.cv);
  }

  @Override
  public Optional<String> getCvKana() {
    return Optional.ofNullable(this.cvKana);
  }

  @Override
  public Optional<String> getProduction() {
    return Optional.ofNullable(this.production);
  }

  @Override
  public Optional<String> getDate() {
    return Optional.ofNullable(this.date);
  }

  @Override
  public Optional<String> getPlace() {
    return Optional.ofNullable(this.place);
  }

  @Override
  public Optional<List<? extends Member>> getMember() {
    return Optional.ofNullable(this.member);
  }

  @Override
  public Optional<Boolean> getSetListExists() {
    return Optional.ofNullable(this.setList);
  }

  @Override
  public Optional<EndPoint> getLyrics() {
    return Optional.ofNullable(this.lyrics);
  }

  @Override
  public Optional<EndPoint> getComposer() {
    return Optional.ofNullable(this.composer);
  }

  @Override
  public Optional<EndPoint> getArrange() {
    return Optional.ofNullable(this.arrange);
  }

  @Override
  @Nonnull
  public List<Music> getMusic() {
    return this.music;
  }


}
