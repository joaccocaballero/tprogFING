package logica;

public enum EstadoSesion {
    NO_LOGIN,           // nunca intentó iniciar sesión
    LOGIN_POSTULANTE, // tiene la sesión iniciada como postulante
    LOGIN_EMPRESA, // tiene la sesión iniciada como empresa
    LOGIN_INCORRECTO    // le erro a la sesión al menos una vez
}
