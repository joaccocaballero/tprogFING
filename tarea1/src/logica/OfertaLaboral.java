package logica;

import servidor.types.DTOferta;
import servidor.types.DTPostulacion;
import servidor.types.DTPostulante;
import servidor.types.DTUsuario;
import servidor.types.EnumEstadoOferta;

import java.time.LocalDate;
//import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OfertaLaboral {
	
	private String nombre;
	private String descripcion;
	private String ciudad;
	private String departamento;
	private String horario;
	private String remuneracion;
	private LocalDate fechaAlta;
	private EnumEstadoOferta estado;
	private String formaPago;
	private String urlImagen;
	private String paqueteSeleccionado;
	private Usuario empresa;
	private List<Postulacion> postulaciones = new ArrayList<Postulacion>();
	private List<Publicacion> publicaciones = new ArrayList<Publicacion>();
	private List<Postulante> faveados = new ArrayList<Postulante>();
	private List<Keyword> keywords = new ArrayList<Keyword>();
	
	
	// constructores
	public OfertaLaboral() {
		this.ciudad = new String();
		this.departamento = new String();
		this.descripcion = new String();
		this.fechaAlta = null;
		this.estado = null;
		this.urlImagen = null;
		this.horario = new String();
		this.nombre = new String();
		this.remuneracion = new String();
		this.empresa = new Empresa();
	}
	
	//constructor basico
	public OfertaLaboral(String n, String desc, String c, String dep, String hora, LocalDate fecha,EnumEstadoOferta estado, String remuneracion, Usuario emp) {
		this.setCiudad(c);
		this.setDepartamento(dep);
		this.setDescripcion(desc);
		this.setFechaAlta(fecha);
		this.setEstado(estado);
		this.setHorario(hora);
		this.setNombre(n);
		this.setRemuneracion(remuneracion);
		this.setEmpresa(emp);
	}
	//constructor con formaPago
	public OfertaLaboral(String n, String desc, String c, String dep, String hora, LocalDate fecha,EnumEstadoOferta estado, String remuneracion, Usuario emp, String formaPago) {
		this.setCiudad(c);
		this.setDepartamento(dep);
		this.setDescripcion(desc);
		this.setFechaAlta(fecha);
		this.setHorario(hora);
		this.setEstado(estado);
		this.setNombre(n);
		this.setRemuneracion(remuneracion);
		this.setEmpresa(emp);
		this.setFormaPago(formaPago);
	}
	
	//constructor con URL
	public OfertaLaboral(String n, String desc, String c, String dep, String hora, LocalDate fecha,EnumEstadoOferta estado, String remuneracion, String urlImagen, Usuario emp, String formaPago) {
		this(n, desc, c, dep, hora, fecha, estado, remuneracion, emp, formaPago);
		this.setUrlImagen(urlImagen);
	}
	//constructor con paquete
	public OfertaLaboral(String n, String desc, String c, String dep, String hora, LocalDate fecha,EnumEstadoOferta estado, String remuneracion, Usuario emp,String formaPago, String paqueteSeleccionado) {
		this(n, desc, c, dep, hora, fecha,estado, remuneracion, emp, formaPago);
		this.setPaqueteSeleccionado(paqueteSeleccionado);
	}
	
	//constructor con URL y paquete
	public OfertaLaboral(String n, String desc, String c, String dep, String hora, LocalDate fecha,EnumEstadoOferta estado, String remuneracion, Usuario emp, String formaPago, String urlImagen, String paqueteSeleccionado) {
	    this(n, desc, c, dep, hora, fecha,estado, remuneracion, emp, formaPago);
	    this.setUrlImagen(urlImagen);
	    this.setPaqueteSeleccionado(paqueteSeleccionado);
	}
	
	public void addPublicacion(Publicacion pub) {
        publicaciones.add(pub);
    }
	
	public void addKeyword(Keyword keyword) {
        keywords.add(keyword);
    }
	
	public String getNombre() {
		return nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public String getCiudad() {
		return ciudad;
	}
	public String getDepartamento() {
		return departamento;
	}
	public String getHorario() {
		return horario;
	}
	public String getRemuneracion() {
		return remuneracion;
	}
	public LocalDate getFechaAlta() {
		return fechaAlta;
	}
	public EnumEstadoOferta getEstado() {
		return estado;
	}

	public List<Keyword> getKeywords(){
		return keywords;
	}
	public DTUsuario getEmpresa() {
		return empresa.toDataType();
	}
	
	public String getFormaPago() {
	    return formaPago;
	}
	
	public String getPaqueteSeleccionado() {
	    return paqueteSeleccionado;
	}
	
	public String getUrlImagen() {
	    return urlImagen;
	}
	
	public List<DTPostulante> getFaveados() {
		if (this.postulaciones.isEmpty())
			return new ArrayList<DTPostulante>();
		return this.faveados
				.stream()
				.map(Postulante::toDataType)
				.collect(Collectors.toList());
	}
	
	/**
	 * Devuelve una lista sin ordenar de tipo DTPostulacion con todas las postulaciones asociadas a la oferta laboral.
	 * Si no hay postulaciones asociadas a la oferta laboral devuelve una lista vacia.
	 */
	public List<DTPostulacion> getPostulaciones() {
		if (this.postulaciones.isEmpty())
			return new ArrayList<DTPostulacion>();
		return this.postulaciones
				.stream()
				.map(Postulacion::toDataType)
				.collect(Collectors.toList());
	}

		
	public void setNombre(String name) {
		this.nombre = name;
	}
	public void setDescripcion(String desc) {
		this.descripcion= desc;
	}
	
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	public void setDepartamento(String depa) {
		this.departamento = depa;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public void setRemuneracion(String rem) {
		this.remuneracion = rem;
	}
	public void setFechaAlta(LocalDate fechaA) {
		this.fechaAlta = fechaA;
	}
	public void setPublicacion(ArrayList<Publicacion> pub) {
		this.publicaciones = pub;
	}
	public void setKeywords(ArrayList<Keyword> keys) {
		this.keywords = keys;
	}
	public void setEmpresa (Usuario empresa) {
		this.empresa = empresa;
	}
	public void setEstado (EnumEstadoOferta estado) {
		this.estado = estado;
	}
	
	public void setFormaPago(String formaPago) {
	    this.formaPago = formaPago;
	}

	public void setPaqueteSeleccionado(String paqueteSeleccionado) {
	    this.paqueteSeleccionado = paqueteSeleccionado;
	}
	
	public void setUrlImagen(String urlImagen) {
	    this.urlImagen = urlImagen;
	}
	
	/**
	 * Añade la postulacion a la coleccion de postulaciones asociadas a la oferta laboral.
	 */
	public void asociarPostulacion(Postulacion postulacion) {
		this.postulaciones.add(postulacion);
	}
	
	public void asociarFavorito(Postulante postulante) {
		this.faveados.add(postulante);
	}
	
	public void desasociarPostulante(Postulante postulante) {
		this.faveados.remove(postulante);
	}
	
	public Boolean checkFav(Postulante postulante) {
		return faveados.contains(postulante);
	}

	public Postulacion getPostulacionNickname(String nickname) {
		for (Postulacion post : postulaciones) {
            if (post.getNicknamePostulante().equals(nickname)) {
                return post; // Persona encontrada
            }
        }
		return null;
	}
	
	/**
	 * Devuelve los datos de la oferta como un datatype DTOferta.
	 */
	public DTOferta toDataType() {
		// creo una lista con las keywords asociadas a la oferta en formato String
		List<String> listaKeywordsString = new ArrayList<String>();
		this.keywords.stream().forEach(keyword -> listaKeywordsString.add(keyword.getNombre()));
		DTOferta dto =  new DTOferta(
				this.getNombre(),
				this.getDescripcion(),
				this.getCiudad(),
				this.getDepartamento(),
				this.getHorario(),
				this.getRemuneracion(),
				this.getFechaAlta(),
				this.getEstado(),
				this.getPostulaciones(),
				this.empresa.getNickname(),
				this.getFaveados(),
				listaKeywordsString);
		
		if(this.getUrlImagen() != null && !this.getUrlImagen().isEmpty()) { 
		    dto.setUrlImagen(this.getUrlImagen()); 
		}

		if(this.getPaqueteSeleccionado() != null && !this.getPaqueteSeleccionado().isEmpty()) { 
		    dto.setPaqueteSeleccionado(this.getPaqueteSeleccionado()); 
		}

		if(this.getFormaPago() != null && !this.getFormaPago().isEmpty()) { 
		    dto.setFormaPago(this.getFormaPago()); 
		}
		
		return dto;
	}
	
	/**
	 * Devuelve true si tiene al menos una publicacion vigente asociada a la oferta laboral.
	 */
	public boolean tienePublicacionVigente() {
		return this.publicaciones
				.stream()
				.anyMatch(publicacion -> publicacion.esVigente());
	}
	
}
