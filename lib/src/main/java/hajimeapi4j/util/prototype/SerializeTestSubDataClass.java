package hajimeapi4j.util.prototype;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Optional;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@JsonIgnoreProperties(ignoreUnknown = true)
@Slf4j
@Setter
@ToString(callSuper = true)
public class SerializeTestSubDataClass extends SerializeTestDataClass {

  protected String subClassData;

  public Optional<String> getSubClassData() {
    return Optional.ofNullable(this.subClassData);
  }
}
