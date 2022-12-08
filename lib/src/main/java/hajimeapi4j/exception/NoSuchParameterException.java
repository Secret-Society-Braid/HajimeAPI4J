package hajimeapi4j.exception;

import java.util.NoSuchElementException;

/**
 * リクエストを送るためのURIへ付与するパラメータの名前が解決できなかった場合にスローされます。
 * この例外は必須パラメータに対してのみスローされます。
 * 
 * @author Ranfa
 * @since 1.0.0
 */
public class NoSuchParameterException extends NoSuchElementException {
    
    /**
     * 詳細メッセージなしで新規の<code>NoSuchParameterException</code>を作成します。
     */
    public NoSuchParameterException() {
        super();
    }

    /**
     * 詳細メッセージを指定して新規の<code>NoSuchParameterException</code>を作成します。
     * @param e 指定する詳細メッセージ
     */
    public NoSuchParameterException(String e) {
        super(e);
    }

}
