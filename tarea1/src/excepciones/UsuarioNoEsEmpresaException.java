package excepciones;

public class UsuarioNoEsEmpresaException extends Exception {

	private static final long serialVersionUID = 1L;

	public UsuarioNoEsEmpresaException() {
		super();
	}

	public UsuarioNoEsEmpresaException(String message) {
		super(message);
	}

	public UsuarioNoEsEmpresaException(Throwable cause) {
		super(cause);
	}

	public UsuarioNoEsEmpresaException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsuarioNoEsEmpresaException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
