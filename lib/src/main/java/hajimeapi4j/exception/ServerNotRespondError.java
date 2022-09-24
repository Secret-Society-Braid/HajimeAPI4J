package hajimeapi4j.exception;

/**
 * ふじわらはじめAPIのAPIサーバー側からエラーステータスが返された場合にスロー及び発生します。
 * 特に、ふじわらはじめAPIでは一度に短期間のリクエスト送信やサーバーの混雑具合によって503エラーを返す場合があるため、必ずしもサーバーの不具合ではないことに留意してください。
 * 
 * @author Ranfa
 * @since 1.0.0
 */
public class ServerNotRespondError extends Error {
    
    /**
     * 詳細メッセージなしで新規の <code>ServerNotRespondError</code> を作成します。
     */
    public ServerNotRespondError() {
        super();
    }

    /**
     * 指定された詳細メッセージで新規の<code>ServerNotRespondError</code>を作成します。
     * @param message 指定する詳細メッセージ
     */
    public ServerNotRespondError(String message) {
        super(message);
    }

    /**
     * 指定された詳細メッセージと原因で新規の<code>ServerNotRespondError</code>を作成します。
     * @param message 指定する詳細メッセージ
     * @param cause 指定する原因（原因がない、もしくは不明の場合は<code>null</code>）
     */
    public ServerNotRespondError(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 指定された原因で新規の<code>ServerNotRespondError</code>を作成します。
     * このコンストラクタの場合、詳細メッセージは
     * <code>cause==null ? null : cause.toString();</code>となります。
     * @param cause 指定する原因（原因がない、もしくは不明の場合は<code>null</code>）
     */
    public ServerNotRespondError(Throwable cause) {
        super(cause);
    }

    /**
     * 指定された詳細メッセージと原因、suppressionの有無、スタックトレースの書き込み可否で新規の<code>ServerNotRespondError</code>を作成します。
     * @param message 指定する詳細メッセージ
     * @param cause 指定する原因（原因がない、もしくは不明の場合は<code>null</code>）
     * @param enableSuppression suppressionを有効にする場合は<code>TRUE</code>、無効にする場合は<code>FALSE</code>
     * @param writableStackTrace スタックトレースを書き込み可能にする場合は<code>TRUE</code>、無効にする場合は<code>FALSE</code>
     */
    public ServerNotRespondError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
