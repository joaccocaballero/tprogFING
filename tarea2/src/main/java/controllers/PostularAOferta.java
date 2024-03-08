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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Servlet implementation class PostularAOferta
 */
@WebServlet("/postularAOferta")
public class PostularAOferta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static servidor.publicar.ServicioOfertasService serviceOfertas = new servidor.publicar.ServicioOfertasService();
	private static servidor.publicar.ServicioOfertas portOfertas = serviceOfertas.getServicioOfertasPort();
	private static servidor.publicar.ServicioUsuariosService service = new servidor.publicar.ServicioUsuariosService();
	private static servidor.publicar.ServicioUsuarios portUsuarios = service.getServicioUsuariosPort();
    private static CookiesUtils cookies = CookiesUtils.obtenerInstancia();
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public PostularAOferta() {
        super();
        // TODO Auto-generated constructor stub
    }
    
   
    
    public static List<DtUsuario> obtenerEmpresasConPublicacionesConfirmadas() {
        List<DtUsuario> empresasConPublicacionesConfirmadas = new ArrayList<>();
        List<DtEmpresa> todasLasEmpresas = portUsuarios.listarEmpresas().getItem();
        
        for (DtUsuario empresa : todasLasEmpresas) {
            List<DtPublicacion> publicacionesDeEmpresa = portOfertas.obtenerPublicacionesDeEmpresa(empresa.getNickname()).getItem();
            for (DtPublicacion publicacion : publicacionesDeEmpresa) {
                if (EnumEstadoOferta.CONFIRMADA.equals(publicacion.getDtOferta().getEstado())) {
                    empresasConPublicacionesConfirmadas.add(empresa);
                    break; 
                }
            }
        }
        
        return empresasConPublicacionesConfirmadas;
    }
    
    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String jwt = cookies.obtenerJWTEnCookies(req, resp);
    	if(jwt != null) {
    		String tipoUsuario = portUsuarios.tipoUsuario(jwt);
    		List<DtUsuario> empresas = new ArrayList<>();
    		empresas = obtenerEmpresasConPublicacionesConfirmadas();
    		req.setAttribute("empresas", empresas);
    		DtUsuario user = portUsuarios.obtenerDatosDeUsuarioJWT(jwt);
    		if(user!=null) {
    			req.setAttribute("imgPerfil", user.getUrlImagen());
    		}
        	switch(tipoUsuario) {
        		case ("postulante"):
        			req.getRequestDispatcher("/WEB-INF/postulante/PostularAOferta.jsp").forward(req, resp);
        			break;
        		case ("empresa"):
        			req.getRequestDispatcher("/WEB-INF/empresa/dashboardEmpresa.jsp").forward(req, resp);
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

