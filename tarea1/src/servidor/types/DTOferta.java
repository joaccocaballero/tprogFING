package servidor.types;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import logica.Postulante;


@XmlAccessorType(XmlAccessType.FIELD)
public class DTOferta {

	 private String nombre;
	    private String descripcion;
	    private String ciudad;
	    private String departamento;
	    private String horario;
	    private String remuneracion;
	    @XmlJavaTypeAdapter(LocalDateAdapter.class)
	    private LocalDate fechaAlta;
	    private EnumEstadoOferta estado;
	    private String urlImagen; // He cambiado 'imagen' por 'urlImagen' para ser consistente con OfertaLaboral.
	    private String formaPago;
	    private String paqueteSeleccionado;
	    private List<DTPostulacion> postulaciones;
	    private List<DTPostulante> faveados;
	    private List<String> keywords;
	    private String nicknameEmpresa;

	    // Constructores
	    public DTOferta() {
	        this.setNombre(new String());
	        this.setDescripcion(new String());
	        this.setCiudad(new String());
	        this.setDepartamento(new String());
	        this.setHorario(new String());
	        this.setRemuneracion(new String());
	        this.setFechaAlta(fechaAlta);
	        this.setEstado(estado);
	        this.setUrlImagen(urlImagen);
	        this.setPostulacion(new ArrayList<DTPostulacion>());
	        this.setNicknameEmpresa(new String());
	        this.setFaveados(new ArrayList<DTPostulante>());
	        this.setKeywords(new ArrayList<String>());
	    }

	    // Constructor SIN lista de postulaciones asociadas a la oferta laboral.
	    public DTOferta(String nombre, String descripcion, String ciudad, String departamento, String horario, 
	                    String remuneracion, LocalDate fechaAlta, EnumEstadoOferta estado, 
	                    String nicknameEmpresa, List<String> keywords) {
	        this.setNombre(nombre);
	        this.setDescripcion(descripcion);
	        this.setCiudad(ciudad);
	        this.setDepartamento(departamento);
	        this.setHorario(horario);
	        this.setRemuneracion(remuneracion);
	        this.setFechaAlta(fechaAlta);
	        this.setEstado(estado);
	        this.setPostulacion(null);
	        this.setFaveados(new ArrayList<DTPostulante>());
	        this.setNicknameEmpresa(nicknameEmpresa);
	        this.setKeywords(keywords);
	    }

	    // Constructor CON lista de postulaciones asociadas a la oferta laboral.
	    public DTOferta(String nombre, String descripcion, String ciudad, String departamento, String horario, 
	                    String remuneracion, LocalDate fechaAlta, EnumEstadoOferta estado,
	                    List<DTPostulacion> postulaciones, String nicknameEmpresa, List<DTPostulante> faveados, List<String> keywords) {
	        this.setNombre(nombre);
	        this.setDescripcion(descripcion);
	        this.setCiudad(ciudad);
	        this.setDepartamento(departamento);
	        this.setHorario(horario);
	        this.setRemuneracion(remuneracion);
	        this.setFechaAlta(fechaAlta);
	        this.setEstado(estado);
	        this.setPostulacion(postulaciones);
	        this.setNicknameEmpresa(nicknameEmpresa);
	        this.setFaveados(faveados);
	        this.setKeywords(keywords);
	    }

	// getters
	    
	public String toString() {
		return nombre;
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

	public List<DTPostulacion> getPostulaciones() {
		return postulaciones;
	}
	
	public List<DTPostulante> getFaveados() {
		return faveados;
	}

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public String getNicknameEmpresa() {
		return nicknameEmpresa;
	}

	public List<String> getKeywords() {
		return keywords;
	}
	
	public EnumEstadoOferta getEstado() {
		return estado;
	}

	
	public String getUrlImagen() {
	    return urlImagen;
	}
	
	public String getPaqueteSeleccionado() {
	    return paqueteSeleccionado;
	}
	
	public String getFormaPago() {
	    return formaPago;
	}

	// setters
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public void setRemuneracion(String remuneracion) {
		this.remuneracion = remuneracion;
	}

	public void setPostulacion(List<DTPostulacion> postulaciones) {
		this.postulaciones = postulaciones;
	}
	
	public void setFaveados(List<DTPostulante> faveados) {
		this.faveados = faveados;
	}

	public void setFechaAlta(LocalDate fecha) {
		this.fechaAlta = fecha;
	}

	public void setNicknameEmpresa(String nicknameEmpresa) {
		this.nicknameEmpresa = nicknameEmpresa;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}
	
	public void setEstado (EnumEstadoOferta estado) {
		this.estado = estado;
	}
	
	public void setUrlImagen(String urlImagen) {
	    this.urlImagen = urlImagen;
	}
	
	public void setPaqueteSeleccionado(String paqueteSeleccionado) {
	    this.paqueteSeleccionado = paqueteSeleccionado;
	}
	
	public void setFormaPago(String formaPago) {
	    this.formaPago = formaPago;
	}
	
	public Boolean checkFavString(String postulante) {
		boolean encontrado = false;
		for (DTPostulante post : faveados) {
		    if (post.getNickname().equals(postulante)) {
		        encontrado = true;
		        break;
		    }
		}
		return encontrado;
	}

}
