package HajimeAPI4J.api.util.datatype;


import HajimeAPI4J.api.HajimeAPI4J;
import HajimeAPI4J.api.util.internal.IParse;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * ふじわらはじめAPIでよく使用されているMember型を定義したクラスです。
 * フィールド名はパラメータ名から、説明はAPI説明にて掲載されている文言と同じものとしています。
 *
 * @author Ranfa
 * @since 1.0.0
 */
public class MemberSolo extends Member implements IParse {
    
    /**アイドル名 */
    private String name;
    
    /**出力しているカテゴリ名 */
    private String type;

    /**カテゴリID（ふじわらはじめAPI内部管理ID） */
    private int tax_id;

    /**ふじわらはじめAPI内カテゴリーページURL */
    private String link;

    /**カテゴリのJSON 取得用URI */
    private String api;

    /**アイドルの所属プロダクション */
    private String production;

    /**声優名 */
    private String cv;

    /**ソロバージョンかどうか(Solo型のみ) */
    private boolean solo;

    public MemberSolo(JsonNode node) {
        super();
        this.name = node.get("name").asText();
        this.type = node.get("type").asText();
        this.tax_id = node.get("tax_id").asInt();
        this.link = node.get("link").asText();
        this.api = node.get("api").asText();
        this.production = node.get("production").asText();
        this.cv = node.get("cv").asText();
    }

    public boolean isSolo() {
        return solo;
    }

    public void setSolo(boolean solo) {
        this.solo = solo;
    }

    /**
     * {@inheritDoc}
     *
     * @deprecated このクラスではこの操作はできません。
     */
    @Deprecated
    @Override
    public HajimeAPI4J getAPIInstance() {
        throw new UnsupportedOperationException("This operation is not supported in this class.");
    }


}
