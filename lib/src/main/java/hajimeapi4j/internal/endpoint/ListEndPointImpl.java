package hajimeapi4j.internal.endpoint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import hajimeapi4j.api.endpoint.ListEndPoint;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(as = ListEndPointImpl.class)
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
