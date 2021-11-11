package HajimeAPI4J.responses;

import java.util.List;

import javax.annotation.Nonnull;

public interface InternalArrayResponseInterface {
    
    @Nonnull
    List<Root> getRoot();

    void setRoot(@Nonnull List<Root> roots);

    @Nonnull
    List<Member> getMembers();

    void setMembers(@Nonnull List<Member> members);

    @Nonnull
    List<Music> getMusics();

    void setMusics(@Nonnull List<Music> musics);

    @Nonnull
    List<Lyrics> getLyricses();

    void setLyricses(@Nonnull List<Lyrics> lyricses);

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
