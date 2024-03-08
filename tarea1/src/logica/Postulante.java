package logica;

import servidor.types.DTPostulante;
import servidor.types.DTPostulacion;

import java.time.LocalDate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Representa al postulante en el sistema.
 * Tiene nickname, nombre, apellido, correo, fechaNacimiento, nacionalidad y un HashMap<String, Postulacion> postulaciones.
 * El campo postulaciones contiene las postulaciones asociadas al postulante y usa como key el nombre de la oferta laboral asociada a cada postulacion.
 * Clase hija de Usuario.
 *
 */
public class Postulante extends Usuario{

	private LocalDate fechaNacimiento;
	private String nacionalidad;
	private Map<String, Postulacion> postulaciones;
	
	// constructores
	public Postulante() {
		super();
		this.setFechaNacimiento(LocalDate.now());
		this.setNacionalidad(new String());
		this.postulaciones = new HashMap<String, Postulacion>();
	}
	
	public Postulante(String nickname, String nombre, String apellido, String correo, String contrasenia ,LocalDate fechaNacimiento, String nacionalidad,String url_imagen) {
		super(nickname, nombre, apellido, correo, contrasenia,url_imagen);
		this.setFechaNacimiento(fechaNacimiento);
		this.setNacionalidad(nacionalidad);
		this.postulaciones = new HashMap<String, Postulacion>();
	}
	
	// getters
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public String getNacionalidad() {
		return nacionalidad;
	}
	
	public List<DTPostulacion> getPostulaciones() {
		return postulaciones.values()
				.stream()
				.map(Postulacion::toDataType)
				.collect(Collectors.toList());
	}
	
	// setters
	void setFechaNacimiento(LocalDate fechaNacimiento2) {
		this.fechaNacimiento = fechaNacimiento2;
	}
	
	void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
	/**
	 * Asocia la postulacion al postulante y usa el nombre de la oferta asociada a la postulacion como la key en el mapa de postulaciones.
	 */
	public void asociarPostulacion(Postulacion postulacion, String nombreOferta) {
		this.postulaciones.put(nombreOferta, postulacion);
	}
	
	/**
	 * Retorna los datos del usuario como un datatype DTPostulante.
	 */
	@Override
	public DTPostulante toDataType() {
		DTPostulante dtt = new DTPostulante(getNickname(), getNombre(), getApellido(), getCorreo(), getContrasenia(), getFechaNacimiento(), getNacionalidad(), getPostulaciones(), getUrlImagen());
		return dtt;
	}

	
}

