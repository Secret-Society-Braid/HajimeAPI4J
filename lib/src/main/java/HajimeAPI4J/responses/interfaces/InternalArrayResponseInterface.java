package HajimeAPI4J.responses.interfaces;

import java.util.List;

import javax.annotation.Nonnull;

import HajimeAPI4J.responses.Arrange;
import HajimeAPI4J.responses.Composer;
import HajimeAPI4J.responses.Disc;
import HajimeAPI4J.responses.Live;
import HajimeAPI4J.responses.Lyric;
import HajimeAPI4J.responses.Member;
import HajimeAPI4J.responses.Music;

public interface InternalArrayResponseInterface {

    @Nonnull
    List<Member> getMembers();

    void setMembers(@Nonnull List<Member> members);

    @Nonnull
    List<Music> getMusics();

    void setMusics(@Nonnull List<Music> musics);

    @Nonnull
    List<Lyric> getLyrics();

    void setLyrics(@Nonnull List<Lyric> lyrics);

    @Nonnull
    List<Composer> getComposers();

    void setComposers(@Nonnull List<Composer> composers);

    @Nonnull
    List<Arrange> getArranges();

    void setArranges(@Nonnull List<Arrange> arranges);

    @Nonnull
    List<Disc> getDisc();

    void setDisc(@Nonnull List<Disc> discs);

    @Nonnull
    List<Live> getLives();

    void setLives(@Nonnull List<Live> lives);

}
