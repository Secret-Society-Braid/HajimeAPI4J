package hajimeapi4j.internal.datatype.utilizations;

import hajimeapi4j.internal.EndPointImpl;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Music extends EndPointImpl {

  protected String musicType;
  protected String songText;

}
