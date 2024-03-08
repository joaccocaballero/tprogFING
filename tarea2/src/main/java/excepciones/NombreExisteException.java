package excepciones;

public class NombreExisteException extends Exception {
	private static final long serialVersionUID = 1L;

	public NombreExisteException() {
		super();
	}

	public NombreExisteException(String message) {
		super(message);
	}

	public NombreExisteException(Throwable cause) {
		super(cause);
	}

	public NombreExisteException(String message, Throwable cause) {
		super(message, cause);
	}

	public NombreExisteException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
