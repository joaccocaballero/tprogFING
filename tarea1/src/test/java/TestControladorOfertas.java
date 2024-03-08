package test.java;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
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

public class TestControladorOfertas {

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
	
	
	@SuppressWarnings("unused")
	@Test
	public void testContructoresVacios() {
		Paquete paqueteVacio = new Paquete();
		OfertaLaboral ofertaVacia = new OfertaLaboral();
		Empresa empresaVacia = new Empresa();
		Postulante postulanteVacio = new Postulante();
		Keyword keywordVacia = new Keyword();
		Postulacion postulacionVacia = new Postulacion();
		Publicacion publicacionVacia = new Publicacion();
		TipoPublicacion tipoVacio = new TipoPublicacion();
	}
	
	// tests altaKeyword()
	@Test
	public void testAltaKeyword_OK() {
		String nombreKeyword = "llavepalabra";
		try {
			controladorOfertas.altaKeyword(nombreKeyword);
		} catch (KeywordExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		List<String> keywords = manejadorOL.obtenerKeywords();
		
		assertEquals(keywords.size(), 1);
		assertEquals(keywords.get(0), nombreKeyword);
	}
	
	@Test
	public void testAltaKeyword_Duplicada() {
		String nombreKeyword = "llavepalabra";
		try {
			controladorOfertas.altaKeyword(nombreKeyword);
		} catch (KeywordExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		List<String> keywords = manejadorOL.obtenerKeywords();
		assertEquals(keywords.size(), 1);
		
		assertThrows(KeywordExisteException.class, () -> controladorOfertas.altaKeyword(nombreKeyword));
	}
	
	// tests obtenerKeywords()
	@Test
	public void testObtenerKeywords_SinKeywords() {
		assertEquals(controladorOfertas.obtenerKeywords().size(), 0);
	}
	
	@Test
	public void testObtenerKeywords_UnaKeyword() {
		String nombreKeyword = "llavepalabra";
		try {
			controladorOfertas.altaKeyword(nombreKeyword);
		} catch (KeywordExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		List<String> keywords = controladorOfertas.obtenerKeywords();
		
		assertEquals(keywords.size(), 1);
		assertEquals(keywords.get(0), nombreKeyword);
	}
	
	@Test
	public void testObtenerKeywords_DosKeywords() {
		String nombreKeyword1 = "llavepalabra";
		String nombreKeyword2 = "llavepalabra2";
		try {
			controladorOfertas.altaKeyword(nombreKeyword1);
			controladorOfertas.altaKeyword(nombreKeyword2);
		} catch (KeywordExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		List<String> keywords = controladorOfertas.obtenerKeywords();

		assertEquals(keywords.size(), 2);
		assertTrue(keywords.stream().anyMatch(keyword -> keyword.equals(nombreKeyword1)));
		assertTrue(keywords.stream().anyMatch(keyword -> keyword.equals(nombreKeyword2)));
	}
	
	// tests postularAOferta()
	@Test
	public void testPostularAOferta_OK() {
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
		
		// le cargo un tipo de publicacion al sistema
		String nombreTipoPublicacion1 = "nombreTipoPublicacionUno";
		String descripcionTipoPublicacion1 = "descripcionTipoPublicacionUno";
		int exposicionTipoPublicacion1 = 68;
		Integer duracionTipoPublicacion1 = 24;
		Integer costoTipoPublicacion1 = 9001;
		LocalDate fechaTipoPublicacion1 = LocalDate.now();
		try {
			controladorPublicaciones.altaTipoPublicacionOL(nombreTipoPublicacion1, descripcionTipoPublicacion1, exposicionTipoPublicacion1, duracionTipoPublicacion1, costoTipoPublicacion1, fechaTipoPublicacion1);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		assertEquals(controladorPublicaciones.obtenerTipos().size(), 1);
		
		// le cargo una oferta al sistema
		String nombreOferta1 = "nombreOfertaUno";
		String descripcionOferta1 = "descripcionOfertaUno";
		String remuneracionOferta1 = "remuneracionOfertaUno";
		String horarioOferta1 = "horarioOfertaUno";
		List<String> keywordsOferta1 = new ArrayList<>();
		String ciudadOferta1 = "ciudadOfertaUno";
		String departamentoOferta1 = "departamentoOfertaUno";
		String tipoPostulacionOferta1 = nombreTipoPublicacion1;
		String nicknameEmpresaOferta1 = nicknameEmpresa1;
		try {
			controladorOfertas.altaOferta(nombreOferta1, descripcionOferta1, remuneracionOferta1, horarioOferta1, keywordsOferta1, ciudadOferta1, departamentoOferta1, tipoPostulacionOferta1, nicknameEmpresaOferta1);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		assertEquals(manejadorOL.getCantidadOfertas(), 1);
		
		// ahora si, postulo a la oferta
		String nombreOfertaPostulacion1 = nombreOferta1;
		String nicknamePostulantePostulacion1 = nicknamePostulante1;
		String cvReducidoPostulacion1 = "cvReducidoPostulacionUno";
		String motivacionPostulacion1 = "motivacionPostulacionUno";
		String fechaPostulacion1 = "01/10/2023";
		String urlVideo = "https://www.youtube.com/watch?v=dQw4w9WgXcQ";
		
		// parseo la fecha para que tenga el formato correcto
				String fechaPostulacion1Parseada = LocalDate.parse(fechaPostulacion1, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
						.atStartOfDay()
						.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
				
		try {
			controladorOfertas.postularAOferta(nombreOfertaPostulacion1, nicknamePostulantePostulacion1, cvReducidoPostulacion1, motivacionPostulacion1, fechaPostulacion1Parseada, urlVideo);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}

		// checkeo si esta todo bien desde postulante
		List<DTPostulacion> postulacionesDePostulante = null;
		try {
			postulacionesDePostulante = ((DTPostulante) controladorUsuarios.consultarUsuario(nicknamePostulante1)).getPostulaciones();
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		assertEquals(postulacionesDePostulante.size(), 1);
		assertEquals(postulacionesDePostulante.get(0).getNicknamePostulante(), nicknamePostulantePostulacion1);
		assertEquals(postulacionesDePostulante.get(0).getNombreOfertaLaboral(), nombreOfertaPostulacion1);
		assertEquals(postulacionesDePostulante.get(0).getCvReducido(), cvReducidoPostulacion1);
		assertEquals(postulacionesDePostulante.get(0).getMotivacion(), motivacionPostulacion1);
		assertEquals(postulacionesDePostulante.get(0).getFecha(), LocalDateTime.parse(fechaPostulacion1Parseada, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
		
		// checkeo si esta todo bien desde oferta laboral
		List<DTPostulacion> postulacionesAOferta = null;
		try {
			postulacionesAOferta = controladorOfertas.obtenerDatosOferta(nombreOferta1).getPostulaciones();
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		assertEquals(postulacionesAOferta.size(), 1);
		assertEquals(postulacionesAOferta.get(0).getNicknamePostulante(), nicknamePostulantePostulacion1);
		assertEquals(postulacionesAOferta.get(0).getNombreOfertaLaboral(), nombreOfertaPostulacion1);
		assertEquals(postulacionesAOferta.get(0).getCvReducido(), cvReducidoPostulacion1);
		assertEquals(postulacionesAOferta.get(0).getMotivacion(), motivacionPostulacion1);
		assertEquals(postulacionesAOferta.get(0).getFecha(), LocalDateTime.parse(fechaPostulacion1Parseada, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
	}
	
	@Test
	public void testPostularAOferta_NoExistePostulante() {
		String nicknamePostulanteQueNoExiste = "eu nao esisto";
		String nombreOferta = "nombreOfertaAPostular";
		String cvReducido = "cvReducidoPostulacion";
		String motivacion = "motivacionPostulacion";
		String fecha = "01/10/2023";
		String urlVideo = "https://www.youtube.com/watch?v=dQw4w9WgXcQ";
		
		// parseo la fecha para que tenga el formato correcto
		String fechaParseada = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
				.atStartOfDay()
				.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
		
		assertThrows(NicknameNoExisteException.class, () -> controladorOfertas.postularAOferta(nombreOferta, nicknamePostulanteQueNoExiste, cvReducido, motivacion, fechaParseada, urlVideo));
	}
	
	@Test
	void testAltaOferta_OK() throws NombreExisteException, KeywordExisteException, UsuarioRepetidoException, TipoPublicExisteException {
		//empresa
		String nicknameTest = "nickname";
		String apellidoTest = "apellidoEmpresaUno";
		String emailTest = "emailEmpresaUno";
		String nombreEmpresaTest = "empresa";
		String descripcionTest = "descripcionEmpresaUno";
		String contraseñaEmpresa1 = "contraseñaEmpresa1";
		String linkWebTest = "linkWebEmpresaUno";
		String imagenTest = "imagenUno.png";
		
		String nombreTest = "testOferta";
		String descTest = "desc";
		List<String> keys = new ArrayList<String>();
		
		//tipo
		String nombreTipo = "tipo";
		String descTipo = "descTipo";
		int exposicion = 24;
		int duracion = 1;
		int costo = 1;
		LocalDate fecha = LocalDate.now();
		
//		(String nombre, String descripcion, String exposicion, Integer duracion, Integer CostoPublic, LocalDate fecha) throws TipoPublicExisteException
		try {
			controladorPublicaciones.altaTipoPublicacionOL(nombreTipo, descTipo, exposicion, duracion, costo, fecha);

			controladorUsuarios.altaEmpresa(nicknameTest, nombreTest, apellidoTest, emailTest, contraseñaEmpresa1 ,nombreEmpresaTest, descTest, linkWebTest, imagenTest);
			controladorOfertas.altaOferta(nombreEmpresaTest, descripcionTest, nombreTest, apellidoTest, keys, emailTest, descripcionTest, nombreTipo, nicknameTest);			
			
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testAltaOferta_keys() throws NombreExisteException, KeywordExisteException, UsuarioRepetidoException, TipoPublicExisteException, OfertaNoExisteException, NicknameNoExisteException {
		//empresa
		String nicknameTest = "nickname";
		String apellidoTest = "apellidoEmpresaUno";
		String emailTest = "emailEmpresaUno";
		String nombreEmpresaTest = "empresa";
		String descripcionTest = "descripcionEmpresaUno";
		String contraseñaEmpresa1 = "contraseñaEmpresa1";
		String linkWebTest = "linkWebEmpresaUno";
		String imagenTest = "imagenUno.png";
		
		String remuTest = "10 peso";
		String horaTest = "10-18";
		String nombreTest = "testOferta";
		String descTest = "desc";
		List<String> keys = new ArrayList<String>();
		
		keys.add("key");
		keys.add("word");
		

		//tipo
		String nombreTipo = "tipo";
		String descTipo = "descTipo";
		int exposicion = 24;
		int duracion = 1;
		int costo = 1;
		LocalDate fecha = LocalDate.now();
		
//		(String nombre, String descripcion, String exposicion, Integer duracion, Integer CostoPublic, LocalDate fecha) throws TipoPublicExisteException
		try {
			controladorOfertas.altaKeyword("key");
			controladorOfertas.altaKeyword("word");
			controladorPublicaciones.altaTipoPublicacionOL(nombreTipo, descTipo, exposicion, duracion, costo, fecha);

			controladorUsuarios.altaEmpresa(nicknameTest, nombreTest, apellidoTest, emailTest,contraseñaEmpresa1,nombreEmpresaTest, descTest, linkWebTest, imagenTest);
			controladorOfertas.altaOferta(nombreEmpresaTest, descripcionTest, remuTest, horaTest, keys, emailTest, descripcionTest, nombreTipo, nicknameTest);			
			
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		DTOferta oferta = manejadorOL.obtenerOfertaLaboral(nombreEmpresaTest);
		Empresa emp = (Empresa) manejadorU.getUsuario(nicknameTest);
		
		Set<DTOferta> ofertas = emp.getOfertas();
		assertEquals(ofertas.isEmpty(), false);
		
		System.out.println(emp.getOfertas().isEmpty());
		assertEquals(oferta.getDescripcion(), descripcionTest);
		assertEquals(oferta.getNombre(), nombreEmpresaTest);
		assertEquals(oferta.getRemuneracion(), remuTest);
		assertEquals(oferta.getHorario(), horaTest);
	
	}
	
	@Test
	void testAltaOferta_keysThrow() throws NombreExisteException, KeywordExisteException, UsuarioRepetidoException, TipoPublicExisteException {
		//empresa
		String nicknameTest = "nickname";
		String apellidoTest = "apellidoEmpresaUno";
		String emailTest = "emailEmpresaUno";
		String nombreEmpresaTest = "empresa";
		String descripcionTest = "descripcionEmpresaUno";
		String contraseñaEmpresa1 = "contraseñaEmpresa1";
		String linkWebTest = "linkWebEmpresaUno";
		String imagenTest = "imagenUno.png";
		
		String nombreTest = "testOferta";
		String descTest = "desc";
		List<String> keys = new ArrayList<String>();
		
		keys.add("key");
		keys.add("Messi");
		

		//tipo
		String nombreTipo = "tipo";
		String descTipo = "descTipo";
		int exposicion = 24;
		int duracion = 1;
		int costo = 1;
		LocalDate fecha = LocalDate.now();
		
//		(String nombre, String descripcion, String exposicion, Integer duracion, Integer CostoPublic, LocalDate fecha) throws TipoPublicExisteException
		try {
			controladorOfertas.altaKeyword("key");
			controladorOfertas.altaKeyword("word");
			controladorPublicaciones.altaTipoPublicacionOL(nombreTipo, descTipo, exposicion, duracion, costo, fecha);

			controladorUsuarios.altaEmpresa(nicknameTest, nombreTest, apellidoTest, emailTest,contraseñaEmpresa1 ,nombreEmpresaTest, descTest, linkWebTest, imagenTest);
			
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		assertThrows(KeywordExisteException.class, () -> controladorOfertas.altaOferta(nombreEmpresaTest, descripcionTest, nombreTest, apellidoTest, keys, emailTest, descripcionTest, nombreTipo, nicknameTest));

		
	}
	
	@Test
	void testAltaOferta_REP() throws NombreExisteException, KeywordExisteException, UsuarioRepetidoException, TipoPublicExisteException {
		//empresa
		String nicknameTest = "nickname";
		String apellidoTest = "apellidoEmpresaUno";
		String emailTest = "emailEmpresaUno";
		String nombreEmpresaTest = "empresa";
		String descripcionTest = "descripcionEmpresaUno";
		String contraseñaEmpresa1 = "contraseñaEmpresa1";
		String linkWebTest = "linkWebEmpresaUno";
		String imagenTest = "imagenUno.png";
		
		String nombreTest = "testOferta";
		String descTest = "desc";
		List<String> keys = new ArrayList<String>();
		
		//tipo
		String nombreTipo = "tipo";
		String descTipo = "descTipo";
		int exposicion = 24;
		int duracion = 1;
		int costo = 1;
		LocalDate fecha = LocalDate.now();
		
//		(String nombre, String descripcion, String exposicion, Integer duracion, Integer CostoPublic, LocalDate fecha) throws TipoPublicExisteException
		try {
			controladorPublicaciones.altaTipoPublicacionOL(nombreTipo, descTipo, exposicion, duracion, costo, fecha);

			controladorUsuarios.altaEmpresa(nicknameTest, nombreTest, apellidoTest, contraseñaEmpresa1,emailTest, nombreEmpresaTest, descTest, linkWebTest, imagenTest);
			controladorOfertas.altaOferta(nombreEmpresaTest, descripcionTest, nombreTest, apellidoTest, keys, emailTest, descripcionTest, nombreTipo, nicknameTest);
			
			
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		assertThrows(NombreExisteException.class, () -> controladorOfertas.altaOferta(nombreEmpresaTest, descripcionTest, nombreTest, apellidoTest, keys, emailTest, descripcionTest, nombreTipo, nicknameTest));
		
	}
	
	@Test
	void testObtenerEmpresas_ok() throws UsuarioRepetidoException, CorreoRepetidoException {
		String nicknameTest = "nickname";
		String nombreTestEmp = "nombre";
		String apellidoTest = "apellidoEmpresaUno";
		String emailTest = "emailEmpresaUno";
		String nombreEmpresaTest = "empresa";
		String descripcionTest = "descripcionEmpresaUno";
		String contraseñaEmpresa1 = "contraseñaEmpresa1";
		String linkWebTest = "linkWebEmpresaUno";
		String imagenTest = "imagenUno.png";
		
		controladorUsuarios.altaEmpresa(nicknameTest, nombreTestEmp, apellidoTest,contraseñaEmpresa1 ,emailTest, nombreEmpresaTest, descripcionTest, linkWebTest, imagenTest);
		
		 List<DTEmpresa> emps = controladorOfertas.obtenerEmpresas();
		 
		 
		assertEquals(emps.size(), 1);
		assertEquals(emps.get(0).getDescripcion(), "descripcionEmpresaUno");

	}
	
	@Test
	void testObtenerDatosOferta() throws KeywordExisteException, TipoPublicExisteException, NombreExisteException, UsuarioRepetidoException, OfertaNoExisteException {
		String nicknameTest = "nickname";
		String apellidoTest = "apellidoEmpresaUno";
		String emailTest = "emailEmpresaUno";
		String nombreEmpresaTest = "empresa";
		String descripcionTest = "descripcionEmpresaUno";
		String contraseñaEmpresa1 = "contraseñaEmpresa1";
		String linkWebTest = "linkWebEmpresaUno";
		String imagenTest = "imagenUno.png";
		
		String remuTest = "10 peso";
		String horaTest = "10-18";
		String nombreTest = "testOferta";
		String descTest = "desc";
		List<String> keys = new ArrayList<String>();
		
		keys.add("key");
		keys.add("word");
		

		//tipo
		String nombreTipo = "tipo";
		String descTipo = "descTipo";
		int exposicion = 24;
		int duracion = 1;
		int costo = 1;
		LocalDate fecha = LocalDate.now();
		
//		(String nombre, String descripcion, String exposicion, Integer duracion, Integer CostoPublic, LocalDate fecha) throws TipoPublicExisteException
		try {
			controladorOfertas.altaKeyword("key");
			controladorOfertas.altaKeyword("word");
			controladorPublicaciones.altaTipoPublicacionOL(nombreTipo, descTipo, exposicion, duracion, costo, fecha);

			controladorUsuarios.altaEmpresa(nicknameTest, nombreTest, apellidoTest, emailTest, contraseñaEmpresa1, nombreEmpresaTest, descTest, linkWebTest, imagenTest);
			controladorOfertas.altaOferta(nombreEmpresaTest, descripcionTest, remuTest, horaTest, keys, emailTest, descripcionTest, nombreTipo, nicknameTest);			
			
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		DTOferta datos = controladorOfertas.obtenerDatosOferta(nombreEmpresaTest);
		
		assertEquals(datos.getDescripcion(), descripcionTest);
		assertEquals(datos.getNombre(), nombreEmpresaTest);
		assertEquals(datos.getRemuneracion(), remuTest);
		assertEquals(datos.getHorario(), horaTest);
	}
	
	// obtenerOfertasLaborales() -> sin ofertas
	@Test
	void testObtenerOfertasLaborales_SinOfertas() {
		assertEquals(controladorOfertas.obtenerOfertasLaborales().size(), 0);
	}
	
	// obtenerOfertasVigentesDeEmpresa() -> empresa sin ofertas
	@Test
	void testObtenerOfertasVigentesDeEmpresa_EmpresaSinOfertas() {
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
			controladorUsuarios.altaEmpresa(nicknameTest, nombreTest, apellidoTest, emailTest, contraseñaPostulante1, nombreEmpresaTest, descripcionTest, linkWebTest, imagenTest);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		// checkeo que la empresa no tenga ofertas
		try {
			assertEquals(controladorOfertas.obtenerOfertasVigentesDeEmpresa(nicknameTest).size(), 0);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	// obtenerPostulacionesPorPostulante() -> postulante sin postulaciones
	@Test
	void testObtenerPostulacionesPorPostulante_PostulanteSinPostulaciones() {
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
			controladorUsuarios.altaPostulante(nicknameTest, nombreTest, apellidoTest, emailTest, contraseñaPostulante1, fechaNacimientoTest, nacionalidadTest, imagenTest);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		// checkeo que no tenga postulaciones
		try {
			assertEquals(controladorOfertas.obtenerPostulacionesPorPostulante(nicknameTest).size(), 0);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	// estaPostuladoAOfertaLaboral() -> no esta postulado
	@Test
	void testEstaPostuladoAOfertaLAboral_NoEstaPostulado() {
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
		
		// le cargo un tipo de publicacion al sistema
		String nombreTipoPublicacion1 = "nombreTipoPublicacionUno";
		String descripcionTipoPublicacion1 = "descripcionTipoPublicacionUno";
		int exposicionTipoPublicacion1 = 68;
		Integer duracionTipoPublicacion1 = 24;
		Integer costoTipoPublicacion1 = 9001;
		LocalDate fechaTipoPublicacion1 = LocalDate.now();
		try {
			controladorPublicaciones.altaTipoPublicacionOL(nombreTipoPublicacion1, descripcionTipoPublicacion1, exposicionTipoPublicacion1, duracionTipoPublicacion1, costoTipoPublicacion1, fechaTipoPublicacion1);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		assertEquals(controladorPublicaciones.obtenerTipos().size(), 1);
		
		// le cargo una oferta al sistema
		String nombreOferta1 = "nombreOfertaUno";
		String descripcionOferta1 = "descripcionOfertaUno";
		String remuneracionOferta1 = "remuneracionOfertaUno";
		String horarioOferta1 = "horarioOfertaUno";
		List<String> keywordsOferta1 = new ArrayList<>();
		String ciudadOferta1 = "ciudadOfertaUno";
		String departamentoOferta1 = "departamentoOfertaUno";
		String tipoPostulacionOferta1 = nombreTipoPublicacion1;
		String nicknameEmpresaOferta1 = nicknameEmpresa1;
		try {
			controladorOfertas.altaOferta(nombreOferta1, descripcionOferta1, remuneracionOferta1, horarioOferta1, keywordsOferta1, ciudadOferta1, departamentoOferta1, tipoPostulacionOferta1, nicknameEmpresaOferta1);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		assertEquals(manejadorOL.getCantidadOfertas(), 1);
		
		// chequeo que no este postulado
		try {
			assertNull(controladorOfertas.estaPostuladoAOfertaLaboral(nicknamePostulante1, nombreOferta1));
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testConfirmarOfertaLaboral_OK() throws OfertaNoExisteException {
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
		
		// le cargo un tipo de publicacion al sistema
		String nombreTipoPublicacion1 = "nombreTipoPublicacionUno";
		String descripcionTipoPublicacion1 = "descripcionTipoPublicacionUno";
		int exposicionTipoPublicacion1 = 68;
		Integer duracionTipoPublicacion1 = 24;
		Integer costoTipoPublicacion1 = 9001;
		LocalDate fechaTipoPublicacion1 = LocalDate.now();
		try {
			controladorPublicaciones.altaTipoPublicacionOL(nombreTipoPublicacion1, descripcionTipoPublicacion1, exposicionTipoPublicacion1, duracionTipoPublicacion1, costoTipoPublicacion1, fechaTipoPublicacion1);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		assertEquals(controladorPublicaciones.obtenerTipos().size(), 1);
		
		// le cargo una oferta al sistema
		String nombreOferta1 = "nombreOfertaUno";
		String descripcionOferta1 = "descripcionOfertaUno";
		String remuneracionOferta1 = "remuneracionOfertaUno";
		String horarioOferta1 = "horarioOfertaUno";
		List<String> keywordsOferta1 = new ArrayList<>();
		String ciudadOferta1 = "ciudadOfertaUno";
		String departamentoOferta1 = "departamentoOfertaUno";
		String tipoPostulacionOferta1 = nombreTipoPublicacion1;
		String nicknameEmpresaOferta1 = nicknameEmpresa1;
		try {
			controladorOfertas.altaOferta(nombreOferta1, descripcionOferta1, remuneracionOferta1, horarioOferta1, keywordsOferta1, ciudadOferta1, departamentoOferta1, tipoPostulacionOferta1, nicknameEmpresaOferta1);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		assertEquals(manejadorOL.getCantidadOfertas(), 1);
		
		try {
			controladorOfertas.confirmarOfertaLaboral(nombreOferta1);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		assertEquals(controladorOfertas.obtenerDatosOferta(nombreOferta1).getEstado(), EnumEstadoOferta.CONFIRMADA);
	}
	
	@Test
	void testConfirmarOfertaLaboral_noExisteOferta() {
		String nombreOferta = "no existo";
		assertThrows(OfertaNoExisteException.class, () -> controladorOfertas.confirmarOfertaLaboral(nombreOferta));
	}
	
	@Test
	public void testObtenerOfertasIngresadasDeEmpresa() throws NicknameNoExisteException, UsuarioNoEsEmpresaException {
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
		
		// le cargo un tipo de publicacion al sistema
		String nombreTipoPublicacion1 = "nombreTipoPublicacionUno";
		String descripcionTipoPublicacion1 = "descripcionTipoPublicacionUno";
		int exposicionTipoPublicacion1 = 68;
		Integer duracionTipoPublicacion1 = 24;
		Integer costoTipoPublicacion1 = 9001;
		LocalDate fechaTipoPublicacion1 = LocalDate.now();
		try {
			controladorPublicaciones.altaTipoPublicacionOL(nombreTipoPublicacion1, descripcionTipoPublicacion1, exposicionTipoPublicacion1, duracionTipoPublicacion1, costoTipoPublicacion1, fechaTipoPublicacion1);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		assertEquals(controladorPublicaciones.obtenerTipos().size(), 1);
		
		// le cargo una oferta al sistema
		String nombreOferta1 = "nombreOfertaUno";
		String descripcionOferta1 = "descripcionOfertaUno";
		String remuneracionOferta1 = "remuneracionOfertaUno";
		String horarioOferta1 = "horarioOfertaUno";
		List<String> keywordsOferta1 = new ArrayList<>();
		String ciudadOferta1 = "ciudadOfertaUno";
		String departamentoOferta1 = "departamentoOfertaUno";
		String tipoPostulacionOferta1 = nombreTipoPublicacion1;
		String nicknameEmpresaOferta1 = nicknameEmpresa1;
		try {
			controladorOfertas.altaOferta(nombreOferta1, descripcionOferta1, remuneracionOferta1, horarioOferta1, keywordsOferta1, ciudadOferta1, departamentoOferta1, tipoPostulacionOferta1, nicknameEmpresaOferta1);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		assertEquals(manejadorOL.getCantidadOfertas(), 1);
		
		TreeSet<DTOferta> ofertasIngresadasEmpresa = controladorOfertas.obtenerOfertasIngresadasDeEmpresa(nicknameEmpresa1);
		assertEquals(ofertasIngresadasEmpresa.size(), 1);
		assertEquals(ofertasIngresadasEmpresa.first().getNombre(), nombreOferta1);
	}
	
	@Test
	public void testCambiarEstadoOferta() throws OfertaNoExisteException, NicknameNoExisteException {
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
		
		// le cargo un tipo de publicacion al sistema
		String nombreTipoPublicacion1 = "nombreTipoPublicacionUno";
		String descripcionTipoPublicacion1 = "descripcionTipoPublicacionUno";
		int exposicionTipoPublicacion1 = 68;
		Integer duracionTipoPublicacion1 = 24;
		Integer costoTipoPublicacion1 = 9001;
		LocalDate fechaTipoPublicacion1 = LocalDate.now();
		try {
			controladorPublicaciones.altaTipoPublicacionOL(nombreTipoPublicacion1, descripcionTipoPublicacion1, exposicionTipoPublicacion1, duracionTipoPublicacion1, costoTipoPublicacion1, fechaTipoPublicacion1);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		assertEquals(controladorPublicaciones.obtenerTipos().size(), 1);
		
		// le cargo una oferta al sistema
		String nombreOferta1 = "nombreOfertaUno";
		String descripcionOferta1 = "descripcionOfertaUno";
		String remuneracionOferta1 = "remuneracionOfertaUno";
		String horarioOferta1 = "horarioOfertaUno";
		List<String> keywordsOferta1 = new ArrayList<>();
		String ciudadOferta1 = "ciudadOfertaUno";
		String departamentoOferta1 = "departamentoOfertaUno";
		String tipoPostulacionOferta1 = nombreTipoPublicacion1;
		String nicknameEmpresaOferta1 = nicknameEmpresa1;
		try {
			controladorOfertas.altaOferta(nombreOferta1, descripcionOferta1, remuneracionOferta1, horarioOferta1, keywordsOferta1, ciudadOferta1, departamentoOferta1, tipoPostulacionOferta1, nicknameEmpresaOferta1);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		assertEquals(manejadorOL.getCantidadOfertas(), 1);
		
		// le cambio el estado a la oferta
		OfertaLaboral oferta = manejadorOL.getOfertaLaboral(nombreOferta1);
		assertEquals(oferta.getEstado(), EnumEstadoOferta.INGRESADA);
		
		controladorOfertas.cambiarEstadoOferta(EnumEstadoOferta.CONFIRMADA, nombreOferta1);
		assertEquals(oferta.getEstado(), EnumEstadoOferta.CONFIRMADA);
	}
	
}
