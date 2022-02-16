package HajimeAPI4J.api.util.datatype;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * ふじわらはじめAPIでよく使用されているUnit型を定義したクラスです。
 * フィールド名はパラメータ名から、説明はAPI説明にて掲載されている文言と同じものとしています。
 */
@Getter
@Setter
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

}
