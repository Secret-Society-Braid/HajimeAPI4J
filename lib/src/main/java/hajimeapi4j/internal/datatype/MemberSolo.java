package hajimeapi4j.internal.datatype;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(as = MemberSolo.class)
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
