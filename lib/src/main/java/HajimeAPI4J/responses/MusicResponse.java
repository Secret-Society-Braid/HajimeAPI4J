package HajimeAPI4J.responses;

import java.util.List;


import HajimeAPI4J.responses.interfaces.ResponseAdapter;
import HajimeAPI4J.responses.wrapper.Arrange;
import HajimeAPI4J.responses.wrapper.Composer;
import HajimeAPI4J.responses.wrapper.Disc;
import HajimeAPI4J.responses.wrapper.Live;
import HajimeAPI4J.responses.wrapper.Lyric;
import HajimeAPI4J.responses.wrapper.Member;

/**
 * APIへmusicを使いリクエストを送った際のレスポンスです。
 * @author Ranfa
 * @since 1.0.0
 *
 */
public class MusicResponse extends ResponseAdapter {

	//Declare field
	private String name;
	private String type;
	private int musicId;
	private String link;
	private String api;
	private List<Lyric> lyrics;
	private List<Composer> composers;
	private List<Arrange> arranges;
	private String lyricsUrl;
	private List<Member> members;
	private List<Disc> discs;
	private List<Live> lives;

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

	public int getMusicId() {
		return musicId;
	}
	public void setMusicId(int musicId) {
		this.musicId = musicId;
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
	public List<Lyric> getLyrics() {
		return lyrics;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setLyrics(List<Lyric> lyrics) {
		this.lyrics = lyrics;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Composer> getComposers() {
		return composers;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setComposers(List<Composer> composers) {
		this.composers = composers;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Arrange> getArranges() {
		return arranges;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setArranges(List<Arrange> arranges) {
		this.arranges = arranges;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getLyricsUrl() {
		return lyricsUrl;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setLyricsUrl(String lyricsUrl) {
		this.lyricsUrl = lyricsUrl;
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
	public List<Disc> getDiscs() {
		return discs;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setDiscs(List<Disc> discs) {
		this.discs = discs;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Live> getLives() {
		return lives;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setLives(List<Live> lives) {
		this.lives = lives;
	}


}
