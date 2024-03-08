package servidor.types;

import java.util.Set;

/**
 * DataType para transportar la información de una empresa entre capa lógica y
 * de presentación. Contiene nickname, nombre, apellido, correo, nombreEmpresa,
 * descripcion, linkWeb y ofertas que es un set de DTOferta ordenado
 * alfabeticamente por el nombre de las ofertas. Clase hija de DTUsuario.
 */
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;


@XmlAccessorType(XmlAccessType.FIELD)
public class DTEmpresa extends DTUsuario {

	private String nombreEmpresa;
	private String descripcion;
	private String linkWeb;
	private Set<DTOferta> ofertas;

	// constructores
	public DTEmpresa(String nickname, String nombre, String apellido, String correo, String contrasenia,
			String nombreEmpresa, String descripcion, String linkWeb, Set<DTOferta> ofertas, String url_imagen) {
		super(nickname, nombre, apellido, correo, contrasenia, url_imagen);
		this.setNombreEmpresa(nombreEmpresa);
		this.setDescripcion(descripcion);
		this.setLinkWeb(linkWeb);
		this.setOfertas(ofertas);
	}

	// getters
	public String toString() {
		return nombreEmpresa;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getLinkWeb() {
		return linkWeb;
	}

	public Set<DTOferta> getOfertas() {
		return ofertas;
	}

	// setters
	private void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	private void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	private void setLinkWeb(String linkWeb) {
		this.linkWeb = linkWeb;
	}

	private void setOfertas(Set<DTOferta> ofertas) {
		this.ofertas = ofertas;
	}

}
