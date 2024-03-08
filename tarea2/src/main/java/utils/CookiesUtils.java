package utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CookiesUtils {
	private static CookiesUtils instancia;
	private CookiesUtils() {}
	public static CookiesUtils obtenerInstancia() {
        if (instancia == null) {
            instancia = new CookiesUtils();
        }
        return instancia;
	}
	
	public String obtenerJWTEnCookies(HttpServletRequest req, HttpServletResponse resp) {
		Cookie[] cookies = req.getCookies();
        String jwtCookieName = "jwt";
        String jwt = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (jwtCookieName.equals(cookie.getName())) {
                    jwt = cookie.getValue();
                    break;
                }
            }
            return jwt;
        }
        return jwt;
	}
}
