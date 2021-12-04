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

/**
 * ふじわらはじめAPIで使用されているレスポンス型のインターフェースです。
 * レスポンス型の宣言には {@link HajimeAPI4J.responses.interfaces.ResponseAdapter アダプタークラス} を継承しています。
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
public interface InternalArrayResponseInterface {

    /**
     * レスポンスに含まれているパラメータ「member」の配列を返します。
     * @return レスポンスに含まれているパラメータのうち、keyが「member」であるものの配列
     */
    @Nonnull
    List<Member> getMembers();

    /**
     * レスポンスに含まれているパラメータ「member」の配列をセットします。
     * @param members パラメータ「member」の配列
     */
    void setMembers(@Nonnull List<Member> members);

    /**
     * 
     * @return
     */
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
    List<Disc> getDiscs();

    void setDiscs(@Nonnull List<Disc> discs);

    @Nonnull
    List<Live> getLives();

    void setLives(@Nonnull List<Live> lives);

}
