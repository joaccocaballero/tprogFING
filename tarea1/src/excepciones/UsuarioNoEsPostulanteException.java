package excepciones;


public class UsuarioNoEsPostulanteException extends Exception {

	private static final long serialVersionUID = 1L;

	public UsuarioNoEsPostulanteException() {
		super();
	}

	public UsuarioNoEsPostulanteException(String message) {
		super(message);
	}

	public UsuarioNoEsPostulanteException(Throwable cause) {
		super(cause);
	}

	public UsuarioNoEsPostulanteException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsuarioNoEsPostulanteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
