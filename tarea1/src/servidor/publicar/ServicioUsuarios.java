package servidor.publicar;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import logica.*;
import servidor.types.DTEmpresa;
import servidor.types.DTPostulante;
import servidor.types.DTUsuario;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

import excepciones.CorreoNoEncontradoException;
import excepciones.CorreoRepetidoException;
/*import servidor.types.DTUsuario;
import servidor.types.DTEmpresa;
import servidor.types.DTPostulante;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;*/
import excepciones.NicknameNoExisteException;
import excepciones.UsuarioRepetidoException;
import jakarta.jws.WebMethod;
//import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;


@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class ServicioUsuarios {

    private Endpoint endpoint = null;
    private static  String endpointRouteServicioUsuarios  = "";
    private static String rutaArchivo = System.getProperty("user.home") + File.separator + "trabajoUy" + File.separator + ".properties";
    //Constructor
    public ServicioUsuarios(){}

    //Operaciones las cuales quiero publicar
    @WebMethod(exclude = true)
    public void publicar(){
    	 try (InputStream input = new FileInputStream(rutaArchivo)) {
             Properties prop = new Properties();

             System.out.println("RUTA ARCHIVO");
             System.out.println(rutaArchivo);

             // Carga las propiedades del archivo
             prop.load(input);


            endpointRouteServicioUsuarios = prop.getProperty("serviceUsuarios");
            endpoint = Endpoint.publish(endpointRouteServicioUsuarios, this);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
     }
    

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
            return endpoint;
    }
    
    //JWT
    @WebMethod
    public String generateJWT(String email, String tipo_usuario) {
    	Jwt tokenUtils = new Jwt();
    	return tokenUtils.generateJWT(email, tipo_usuario);
    }
    
    @WebMethod
    public boolean validarToken(String jwt){
    	Jwt tokenUtils = new Jwt();
		return tokenUtils.validarUsuario(jwt);
    }
    
    @WebMethod
    public boolean isTokenBlacklisted(String token) {
    	TokenBlacklist list = TokenBlacklist.getInstance();
    	return list.isTokenBlacklisted(token);
    }
    
    @WebMethod
    public String obtenerCorreoPorJWT(String jwt) {
    	Jwt tokenUtils = new Jwt();
		return tokenUtils.obtenerCorreoPorJWT(jwt);
    }
    
    @WebMethod
    public DTUsuario obtenerDatosDeUsuarioJWT(String jwt) {
    	Jwt tokenUtils = new Jwt();
		return tokenUtils.obtenerDatosDeUsuarioJWT(jwt);
    }
    
    @WebMethod 
    public void cerrarSesion(String jwt) {
    	Jwt tokenUtils = new Jwt();
    	tokenUtils.CerrarSesion(jwt);
    }
    
    //VERIFICACIONES
    @WebMethod
    public String tipoUsuario(String jwt){
    	Jwt tokenUtils = new Jwt();
		return tokenUtils.tipoUsuario(jwt);
    }
    
    @WebMethod 
    public Boolean usuarioExiste(String correo){
    	Fabrica factory = Fabrica.getInstance();
		IControladorUsuario icontuser = factory.getIControladorUsuario();
		return icontuser.usuarioExiste(correo);
    }
    
    @WebMethod 
    public Boolean validarUsuario(String correo, String contraseña) throws NicknameNoExisteException{
    	Fabrica factory = Fabrica.getInstance();
		IControladorUsuario icontuser = factory.getIControladorUsuario();
		return icontuser.validarUsuario(correo, contraseña);
    }
    
    @WebMethod 
    public DTPostulante esPostulante(DTUsuario usuario){
    	return (DTPostulante) usuario;
    }
    
    @WebMethod 
    public DTEmpresa esEmpresa(DTUsuario usuario){
    	return (DTEmpresa) usuario;
    }
    
    //CONSULTAS
    @WebMethod 
    public DTUsuario consultarUsuarioPorCorreo(String correo) throws CorreoNoEncontradoException, NicknameNoExisteException{
    	Fabrica factory = Fabrica.getInstance();
		IControladorUsuario icontuser = factory.getIControladorUsuario();
		return icontuser.consultarUsuarioPorCorreo(correo);
    }
    
    @WebMethod
    public DTUsuario consultarUsuario(String nicknameUsuario) throws NicknameNoExisteException {
    	Fabrica factory = Fabrica.getInstance();
		IControladorUsuario icontuser = factory.getIControladorUsuario();
		return icontuser.consultarUsuario(nicknameUsuario);
    }
    
    @WebMethod
    public DTUsuario[] listarUsuarios(){
    	Fabrica factory = Fabrica.getInstance();
		IControladorUsuario icontuser = factory.getIControladorUsuario();
		List<DTUsuario> todosLosUsuarios = icontuser.listarUsuarios();
		DTUsuario[] usuarios = todosLosUsuarios.toArray(new DTUsuario[0]);
		return usuarios;
    	
    }
    
    @WebMethod
    public DTEmpresa[] listarEmpresas(){
    	Fabrica factory = Fabrica.getInstance();
		IControladorUsuario icontuser = factory.getIControladorUsuario();
		List<DTEmpresa> todasLasEmpresas = icontuser.listarEmpresas();
		DTEmpresa[] empresas = todasLasEmpresas.toArray(new DTEmpresa[0]);
		return empresas;
    	
    }
    
    //ALTAS
    @WebMethod
    public void altaEmpresa(String nickname, String nombre, String apellido, String email, String password, String nomEmpresa,
    		String descripcion, String linkWeb, String url_imagen) throws UsuarioRepetidoException, CorreoRepetidoException {
    	Fabrica factory = Fabrica.getInstance();
		IControladorUsuario icontuser = factory.getIControladorUsuario();
		icontuser.altaEmpresa(nickname, nombre, apellido, email, password, nomEmpresa,descripcion,linkWeb,url_imagen);
    }
    
    @WebMethod
    public void  altaPostulante(String nickname, String nombre, String apellido, String email, String contraseña,
			String fechaNacimiento, String nacionalidad, String url_imagen) throws UsuarioRepetidoException, CorreoRepetidoException {
    	Fabrica factory = Fabrica.getInstance();
		IControladorUsuario icontuser = factory.getIControladorUsuario();
		icontuser.altaPostulante(nickname, nombre, apellido, email, contraseña, fechaNacimiento, nacionalidad, url_imagen);
    	
    }
    
    //Modificaciones
    @WebMethod
    public void actualizarDatosPostulante(String nickname, String nuevoNombre, String nuevoApellido,
			String fechaNacimiento, String nacionalidad, String urlImagen){
    	Fabrica factory = Fabrica.getInstance();
		IControladorUsuario icontuser = factory.getIControladorUsuario();
		icontuser.actualizarDatosPostulante(nickname, nuevoNombre, nuevoApellido, fechaNacimiento, nacionalidad, urlImagen);
    }
    
    @WebMethod
    public void actualizarDatosEmpresa(String nickname, String nuevoNombre, String nuevoApellido,
			String nombreEmpresa, String descripcionEmpresa, String linkWebEmpresa, String urlImagen) {
    	Fabrica factory = Fabrica.getInstance();
		IControladorUsuario icontuser = factory.getIControladorUsuario();
		icontuser.actualizarDatosEmpresa(nickname, nuevoNombre, nuevoApellido, nombreEmpresa, descripcionEmpresa, linkWebEmpresa, urlImagen);
    }
    
    
}
