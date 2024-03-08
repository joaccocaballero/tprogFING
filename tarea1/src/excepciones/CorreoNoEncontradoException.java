package excepciones;


public class CorreoNoEncontradoException extends Exception {

	private static final long serialVersionUID = 1L;

	public CorreoNoEncontradoException() {
		super();
	}

	public CorreoNoEncontradoException(String message) {
		super(message);
	}

	public CorreoNoEncontradoException(Throwable cause) {
		super(cause);
	}

	public CorreoNoEncontradoException(String message, Throwable cause) {
		super(message, cause);
	}

	public CorreoNoEncontradoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
