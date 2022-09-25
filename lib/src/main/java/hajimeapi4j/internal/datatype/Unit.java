package hajimeapi4j.internal.datatype;

import hajimeapi4j.internal.EndPointImpl;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Getter
public class Unit extends EndPointImpl {

  protected final List<Member> member;

}
