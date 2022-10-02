package hajimeapi4j.internal.datatype;

import hajimeapi4j.internal.EndPointImpl;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;
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

  protected Unit(String name, String type, int taxId, int songId, String link, String api,
      List<Member> member) {
    super(name, type, taxId, songId, link, api);
    this.member = member;
  }

  public static Unit createInstance(String name, String type, int taxId, String link, String api,
      List<Member> members) {
    return new Unit(name, type, taxId, taxId, link, api, members);
  }

  public static Unit createEmpty() {
    return new Unit("", "", -1, -1, "", "", Collections.emptyList());
  }

  @Nonnull
  public List<Member> getMember() {
    return this.member;
  }

}
