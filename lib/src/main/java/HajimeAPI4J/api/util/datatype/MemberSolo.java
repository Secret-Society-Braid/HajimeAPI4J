package HajimeAPI4J.api.util.datatype;

import lombok.Getter;
import lombok.Setter;

/**
 * ふじわらはじめAPIでよく使用されているMember型を定義したクラスです。
 * フィールド名はパラメータ名から、説明はAPI説明にて掲載されている文言と同じものとしています。
 */
@Getter
@Setter
public class MemberSolo {
    
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
}
