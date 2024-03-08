package excepciones;

public class TipoPublicExisteException extends Exception {

	private static final long serialVersionUID = 1L;

	public TipoPublicExisteException() {
		super();
	}

	public TipoPublicExisteException(String message) {
		super(message);
	}

	public TipoPublicExisteException(Throwable cause) {
		super(cause);
	}

	public TipoPublicExisteException(String message, Throwable cause) {
		super(message, cause);
	}

	public TipoPublicExisteException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}


