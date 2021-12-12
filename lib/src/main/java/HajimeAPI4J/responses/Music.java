package HajimeAPI4J.responses;

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
    // TODO: make the others method

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
    public String getType() {
        return type;
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
    public String getApi() {
        return api;
    }

    // setter

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
    public void setType(String type) {
        this.type = type;
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
    public void setApi(String api) {
        this.api = api;
    }

}
