
package servidor.publicar;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtOferta complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtOferta">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="ciudad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="departamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="horario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="remuneracion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fechaAlta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="estado" type="{http://publicar.servidor/}enumEstadoOferta" minOccurs="0"/>
 *         <element name="urlImagen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="formaPago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="paqueteSeleccionado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="postulaciones" type="{http://publicar.servidor/}dtPostulacion" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="faveados" type="{http://publicar.servidor/}dtPostulante" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="keywords" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="nicknameEmpresa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtOferta", propOrder = {
    "nombre",
    "descripcion",
    "ciudad",
    "departamento",
    "horario",
    "remuneracion",
    "fechaAlta",
    "estado",
    "urlImagen",
    "formaPago",
    "paqueteSeleccionado",
    "postulaciones",
    "faveados",
    "keywords",
    "nicknameEmpresa"
})
public class DtOferta {

    protected String nombre;
    protected String descripcion;
    protected String ciudad;
    protected String departamento;
    protected String horario;
    protected String remuneracion;
    protected String fechaAlta;
    @XmlSchemaType(name = "string")
    protected EnumEstadoOferta estado;
    protected String urlImagen;
    protected String formaPago;
    protected String paqueteSeleccionado;
    @XmlElement(nillable = true)
    protected List<DtPostulacion> postulaciones;
    @XmlElement(nillable = true)
    protected List<DtPostulante> faveados;
    @XmlElement(nillable = true)
    protected List<String> keywords;
    protected String nicknameEmpresa;

    /**
     * Obtiene el valor de la propiedad nombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el valor de la propiedad nombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Define el valor de la propiedad descripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad ciudad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Define el valor de la propiedad ciudad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCiudad(String value) {
        this.ciudad = value;
    }

    /**
     * Obtiene el valor de la propiedad departamento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * Define el valor de la propiedad departamento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartamento(String value) {
        this.departamento = value;
    }

    /**
     * Obtiene el valor de la propiedad horario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHorario() {
        return horario;
    }

    /**
     * Define el valor de la propiedad horario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHorario(String value) {
        this.horario = value;
    }

    /**
     * Obtiene el valor de la propiedad remuneracion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemuneracion() {
        return remuneracion;
    }

    /**
     * Define el valor de la propiedad remuneracion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemuneracion(String value) {
        this.remuneracion = value;
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
     * Obtiene el valor de la propiedad estado.
     * 
     * @return
     *     possible object is
     *     {@link EnumEstadoOferta }
     *     
     */
    public EnumEstadoOferta getEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumEstadoOferta }
     *     
     */
    public void setEstado(EnumEstadoOferta value) {
        this.estado = value;
    }

    /**
     * Obtiene el valor de la propiedad urlImagen.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlImagen() {
        return urlImagen;
    }

    /**
     * Define el valor de la propiedad urlImagen.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlImagen(String value) {
        this.urlImagen = value;
    }

    /**
     * Obtiene el valor de la propiedad formaPago.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormaPago() {
        return formaPago;
    }

    /**
     * Define el valor de la propiedad formaPago.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormaPago(String value) {
        this.formaPago = value;
    }

    /**
     * Obtiene el valor de la propiedad paqueteSeleccionado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaqueteSeleccionado() {
        return paqueteSeleccionado;
    }

    /**
     * Define el valor de la propiedad paqueteSeleccionado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaqueteSeleccionado(String value) {
        this.paqueteSeleccionado = value;
    }

    /**
     * Gets the value of the postulaciones property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the postulaciones property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPostulaciones().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtPostulacion }
     * 
     * 
     * @return
     *     The value of the postulaciones property.
     */
    public List<DtPostulacion> getPostulaciones() {
        if (postulaciones == null) {
            postulaciones = new ArrayList<>();
        }
        return this.postulaciones;
    }

    /**
     * Gets the value of the faveados property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the faveados property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFaveados().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtPostulante }
     * 
     * 
     * @return
     *     The value of the faveados property.
     */
    public List<DtPostulante> getFaveados() {
        if (faveados == null) {
            faveados = new ArrayList<>();
        }
        return this.faveados;
    }

    /**
     * Gets the value of the keywords property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the keywords property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKeywords().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     * @return
     *     The value of the keywords property.
     */
    public List<String> getKeywords() {
        if (keywords == null) {
            keywords = new ArrayList<>();
        }
        return this.keywords;
    }

    /**
     * Obtiene el valor de la propiedad nicknameEmpresa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNicknameEmpresa() {
        return nicknameEmpresa;
    }

    /**
     * Define el valor de la propiedad nicknameEmpresa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNicknameEmpresa(String value) {
        this.nicknameEmpresa = value;
    }

}
