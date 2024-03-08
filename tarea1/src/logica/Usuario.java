package logica;

import servidor.types.DTUsuario;

/**
 * Representa al usuario en el sistema.
 * Tiene los campos nickname, nombre, apellido y correo.
 * Clase padre de Empresa y Postulante.
 *
 */
public class Usuario {

	private String nickname; //unico
	private String nombre;
    private String apellido;
    private String correo; //unico
    private String contrasenia;
    private String url_imagen;

    // constructores
    public Usuario() {
        setNickname(new String());;
        setNombre(new String());;
        setApellido(new String());;
        setCorreo(new String());;
        setContrasenia(new String());
        setUrlImagen(new String());
    }
    
    public Usuario(String nickname, String nombre, String apellido, String correo, String contrasenia, String url_imagen) {
        this.setNickname(nickname);
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setCorreo(correo);
        this.setContrasenia(contrasenia);
        this.setUrlImagen(url_imagen);
    }

    // getters
    public String getNickname() {
        return nickname;
    }
    
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }
    
    public String getContrasenia() {
        return contrasenia;
    }

    public String getUrlImagen() {
        return url_imagen;
    }

    // setters
    public void setUrlImagen(String urlImagen) {
        this.url_imagen = urlImagen;
    }
    
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public void setContrasenia(String cont) {
    	this.contrasenia = cont;
    }
    
    /**
     * Retorna los datos del usuario como un DataType DTUsuario.
     */
    public DTUsuario toDataType() {
    	if (this instanceof Empresa) {
    		return ( (Empresa) this ).toDataType();
    	}
    	return ( (Postulante) this ).toDataType();
    }

}

