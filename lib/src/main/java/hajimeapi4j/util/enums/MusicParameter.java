package hajimeapi4j.util.enums;

/**
 * {@code music} のパラメータにて決め打ちとして指定できるパラメータを列挙したものです
 */
public interface MusicParameter {

  /**
   * データ出力の際に高速化のために省くデータを指定します
   */
  enum Hide {
    CD_MEMBER,

    LIVE_MEMBER;

    @Override
    public String toString() {
      return super.toString().toLowerCase();
    }
  }

}
