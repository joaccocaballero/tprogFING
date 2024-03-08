package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servidor.publicar.*;
import utils.CookiesUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import excepciones.NicknameNoExisteException;
import excepciones.OfertaNoExisteException;
import excepciones.UsuarioNoEsPostulanteException;

/**
 * Servlet implementation class ConfirmarPostulacion
 */
@WebServlet( urlPatterns = { "/confirmarPostulacion/*", "/confirmarPostulacion"})
public class ConfirmarPostulacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static servidor.publicar.ServicioOfertasService serviceOfertas = new servidor.publicar.ServicioOfertasService();
	private static servidor.publicar.ServicioOfertas portOfertas = serviceOfertas.getServicioOfertasPort();
	private static servidor.publicar.ServicioUsuariosService service = new servidor.publicar.ServicioUsuariosService();
	private static servidor.publicar.ServicioUsuarios portUsuarios = service.getServicioUsuariosPort();
    private static CookiesUtils cookies = CookiesUtils.obtenerInstancia();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmarPostulacion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String NombreOferta = request.getParameter("NombreOferta");
		String jwt = cookies.obtenerJWTEnCookies(request, response);
		if(jwt != null) {

	    	String tipoUsuario = portUsuarios.tipoUsuario(jwt);
			DtUsuario user = portUsuarios.obtenerDatosDeUsuarioJWT(jwt);
			if(user!=null) {
				request.setAttribute("imgPerfil", user.getUrlImagen());
			}
			try {
				DtOferta ofertaSeleccionada = portOfertas.obtenerDatosOferta(NombreOferta);
				request.setAttribute("ofertaSeleccionada", ofertaSeleccionada);
				switch(tipoUsuario) {
				case ("postulante"):
					request.getRequestDispatcher("/WEB-INF/postulante/confirmarPostulacion.jsp").forward(request, response);
					break;
				case ("empresa"):
					request.getRequestDispatcher("/WEB-INF/empresa/dashboardEmpresa.jsp").forward(request, response);
					break;
				default:
					request.getRequestDispatcher("/WEB-INF/visitante/inicio.jsp").forward(request, response);
					break;
		        }
			} catch (OfertaNoExisteException_Exception e) {
				e.printStackTrace();
			}
		}
		else {
			request.getRequestDispatcher("/WEB-INF/visitante/inicio.jsp").forward(request, response);
		}
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String jwt = cookies.obtenerJWTEnCookies(request, response);
		if( jwt != null) {
			String tipoUsuario = portUsuarios.tipoUsuario(jwt);
			try {
				switch(tipoUsuario) {
				case ("postulante"):
					String NombreOferta = request.getParameter("NombreOferta");
			        String nicknamePostulante = portUsuarios.obtenerDatosDeUsuarioJWT(jwt).getNickname();
			        String cvReducido = request.getParameter("cv");
			        String motivacion = request.getParameter("motivacion");
			        if(cvReducido.isEmpty() || motivacion.isEmpty()) {
			        	throw new Exception("Debes ingresar tu CV y una motivación!");
			        }
			        String urlVideo = request.getParameter("urlVideo");
			        LocalDateTime fechaActual = LocalDateTime.now();
			        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			        String fechaActualComoString = fechaActual.format(formatter);
					portOfertas.postularAOferta(NombreOferta, nicknamePostulante, cvReducido, motivacion, fechaActualComoString, urlVideo);
					response.sendRedirect("postulante");
					break;
				case ("empresa"):
					response.sendRedirect("empresa");
					break;
				default:
					response.sendRedirect("visitante");
					break;
		        }
			} catch (NicknameNoExisteException_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (OfertaNoExisteException_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UsuarioNoEsPostulanteException_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("/WEB-INF/errorPages/ofertaExiste.jsp").forward(request, response);
				e.printStackTrace();
			}
			
		}
		else {
			response.sendRedirect("visitante");
		}
		
	}

}
