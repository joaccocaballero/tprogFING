
package servidor.publicar;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the servidor.publicar package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CorreoNoEncontradoException_QNAME = new QName("http://publicar.servidor/", "CorreoNoEncontradoException");
    private final static QName _CorreoRepetidoException_QNAME = new QName("http://publicar.servidor/", "CorreoRepetidoException");
    private final static QName _NicknameNoExisteException_QNAME = new QName("http://publicar.servidor/", "NicknameNoExisteException");
    private final static QName _UsuarioRepetidoException_QNAME = new QName("http://publicar.servidor/", "UsuarioRepetidoException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: servidor.publicar
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CorreoNoEncontradoException }
     * 
     * @return
     *     the new instance of {@link CorreoNoEncontradoException }
     */
    public CorreoNoEncontradoException createCorreoNoEncontradoException() {
        return new CorreoNoEncontradoException();
    }

    /**
     * Create an instance of {@link CorreoRepetidoException }
     * 
     * @return
     *     the new instance of {@link CorreoRepetidoException }
     */
    public CorreoRepetidoException createCorreoRepetidoException() {
        return new CorreoRepetidoException();
    }

    /**
     * Create an instance of {@link NicknameNoExisteException }
     * 
     * @return
     *     the new instance of {@link NicknameNoExisteException }
     */
    public NicknameNoExisteException createNicknameNoExisteException() {
        return new NicknameNoExisteException();
    }

    /**
     * Create an instance of {@link UsuarioRepetidoException }
     * 
     * @return
     *     the new instance of {@link UsuarioRepetidoException }
     */
    public UsuarioRepetidoException createUsuarioRepetidoException() {
        return new UsuarioRepetidoException();
    }

    /**
     * Create an instance of {@link DtUsuario }
     * 
     * @return
     *     the new instance of {@link DtUsuario }
     */
    public DtUsuario createDtUsuario() {
        return new DtUsuario();
    }

    /**
     * Create an instance of {@link DtEmpresa }
     * 
     * @return
     *     the new instance of {@link DtEmpresa }
     */
    public DtEmpresa createDtEmpresa() {
        return new DtEmpresa();
    }

    /**
     * Create an instance of {@link DtOferta }
     * 
     * @return
     *     the new instance of {@link DtOferta }
     */
    public DtOferta createDtOferta() {
        return new DtOferta();
    }

    /**
     * Create an instance of {@link DtPostulacion }
     * 
     * @return
     *     the new instance of {@link DtPostulacion }
     */
    public DtPostulacion createDtPostulacion() {
        return new DtPostulacion();
    }

    /**
     * Create an instance of {@link DtPostulante }
     * 
     * @return
     *     the new instance of {@link DtPostulante }
     */
    public DtPostulante createDtPostulante() {
        return new DtPostulante();
    }

    /**
     * Create an instance of {@link DtEmpresaArray }
     * 
     * @return
     *     the new instance of {@link DtEmpresaArray }
     */
    public DtEmpresaArray createDtEmpresaArray() {
        return new DtEmpresaArray();
    }

    /**
     * Create an instance of {@link DtUsuarioArray }
     * 
     * @return
     *     the new instance of {@link DtUsuarioArray }
     */
    public DtUsuarioArray createDtUsuarioArray() {
        return new DtUsuarioArray();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CorreoNoEncontradoException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CorreoNoEncontradoException }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.servidor/", name = "CorreoNoEncontradoException")
    public JAXBElement<CorreoNoEncontradoException> createCorreoNoEncontradoException(CorreoNoEncontradoException value) {
        return new JAXBElement<>(_CorreoNoEncontradoException_QNAME, CorreoNoEncontradoException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CorreoRepetidoException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CorreoRepetidoException }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.servidor/", name = "CorreoRepetidoException")
    public JAXBElement<CorreoRepetidoException> createCorreoRepetidoException(CorreoRepetidoException value) {
        return new JAXBElement<>(_CorreoRepetidoException_QNAME, CorreoRepetidoException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NicknameNoExisteException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link NicknameNoExisteException }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.servidor/", name = "NicknameNoExisteException")
    public JAXBElement<NicknameNoExisteException> createNicknameNoExisteException(NicknameNoExisteException value) {
        return new JAXBElement<>(_NicknameNoExisteException_QNAME, NicknameNoExisteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsuarioRepetidoException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UsuarioRepetidoException }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.servidor/", name = "UsuarioRepetidoException")
    public JAXBElement<UsuarioRepetidoException> createUsuarioRepetidoException(UsuarioRepetidoException value) {
        return new JAXBElement<>(_UsuarioRepetidoException_QNAME, UsuarioRepetidoException.class, null, value);
    }

}
