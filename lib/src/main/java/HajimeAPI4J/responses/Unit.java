package HajimeAPI4J.responses;

import java.util.List;

/**
 * ふじわらはじめAPIで使用されているレスポンス型「Unit」の宣言です。
 * 似通ったレスポンスパラメータを取るため、{@link HajimeAPI4J.responses.ResponseAdapter 独自アダプター} を継承しています
 * 
 * @author Ranfa
 * @since 1.0.0
 * 
 * @see ResponseAdapter
 */
public class Unit extends ResponseAdapter {

	private String name;
	private String type;
	private int taxId;
	private String link;
	private String api;
	private List<Member> members;

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
	public Integer getTaxId() {
		return taxId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setTaxId(Integer taxId) {
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
	public List<Member> getMembers() {
		return members;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMembers(List<Member> members) {
		this.members = members;
	}

}
