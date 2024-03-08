package logica;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import excepciones.KeywordExisteException;
import excepciones.NombreExisteException;
import excepciones.NicknameNoExisteException;
import excepciones.OfertaNoExisteException;
import excepciones.UsuarioNoEsEmpresaException;
import excepciones.UsuarioNoEsPostulanteException;
import servidor.types.DTEmpresa;
import servidor.types.DTOferta;
import servidor.types.DTPostulacion;
import servidor.types.EnumEstadoOferta;

public class ControladorOfertas implements IControladorOfertas {

    private static ControladorOfertas instancia;

    public static ControladorOfertas getInstance() {
        if (instancia == null) {
            instancia = new ControladorOfertas();
        }
        return instancia;
    }

    public DTOferta obtenerDatosOferta(String nombreOferta) throws OfertaNoExisteException {
        ManejadorOfertaLaboral manejadorOL = ManejadorOfertaLaboral.getInstance();
        return manejadorOL.obtenerOfertaLaboral(nombreOferta);
    }

    public void altaKeyword(String nombre) throws KeywordExisteException {
        Keyword key = new Keyword(nombre);
        ManejadorOfertaLaboral manejadorOL = ManejadorOfertaLaboral.getInstance();
        manejadorOL.addKeyword(key);
    }

    public void altaOferta(
        String nombre,
        String desc,
        String remuner,
        String horario,
        List<String> keywords,
        String ciudad,
        String depa,
        String tipo,
        String empresa
    ) throws NombreExisteException, KeywordExisteException, NicknameNoExisteException {
        LocalDate fecha = LocalDate.now();
        ControladorUsuarios controladorUsuarios = ControladorUsuarios.getInstance();
        Empresa usuarioEmpresa = (Empresa) controladorUsuarios.obtenerUsuario(empresa);
        ManejadorOfertaLaboral manejadorOL = ManejadorOfertaLaboral.getInstance();
        OfertaLaboral ofertaLaboral = new OfertaLaboral(
            nombre,
            desc,
            ciudad,
            depa,
            horario,
            fecha,
            EnumEstadoOferta.INGRESADA,
            remuner,
            usuarioEmpresa
        );
        manejadorOL.addOferta(ofertaLaboral, keywords);
        ControladorPublicaciones controladorPublicaciones = ControladorPublicaciones.getInstance();
        Publicacion publicacion = controladorPublicaciones.addPublicacion(ofertaLaboral, tipo);
        usuarioEmpresa.asociarOferta(ofertaLaboral.toDataType());
        ofertaLaboral.addPublicacion(publicacion);
    }
	
    public void altaOfertaWeb(
    	    String nombre,
    	    String descripcion,
    	    String renumeracion,
    	    String horario,
    	    String ciudad,
    	    String departamento,
    	    String tipoPublicacion,
    	    String formaPago,
    	    String paqueteSeleccionado,
    	    EnumEstadoOferta estado,
    	    String[] keywords,
    	    String urlImagen,
    	    String empresaActual
    	) throws NicknameNoExisteException, NombreExisteException, KeywordExisteException {
    	    LocalDate fecha = LocalDate.now();
    	    ManejadorOfertaLaboral manejadorOL = ManejadorOfertaLaboral.getInstance();
    	    ControladorUsuarios controladorUsuarios = ControladorUsuarios.getInstance();
    	    Empresa usuarioEmpresa = (Empresa) controladorUsuarios.obtenerUsuario(empresaActual);
    	    OfertaLaboral ofertaLaboral;

    	    if (urlImagen != null && !urlImagen.trim().isEmpty() && paqueteSeleccionado != null && !paqueteSeleccionado.trim().isEmpty()) {
    	        ofertaLaboral = new OfertaLaboral(
    	            nombre, descripcion, ciudad, departamento, horario, fecha, estado, renumeracion,
    	            usuarioEmpresa, formaPago, urlImagen, paqueteSeleccionado
    	        );
    	    } else if (urlImagen != null && !urlImagen.trim().isEmpty()) {
    	        ofertaLaboral = new OfertaLaboral(
    	            nombre, descripcion, ciudad, departamento, horario, fecha, estado, renumeracion,
    	            urlImagen, usuarioEmpresa, formaPago
    	        );
    	    } else if (paqueteSeleccionado != null && !paqueteSeleccionado.trim().isEmpty()) {
    	        ofertaLaboral = new OfertaLaboral(
    	            nombre, descripcion, ciudad, departamento, horario, fecha, estado, renumeracion,
    	            usuarioEmpresa, formaPago, paqueteSeleccionado
    	        );
    	    } else {
    	        ofertaLaboral = new OfertaLaboral(
    	            nombre, descripcion, ciudad, departamento, horario, fecha, estado, renumeracion,
    	            usuarioEmpresa, formaPago
    	        );
    	    }

    	    List<String> keywordsList = new ArrayList<>(Arrays.asList(keywords));
    	    manejadorOL.addOferta(ofertaLaboral, keywordsList);
    	    ControladorPublicaciones controladorPublicaciones = ControladorPublicaciones.getInstance();
    	    Publicacion publicacion = controladorPublicaciones.addPublicacion(ofertaLaboral, tipoPublicacion);
    	    usuarioEmpresa.asociarOferta(ofertaLaboral.toDataType());
    	    ofertaLaboral.addPublicacion(publicacion);
    }
    


    public List<String> obtenerKeywords() {
        ManejadorOfertaLaboral manejadorOL = ManejadorOfertaLaboral.getInstance();
        return manejadorOL.obtenerKeywords();
    }

    public void postularAOferta(
        String nombreOfertaLaboral,
        String nicknamePostulante,
        String cvReducido,
        String motivacion,
        String fechaPostulacion,
        String urlVideo
    ) throws NicknameNoExisteException, UsuarioNoEsPostulanteException, OfertaNoExisteException {
        ManejadorOfertaLaboral manejadorOL = ManejadorOfertaLaboral.getInstance();
        String fechaString = fechaPostulacion;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime fechaConvertida = LocalDateTime.parse(fechaString, formatter);
        manejadorOL.postularAOferta(nombreOfertaLaboral, nicknamePostulante, cvReducido, motivacion, fechaConvertida, urlVideo);
    }

    public List<DTEmpresa> obtenerEmpresas() {
        IControladorUsuario controladorUsuarios = ControladorUsuarios.getInstance();
        return controladorUsuarios.listarEmpresas();
    }

    public List<DTOferta> obtenerOfertasLaborales() {
        ManejadorOfertaLaboral manejadorOL = ManejadorOfertaLaboral.getInstance();
        return manejadorOL.obtenerOfertasLaborales();
    }

    public Set<DTOferta> obtenerOfertasVigentesDeEmpresa(String nicknameEmpresa)
        throws NicknameNoExisteException, UsuarioNoEsEmpresaException {
        ManejadorOfertaLaboral manejadorOL = ManejadorOfertaLaboral.getInstance();
        return manejadorOL.obtenerOfertasVigentesDeEmpresa(nicknameEmpresa);
    }

    public List<DTPostulacion> obtenerPostulacionesPorPostulante(String nicknamePostulante)
        throws NicknameNoExisteException, UsuarioNoEsPostulanteException {
        ManejadorOfertaLaboral manejadorOL = ManejadorOfertaLaboral.getInstance();
        return manejadorOL.obtenerPostulacionesPorPostulante(nicknamePostulante);
    }

    public DTPostulacion estaPostuladoAOfertaLaboral(String nicknameUsuario, String nombreOfertaLaboral)
        throws OfertaNoExisteException, NicknameNoExisteException, UsuarioNoEsPostulanteException {
        ManejadorOfertaLaboral manejadorOL = ManejadorOfertaLaboral.getInstance();
        return manejadorOL.estaPostuladoAOfertaLaboral(nicknameUsuario, nombreOfertaLaboral);
    }

    public void confirmarOfertaLaboral(String nombreOferta) throws OfertaNoExisteException {
        ManejadorOfertaLaboral manejadorOL = ManejadorOfertaLaboral.getInstance();
        manejadorOL.confirmarOfertaLaboral(nombreOferta);
    }

    public boolean verificarPertenenciaOferta(String nombreOferta, String nickname) throws OfertaNoExisteException {
        ManejadorOfertaLaboral manejadorOL = ManejadorOfertaLaboral.getInstance();
        DTOferta oferta = manejadorOL.obtenerOfertaLaboral(nombreOferta);
        if (oferta != null) {
            String nicknameEmpresa = oferta.getNicknameEmpresa();
            return nicknameEmpresa.equals(nickname);
        } else {
            throw new OfertaNoExisteException("No existe una oferta laboral con el nombre proporcionado: " + nombreOferta);
        }
    }

    public TreeSet<DTOferta> obtenerOfertasIngresadasDeEmpresa(String nicknameEmpresa)
        throws NicknameNoExisteException, UsuarioNoEsEmpresaException {
        ManejadorOfertaLaboral manejadorOL = ManejadorOfertaLaboral.getInstance();
        return manejadorOL.obtenerOfertasIngresadasDeEmpresa(nicknameEmpresa);
    }
    
    public void cambiarEstadoOferta(EnumEstadoOferta estado, String nombreOferta) throws OfertaNoExisteException {
    	ManejadorOfertaLaboral manejadorOL = ManejadorOfertaLaboral.getInstance();
		manejadorOL.cambiarEstadoOferta(estado,nombreOferta);
    }
    
    public void actualizarEstadoOfertaLaboral(String nombreOfertaLaboral, EnumEstadoOferta estado) throws OfertaNoExisteException {
		ManejadorOfertaLaboral manejadorOL = ManejadorOfertaLaboral.getInstance();
		OfertaLaboral o = manejadorOL.getOfertaLaboral(nombreOfertaLaboral);
		o.setEstado(estado);
	}

	public void agregarEliminarFavorito(String nickname, String nombreOferta) throws NicknameNoExisteException {
        ManejadorOfertaLaboral manejadorOL = ManejadorOfertaLaboral.getInstance();
        ControladorUsuarios controladorUsuarios = ControladorUsuarios.getInstance();
        Postulante usuarioPostulante = (Postulante) controladorUsuarios.obtenerUsuario(nickname);
        manejadorOL.agregarEliminarFavorito(usuarioPostulante, nombreOferta);
	}
	
	public void seleccionarPostulaciones(String nombreOferta, String postulaciones) {
        ManejadorOfertaLaboral manejadorOL = ManejadorOfertaLaboral.getInstance();
        manejadorOL.seleccionarPostulaciones(nombreOferta,postulaciones);
	}
}
