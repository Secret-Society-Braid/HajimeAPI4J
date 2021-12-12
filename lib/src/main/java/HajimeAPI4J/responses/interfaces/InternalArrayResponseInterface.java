package HajimeAPI4J.responses.interfaces;

import java.util.List;

import javax.annotation.Nonnull;

import HajimeAPI4J.responses.wrapper.Arrange;
import HajimeAPI4J.responses.wrapper.Composer;
import HajimeAPI4J.responses.wrapper.Disc;
import HajimeAPI4J.responses.wrapper.Live;
import HajimeAPI4J.responses.wrapper.Lyric;
import HajimeAPI4J.responses.wrapper.Member;
import HajimeAPI4J.responses.wrapper.Music;

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
     * レスポンスに含まれているパラメータ「music」の配列を返します。
     * @return レスポンスに含まれているパラメータのうち、Keyが「music」であるものの配列
     */
    @Nonnull
    List<Music> getMusics();

    /**
     * レスポンスに含まれているパラメータ「music」の配列をセットします。
     * @param musics パラメータ「music」の配列
     */
    void setMusics(@Nonnull List<Music> musics);

    /**
     * レスポンスに含まれているパラメータ「lyrics」の配列を返します。
     * @return レスポンスに含まれているパラメータのうち、keyが「lyrics」であるものの配列
     */
    @Nonnull
    List<Lyric> getLyrics();

    /**
     * レスポンスに含まれているパラメータ「lyrics」の配列をセットします。
     * @param lyrics パラメータ「lyrics」の配列
     */
    void setLyrics(@Nonnull List<Lyric> lyrics);

    /**
     * レスポンスに含まれているパラメータ「composer」の配列を返します。
     * @return レスポンスに含まれているパラメータのうち、keyが「composer」であるものの配列
     */
    @Nonnull
    List<Composer> getComposers();

    /**
     * レスポンスに含まれているパラメータ「composer」の配列をセットします。
     * @param composers パラメータ「composer」の配列
     */
    void setComposers(@Nonnull List<Composer> composers);
/**
     * レスポンスに含まれているパラメータ「arrange」の配列を返します。
     * @return レスポンスに含まれているパラメータのうち、keyが「arrange」であるものの配列
     */
    @Nonnull
    List<Arrange> getArranges();

    /**
     * レスポンスに含まれているパラメータ「arrange」の配列をセットします。
     * @param arranges パラメータ「arrange」の配列
     */
    void setArranges(@Nonnull List<Arrange> arranges);
/**
     * レスポンスに含まれているパラメータ「disc」の配列を返します。
     * @return レスポンスに含まれているパラメータのうち、keyが「disc」であるものの配列
     */
    @Nonnull
    List<Disc> getDiscs();

    /**
     * レスポンスに含まれているパラメータ「disc」の配列をセットします。
     * @param discs パラメータ「disc」の配列
     */
    void setDiscs(@Nonnull List<Disc> discs);
/**
     * レスポンスに含まれているパラメータ「live」の配列を返します。
     * @return レスポンスに含まれているパラメータのうち、keyが「live」であるものの配列
     */
    @Nonnull
    List<Live> getLives();

    /**
     * レスポンスに含まれているパラメータ「live」の配列をセットします。
     * @param lives パラメータ「live」の配列
     */
    void setLives(@Nonnull List<Live> lives);

}
