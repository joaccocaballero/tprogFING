package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servidor.publicar.DtEmpresa;
import servidor.publicar.DtOferta;
import servidor.publicar.DtPostulacion;
import servidor.publicar.DtPostulacionArray;
import servidor.publicar.DtPostulante;
import servidor.publicar.DtPublicacion;
import servidor.publicar.DtUsuario;
import servidor.publicar.EnumEstadoOferta;
import servidor.publicar.KeywordExisteException_Exception;
import servidor.publicar.NicknameNoExisteException_Exception;
import servidor.publicar.OfertaNoExisteException_Exception;
import servidor.publicar.ServicioOfertas;
import servidor.publicar.ServicioOfertasService;
import servidor.publicar.ServicioUsuarios;
import servidor.publicar.ServicioUsuariosService;
import servidor.publicar.UsuarioNoEsPostulanteException_Exception;

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

@WebServlet("/postular-oferta-mobile")
public class postularAOfertaMobile extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String JWT_COOKIE_NAME = "jwt";
    private ServicioOfertas portOfertas;
    private ServicioUsuarios portUsuarios;

    public postularAOfertaMobile() {
        super();
        ServicioOfertasService serviceOfertas = new ServicioOfertasService();
        portOfertas = serviceOfertas.getServicioOfertasPort();
        ServicioUsuariosService serviceUsuarios = new ServicioUsuariosService();
        portUsuarios = serviceUsuarios.getServicioUsuariosPort();
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
        String jwt = getJwtFromCookies(req.getCookies());
        if (jwt != null && portUsuarios.validarToken(jwt)) {
            String tipoUsuario = portUsuarios.tipoUsuario(jwt);
            DtUsuario usuario = portUsuarios.obtenerDatosDeUsuarioJWT(jwt);
  
            req.setAttribute("postulante", usuario.getNickname());
            req.setAttribute("imgPerfil", usuario.getUrlImagen());
            req.setAttribute("nombre", usuario.getNombre());
            req.setAttribute("posibleGenero", predecirGenero(usuario.getNombre()));
            if ("postulante".equals(tipoUsuario)) {
            	String nombreOferta = req.getParameter("NombreOferta");
                if (!nombreOferta.isEmpty()) {
                  try {    
                    DtPublicacion publicacion = portOfertas.obtenerPublicacionAsociadaAOferta(nombreOferta);
                    DtOferta oferta = portOfertas.obtenerDatosOferta(nombreOferta);
                    req.setAttribute("publicacion", publicacion);
                    req.setAttribute("fecha", oferta.getFechaAlta());
                    if (jwt != null) {
                      DtUsuario user = portUsuarios.obtenerDatosDeUsuarioJWT(jwt);
                      if (user instanceof DtPostulante) {
                    	boolean  estaPostulado = portOfertas.estaPostuladoAOfertaBoolean(user.getNickname(), oferta.getNombre());  
                        if (estaPostulado) {
                        	resp.sendRedirect("visitante");
                        }
                      }
                    }
                  } catch (OfertaNoExisteException_Exception | NicknameNoExisteException_Exception | UsuarioNoEsPostulanteException_Exception e) {
                	  resp.sendRedirect("visitante");
                	  e.printStackTrace();
                  }
                  
                  
                }else {
                	resp.sendRedirect("visitante");
                }
            	
                if (isMobile) {
                    dispatchToPage(req, resp, "/WEB-INF/mobile/postularmeAOfertaMobile.jsp");
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
	

	
