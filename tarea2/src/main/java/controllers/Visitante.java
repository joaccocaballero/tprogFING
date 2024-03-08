package controllers;

import servidor.publicar.DtPublicacion;
import servidor.publicar.EnumEstadoOferta;
import servidor.publicar.KeywordExisteException_Exception;
import servidor.publicar.ServicioOfertas;
import servidor.publicar.ServicioOfertasService;
import servidor.publicar.ServicioUsuarios;
import servidor.publicar.ServicioUsuariosService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@WebServlet("/visitante")
public class Visitante extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String JWT_COOKIE_NAME = "jwt";
    private ServicioOfertas portOfertas;
    private ServicioUsuarios portUsuarios;

    public Visitante() {
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

    private void redirectToAppropriatePage(HttpServletRequest req, HttpServletResponse resp, boolean isMobile, String tipoUsuario) throws ServletException, IOException {
        switch (tipoUsuario) {
            case "empresa":
                handleEmpresaUser(isMobile, resp);
                break;
            case "postulante":
                resp.sendRedirect("postulante");
                break;
            default:
                invalidateToken(req, resp, isMobile);
                break;
        }
    }

    private void handleEmpresaUser(boolean isMobile, HttpServletResponse resp) throws IOException, ServletException {
        if (isMobile) {
        	clearCookie(resp);
            resp.sendRedirect("visitante");
        } else {
            resp.sendRedirect("empresa");
        }
    }

    private void invalidateToken(HttpServletRequest req, HttpServletResponse resp, boolean isMobile) throws ServletException, IOException {
        req.setAttribute("invalidToken", true);
        clearCookie(resp);
        dispatchToLogin(req, resp, isMobile);
    }

    private void clearCookie(HttpServletResponse resp) {
        Cookie jwtCookie = new Cookie(JWT_COOKIE_NAME, "");
        jwtCookie.setMaxAge(0);
        resp.addCookie(jwtCookie);
    }

    private void dispatchToLogin(HttpServletRequest req, HttpServletResponse resp, boolean isMobile) throws ServletException, IOException {
        String path = isMobile ? "/WEB-INF/mobile/loginMobile.jsp" : "/WEB-INF/visitante/inicio.jsp";
        req.getRequestDispatcher(path).forward(req, resp);
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
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (JWT_COOKIE_NAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, KeywordExisteException_Exception {
        boolean isMobile = isMobileRequest(req);

        req.setAttribute("keywords", portOfertas.obtenerKeywords().getItem());
        String busqueda = req.getParameter("busqueda");
        String[] keywordsSeleccionadas = req.getParameterValues("keywords");
        List<DtPublicacion> publicaciones = getPublicaciones(busqueda, keywordsSeleccionadas);
        req.setAttribute("publicaciones", filtrarPublicacionesConfirmadas(publicaciones));

        String jwt = getJwtFromCookies(req.getCookies());
        if (jwt != null && portUsuarios.validarToken(jwt)) {
            redirectToAppropriatePage(req, resp, isMobile, portUsuarios.tipoUsuario(jwt));
        } else {
            dispatchToLogin(req, resp, isMobile);
        }
    }

    private List<DtPublicacion> getPublicaciones(String busqueda, String[] keywordsSeleccionadas) {
        if (busqueda != null && !busqueda.isEmpty()) {
            return portOfertas.obtenerPublicacionesPorBusqueda(busqueda).getItem();
        } else if (keywordsSeleccionadas != null && keywordsSeleccionadas.length > 0) {
            String keywordsString = String.join("/", keywordsSeleccionadas);
            return portOfertas.obtenerPublicacionesPorKeywords(keywordsString).getItem();
        } else {
            return portOfertas.obtenerPublicaciones().getItem();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
			processRequest(request, response);
		} catch (ServletException | IOException | KeywordExisteException_Exception e) {
			e.printStackTrace();
		}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
			processRequest(request, response);
		} catch (ServletException | IOException | KeywordExisteException_Exception e) {
			e.printStackTrace();
		}
    }
}

