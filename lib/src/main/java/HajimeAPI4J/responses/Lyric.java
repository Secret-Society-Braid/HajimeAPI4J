package HajimeAPI4J.responses;

import HajimeAPI4J.responses.interfaces.ResponseAdapter;

/**
 * ふじわらはじめAPIで使用されるレスポンス「lyrics」の宣言です。
 * この宣言には {@link HajimeAPI4J.responses.interfaces.ResponseAdapter 独自アダプター} を継承しています。
 * 
 * @author Ranfa
 * @since 
 */
public class Lyric extends ResponseAdapter {
    
    // Declare field
    private String name;
    private String type;
    private int taxId;
    private String link;
    private String api;
    
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
    public String getType() {
        return type;
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
    public void setTaxId(int taxId) {
        this.taxId = taxId;
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
