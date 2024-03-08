package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servidor.publicar.*;
import utils.CookiesUtils;

@WebServlet(urlPatterns = { "/mostrarPostulacion/*", "/mostrarPostulacion"})
public class MostrarPostulaciones extends HttpServlet{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static servidor.publicar.ServicioOfertasService serviceOfertas = new servidor.publicar.ServicioOfertasService();
	private static servidor.publicar.ServicioOfertas portOfertas = serviceOfertas.getServicioOfertasPort();
	private static servidor.publicar.ServicioUsuariosService service = new servidor.publicar.ServicioUsuariosService();
	private static servidor.publicar.ServicioUsuarios portUsuarios = service.getServicioUsuariosPort();
    private static CookiesUtils cookies = CookiesUtils.obtenerInstancia();

	/**
     * @see HttpServlet#HttpServlet()
     */
    public MostrarPostulaciones() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, OfertaNoExisteException_Exception {
    	String jwt = cookies.obtenerJWTEnCookies(request, response);
    	if(jwt!=null) {
    		String nombre = request.getParameter("nombre");
        	String tipoUsuario = portUsuarios.tipoUsuario(jwt);
    		DtOferta oferta = portOfertas.obtenerDatosOferta(nombre);
            request.setAttribute("oferta", oferta);
            DtUsuario user = portUsuarios.obtenerDatosDeUsuarioJWT(jwt);
            if(user!= null) {
            	 request.setAttribute("imgPerfil", user.getUrlImagen());
            }
            switch(tipoUsuario) {
    		case "postulante":
    			request.getRequestDispatcher("/WEB-INF/postulante/dashboardPostulante.jsp").forward(request, response);
    			break;
    		case "empresa":
    			request.getRequestDispatcher("/WEB-INF/empresa/informacionPostulacion.jsp").forward(request, response);
    			break;
    		default:
    			request.getRequestDispatcher("/WEB-INF/visitante/inicio.jsp").forward(request, response);
    			break;
            }
    	}
    	else {
    		request.getRequestDispatcher("/WEB-INF/visitante/inicio.jsp").forward(request, response);
    	}
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			processRequest(request,response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OfertaNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
