package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import excepciones.NicknameNoExisteException;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servidor.publicar.*;
import utils.CookiesUtils;

@WebServlet("/mostrarUsuario")
public class MostrarUsuario extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static List<DtPublicacion> filtrarPublicacionesConfirmadas(List<DtPublicacion> publicaciones) {
        return publicaciones.stream()
                .filter(publicacion -> EnumEstadoOferta.CONFIRMADA.equals(publicacion.getDtOferta().getEstado()))
                .collect(Collectors.toList());
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, java.io.IOException {
		servidor.publicar.ServicioUsuariosService serviceUsuarios = new servidor.publicar.ServicioUsuariosService();
		servidor.publicar.ServicioUsuarios portUsuarios = serviceUsuarios.getServicioUsuariosPort();
		servidor.publicar.ServicioOfertasService serviceOfertas = new servidor.publicar.ServicioOfertasService();
        servidor.publicar.ServicioOfertas portOfertas = serviceOfertas.getServicioOfertasPort();
		String usuarioSeleccionado = request.getParameter("nickname");
		if(usuarioSeleccionado == null) {
			response.sendRedirect("visitante");
			return;
		}
		CookiesUtils cookies = CookiesUtils.obtenerInstancia();
        String jwt = cookies.obtenerJWTEnCookies(request, response);
        String tipoUsuario = "visitante";
        if (jwt != null) {
         tipoUsuario = portUsuarios.tipoUsuario(jwt);
        }
        DtUsuario usuario = null;
		try {
			usuario = portUsuarios.consultarUsuario(usuarioSeleccionado);
			if(usuario instanceof DtEmpresa) {
				List<DtPublicacion> publicaciones = portOfertas.obtenerPublicacionesDeEmpresa(usuario.getNickname()).getItem();
				List<DtPublicacion> pubFiltered = filtrarPublicacionesConfirmadas(publicaciones);
				request.setAttribute("publicaciones", pubFiltered);
				
			}
		} catch (NicknameNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if(jwt != null) {
    		DtUsuario autenticado = portUsuarios.obtenerDatosDeUsuarioJWT(jwt);
    		request.setAttribute("imgPerfil", autenticado.getUrlImagen());
    		request.setAttribute("autenticado", autenticado.getNickname());
           
    	}
    	request.setAttribute("usuarioSeleccionado", usuario);
    
        switch(tipoUsuario) {
		case ("postulante"):
			if (usuario instanceof DtPostulante) {
				request.getRequestDispatcher("/WEB-INF/postulante/mostrarPostulante.jsp").forward(request, response);
				break;
			}
			request.getRequestDispatcher("/WEB-INF/postulante/mostrarEmpresa.jsp").forward(request, response);
			break;
		case ("empresa"):
			if (usuario instanceof DtPostulante) {
				request.getRequestDispatcher("/WEB-INF/empresa/mostrarPostulante.jsp").forward(request, response);
				break;
			}
			request.getRequestDispatcher("/WEB-INF/empresa/mostrarEmpresa.jsp").forward(request, response);
			break;
		default:
			if (usuario instanceof DtPostulante) {
				request.getRequestDispatcher("/WEB-INF/visitante/mostrarPostulante.jsp").forward(request, response);
				break;
			}
			
			request.getRequestDispatcher("/WEB-INF/visitante/mostrarEmpresa.jsp").forward(request, response);
			break;
	}   
    }
}