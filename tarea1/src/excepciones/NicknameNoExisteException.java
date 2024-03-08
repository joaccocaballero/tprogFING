package excepciones;


public class NicknameNoExisteException extends Exception {

	private static final long serialVersionUID = 1L;

	public NicknameNoExisteException() {
		super();
	}

	public NicknameNoExisteException(String message) {
		super(message);
	}

	public NicknameNoExisteException(Throwable cause) {
		super(cause);
	}

	public NicknameNoExisteException(String message, Throwable cause) {
		super(message, cause);
	}

	public NicknameNoExisteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
