
package servidor.publicar;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtPublicacion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtPublicacion">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         <element name="costoAsociado" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         <element name="fechaAlta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fechaVencimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="dtOferta" type="{http://publicar.servidor/}dtOferta" minOccurs="0"/>
 *         <element name="dtTipo" type="{http://publicar.servidor/}dtTipoPublicacion" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtPublicacion", propOrder = {
    "id",
    "costoAsociado",
    "fechaAlta",
    "fechaVencimiento",
    "dtOferta",
    "dtTipo"
})
public class DtPublicacion {

    protected Integer id;
    protected Integer costoAsociado;
    protected String fechaAlta;
    protected String fechaVencimiento;
    protected DtOferta dtOferta;
    protected DtTipoPublicacion dtTipo;

    /**
     * Obtiene el valor de la propiedad id.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getId() {
        return id;
    }

    /**
     * Define el valor de la propiedad id.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setId(Integer value) {
        this.id = value;
    }

    /**
     * Obtiene el valor de la propiedad costoAsociado.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCostoAsociado() {
        return costoAsociado;
    }

    /**
     * Define el valor de la propiedad costoAsociado.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCostoAsociado(Integer value) {
        this.costoAsociado = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaAlta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaAlta() {
        return fechaAlta;
    }

    /**
     * Define el valor de la propiedad fechaAlta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaAlta(String value) {
        this.fechaAlta = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaVencimiento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * Define el valor de la propiedad fechaVencimiento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaVencimiento(String value) {
        this.fechaVencimiento = value;
    }

    /**
     * Obtiene el valor de la propiedad dtOferta.
     * 
     * @return
     *     possible object is
     *     {@link DtOferta }
     *     
     */
    public DtOferta getDtOferta() {
        return dtOferta;
    }

    /**
     * Define el valor de la propiedad dtOferta.
     * 
     * @param value
     *     allowed object is
     *     {@link DtOferta }
     *     
     */
    public void setDtOferta(DtOferta value) {
        this.dtOferta = value;
    }

    /**
     * Obtiene el valor de la propiedad dtTipo.
     * 
     * @return
     *     possible object is
     *     {@link DtTipoPublicacion }
     *     
     */
    public DtTipoPublicacion getDtTipo() {
        return dtTipo;
    }

    /**
     * Define el valor de la propiedad dtTipo.
     * 
     * @param value
     *     allowed object is
     *     {@link DtTipoPublicacion }
     *     
     */
    public void setDtTipo(DtTipoPublicacion value) {
        this.dtTipo = value;
    }

}
