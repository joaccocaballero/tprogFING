package logica;

public class Fabrica {

    private static Fabrica instancia;

    private Fabrica() {
    };

    public static Fabrica getInstance() {
        if (instancia == null) {
            instancia = new Fabrica();
        }
        return instancia;
    }

    public IControladorUsuario getIControladorUsuario() {
        return ControladorUsuarios.getInstance();
    }
    
    public IControladorPublicaciones getIControladorPublicaciones() {
    	return ControladorPublicaciones.getInstance();
    }

    public IControladorOfertas getIControladorOfertas() {
    	return ControladorOfertas.getInstance();
    }
    
}
