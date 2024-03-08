package controllers;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servidor.publicar.DtUsuario;
import servidor.publicar.NicknameNoExisteException_Exception;

@WebServlet("/verificarNickname")
public class VerificarNickname extends HttpServlet {

	private static final long serialVersionUID = 1L;
	servidor.publicar.ServicioUsuariosService serviceUsuarios = new servidor.publicar.ServicioUsuariosService();
	servidor.publicar.ServicioUsuarios portUsuarios = serviceUsuarios.getServicioUsuariosPort();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
                
        String nickname = request.getParameter("nickname");
        boolean disponible = verificarDisponibilidadNickname(nickname);
        response.getWriter().write(String.valueOf(disponible));

        
    }

    private boolean verificarDisponibilidadNickname(String nickname) {
    	boolean res = false;
    	try {
			DtUsuario user = portUsuarios.consultarUsuario(nickname);
		} catch (NicknameNoExisteException_Exception e) {
			res = true;
		}
		return res; 
    }
}