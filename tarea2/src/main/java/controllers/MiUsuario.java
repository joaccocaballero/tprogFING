package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servidor.publicar.DtPublicacionArray;
import servidor.publicar.DtPostulacionArray;
import servidor.publicar.DtEmpresa;
import servidor.publicar.DtPostulante;
import servidor.publicar.DtUsuario;
import servidor.publicar.NicknameNoExisteException_Exception;
import servidor.publicar.UsuarioNoEsPostulanteException_Exception;


/*
 * Servlet implementation class MiUsuario
 */

@WebServlet("/miUsuario")
public class MiUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MiUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    	servidor.publicar.ServicioOfertasService serviceOfertas = new servidor.publicar.ServicioOfertasService();
        servidor.publicar.ServicioOfertas portOfertas = serviceOfertas.getServicioOfertasPort();
    	servidor.publicar.ServicioUsuariosService serviceUsuarios = new servidor.publicar.ServicioUsuariosService();
		servidor.publicar.ServicioUsuarios portUsuarios = serviceUsuarios.getServicioUsuariosPort();
		Cookie[] cookies = req.getCookies();
		String jwtCookieName = "jwt";
		String jwt = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (jwtCookieName.equals(cookie.getName())) {
					jwt = cookie.getValue();
					break;
				}
			}
		}

		if(jwt != null) {
			DtUsuario user = portUsuarios.obtenerDatosDeUsuarioJWT(jwt);
			if(user != null) {
	    		req.setAttribute("imgPerfil", user.getUrlImagen());
	    		if(user instanceof DtPostulante) {
	        		req.setAttribute("nickname", user.getNickname());
	        		req.setAttribute("nombre", user.getNombre());
	        		req.setAttribute("apellido", user.getApellido());
	        		req.setAttribute("correo", user.getCorreo());
	        		req.setAttribute("nacionalidad", ((DtPostulante) user).getNacionalidad());
	        		req.setAttribute("fechaNacimiento", ((DtPostulante) user).getFechaNacimiento());
	        		try {
	        			DtPostulacionArray postulacionesDePostulante = portOfertas.obtenerPostulacionesPorPostulante(user.getNickname());
	        			if(postulacionesDePostulante != null) {
		        			req.setAttribute("postulaciones", postulacionesDePostulante.getItem());
		        		}
		        		req.getRequestDispatcher("/WEB-INF/postulante/MiUsuario.jsp").forward(req, resp);
	    			} catch (NicknameNoExisteException_Exception | UsuarioNoEsPostulanteException_Exception e) {
	    				
	    			}
	        		
	        	}else {
	        		if(user  instanceof DtEmpresa) {
	        			req.setAttribute("nickname", user.getNickname());
	            		req.setAttribute("nombre", user.getNombre());
	            		req.setAttribute("apellido", user.getApellido());
	            		req.setAttribute("correo", user.getCorreo());
	            		req.setAttribute("descripcion", ((DtEmpresa) user).getDescripcion());
	            		req.setAttribute("nombreEmpresa", ((DtEmpresa) user).getNombreEmpresa());
	            		req.setAttribute("link_web", ((DtEmpresa) user).getLinkWeb());
	            		DtPublicacionArray publicaciones = portOfertas.obtenerPublicacionesDeEmpresa(user.getNickname());
	            		if(publicaciones != null && !publicaciones.getItem().isEmpty()) {
	            			req.setAttribute("publicaciones", publicaciones.getItem());
	            		}
	            		
	        			req.getRequestDispatcher("/WEB-INF/empresa/MiUsuario.jsp").forward(req, resp);
	        		}
	        	}
	    	}else {
	    		resp.sendRedirect("visitante");
	    	}
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

