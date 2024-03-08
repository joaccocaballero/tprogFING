package excepciones;

public class KeywordExisteException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public KeywordExisteException() {
		super();
	}

	public KeywordExisteException(String message) {
		super(message);
	}

	public KeywordExisteException(Throwable cause) {
		super(cause);
	}

	public KeywordExisteException(String message, Throwable cause) {
		super(message, cause);
	}

	public KeywordExisteException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
