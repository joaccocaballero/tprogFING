package logica;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import servidor.types.DTPaquete;




public class Paquete {
	private String nombre;
	private String descripcion;
	private Integer validez;
	private Integer descuento;
	private LocalDate fechaAlta;
	private double costoAsociado;
	private String url_imagen;
	private List<Tupla_Cantidad_TipoPublicacion> listaDeTuplas = new ArrayList<>();
	
	public Paquete() {
		this.setNombre(new String());
		this.setDescripcion(new String());
		this.setValidez(0);
		this.setDescuento(0);
		this.setCostoAsociado(0);
		this.setListaDeTuplas(null);
		this.setUrlImagen("");
	}
	
	public Paquete(String nombre, String descripcion, Integer validez, Integer descuento, double costoAsociado, LocalDate fechaAlta,String url_imagen) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.validez = validez;
		this.descuento = descuento;
		this.costoAsociado = costoAsociado;
		this.fechaAlta = fechaAlta;
		this.url_imagen = url_imagen;
	}
	
    // Getters
    public String getNombre() {
        return nombre;
    }
    public String getUrlImagen() {
        return url_imagen;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public Integer getValidez() {
        return validez;
    }

    public Integer getDescuento() {
        return descuento;
    }

    public double getCostoAsociado() {
    	for(Tupla_Cantidad_TipoPublicacion item : listaDeTuplas) {
    		this.costoAsociado = this.costoAsociado + item.getCantidad() * item.getTipoPublicacion().getCosto();
    	}
    	if(getDescuento()> 0) {
    		double aDescontar = this.costoAsociado*getDescuento()/100.0;
    		return this.costoAsociado-aDescontar;
    	}
    	else {
    		return this.costoAsociado;    		
    	}
    }
    public LocalDate getFechaAlta() {
    	return fechaAlta;
    }
    public List<Tupla_Cantidad_TipoPublicacion> getListaDeTuplas(){
    	return listaDeTuplas;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setUrlImagen(String urlImagen) {
        this.url_imagen = urlImagen;
    }


    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setValidez(Integer validez) {
        this.validez = validez;
    }

    public void setDescuento(Integer descuento) {
        this.descuento = descuento;
    }

    public void setCostoAsociado(double costoAsociado) {
        this.costoAsociado = costoAsociado;
    }
    
    public void setFechaAlta(LocalDate feachaA) {
    	this.fechaAlta = feachaA;
    }
    
    public void setListaDeTuplas(List<Tupla_Cantidad_TipoPublicacion> listTuplas) {
    	this.listaDeTuplas = listTuplas;
    	
    }
    
    public void agregarTipoPublicacion(Integer cant, TipoPublicacion tipoPublicacion) {
    	 boolean encontrado = false;
    	    for (Tupla_Cantidad_TipoPublicacion tupla : listaDeTuplas) {
    	        if (tupla.getTipoPublicacion().getNombre().equals(tipoPublicacion.getNombre())) {
    	            tupla.agregarCantidad(cant);
    	            encontrado = true;
    	            break;
    	        }
    	    }
    	    if (!encontrado) {
    	        Tupla_Cantidad_TipoPublicacion nuevaTupla = new Tupla_Cantidad_TipoPublicacion(cant, tipoPublicacion);
    	        listaDeTuplas.add(nuevaTupla);
    	    }
    }

    /**
     * Retorna los datos del usuario como un DataType DTPaquete.
     */
    DTPaquete toDataType() {
    	return new DTPaquete(getNombre(), getDescripcion(), getValidez(),getDescuento(), getCostoAsociado(), getFechaAlta(), getListaDeTuplas(),getUrlImagen());
    }

}
