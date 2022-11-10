package hajimeapi4j.internal.datatype.utilizations;

import hajimeapi4j.api.endpoint.EndPoint;
import hajimeapi4j.internal.EndPointImpl;
import hajimeapi4j.internal.datatype.Member;
import hajimeapi4j.internal.datatype.Unit;
import java.util.List;
import javax.annotation.Nonnull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Getter
public class Live extends EndPointImpl implements EndPoint {

  protected String date;
  protected String place;
  protected List<Unit> unit;
  protected List<Member> member;

  private Live(
      String name,
      String type,
      int tax_id,
      int song_id,
      String link,
      String api,
      String date,
      String place,
      List<Unit> unit,
      List<Member> member) {
    super(name, type, tax_id, song_id, link, api);
    this.date = date;
    this.place = place;
    this.unit = unit;
    this.member = member;
  }

  @Nonnull
  public static Live createInstance(
      String name,
      String type,
      int tax_id,
      String link,
      String api,
      String date,
      String place,
      List<Unit> unit,
      List<Member> member) {
    return new Live(name, type, tax_id, tax_id, link, api, date, place, unit, member);
  }

}
