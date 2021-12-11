package HajimeAPI4J.responses.interfaces;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

import HajimeAPI4J.responses.Arrange;
import HajimeAPI4J.responses.Composer;
import HajimeAPI4J.responses.Disc;
import HajimeAPI4J.responses.Live;
import HajimeAPI4J.responses.Lyric;
import HajimeAPI4J.responses.Member;
import HajimeAPI4J.responses.Music;
import HajimeAPI4J.responses.Unit;

/**
 * ふじわらはじめAPIで使用されているレスポンス型を実装するためのアダプター・クラスです。
 * 基本的に全てのレスポンス型実装はこのクラスを継承して作成しています。
 * 
 * @author Ranfa
 * @since 1.0.0
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
    
    /**
     * {@inheritDoc}
     */
    public String getName() {return null;}

    /**
     * {@inheritDoc}
     */
    public void setName(String name) {}

    /**
     * {@inheritDoc}
     */
    public String getType() {return null;}

    /**
     * {@inheritDoc}
     */
    public void setType(@Nonnull String type) {}

    /**
     * {@inheritDoc}
     */
    public int getTaxId() {return Integer.MIN_VALUE;}

    /**
     * {@inheritDoc}
     */
    public void setTaxId(int taxId) {}

    /**
     * {@inheritDoc}
     */
    public String getLink() {return null;}

    /**
     * {@inheritDoc}
     */
    public void setLink(@Nonnull String link) {}

    /**
     * {@inheritDoc}
     */
    public String getApi() {return null;}

    /**
     * {@inheritDoc}
     */
    public void setApi(@Nonnull String api) {}

    /**
     * {@inheritDoc}
     */
    public String getDate() {return null;}

    /**
     * {@inheritDoc}
     */
    public void setDate(@Nonnull String date) {}

    /**
     * {@inheritDoc}
     */
    public String getProduction() {return null;}

    /**
     * {@inheritDoc}
     */
    public void setProduction(@Nonnull String production) {}

    /**
     * {@inheritDoc}
     */
    public String getCv() {return null;}

    /**
     * {@inheritDoc}
     */
    public void setCv(@Nonnull String cv) {}

    /**
     * {@inheritDoc}
     */
    public String getKana() {return null;}

    /**
     * {@inheritDoc}
     */
    public void setKana(@Nonnull String kana) {}

    /**
     * {@inheritDoc}
     */
    public String getCvKana() {return null;}

    /**
     * {@inheritDoc}
     */
    public void setCvKana(@Nonnull String cvKana) {}

    /**
     * {@inheritDoc}
     */
    public String getPlace() {return null;}

    /**
     * {@inheritDoc}
     */
    public void setPlace(@Nonnull String place) {}

    /**
     * {@inheritDoc}
     */
    public boolean getSetList() {return false;}

    /**
     * {@inheritDoc}
     */
    public void setSetList(@Nonnull boolean setList) {}
    
    /**
     * {@inheritDoc}
     */
    public Lyric getLyric() {return null;}

    /**
     * {@inheritDoc}
     */
    public void setLyric(@Nonnull Lyric lyric) {}

    /**
     * {@inheritDoc}
     */
    public Composer getComposer() {return null;}

    /**
     * {@inheritDoc}
     */
    public void setComposer(@Nonnull Composer composer) {}

    /**
     * {@inheritDoc}
     */
    public Arrange getArrange() {return null;}

    /**
     * {@inheritDoc}
     */
    public void setArrange(@Nonnull Arrange arrange) {}

    /**
     * {@inheritDoc}
     */
    public String getLyricsUrl() {return null;}

    /**
     * {@inheritDoc}
     */
    public void setLyricsUrl(@Nonnull String lyricsUrl) {}

    /**
     * {@inheritDoc}
     */
    public Disc getDisc() {return null;}

    /**
     * {@inheritDoc}
     */
    public void setDisc(@Nonnull Disc disc) {}

    /**
     * {@inheritDoc}
     */
    public List<Member> getMembers() {return Collections.emptyList();}

    /**
     * {@inheritDoc}
     */
    public void setMembers(@Nonnull List<Member> members) {}

    /**
     * {@inheritDoc}
     */
    public List<Music> getMusics() {return Collections.emptyList();}

    /**
     * {@inheritDoc}
     */
    public void setMusics(@Nonnull List<Music> musics) {}

    /**
     * {@inheritDoc}
     */
    public List<Lyric> getLyrics() {return Collections.emptyList();}

    /**
     * {@inheritDoc}
     */
    public void setLyrics(@Nonnull List<Lyric> lyrics) {}

    /**
     * {@inheritDoc}
     */
    public List<Composer> getComposers() {return Collections.emptyList();}

    /**
     * {@inheritDoc}
     */
    public void setComposers(@Nonnull List<Composer> composers) {}

    /**
     * {@inheritDoc}
     */
    public List<Arrange> getArranges() {return Collections.emptyList();}

    /**
     * {@inheritDoc}
     */
    public void setArranges(@Nonnull List<Arrange> arranges) {}

    /**
     * {@inheritDoc}
     */
    public List<Disc> getDiscs() {return Collections.emptyList();}

    /**
     * {@inheritDoc}
     */
    public void setDiscs(@Nonnull List<Disc> discs) {}

    /**
     * {@inheritDoc}
     */
    public List<Live> getLives() {return Collections.emptyList();}

    /**
     * {@inheritDoc}
     */
    public void setLives(@Nonnull List<Live> lives) {}

}
