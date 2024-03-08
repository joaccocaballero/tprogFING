package logica;

import servidor.types.DTPublicacion;
import java.time.LocalDate;

public class Publicacion {
	private Integer id;
	private Integer costoAsociado;
	private LocalDate  fechaAlta;
	private LocalDate  fechaVencimiento;
	private OfertaLaboral oferta;
	private TipoPublicacion tipo;

	public Publicacion(){
		this.setCosto(null);
		this.setAlta(null);
		this.setFin(null);
		this.setId(null);
		this.setOferta(null);
		this.setTipo(null);
	}
	
	public Publicacion(Integer id, Integer costo, LocalDate  alta, LocalDate  fin, OfertaLaboral oferta, TipoPublicacion tipo){
		this.costoAsociado = costo;
		this.fechaAlta = alta;
		this.fechaVencimiento = fin;
		this.id = id;
		this.oferta = oferta;
		this.tipo = tipo;
	}
	
	public Integer getCosto() {
		return costoAsociado;
	}
	
	public OfertaLaboral getOferta() {
		return oferta;
	}
	
	public LocalDate  getAlta() {
		return fechaAlta;
	}
	
	public Integer getId() {
		return id;
	}
	
	public LocalDate  getFin() {
		return fechaVencimiento;
	}
	
	public TipoPublicacion getTipo() {
		return tipo;
	}
	
	public void setCosto(Integer costo) {
		this.costoAsociado=costo;
	}
	
	public void setOferta(OfertaLaboral oferta) {
		this.oferta=oferta;
	}
	
	public void setAlta(LocalDate  alta) {
		this.fechaAlta=alta;
	}
	
	public void setId(Integer id) {
		this.id=id;
	}
	
	public void setFin(LocalDate  fin) {
		this.fechaVencimiento=fin;
	}
	
	public void setTipo(TipoPublicacion tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * Devuelve true si la oferta es vigente usando la fecha y hora actual del sistema.
	 * 
	 */
	public boolean esVigente() {
		LocalDate fechaActual = LocalDate.now();
		return fechaActual.isBefore(fechaVencimiento);
	}
	
	public DTPublicacion toDatatype() {
	        DTPublicacion dtPublicacion = new DTPublicacion();
	        dtPublicacion.setId(this.id);
	        dtPublicacion.setCostoAsociado(this.costoAsociado);
	        dtPublicacion.setFechaAlta(this.fechaAlta);
	        dtPublicacion.setFechaVencimiento(this.fechaVencimiento);
	        
	        // Suponiendo que OfertaLaboral tiene un método toDatatype que devuelve un DTOferta
	        if (this.oferta != null) {
	            dtPublicacion.setDtOferta(this.oferta.toDataType());
	        }
	        if( this.tipo != null) {
	        	dtPublicacion.setDtTipo(this.tipo.toDataType());
	        }	        
	        return dtPublicacion;
    }
	
}
