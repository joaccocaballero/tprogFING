
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

    private final static QName _KeywordExisteException_QNAME = new QName("http://publicar.servidor/", "KeywordExisteException");
    private final static QName _NicknameNoExisteException_QNAME = new QName("http://publicar.servidor/", "NicknameNoExisteException");
    private final static QName _NombreExisteException_QNAME = new QName("http://publicar.servidor/", "NombreExisteException");
    private final static QName _OfertaNoExisteException_QNAME = new QName("http://publicar.servidor/", "OfertaNoExisteException");
    private final static QName _UsuarioNoEsEmpresaException_QNAME = new QName("http://publicar.servidor/", "UsuarioNoEsEmpresaException");
    private final static QName _UsuarioNoEsPostulanteException_QNAME = new QName("http://publicar.servidor/", "UsuarioNoEsPostulanteException");
    private final static QName _DtPublicacion_QNAME = new QName("http://publicar.servidor/", "dtPublicacion");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: servidor.publicar
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link KeywordExisteException }
     * 
     * @return
     *     the new instance of {@link KeywordExisteException }
     */
    public KeywordExisteException createKeywordExisteException() {
        return new KeywordExisteException();
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
     * Create an instance of {@link NombreExisteException }
     * 
     * @return
     *     the new instance of {@link NombreExisteException }
     */
    public NombreExisteException createNombreExisteException() {
        return new NombreExisteException();
    }

    /**
     * Create an instance of {@link OfertaNoExisteException }
     * 
     * @return
     *     the new instance of {@link OfertaNoExisteException }
     */
    public OfertaNoExisteException createOfertaNoExisteException() {
        return new OfertaNoExisteException();
    }

    /**
     * Create an instance of {@link UsuarioNoEsEmpresaException }
     * 
     * @return
     *     the new instance of {@link UsuarioNoEsEmpresaException }
     */
    public UsuarioNoEsEmpresaException createUsuarioNoEsEmpresaException() {
        return new UsuarioNoEsEmpresaException();
    }

    /**
     * Create an instance of {@link UsuarioNoEsPostulanteException }
     * 
     * @return
     *     the new instance of {@link UsuarioNoEsPostulanteException }
     */
    public UsuarioNoEsPostulanteException createUsuarioNoEsPostulanteException() {
        return new UsuarioNoEsPostulanteException();
    }

    /**
     * Create an instance of {@link DtPublicacion }
     * 
     * @return
     *     the new instance of {@link DtPublicacion }
     */
    public DtPublicacion createDtPublicacion() {
        return new DtPublicacion();
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
     * Create an instance of {@link DtUsuario }
     * 
     * @return
     *     the new instance of {@link DtUsuario }
     */
    public DtUsuario createDtUsuario() {
        return new DtUsuario();
    }

    /**
     * Create an instance of {@link DtTipoPublicacion }
     * 
     * @return
     *     the new instance of {@link DtTipoPublicacion }
     */
    public DtTipoPublicacion createDtTipoPublicacion() {
        return new DtTipoPublicacion();
    }

    /**
     * Create an instance of {@link ArrayList }
     * 
     * @return
     *     the new instance of {@link ArrayList }
     */
    public ArrayList createArrayList() {
        return new ArrayList();
    }

    /**
     * Create an instance of {@link DtPublicacionArray }
     * 
     * @return
     *     the new instance of {@link DtPublicacionArray }
     */
    public DtPublicacionArray createDtPublicacionArray() {
        return new DtPublicacionArray();
    }

    /**
     * Create an instance of {@link DtOfertaArray }
     * 
     * @return
     *     the new instance of {@link DtOfertaArray }
     */
    public DtOfertaArray createDtOfertaArray() {
        return new DtOfertaArray();
    }

    /**
     * Create an instance of {@link DtTipoPublicacionArray }
     * 
     * @return
     *     the new instance of {@link DtTipoPublicacionArray }
     */
    public DtTipoPublicacionArray createDtTipoPublicacionArray() {
        return new DtTipoPublicacionArray();
    }

    /**
     * Create an instance of {@link DtPostulacionArray }
     * 
     * @return
     *     the new instance of {@link DtPostulacionArray }
     */
    public DtPostulacionArray createDtPostulacionArray() {
        return new DtPostulacionArray();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link KeywordExisteException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link KeywordExisteException }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.servidor/", name = "KeywordExisteException")
    public JAXBElement<KeywordExisteException> createKeywordExisteException(KeywordExisteException value) {
        return new JAXBElement<>(_KeywordExisteException_QNAME, KeywordExisteException.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link NombreExisteException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link NombreExisteException }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.servidor/", name = "NombreExisteException")
    public JAXBElement<NombreExisteException> createNombreExisteException(NombreExisteException value) {
        return new JAXBElement<>(_NombreExisteException_QNAME, NombreExisteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OfertaNoExisteException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link OfertaNoExisteException }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.servidor/", name = "OfertaNoExisteException")
    public JAXBElement<OfertaNoExisteException> createOfertaNoExisteException(OfertaNoExisteException value) {
        return new JAXBElement<>(_OfertaNoExisteException_QNAME, OfertaNoExisteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsuarioNoEsEmpresaException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UsuarioNoEsEmpresaException }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.servidor/", name = "UsuarioNoEsEmpresaException")
    public JAXBElement<UsuarioNoEsEmpresaException> createUsuarioNoEsEmpresaException(UsuarioNoEsEmpresaException value) {
        return new JAXBElement<>(_UsuarioNoEsEmpresaException_QNAME, UsuarioNoEsEmpresaException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsuarioNoEsPostulanteException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UsuarioNoEsPostulanteException }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.servidor/", name = "UsuarioNoEsPostulanteException")
    public JAXBElement<UsuarioNoEsPostulanteException> createUsuarioNoEsPostulanteException(UsuarioNoEsPostulanteException value) {
        return new JAXBElement<>(_UsuarioNoEsPostulanteException_QNAME, UsuarioNoEsPostulanteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DtPublicacion }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DtPublicacion }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.servidor/", name = "dtPublicacion")
    public JAXBElement<DtPublicacion> createDtPublicacion(DtPublicacion value) {
        return new JAXBElement<>(_DtPublicacion_QNAME, DtPublicacion.class, null, value);
    }

}
