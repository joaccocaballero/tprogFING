package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servidor.publicar.DtOfertaArray;
import servidor.publicar.DtPostulante;
import servidor.publicar.DtUsuario;
import servidor.publicar.NicknameNoExisteException_Exception;
import servidor.publicar.UsuarioNoEsEmpresaException_Exception;
import utils.CookiesUtils;
import java.io.IOException;
/**
 * Servlet implementation class ConsultaPostulacionAOferta
 */
@WebServlet("/consultaPostulacionAOferta")
public class ConsultaPostulacionAOferta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConsultaPostulacionAOferta() {
		super();
		// TODO Auto-generated constructor stub
	}

	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NicknameNoExisteException_Exception, UsuarioNoEsEmpresaException_Exception {
		servidor.publicar.ServicioUsuariosService service = new servidor.publicar.ServicioUsuariosService();
		servidor.publicar.ServicioUsuarios portUsuarios = service.getServicioUsuariosPort();
		servidor.publicar.ServicioOfertasService serviceOfertas = new servidor.publicar.ServicioOfertasService();
	    servidor.publicar.ServicioOfertas portOfertas = serviceOfertas.getServicioOfertasPort();
	    CookiesUtils cookies = CookiesUtils.obtenerInstancia();
	    String jwt = cookies.obtenerJWTEnCookies(req, resp);
	    String tipoUsuario = "visitante";
		
		if(jwt!=null) {
			tipoUsuario = portUsuarios.tipoUsuario(jwt);
			switch (tipoUsuario) {
			case "postulante":
				DtPostulante usuario = (DtPostulante) portUsuarios.obtenerDatosDeUsuarioJWT(jwt);
				req.setAttribute("usuario", usuario);
				req.setAttribute("imgPerfil", usuario.getUrlImagen());
				req.getRequestDispatcher("/WEB-INF/postulante/consultaPostulacionAOferta.jsp").forward(req, resp);
				break;
			case "empresa":
				DtUsuario usr = portUsuarios.obtenerDatosDeUsuarioJWT(jwt);
		    	DtOfertaArray ofertas = portOfertas.obtenerOfertasVigentesDeEmpresa(usr.getNickname());
		    	req.setAttribute("imgPerfil", usr.getUrlImagen());
		    	req.setAttribute("ofertas", ofertas.getItem());
		    	
				req.getRequestDispatcher("/WEB-INF/empresa/consultaPostulacionOferta.jsp").forward(req, resp);
				break;
			default:
				req.getRequestDispatcher("/WEB-INF/visitante/inicio.jsp").forward(req, resp);
				break;
			}
		}
		else {
			req.getRequestDispatcher("/WEB-INF/visitante/inicio.jsp").forward(req, resp);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			processRequest(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NicknameNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsuarioNoEsEmpresaException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
