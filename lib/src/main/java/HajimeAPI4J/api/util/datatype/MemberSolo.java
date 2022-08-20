package hajimeapi4j.api.util.datatype;


import com.fasterxml.jackson.databind.JsonNode;

import hajimeapi4j.api.HajimeAPI4J;

/**
 * ふじわらはじめAPIでよく使用されているMember型を定義したクラスです。
 * フィールド名はパラメータ名から、説明はAPI説明にて掲載されている文言と同じものとしています。
 *
 * @author Ranfa
 * @since 1.0.0
 */
public class MemberSolo extends Member {

    /** ソロバージョンかどうか */
    private boolean solo;
    
    public MemberSolo(JsonNode node) {
        super(node);
        this.solo = node.get("solo").asBoolean();
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
    @Deprecated(forRemoval = false)
    @Override
    public HajimeAPI4J getAPIInstance() {
        throw new UnsupportedOperationException("This operation is not supported in this class.");
    }


}
