package HajimeAPI4J.responses;

/**
 * ふじわらはじめAPIで使用されている「Member」型の実装です。
 * 独自パラメータは
 * production, cv, solo
 * の3つです。
 * @author Ranfa
 *
 * @see ResponseInterface
 */
public class Member implements ResponseInterface {

	private String name;
	private String type;
	private int taxId;
	private String link;
	private String api;
	private String production;
	private String cv;
	private boolean solo;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public Integer getTaxId() {
		return taxId;
	}

	@Override
	public void setTaxId(Integer taxId) {
		this.taxId = taxId;
	}

	@Override
	public String getLink() {
		return link;
	}

	@Override
	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String getApi() {
		return api;
	}

	@Override
	public void setApi(String api) {
		this.api = api;
	}

	/**
	 * APIから取得したproductionの値を返します。
	 * このパラメータには「cg」「765」「sc」のうちどれかが入ります。
	 * @return production
	 */
	public String getProduction() {
		return production;
	}

	/**
	 * @param production セットする production
	 */
	public void setProduction(String production) {
		this.production = production;
	}

	/**
	 * @return cv
	 */
	public String getCv() {
		return cv;
	}

	/**
	 * @param cv セットする cv
	 */
	public void setCv(String cv) {
		this.cv = cv;
	}

	/**
	 * @return solo
	 */
	public boolean isSolo() {
		return solo;
	}

	/**
	 * @param solo セットする solo
	 */
	public void setSolo(boolean solo) {
		this.solo = solo;
	}



}
