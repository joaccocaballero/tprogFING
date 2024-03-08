package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servidor.publicar.DtOferta;
import servidor.publicar.DtPostulacion;
import servidor.publicar.DtUsuario;
import servidor.publicar.OfertaNoExisteException_Exception;
import utils.CookiesUtils;

@WebServlet("/seleccionarPostulaciones")
public class SeleccionarPostulaciones extends HttpServlet {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static servidor.publicar.ServicioOfertasService serviceOfertas = new servidor.publicar.ServicioOfertasService();
	private static servidor.publicar.ServicioOfertas portOfertas = serviceOfertas.getServicioOfertasPort();
	private static servidor.publicar.ServicioUsuariosService service = new servidor.publicar.ServicioUsuariosService();
	private static servidor.publicar.ServicioUsuarios portUsuarios = service.getServicioUsuariosPort();
    private static CookiesUtils cookies = CookiesUtils.obtenerInstancia();
    DtOferta oferta;
	/**
     * @see HttpServlet#HttpServlet()
     */
    public SeleccionarPostulaciones() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, OfertaNoExisteException_Exception {
    	String jwt = cookies.obtenerJWTEnCookies(request, response);
    	if(jwt!=null) {
    		String nombre = request.getParameter("NombreOferta");
        	String tipoUsuario = portUsuarios.tipoUsuario(jwt);
    		DtOferta oferta = portOfertas.obtenerDatosOferta(nombre);
    		this.oferta = oferta;
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
    			request.getRequestDispatcher("/WEB-INF/empresa/seleccionarPostulaciones.jsp").forward(request, response);
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

		String[] posiciones = request.getParameterValues("posiciones[]");
	    String posts = obtenerStringOrdenado(posiciones, this.oferta.getPostulaciones());
	    System.out.println(posts);
	    
	    try {
			portOfertas.seleccionarPostulaciones(this.oferta.getNombre(), posts);
			portOfertas.finalizarOferta(this.oferta.getNombre());
			response.sendRedirect("empresa");
			
		} catch (OfertaNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String obtenerStringOrdenado(String[] posiciones, List<DtPostulacion> posts) {

	    String postsOrdenados = "";
	    for (String posicion : posiciones) {
	        int index = Integer.parseInt(posicion)-1;
	        if (index >= 0 && index < posts.size()) {
	            postsOrdenados += posts.get(index).getNicknamePostulante() + ",";
	        }
	    }
	    if (postsOrdenados.endsWith(",")) {
	        postsOrdenados = postsOrdenados.substring(0, postsOrdenados.length() - 1);
	    }
	    return postsOrdenados;
	}
}
