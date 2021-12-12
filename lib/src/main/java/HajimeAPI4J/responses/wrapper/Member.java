package HajimeAPI4J.responses.wrapper;

import HajimeAPI4J.responses.interfaces.ResponseAdapter;

/**
 * ふじわらはじめAPIで使用されているレスポンス型「Member」の宣言です。
 * 似通ったレスポンスパラメータを取るため、{@link HajimeAPI4J.responses.interfaces.ResponseAdapter 独自アダプター} を継承しています
 * 
 * @author Ranfa
 * @since 1.0.0
 * 
 * @see ResponseAdapter
 */
public class Member extends ResponseAdapter {

	private String name;
	private String type;
	private int taxId;
	private String link;
	private String api;
	private String production;
	private String cv;
	private boolean solo;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getType() {
		return type;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getTaxId() {
		return taxId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setTaxId(int taxId) {
		this.taxId = taxId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getLink() {
		return link;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getApi() {
		return api;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setApi(String api) {
		this.api = api;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getProduction() {
		return production;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setProduction(String production) {
		this.production = production;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCv() {
		return cv;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setCv(String cv) {
		this.cv = cv;
	}

	/**
	 * レスポンスに含まれているパラメータ「solo」の値を返します
	 * @return ソロが実装されている場合は<code>TRUE</code>、そうでない場合は<code>FALSE</code>
	 */
	public boolean isSolo() {
		return solo;
	}

	/**
	 * レスポンスに含まれているパラメータ「solo」の値をセットします
	 * @param solo レスポンスに含まれているパラメータ「solo」のvalue
	 */
	public void setSolo(boolean solo) {
		this.solo = solo;
	}
}
