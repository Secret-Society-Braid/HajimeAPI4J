package HajimeAPI4J.responses;

import javax.annotation.Nonnull;

public interface InternalResponseInterface {

    @Nonnull
    String getDate();

    void setDate(@Nonnull String date);

    @Nonnull
    String getProduction();

    void setProduction(@Nonnull String production);

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
    Lyrics getLyrics();

    void setLyrics(@Nonnull Lyrics lyrics);

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
