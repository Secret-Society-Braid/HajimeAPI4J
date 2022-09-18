package HajimeAPI4J.internal.datatype;

import java.util.Optional;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Member {

  protected final String name;
  protected final String type;
  protected final int taxId;
  protected final String link;
  protected final String api;
  protected final String production;
  protected final String cv;

  public Member() {
    this(null, null, 0, null, null, null, null);
  }

  @Nonnull
  public String getName() {
    return this.name;
  }

  @Nonnull
  public String getType() {
    return this.type;
  }

  public int getTaxId() {
    return this.taxId;
  }

  @Nonnull
  public String getLink() {
    return this.link;
  }

  @Nonnull
  public String getApi() {
    return this.api;
  }

  @CheckReturnValue
  public Optional<String> getProduction() {
    return Optional.ofNullable(this.production);
  }

  @Nonnull
  public String getCv() {
    return this.cv;
  }
}
