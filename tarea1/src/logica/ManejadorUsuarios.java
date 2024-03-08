package logica;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;
import excepciones.*;
import servidor.types.DTUsuario;
import servidor.types.DTEmpresa;
import servidor.types.DTOferta;
import servidor.types.DTPostulante;

public class ManejadorUsuarios {

	private static ManejadorUsuarios instancia;
	
	private Map<String, Usuario> coleccionUsuarios = new HashMap<String,Usuario>();
	private Map<String, String> coleccionCorreosUsuarios = new HashMap<String, String>();
	
	public static ManejadorUsuarios getInstance() {
        if (instancia == null) {
            instancia = new ManejadorUsuarios();
        }
        return instancia;
    }
	
	/**
	 * Crea la empresa y la agrega a coleccionUsuarios.
	 */
	void altaEmpresa(Empresa emp) throws UsuarioRepetidoException, CorreoRepetidoException {
		if (coleccionUsuarios.containsKey(emp.getNickname().toLowerCase())){
			throw new UsuarioRepetidoException("Ya existe un usuario con el nickname ingresado.");
		}
		if (coleccionCorreosUsuarios.containsKey(emp.getCorreo()))
			throw new CorreoRepetidoException("Ya existe un usuario con el correo " + emp.getCorreo() + ".");
		coleccionUsuarios.put(emp.getNickname().toLowerCase(), emp);
		coleccionCorreosUsuarios.put(emp.getCorreo(), emp.getNickname());
	}
	
	/**
	 * Crea el postulante y lo agrega a coleccionUsuarios.
	 */
	void altaPostulante(Postulante post) throws UsuarioRepetidoException, CorreoRepetidoException {
		if (coleccionUsuarios.containsKey(post.getNickname().toLowerCase())){
			throw new UsuarioRepetidoException("Ya existe un usuario con el nickname ingresado.");
		}
		if (coleccionCorreosUsuarios.containsKey(post.getCorreo()))
			throw new CorreoRepetidoException("Ya existe un usuario con el correo " + post.getCorreo() + ".");
		coleccionUsuarios.put(post.getNickname().toLowerCase(), post);
		coleccionCorreosUsuarios.put(post.getCorreo(), post.getNickname());
	}

	/**
	 * Devuelve un DTUsuario con los datos del usuario con el nickname dado. Si es una empresa tambien devuelve sus ofertas y si es un postulante tambien devuelve sus postulaciones.
	 * Si el nickname no existe en el sistema tira un NicknameNoExisteException.
	 * Si el nickname es NULL tira una unchecked exception.
	 */
	public DTUsuario obtenerUsuario(String nicknameUsuario) throws NicknameNoExisteException {
		if (!coleccionUsuarios.containsKey(nicknameUsuario.toLowerCase()))
			throw new NicknameNoExisteException("El usuario con el nickname " + nicknameUsuario + " no existe.");
		return coleccionUsuarios.get(nicknameUsuario.toLowerCase()).toDataType();
	}
	
	public DTUsuario obtenerUsuarioPorCorreo(String correo) throws CorreoNoEncontradoException, NicknameNoExisteException{
		if (!coleccionCorreosUsuarios.containsKey(correo)){
			throw new CorreoNoEncontradoException("Usuario Invalido");
		}else {
			Usuario user = getUsuarioXCorreo(correo);
			return user.toDataType();
		}
		
	}
	
	public Usuario getUsuario(String nickname) throws NicknameNoExisteException {
		if (!coleccionUsuarios.containsKey(nickname.toLowerCase()))
			throw new NicknameNoExisteException("El usuario con el nickname " + nickname + " no existe.");
		return coleccionUsuarios.get(nickname.toLowerCase());
	}
	
	public Usuario getUsuarioXCorreo(String correo) throws NicknameNoExisteException {
		if (!coleccionCorreosUsuarios.containsKey(correo)){
			throw new NicknameNoExisteException("Usuario Invalido");
		}else {
			String nickname = coleccionCorreosUsuarios.get(correo);
			return coleccionUsuarios.get(nickname.toLowerCase());
		}
	}
	/**
	 * Devuelve una lista de DTUsuario con la informacion de todos los usuarios registrados en el sistema ordenados segun su nombre.
	 * Si no hay usuarios registrados devuelve una lista vacia.
	 */
	public List<DTUsuario> obtenerListaUsuarios() {
		List<DTUsuario> listaUsuarios = new ArrayList<DTUsuario>();
		for (Map.Entry<String, Usuario> entry : coleccionUsuarios.entrySet()) {
			Usuario usr = entry.getValue();
			listaUsuarios.add(usr.toDataType());
		}
		listaUsuarios.sort(Comparator.comparing(DTUsuario::getNombre)
				.thenComparing(DTUsuario::getApellido));
		return listaUsuarios;
	}
	
	/**
	 * Devuelve una lista de DTUsuario con la informacion de todas las empresas registradas en el sistema ordenadas segun el nombre de la empresa.
	 * Si no hay empresas registradas devuelve una lista vacia.
	 */
	/**
	 * Devuelve una lista de DTUsuario con la informacion de todas las empresas registradas en el sistema ordenadas segun el nombre de la empresa.
	 * Si no hay empresas registrados devuelve una lista vacia.
	 */
	public List<DTEmpresa> obtenerListaEmpresas() {
		List<DTEmpresa> listaEmpresas = this.coleccionUsuarios
				.values()
				.stream()
				.filter(usuario -> usuario instanceof Empresa)
				.map(Usuario::toDataType)
				.map(usuarioDT -> (DTEmpresa) usuarioDT)
				.collect(Collectors.toList());
		listaEmpresas.sort(Comparator.comparing(DTEmpresa::getNombreEmpresa));
		return listaEmpresas;
	}
	/**
	 * Devuelve una lista de DTPostulante con la informacion de todos los postulantes registrados en el sistema ordenados segun su nombre.
	 * Si no hay postulantes registrados devuelve una lista vacia.
	 */
	public List<DTPostulante> obtenerListaPostulantes() {
		List<DTPostulante> listaPostulantes = this.coleccionUsuarios
				.values()
				.stream()
				.filter(usuario -> usuario instanceof Postulante)
				.map(Usuario::toDataType)
				.map(usuarioDT -> (DTPostulante) usuarioDT)
				.collect(Collectors.toList());
		listaPostulantes.sort(Comparator.comparing(DTPostulante::getNombre)
				.thenComparing(DTPostulante::getApellido));
		return listaPostulantes;
	}
	
	/**
	 * Devuelve un set de tipo DTOferta con todas las ofertas asociadas a la empresa con el nickname "nicknameEmpresa" ordenadas alfabeticamente por el nombre de las ofertas.
	 * Si el nickname no esta asociado a un usuario en el sistema tira una NicknameNoExisteException.
	 * Si existe el usuario con ese nickname pero no es una empresa tira una UsuarioNoEsEmpresaException.
	 * Si no tiene ofertas asociadas devuelve una lista vacia.
	 */
	public Set<DTOferta> obtenerOfertasDeEmpresa(String nicknameEmpresa) throws NicknameNoExisteException, UsuarioNoEsEmpresaException {
		if (!coleccionUsuarios.containsKey(nicknameEmpresa.toLowerCase()))
			throw new NicknameNoExisteException("La empresa con el nickname " + nicknameEmpresa + " no existe.");
		if (!(coleccionUsuarios.get(nicknameEmpresa.toLowerCase()) instanceof Empresa))
			throw new UsuarioNoEsEmpresaException("El usuario con el nickname " + nicknameEmpresa + " no es una empresa.");
		return ((Empresa) coleccionUsuarios.get(nicknameEmpresa.toLowerCase())).getOfertas();
	}
	
	/**
	 * Metodo que solo existe para ser llamado desde la clase ManejadorOfertaLaboral.
	 * Asocia la postulacion a la coleccion de postulaciones del postulante de nombre postulacion.getNicknamePostulante(). 
	 */
	public void postularAOferta(Postulacion postulacion) {
		Postulante postulante = (Postulante) this.coleccionUsuarios.get(postulacion.getNicknamePostulante().toLowerCase());
		postulante.asociarPostulacion(postulacion, postulacion.getNombreOfertaLaboral());
	}
	
	public void actualizarDatosEmpresa(String nickFiltrado,String nuevoNombre,String nuevoApellido,String nombreEmpresa,String descripcionEmpresa, String linkWebEmpresa
			,String urlImagen) throws NicknameNoExisteException {
		String nicknameLowerCase = nickFiltrado.toLowerCase();
		if (coleccionUsuarios.containsKey(nicknameLowerCase) ) {
			Usuario user = coleccionUsuarios.get(nicknameLowerCase);
			Empresa empresa = (Empresa) user;
			empresa.setNombre(nuevoNombre);
			empresa.setApellido(nuevoApellido);
			empresa.setNombreEmpresa(nombreEmpresa);
			empresa.setDescripcion(descripcionEmpresa);
			empresa.setLinkWeb(linkWebEmpresa);
			empresa.setUrlImagen(urlImagen);
			
		} else {
			throw new NicknameNoExisteException("El usuario con el nickname " + nickFiltrado + " no existe.");
		}
	}
	
	
	public void actualizarDatosPostulante(String nickname, String nuevoNombre,String nuevoApellido,String fechaNacimiento, String nacionalidad
			,String urlImagen) throws NicknameNoExisteException {
		String nicknameLowerCase = nickname.toLowerCase();
		if (coleccionUsuarios.containsKey(nicknameLowerCase) ) {
			Usuario user = coleccionUsuarios.get(nicknameLowerCase);
			Postulante postulante = (Postulante) user;
			postulante.setNombre(nuevoNombre);
			postulante.setApellido(nuevoApellido);
			LocalDate fecha = null;
			try {
			    fecha = LocalDate.parse(fechaNacimiento, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			    postulante.setFechaNacimiento(fecha);
			} catch (DateTimeParseException e) {
			    e.printStackTrace();
			    // Aquí puedes manejar la excepción si la cadena de fecha no es válida
			}

			if (fecha != null) {
				postulante.setFechaNacimiento(fecha);
			}
			
			postulante.setNacionalidad(nacionalidad);
			postulante.setUrlImagen(urlImagen);
			} else {
			throw new NicknameNoExisteException("El usuario con el nickname " + nickname + " no existe.");
		}
	}
	
	/**
	 * Sustituye la coleccion de usuarios por una vacia.
	 */
	public void limpiarColeccionUsuarios() {
		this.coleccionUsuarios = new HashMap<String,Usuario>();
		this.coleccionCorreosUsuarios = new HashMap<String, String>();
	}

}


