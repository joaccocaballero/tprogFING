package servidor.types;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;



@XmlAccessorType(XmlAccessType.FIELD)
public class DTTipoPublicacion {
	private String nombre;
	private String descripcion;
	private Integer duracionPublicacion;
	private Integer costo;
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	private LocalDate  fechaAlta;
	private int exposicion;
	

	public DTTipoPublicacion(){
		this.nombre = new String();
		this.descripcion = new String();
		this.duracionPublicacion = 0;
		this.costo = 0;
		this.fechaAlta = null;
		this.exposicion = 0;
	}
	
	public DTTipoPublicacion(String nombre, String descripcion, Integer duracionPublicacion, Integer costo, LocalDate alta, int exposicion){
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.duracionPublicacion = duracionPublicacion;
		this.costo = costo;
		this.fechaAlta = alta;
		this.exposicion = exposicion;
	}
	
	
	public String toString() {
		return nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public String getDuracion() {
		return duracionPublicacion.toString();
	}
	
	public Integer getCosto() {
		return costo;
	}
	
	public LocalDate getAlta() {
		return fechaAlta;
	}
	
	public int getExposicion() {
		return exposicion;
	}
	
	
	public void setCosto(Integer costo) {
		this.costo = costo;
	}
	
	public void setDuracion(Integer duracion) {
		this.duracionPublicacion = duracion;
	}
	
	public void setAlta(LocalDate  alta) {
		this.fechaAlta = alta;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void setExposicion(int exposicion) {
		this.exposicion = exposicion;
	}
	
}
