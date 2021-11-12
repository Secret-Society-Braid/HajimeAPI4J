package HajimeAPI4J.responses;

import javax.annotation.Nonnull;

/**
 * ふじわらはじめAPIで使用されているレスポンス型のインターフェースです。
 * レスポンス型の宣言には {@link HajimeAPI4J.responses.ResponseAdapter アダプタークラス} を継承しています 
 *
 * @author Ranfa
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
	Integer getTaxId();

	/**
	 * レスポンスに含まれているパラメータ「tax_id」の値をセットします
	 * @param taxId パラメータ「tax_id」の値
	 */
	void setTaxId(@Nonnull Integer taxId);

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
