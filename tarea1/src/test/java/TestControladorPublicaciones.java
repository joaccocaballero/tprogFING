package test.java;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import excepciones.*;
import logica.*;
import servidor.types.*;

public class TestControladorPublicaciones {

	private static IControladorPublicaciones controladorPublicaciones;
	
	private static ManejadorUsuarios manejadorU;
	private static ManejadorOfertaLaboral manejadorOL;
	private static ManejadorPublicaciones manejadorP;
	
	
	@BeforeAll
	public static void iniciar() {
		Fabrica fabrica = Fabrica.getInstance();
		controladorPublicaciones = fabrica.getIControladorPublicaciones();
		
		manejadorU = ManejadorUsuarios.getInstance();
		manejadorOL = ManejadorOfertaLaboral.getInstance();
		manejadorP = ManejadorPublicaciones.getInstance();
	}
	
	@BeforeEach
	public void limpiarColecciones() {
		manejadorU.limpiarColeccionUsuarios();
		manejadorOL.limpiarColeccionOfertasLaborales();
		manejadorOL.limpiarColeccionKeywords();
		manejadorP.limpiarColeccionPublicaciones();
		manejadorP.limpiarColeccionPaquetes();
		manejadorP.limpiarColeccionTipos();
	}
	
	
	// altaTipoPublicacionOL() -> OK
	@Test
	void altaTipoPublicacionOL_OK() {
		String nombreTipoP = "nombreTipoP";
		String descripcionTipoP = "descripcionTipoP";
		int exposicionTipoP = 4;
		Integer duracionTipoP = 24;
		Integer costoPublicTipoP = 69;
		LocalDate fechaTipoP = LocalDate.now();
		
		try {
			controladorPublicaciones.altaTipoPublicacionOL(nombreTipoP, descripcionTipoP, exposicionTipoP, duracionTipoP, costoPublicTipoP, fechaTipoP);
		} catch (TipoPublicExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		assertEquals(controladorPublicaciones.obtenerTipos().size(), 1);
		DTTipoPublicacion tipoDT = controladorPublicaciones.obtenerTipos().get(0);
		assertEquals(tipoDT.getNombre(), nombreTipoP);
		assertEquals(tipoDT.getDescripcion(), descripcionTipoP);
		assertEquals(tipoDT.getExposicion(), exposicionTipoP);
		assertEquals(tipoDT.getDuracion(), duracionTipoP.toString());
		assertEquals(tipoDT.getCosto(), costoPublicTipoP);
		assertEquals(tipoDT.getAlta(), fechaTipoP);
	}
	
	// altaTipoPublicacionOL() -> nombre repetido
	@Test
	void altaTipoPublicacionOL_Repetida() {
		String nombreTipoP = "nombreTipoP";
		String descripcionTipoP = "descripcionTipoP";
		int exposicionTipoP = 4;
		Integer duracionTipoP = 24;
		Integer costoPublicTipoP = 69;
		LocalDate fechaTipoP = LocalDate.now();
		
		try {
			controladorPublicaciones.altaTipoPublicacionOL(nombreTipoP, descripcionTipoP, exposicionTipoP, duracionTipoP, costoPublicTipoP, fechaTipoP);
		} catch (TipoPublicExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}

		assertEquals(controladorPublicaciones.obtenerTipos().size(), 1);
		assertThrows(TipoPublicExisteException.class, () -> controladorPublicaciones.altaTipoPublicacionOL(nombreTipoP, descripcionTipoP, exposicionTipoP, duracionTipoP, costoPublicTipoP, fechaTipoP));
	}
	
	// obtenerTipos() -> sin tipos
	@Test
	void testObtenerTipos_SinTipos() {
		assertEquals(controladorPublicaciones.obtenerTipos().size(), 0);
	}
	
	// obtenerTipos() -> OK
	@Test
	void testObtenerTipos_DosTipos() {
		String nombreTipoP1 = "nombreTipoP";
		String descripcionTipoP1 = "descripcionTipoP";
		int exposicionTipoP1 = 4;
		Integer duracionTipoP1 = 24;
		Integer costoPublicTipoP1 = 69;
		LocalDate fechaTipoP1 = LocalDate.now();
		
		String nombreTipoP2 = "nombreTipoP2";
		String descripcionTipoP2 = "descripcionTipoP2";
		int exposicionTipoP2 = 45;
		Integer duracionTipoP2 = 25;
		Integer costoPublicTipoP2 = 70;
		LocalDate fechaTipoP2 = LocalDate.now().minusDays(1);
		
		try {
			controladorPublicaciones.altaTipoPublicacionOL(nombreTipoP1, descripcionTipoP1, exposicionTipoP1, duracionTipoP1, costoPublicTipoP1, fechaTipoP1);
		} catch (TipoPublicExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		assertEquals(controladorPublicaciones.obtenerTipos().size(), 1);
		
		try {
			controladorPublicaciones.altaTipoPublicacionOL(nombreTipoP2, descripcionTipoP2, exposicionTipoP2, duracionTipoP2, costoPublicTipoP2, fechaTipoP2);
		} catch (TipoPublicExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}

		List<DTTipoPublicacion> listaTipos = controladorPublicaciones.obtenerTipos();
		assertEquals(listaTipos.size(), 2);
		assertTrue(listaTipos.stream().anyMatch(nombreTipoP -> nombreTipoP.getNombre().equals(nombreTipoP1)));
		assertTrue(listaTipos.stream().anyMatch(nombreTipoP -> nombreTipoP.getNombre().equals(nombreTipoP2)));
		assertTrue(listaTipos.stream().anyMatch(descripcionTipoP -> descripcionTipoP.getDescripcion().equals(descripcionTipoP1)));
		assertTrue(listaTipos.stream().anyMatch(descripcionTipoP -> descripcionTipoP.getDescripcion().equals(descripcionTipoP2)));
		assertTrue(listaTipos.stream().anyMatch(exposicionTipoP -> exposicionTipoP.getExposicion() == exposicionTipoP1));
		assertTrue(listaTipos.stream().anyMatch(exposicionTipoP -> exposicionTipoP.getExposicion() == exposicionTipoP2));
		assertTrue(listaTipos.stream().anyMatch(duracionTipoP -> duracionTipoP.getDuracion().equals(duracionTipoP1.toString())));
		assertTrue(listaTipos.stream().anyMatch(duracionTipoP -> duracionTipoP.getDuracion().equals(duracionTipoP2.toString())));
		assertTrue(listaTipos.stream().anyMatch(costoPublicTipoP -> costoPublicTipoP.getCosto().equals(costoPublicTipoP1)));
		assertTrue(listaTipos.stream().anyMatch(costoPublicTipoP -> costoPublicTipoP.getCosto().equals(costoPublicTipoP2)));
		assertTrue(listaTipos.stream().anyMatch(fechaTipoP -> fechaTipoP.getAlta().equals(fechaTipoP1)));
		assertTrue(listaTipos.stream().anyMatch(fechaTipoP -> fechaTipoP.getAlta().equals(fechaTipoP2)));
	}
	
	// listarPaquetes() -> sin paquetes
	@Test
	void testListarPaquetes_SinPaquetes() {
		assertEquals(controladorPublicaciones.listarPaquetes().size(), 0);
	}
	
}
