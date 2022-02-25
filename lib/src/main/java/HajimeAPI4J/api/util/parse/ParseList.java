package HajimeAPI4J.api.util.parse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import HajimeAPI4J.api.HajimeAPI4J;
import HajimeAPI4J.api.HajimeAPIBuilder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import HajimeAPI4J.api.util.internal.IParse;

public class ParseList implements IParse {

    private Logger logger = LoggerFactory.getLogger(ParseList.class);
    private JsonNode node = null;
    private ArrayNode arrayNode = null;

    // constructor
    public ParseList(JsonNode node) {
	    this.node = node;
        if(node.isArray()) {
            this.arrayNode = (ArrayNode)node;
        } else {
            /* This won't be happened. */
            logger.error("Illegal node type. was there some changes in API?");
            throw new RuntimeException("Illegal node type. was there some changes in API?");
        }
    }


    /**
     * 指定した配列インデックスのNodeに格納されている「name」キーの値を取得します。
     * 
     * @param index 指定する配列インデックス
     * 
     * @throws IndexOutOfBoundsException 指定したインデックスが配列の範囲外の場合
     * 
     * @return 指定した配列インデックスのNodeに格納されている「name」キーの値
     */
    public String getName(int index) {
        if(this.arrayNode.get(index) != null) {
            return this.arrayNode.get(index).get("name").asText();
        }
        throw new IndexOutOfBoundsException("input " + index + " is out of bounds. length : " + this.arrayNode.size());
    }

    /**
     * 指定した配列インデックスのNodeに格納されている「type」キーの値を取得します。
     * 
     * @param index 指定する配列インデックス
     * 
     * @throws IndexOutOfBoundsException 指定したインデックスが配列の範囲外の場合
     * 
     * @return 指定した配列インデックスのNodeに格納されている「type」キーの値
     */
    public String getType(int index) {
        if(this.arrayNode.get(index) != null) {
            return this.arrayNode.get(index).get("type").asText();
        }
        throw new IndexOutOfBoundsException("input " + index + " is out of bounds. length : " + this.arrayNode.size());
    }

    /**
     * 指定した配列インデックスのNodeに格納されている「song_id, tax_id」キーの値を取得します。
     * 
     * @param index 指定する配列インデックス
     * 
     * @throws NullPointerException 指定したインデックスのNodeに「song_id, tax_id」キーが存在しない場合
     * @throws IndexOutOfBoundsException 指定したインデックスが配列の範囲外の場合
     * 
     * @return 指定した配列インデックスのNodeに格納されている「song_id, tax_id」キーの値
     */
    public int getInternalId(int index) {
        if(this.arrayNode.get(index) != null) {
            JsonNode indexNode = this.arrayNode.get(index);
            while(indexNode.fieldNames().hasNext()) {
                String fieldName = indexNode.fieldNames().next();
                if(fieldName.equals("song_id") || fieldName.equals("tax_id")) {
                    return indexNode.get(fieldName).asInt();
                }
            }
            throw new NullPointerException("song_id or tax_id is not found.");
        }
        throw new IndexOutOfBoundsException("input " + index + " is out of bounds. length : " + this.arrayNode.size());
    }

    /**
     * 指定した配列インデックスのNodeに格納されている「link」キーの値を取得します。
     * 
     * @param index 指定する配列インデックス
     * 
     * @throws IndexOutOfBoundsException 指定したインデックスが配列の範囲外の場合
     * 
     * @return 指定した配列インデックスのNodeに格納されている「link」キーの値
     */
    public String getLink(int index) {
        if(this.arrayNode.get(index) != null) {
            return this.arrayNode.get(index).get("link").asText();
        }
        throw new IndexOutOfBoundsException("input " + index + " is out of bounds. length : " + this.arrayNode.size());
    }

    /**
     * 指定した配列インデックスのNodeに格納されている「api」キーの値を取得します。
     * 
     * @param index 指定する配列インデックス
     * 
     * @throws IndexOutOfBoundsException 指定したインデックスが配列の範囲外の場合
     * 
     * @return 指定した配列インデックスのNodeに格納されている「api」キーの値
     */
    public String getApi(int index) {
        if(this.arrayNode.get(index) != null) {
            return this.arrayNode.get(index).get("api").asText();
        }
        throw new IndexOutOfBoundsException("input " + index + " is out of bounds. length : " + this.arrayNode.size());
    }

    /**
     * 指定した配列インデックスのNodeを取得します。
     * 
     * @param index 指定する配列インデックス
     * 
     * @throws IndexOutOfBoundsException 指定したインデックスが配列の範囲外の場合
     * 
     * @return 指定した配列インデックスのNode
     */
    public JsonNode getNode(int index) {
        if(this.arrayNode.get(index) != null) {
            return this.arrayNode.get(index);
        }
        throw new IndexOutOfBoundsException("input " + index + " is out of bounds. length : " + this.arrayNode.size());
    }

    /**
     * 指定したキーに対応する値が含まれているNodeのインデックスを返します。
     * 一致が見つからない場合は値にその文字列が含まれているNodeのインデックスを返します。
     * 見つからない場合は-1を返します。
     * 
     * @param key 検索するキー
     * @param value 検索する値
     * 
     * @return 指定したキーに対応する値が含まれているNodeのインデックス
     */
    public int getIndex(String key, String value) {
        for (int i = 0; i < this.arrayNode.size(); i++) {
            if (this.arrayNode.get(i).get(key).asText().equals(value)) {
                return i;
            } else if (this.arrayNode.get(i).get(key).asText().contains(value)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 指定した文字列が「name」キーの値に一致したものを検索し、そのNodeを返します。
     * 一致しなかった場合は指定した文字列を含むNodeを検索し返します。
     * 両方あてはまるものがなかった場合、nullを返します。
     * 
     * @param name 検索する文字列
     * 
     * @return 指定した文字列が「name」キーの値に一致したもの、もしくは指定した文字列を含むNode
     */
    public JsonNode getNode( String name ) {
        for(JsonNode node : this.arrayNode) {
            if(node.get("name").asText().equals(name)) {
                return node;
            } else if(node.get("name").asText().contains(name)) {
                return node;
            }
        }
        return null;
    }

    /**
     * @deprecated listトークンではレスポンスは配列で返されるため、このメソッドではなく {@link #getName(int)} を使用してください
     * 
     * {@inheritDoc}
     * 
     * @see #getName(int)
     */
    @Deprecated
    @Override
    public String getName() {
        throw new UnsupportedOperationException("This operation is not supported by list token.");
    }

    /**
     * @deprecated listトークンではレスポンスは配列で返されるため、このメソッドではなく {@link #getType(int)} を使用してください
     * 
     * {@inheritDoc}
     * 
     * @see #getType(int)
     */
    @Deprecated
    @Override
    public String getType() {
        throw new UnsupportedOperationException("This operation is not supported by list token.");
    }

    /**
     * @deprecated listトークンではレスポンスは配列で返されるため、このメソッドではなく {@link #getInternalId(int)} を使用してください
     * 
     * {@inheritDoc}
     * 
     * @see #getInternalId(int)
     */
    @Deprecated
    @Override
    public String getInternalId() {
        throw new UnsupportedOperationException("This operation is not supported by list token.");
    }

    /**
     * @deprecated listトークンではレスポンスは配列で返されるため、このメソッドではなく {@link #getLink(int)} を使用してください
     * 
     * {@inheritDoc}
     * 
     * @see #getLink(int)
     */
    @Deprecated
    @Override
    public String getLink() {
        throw new UnsupportedOperationException("This operation is not supported by list token.");
    }

    /**
     * @deprecated listトークンではレスポンスは配列で返されるため、このメソッドではなく {@link #getApi(int)} を使用してください
     * 
     * {@inheritDoc}
     * 
     * @see #getApi(int)
     */
    @Deprecated
    @Override
    public String getApi() {
        throw new UnsupportedOperationException("This operation is not supported by list token.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonNode getJsonNode() {
        return node;
    }

    /**
     * 指定したインデックスの「api」情報を使用して新しいHajimeAPI４Jのインスタンスを返します。
     * 楽曲情報などの詳細を調べる用途に便利です。
     *
     * @param index 指定するインデックス
     *
     * @return 「music」もしくは「tax」トークンと「api」データが設定された新しいHajimeAPI４Jインスタンス
     */
    public HajimeAPI4J getAPIInstance(int index) {
        JsonNode node = this.arrayNode.get(index);
        HajimeAPIBuilder builder;
        if(node.get("type").asText().equals("music")) {
            builder = HajimeAPIBuilder.createDefault(HajimeAPI4J.Token.MUSIC);
            builder.addParameter(HajimeAPI4J.Music_Params.ID, node.get("song_id").asText());
            return builder.build();
        } else {
            builder = HajimeAPIBuilder.createDefault(HajimeAPI4J.Token.TAX);
            builder.addParameter(HajimeAPI4J.Tax_Params.ID, node.get("tax_id").asText());
            return builder.build();
        }
    }

}
