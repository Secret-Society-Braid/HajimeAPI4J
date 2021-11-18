package HajimeAPI4J.responses;

import javax.annotation.Nonnull;

/**
 * ふじわらはじめAPIで使用されているレスポンス型のインターフェースです。
 * レスポンス型の宣言には {@link HajimeAPI4J.responses.ResponseAdapter アダプタークラス} を継承しています。
 * 
 * @author Ranfa
 * @since 1.0.0
 * 
 * @see ResponseAdapter
 * @see Unit
 * @see Member
 * @see Music
 * @see Lyric
 * @see Composer
 * @see Arrange
 * @see Disc
 * @see Live
 */
public interface InternalResponseInterface {

    /**
     * レスポンスに含まれているパラメータ「date」の値を返します。
     * @return レスポンスに含まれているパラメータのうち、keyが「date」であるもののvalue
     */
    @Nonnull
    String getDate();

    /**
     * レスポンスに含まれているパラメータ「date」の値をセットします。
     * @param date パラメータ「date」の値
     */
    void setDate(@Nonnull String date);

    /**
     * レスポンスに含まれているパラメータ「production」の値を返します。
     * @return レスポンスに含まれているパラメータのうち、keyが「production」であるもののvalue
     */
    @Nonnull
    String getProduction();

    /**
     * レスポンスに含まれているパラメータ「production」の値をセットします。
     * @param production パラメータ「production」の値
     */
    void setProduction(@Nonnull String production);

    /**
     * レスポンスに含まれているパラメータ「kana」の値を返します。
     * @return レスポンスに含まれているパラメータのうち、keyが「kana」であるもののvalue
     */
    @Nonnull
    String getKana();

    void setKana(@Nonnull String kana);

    @Nonnull
    String getCv();

    void setCv(@Nonnull String cv);

    @Nonnull
    String getCvKana();

    void setCvKana(@Nonnull String cvKana);

    @Nonnull
    String getPlace();

    void setPlace(@Nonnull String place);

    @Nonnull
    boolean getSetList();

    void setSetList(@Nonnull boolean setList);

    @Nonnull
    Lyric getLyric();

    void setLyric(@Nonnull Lyric lyric);

    @Nonnull
    Composer getComposer();

    void setComposer(@Nonnull Composer composer);

    @Nonnull
    Arrange getArrange();

    void setArrange(@Nonnull Arrange arrange);

    @Nonnull
    String getLyricsUrl();

    void setLyricsUrl(@Nonnull String lyricsUrl);

}
