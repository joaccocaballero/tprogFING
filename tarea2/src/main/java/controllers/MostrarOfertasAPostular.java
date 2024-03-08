package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servidor.publicar.*;
import utils.CookiesUtils;
import utils.LocalDateSerializer;
import utils.LocalDateTimeAdapter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class MostrarOfertasAPostular
 */
@WebServlet( urlPatterns = { "/mostrarOfertasAPostular/*", "/mostrarOfertasAPostular"})
public class MostrarOfertasAPostular extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static servidor.publicar.ServicioOfertasService serviceOfertas = new servidor.publicar.ServicioOfertasService();
	private static servidor.publicar.ServicioOfertas portOfertas = serviceOfertas.getServicioOfertasPort();
	private static servidor.publicar.ServicioUsuariosService service = new servidor.publicar.ServicioUsuariosService();
	private static servidor.publicar.ServicioUsuarios portUsuarios = service.getServicioUsuariosPort();
    private static CookiesUtils cookies = CookiesUtils.obtenerInstancia();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostrarOfertasAPostular() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	public List<DtOferta> filtrarOfertasConfirmadas(List<DtOferta> ofertas) {
    	return ofertas.stream()
        .filter(oferta -> EnumEstadoOferta.CONFIRMADA.equals(oferta.getEstado()))
        .collect(Collectors.toList());
    }
    
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NicknameNoExisteException_Exception, UsuarioNoEsEmpresaException_Exception {
        String jwt = cookies.obtenerJWTEnCookies(request, response);
    	if(jwt!=null) {
    		String nicknameEmpresa = request.getParameter("Empresa");
    		String tipoUsuario = portUsuarios.tipoUsuario(jwt);
			List<DtOferta> ofertas = portOfertas.obtenerOfertasVigentesDeEmpresa(nicknameEmpresa).getItem();
			List<DtOferta> ofertasConfirmadas = filtrarOfertasConfirmadas(ofertas);
			Gson gsonAux = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
    				.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();
    		String ofertasJson = gsonAux.toJson(ofertasConfirmadas);
    		request.setAttribute("ofertasVigentes", ofertasJson);
    		
    		DtUsuario user = portUsuarios.obtenerDatosDeUsuarioJWT(jwt);
    		if(user!=null) {
    			request.setAttribute("imgPerfil", user.getUrlImagen());
    		}
    		
    		switch(tipoUsuario) {
    		case ("postulante"):
    			request.getRequestDispatcher("/WEB-INF/postulante/mostrarOfertasAPostular.jsp").forward(request, response);
    			break;
    		case ("empresa"):
    			request.getRequestDispatcher("/WEB-INF/empresa/dashboardEmpresa.jsp").forward(request, response);
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			processRequest(request, response);
		} catch (ServletException | IOException | NicknameNoExisteException_Exception | UsuarioNoEsEmpresaException_Exception  e) {
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


