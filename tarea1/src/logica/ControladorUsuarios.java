package logica;

import org.mindrot.jbcrypt.BCrypt;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import excepciones.CorreoNoEncontradoException;
import excepciones.CorreoRepetidoException;
import excepciones.NicknameNoExisteException;
import excepciones.UsuarioNoEsEmpresaException;
import excepciones.UsuarioRepetidoException;
import servidor.types.DTEmpresa;
import servidor.types.DTOferta;
import servidor.types.DTPostulante;
import servidor.types.DTUsuario;

public class ControladorUsuarios implements IControladorUsuario {

	private static ControladorUsuarios instancia;

	public static ControladorUsuarios getInstance() {
		if (instancia == null) {
			instancia = new ControladorUsuarios();
		}
		return instancia;
	}

	public void altaPostulante(String nickname, String nombre, String apellido, String email, String contraseña,
			String fechaNacimiento, String nacionalidad, String url_imagen)
			throws UsuarioRepetidoException, CorreoRepetidoException {
		
		ManejadorUsuarios manejadorU = ManejadorUsuarios.getInstance();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
		LocalDate fechaLocalDate = LocalDate.parse(fechaNacimiento, formatter);
		if(contraseña.length() >= 6) {
			String contraseñaHasheada = BCrypt.hashpw(contraseña, BCrypt.gensalt());
			Postulante p = new Postulante(nickname, nombre, apellido, email, contraseñaHasheada, fechaLocalDate,
					nacionalidad, url_imagen);
			manejadorU.altaPostulante(p);
		}
	}

	@Override
	public void altaEmpresa(String nickname, String nombre, String apellido, String email, String contraseña,
			String nomEmpresa, String desc, String linkWeb, String url_imagen)
			throws UsuarioRepetidoException, CorreoRepetidoException {
		ManejadorUsuarios manejadorU = ManejadorUsuarios.getInstance();
		String contraseñaHasheada = BCrypt.hashpw(contraseña, BCrypt.gensalt());
		Empresa e = new Empresa(nickname, nombre, apellido, email, contraseñaHasheada, nomEmpresa, desc, linkWeb,
				url_imagen);
		manejadorU.altaEmpresa(e);
	}

	public Usuario obtenerUsuario(String nickname) throws NicknameNoExisteException {
		ManejadorUsuarios manejadorU = ManejadorUsuarios.getInstance();
		return manejadorU.getUsuario(nickname);
	}

	public Boolean usuarioExiste(String correo) {
		try {
			ManejadorUsuarios manejadorU = ManejadorUsuarios.getInstance();
			@SuppressWarnings("unused")
			Usuario u = manejadorU.getUsuarioXCorreo(correo);
			return true;
		} catch (NicknameNoExisteException e) {
			return false;
		}
	};

	public DTUsuario consultarUsuarioPorCorreo(String correo)
			throws CorreoNoEncontradoException, NicknameNoExisteException {
		ManejadorUsuarios manejadorU = ManejadorUsuarios.getInstance();
		DTUsuario u = manejadorU.obtenerUsuarioPorCorreo(correo);
		return u;
	}

	public Boolean validarUsuario(String correo, String contraseña) throws NicknameNoExisteException {
		try {
			ManejadorUsuarios manejadorU = ManejadorUsuarios.getInstance();
			Usuario u = manejadorU.getUsuarioXCorreo(correo);
			return BCrypt.checkpw(contraseña, u.getContrasenia());
		} catch (NicknameNoExisteException e) {
			return false;
		}
	}

	public List<DTUsuario> listarUsuarios() {
		ManejadorUsuarios manejadorU = ManejadorUsuarios.getInstance();
		return manejadorU.obtenerListaUsuarios();
	}

	public List<DTEmpresa> listarEmpresas() {
		ManejadorUsuarios manejadorU = ManejadorUsuarios.getInstance();
		return manejadorU.obtenerListaEmpresas();
	}

	public List<DTPostulante> listarPostulantes() {
		ManejadorUsuarios manejadorU = ManejadorUsuarios.getInstance();
		return manejadorU.obtenerListaPostulantes();
	}

	public DTUsuario consultarUsuario(String nicknameUsuario) throws NicknameNoExisteException {
		ManejadorUsuarios manejadorU = ManejadorUsuarios.getInstance();
		return manejadorU.obtenerUsuario(nicknameUsuario);
	}

	public Set<DTOferta> obtenerOfertasDeEmpresa(String nicknameEmpresa)
			throws NicknameNoExisteException, UsuarioNoEsEmpresaException {
		ManejadorUsuarios manejadorU = ManejadorUsuarios.getInstance();
		return manejadorU.obtenerOfertasDeEmpresa(nicknameEmpresa);
	}

	public void actualizarDatosEmpresa(String nickFiltrado, String nuevoNombre, String nuevoApellido,
			String nombreEmpresa, String descripcionEmpresa, String linkWebEmpresa, String urlImagen) {
		ManejadorUsuarios manejadorU = ManejadorUsuarios.getInstance();
		try {
			manejadorU.actualizarDatosEmpresa(nickFiltrado, nuevoNombre, nuevoApellido, nombreEmpresa,
					descripcionEmpresa, linkWebEmpresa, urlImagen);
		} catch (NicknameNoExisteException e) {
			e.printStackTrace();
		}
	}

	public void actualizarDatosPostulante(String nickname, String nuevoNombre, String nuevoApellido,
			String fechaNacimiento, String nacionalidad, String urlImagen) {
		ManejadorUsuarios manejadorU = ManejadorUsuarios.getInstance();
		try {
			manejadorU.actualizarDatosPostulante(nickname, nuevoNombre, nuevoApellido, fechaNacimiento, nacionalidad
					,urlImagen);
		} catch (NicknameNoExisteException e) {
			e.printStackTrace();
		}
	}

}
