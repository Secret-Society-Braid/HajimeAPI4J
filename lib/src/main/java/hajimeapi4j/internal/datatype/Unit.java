package hajimeapi4j.internal.datatype;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import hajimeapi4j.internal.datatype.utilizations.Live;
import hajimeapi4j.internal.datatype.utilizations.Song;
import hajimeapi4j.internal.endpoint.EndPointImpl;
import lombok.*;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * ふじわらはじめAPIにて良く使用される、Unit型の情報をJavaで扱えるよう調整したデータクラスです。
 * <p>
 * このデータクラスが使われるレスポンスデータの参照や、各パラメータの詳しい情報などは、APIの公式ドキュメントをご参照ください。
 *
 * @author Ranfa
 * @see EndPointImpl
 * @see Song
 * @see Live
 * @see <a href="https://api.fujiwarahaji.me/v2/doc/">APIv2ドキュメント</a>
 * @since 5.0.0.0-Alpha.1
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(as = Unit.class)
public class Unit extends EndPointImpl {

  protected final List<Member> member;

  protected Unit(String name, String type, int taxId, int songId, String link, String api,
      List<Member> member) {
    super(name, type, taxId, songId, link, api);
    this.member = member;
  }

  public static Unit createInstance(String name, String type, int taxId, String link, String api,
      List<Member> members) {
    return new Unit(name, type, taxId, taxId, link, api, members);
  }

  /**
   * ユニットに所属しているメンバー情報を {@link List} として返します。
   *
   * @return ユニットに所属しているメンバー情報
   */
  @Nonnull
  public List<Member> getMember() {
    return this.member;
  }

}
