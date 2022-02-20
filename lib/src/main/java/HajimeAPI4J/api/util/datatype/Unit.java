package HajimeAPI4J.api.util.datatype;

import java.util.List;


/**
 * ふじわらはじめAPIでよく使用されているUnit型を定義したクラスです。
 * フィールド名はパラメータ名から、説明はAPI説明にて掲載されている文言と同じものとしています。
 */
public class Unit {
    
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
    private List<MemberSolo> member;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public List<MemberSolo> getMember() {
        return member;
    }

    public void setMember(List<MemberSolo> member) {
        this.member = member;
    }

    
}
