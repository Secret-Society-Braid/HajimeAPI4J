package hajimeapi4j.internal.datatype.utilizations;

import hajimeapi4j.internal.EndPointImpl;
import hajimeapi4j.internal.datatype.Member;
import hajimeapi4j.internal.datatype.Unit;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nonnull;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Music extends EndPointImpl {

  protected String musicType;
  protected String songText;
  protected List<Unit> unit;
  protected List<? extends Member> member;
  protected String memberText;
  protected Boolean solo;

  private Music(
      String name,
      String type,
      String musicType,
      int id,
      String link,
      String api,
      String songText,
      List<Unit> unit,
      List<? extends Member> member,
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

  private Music() {
    this("", "", "", -1, "", "", "", null, null, "", null);
  }

  public static Music createInstance(
      String name,
      String type,
      String musicType,
      int songId,
      String link,
      String api,
      String songText,
      List<Unit> unit,
      List<? extends Member> member,
      String memberText,
      Boolean solo) {
    return new Music(name, type, musicType, songId, link, api, songText, unit, member, memberText, solo);
  }

  public static Music createEmpty() {
    return new Music();
  }

  @Nonnull
  public String getMusicType() {
    return this.musicType;
  }

  public Optional<String> getSongText() {
    return Optional.ofNullable(this.songText);
  }

  public Optional<List<Unit>> getUnit() {
    return Optional.ofNullable(this.unit);
  }

  public Optional<List<? extends Member>> getMember() {
    return Optional.ofNullable(this.member);
  }

  public Optional<String> getMemberText() {
    return Optional.ofNullable(memberText);
  }

  public Optional<Boolean> getSolo() {
    return Optional.ofNullable(this.solo);
  }

}
