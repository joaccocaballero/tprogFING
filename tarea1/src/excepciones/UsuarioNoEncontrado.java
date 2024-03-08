package excepciones;

public class UsuarioNoEncontrado extends Exception {

	private static final long serialVersionUID = 1L;

	public UsuarioNoEncontrado() {
		super();
	}

	public UsuarioNoEncontrado(String message) {
		super(message);
	}

	public UsuarioNoEncontrado(Throwable cause) {
		super(cause);
	}

	public UsuarioNoEncontrado(String message, Throwable cause) {
		super(message, cause);
	}

	public UsuarioNoEncontrado(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
