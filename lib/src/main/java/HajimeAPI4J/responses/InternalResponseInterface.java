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

    /**
     * レスポンスに含まれているパラメータ「kana」の値をセットします。
     * @param kana パラメータ「kana」の値
     */
    void setKana(@Nonnull String kana);

    /**
     * レスポンスに含まれているパラメータ「cv」の値を返します。
     * @return レスポンスに含まれているパラメータのうち、keyが「cv」であるもののvalue
     */
    @Nonnull
    String getCv();

    /**
     * レスポンスに含まれているパラメータ「cv」の値をセットします。
     * @param cv　パラメータ「cv」の値
     */
    void setCv(@Nonnull String cv);

    /**
     * レスポンスに含まれているパラメータ「cvkana」の値を返します。
     * @return レスポンスに含まれているパラメータのうち、keyが「cvkana」であるもののvalue
     */
    @Nonnull
    String getCvKana();

    /**
     * レスポンスに含まれているパラメータ「cvkana」の値をセットします。
     * @param cvKana パラメータ「cvkana」の値
     */
    void setCvKana(@Nonnull String cvKana);

    /**
     * レスポンスに含まれているパラメータ「place」の値を返します。
     * @return レスポンスに含まれているパラメータのうち。keyが「place」であるもののvalue
     */
    @Nonnull
    String getPlace();

    /**
     * レスポンスに含まれているパラメータ「place」の値をセットします。
     * @param place パラメータ「place」の値
     */
    void setPlace(@Nonnull String place);

    /**
     * レスポンスに含まれているパラメータ「setlist」の値を返します。
     * @return レスポンスに含まれているパラメータのうち、keyが「setlist」であるもののvalue
     */
    @Nonnull
    boolean getSetList();

    /**
     * レスポンスに含まれているパラメータ「setlist」の値をセットします。
     * @param setList パラメータ「setlist」の値
     */
    void setSetList(@Nonnull boolean setList);

    /**
     * レスポンスに含まれているパラメータ「lyric」の値を返します。
     * @return レスポンスに含まれているパラメータのうち、keyが「lyric」であるもののvalue
     */
    @Nonnull
    Lyric getLyric();

    /**
     * レスポンスに含まれているパラメータ「lyric」の値をセットします。
     * @param lyric パラメータ「lyric」の値
     */
    void setLyric(@Nonnull Lyric lyric);

    /**
     * レスポンスに含まれているパラメータ「composer」の値を返します。
     * @return レスポンスに含まれているパラメータのうち、keyが「composer」であるもののvalue
     */
    @Nonnull
    Composer getComposer();

    /**
     * レスポンスに含まれているパラメータ「composer」の値をセットします。
     * @param composer パラメータ「composer」の値
     */
    void setComposer(@Nonnull Composer composer);

    /**
     * レスポンスに含まれているパラメータ「arrange」の値を返します。
     * @return レスポンスに含まれているパラメータのうち、keyが「arrange」であるもののvalue
     */
    @Nonnull
    Arrange getArrange();

    /**
     * レスポンスに含まれているパラメータ「arrange」の値をセットします。
     * @param arrange パラメータ「arrange」の値
     */
    void setArrange(@Nonnull Arrange arrange);

    /**
     * レスポンスに含まれているパラメータ「lyricurl」の値を返します。
     * @return レスポンスに含まれているパラメータのうち、keyが「lyricurl」であるもののvalue
     */
    @Nonnull
    String getLyricsUrl();

    /**
     * レスポンスに含まれているパラメータ「lyricurl」の値をセットします。
     * @param lyricsUrl パラメータ「lyricurl」の値
     */
    void setLyricsUrl(@Nonnull String lyricsUrl);

}
