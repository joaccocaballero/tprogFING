package excepciones;


public class UsuarioRepetidoException extends Exception {

	private static final long serialVersionUID = 1L;

	public UsuarioRepetidoException() {
		super();
	}

	public UsuarioRepetidoException(String message) {
		super(message);
	}

	public UsuarioRepetidoException(Throwable cause) {
		super(cause);
	}

	public UsuarioRepetidoException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsuarioRepetidoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
