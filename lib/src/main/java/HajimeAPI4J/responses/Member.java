package HajimeAPI4J.responses;

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

	public String getProduction() {
		return production;
	}

	public void setProduction(String production) {
		this.production = production;
	}

	public String getCv() {
		return cv;
	}

	public void setCv(String cv) {
		this.cv = cv;
	}

	public boolean isSolo() {
		return solo;
	}

	public void setSolo(boolean solo) {
		this.solo = solo;
	}



}
