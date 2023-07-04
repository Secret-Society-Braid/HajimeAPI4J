package hajimeapi4j.internal.datatype;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import hajimeapi4j.internal.endpoint.EndPointImpl;
import java.util.Optional;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * ゲーム内メンバー、LIVE披露時のメンバー、円盤収録時などのメンバー情報を保持するクラスです。
 *
 * @since 5.0.0.0-Alpha.1
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(as = Member.class)
public class Member extends EndPointImpl {

  protected String production;

  protected String cv;

  protected Member(String name, String type, int taxId, int songId, String link, String api,
      String production, String cv) {
    super(name, type, taxId, songId, link, api);
    this.production = production;
    this.cv = cv;
  }

  public static Member createInstance(String name, String type, int taxId, String link, String api,
      String production, String cv) {
    return new Member(name, type, taxId, taxId, link, api, production, cv);
  }

  public static Member createInstance(String name) {
    return createInstance(name, null, -1, null, null, null, null);
  }

  /**
   * このアイドルの所属プロダクションを返します。
   *
   * @return このアイドルの所属プロダクション
   */
  @CheckReturnValue
  public Optional<String> getProduction() {
    return Optional.ofNullable(this.production);
  }

  /**
   * このメンバーの声優を返します。
   *
   * @return 声優
   */
  @Nonnull
  public String getCv() {
    return this.cv;
  }
}
