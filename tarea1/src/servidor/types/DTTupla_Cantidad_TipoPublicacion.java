package servidor.types;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;


@XmlAccessorType(XmlAccessType.FIELD)
public class DTTupla_Cantidad_TipoPublicacion {
	private Integer cantidad;
	private String tipoPublicacion;

	public DTTupla_Cantidad_TipoPublicacion(Integer cantidad, String tipo) {
		this.setCantidad(cantidad);
		this.setTipoPublicacion(tipo);
	}

	// getters
	public Integer getCantidad() {
		return cantidad;
	}

	public String getTipoPublicacion() {
		return tipoPublicacion;
	}

	public void setCantidad(Integer cant) {
		this.cantidad = cant;
	}

	public void setTipoPublicacion(String tipoP) {
		this.tipoPublicacion = tipoP;
	}

}
