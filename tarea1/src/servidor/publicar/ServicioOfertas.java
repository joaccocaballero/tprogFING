package servidor.publicar;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import logica.*;
import servidor.types.DTOferta;
import servidor.types.DTPostulacion;
import servidor.types.DTPublicacion;
import servidor.types.DTTipoPublicacion;
import servidor.types.EnumEstadoOferta;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import excepciones.KeywordExisteException;
import excepciones.NicknameNoExisteException;
import excepciones.NombreExisteException;
import excepciones.OfertaNoExisteException;
import excepciones.UsuarioNoEsEmpresaException;
import excepciones.UsuarioNoEsPostulanteException;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class ServicioOfertas {

    private Endpoint endpoint = null;
    private static  String endpointRouteServicioOfertas  = "";
    private static String rutaArchivo = System.getProperty("user.home") + File.separator + "trabajoUy" + File.separator + ".properties";
    //Constructor
    public ServicioOfertas(){}

    //Operaciones las cuales quiero publicar

    @WebMethod(exclude = true)
    public void publicar(){
    	 try (InputStream input = new FileInputStream(rutaArchivo)) {
             Properties prop = new Properties();
             System.out.println("RUTA ARCHIVO");
             System.out.println(rutaArchivo);
             
             // Carga las propiedades del archivo
             prop.load(input);

            endpointRouteServicioOfertas = prop.getProperty("serviceOfertas");
            endpoint = Endpoint.publish(endpointRouteServicioOfertas, this);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
            return endpoint;
    }
    
    //funciona
    @WebMethod
    public String[] obtenerKeywords() throws KeywordExisteException {
        Fabrica factory = Fabrica.getInstance();
        IControladorOfertas ICO = factory.getIControladorOfertas();
        String[] keywords = ICO.obtenerKeywords().toArray(new String[0]);
        return keywords;
    }
    
    //funciona
    @WebMethod
    public DTPublicacion[] obtenerPublicacionesPorBusqueda(String busqueda) {
        Fabrica factory = Fabrica.getInstance();
        IControladorPublicaciones ICP = factory.getIControladorPublicaciones();
        List<DTPublicacion> todasLasPublicaciones = ICP.obtenerPublicaciones();
        List<DTPublicacion> listaPublicaciones = todasLasPublicaciones.stream()
            .filter(dtPublicacion -> {
                DTOferta oferta = dtPublicacion.getDtOferta();
                return oferta.getNombre().toLowerCase().contains(busqueda.toLowerCase())
                    || oferta.getDescripcion().toLowerCase().contains(busqueda.toLowerCase())
                    || oferta.getCiudad().toLowerCase().contains(busqueda.toLowerCase())
                    || oferta.getDepartamento().toLowerCase().contains(busqueda.toLowerCase());
            })
            .collect(Collectors.toList());
        DTPublicacion[] publicaciones = listaPublicaciones.toArray(new DTPublicacion[0]);
        return publicaciones;
    }
    
    //funciona
    @WebMethod
    public DTPublicacion[] obtenerPublicacionesPorKeywords(String keywords) {
        Fabrica factory = Fabrica.getInstance();
        IControladorPublicaciones ICP = factory.getIControladorPublicaciones();
        String[] keywordsArray = keywords.split("/");
        List<String> keys = Arrays.asList(keywordsArray);
        List<DTPublicacion> pub = ICP.obtenerPublicacionesPorKeywords(keys);
        DTPublicacion[] publicaciones = pub.toArray(new DTPublicacion[0]);
        return publicaciones;
    }
    
    //funciona
    @WebMethod
    public DTPublicacion[] obtenerPublicaciones() {
        Fabrica factory = Fabrica.getInstance();
        IControladorPublicaciones ICP = factory.getIControladorPublicaciones();
        List<DTPublicacion> todasLasPublicaciones = ICP.obtenerPublicaciones();
        DTPublicacion[] publicaciones = todasLasPublicaciones.toArray(new DTPublicacion[0]);
        return publicaciones;
    }
    
    //funciona
    @WebMethod
    public DTTipoPublicacion[] obtenerTipos() {
        Fabrica factory = Fabrica.getInstance();
        IControladorPublicaciones ICP = factory.getIControladorPublicaciones();
        List<DTTipoPublicacion> todosLosTipos = ICP.obtenerTipos();
        DTTipoPublicacion[] tipos = todosLosTipos.toArray(new DTTipoPublicacion[0]);
        return tipos;
    }
    
    //funciona
    @WebMethod
    public DTOferta obtenerDatosOferta(String nombreOferta) throws OfertaNoExisteException {
    	Fabrica factory = Fabrica.getInstance();
    	IControladorOfertas ICO = factory.getIControladorOfertas();
		return ICO.obtenerDatosOferta(nombreOferta);
    }
    
    //funciona
    @WebMethod
    public DTTipoPublicacion obtenerDatosTipoPublicacion(String nombre) {
    	Fabrica factory = Fabrica.getInstance();
        IControladorPublicaciones ICP = factory.getIControladorPublicaciones();
		return ICP.obtenerDatosTipoPublicacion(nombre);
    	
    }
    
    //funciona
    @WebMethod
    public DTPublicacion obtenerPublicacionAsociadaAOferta(String nombreOferta) {
    	Fabrica factory = Fabrica.getInstance();
    	IControladorPublicaciones ICP = factory.getIControladorPublicaciones();
    	return ICP.obtenerPublicacionAsociadaAOferta(nombreOferta);
    }
    
    //funciona
    @WebMethod
    public DTOferta[] obtenerOfertasVigentesDeEmpresa(String nicknameEmpresa) throws NicknameNoExisteException, UsuarioNoEsEmpresaException{
    	Fabrica factory = Fabrica.getInstance();
    	IControladorOfertas ICO = factory.getIControladorOfertas();
    	Set<DTOferta> todasOfertasVigentes = ICO.obtenerOfertasVigentesDeEmpresa(nicknameEmpresa);
    	return todasOfertasVigentes.toArray(new DTOferta[0]);
    }
    
    //funciona
    @WebMethod
    public DTPublicacion[] obtenerPublicacionesDeEmpresa(String nicknameEmpresa) {
    	Fabrica factory = Fabrica.getInstance();
    	IControladorPublicaciones ICP = factory.getIControladorPublicaciones();
    	List<DTPublicacion> todasPublicacionesEmpresa = ICP.obtenerPublicacionesDeEmpresa(nicknameEmpresa);
    	return todasPublicacionesEmpresa.toArray(new DTPublicacion[0]);
    }
    
    @WebMethod
    public boolean verificarPertenenciaOferta(String nombreOferta, String nickname) throws OfertaNoExisteException {
    	Fabrica factory = Fabrica.getInstance();
    	IControladorOfertas ICO = factory.getIControladorOfertas();
    	return ICO.verificarPertenenciaOferta(nombreOferta, nickname);
    }
    
    @WebMethod
    public void confirmarOfertaLaboral(String nombreOferta) throws OfertaNoExisteException {
    	Fabrica factory = Fabrica.getInstance();
    	IControladorOfertas ICO = factory.getIControladorOfertas();
    	ICO.confirmarOfertaLaboral(nombreOferta);
    }
    
    //POSTULACIONES
    @WebMethod
    public void postularAOferta(String nombreOfertaLaboral, String nicknamePostulante, String cvReducido, String motivacion, String fechaPostulacion, String urlVideo) throws NicknameNoExisteException, UsuarioNoEsPostulanteException, OfertaNoExisteException {
    	Fabrica factory = Fabrica.getInstance();
    	IControladorOfertas ICO = factory.getIControladorOfertas();
    	ICO.postularAOferta(nombreOfertaLaboral, nicknamePostulante, cvReducido, motivacion, fechaPostulacion, urlVideo);
    }
    
    @WebMethod
    public DTPostulacion[] obtenerPostulacionesPorPostulante(String nicknamePostulante) throws NicknameNoExisteException, UsuarioNoEsPostulanteException{
    	Fabrica factory = Fabrica.getInstance();
    	IControladorOfertas ICO = factory.getIControladorOfertas();
    	List<DTPostulacion> aux =  ICO.obtenerPostulacionesPorPostulante(nicknamePostulante);
    	DTPostulacion[] postulaciones = aux.toArray(new DTPostulacion[0]);
    	return postulaciones;
    }
    
    @WebMethod 
    public boolean estaPostuladoAOfertaBoolean(String nicknameUsuario, String nombreOfertaLaboral) throws OfertaNoExisteException, NicknameNoExisteException, UsuarioNoEsPostulanteException {
    	boolean out = false;
    	Fabrica factory = Fabrica.getInstance();
    	IControladorOfertas ICO = factory.getIControladorOfertas();
    	DTPostulacion esta = ICO.estaPostuladoAOfertaLaboral(nicknameUsuario, nombreOfertaLaboral);
    	if(esta != null) {
    		out = true;
    	}
    	return out;
    }
    
    @WebMethod 
    public DTPostulacion estaPostuladoAOfertaLaboral(String nicknameUsuario, String nombreOfertaLaboral) throws OfertaNoExisteException, NicknameNoExisteException, UsuarioNoEsPostulanteException {
    	Fabrica factory = Fabrica.getInstance();
    	IControladorOfertas ICO = factory.getIControladorOfertas();
    	return ICO.estaPostuladoAOfertaLaboral(nicknameUsuario, nombreOfertaLaboral);
    }

    //ALTAS
    @WebMethod
    public void altaOferta(String nombre,String desc,String remuner,String horario,ArrayList<String> keywords,String ciudad,
    String depa,String tipo,String empresa) throws NombreExisteException, KeywordExisteException, NicknameNoExisteException{
    	 Fabrica factory = Fabrica.getInstance();
    	 IControladorOfertas ICO = factory.getIControladorOfertas();
    	 ICO.altaOferta(nombre, desc, remuner, horario, keywords, ciudad, depa, tipo, empresa);
    }
    
    @WebMethod
    public void altaOfertaWeb(String nombre,String descripcion,String renumeracion,String horario,String ciudad,String departamento,
    	    String tipoPublicacion,String formaPago,String paqueteSeleccionado,EnumEstadoOferta estado,String keywords, String urlImagen,String empresaActual) throws NicknameNoExisteException, NombreExisteException, KeywordExisteException {
    	    	 Fabrica factory = Fabrica.getInstance();
    	    	 IControladorOfertas ICO = factory.getIControladorOfertas();
    	    	 String[] keywordsArray = keywords.split("/");
    	    	 ICO.altaOfertaWeb(nombre, descripcion, renumeracion, horario, ciudad, departamento, tipoPublicacion, formaPago, paqueteSeleccionado, estado, keywordsArray, urlImagen, empresaActual);    
    }
    
    @WebMethod
    public void agregarEliminarFavorito(String nickname, String nombreOferta) throws NicknameNoExisteException {
    	Fabrica factory = Fabrica.getInstance();
   	 	IControladorOfertas ICO = factory.getIControladorOfertas();
   	 	ICO.agregarEliminarFavorito(nickname, nombreOferta);
    }
    
    @WebMethod
    public void finalizarOferta(String nombreOferta) throws OfertaNoExisteException {
    	Fabrica factory = Fabrica.getInstance();
   	 	IControladorOfertas ICO = factory.getIControladorOfertas();
   	 	ICO.actualizarEstadoOfertaLaboral(nombreOferta, EnumEstadoOferta.FINALIZADA);
    }

    @WebMethod
    public void seleccionarPostulaciones(String nombreOferta, String postulaciones) throws OfertaNoExisteException {
    	Fabrica factory = Fabrica.getInstance();
   	 	IControladorOfertas ICO = factory.getIControladorOfertas();
   	 	ICO.seleccionarPostulaciones(nombreOferta, postulaciones);
    }
}

