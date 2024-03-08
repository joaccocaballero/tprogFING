package logica;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Map;
import java.util.Set;

import excepciones.NombreExisteException;
import servidor.types.DTUsuario;
import servidor.types.EnumEstadoOferta;
import java.time.LocalDateTime;
import excepciones.OfertaNoExisteException;
import excepciones.UsuarioNoEsEmpresaException;
import excepciones.UsuarioNoEsPostulanteException;
import excepciones.KeywordExisteException;
import excepciones.NicknameNoExisteException;
import servidor.types.DTEmpresa;
import servidor.types.DTOferta;
import servidor.types.DTPostulacion;
import servidor.types.DTPostulante;

public class ManejadorOfertaLaboral {
	private Map<String, OfertaLaboral> coleccionOfertasLaborales = new TreeMap<String, OfertaLaboral>();
	private Map<String, Keyword> coleccionKeyword = new HashMap<String, Keyword>();
	private static ManejadorOfertaLaboral instancia;

	public static ManejadorOfertaLaboral getInstance() {
		if (instancia == null) {
			instancia = new ManejadorOfertaLaboral();
		}
		return instancia;
	}

	public void addOferta(OfertaLaboral ofL, List<String> keys) throws NombreExisteException, KeywordExisteException {
		if (coleccionOfertasLaborales.containsKey(ofL.getNombre())) {
			throw new NombreExisteException("Ya existe una oferta laboral con el nombre ingresado");
		}
		for (String key : keys) {
			if (!coleccionKeyword.containsKey(key)) {
				throw new KeywordExisteException("La Keyword con nombre" + key + " no existe");
			}
			Keyword keyword = coleccionKeyword.get(key);
			ofL.addKeyword(keyword);
		}
		coleccionOfertasLaborales.put(ofL.getNombre(), ofL);
	}
	
	public void confirmarOfertaLaboral(String nombreOferta) throws OfertaNoExisteException {
	    OfertaLaboral oferta = this.coleccionOfertasLaborales.get(nombreOferta);
	    if (oferta == null) {
	        throw new OfertaNoExisteException("No existe una oferta laboral con el nombre: " + nombreOferta);
	    }
	    oferta.setEstado(EnumEstadoOferta.CONFIRMADA);
	}

	/**
	 * Devuelve un DTOferta con la informacion de la oferta con el nombre brindado
	 * incluyendo sus postulaciones. Si no existe una oferta con ese nombre en el
	 * sistema tira una OfertaNoExisteException.
	 */
	public DTOferta obtenerOfertaLaboral(String nombreOferta) throws OfertaNoExisteException {
		return coleccionOfertasLaborales.get(nombreOferta).toDataType();
	}
	
	public OfertaLaboral getOfertaLaboral(String nombreOferta) throws OfertaNoExisteException {
		return coleccionOfertasLaborales
				.get(nombreOferta);
	}

	public List<DTOferta> obtenerOfertasLaborales() {
		Map<String, OfertaLaboral> ofertas = coleccionOfertasLaborales;
		List<DTOferta> returnOfertas = new ArrayList<>();

		for (OfertaLaboral oferta : ofertas.values()) {
			if (!oferta.getEstado().equals(EnumEstadoOferta.FINALIZADA)) {
				DTOferta dtoOferta = oferta.toDataType();
				returnOfertas.add(dtoOferta);
			}
		}

		return returnOfertas;
	}

	public void addKeyword(Keyword key) throws KeywordExisteException {
		if (coleccionKeyword.containsKey(key.getNombre())) {
			throw new KeywordExisteException("La Keyword con nombre" + key.getNombre() + " ya existe");
		}
		coleccionKeyword.put(key.getNombre(), key);
	}

	public List<String> obtenerKeywords() {
		List<String> out = new ArrayList<String>();
		for (Map.Entry<String, Keyword> entry : coleccionKeyword.entrySet()) {
			out.add(entry.getKey());
		}
		return out;
	}

	/**
	 * Postula al postulante a la oferta cuyo nombre es "nombreOfertaLaboral". Si no
	 * existe una oferta con ese nombre tira una OfertaNoExisteException.
	 */
	public void postularAOferta(String nombreOfertaLaboral, String nicknamePostulante, String cvReducido,
			String motivacion, LocalDateTime fechaPostulacion, String urlVideo)
			throws NicknameNoExisteException, UsuarioNoEsPostulanteException, OfertaNoExisteException {
		OfertaLaboral oferta = this.coleccionOfertasLaborales.get(nombreOfertaLaboral);

		// checkeo si existe el postulante con el nick "nicknamePostulante"
		ManejadorUsuarios manejadorU = ManejadorUsuarios.getInstance();
		DTUsuario usuario = manejadorU.obtenerUsuario(nicknamePostulante);

		// checkeo si el usuario es un postulante
		if (!(usuario instanceof DTPostulante))
			throw new UsuarioNoEsPostulanteException(
					"El usuario con nickname " + nicknamePostulante + " no es un postulante.");

		// checkeo si el postulante ya habia postulado a la oferta laboral
		boolean postulacionRepetida = oferta.getPostulaciones().stream()
				.anyMatch(postulacionDT -> postulacionDT.getNicknamePostulante().equals(nicknamePostulante));
		if (postulacionRepetida)
			throw new OfertaNoExisteException("Ya existe una postulacion a la oferta " + nombreOfertaLaboral
					+ " asociada al postulante " + nicknamePostulante + ".");

		// si nunca habia postulado a la oferta entonces creo la nueva postulacion
		Postulacion postulacion = new Postulacion(nombreOfertaLaboral, nicknamePostulante, cvReducido, motivacion,
				fechaPostulacion, urlVideo);
		oferta.asociarPostulacion(postulacion);
		manejadorU.postularAOferta(postulacion);
	}

	/**
	 * Devuelve un set de tipo DTOferta con todas las ofertas vigentes asociadas a
	 * la empresa con el nickname "nicknameEmpresa" ordenadas alfabeticamente por el
	 * nombre de las ofertas. Si el nickname no esta asociado a un usuario en el
	 * sistema tira una NicknameNoExisteException. Si existe el usuario con ese
	 * nickname pero no es una empresa tira una UsuarioNoEsEmpresaException. Si no
	 * tiene ofertas vigentes asociadas devuelve una lista vacia.
	 */
	public Set<DTOferta> obtenerOfertasVigentesDeEmpresa(String nicknameEmpresa)
			throws NicknameNoExisteException, UsuarioNoEsEmpresaException {
		ManejadorUsuarios manejadorU = ManejadorUsuarios.getInstance();
		DTUsuario usuarioDT = manejadorU.obtenerUsuario(nicknameEmpresa);
		if (!(usuarioDT instanceof DTEmpresa))
			throw new UsuarioNoEsEmpresaException(
					"El usuario con el nickname " + nicknameEmpresa + " no es una empresa.");
		return coleccionOfertasLaborales.values().stream()
				.filter(oferta -> oferta.getEmpresa().getNickname().equals(nicknameEmpresa))
				.filter(oferta -> oferta.tienePublicacionVigente()).map(OfertaLaboral::toDataType)
				.collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(DTOferta::getNombre))));
	}

	/**
	 * Devuelve la cantidad de ofertas laborales actualmente en el sistema.
	 */
	public int getCantidadOfertas() {
		return this.coleccionOfertasLaborales.size();
	}

	/**
	 * Sustituye la coleccion de ofertas laborales por una vacia.
	 */
	public void limpiarColeccionOfertasLaborales() {
		this.coleccionOfertasLaborales = new TreeMap<String, OfertaLaboral>();
	}

	/**
	 * Sustituye la coleccion de keywords por una vacia.
	 */
	public void limpiarColeccionKeywords() {
		this.coleccionKeyword = new HashMap<String, Keyword>();
	}
	
	public List<DTPostulacion> obtenerPostulacionesPorPostulante(String nicknamePostulante)
            throws NicknameNoExisteException, UsuarioNoEsPostulanteException {
        ManejadorUsuarios manejadorU = ManejadorUsuarios.getInstance();
        DTUsuario usuario = manejadorU.obtenerUsuario(nicknamePostulante);

        // Verificar si el usuario es un postulante
        if (!(usuario instanceof DTPostulante))
            throw new UsuarioNoEsPostulanteException(
                    "El usuario con nickname " + nicknamePostulante + " no es un postulante.");

        List<DTPostulacion> postulaciones = new ArrayList<>();
        for (OfertaLaboral oferta : coleccionOfertasLaborales.values()) {
            for (DTPostulacion postulacion : oferta.getPostulaciones()) {
                if (postulacion.getNicknamePostulante().equals(nicknamePostulante)) {
                    postulaciones.add(postulacion);
                }
            }
        }

        return postulaciones;
    }
	
	public TreeSet<DTOferta> obtenerOfertasIngresadasDeEmpresa(String nicknameEmpresa) throws NicknameNoExisteException, UsuarioNoEsEmpresaException {
	    ManejadorUsuarios manejadorU = ManejadorUsuarios.getInstance();
	    DTUsuario usuarioDT = manejadorU.obtenerUsuario(nicknameEmpresa);
	    if (!(usuarioDT instanceof DTEmpresa))
	        throw new UsuarioNoEsEmpresaException("El usuario con el nickname " + nicknameEmpresa + " no es una empresa.");
	    return coleccionOfertasLaborales
	            .values()
	            .stream()
	            .filter(oferta -> oferta.getEmpresa().getNickname().equals(nicknameEmpresa))
	            .filter(oferta -> oferta.getEstado().equals(EnumEstadoOferta.INGRESADA))
	            .map(OfertaLaboral::toDataType)
	            .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(DTOferta::getNombre))));
	}

	
	
	public DTPostulacion estaPostuladoAOfertaLaboral(String nicknameUsuario, String nombreOfertaLaboral)
            throws OfertaNoExisteException, NicknameNoExisteException, UsuarioNoEsPostulanteException {
        ManejadorUsuarios manejadorU = ManejadorUsuarios.getInstance();
        DTUsuario usuario = manejadorU.obtenerUsuario(nicknameUsuario);
        if (usuario instanceof DTPostulante) {
        	  OfertaLaboral oferta = coleccionOfertasLaborales.get(nombreOfertaLaboral);
              if (oferta != null){
            	  for (DTPostulacion postulacion : oferta.getPostulaciones()) {
                      if (postulacion.getNicknamePostulante().equals(nicknameUsuario)) {
                    	  return postulacion; 
                      }
                  }
              }
        }
        return null;
    }
	
	public void cambiarEstadoOferta(EnumEstadoOferta estado, String nombreOferta) throws OfertaNoExisteException {
		OfertaLaboral oferta = this.coleccionOfertasLaborales.get(nombreOferta);
		oferta.setEstado(estado);
	}
	
	public void agregarEliminarFavorito(Postulante postulante, String nombreOferta) {
		OfertaLaboral oferta = this.coleccionOfertasLaborales.get(nombreOferta);
		if (!oferta.checkFav(postulante)) {
			oferta.asociarFavorito(postulante);
		} else {
			oferta.desasociarPostulante(postulante);
		}
	}
	
	public void finalizarOferta(Empresa empresa, String nombreOferta) {
		OfertaLaboral oferta = this.coleccionOfertasLaborales.get(nombreOferta);
		System.out.println(oferta.getEstado());

		if (oferta.getEmpresa().equals(empresa.toDataType())) {
			oferta.setEstado(EnumEstadoOferta.FINALIZADA);
			System.out.println(oferta.getEstado());
		}
	}
	
	public void seleccionarPostulaciones(String nombreOferta, String postulaciones) {
		OfertaLaboral oferta = this.coleccionOfertasLaborales.get(nombreOferta);
		int index = 1;
		String[] postulacionesArray = postulaciones.split(",");
		for (String nick: postulacionesArray) {
			Postulacion post = oferta.getPostulacionNickname(nick);
			post.setResultado(index);
			index ++;
		}
	}

}
