package logica;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import servidor.types.DTOferta;
import servidor.types.DTPaquete;
import servidor.types.DTPublicacion;
import servidor.types.DTTipoPublicacion;
import excepciones.TipoPublicExisteException;
import excepciones.PaqueteExisteException;

public class ControladorPublicaciones implements IControladorPublicaciones {

    private static ControladorPublicaciones instancia;

    public static ControladorPublicaciones getInstance() {
        if (instancia == null) {
            instancia = new ControladorPublicaciones();
        }
        return instancia;
    }

    public List<DTTipoPublicacion> obtenerTipos() {
        ManejadorPublicaciones manejadorPublicaciones = ManejadorPublicaciones.getInstance();
        return manejadorPublicaciones.obtenerTipos();
    }

    public DTTipoPublicacion obtenerDatosTipoPublicacion(String nombre) {
        ManejadorPublicaciones manejadorPublicaciones = ManejadorPublicaciones.getInstance();
        return manejadorPublicaciones.obtenerTipoPublicacion(nombre).toDataType();
    }

    public Publicacion addPublicacion(OfertaLaboral ofertaLaboral, String tipo) {
        ManejadorPublicaciones manejadorPublicaciones = ManejadorPublicaciones.getInstance();
        TipoPublicacion datosTipo = manejadorPublicaciones.getTipo(tipo);
        Integer id = manejadorPublicaciones.getLastPubId();
        Integer duracion = datosTipo.getDuracion();
        LocalDate fechaActual = LocalDate.now();
        LocalDate fin = fechaActual.plusDays(duracion);
        Publicacion publicacion = new Publicacion(id, datosTipo.getCosto(), fechaActual, fin, ofertaLaboral, datosTipo);
        manejadorPublicaciones.addPublicacion(publicacion);
        return publicacion;
    }
    
    public void altaTipoPublicacionOL(String nombre, String descripcion, int exposicion, Integer duracion,
            Integer costoPublic, LocalDate fechaAlta) throws TipoPublicExisteException {
        ManejadorPublicaciones manejadorPublicaciones = ManejadorPublicaciones.getInstance();
        TipoPublicacion tipoPublicacion = new TipoPublicacion(nombre, descripcion, duracion, costoPublic, fechaAlta,
                exposicion);
        manejadorPublicaciones.altaTipoPublicacionOL(tipoPublicacion);
    }

    public void altaPaqueteTipoPublicacion(String nombre, String descripcion, int validez, int descuento, String fecha,
            String urlImagen) throws PaqueteExisteException {
        ManejadorPublicaciones manejadorPublicaciones = ManejadorPublicaciones.getInstance();
        LocalDate fechaAlta = !fecha.isEmpty() ? LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                : LocalDate.now();
        Paquete paquete = new Paquete(nombre, descripcion, validez, descuento, 0, fechaAlta, urlImagen);
        manejadorPublicaciones.addPaqueteTipoPublicacion(paquete);
    }

    public void agregarTipoPublicacion(String nombrePaquete, Integer cantidad, String nombreTipoPublicacion) {
        ManejadorPublicaciones manejadorPublicaciones = ManejadorPublicaciones.getInstance();
        Paquete paquete = manejadorPublicaciones.obtenerPaquete(nombrePaquete);
        TipoPublicacion tipoPublicacion = manejadorPublicaciones.obtenerTipoPublicacion(nombreTipoPublicacion);
        paquete.agregarTipoPublicacion(cantidad, tipoPublicacion);
    }


    public List<DTPaquete> listarPaquetes() {
        ManejadorPublicaciones manejadorPublicaciones = ManejadorPublicaciones.getInstance();
        return manejadorPublicaciones.obtenerListaPaquetes();
    }

    public List<DTPublicacion> obtenerPublicaciones() {
        ManejadorPublicaciones manejadorPublicaciones = ManejadorPublicaciones.getInstance();
        return manejadorPublicaciones.obtenerPublicaciones();
    }

    public List<DTPublicacion> obtenerPublicacionesDeEmpresa(String nicknameEmpresa) {
        ManejadorPublicaciones manejadorPublicaciones = ManejadorPublicaciones.getInstance();
        List<DTPublicacion> publicaciones = manejadorPublicaciones.obtenerPublicaciones();
        return publicaciones.stream()
            .filter(dtPublicacion -> dtPublicacion.getDtOferta().getNicknameEmpresa().equalsIgnoreCase(nicknameEmpresa))
            .collect(Collectors.toList());
    }

    public List<DTPublicacion> obtenerPublicacionesPorBusqueda(String busqueda) {
        ManejadorPublicaciones manejadorPublicaciones = ManejadorPublicaciones.getInstance();
        List<DTPublicacion> publicaciones = manejadorPublicaciones.obtenerPublicaciones();
        return publicaciones.stream()
            .filter(dtPublicacion -> {
                DTOferta oferta = dtPublicacion.getDtOferta();
                return oferta.getNombre().toLowerCase().contains(busqueda.toLowerCase())
                    || oferta.getDescripcion().toLowerCase().contains(busqueda.toLowerCase())
                    || oferta.getCiudad().toLowerCase().contains(busqueda.toLowerCase())
                    || oferta.getDepartamento().toLowerCase().contains(busqueda.toLowerCase());
            })
            .collect(Collectors.toList());
    }

    public List<DTPublicacion> obtenerPublicacionesPorKeywords(List<String> keywords) {
        ManejadorPublicaciones manejadorPublicaciones = ManejadorPublicaciones.getInstance();
        List<DTPublicacion> publicaciones = manejadorPublicaciones.obtenerPublicaciones();
        List<String> keywordsLowerCase = keywords.stream()
            .map(String::toLowerCase)
            .collect(Collectors.toList());

        return publicaciones.stream()
            .filter(dtPublicacion -> {
                DTOferta oferta = dtPublicacion.getDtOferta();
                List<String> ofertaKeywords = oferta.getKeywords();
                List<String> ofertaKeywordsLowerCase = ofertaKeywords.stream()
                    .map(String::toLowerCase)
                    .collect(Collectors.toList());

                return !Collections.disjoint(keywordsLowerCase, ofertaKeywordsLowerCase);
            })
            .collect(Collectors.toList());
    }

    public DTPublicacion obtenerPublicacionAsociadaAOferta(String nombreOferta) {
        ManejadorPublicaciones manejadorPublicaciones = ManejadorPublicaciones.getInstance();
        return manejadorPublicaciones.obtenerPublicacionAsociadaAOferta(nombreOferta).toDatatype();
    }
}
