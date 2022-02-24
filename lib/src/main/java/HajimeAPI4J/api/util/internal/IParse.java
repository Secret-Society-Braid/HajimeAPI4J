package HajimeAPI4J.api.util.internal;

import com.fasterxml.jackson.databind.JsonNode;

import HajimeAPI4J.api.util.parse.ParseList;
import HajimeAPI4J.api.util.parse.ParseMusic;
import HajimeAPI4J.api.util.parse.ParseTax;

/**
 * パース処理共通部分のインターフェースです。
 * list, tax, musicそれぞれのトークンにて共通でないレスポンスパラメータが存在するため、固有レスポンスを取り出す際には実装クラスをそのまま使用してください。
 * 共通部分であるデータを取り出す際にはこちらのインターフェースを使用してもかまいません。
 * 
 * @author Ranfa
 * @since 2.0.5-Experimental
 * 
 * @see ParseList
 * @see ParseTax
 * @see ParseMusic
 */
public interface IParse {

    //共通レスポンス部分

    /**
     * 曲名、アイドル名、ユニット名など「name」キーに割り当てられている情報を取り出します。
     * @return キー「name」の情報
     */
    String getName();

    /**
     * 出力カテゴリ名「type」に割り当てられている情報を取り出します。
     * @return キー「type」の情報
     */
    String getType();

    /**
     * API内部管理ID「music_id, song_id, tax_id」に割り当てられている情報を取り出します。
     * @return キー「music_id, song_id, tax_id」の情報
     */
    String getInternalId();

    /**
     * ふじわらはじめ楽曲DB内でのページURL「link」に割り当てられている情報を取り出します。
     * @return キー「link」の情報
     */
    String getLink();

    /**
     * 出力する情報の詳細JSONが格納されているJSON URI「api」に割り当てられている情報を取り出します。
     * @return キー「api」の情報
     */
    String getApi();

    // プログラム内共通処理

    /**
     * 取得したJSONの生JsonNodeを返します
     * @return 取得したJSONの生JsonNode
     */
    JsonNode getJsonNode();

    /**
     * 取得したデータの文字エンコードを返します。
     * ふじわらはじめAPIの共通仕様により、常に「UTF-8」となります。
     * @return 取得したデータの文字エンコード
     */
    default String getEncoding() {
        return "UTF-8";
    }

}
