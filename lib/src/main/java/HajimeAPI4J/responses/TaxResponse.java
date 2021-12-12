package HajimeAPI4J.responses;

import java.util.List;

import HajimeAPI4J.responses.interfaces.ResponseAdapter;
import HajimeAPI4J.responses.wrapper.Arrange;
import HajimeAPI4J.responses.wrapper.Composer;
import HajimeAPI4J.responses.wrapper.Lyric;
import HajimeAPI4J.responses.wrapper.Member;
import HajimeAPI4J.responses.wrapper.Music;


/**
 * APIへtaxのリクエストを送った際のレスポンスです。
 * @author Ranfa
 * @since 1.0.0
 *
 */
public class TaxResponse extends ResponseAdapter {

	//Declare field
	private String name;
	private String type;
	private int taxId;
	private String link;
	private String api;
	private String kana;
	private String cv;
	private String cvKana;
	private String production;
	private String date;
	private String place;
	private List<Member> members;
	private boolean setList;
	private Lyric lyric;
	private Composer composer;
	private Arrange arrange;
	private List<Music> musics;

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
	public String getPlace() {
		return place;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPlace(String place) {
		this.place = place;
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean getSetList() {
		return setList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setSetList(boolean setList) {
		this.setList = setList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Lyric getLyric() {
		return lyric;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setLyric(Lyric lyric) {
		this.lyric = lyric;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Composer getComposer() {
		return composer;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setComposer(Composer composer) {
		this.composer = composer;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Arrange getArrange() {
		return arrange;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setArrange(Arrange arrange) {
		this.arrange = arrange;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Music> getMusics() {
		return musics;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMusics(List<Music> musics) {
		this.musics = musics;
	}



}
