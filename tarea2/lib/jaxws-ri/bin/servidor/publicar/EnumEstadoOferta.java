
package servidor.publicar;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para enumEstadoOferta.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <pre>{@code
 * <simpleType name="enumEstadoOferta">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="INGRESADA"/>
 *     <enumeration value="CONFIRMADA"/>
 *     <enumeration value="RECHAZADA"/>
 *     <enumeration value="FINALIZADA"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "enumEstadoOferta")
@XmlEnum
public enum EnumEstadoOferta {

    INGRESADA,
    CONFIRMADA,
    RECHAZADA,
    FINALIZADA;

    public String value() {
        return name();
    }

    public static EnumEstadoOferta fromValue(String v) {
        return valueOf(v);
    }

}
