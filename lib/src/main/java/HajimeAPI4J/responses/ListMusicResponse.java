package HajimeAPI4J.responses;

import HajimeAPI4J.responses.interfaces.ResponseAdapter;

/**
 * APIへlist,musicを使用してリクエストを送った際のレスポンスです。
 * このクラスのフィールドはすべてNull値を受け付けません。
 *
 * @author Ranfa
 * @since 1.0.0
 */
public class ListMusicResponse extends ResponseAdapter {

	// Declare field
	private String name;
	private String type;
	private String musicType;
	private int songId;
	private String link;
	private String api;

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
	 * 楽曲のタイプ「cg, ml, sc, as, joint, cover」を取得します。
	 * @return 楽曲のタイプ
	 */
	public String getMusicType() {
		return musicType;
	}

	/**
	 * 楽曲のタイプ「cg, ml, sc, as, joint, cover」をセットします。
	 * @param musicType 楽曲のタイプ
	 */
	public void setMusicType(String musicType) {
		this.musicType = musicType;
	}

	/**
	 * 楽曲IDを取得します。
	 * @return 楽曲ID
	 */
	public int getSongId() {
		return songId;
	}

	/**
	 * 楽曲IDをセットします。
	 * @param songId　楽曲ID
	 */
	public void setSongId(int songId) {
		this.songId = songId;
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



}
