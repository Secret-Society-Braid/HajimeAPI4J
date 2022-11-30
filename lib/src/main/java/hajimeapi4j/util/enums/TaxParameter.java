package hajimeapi4j.util.enums;

/**
 * {@code tax} エンドポイントで指定できるパラメータを列挙したものです
 * <p>
 * いくつかのパラメータは指定された値を {@code String} で指定する必要があるため、こちらで安全な処理を行うため列挙しています
 *
 * @see hajimeapi4j.api.endpoint.TaxEndPoint
 * @see hajimeapi4j.internal.endpoint.TaxEndPointImpl
 * @see hajimeapi4j.internal.builder.TaxEndPointBuilder
 * @since 5.0.0
 */
@SuppressWarnings("unused")
public interface TaxParameter {

  /**
   * 楽曲の種類を絞り込む際に指定します。呼び出す定数自体をわかりやすい名前に変えて列挙しています。
   * <p>
   * このパラメータは複数指定可能です。
   */
  enum MusicType {
    CINDERELLA_GIRLS("cg"),

    MILLION_LIVE("ml"),

    SHINY_COLORS("sc"),

    NAMCO_ALL_STARS("as"),

    JOINT("joint"),

    COVER("cover"),

    REMIX("remix"),

    SIDEM("sidem");

    final String identifier;

    MusicType(String identifier) {
      this.identifier = identifier;
    }
  }

  /**
   * ソートを行う際に基準とするアイテムを指定します。
   */
  enum OrderBy {
    DATE,

    RAND,

    NAME;

    @Override
    public String toString() {
      return super.toString().toLowerCase();
    }
  }

  /**
   * 昇順、降順の設定を行うクエリパラメータを指定します。
   */
  enum Order {
    /**
     * 昇順
     */
    ASCENDING("asc"),

    /**
     * 降順
     */
    DESCENDING("desc");

    final String identifier;

    Order(String identifier) {
      this.identifier = identifier;
    }

    @Override
    public String toString() {
      return this.identifier;
    }
  }
}
