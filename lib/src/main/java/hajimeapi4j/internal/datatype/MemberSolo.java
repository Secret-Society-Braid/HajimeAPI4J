package hajimeapi4j.internal.datatype;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Getter
public class MemberSolo extends Member {


  protected boolean solo;

  protected MemberSolo(String name, String type, int taxId, int songId, String link, String api,
      String production, String cv, boolean solo) {
    super(name, type, taxId, songId, link, api, production, cv);
    this.solo = solo;
  }

  public static MemberSolo createInstance(String name, String type, int taxId, String link,
      String api, String production, String cv, boolean solo) {
    return new MemberSolo(name, type, taxId, taxId, link, api, production, cv, solo);
  }
}
