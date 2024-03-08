package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servidor.publicar.DtEmpresa;
import servidor.publicar.DtPublicacion;
import servidor.publicar.DtUsuario;
import servidor.publicar.EnumEstadoOferta;
import servidor.publicar.KeywordExisteException_Exception;
import servidor.publicar.ServicioOfertas;
import servidor.publicar.ServicioOfertasService;
import servidor.publicar.ServicioUsuarios;
import servidor.publicar.ServicioUsuariosService;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@WebServlet("/ver-ofertas")
public class verOfertas extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String JWT_COOKIE_NAME = "jwt";
    private ServicioOfertas portOfertas;
    private ServicioUsuarios portUsuarios;

    public verOfertas() {
        super();
        ServicioOfertasService serviceOfertas = new ServicioOfertasService();
        portOfertas = serviceOfertas.getServicioOfertasPort();
        ServicioUsuariosService serviceUsuarios = new ServicioUsuariosService();
        portUsuarios = serviceUsuarios.getServicioUsuariosPort();
    }

    private List<DtPublicacion> filtrarPublicacionesConfirmadas(List<DtPublicacion> publicaciones) {
        return publicaciones.stream()
                .filter(p -> EnumEstadoOferta.CONFIRMADA.equals(p.getDtOferta().getEstado()))
                .collect(Collectors.toList());
    }
    
    private List<DtPublicacion> filtrarPublicacionesPorEmpresa(List<DtPublicacion> publicaciones, String nombreEmpresa) {
        return publicaciones.stream()
                .filter(p -> EnumEstadoOferta.CONFIRMADA.equals(p.getDtOferta().getEstado()))
                .filter(p -> nombreEmpresa.equals(p.getDtOferta().getNicknameEmpresa()))
                .collect(Collectors.toList());
    }

    private boolean isMobileRequest(HttpServletRequest req) {
        String userAgent = req.getHeader("User-Agent");
        if(userAgent == null) {
        	return false;
        }
        userAgent.toLowerCase();
        Pattern mobilePattern = Pattern.compile("mobi|android|iphone|webos|blackberry", Pattern.CASE_INSENSITIVE);
        return mobilePattern.matcher(userAgent).find();
    }

    private String getJwtFromCookies(Cookie[] cookies) {
        if (cookies == null) return null;
        return Arrays.stream(cookies)
                .filter(c -> JWT_COOKIE_NAME.equals(c.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElse(null);
    }

    private void dispatchToPage(HttpServletRequest req, HttpServletResponse resp, String page) throws ServletException, IOException {
        req.getRequestDispatcher(page).forward(req, resp);
    }
    
    public static String predecirGenero(String nombre) {

        nombre = nombre.toLowerCase();
        String[] terminacionesFemeninas = {"a", "ia", "ra", "na", "da", "la"};
        for (String terminacion : terminacionesFemeninas) {
            if (nombre.endsWith(terminacion)) {
                return "Femenino";
            }
        }
        return "Masculino";
    }
    
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, KeywordExisteException_Exception {
        boolean isMobile = isMobileRequest(req);
        
        List<DtEmpresa> empresas = portUsuarios.listarEmpresas().getItem();
        req.setAttribute("empresas", empresas);
        
        String nombreEmpresa = req.getParameter("nombreEmpresa");
        if (nombreEmpresa != null) {
        	List<DtPublicacion> publicaciones =  portOfertas.obtenerPublicaciones().getItem();
            req.setAttribute("publicaciones", filtrarPublicacionesPorEmpresa(publicaciones,nombreEmpresa));
        }else {
        	List<DtPublicacion> publicaciones = portOfertas.obtenerPublicaciones().getItem();
            req.setAttribute("publicaciones", filtrarPublicacionesConfirmadas(publicaciones));
        }

        String jwt = getJwtFromCookies(req.getCookies());
        if (jwt != null && portUsuarios.validarToken(jwt)) {
            String tipoUsuario = portUsuarios.tipoUsuario(jwt);
            DtUsuario usuario = portUsuarios.obtenerDatosDeUsuarioJWT(jwt);
            
            req.setAttribute("postulante", usuario.getNickname());
            req.setAttribute("imgPerfil", usuario.getUrlImagen());
            req.setAttribute("nombre", usuario.getNombre());
            req.setAttribute("posibleGenero", predecirGenero(usuario.getNombre()));
            if ("postulante".equals(tipoUsuario)) {
                if (isMobile) {
                    dispatchToPage(req, resp, "/WEB-INF/mobile/verOfertas.jsp");
                } else {
                	resp.sendRedirect("visitante");
                }
                
            } else {
                resp.sendRedirect("visitante");
            }
        } else {
        	resp.sendRedirect("visitante");
        }
    }
    
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	try {
			processRequest(request, response);
		} catch (ServletException | IOException | KeywordExisteException_Exception e) {
			e.printStackTrace();
		}
    }
    
  
}
	

	
