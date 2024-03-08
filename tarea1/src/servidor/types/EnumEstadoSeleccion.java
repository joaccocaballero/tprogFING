package servidor.types;

/**
 * Enum para representar el estado de la Postulacion de un Postulante a una OfertaLaboral.
 *
 */
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;


@XmlAccessorType(XmlAccessType.FIELD)
public enum EnumEstadoSeleccion {
	ACEPTADO,
	PENDIENTE,
	RECHAZADO
}
