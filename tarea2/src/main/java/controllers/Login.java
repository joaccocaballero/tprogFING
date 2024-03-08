package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import servidor.publicar.CorreoNoEncontradoException_Exception;
import servidor.publicar.DtEmpresa;
import servidor.publicar.DtPostulante;
import servidor.publicar.DtUsuario;
import servidor.publicar.NicknameNoExisteException_Exception;
import java.io.IOException;
import excepciones.CorreoNoEncontradoException;
import excepciones.CorreoRepetidoException;
import excepciones.NicknameNoExisteException;
import excepciones.UsuarioNoEncontrado;
import excepciones.UsuarioRepetidoException;

/**
 * Servlet implementation class Login
 */
@WebServlet("/iniciar-sesion")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NicknameNoExisteException, UsuarioNoEncontrado,
			UsuarioRepetidoException, CorreoNoEncontradoException_Exception, NicknameNoExisteException_Exception {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		servidor.publicar.ServicioUsuariosService serviceUsuarios = new servidor.publicar.ServicioUsuariosService();
		servidor.publicar.ServicioUsuarios portUsuarios = serviceUsuarios.getServicioUsuariosPort();
		if (portUsuarios.usuarioExiste(login)) {
			if (portUsuarios.validarUsuario(login, password)) {
				DtUsuario user = portUsuarios.consultarUsuarioPorCorreo(login);
				if (user instanceof DtEmpresa) {
					String jwt = portUsuarios.generateJWT(login, "empresa");
					response.addCookie(new Cookie("jwt", jwt));
				}  
				if (user instanceof DtPostulante) {
					String jwt = portUsuarios.generateJWT(login, "postulante");
					response.addCookie(new Cookie("jwt", jwt));
				}
			} else {
				session.setAttribute("errorMessage", "Contraseña incorrecta");
			}
		} else {
			session.setAttribute("errorMessage", "Usuario inexistente");
		}
		response.sendRedirect("visitante");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			processRequest( request,  response);
		} catch (ServletException | IOException | NicknameNoExisteException | UsuarioNoEncontrado
				| UsuarioRepetidoException | CorreoNoEncontradoException_Exception
				| NicknameNoExisteException_Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
