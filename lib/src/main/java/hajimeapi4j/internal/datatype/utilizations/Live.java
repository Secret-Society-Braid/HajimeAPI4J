package hajimeapi4j.internal.datatype.utilizations;

import hajimeapi4j.api.endpoint.EndPoint;
import hajimeapi4j.internal.EndPointImpl;
import hajimeapi4j.internal.datatype.Member;
import hajimeapi4j.internal.datatype.Unit;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Live extends EndPointImpl implements EndPoint {

  protected String date;
  protected String place;
  protected List<Unit> unit;
  protected List<Member> member;

}
