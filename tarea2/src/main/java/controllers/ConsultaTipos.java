package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servidor.publicar.*;
import utils.CookiesUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class ConsultaTipos
 */
@WebServlet("/consultaTipos")
public class ConsultaTipos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConsultaTipos() {
		super();
		// TODO Auto-generated constructor stub
	}

	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		servidor.publicar.ServicioOfertasService serviceOfertas = new servidor.publicar.ServicioOfertasService();
        servidor.publicar.ServicioOfertas portOfertas = serviceOfertas.getServicioOfertasPort();
        servidor.publicar.ServicioUsuariosService serviceUsuarios = new servidor.publicar.ServicioUsuariosService();
		servidor.publicar.ServicioUsuarios portUsuarios = serviceUsuarios.getServicioUsuariosPort();
		
		CookiesUtils cookies = CookiesUtils.obtenerInstancia();
        String jwt = cookies.obtenerJWTEnCookies(req, resp); 
        String tipoUsuario = "visitante";
       
		DtTipoPublicacionArray tiposPublicacion = portOfertas.obtenerTipos();
		req.setAttribute("tiposPublicacion", tiposPublicacion.getItem());
		if(jwt != null) {
			tipoUsuario = portUsuarios.tipoUsuario(jwt);
			DtUsuario user = portUsuarios.obtenerDatosDeUsuarioJWT(jwt);
			req.setAttribute("imgPerfil", user.getUrlImagen());
		}
		
		switch (tipoUsuario) {
		case "postulante":
			req.getRequestDispatcher("/WEB-INF/postulante/consultaTipoPublicacion.jsp").forward(req, resp);
			break;
		case "empresa":
			req.getRequestDispatcher("/WEB-INF/empresa/consultaTipoPublicacion.jsp").forward(req, resp);
			break;
		default:
			req.getRequestDispatcher("/WEB-INF/visitante/consultaTipos.jsp").forward(req, resp);
			break;

		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
