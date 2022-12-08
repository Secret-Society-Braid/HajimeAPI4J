package hajimeapi4j.internal.datatype.utilizations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import hajimeapi4j.internal.datatype.Member;
import hajimeapi4j.internal.datatype.Unit;
import hajimeapi4j.internal.endpoint.EndPointImpl;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nonnull;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;

/**
 * 楽曲の情報を格納、表示します。
 * <p>
 * 格納されている {@link Member}, {@link Unit} のデータについては、それぞれのクラスのドキュメントを参照してください。
 *
 * @see Member
 * @see Unit
 * @since 5.0.0.0-Alpha.1
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(as = Song.class)
public class Song extends EndPointImpl {

  protected String musicType;
  protected String songText;
  protected List<Unit> unit;
  protected List<Member> member;
  protected String memberText;
  protected Boolean solo;

  private Song(
      String name,
      String type,
      String musicType,
      int id,
      String link,
      String api,
      String songText,
      List<Unit> unit,
      List<Member> member,
      String memberText,
      Boolean solo) {
    super(name, type, id, id, link, api);
    this.musicType = musicType;
    this.songText = songText;
    this.unit = unit;
    this.member = member;
    this.memberText = memberText;
    this.solo = solo;
  }

  private Song() {
    this("", "", "", -1, "", "", "", null, null, "", null);
  }

  public static Song createInstance(
      String name,
      String type,
      String musicType,
      int songId,
      String link,
      String api,
      String songText,
      List<Unit> unit,
      List<Member> member,
      String memberText,
      Boolean solo) {
    return new Song(name, type, musicType, songId, link, api, songText, unit, member, memberText,
        solo);
  }

  public static Song createEmpty() {
    return new Song();
  }

  /**
   * 楽曲の種類を取得します。
   *
   * @return 楽曲の種類
   */
  @Nonnull
  public String getMusicType() {
    return this.musicType;
  }

  /**
   * 楽曲の追加情報を取得します。
   *
   * @return 楽曲の追加情報
   */
  public Optional<String> getSongText() {
    return Optional.ofNullable(this.songText);
  }

  /**
   * 楽曲に関連するユニットのリストを取得します。
   *
   * @return 楽曲に関連するユニットのリスト
   */
  public Optional<List<Unit>> getUnit() {
    return Optional.ofNullable(this.unit);
  }

  /**
   * 楽曲に関連するメンバーのリストを取得します。
   *
   * @return 楽曲に関連するメンバーのリスト
   */
  public Optional<List<Member>> getMember() {
    return Optional.ofNullable(this.member);
  }

  /**
   * 楽曲に関連するメンバーの追加情報を取得します。
   *
   * @return 楽曲に関連するメンバーの追加情報
   */
  public Optional<String> getMemberText() {
    return Optional.ofNullable(memberText);
  }

  /**
   * 楽曲がソロ曲かどうかを取得します。
   *
   * @return 楽曲がソロ曲であれば {@code true}、そうでなければ {@code false}、ソロ曲が存在し得ないユニット楽曲などの場合は
   * {@link Optional#empty()}
   */
  public Optional<Boolean> getSolo() {
    return Optional.ofNullable(this.solo);
  }

}
