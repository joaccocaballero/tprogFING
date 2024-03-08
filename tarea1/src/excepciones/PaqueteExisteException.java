package excepciones;

public class PaqueteExisteException extends Exception {
	private static final long serialVersionUID = 1L;

	public PaqueteExisteException() {
		super();
	}

	public PaqueteExisteException(String message) {
		super(message);
	}

	public PaqueteExisteException(Throwable cause) {
		super(cause);
	}

	public PaqueteExisteException(String message, Throwable cause) {
		super(message, cause);
	}

	public PaqueteExisteException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
