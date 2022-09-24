package hajimeapi4j.internal;

import hajimeapi4j.api.endpoint.EndPoint;
import hajimeapi4j.api.endpoint.TaxEndPoint;
import hajimeapi4j.internal.datatype.Member;
import hajimeapi4j.internal.datatype.utilizations.Music;
import java.util.List;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class TaxEndPointImpl extends EndPointImpl implements TaxEndPoint {

  protected String kana;
  protected String cv;
  protected String cvKana;
  protected String production;
  protected String date;
  protected String place;
  protected List<Member> member;
  protected boolean setList;
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

  private TaxEndPoint createInstance(
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

}
