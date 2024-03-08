
package servidor.types;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public enum EnumEstadoOferta {
	INGRESADA,
	CONFIRMADA,
	RECHAZADA,
	FINALIZADA
}



