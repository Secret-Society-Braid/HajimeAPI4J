package hajimeapi4j.util.prototype;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Optional;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
// FIXME: delete this temporal class when completed implementation for main libraries
public class SerializeTestDataClass {

  protected String name;
  protected String type;
  @Getter
  protected int songId;
  @Getter
  protected int taxId;
  protected String link;
  protected String api;
  protected String nullValueTest;

  public Optional<String> getName() {
    return Optional.ofNullable(this.name);
  }

  public Optional<String> getType() {
    return Optional.ofNullable(this.type);
  }

  public Optional<String> getLink() {
    return Optional.ofNullable(this.link);
  }

  public Optional<String> getApi() {
    return Optional.ofNullable(this.api);
  }

  public Optional<String> getNullValueTest() {
    return Optional.ofNullable(this.nullValueTest);
  }
}
