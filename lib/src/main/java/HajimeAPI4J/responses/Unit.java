package HajimeAPI4J.responses;

import java.util.List;

public class Unit implements InterfaceResponse {

	private String name;
	private String type;
	private int taxId;
	private String link;
	private String api;
	private List<? extends Member> members;

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

	public List<? extends Member> getMembers() {
		return members;
	}

	public void setMembers(List<? extends Member> members) {
		this.members = members;
	}

}
