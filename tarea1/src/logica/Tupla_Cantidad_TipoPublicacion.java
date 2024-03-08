package logica;
import servidor.types.DTTupla_Cantidad_TipoPublicacion;

public class Tupla_Cantidad_TipoPublicacion{
	private Integer cantidad;
    private TipoPublicacion tipoPublicacion;

    public Tupla_Cantidad_TipoPublicacion(Integer cantidad, TipoPublicacion tipoPublicacion) {
        this.cantidad = cantidad;
        this.tipoPublicacion = tipoPublicacion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public TipoPublicacion getTipoPublicacion() {
        return tipoPublicacion;
    }
    
	public void setCantidad(Integer cant) {
		this.cantidad = cant;
	}
	
	public void agregarCantidad(Integer cant) {
		this.cantidad = this.cantidad + cant;
	}
	
	public DTTupla_Cantidad_TipoPublicacion toDataType() {
    	return new DTTupla_Cantidad_TipoPublicacion(getCantidad(),getTipoPublicacion().getNombre());
    }

}
