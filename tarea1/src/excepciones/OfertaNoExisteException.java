package excepciones;

public class OfertaNoExisteException extends Exception {

	private static final long serialVersionUID = 1L;

	public OfertaNoExisteException() {
		super();
	}

	public OfertaNoExisteException(String message) {
		super(message);
	}

	public OfertaNoExisteException(Throwable cause) {
		super(cause);
	}

	public OfertaNoExisteException(String message, Throwable cause) {
		super(message, cause);
	}

	public OfertaNoExisteException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
