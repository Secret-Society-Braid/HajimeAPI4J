package HajimeAPI4J.exception;

public class IllegalParameterException extends IllegalArgumentException {


	public IllegalParameterException() {
		super();
	}

	public IllegalParameterException(String s) {
		super(s);
	}

	public IllegalParameterException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalParameterException(Throwable cause) {
		super(cause);
	}

}
