package excepciones;

public class CorreoRepetidoException extends Exception {

	private static final long serialVersionUID = 1L;

	public CorreoRepetidoException() {
		super();
	}

	public CorreoRepetidoException(String message) {
		super(message);
	}

	public CorreoRepetidoException(Throwable cause) {
		super(cause);
	}

	public CorreoRepetidoException(String message, Throwable cause) {
		super(message, cause);
	}

	public CorreoRepetidoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
