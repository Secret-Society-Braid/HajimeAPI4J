package hajimeapi4j.api.util.datatype;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import hajimeapi4j.api.HajimeAPI4J;
import hajimeapi4j.api.util.internal.IParse;

import java.io.IOException;
import java.util.List;


/**
 * ふじわらはじめAPIでよく使用されているUnit型を定義したクラスです。
 * フィールド名はパラメータ名から、説明はAPI説明にて掲載されている文言と同じものとしています。
 *
 * @author Ranfa
 * @since 1.0.0
 */
public class Unit implements IParse {
    
    /**ユニット名 */
    private String name;

    /**出力しているカテゴリ名 */
    private String type;

    /**カテゴリID（ふじわらはじめAPI内部管理ID） */
    private int tax_id;

    /**ふじわらはじめAPI内カテゴリーページURL */
    private String link;

    /**カテゴリのJSON 取得用URI */
    private String api;

    /**所属メンバー */
    private List<Member> member;

    //constructor
    public Unit() {
        this("", "", 0, "", "", null);
    }

    public Unit(String name, String type, int tax_id, String link, String api, List<Member> member) {
        this.name = name;
        this.type = type;
        this.tax_id = tax_id;
        this.link = link;
        this.api = api;
        this.member = member;
    }

    public Unit(JsonNode node) {
        this.name = node.get("name").asText();
        this.type = node.get("type").asText();
        this.tax_id = node.get("tax_id").asInt();
        this.link = node.get("link").asText();
        this.api = node.get("api").asText();
        try {
            List<Member> members = new ObjectMapper().readValue(node.get("member").traverse(), new TypeReference<List<Member>>() {});
            this.member = members;
        } catch (IOException e) {
            e.printStackTrace();
            this.member = null;
        }
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTax_id() {
        return tax_id;
    }

    public void setTax_id(int tax_id) {
        this.tax_id = tax_id;
    }

    @Override
    public String getInternalId() {
        return String.valueOf(getTax_id());
    }

    @Override
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public List<Member> getMember() {
        return member;
    }

    public void setMember(List<Member> member) {
        this.member = member;
    }

    @Override
    public JsonNode getJsonNode() {
        throw new UnsupportedOperationException("This operation is not supported by this class.");
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
