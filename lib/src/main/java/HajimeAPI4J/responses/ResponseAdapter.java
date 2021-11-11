package HajimeAPI4J.responses;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

/**
 * ふじわらはじめAPIで使用されているレスポンス型を実装するためのアダプター・クラスです。
 * 基本的に全てのレスポンス型実装はこのクラスを継承して作成しています。
 * 
 * @author Ranfa
 * 
 * @see ResponseInterface
 * @see InternalResponseInterface
 * @see InternalArrayResponseInterface
 * @see Unit
 * @see Member
 * @see Music
 * @see Lyric
 * @see Composer
 * @see Arrange
 * @see Disc
 * @see Live
 */
public abstract class ResponseAdapter implements ResponseInterface,
                                                InternalResponseInterface,
                                                InternalArrayResponseInterface {
    
    public String getName() {return null;}

    public void setName(String name) {}

    public String getType() {return null;}

    public void setType(@Nonnull String type) {}

    public Integer getTaxId() {return null;}

    public void setTaxId(@Nonnull Integer taxId) {}

    public String getLink() {return null;}

    public void setLink(@Nonnull String link) {}

    public String getApi() {return null;}

    public void setApi(@Nonnull String api) {}

    public String getDate() {return null;}

    public void setDate(@Nonnull String date) {}

    public String getProduction() {return null;}

    public void setProduction(@Nonnull String production) {}

    public String getCv() {return null;}

    public void setCv(@Nonnull String cv) {}

    public String getKana() {return null;}

    public void setKana(@Nonnull String kana) {}

    public String getCvKana() {return null;}

    public void setCvKana(@Nonnull String cvKana) {}

    public String getPlace() {return null;}

    public void setPlace(@Nonnull String place) {}

    public boolean getSetList() {return false;}

    public void setSetList(@Nonnull boolean setList) {}
    
    public Lyric getLyrics() {return null;}

    public void setLyrics(@Nonnull Lyric lyrics) {}

    public Composer getComposer() {return null;}

    public void setComposer(@Nonnull Composer composer) {}

    public Arrange getArrange() {return null;}

    public void setArrange(@Nonnull Arrange arrange) {}

    public String getLyricsUrl() {return null;}

    public void setLyricsUrl(@Nonnull String lyricsUrl) {}

    public List<Member> getMembers() {return Collections.emptyList();}

    public void setMembers(@Nonnull List<Member> members) {}

    public List<Music> getMusics() {return Collections.emptyList();}

    public void setMusics(@Nonnull List<Music> musics) {}

    public List<Lyric> getLyricses() {return Collections.emptyList();}

    public void setLyricses(@Nonnull List<Lyric> lyricses) {}

    public List<Composer> getComposers() {return Collections.emptyList();}

    public void setComposers(@Nonnull List<Composer> composers) {}

    public List<Arrange> getArranges() {return Collections.emptyList();}

    public void setArranges(@Nonnull List<Arrange> arranges) {}

    public List<Disc> getDisc() {return Collections.emptyList();}

    public void setDisc(@Nonnull List<Disc> discs) {}

    public List<Live> getLives() {return Collections.emptyList();}

    public void setLives(@Nonnull List<Live> lives) {}

}
