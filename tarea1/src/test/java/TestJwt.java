package test.java;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Set;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import excepciones.*;
import logica.*;
import servidor.types.*;

public class TestJwt {

	private static IControladorOfertas controladorOfertas;
	private static IControladorUsuario controladorUsuarios;
	private static IControladorPublicaciones controladorPublicaciones;
	
	private static ManejadorUsuarios manejadorU;
	private static ManejadorOfertaLaboral manejadorOL;
	private static ManejadorPublicaciones manejadorP;
	
	
	@BeforeAll
	public static void iniciar() {
		Fabrica fabrica = Fabrica.getInstance();
		controladorOfertas = fabrica.getIControladorOfertas();
		controladorUsuarios = fabrica.getIControladorUsuario();
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
	
	@Test
	public void testearJwt() {
		// le cargo una empresa al sistema
		String nicknameEmpresa1 = "nicknameEmpresaUno";
		String nombreEmpresa1 = "nombrePersonaEmpresaUno";
		String apellidoEmpresa1 = "apellidoEmpresaUno";
		String emailEmpresa1 = "emailEmpresaUno";
		String contraseñaEmpresa1 = "contraseñaEmpresa1";
		String nombreEmpresaEmpresa1 = "nombreEmpresaUno";
		String descripcionEmpresa1 = "descripcionEmpresaUno";
		String linkWebEmpresa1 = "linkWebEmpresaUno";
		String imagenEmpresa1 = "imagenEmpresaUno.png";
		try {
			controladorUsuarios.altaEmpresa(nicknameEmpresa1, nombreEmpresa1, apellidoEmpresa1, emailEmpresa1, contraseñaEmpresa1,nombreEmpresaEmpresa1, descripcionEmpresa1, linkWebEmpresa1, imagenEmpresa1);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		assertEquals(controladorUsuarios.listarEmpresas().size(), 1);
		
		// le cargo un postulante al sistema
		String nicknamePostulante1 = "nicknamePostulanteUno";
		String nombrePostulante1 = "nombrePersonaPostulanteUno";
		String apellidoPostulante1 = "apellidoPostulanteUno";
		String emailPostulante1 = "emailPostulanteUno";
		String contraseñaPostulante1 = "contraseñaPostulante1";
		String fechaNacimientoPostulante1 = "01/10/2023";
		String nacionalidadPostulante1 = "nacionalidadPostulanteUno";
		String imagenPostulante1 = "imagenPostulanteUno.png";
		try {
			controladorUsuarios.altaPostulante(nicknamePostulante1, nombrePostulante1, apellidoPostulante1, emailPostulante1,contraseñaPostulante1 ,fechaNacimientoPostulante1, nacionalidadPostulante1, imagenPostulante1);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		assertEquals(controladorUsuarios.listarPostulantes().size(), 1);
		assertEquals(controladorUsuarios.listarUsuarios().size(), 2);
		
		// creo una instancia de la clase jwt
		Jwt instanciaJwt = new Jwt();
		
		// creo un jwt para la empresa y otro para el postulante
		String jwtEmpresa = instanciaJwt.generateJWT(emailEmpresa1, "empresa");
		String jwtPostulante = instanciaJwt.generateJWT(emailPostulante1, "postulante");
		
		// chequeo que este todo bien
		assertTrue(instanciaJwt.validarUsuario(jwtEmpresa));
		assertTrue(instanciaJwt.validarUsuario(jwtPostulante));
		
		assertEquals(instanciaJwt.tipoUsuario(null), "visitante");
		assertEquals(instanciaJwt.tipoUsuario(jwtEmpresa), "empresa");
		assertEquals(instanciaJwt.tipoUsuario(jwtPostulante), "postulante");

		assertEquals(instanciaJwt.obtenerCorreoPorJWT(jwtEmpresa), emailEmpresa1);
		assertEquals(instanciaJwt.obtenerCorreoPorJWT(jwtPostulante), emailPostulante1);
		
		DTUsuario empresaDt = instanciaJwt.obtenerDatosDeUsuarioJWT(jwtEmpresa);
		assertEquals(empresaDt.getNickname(), nicknameEmpresa1);
		DTUsuario postulanteDt = instanciaJwt.obtenerDatosDeUsuarioJWT(jwtPostulante);
		assertEquals(postulanteDt.getNickname(), nicknamePostulante1);
		
		instanciaJwt.CerrarSesion(jwtEmpresa);
		assertEquals(instanciaJwt.tipoUsuario(jwtEmpresa), "invalido");
	}
	
}
