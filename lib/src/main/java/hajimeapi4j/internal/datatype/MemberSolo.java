package hajimeapi4j.internal.datatype;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Optional;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MemberSolo extends Member {


  protected Boolean solo;

  protected MemberSolo(String name, String type, int taxId, int songId, String link, String api,
      String production, String cv, Boolean solo) {
    super(name, type, taxId, songId, link, api, production, cv);
    this.solo = solo;
  }

  public static MemberSolo createInstance(String name, String type, int taxId, String link,
      String api, String production, String cv, Boolean solo) {
    return new MemberSolo(name, type, taxId, taxId, link, api, production, cv, solo);
  }

  public Optional<Boolean> getSolo() {
    return Optional.ofNullable(this.solo);
  }
}
