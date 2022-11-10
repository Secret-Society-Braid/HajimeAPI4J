package hajimeapi4j.internal.datatype.utilizations;

import hajimeapi4j.api.endpoint.EndPoint;
import hajimeapi4j.internal.EndPointImpl;
import hajimeapi4j.internal.datatype.Member;
import hajimeapi4j.internal.datatype.Unit;
import java.util.Collections;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Getter
public class Disc extends EndPointImpl implements EndPoint {

  protected List<Unit> unit;
  protected List<? extends Member> member;

  protected Disc(
      String name,
      String type,
      int taxId,
      int songId,
      String link,
      String api,
      List<Unit> unit,
      List<? extends Member> member) {
    super(name, type, taxId, songId, link, api);
    this.unit = unit;
    this.member = member;
  }

  private Disc() {
    this("", "", -1, -1, "", "", Collections.emptyList(), Collections.emptyList());
  }

  public static Disc createInstance(
      String name,
      String type,
      int taxId,
      String link,
      String api,
      List<Unit> unit,
      List<? extends Member> member) {
    return new Disc(name, type, taxId, taxId, link, api, unit, member);
  }
}
