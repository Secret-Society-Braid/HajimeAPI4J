package hajimeapi4j.util.enums;

/**
 * {@code list} エンドポイントで指定できるパラメータを列挙したものです
 * <p>
 * いくつかのパラメータは指定した値を {@code String} で指定する必要があるため、こちらで安全な処理を行うため列挙しています。
 *
 * @see hajimeapi4j.api.endpoint.ListEndPoint
 * @see hajimeapi4j.internal.builder.ListEndPointBuilder
 * @see hajimeapi4j.internal.endpoint.ListEndPointImpl
 * @since 5.0.0.0-Alpha.1
 */
@SuppressWarnings("unused")
public interface ListParameter {

  /**
   * {@code type} 指定です。
   * <p>
   * {@code list} エンドポイントではこの値を設定することが必須です。
   */
  enum Type {
    /**
     * 出力モードを music に設定
     */
    MUSIC,

    /**
     * 出力モードを live に設定
     */
    LIVE,

    /**
     * 出力モードを idol に設定
     */
    IDOL,

    /**
     * 出力モードを composer に設定
     */
    COMPOSER,

    /**
     * 出力モードを arrange に設定
     */
    ARRANGE,

    /**
     * 出力モードを disc に設定
     */
    DISC,

    /**
     * 出力モードを cv に設定
     */
    CV;

    @Override
    public String toString() {
      return super.toString().toLowerCase();
    }
  }

  /**
   * 楽曲の種類（シンデレラ、合同、リミックスなど）を指定します
   * <p>
   * このパラメータは複数指定可能です。
   * <p>
   * 何を指定しているかわかりやすくするため、列挙自身をわかりやすい形に書き換えています。
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

    @Override
    public String toString() {
      return this.identifier;
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

  /**
   * 並び変えの祭に使用する情報を指定します。
   * <p>
   * {@code list}エンドポイントでは、{@code music} タイプは、{@code title}, {@code date},
   * {@code random}の3つでソートすることができます。
   * <p>
   * そのほかのタイプでは {@code name}, {@code count}が利用可能です。
   */
  enum OrderBy {
    TITLE,

    DATE,

    RAND,

    NAME(false),

    COUNT(false);

    final boolean applicableForMusic;

    OrderBy(boolean applicableForMusic) {
      this.applicableForMusic = applicableForMusic;
    }

    OrderBy() {
      this.applicableForMusic = true;
    }

    public boolean isApplicableForMusic() {
      return this.applicableForMusic;
    }

    @Override
    public String toString() {
      return super.toString().toLowerCase();
    }
  }

  /**
   * 開催されたプロダクションの情報を指定します。
   * <p>
   * デフォルトでは全て出力されます。
   */
  enum Production {
    CINDERELLA_GIRLS("cg"),

    NAMCO_PRODUCTION("765"),

    SHINY_COLORS("sc"),

    SIDEM_315_PRODUCTION("315");

    final String identifier;

    Production(String identifier) {
      this.identifier = identifier;
    }

    @Override
    public String toString() {
      return this.identifier;
    }
  }

}
