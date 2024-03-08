package logica;

import java.time.LocalDate;

import java.util.List;
import java.util.Set;

import excepciones.CorreoNoEncontradoException;
import excepciones.CorreoRepetidoException;
import excepciones.NicknameNoExisteException;
import excepciones.UsuarioNoEsEmpresaException;
import servidor.types.DTEmpresa;
import servidor.types.DTOferta;
import servidor.types.DTPostulante;
import excepciones.UsuarioRepetidoException;
import servidor.types.DTUsuario;

public interface IControladorUsuario {

	/**
	 * Devuelve una lista de tipo DTUsuario con la informacion de todos los usuarios
	 * registrados en el sistema ordenados segun su nombre. Si no hay usuarios
	 * registrados devuelve una lista vacia.
	 */
	public List<DTUsuario> listarUsuarios();

	/**
	 * Devuelve una lista de tipo DTUsuario con la informacion de todas las empresas
	 * registradas en el sistema ordenadas segun el nombre de la empresa. Si no hay
	 * empresas registradas devuelve una lista vacia.
	 */
	public List<DTEmpresa> listarEmpresas();

	/**
	 * Devuelve una lista de tipo DTPostulante con la informacion de todos los
	 * postulantes registrados en el sistema ordenados segun su nombre. Si no hay
	 * postulantes registrados devuelve una lista vacia.
	 */
	public List<DTPostulante> listarPostulantes();

	/**
	 * Dado el nickname de un usuario devuelve todos sus datos. Si es una empresa
	 * tambien devuelve un set de tipo DTOferta con sus ofertas ordenadas
	 * alfabeticamente por el nombre de las mismas. Si es un postulante tambien
	 * devuelve una lista de tipo DTPostulacion con sus postulaciones. Si no existe
	 * un usuario con el nickname brindado tira una NicknameNoExisteException.
	 */
	public DTUsuario consultarUsuario(String nicknameUsuario) throws NicknameNoExisteException;

	public DTUsuario consultarUsuarioPorCorreo(String correo)
			throws CorreoNoEncontradoException, NicknameNoExisteException;

	public Boolean usuarioExiste(String email);

	public Boolean validarUsuario(String correo, String contraseña) throws NicknameNoExisteException;

	public void altaPostulante(String nickname, String nombre, String apellido, String email,
			String contraseña, String fechaNacimiento, String nacionalidad, String url_imagen)
			throws UsuarioRepetidoException, CorreoRepetidoException;

	public void altaEmpresa(String nickname, String nombre, String apellido, String email, String contraseña,
			String nomEmpresa, String desc, String linkWeb, String url_imagen)
			throws UsuarioRepetidoException, CorreoRepetidoException;

	/**
	 * Devuelve un set de tipo DTOferta con todas las ofertas asociadas a la empresa
	 * con el nickname "nicknameEmpresa" ordenadas alfabeticamente por el nombre de
	 * las ofertas. Si el nickname no esta asociado a un usuario en el sistema tira
	 * una NicknameNoExisteException. Si existe el usuario con ese nickname pero no
	 * es una empresa tira una UsuarioNoEsEmpresaException. Si no tiene ofertas
	 * asociadas devuelve una lista vacia.
	 */
	public Set<DTOferta> obtenerOfertasDeEmpresa(String nicknameEmpresa)
			throws NicknameNoExisteException, UsuarioNoEsEmpresaException;

	public void actualizarDatosEmpresa(String nickFiltrado, String nuevoNombre, String nuevoApellido,
			String nombreEmpresa, String descripcionEmpresa, String linkWebEmpresa, String urlImagen);

	public void actualizarDatosPostulante(String nickname, String nuevoNombre, String nuevoApellido,
			String fechaNacimiento, String nacionalidad, String urlImagen);
}
