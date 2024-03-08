package logica;

import servidor.types.DTEmpresa;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.time.LocalDateTime;


import excepciones.KeywordExisteException;
import excepciones.NicknameNoExisteException;
import excepciones.NombreExisteException;
import excepciones.OfertaNoExisteException;
import excepciones.UsuarioNoEsEmpresaException;
import excepciones.UsuarioNoEsPostulanteException;
import servidor.types.DTOferta;
import servidor.types.DTPostulacion;
import servidor.types.EnumEstadoOferta;


public interface IControladorOfertas {


	public void altaKeyword(String nombre) throws KeywordExisteException;
	public List<String> obtenerKeywords();
	public void altaOferta(String nombre, String desc, String remuner, String horario, List<String> keywords, String ciudad, String depa, String tipo, String empresa) throws NombreExisteException, KeywordExisteException, NicknameNoExisteException;
	public void altaOfertaWeb(String nombre, String descripcion, String renumeracion, String horario, String ciudad, String departanemto, String tipoPublicacion, String formaPago, String paqueteSeleccionado,EnumEstadoOferta estado, String[] Keywords,String urlImagen, String empresaActual ) throws NicknameNoExisteException, NombreExisteException, KeywordExisteException;
	/**
	 * Devuelve un DTOferta con la informacion de la oferta con el nombre brindado incluyendo sus postulaciones.
	 * Si no existe una oferta con ese nombre en el sistema tira una OfertaNoExisteException.
	 */
	public DTOferta obtenerDatosOferta(String nombreOferta) throws OfertaNoExisteException;
	public List<DTEmpresa> obtenerEmpresas();
	public List<DTOferta> obtenerOfertasLaborales();
	
	
	

	/**
	 * Postula al postulante con nick "nicknamePostulante" a la oferta de nombre "nombreOfertaLaboral".
	 * Si no existe un usuario con ese nick tira una NicknameNoExisteException.
	 * Si el usuario asociado al nick "nicknamePostulante" no es un postulante tira una UsuarioNoEsPostulanteException
	 * Si no existe una oferta con el nombre "nombreOfertaLaboral" tira una OfertaNoExisteException.
	 */
	public void postularAOferta(String nombreOfertaLaboral, String nicknamePostulante, String cvReducido, String motivacion, String fechaPostulacion, String urlVideo) throws NicknameNoExisteException, UsuarioNoEsPostulanteException, OfertaNoExisteException;
	
    /**
	 * Devuelve un set de tipo DTOferta con todas las ofertas vigentes asociadas a la empresa con el nickname "nicknameEmpresa" ordenadas alfabeticamente por el nombre de las ofertas.
	 * Si el nickname no esta asociado a un usuario en el sistema tira una NicknameNoExisteException.
	 * Si existe el usuario con ese nickname pero no es una empresa tira una UsuarioNoEsEmpresaException.
	 * Si no tiene ofertas vigentes asociadas devuelve una lista vacia.
	 */
	public Set<DTOferta> obtenerOfertasVigentesDeEmpresa(String nicknameEmpresa) throws NicknameNoExisteException, UsuarioNoEsEmpresaException;
	
	public void actualizarEstadoOfertaLaboral(String nombreOfertaLaboral, EnumEstadoOferta estado) throws OfertaNoExisteException;

	public List<DTPostulacion> obtenerPostulacionesPorPostulante(String nicknamePostulante)
            throws NicknameNoExisteException, UsuarioNoEsPostulanteException ;
	public DTPostulacion estaPostuladoAOfertaLaboral(String nicknameUsuario, String nombreOfertaLaboral)
            throws OfertaNoExisteException, NicknameNoExisteException, UsuarioNoEsPostulanteException ;
	
	public void confirmarOfertaLaboral(String nombreOferta) throws OfertaNoExisteException ;
	
	public void cambiarEstadoOferta(EnumEstadoOferta estado, String nombreOferta) throws OfertaNoExisteException;
	
	public boolean verificarPertenenciaOferta(String nombreOferta, String nickname) throws OfertaNoExisteException ;
	
	public TreeSet<DTOferta> obtenerOfertasIngresadasDeEmpresa(String nicknameEmpresa)throws NicknameNoExisteException, UsuarioNoEsEmpresaException;
	public void agregarEliminarFavorito(String nickname, String nombreOferta) throws NicknameNoExisteException;
	public void seleccionarPostulaciones(String nombreOferta, String postulaciones);
}
