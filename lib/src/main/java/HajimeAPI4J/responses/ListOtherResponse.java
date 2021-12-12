package HajimeAPI4J.responses;

import HajimeAPI4J.responses.interfaces.ResponseAdapter;

/**
 * APIへlist, music以外のリクエストを送った際のレスポンスです。
 * @author Ranfa
 * @since 1.0.0
 *
 */
public class ListOtherResponse extends ResponseAdapter {

	// Declare field
	private String name;
	private String type;
	private int taxId;
	private String link;
	private String api;
	private String date;
	private String production;
	private String kana;
	private String cv;
	private String cvKana;


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
	public String getDate() {
		return date;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setDate(String date) {
		this.date = date;
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
	public String getKana() {
		return kana;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setKana(String kana) {
		this.kana = kana;
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
	 * {@inheritDoc}
	 */
	@Override
	public String getCvKana() {
		return cvKana;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setCvKana(String cvKana) {
		this.cvKana = cvKana;
	}



}
