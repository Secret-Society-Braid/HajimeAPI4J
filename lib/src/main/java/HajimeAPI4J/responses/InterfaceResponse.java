package HajimeAPI4J.responses;

import javax.annotation.Nonnull;

/**
 * ふじわらはじめAPIで使用されているレスポンス型の共通部分のインターフェースです。
 * 型ごとにユニークなパラメータについてはそれぞれのクラスで実装しています。
 *
 * @author Ranfa
 *
 * @see Unit
 * @see Member
 */
public interface InterfaceResponse {

	/**
	 * レスポンスに含まれているパラメータ「name」の値を返します。
	 * @return レスポンスに含まれているパラメータのうち、keyが「name」であるもののvalueのGetter
	 */
	String getName();

	/**
	 * レスポンスに含まれているパラメータ「name」の値をセットします
	 */
	void setName(String name);

	/**
	 * レスポンスに含まれているパラメータ「type」の値を返します。
	 * ふじわらはじめAPIの仕様上、返り値は「unit」「idol」の2種類のみとなります。
	 * @return レスポンスに含まれているパラメータのうち、keyが「type」であるもののvalueのGetter
	 */
	@Nonnull
	String getType();

	/**
	 * レスポンスに含まれているパラメータ「type」の値をセットします
	 */
	void setType(String type);

	/**
	 * レスポンスに含まれているパラメータ「tax_id」の値を返します。
	 * ふじわらはじめAPIの仕様上、返り値が必ず存在します。
	 * @return レスポンスに含まれているパラメータのうち、keyが「tax_id」であるもののvalueのGetter
	 */
	@Nonnull
	Integer getTaxId();

	/**
	 * レスポンスに含まれているパラメータ「tax_id」の値をセットします
	 */
	void setTaxId(Integer taxId);

	/**
	 * レスポンスに含まれているパラメータ「link」の値を返します。
	 * ふじわらはじめAPIの仕様上、返り値が必ず存在します。
	 * @return レスポンスに含まれているパラメータのうち、keyが「link」であるもののvalueのGetter
	 */
	@Nonnull
	String getLink();

	/**
	 * レスポンスに含まれているパラメータ「link」の値をセットします
	 */
	void setLink(String link);

	/**
	 * レスポンスに含まれているパラメータ「api」の値を返します。
	 * ふじわらはじめAPIの仕様上、返り値が必ず存在します。
	 * @return レスポンスに含まれているパラメータのうち、keyが「api」であるもののvalueのGetter
	 */
	@Nonnull
	String getApi();

	/**
	 * レスポンスに含まれているパラメータ「api」の値をセットします
	 */
	void setApi(String api);

}
