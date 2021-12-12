package HajimeAPI4J.responses.interfaces;

import javax.annotation.Nonnull;

import HajimeAPI4J.responses.wrapper.Arrange;
import HajimeAPI4J.responses.wrapper.Composer;
import HajimeAPI4J.responses.wrapper.Disc;
import HajimeAPI4J.responses.wrapper.Live;
import HajimeAPI4J.responses.wrapper.Lyric;
import HajimeAPI4J.responses.wrapper.Member;
import HajimeAPI4J.responses.wrapper.Music;
import HajimeAPI4J.responses.wrapper.Unit;

/**
 * ふじわらはじめAPIで使用されているレスポンス型のインターフェースです。
 * レスポンス型の宣言には {@link HajimeAPI4J.responses.interfaces.ResponseAdapter アダプタークラス} を継承しています 
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
public interface ResponseInterface {

	/**
	 * レスポンスに含まれているパラメータ「name」の値を返します。
	 * @return レスポンスに含まれているパラメータのうち、keyが「name」であるもののvalue
	 */
	String getName();

	/**
	 * レスポンスに含まれているパラメータ「name」の値をセットします
	 * @param name パラメータ「name」の値
	 */
	void setName(@Nonnull String name);

	/**
	 * レスポンスに含まれているパラメータ「type」の値を返します。
	 * ふじわらはじめAPIの仕様上、返り値は「unit」「idol」の2種類のみとなります。
	 * @return レスポンスに含まれているパラメータのうち、keyが「type」であるもののvalue
	 */
	@Nonnull
	String getType();

	/**
	 * レスポンスに含まれているパラメータ「type」の値をセットします
	 * @param type パラメータ「type」の値
	 */
	void setType(@Nonnull String type);

	/**
	 * レスポンスに含まれているパラメータ「tax_id」の値を返します。
	 * ふじわらはじめAPIの仕様上、返り値が必ず存在します。
	 * @return レスポンスに含まれているパラメータのうち、keyが「tax_id」であるもののvalue
	 */
	@Nonnull
	int getTaxId();

	/**
	 * レスポンスに含まれているパラメータ「tax_id」の値をセットします
	 * @param taxId パラメータ「tax_id」の値
	 */
	void setTaxId(int taxId);

	/**
	 * レスポンスに含まれているパラメータ「link」の値を返します。
	 * ふじわらはじめAPIの仕様上、返り値が必ず存在します。
	 * @return レスポンスに含まれているパラメータのうち、keyが「link」であるもののvalue
	 */
	@Nonnull
	String getLink();

	/**
	 * レスポンスに含まれているパラメータ「link」の値をセットします
	 * @param link パラメータ「link」の値
	 */
	void setLink(@Nonnull String link);

	/**
	 * レスポンスに含まれているパラメータ「api」の値を返します。
	 * ふじわらはじめAPIの仕様上、返り値が必ず存在します。
	 * @return レスポンスに含まれているパラメータのうち、keyが「api」であるもののvalue
	 */
	@Nonnull
	String getApi();

	/**
	 * レスポンスに含まれているパラメータ「api」の値をセットします
	 * @param api パラメータ「api」の値
	 */
	void setApi(@Nonnull String api);

}
