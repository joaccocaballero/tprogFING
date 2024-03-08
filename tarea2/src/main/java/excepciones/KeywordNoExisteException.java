package excepciones;

public class KeywordNoExisteException extends Exception {

	private static final long serialVersionUID = 1L;

	public KeywordNoExisteException() {
		super();
	}

	public KeywordNoExisteException(String message) {
		super(message);
	}

	public KeywordNoExisteException(Throwable cause) {
		super(cause);
	}

	public KeywordNoExisteException(String message, Throwable cause) {
		super(message, cause);
	}

	public KeywordNoExisteException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
