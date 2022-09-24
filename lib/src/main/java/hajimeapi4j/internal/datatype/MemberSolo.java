package HajimeAPI4J.internal.datatype;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MemberSolo extends Member{

  private final boolean solo;

  public MemberSolo() {
    super();
    this.solo = false;
  }

  public MemberSolo(String name, String type, int taxId, String link, String api, String production, String cv, boolean solo) {
    super(name, type, taxId, link, api, production, cv);
    this.solo = solo;
  }

  public boolean getSolo() {
    return this.solo;
  }
}
