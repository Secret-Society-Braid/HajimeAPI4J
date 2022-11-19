package hajimeapi4j.internal.datatype.utilizations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import hajimeapi4j.api.endpoint.EndPoint;
import hajimeapi4j.internal.datatype.Member;
import hajimeapi4j.internal.datatype.Unit;
import hajimeapi4j.internal.endpoint.EndPointImpl;
import java.util.List;
import javax.annotation.Nonnull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(as = Live.class)
public class Live extends EndPointImpl implements EndPoint {

  protected String date;
  protected String place;
  protected List<Unit> unit;
  protected List<Member> member;

  private Live(
      String name,
      String type,
      int taxId,
      int songId,
      String link,
      String api,
      String date,
      String place,
      List<Unit> unit,
      List<Member> member) {
    super(name, type, taxId, songId, link, api);
    this.date = date;
    this.place = place;
    this.unit = unit;
    this.member = member;
  }

  @Nonnull
  public static Live createInstance(
      String name,
      String type,
      int taxId,
      String link,
      String api,
      String date,
      String place,
      List<Unit> unit,
      List<Member> member) {
    return new Live(name, type, taxId, taxId, link, api, date, place, unit, member);
  }

}
