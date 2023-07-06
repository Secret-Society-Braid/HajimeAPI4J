package hajimeapi4j.internal.datatype.utilizations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import hajimeapi4j.api.endpoint.EndPoint;
import hajimeapi4j.internal.datatype.Member;
import hajimeapi4j.internal.datatype.Unit;
import hajimeapi4j.internal.endpoint.EndPointImpl;
import lombok.*;

import javax.annotation.Nonnull;

/**
 * ライブ時での披露情報を格納、表示します。
 * <p>
 * 格納されている {@link Member}, {@link Unit} のデータについては、それぞれのクラスのドキュメントを参照してください。
 *
 * @see Member
 * @see Unit
 * @since 5.0.0.0-Alpha.1
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(as = Live.class)
public class Live extends EndPointImpl implements EndPoint {

  protected String date;
  protected String place;

  private Live(
    String name,
    String type,
    int taxId,
    int songId,
    String link,
    String api,
    String date,
    String place) {
    super(name, type, taxId, songId, link, api);
    this.date = date;
    this.place = place;
  }

  @Nonnull
  public static Live createInstance(
    String name,
    String type,
    int taxId,
    String link,
    String api,
    String date,
    String place) {
    return new Live(name, type, taxId, taxId, link, api, date, place);
  }

}
