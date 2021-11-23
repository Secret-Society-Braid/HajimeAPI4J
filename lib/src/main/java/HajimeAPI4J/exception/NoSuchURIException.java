package HajimeAPI4J.exception;

/**
 * ふじわらはじめAPIへリクエストを送るURIが見つからなかったか、正常にURIが作成されないままリクエストを送ろうとした場合にスローされます。
 * リクエストする項目とパラメータが合っていればめったにスローされないはずです。
 * 
 * @author Ranfa
 * @since 1.0.0
 */
public class NoSuchURIException extends NoSuchFieldException {
    
    /**
     * 詳細メッセージなしで新規の<code>NoSuchURIException</code>を作成します。
     */
    public NoSuchURIException() {
        super();
    }

    /**
     * 指定された詳細メッセージで新規の<code>NoSuchURIException</code>を作成します。
     * @param e 指定するメッセージ
     */
    public NoSuchURIException(String e) {
        super(e);
    }
}
