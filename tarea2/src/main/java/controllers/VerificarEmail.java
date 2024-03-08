package controllers;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servidor.publicar.CorreoNoEncontradoException_Exception;
import servidor.publicar.DtUsuario;
import servidor.publicar.NicknameNoExisteException_Exception;

@WebServlet("/verificarEmail")
public class VerificarEmail extends HttpServlet {

	private static final long serialVersionUID = 1L;
	servidor.publicar.ServicioUsuariosService serviceUsuarios = new servidor.publicar.ServicioUsuariosService();
	servidor.publicar.ServicioUsuarios portUsuarios = serviceUsuarios.getServicioUsuariosPort();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
                
        String email = request.getParameter("email");
        System.out.println(email);
        boolean disponible = verificarDisponibilidadEmail(email);
        response.getWriter().write(String.valueOf(disponible));

        
    }

    private boolean verificarDisponibilidadEmail(String email) {
    	boolean res = false;
    	try {
			DtUsuario user = portUsuarios.consultarUsuarioPorCorreo(email);
		} catch (CorreoNoEncontradoException_Exception e) {
			// TODO Auto-generated catch block
			System.out.println("hola");
			res = true;
		} catch (NicknameNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res; 
    }
}