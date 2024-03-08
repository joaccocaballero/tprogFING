package logica;

import java.util.*;

import servidor.types.DTUsuario;
import servidor.types.DTEmpresa;
import servidor.types.DTOferta;

/**
 * Representa a la empresa en el sistema.
 * Tiene nombreEmpresa, descripcion, linkWeb y un set ofertasLaborales de tipo DTOferta que contiene todas las ofertas asociadas a la empresa ordenadas por su nombre.
 * Clase hija de Usuario.
 */
public class Empresa extends Usuario {

	private String nombreEmpresa;
	private String descripcion;
	private String linkWeb;
	private Set<DTOferta> ofertasLaborales;
	
	// constructores
	public Empresa() {
		super();
		this.setNombreEmpresa(new String());
		this.setDescripcion(new String());
		this.setLinkWeb(new String());
		this.ofertasLaborales = new TreeSet<>(Comparator.comparing(DTOferta::getNombre));
	}
	
	public Empresa(String nickname, String nombre, String apellido, String correo, String contrasenia ,String nombreEmpresa, String descripcion, String linkWeb, String url_imagen) {
		super(nickname, nombre, apellido, correo, contrasenia, url_imagen);
		this.setNombreEmpresa(nombreEmpresa);
		this.setDescripcion(descripcion);
		this.setLinkWeb(linkWeb);
		this.ofertasLaborales = new TreeSet<>(Comparator.comparing(DTOferta::getNombre));
	}
	
	// getters
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public String getLinkWeb() {
		return linkWeb;
	}
	
	/**
	 * Devuelve un set de DTOferta con todas las ofertas laborales asociadas a la empresa ordenadas segun su nombre.
	 */
	public Set<DTOferta> getOfertas() {
		return this.ofertasLaborales;
	}
	
	// setters
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void setLinkWeb(String linkWeb) {
		this.linkWeb = linkWeb;
	}
	
	/**
	 * Agrega el nombre de la oferta laboral al set ordenado de ofertas laborales asociadas a la empresa.
	 */
	public void asociarOferta(DTOferta oferta) {
		this.ofertasLaborales.add(oferta);
	}
	
	/**
	 * Retorna los datos de la empresa como un datatype DTUsuario excepto el set de ofertas laborales asociadas a la misma.
	 */
	@Override
	public DTUsuario toDataType() {
		return new DTEmpresa(this.getNickname(), this.getNombre(), this.getApellido(), this.getCorreo(), this.getContrasenia() ,this.getNombreEmpresa(), this.getDescripcion(), this.getLinkWeb(), this.getOfertas(), this.getUrlImagen());
	}

}


