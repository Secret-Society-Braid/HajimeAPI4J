package HajimeAPI4J.internal.datatype;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Getter
public class Unit {

  private final String name;
  private final String type;
  private final int taxId;
  private final String link;
  private final String api;
  private final List<Member> member;

}
