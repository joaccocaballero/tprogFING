package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class Logout
 */

@WebServlet("/cerrar-sesion")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void CerrarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	Cookie[] cookies = request.getCookies();
        String jwtCookieName = "jwt";
        String jwt = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (jwtCookieName.equals(cookie.getName())) {
                    jwt = cookie.getValue();
                    break;
                }
            }
        }
        
        if (jwt != null) {
	    try {
	    	servidor.publicar.ServicioUsuariosService service = new servidor.publicar.ServicioUsuariosService();
	        servidor.publicar.ServicioUsuarios port = service.getServicioUsuariosPort();
	        port.cerrarSesion(jwt);
            Cookie cookie = new Cookie("jwt", "");
	        cookie.setMaxAge(0);
	        response.addCookie(cookie);
	        response.sendRedirect("visitante");
        
	    } catch (Exception e) {
	    	 Cookie cookie = new Cookie("jwt", "");
	         cookie.setMaxAge(0);
	         response.addCookie(cookie);
	         response.sendRedirect("visitante");
        }
    	
    	 
        }else {
        	response.sendRedirect("visitante");
        }
	    
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CerrarSesion(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
