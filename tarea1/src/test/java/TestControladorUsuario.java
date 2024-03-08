package test.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import excepciones.*;
import logica.*;
import servidor.types.*;

public class TestControladorUsuario {

	private static IControladorUsuario controladorUsuario;
	
	private static ManejadorUsuarios manejadorU;
	private static ManejadorOfertaLaboral manejadorOL;
	private static ManejadorPublicaciones manejadorP;
	
	
	@BeforeAll
	public static void iniciar() {
		Fabrica fabrica = Fabrica.getInstance();
		controladorUsuario = fabrica.getIControladorUsuario();
		
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

	// consultarUsuario() -> OK
	@Test
	void testConsultarUsuario_OK() {
		String nicknameTest = "nicknameEmpresaUno";
		String nombreTest = "nombrePersonaEmpresaUno";
		String apellidoTest = "apellidoEmpresaUno";
		String emailTest = "emailEmpresaUno";
		String nombreEmpresaTest = "nombreEmpresaUno";
		String descripcionTest = "descripcionEmpresaUno";
		String contraseñaEmpresa1 = "contraseñaEmpresa1";
		String linkWebTest = "linkWebEmpresaUno";
		String imagenTest = "imagen.png";
		
		try {
			controladorUsuario.altaEmpresa(nicknameTest, nombreTest, apellidoTest, emailTest, contraseñaEmpresa1, nombreEmpresaTest, descripcionTest, linkWebTest, imagenTest);
			
			DTUsuario usuarioConsultado = controladorUsuario.consultarUsuario(nicknameTest);
			
			assertEquals(usuarioConsultado.getNickname(), nicknameTest);
			assertEquals(usuarioConsultado.getNombre(), nombreTest);
			assertEquals(usuarioConsultado.getApellido(), apellidoTest);
			assertEquals(usuarioConsultado.getCorreo(), emailTest);
			assertEquals(((DTEmpresa) usuarioConsultado).getNombreEmpresa(), nombreEmpresaTest);
			assertEquals(((DTEmpresa) usuarioConsultado).getDescripcion(), descripcionTest);
			assertEquals(((DTEmpresa) usuarioConsultado).getLinkWeb(), linkWebTest);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	// consultarUsuario() -> nickname no existe
	@Test
	void testConsultarUsuario_NoExiste() {
		String nicknameUsuarioQueNoExiste = "eu nao esisto";
		
		// testeo sobre la coleccion vacia
		assertThrows(NicknameNoExisteException.class, () -> controladorUsuario.consultarUsuario(nicknameUsuarioQueNoExiste));
		
		// le cargo un usuario al sistema
		String nicknameTest = "nicknameEmpresaUno";
		String nombreTest = "nombrePersonaEmpresaUno";
		String apellidoTest = "apellidoEmpresaUno";
		String emailTest = "emailEmpresaUno";
		String nombreEmpresaTest = "nombreEmpresaUno";
		String descripcionTest = "descripcionEmpresaUno";
		String contraseñaEmpresa1 = "contraseñaEmpresa1";
		String linkWebTest = "linkWebEmpresaUno";
		String imagenTest = "imagen.png";
		try {
			controladorUsuario.altaEmpresa(nicknameTest, nombreTest, apellidoTest, emailTest, contraseñaEmpresa1, nombreEmpresaTest, descripcionTest, linkWebTest, imagenTest);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		// testeo sobre una coleccion con 1 usuario en el sistema
		assertThrows(NicknameNoExisteException.class, () -> controladorUsuario.consultarUsuario(nicknameUsuarioQueNoExiste));
	}
	
	// listarUsuarios() -> una empresa
	@Test
	void testListarUsuarios_UnUsuarioEmpresa() {
		// le cargo un usuario al sistema
		String nicknameTest = "nicknameEmpresaUno";
		String nombreTest = "nombrePersonaEmpresaUno";
		String apellidoTest = "apellidoEmpresaUno";
		String emailTest = "emailEmpresaUno";
		String nombreEmpresaTest = "nombreEmpresaUno";
		String descripcionTest = "descripcionEmpresaUno";
		String contraseñaEmpresa1 = "contraseñaEmpresa1";
		String linkWebTest = "linkWebEmpresaUno";
		String imagenTest = "imagen.png";
		try {
			controladorUsuario.altaEmpresa(nicknameTest, nombreTest, apellidoTest, emailTest, contraseñaEmpresa1, nombreEmpresaTest, descripcionTest, linkWebTest, imagenTest);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		List<DTUsuario> listaUsuarios = controladorUsuario.listarUsuarios();
		DTUsuario primerUsuario = listaUsuarios.get(0);
		assertEquals(listaUsuarios.size(), 1);
		assertEquals(primerUsuario.getNickname(), nicknameTest);
		assertEquals(primerUsuario.getNombre(), nombreTest);
		assertEquals(primerUsuario.getApellido(), apellidoTest);
		assertEquals(primerUsuario.getCorreo(), emailTest);
		assertEquals(((DTEmpresa) primerUsuario).getNombreEmpresa(), nombreEmpresaTest);
		assertEquals(((DTEmpresa) primerUsuario).getDescripcion(), descripcionTest);
		assertEquals(((DTEmpresa) primerUsuario).getLinkWeb(), linkWebTest);
	}

	// listarUsuarios() -> un postulante
	@Test
	void testListarUsuarios_UnUsuarioPostulante() {
		// le cargo un usuario al sistema
		String nicknameTest = "nicknamePostulanteUno";
		String nombreTest = "nombrePersonaPostulanteUno";
		String apellidoTest = "apellidoPostulanteUno";
		String emailTest = "emailPostulanteUno";
		String contraseñaPostulante1 = "contraseñaPostulante1";
		String fechaNacimientoTest = "01/10/2023";
		String nacionalidadTest = "nacionalidadPostulanteUno";
		String imagenTest = "imagen.png";
		try {
			controladorUsuario.altaPostulante(nicknameTest, nombreTest, apellidoTest, emailTest, contraseñaPostulante1, fechaNacimientoTest, nacionalidadTest, imagenTest);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		List<DTUsuario> listaUsuarios = controladorUsuario.listarUsuarios();
		DTUsuario primerUsuario = listaUsuarios.get(0);
		assertEquals(listaUsuarios.size(), 1);
		assertEquals(primerUsuario.getNickname(), nicknameTest);
		assertEquals(primerUsuario.getNombre(), nombreTest);
		assertEquals(primerUsuario.getApellido(), apellidoTest);
		assertEquals(primerUsuario.getCorreo(), emailTest);
		assertEquals(((DTPostulante) primerUsuario).getFechaNacimiento(), LocalDate.parse(fechaNacimientoTest, DateTimeFormatter.ofPattern("d/M/yyyy")));
		assertEquals(((DTPostulante) primerUsuario).getNacionalidad(), nacionalidadTest);
	}
	
	// listarUsuarios() -> sin usuarios
	@Test
	void testListarUsuarios_SinUsuarios() {
		assertEquals(controladorUsuario.listarUsuarios().size(), 0);
	}
	
	// listarEmpresas() -> sin usuarios
	@Test
	void testListarEmpresas_SinUsuarios() {
		assertEquals(controladorUsuario.listarEmpresas().size(), 0);
	}
	
	// listarEmpresas() -> sin empresas (un postulante en el sistema)
	@Test
	void testListarEmpresas_SinEmpresas() {
		// le cargo un postulante al sistema
		String nicknameTest = "nicknamePostulanteUno";
		String nombreTest = "nombrePersonaPostulanteUno";
		String apellidoTest = "apellidoPostulanteUno";
		String emailTest = "emailPostulanteUno";
		String contraseñaPostulante1 = "contraseñaPostulante1";
		String fechaNacimientoTest = "01/10/2023";
		String nacionalidadTest = "nacionalidadPostulanteUno";
		String imagenTest = "imagen.png";
		try {
			controladorUsuario.altaPostulante(nicknameTest, nombreTest, apellidoTest, emailTest, contraseñaPostulante1, fechaNacimientoTest, nacionalidadTest, imagenTest);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		assertEquals(controladorUsuario.listarEmpresas().size(), 0);
	}
	
	// listarPostulantes() -> sin usuarios
	@Test
	void testListarPostulantes_SinUsuarios() {
		assertEquals(controladorUsuario.listarPostulantes().size(), 0);
	}

	// listarPostulantes() -> un postulante 
	@Test
	void testListarPostulantes_UnPostulante() {
		// le cargo un postulante al sistema
		String nicknameTest = "nicknamePostulanteUno";
		String nombreTest = "nombrePersonaPostulanteUno";
		String apellidoTest = "apellidoPostulanteUno";
		String emailTest = "emailPostulanteUno";
		String contraseñaPostulante1 = "contraseñaPostulante1";
		String fechaNacimientoTest = "01/10/2023";
		String nacionalidadTest = "nacionalidadPostulanteUno";
		String imagenTest = "imagen.png";
		try {
			controladorUsuario.altaPostulante(nicknameTest, nombreTest, apellidoTest, emailTest, contraseñaPostulante1, fechaNacimientoTest, nacionalidadTest, imagenTest);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		List<DTPostulante> listaPostulantes = controladorUsuario.listarPostulantes();
		DTPostulante primerPostulante = listaPostulantes.get(0);
		assertEquals(listaPostulantes.size(), 1);
		assertEquals(primerPostulante.getNickname(), nicknameTest);
		assertEquals(primerPostulante.getNombre(), nombreTest);
		assertEquals(primerPostulante.getApellido(), apellidoTest);
		assertEquals(primerPostulante.getCorreo(), emailTest);
		assertEquals(primerPostulante.getFechaNacimiento(), LocalDate.parse(fechaNacimientoTest, DateTimeFormatter.ofPattern("d/M/yyyy")));
		assertEquals(primerPostulante.getNacionalidad(), nacionalidadTest);
	}

	// obtenerOfertasDeEmpresa() -> sin ofertas
	@Test
	void testObtenerOfertasDeEmpresa_SinOfertas() {
		// le cargo una empresa al sistema
		String nicknameTest = "nicknameEmpresaUno";
		String nombreTest = "nombrePersonaEmpresaUno";
		String apellidoTest = "apellidoEmpresaUno";
		String emailTest = "emailEmpresaUno";
		String contraseñaPostulante1 = "contraseñaPostulante1";
		String nombreEmpresaTest = "nombreEmpresaUno";
		String descripcionTest = "descripcionEmpresaUno";
		String linkWebTest = "linkWebEmpresaUno";
		String imagenTest = "imagen.png";
		try {
			controladorUsuario.altaEmpresa(nicknameTest, nombreTest, apellidoTest, emailTest, contraseñaPostulante1, nombreEmpresaTest, descripcionTest, linkWebTest, imagenTest);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		try {
			assertEquals(controladorUsuario.obtenerOfertasDeEmpresa(nicknameTest).size(), 0);
		} catch (NicknameNoExisteException | UsuarioNoEsEmpresaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	// obtenerOfertasDeEmpresa() -> no existe emmpresa con ese nick
	@Test
	void testObtenerOfertasDeEmpresa_NoExisteEmpresa() {
		String nicknameDeEmpresaQueNoExiste = "eu nao esisto";
		assertThrows(NicknameNoExisteException.class, () -> controladorUsuario.obtenerOfertasDeEmpresa(nicknameDeEmpresaQueNoExiste));
	}
	
	// obtenerOfertasDeEmpresa() -> el usuario asociado al nick no es una empresa
	@Test
	void testObtenerOfertasDeEmpresa_NoEsEmpresa() {
		// le cargo un postulante al sistema
		String nicknameTest = "nicknamePostulanteUno";
		String nombreTest = "nombrePersonaPostulanteUno";
		String apellidoTest = "apellidoPostulanteUno";
		String emailTest = "emailPostulanteUno";
		String contraseñaPostulante1 = "contraseñaPostulante1";
		String fechaNacimientoTest = "01/10/2023";
		String nacionalidadTest = "nacionalidadPostulanteUno";
		String imagenTest = "imagen.png";
		try {
			controladorUsuario.altaPostulante(nicknameTest, nombreTest, apellidoTest, emailTest, contraseñaPostulante1, fechaNacimientoTest, nacionalidadTest, imagenTest);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		assertThrows(UsuarioNoEsEmpresaException.class, () -> controladorUsuario.obtenerOfertasDeEmpresa(nicknameTest));
	}
	
	// altaPostulante() -> nickname repetido
	@Test 
	void testAltaPostulante_NicknameRepetido() {
		// le cargo un postulante al sistema
		String nicknameTest = "nicknamePostulanteUno";
		String nombreTest = "nombrePersonaPostulanteUno";
		String apellidoTest = "apellidoPostulanteUno";
		String emailTest = "emailPostulanteUno";
		String contraseñaPostulante1 = "contraseñaPostulante1";
		String fechaNacimientoTest = "01/10/2023";
		String nacionalidadTest = "nacionalidadPostulanteUno";
		String imagenTest = "imagen.png";
		try {
			controladorUsuario.altaPostulante(nicknameTest, nombreTest, apellidoTest, emailTest, contraseñaPostulante1, fechaNacimientoTest, nacionalidadTest, imagenTest);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		// intento cargar un postulante con el mismo nickname
		String nicknameTestRepetido = "nicknamePostulanteUno";
		String nombreTest2 = "nombrePersonaPostulanteDos";
		String apellidoTest2 = "apellidoPostulanteDos";
		String emailTest2 = "emailPostulanteDos";
		String contraseñaPostulante2 = "contraseñaPostulante2";
		String fechaNacimientoTest2 = "01/10/2023";
		String nacionalidadTest2 = "nacionalidadPostulanteDos";
		String imagenTest2 = "imagen2.png";
		
		assertThrows(UsuarioRepetidoException.class, () -> controladorUsuario.altaPostulante(nicknameTestRepetido, nombreTest2, apellidoTest2, emailTest2, contraseñaPostulante2,fechaNacimientoTest2, nacionalidadTest2, imagenTest2));
	}
	
	// altaPostulante() -> correo repetido
	@Test 
	void testAltaPostulante_CorreoRepetido() {
		// le cargo un postulante al sistema
		String nicknameTest = "nicknamePostulanteUno";
		String nombreTest = "nombrePersonaPostulanteUno";
		String apellidoTest = "apellidoPostulanteUno";
		String emailTest = "emailPostulanteUno";
		String contraseñaPostulante1 = "contraseñaPostulante1";
		String fechaNacimientoTest = "01/10/2023";
		String nacionalidadTest = "nacionalidadPostulanteUno";
		String imagenTest = "imagen.png";
		try {
			controladorUsuario.altaPostulante(nicknameTest, nombreTest, apellidoTest, emailTest, contraseñaPostulante1, fechaNacimientoTest, nacionalidadTest, imagenTest);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		// intento cargar un postulante con el mismo nickname
		String nicknameTest2 = "nicknamePostulanteDos";
		String nombreTest2 = "nombrePersonaPostulanteDos";
		String apellidoTest2 = "apellidoPostulanteDos";
		String emailTestRepetido = "emailPostulanteUno";
		String contraseñaPostulante2 = "contraseñaPostulante2";
		String fechaNacimientoTest2 = "01/10/2023";
		String nacionalidadTest2 = "nacionalidadPostulanteDos";
		String imagenTest2 = "imagen2.png";
		
		assertThrows(CorreoRepetidoException.class, () -> controladorUsuario.altaPostulante(nicknameTest2, nombreTest2, apellidoTest2, emailTestRepetido, contraseñaPostulante2,fechaNacimientoTest2, nacionalidadTest2, imagenTest2));
	}

	// altaEmpresa() -> nickname repetido
	@Test 
	void testAltaEmpresa_NicknameRepetido() {
		// le cargo un postulante al sistema
		String nicknameTest = "nicknamePostulanteUno";
		String nombreTest = "nombrePersonaPostulanteUno";
		String apellidoTest = "apellidoPostulanteUno";
		String emailTest = "emailPostulanteUno";
		String contraseñaPostulante1 = "contraseñaPostulante1";
		String fechaNacimientoTest = "01/10/2023";
		String nacionalidadTest = "nacionalidadPostulanteUno";
		String imagenTest = "imagen.png";
		try {
			controladorUsuario.altaPostulante(nicknameTest, nombreTest, apellidoTest, emailTest, contraseñaPostulante1, fechaNacimientoTest, nacionalidadTest, imagenTest);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		

		// intento cargar una empresa con el mismo correo
		String nicknameTestRepetido = "nicknamePostulanteUno";
		String nombreTest2 = "nombrePersonaEmpresaDos";
		String apellidoTest2 = "apellidoEmpresaDos";
		String emailTest2 = "emailEmpresaDos";
		String contraseña2 = "contraseñaPostulante2";
		String nombreEmpresaTest2 = "nombreEmpresaDos";
		String descripcionTest2 = "descripcionEmpresaDos";
		String linkWebTest2 = "linkWebEmpresaDos";
		String imagenTest2 = "imagen2.png";
		
		assertThrows(UsuarioRepetidoException.class, () -> controladorUsuario.altaEmpresa(nicknameTestRepetido, nombreTest2, apellidoTest2, emailTest2, contraseña2, nombreEmpresaTest2, descripcionTest2, linkWebTest2, imagenTest2));
	}
	
	// altaEmpresa() -> correo repetido
	@Test 
	void testAltaEmpresa_CorreoRepetido() {
		// le cargo un postulante al sistema
		String nicknameTest = "nicknamePostulanteUno";
		String nombreTest = "nombrePersonaPostulanteUno";
		String apellidoTest = "apellidoPostulanteUno";
		String emailTest = "emailPostulanteUno";
		String contraseñaPostulante1 = "contraseñaPostulante1";
		String fechaNacimientoTest = "01/10/2023";
		String nacionalidadTest = "nacionalidadPostulanteUno";
		String imagenTest = "imagen.png";
		try {
			controladorUsuario.altaPostulante(nicknameTest, nombreTest, apellidoTest, emailTest, contraseñaPostulante1, fechaNacimientoTest, nacionalidadTest, imagenTest);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		// intento cargar una empresa con el mismo correo
		String nicknameTest2 = "nicknameEmpresaDos";
		String nombreTest2 = "nombrePersonaEmpresaDos";
		String apellidoTest2 = "apellidoEmpresaDos";
		String emailTestRepetido = "emailPostulanteUno";
		String contraseña2 = "contraseñaPostulante2";
		String nombreEmpresaTest2 = "nombreEmpresaDos";
		String descripcionTest2 = "descripcionEmpresaDos";
		String linkWebTest2 = "linkWebEmpresaDos";
		String imagenTest2 = "imagen2.png";
		
		assertThrows(CorreoRepetidoException.class, () -> controladorUsuario.altaEmpresa(nicknameTest2, nombreTest2, apellidoTest2, emailTestRepetido, contraseña2, nombreEmpresaTest2, descripcionTest2, linkWebTest2, imagenTest2));
		
	}
	
	// consultarUsuarioPorCorreo() -> OK
	@Test
	void testConsultarUsuarioPorCorreo_OK() {
		// le cargo un postulante al sistema
		String nicknameTest = "nicknamePostulanteUno";
		String nombreTest = "nombrePersonaPostulanteUno";
		String apellidoTest = "apellidoPostulanteUno";
		String emailTest = "emailPostulanteUno";
		String contraseñaPostulante1 = "contraseñaPostulante1";
		String fechaNacimientoTest = "01/10/2023";
		String nacionalidadTest = "nacionalidadPostulanteUno";
		String imagenTest = "imagen.png";
		try {
			controladorUsuario.altaPostulante(nicknameTest, nombreTest, apellidoTest, emailTest, contraseñaPostulante1, fechaNacimientoTest, nacionalidadTest, imagenTest);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		DTUsuario usuarioConsultado = null;
		try {
			usuarioConsultado = controladorUsuario.consultarUsuarioPorCorreo(emailTest);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		assertEquals(usuarioConsultado.getNickname(), nicknameTest);
	}
	
	// consultarUsuarioPorCorreo() -> correo no existe
	@Test
	void testConsultarUsuarioPorCorreo_CorreoNoExiste() {
		String correoQueNoExiste = "no existo";
		assertThrows(CorreoNoEncontradoException.class, () -> controladorUsuario.consultarUsuarioPorCorreo(correoQueNoExiste));
	}
	
	// usuarioExiste() -> true
	@Test
	void testUsuarioExiste_SiExiste() {
		// le cargo un postulante al sistema
		String nicknameTest = "nicknamePostulanteUno";
		String nombreTest = "nombrePersonaPostulanteUno";
		String apellidoTest = "apellidoPostulanteUno";
		String emailTest = "emailPostulanteUno";
		String contraseñaPostulante1 = "contraseñaPostulante1";
		String fechaNacimientoTest = "01/10/2023";
		String nacionalidadTest = "nacionalidadPostulanteUno";
		String imagenTest = "imagen.png";
		try {
			controladorUsuario.altaPostulante(nicknameTest, nombreTest, apellidoTest, emailTest, contraseñaPostulante1, fechaNacimientoTest, nacionalidadTest, imagenTest);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		assertTrue(() -> controladorUsuario.usuarioExiste(emailTest));
	}
	

	// usuarioExiste() -> false
	@Test
	void testUsuarioExiste_NoExiste() {
		String correoQueNoExiste = "no existo";
		assertFalse(() -> controladorUsuario.usuarioExiste(correoQueNoExiste));
	}
	
	// validarUsuario() -> true
	@Test
	void testValidarUsuario_True() {
		// le cargo un postulante al sistema
		String nicknameTest = "nicknamePostulanteUno";
		String nombreTest = "nombrePersonaPostulanteUno";
		String apellidoTest = "apellidoPostulanteUno";
		String emailTest = "emailPostulanteUno";
		String contraseñaPostulante1 = "contraseñaPostulante1";
		String fechaNacimientoTest = "01/10/2023";
		String nacionalidadTest = "nacionalidadPostulanteUno";
		String imagenTest = "imagen.png";
		try {
			controladorUsuario.altaPostulante(nicknameTest, nombreTest, apellidoTest, emailTest, contraseñaPostulante1, fechaNacimientoTest, nacionalidadTest, imagenTest);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}

		Boolean validez = null;
		try {
			validez = controladorUsuario.validarUsuario(emailTest, contraseñaPostulante1);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		assertTrue(validez);
	}
	
	// validarUsuario() -> false
	@Test
	void testValidarUsuario_False() {
		// le cargo un postulante al sistema
		String nicknameTest = "nicknamePostulanteUno";
		String nombreTest = "nombrePersonaPostulanteUno";
		String apellidoTest = "apellidoPostulanteUno";
		String emailTest = "emailPostulanteUno";
		String contraseñaPostulante1 = "contraseñaPostulante1";
		String fechaNacimientoTest = "01/10/2023";
		String nacionalidadTest = "nacionalidadPostulanteUno";
		String imagenTest = "imagen.png";
		try {
			controladorUsuario.altaPostulante(nicknameTest, nombreTest, apellidoTest, emailTest, contraseñaPostulante1, fechaNacimientoTest, nacionalidadTest, imagenTest);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}

		String contraseñaIncorrecta = "contraseñaIncorrecta";
		Boolean validez = null;
		try {
			validez = controladorUsuario.validarUsuario(emailTest, contraseñaIncorrecta);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		assertFalse(validez);
	}
	
	// actualizarDatosPostulante() -> OK
	@Test
	void testActualizarDatosPostulante_Ok() {
		// le cargo un postulante al sistema
		String nicknameTest = "nicknamePostulanteUno";
		String nombreTest = "nombrePersonaPostulanteUno";
		String apellidoTest = "apellidoPostulanteUno";
		String emailTest = "emailPostulanteUno";
		String contraseñaPostulante1 = "contraseñaPostulante1";
		String fechaNacimientoTest = "01/10/2023";
		String nacionalidadTest = "nacionalidadPostulanteUno";
		String imagenTest = "imagen.png";
		try {
			controladorUsuario.altaPostulante(nicknameTest, nombreTest, apellidoTest, emailTest, contraseñaPostulante1, fechaNacimientoTest, nacionalidadTest, imagenTest);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		// le cambio los datos al postulante
		String nombreCambiado = "nombrePersonaPostulanteDos";
		String apellidoCambiado = "apellidoPostulanteDos";
		String fechaNacimientoCambiado = "2022-10-15";
		String nacionalidadCambiado = "nacionalidadPostulanteDos";
		String imagenCambiado = "imagenCambiada.png";
		controladorUsuario.actualizarDatosPostulante(nicknameTest, nombreCambiado, apellidoCambiado, fechaNacimientoCambiado, nacionalidadCambiado, imagenCambiado);
		
		// checkeo que este bien
		LocalDate fechaNacimientoCambiadoParseado = LocalDate.parse(fechaNacimientoCambiado, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		DTUsuario usuario = null;
		try {
			usuario = controladorUsuario.consultarUsuario(nicknameTest);
		} catch (NicknameNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		assertEquals(usuario.getNombre(), nombreCambiado);
		assertEquals(usuario.getApellido(), apellidoCambiado);
		assertEquals(((DTPostulante) usuario).getFechaNacimiento(), fechaNacimientoCambiadoParseado);
		assertEquals(((DTPostulante) usuario).getNacionalidad(), nacionalidadCambiado);
		assertEquals(usuario.getUrlImagen(), imagenCambiado);
	}
	
	// actualizarDatosEmpresa() -> OK
	@Test
	void testActualizarDatosEmpresa_Ok() {
		// le cargo una empresa al sistema
		String nicknameTest = "nicknameEmpresaUno";
		String nombreTest = "nombrePersonaEmpresaUno";
		String apellidoTest = "apellidoEmpresaUno";
		String emailTest = "emailEmpresaUno";
		String contraseñaPostulante1 = "contraseñaPostulante1";
		String nombreEmpresaTest = "nombreEmpresaUno";
		String descripcionTest = "descripcionEmpresaUno";
		String linkWebTest = "linkWebEmpresaUno";
		String imagenTest = "imagen.png";
		try {
			controladorUsuario.altaEmpresa(nicknameTest, nombreTest, apellidoTest, emailTest, contraseñaPostulante1, nombreEmpresaTest, descripcionTest, linkWebTest, imagenTest);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		// le cambio los datos a la empresa
		String nombreCambiado = "nombrePersonaEmpresaDos";
		String apellidoCambiado = "apellidoEmpresaDos";
		String nombreEmpresaCambiado = "nombreEmpresaDos";
		String descripcionCambiado = "descripcionEmpresaDos"; 
		String linkWebCambiado = "linkWebEmpresaDos";
		String imagenCambiado = "imagenCambiado.png";
		controladorUsuario.actualizarDatosEmpresa(nicknameTest, nombreCambiado, apellidoCambiado, nombreEmpresaCambiado, descripcionCambiado, linkWebCambiado, imagenCambiado);
		
		// checkeo que esta bien
		DTUsuario usuario = null;
		try {
			usuario = controladorUsuario.consultarUsuario(nicknameTest);
		} catch (NicknameNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		assertEquals(usuario.getNombre(), nombreCambiado);
		assertEquals(usuario.getApellido(), apellidoCambiado);
		assertEquals(((DTEmpresa) usuario).getNombreEmpresa(), nombreEmpresaCambiado);
		assertEquals(((DTEmpresa) usuario).getDescripcion(), descripcionCambiado);
		assertEquals(((DTEmpresa) usuario).getLinkWeb(), linkWebCambiado);
		assertEquals(usuario.getUrlImagen(), imagenCambiado);
	}

}
