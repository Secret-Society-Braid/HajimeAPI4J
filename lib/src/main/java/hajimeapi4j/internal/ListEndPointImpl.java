package hajimeapi4j.internal;

import hajimeapi4j.api.endpoint.ListEndPoint;
import java.util.Optional;
import javax.annotation.CheckReturnValue;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EqualsAndHashCode(callSuper = true)
@ToString
public class ListEndPointImpl extends EndPointImpl implements ListEndPoint {

  protected String musicType;
  protected String date;
  protected String production;
  protected String kana;
  protected String cv;
  protected String cvKana;

  protected ListEndPointImpl(String name, String type, int id, String link, String api,
      String musicType, String date, String production, String kana, String cv, String cvKana) {
    super(name, type, id, id, link, api);
    this.musicType = musicType;
    this.date = date;
    this.production = production;
    this.kana = kana;
    this.cv = cv;
    this.cvKana = cvKana;
  }

  @CheckReturnValue
  public static ListEndPoint createInstance(
      String name,
      String type,
      int id,
      String link,
      String api,
      String musicType,
      String date,
      String production,
      String kana,
      String cv,
      String cvKana) {
    return new ListEndPointImpl(name, type, id, link, api, musicType, date, production, kana, cv,
        cvKana);
  }

  @Override
  public Optional<String> getMusicType() {
    return Optional.ofNullable(this.musicType);
  }

  @Override
  public Optional<String> getDate() {
    return Optional.ofNullable(this.date);
  }

  @Override
  public Optional<String> getProduction() {
    return Optional.ofNullable(this.production);
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
}
