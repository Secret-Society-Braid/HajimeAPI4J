package hajimeapi4j.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class SerializeTestDataClass {

  private String name;
  private String type;
  private int songId;
  private int taxId;
  private String link;
  private String api;

}
