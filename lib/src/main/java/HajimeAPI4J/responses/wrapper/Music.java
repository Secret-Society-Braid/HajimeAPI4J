package HajimeAPI4J.responses.wrapper;

import java.util.List;

import HajimeAPI4J.responses.interfaces.ResponseAdapter;

/**
 * ふじわらはじめAPIで使用されるレスポンス「music」の宣言です。
 * この宣言には {@link HajimeAPI4J.responses.interfaces.ResponseAdapter 独自アダプター} を継承しています。
 *
 * @author Ranfa
 * @since 1.0.0
 */
public class Music extends ResponseAdapter {

    // Declare field
    private String name;
    private String type;
    private String musicType;
    private int songId;
    private String link;
    private String api;
    private String songText;
    private List<Unit> units;
    private List<Member> members;

    // getter

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
     * ふじわらはじめAPIで使用されるレスポンス「music_type」の値を取得します。
     * @return パラメータ「music_type」の値
     */
    public String getMusicType() {
        return musicType;
    }

    /**
     * ふじわらはじめAPIで使用されるレスポンス「music_type」の値をセットします。
     * @param musicType パラメータ「music_type」の値
     */
    public void setMusicType(String musicType) {
        this.musicType = musicType;
    }

    /**
     * ふじわらはじめAPIで使用されるレスポンス「song_id」の値を取得します。
     * @return パラメータ「song_id」の値
     */
    public int getSongId() {
        return songId;
    }

    /**
     * ふじわらはじめAPIで使用されるレスポンス「song_id」の値をセットします。
     * @param songId パラメータ「song_id」の値
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

    /**
     * ふじわらはじめAPIで使用されるレスポンス「song_text」の値を取得します。
     * @return パラメータ「song_text」の値
     */
    public String getSongText() {
    	return songText;
    }

    /**
     * ふじわらはじめAPIで使用されるレスポンス「song_text」の値をセットします。
     * @param songText パラメータ「song_text」の値
     */
    public void setSongText(String songText) {
    	this.songText = songText;
    }

    /**
     * ふじわらはじめAPIで使用されるレスポンス「unit」の配列を取得します。
     * @return パラメータ「unit」の配列
     */
    public List<Unit> getUnits() {
		return units;
	}

    /**
     * ふじわらはじめAPIで使用されるレスポンス「unit」の配列をセットします。
     * @param units　パラメータ「unit」の配列
     */
	public void setUnits(List<Unit> units) {
		this.units = units;
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
