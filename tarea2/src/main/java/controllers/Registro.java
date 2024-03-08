package controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servidor.publicar.CorreoRepetidoException_Exception;
import servidor.publicar.UsuarioRepetidoException_Exception;
import excepciones.CorreoRepetidoException;
import excepciones.NicknameNoExisteException;
import excepciones.UsuarioRepetidoException;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;


/**
 * Servlet implementation class Login
 */
@WebServlet("/registro")
public class Registro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	servidor.publicar.ServicioUsuariosService serviceUsuarios = new servidor.publicar.ServicioUsuariosService();
	servidor.publicar.ServicioUsuarios portUsuarios = serviceUsuarios.getServicioUsuariosPort();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Registro() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 * 
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 * @throws NicknameNoExisteException 
	 * @throws CorreoRepetidoException 
	 * @throws UsuarioRepetidoException 
	 * 
	 */
	
	public boolean isValidEmail(String email) {
	    String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(email);
	    return matcher.matches();
	}
	
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CorreoRepetidoException_Exception, UsuarioRepetidoException_Exception, ParseException{
		  String action = request.getParameter("action");
	        if ("altaEmpresa".equals(action)) {
	            String nickname = request.getParameter("nickname");
	            String nombre = request.getParameter("nombre");
	            String apellido = request.getParameter("apellido");
	            String email = request.getParameter("email");
	            String nomEmpresa = request.getParameter("nomEmpresa");
	            String desc = request.getParameter("desc");
	            String linkWeb = request.getParameter("linkWeb");
	            String password = request.getParameter("password");
	            String passwordConfirm = request.getParameter("confirmar_contrasena");
	            if (nickname.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || 
	            	    nomEmpresa.isEmpty() || desc.isEmpty() || password.isEmpty() || 
	            	    passwordConfirm.isEmpty()) {
	            	    request.setAttribute("error", "Todos los campos son obligatorios.");
	            	    request.getRequestDispatcher("/WEB-INF/registro/registro.jsp").forward(request, response);
	            	} else {
				            if (!password.equals(passwordConfirm) || password.length() < 6) {
				                request.setAttribute("error", "Las contraseñas no coinciden.");
				                request.getRequestDispatcher("/WEB-INF/registro/registro.jsp").forward(request, response);
				            }else {
				            	if(isValidEmail(email)) {        	
					            portUsuarios.altaEmpresa(nickname,nombre,apellido,email,password,nomEmpresa,desc,linkWeb,"https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png");
					            String mensaje = "Empresa Registrada con éxito.";
				                response.sendRedirect("visitante?mensaje=" + URLEncoder.encode(mensaje, "UTF-8"));
				            	}else {
				            		request.setAttribute("error", "El email ingresado no es valido.");
					                request.getRequestDispatcher("/WEB-INF/registro/registro.jsp").forward(request, response);
				            	}
				            }
	            	}
	        } else {
	        	if ("altaPostulante".equals(action)) {
	                String nickname = request.getParameter("nickname");
	                String nombre = request.getParameter("nombre");
	                String apellido = request.getParameter("apellido");
	                String email = request.getParameter("email");
	                String fechaNacimiento = request.getParameter("fechaNacimiento");
	                String nacionalidad = request.getParameter("nacionalidad");
	                String password = request.getParameter("password");
	                String passwordConfirm = request.getParameter("confirmar_contrasena");
	                if (nickname.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || 
	                	    fechaNacimiento.isEmpty() || nacionalidad.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty()) {
	                	    request.setAttribute("error", "Todos los campos son obligatorios.");
	                	    request.getRequestDispatcher("/WEB-INF/registro/registro.jsp").forward(request, response);
	                	} else {
	                		if (!password.equals(passwordConfirm) || password.length() < 6) {
				                request.setAttribute("error", "Las contraseñas no coinciden.");
				                request.getRequestDispatcher("/WEB-INF/registro/registro.jsp").forward(request, response);
				            }else {
				            	if(isValidEmail(email)) {
					            	String fecha = fechaNacimiento;				            	
					            	if(verifyFecha(fecha)) {
					            		SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyy-MM-dd");
					            		Date fechaOriginal = formatoEntrada.parse(fecha);
					            		SimpleDateFormat formatoSalida = new SimpleDateFormat("dd/MM/yyyy");
					            		String fechaFormateada = formatoSalida.format(fechaOriginal);
						                portUsuarios.altaPostulante(nickname,nombre,apellido,email,password,fechaFormateada,nacionalidad,"https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png");
						                String mensaje = "Postulante Registrado con éxito.";
						                response.sendRedirect("visitante?mensaje=" + URLEncoder.encode(mensaje, "UTF-8"));
					            	}else {
					            		request.setAttribute("error", "La fecha es invalida.");
						                request.getRequestDispatcher("/WEB-INF/registro/registro.jsp").forward(request, response);
					            	}
				            	}else {
				            		request.setAttribute("error", "El email ingresado no es valido.");
					                request.getRequestDispatcher("/WEB-INF/registro/registro.jsp").forward(request, response);
				            	}
				            }
	                	}          
	        	}else {
	        		request.getRequestDispatcher("/WEB-INF/registro/registro.jsp").forward(request, response);
	        	}
	        }
	}

	private Boolean verifyFecha(String fechaNacimiento){
        LocalDate fechaNacimientoParsed = LocalDate.parse(fechaNacimiento);
        LocalDate fechaActual = LocalDate.now();
        if (fechaNacimientoParsed.isBefore(fechaActual)) {
            return true;
        } else {
            return false;
        }
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/registro/registro.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				try {
					processRequest(request, response);
				} catch (ServletException | IOException  e) {
					request.setAttribute("error", "Ocurrio un error.");
	                request.getRequestDispatcher("/WEB-INF/registro/registro.jsp").forward(request, response);
					e.printStackTrace();
				}
				catch (UsuarioRepetidoException_Exception e) {
					request.setAttribute("error", "El nickname ya existe.");
	                request.getRequestDispatcher("/WEB-INF/registro/registro.jsp").forward(request, response);
	                e.printStackTrace();
				} catch (CorreoRepetidoException_Exception e) {
					request.setAttribute("error", "El correo ya existe.");
	                request.getRequestDispatcher("/WEB-INF/registro/registro.jsp").forward(request, response);
	                e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
}

