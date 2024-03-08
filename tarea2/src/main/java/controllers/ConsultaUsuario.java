package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servidor.publicar.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import utils.CookiesUtils;


/**
 * Servlet implementation class ConsultaUsuario
 */
@WebServlet ("/consultaUsuario")
public class ConsultaUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultaUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private List<DtUsuario> buscarUsuariosPorNickname(String nickname, DtUsuarioArray usuarios) {
    	List<DtUsuario> usuariosAux = usuarios.getItem();
        List<DtUsuario> usuariosEncontrados = new ArrayList<>();
        for (DtUsuario usuario : usuariosAux) {
            if (usuario.getNickname().toLowerCase().contains(nickname.toLowerCase())) {
                usuariosEncontrados.add(usuario);
            }
        }
        return usuariosEncontrados;
    }
       
    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	servidor.publicar.ServicioUsuariosService service = new servidor.publicar.ServicioUsuariosService();
    	servidor.publicar.ServicioUsuarios portUsuarios = service.getServicioUsuariosPort();
    	CookiesUtils cookies = CookiesUtils.obtenerInstancia();
        String jwt = cookies.obtenerJWTEnCookies(req, resp);
        if(jwt != null) {
        	String tipoUsuario = portUsuarios.tipoUsuario(jwt);
    		DtUsuarioArray usuarios = portUsuarios.listarUsuarios();
    		String nickname = req.getParameter("nickname");
    		if(nickname != null) {
    			List<DtUsuario> usuariosFiltrados = buscarUsuariosPorNickname(nickname,usuarios);
    			req.setAttribute("usuariosFiltrados", usuariosFiltrados);
    		}
    		DtUsuario user = portUsuarios.obtenerDatosDeUsuarioJWT(jwt);
    		if(user != null) {
    			req.setAttribute("imgPerfil", user.getUrlImagen());
    		}

    		req.setAttribute("usuarios", usuarios.getItem());
        	switch(tipoUsuario) {
        		case ("postulante"):
        			req.getRequestDispatcher("/WEB-INF/postulante/consultaUsuario.jsp").forward(req, resp);
        			break;
        		case ("empresa"):
        			req.getRequestDispatcher("/WEB-INF/empresa/consultaUsuario.jsp").forward(req, resp);
        			break;
        		default:
        			req.getRequestDispatcher("/WEB-INF/visitante/inicio.jsp").forward(req, resp);
        			break;
        	}   	
            	
        } else {
        	DtUsuarioArray usuarios = portUsuarios.listarUsuarios();
    		String nickname = req.getParameter("nickname");
    		if(nickname != null) {
    			List<DtUsuario> usuariosFiltrados = buscarUsuariosPorNickname(nickname,usuarios);
    			req.setAttribute("usuariosFiltrados", usuariosFiltrados);
    		}
    		req.setAttribute("usuarios", usuarios.getItem());
        	req.getRequestDispatcher("/WEB-INF/visitante/consultaUsuario.jsp").forward(req, resp);
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
		processRequest(request,response);
	}

}
