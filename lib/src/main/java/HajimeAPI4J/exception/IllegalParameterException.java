package HajimeAPI4J.exception;

/**
 * リクエストを送るためのURIへ付与するパラメータに不正な値が渡された場合にスローされます。
 * ふじわらはじめAPIには必須パラメータと任意パラメータが存在しますが、この例外は必須任意問わずにスローされる可能性があります。
 * 
 * @author Ranfa
 * @since 1.0.0
 */
public class IllegalParameterException extends IllegalArgumentException {


	/**
	 * 特別メッセージなしで新規の<code>IllegalParameterException</code>を作成します。
	 */
	public IllegalParameterException() {
		super();
	}

	/**
	 * 指定されたメッセージを使用して新規の<code>IllegalParameterException</code>を作成します。
	 * @param s 指定するメッセージ文字列
	 */
	public IllegalParameterException(String s) {
		super(s);
	}

	/**
	 * 指定されたメッセージと原因を使用して新規の<code>IllegalParameterException</code>を作成します。
	 * @param message 指定するメッセージ文字列
	 * @param cause 原因（原因がない、もしくは不明の場合は<code>null</code>）
	 */
	public IllegalParameterException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 指定された原因を使用して新規の<code>IllegalParameterException</code>を作成します。
	 * その場合のメッセージは<code>cause==null ? null : cause.toString()</code>となります。
	 * @param cause 原因（原因がない、もしくは不明の場合は<code>null</code>）
	 */
	public IllegalParameterException(Throwable cause) {
		super(cause);
	}

}
