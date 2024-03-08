package test.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import excepciones.*;
import logica.*;
import servidor.types.*;

public class TestCargaDeDatos {
	
	Fabrica fabrica = Fabrica.getInstance();
	IControladorUsuario ICU = fabrica.getIControladorUsuario();
	IControladorPublicaciones ICP = fabrica.getIControladorPublicaciones();
	IControladorOfertas ICO = fabrica.getIControladorOfertas();

	// setup para tests
	@SuppressWarnings("unused")
	private static IControladorOfertas controladorOfertas;
	@SuppressWarnings("unused")
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
	public void reiniciarColecciones() {
		manejadorU.limpiarColeccionUsuarios();
		manejadorOL.limpiarColeccionOfertasLaborales();
		manejadorOL.limpiarColeccionKeywords();
		manejadorP.limpiarColeccionPublicaciones();
		manejadorP.limpiarColeccionPaquetes();
		manejadorP.limpiarColeccionTipos();
		this.cargarDatos();
	}
	
	// ICOntroladorPublicaciones.obtenerPublicacionesPorKeywords() -> OK
	@Test
	void testControladorPublicaciones_obtenerPublicacionesPorKeywords_Ok() {
		// esta hardcodeada de acuerdo con los datos de prueba, si cambian se rompe esta funcion
		List<String> listaKeywords = new ArrayList<String>();
		listaKeywords.add("Temporal");
		List<DTPublicacion> publicacionesPorKeyword = controladorPublicaciones.obtenerPublicacionesPorKeywords(listaKeywords);
		assertTrue(() -> publicacionesPorKeyword.size() > 0);
	}
	
	// testea que toda la carga de datos funcione bien
	@Test
	void testCargaDeDatos() {
		// obtengo el path a los archivos .csv
		String currentDirectory = System.getProperty("user.dir");
		String carpetaDatosPath = currentDirectory + File.separator + "DatosCSV" + File.separator;
		
		// usar este path para testear en tarea2 con la parte web
		//String carpetaDatosPath = currentDirectory + File.separator + "src" + File.separator + "main" + File.separator + "webapp" + File.separator + "WEB-INF" + File.separator + "DatosCSV" + File.separator;
		
		String csvFilePathPostulantes = carpetaDatosPath + "Postulantes.csv";
		String csvFilePathKeywords = carpetaDatosPath + "Keywords.csv";
		String csvFilePathEmpresas = carpetaDatosPath + "Empresas.csv";
		String csvFilePathTiposPublicacion = carpetaDatosPath + "TiposPublicacion.csv";
		String csvFilePathPaquetes = carpetaDatosPath + "Paquetes.csv";
		String csvFilePathTiposPublicacionPaquetes = carpetaDatosPath + "TiposPublicacionPaquetes.csv";
		String csvFilePathOfertasLaboralesPrueba = carpetaDatosPath + "OfertasLaboralesPrueba2.csv";	    	 
		String csvFilePathPostulaciones = carpetaDatosPath + "Postulaciones.csv";
   	 	String csvFilePathPostulantesOfertasLaboralesFavoritas = carpetaDatosPath + "PostulantesOfertasLaboralesFavoritas.csv";
		
		// testeo
		try {
			testCargaDatosPostulantes(csvFilePathPostulantes);
			testCargaDatosKeywords(csvFilePathKeywords);
			testCargaDatosEmpresas(csvFilePathEmpresas); 
			testCargaDatosTipoPublicacion(csvFilePathTiposPublicacion);
			testCargaDatosPaquetes(csvFilePathPaquetes);
			testCargaDatosTiposPublicacionPaquetes(csvFilePathTiposPublicacionPaquetes);
			testCargaDatosOfertasLaboralesPrueba(csvFilePathOfertasLaboralesPrueba);
			testCargaPostulaciones(csvFilePathPostulaciones);
			//testCargaPostulantesOfertasLaboralesFavoritas(csvFilePathPostulantesOfertasLaboralesFavoritas);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	void testCargaDatosPostulantes(String csvFile) throws Exception {
		String line;
        String csvSplitBy = ";";
        int iter = 1;
        try (BufferedReader bufferReader = new BufferedReader(new FileReader(csvFile))) {
            // Leer la primera línea (encabezados) y descartarla si es necesario
            //br.readLine();
            while ((line = bufferReader.readLine()) != null) {
                String[] datos = line.split(csvSplitBy);
                String nickname = datos[0];
	            String nombre = datos[1];
	            String apellido = datos[2];
	            String correo = datos[3];
	            LocalDate fecha = parseFechaNacimiento(datos[4].trim());
	            String nacionalidad = datos[5];
	            String password = datos[6];
	            String url_imagen = "media/img/imgPostulantes/U"+iter+".jpg";
	            iter++;
	            
	            // checkeo si existe el postulante en el sistema
	            DTUsuario usuarioDT = manejadorU.obtenerUsuario(nickname);
	            
	            // checkeo que sea un postulante
	            if (!(usuarioDT instanceof DTPostulante))
		        	throw new Exception("El usuario con el nick " + nickname + " no es un postulante.");
		        DTPostulante postulanteDT = (DTPostulante) usuarioDT;
		        
		        // checkeo que los datos esten correctos
		        assertEquals(postulanteDT.getNickname(), nickname);
		        assertEquals(postulanteDT.getNombre(), nombre);
		        assertEquals(postulanteDT.getApellido(), apellido);
		        assertEquals(postulanteDT.getCorreo(), correo);
		        assertEquals(postulanteDT.getFechaNacimiento(), fecha);
		        assertEquals(postulanteDT.getNacionalidad(), nacionalidad);
		        //assertEquals(postulanteDT.getContraseña(), password);
		        assertEquals(postulanteDT.getUrlImagen(), url_imagen);
            }
        }
	}
	
	void testCargaDatosKeywords(String csvFile) throws Exception {
		String line = "";
	    String cvsSplitBy = ";";
	    try (BufferedReader bufferReader = new BufferedReader(new FileReader(csvFile))) {
	    	//br.readLine();
	        while ((line = bufferReader.readLine()) != null) {
	            String[] data = line.split(cvsSplitBy);
	            String nombre = data[0];
	            
	            // checkeo que exista la keyword en el sistema
	            List<String> keywords = manejadorOL.obtenerKeywords();
            	if (!keywords.contains(nombre))
            		throw new Exception("No existe ninguna keyword llamada: " + nombre);
	        }
	    }
	}
	
	void testCargaDatosEmpresas(String csvFile) throws Exception {
		String line = "";
	    String cvsSplitBy = ";";
	    int iter = 11;
	    try (BufferedReader bufferReader = new BufferedReader(new FileReader(csvFile))) {
	        while ((line = bufferReader.readLine()) != null) {
	            String[] empresaData = line.split(cvsSplitBy);
	            String nickname = empresaData[0];
	            String nombre = empresaData[1];
	            String apellido = empresaData[2];
	            String correo = empresaData[3];
	            String descripcion = empresaData[4];
	            String link = empresaData[5]; 
	            String password = empresaData[6];
	            String url_imagen = "media/img/imgEmpresas/U"+iter+".jpg";  
	            iter++;
	            
	            // checkeo si existe el postulante en el sistema
				DTUsuario usuarioDT = manejadorU.obtenerUsuario(nickname);
				
				// checkeo si es un postulante
				if (!(usuarioDT instanceof DTEmpresa))
					throw new Exception("El usuario con el nick " + nickname + " no es una empresa.");
				DTEmpresa empresaDT = (DTEmpresa) usuarioDT;
				
				// checkeo si los datos estan bien
				assertEquals(empresaDT.getNickname(), nickname);
				assertEquals(empresaDT.getNombre(), nombre);
				assertEquals(empresaDT.getApellido(), apellido);
				assertEquals(empresaDT.getCorreo(), correo);
				assertEquals(empresaDT.getDescripcion(), descripcion);
				assertEquals(empresaDT.getLinkWeb(), link);
				//assertEquals(empresaDT.getContraseña(), password);
				assertEquals(empresaDT.getUrlImagen(), url_imagen);
	        }
	    }
	}
	
	void testCargaDatosTipoPublicacion(String csvFile) throws Exception {
		String line = "";
	    String cvsSplitBy = ";";
	    try (BufferedReader bufferReader = new BufferedReader(new FileReader(csvFile))) {
	        while ((line = bufferReader.readLine()) != null) {
	            String[] tiposPublicaionData = line.split(cvsSplitBy);
	            if (tiposPublicaionData.length == 0)
	            	continue;
	            
	            String nombre = tiposPublicaionData[0];
	            String desc = tiposPublicaionData[1];
	            int exp = Integer.parseInt(tiposPublicaionData[2]);
	            Integer duracion = Integer.parseInt(tiposPublicaionData[3]);
	            Integer costo = Integer.parseInt(tiposPublicaionData[4]);
	            LocalDate alta = parseFecha(tiposPublicaionData[5].trim());
	            
	            // checkeo si existe el tipoPublicacion en el sistema
            	TipoPublicacion tipoPublicacion = manejadorP.getTipo(nombre);
            	if (tipoPublicacion == null)
            		throw new Exception("No existe ningun TipoPublicacion llamado " + nombre);
            	
            	// checkeo si los datos estan bien
            	assertEquals(tipoPublicacion.getNombre(), nombre);
            	assertEquals(tipoPublicacion.getDescripcion(), desc);
            	assertEquals(tipoPublicacion.getExposicion(), exp);
            	assertEquals(tipoPublicacion.getDuracion(), duracion);
            	assertEquals(tipoPublicacion.getCosto(), costo);
            	assertEquals(tipoPublicacion.getAlta(), alta);
	        }
	    }
	}
	
	void testCargaDatosPaquetes(String csvFile) throws Exception {
		String line = "";
	    String cvsSplitBy = ";";
	    int iter = 1;
	    try (BufferedReader bufferReader = new BufferedReader(new FileReader(csvFile))) {
	        while ((line = bufferReader.readLine()) != null) {
	            String[] paquetesData = line.split(cvsSplitBy);
	            
	            String nombreTipo = paquetesData[0];
	            String descripcionTipo = paquetesData[1];
	            int validez = Integer.parseInt(paquetesData[2]);
	            int descuento = Integer.parseInt(paquetesData[3]);
	            String fechaAlta = paquetesData[4];
	            String url_imagen = "media/img/imgPaquetes/Paq"+iter+".jpg";
		        iter++;
		        
	            LocalDate fechaParseada = LocalDate.parse(fechaAlta, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	            Paquete paquete = manejadorP.obtenerPaquete(nombreTipo);
	            
	            assertNotNull(paquete);
	            assertEquals(paquete.getDescripcion(), descripcionTipo);
	            assertEquals(paquete.getValidez(), validez);
	            assertEquals(paquete.getDescuento(), descuento);
	            assertEquals(paquete.getFechaAlta(), fechaParseada);
	            assertEquals(paquete.getUrlImagen(), url_imagen);
	        }
	    }
	}
	
	void testCargaDatosTiposPublicacionPaquetes(String csvFile) throws Exception {
		String line = "";
	    String cvsSplitBy = ";";
	    try (BufferedReader bufferReader = new BufferedReader(new FileReader(csvFile))) {
	        while ((line = bufferReader.readLine()) != null) {
	            String[] tiposPublicaionPaquetesData = line.split(cvsSplitBy);
	            String nombrePaquete = tiposPublicaionPaquetesData[0];
	            Integer cantidad = Integer.parseInt(tiposPublicaionPaquetesData[2]);
	            String nombreTipoPublicacion = tiposPublicaionPaquetesData[1];
	            
	            // ICP.agregarTipoPublicacion(nombrePaquete,cantidad,nombreTipoPublicacion);
	            
	            // chequeo que este todo bien
	            Paquete paquete = manejadorP.obtenerPaquete(nombrePaquete);
	    		List<Tupla_Cantidad_TipoPublicacion> tuplasPaquete = paquete.getListaDeTuplas();
	    		Tupla_Cantidad_TipoPublicacion tupla = tuplasPaquete
	    				.stream()
	    				.filter(val -> val.getTipoPublicacion()
	    						.getNombre()
	    						.equals(nombreTipoPublicacion))
	    				.findFirst()
	    				.orElse(null);
	    		assertNotNull(tupla);
	    		assertEquals(tupla.getCantidad(), cantidad);
	    		assertTrue(() -> paquete.getCostoAsociado() >= 0);
	        }
	    }
	}
	
	void testCargaDatosOfertasLaboralesPrueba(String csvFile) throws Exception {
		String line = "";
	    String cvsSplitBy = ";";
	    int iter = 1;
	    try (BufferedReader bufferReader = new BufferedReader(new FileReader(csvFile))) {
	    	bufferReader.readLine();
	        while ((line = bufferReader.readLine()) != null) {
	        	String[] ofertasLaboralesData = line.split(cvsSplitBy);
	        	if (ofertasLaboralesData.length == 0)
	        		continue;
	            
	            String nombre = ofertasLaboralesData[0]; 
	            String desc = ofertasLaboralesData[1];
	            String rem = ofertasLaboralesData[5];
	            String horario = ofertasLaboralesData[4];
	            List<String> keysList = Arrays.asList(ofertasLaboralesData[12].split("/"));
	            String ciudad = ofertasLaboralesData[3];
	            String depa = ofertasLaboralesData[2];
	            String tipo = ofertasLaboralesData[7];
	            String empresa = ofertasLaboralesData[6];
	            String url_imagen = "media/img/imgOfertas/O"+iter+".jpg";
	            iter++;
	            String estado = ofertasLaboralesData[9];
	            String formaPago = "";
	            String compraPaquete = ofertasLaboralesData[10];
	            String paqueteSeleccionado = "";
	            
	            if ("Sin paquete".equals(compraPaquete)) {
	            	formaPago = "General";
        		} else {
        		    formaPago = "Paquete adquirido previamente";
        		    paqueteSeleccionado = compraPaquete;
        		}
                     
	            //cambio de string al enumerado el estado
		        EnumEstadoOferta estadoEnum = null;
		        if ("Ingresada".equals(estado)) {
		        	estadoEnum = EnumEstadoOferta.INGRESADA;
		        } else if ("Confirmada".equals(estado)) {
		        	estadoEnum = EnumEstadoOferta.CONFIRMADA;
		        } else if ("Rechazada".equals(estado)) {
		        	estadoEnum = EnumEstadoOferta.RECHAZADA;
		        }
	            
		        // checkeo si existe la oferta en el sistema
		        DTOferta oferta = manejadorOL.obtenerOfertaLaboral(nombre);
		        
		        // checkeo si los datos estan bien
		        assertEquals(oferta.getNombre(), nombre);
            	assertEquals(oferta.getDescripcion(), desc);
            	assertEquals(oferta.getRemuneracion(), rem);
            	assertEquals(oferta.getHorario(), horario);
            	assertEquals(oferta.getCiudad(), ciudad);
            	assertEquals(oferta.getDepartamento(), depa);
            	assertEquals(oferta.getUrlImagen(), url_imagen);
            	assertEquals(oferta.getEstado(), estadoEnum);
            	assertEquals(oferta.getFormaPago(), formaPago);
            	//assertEquals(oferta.getPaqueteSeleccionado(), paqueteSeleccionado);
            	assertEquals(oferta.getNicknameEmpresa(), empresa);
            	keysList.stream().forEach(keyword -> assertTrue(oferta.getKeywords().contains(keyword)));
	        }
	    }
	}
	
	void testCargaPostulaciones(String csvFile) throws Exception {
		String line = "";
		   String cvsSplitBy = ";";
		   try (BufferedReader bufferReader = new BufferedReader(new FileReader(csvFile))) {
			   while ((line = bufferReader.readLine()) != null) {
				   String[] postulacionesData = line.split(cvsSplitBy);
				   if (postulacionesData.length == 0)
					   continue;
				   
		           String nombre = postulacionesData[4];
		           String motivacion = postulacionesData[2];
		           String nickname = postulacionesData[0];
		           String cv = postulacionesData[1];
		           LocalDateTime fecha = parseFecha(postulacionesData[3].trim()).atStartOfDay();
		           
		        // checkeo si existe la postulacion del lado del postulante
	            DTPostulante postulanteDT = (DTPostulante) manejadorU.obtenerUsuario(nickname);
	            DTPostulacion postulacionPostulante = postulanteDT.getPostulaciones()
	            		.stream()
	            		.filter(postulacion -> postulacion.getNombreOfertaLaboral().equals(nombre))
	            		.findFirst()
	            		.get();
	            
	            // checko si existe la postulacion del lado de la oferta
            	DTOferta ofertaDT = manejadorOL.obtenerOfertaLaboral(nombre);
            	DTPostulacion postulacionOferta = ofertaDT.getPostulaciones()
            			.stream()
            			.filter(postulacion -> postulacion.getNicknamePostulante().equals(nickname))
            			.findFirst()
            			.get();
            	
            	// checko si los datos estan bien del lado del postulante
            	assertEquals(postulacionPostulante.getNicknamePostulante(), nickname);
            	assertEquals(postulacionPostulante.getNombreOfertaLaboral(), nombre);
            	assertEquals(postulacionPostulante.getCvReducido(), cv);
            	assertEquals(postulacionPostulante.getMotivacion(), motivacion);
            	assertEquals(postulacionPostulante.getFecha(), fecha);
            	
            	// checko si los datos estan bien del lado de la oferta
            	assertEquals(postulacionOferta.getNicknamePostulante(), nickname);
            	assertEquals(postulacionOferta.getNombreOfertaLaboral(), nombre);
            	assertEquals(postulacionOferta.getCvReducido(), cv);
            	assertEquals(postulacionOferta.getMotivacion(), motivacion);
            	assertEquals(postulacionOferta.getFecha(), fecha);
			   }
		   }
	}
	
	void testCargaPostulantesOfertasLaboralesFavoritas(String csvFile) throws Exception {
		String line = "";
		String cvsSplitBy = ";";
		try (BufferedReader bufferReader = new BufferedReader(new FileReader(csvFile))) {
			while ((line = bufferReader.readLine()) != null) {
				String[] postulacionesData = line.split(cvsSplitBy);
				String nickname = postulacionesData[0];
		        String nombreOferta = postulacionesData[1];
		        
		        // checkeo que este todo bien
		        DTOferta oferta = ICO.obtenerDatosOferta(nombreOferta);
		        assertTrue(oferta.getFaveados()
		        		.stream()
		        		.anyMatch(DTpostul -> DTpostul.getNickname()
		        				.equals(nickname)));
			}
		}
	}
	
	// carga de datos
	public void cargarDatos() {
		// obtengo el path a los archivos .csv
		String currentDirectory = System.getProperty("user.dir");
		String carpetaDatosPath = currentDirectory + File.separator + "DatosCSV" + File.separator;
		
		// usar este path para testear en tarea2 con la parte web
		//String carpetaDatosPath = currentDirectory + File.separator + "src" + File.separator + "main" + File.separator + "webapp" + File.separator + "WEB-INF" + File.separator + "DatosCSV" + File.separator;
		
	    String csvFilePathPostulantes = carpetaDatosPath + "Postulantes.csv";
   	 	String csvFilePathKeywords = carpetaDatosPath + "Keywords.csv";
   	 	String csvFilePathEmpresas = carpetaDatosPath + "Empresas.csv";
   	 	String csvFilePathTiposPublicacion = carpetaDatosPath + "TiposPublicacion.csv";
   	 	String csvFilePathPaquetes = carpetaDatosPath + "Paquetes.csv";
   	 	String csvFilePathTiposPublicacionPaquetes = carpetaDatosPath + "TiposPublicacionPaquetes.csv";
   	 	String csvFilePathOfertasLaboralesPrueba = carpetaDatosPath + "OfertasLaboralesPrueba2.csv";	    	 
   	 	String csvFilePathPostulaciones = carpetaDatosPath + "Postulaciones.csv";
   	 	String csvFilePathPostulantesOfertasLaboralesFavoritas = carpetaDatosPath + "PostulantesOfertasLaboralesFavoritas.csv";
   	 	
   	 	// checkeo que todos los archivos existen
   	 	if (!Files.exists(Paths.get(csvFilePathPostulantes)))
   	 		throw new RuntimeException("Falta el archivo: " + csvFilePathPostulantes);
   	 	if (!Files.exists(Paths.get(csvFilePathKeywords)))
   	 		throw new RuntimeException("Falta el archivo: " + csvFilePathKeywords);
   	 	if (!Files.exists(Paths.get(csvFilePathEmpresas)))
   	 		throw new RuntimeException("Falta el archivo: " + csvFilePathEmpresas);
   	 	if (!Files.exists(Paths.get(csvFilePathTiposPublicacion)))
   	 		throw new RuntimeException("Falta el archivo: " + csvFilePathTiposPublicacion);
   	 	if (!Files.exists(Paths.get(csvFilePathPaquetes)))
   	 		throw new RuntimeException("Falta el archivo: " + csvFilePathPaquetes);
   	 	if (!Files.exists(Paths.get(csvFilePathTiposPublicacionPaquetes)))
   	 		throw new RuntimeException("Falta el archivo: " + csvFilePathTiposPublicacionPaquetes);
   	 	if (!Files.exists(Paths.get(csvFilePathOfertasLaboralesPrueba)))
   	 		throw new RuntimeException("Falta el archivo: " + csvFilePathOfertasLaboralesPrueba);
   	 	if (!Files.exists(Paths.get(csvFilePathPostulaciones)))
   	 		throw new RuntimeException("Falta el archivo: " + csvFilePathPostulaciones);
   	 	if (!Files.exists(Paths.get(csvFilePathPostulantesOfertasLaboralesFavoritas)))
   	 		throw new RuntimeException("Falta el archivo: " + csvFilePathPostulantesOfertasLaboralesFavoritas);
   	 	
		// hago la carga de datos
   	 	try {
    		cargarDatosPostulantes(csvFilePathPostulantes);
    		cargarDatosKeywords(csvFilePathKeywords);
    		cargarDatosEmpresas(csvFilePathEmpresas); 
    		cargarDatosTipoPublicacion(csvFilePathTiposPublicacion);
    		cargarDatosPaquetes(csvFilePathPaquetes);
    		cargarDatosTiposPublicacionPaquetes(csvFilePathTiposPublicacionPaquetes);
    		cargarDatosOfertasLaboralesPrueba(csvFilePathOfertasLaboralesPrueba);
    		cargarPostulaciones(csvFilePathPostulaciones);
    		cargarPostulantesOfertasLaboralesFavoritas(csvFilePathPostulantesOfertasLaboralesFavoritas);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private LocalDate parseFechaNacimiento(String fechaNacimiento) {
	    try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");

	        LocalDate fechaNacimientoParsed = LocalDate.parse(fechaNacimiento, formatter);
	        LocalDate fechaActual = LocalDate.now();
	        if (fechaNacimientoParsed.isBefore(fechaActual)) {
	            return fechaNacimientoParsed;
	        } else {
	            return null;
	        }
	    } catch (DateTimeParseException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
    private LocalDate parseFecha(String fecha) {
	    try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        LocalDate fechaParsed = LocalDate.parse(fecha, formatter);
	            return fechaParsed;
	    } catch (DateTimeParseException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
    
    
    private void cargarDatosKeywords(String csvFile) throws KeywordExisteException{	
	    String line = "";
	    String cvsSplitBy = ";";
	    try (BufferedReader bufferReader = new BufferedReader(new FileReader(csvFile))) {
	    	//br.readLine();
	        while ((line = bufferReader.readLine()) != null) {
	            String[] data = line.split(cvsSplitBy);
	            String nombre = "";         
	            if(data.length > 0) {
	            	 nombre = data[0];
	            	 Fabrica factory = Fabrica.getInstance();
	            	 IControladorOfertas ICO = factory.getIControladorOfertas();
	            	 ICO.altaKeyword(nombre);
	            }      
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
    }
	
    private void cargarDatosPostulantes(String csvFile) throws UsuarioRepetidoException, CorreoRepetidoException {
        String line;
        String csvSplitBy = ";";
        int iter = 1;
        try (BufferedReader bufferReader = new BufferedReader(new FileReader(csvFile))) {
            // Leer la primera línea (encabezados) y descartarla si es necesario
            //br.readLine();
            while ((line = bufferReader.readLine()) != null) {
                String[] datos = line.split(csvSplitBy);
                String nickname = "";
	            String nombre = "";
	            String apellido = "";
	            String correo = "";
	            String fecha = null;
	            String nacionalidad = "";
	            String password = "";
	            String url_imagen = "";
	            if(datos.length > 0) {
	            	 nickname = datos[0];
	            	 nombre = datos[1];
	            	 apellido = datos[2];
	            	 correo = datos[3];
	            	 fecha = datos[4].trim();
	            	 nacionalidad = datos[5];
	            	 password = datos[6];
	            	 url_imagen = "media/img/imgPostulantes/U"+iter+".jpg";
	            	 iter++;
	            	 Fabrica factory = Fabrica.getInstance();
	            	 IControladorUsuario ICU = factory.getIControladorUsuario();
	            	 ICU.altaPostulante(nickname, nombre, apellido, correo, password,fecha, nacionalidad,url_imagen);
	            }
           
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void cargarDatosEmpresas(String csvFile) throws UsuarioRepetidoException, CorreoRepetidoException {	
	    String line = "";
	    String cvsSplitBy = ";";
	    int iter = 11;
	    try (BufferedReader bufferReader = new BufferedReader(new FileReader(csvFile))) {
	        while ((line = bufferReader.readLine()) != null) {
	            String[] empresaData = line.split(cvsSplitBy);
	            String nickname = "";
	            String nombre = "";
	            String apellido = "";
	            String correo = "";
	            String descripcion = "";
	            String link = "";
	            String password = "";
	            String url_imagen = "";            
	            if(empresaData.length > 0) {
	            	 nickname = empresaData[0];
	            	 nombre = empresaData[1];
	            	 apellido = empresaData[2];
	            	 correo = empresaData[3];
	            	 descripcion = empresaData[4];
			         link = empresaData[5]; 
			         password = empresaData[6];
			         url_imagen = "media/img/imgEmpresas/U"+iter+".jpg";
	            	 iter++;
			         Fabrica factory = Fabrica.getInstance();
	            	 IControladorUsuario ICU = factory.getIControladorUsuario();
	            	 ICU.altaEmpresa(nickname, nombre, apellido, correo, password,nickname, descripcion, link,url_imagen);
	            }
	            
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
    
    private void cargarDatosTipoPublicacion(String csvFile) throws TipoPublicExisteException{	
	    String line = "";
	    String cvsSplitBy = ";";
	    try (BufferedReader bufferReader = new BufferedReader(new FileReader(csvFile))) {
	        while ((line = bufferReader.readLine()) != null) {
	            String[] tiposPublicaionData = line.split(cvsSplitBy);
	            String nombre = "";
	            String desc = "";
	            int exp = 0;
	            Integer duracion = 0;
	            Integer costo = 0;
	            LocalDate alta = null;	            
	            if(tiposPublicaionData.length > 0) {
	            	 nombre = tiposPublicaionData[0];
	            	 desc = tiposPublicaionData[1];
	            	 exp = Integer.parseInt(tiposPublicaionData[2]) ;
	            	 duracion = Integer.parseInt(tiposPublicaionData[3]) ;
	            	 costo = Integer.parseInt(tiposPublicaionData[4]);
	            	 alta = parseFecha(tiposPublicaionData[5].trim());
	            	 Fabrica factory = Fabrica.getInstance();
	            	 IControladorPublicaciones ICP = factory.getIControladorPublicaciones();
	            	 ICP.altaTipoPublicacionOL(nombre, desc, exp, duracion, costo, alta);
	            }
	            
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
    
    private void cargarDatosPaquetes(String csvFile) throws KeywordExisteException, PaqueteExisteException{	  
	    String line = "";
	    String cvsSplitBy = ";";
	    int iter = 1;
	    try (BufferedReader bufferReader = new BufferedReader(new FileReader(csvFile))) {
	        while ((line = bufferReader.readLine()) != null) {
	            String[] paquetesData = line.split(cvsSplitBy);
	            String nombreTipo = "";
	            String descripcionTipo = "";
	            int validez = 0;
	            int descuento = 0;
	            String fechaAlta = "00/00/0000";
	            String url_imagen = "";
	            if(paquetesData.length > 0) {
	            	nombreTipo = paquetesData[0];
	            	descripcionTipo = paquetesData[1];
	            	validez = Integer.parseInt(paquetesData[2]);
	            	descuento = Integer.parseInt(paquetesData[3]);
	            	fechaAlta = paquetesData[4];
	            	url_imagen = "media/img/imgPaquetes/Paq"+iter+".jpg";
			        iter++;
	             	Fabrica factory = Fabrica.getInstance();
	            	IControladorPublicaciones ICP = factory.getIControladorPublicaciones();
	            	ICP.altaPaqueteTipoPublicacion(nombreTipo, descripcionTipo, validez, descuento, fechaAlta,url_imagen);
	            }      
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
    
   private void cargarDatosTiposPublicacionPaquetes(String csvFile){	
	    String line = "";
	    String cvsSplitBy = ";";
	    try (BufferedReader bufferReader = new BufferedReader(new FileReader(csvFile))) {
	        while ((line = bufferReader.readLine()) != null) {
	            String[] tiposPublicaionPaquetesData = line.split(cvsSplitBy);
	            String nombrePaquete = "";
	            Integer cantidad = 0;
	            String nombreTipoPublicacion = "";
	            if(tiposPublicaionPaquetesData.length > 0) {
	            	nombrePaquete = tiposPublicaionPaquetesData[0];
	            	cantidad = Integer.parseInt(tiposPublicaionPaquetesData[2]);
	            	nombreTipoPublicacion = tiposPublicaionPaquetesData[1];
	            	Fabrica factory = Fabrica.getInstance();
	            	IControladorPublicaciones ICP = factory.getIControladorPublicaciones();
	            	ICP.agregarTipoPublicacion(nombrePaquete,cantidad,nombreTipoPublicacion);
	            }
	            
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
   
   private void cargarDatosOfertasLaboralesPrueba(String csvFile) throws NombreExisteException, KeywordExisteException, NicknameNoExisteException{	
	   	String line = "";
	    String cvsSplitBy = ";";
	    int iter = 1;
	    try (BufferedReader bufferReader = new BufferedReader(new FileReader(csvFile))) {
	    	bufferReader.readLine();
	        while ((line = bufferReader.readLine()) != null) {
	            String[] ofertasLaboralesData = line.split(cvsSplitBy);
	            String nombre = "";    
	            String desc = ""; 
	            String rem = ""; 
	            String horario = ""; 
	            List<String> keysList = new ArrayList<>();
	            String ciudad = ""; 
	            String depa = ""; 
	            String tipo = "";
	            String empresa = ""; 
	            String url_imagen = "";  
	            String estado = "";
	            String formaPago = "";
	            String compraPaquete = "";
	            String paqueteSeleccionado = "";
	            Fabrica factory = Fabrica.getInstance();
           	 	IControladorOfertas ICO = factory.getIControladorOfertas();
	            if(ofertasLaboralesData.length > 0) {
	            	
	            	 nombre = ofertasLaboralesData[0];
	            	 desc = ofertasLaboralesData[1];
	            	 rem = ofertasLaboralesData[5];
	            	 horario = ofertasLaboralesData[4];
	            	 ciudad = ofertasLaboralesData[3];
	            	 depa = ofertasLaboralesData[2];
	            	 tipo = ofertasLaboralesData[7];
	            	 empresa = ofertasLaboralesData[6];
	            	 estado = ofertasLaboralesData[9];
	            	 compraPaquete = ofertasLaboralesData[10];
	            	 if ("Sin paquete".equals(compraPaquete)) {
	            		    formaPago = "General";
	            		} else {
	            		    formaPago = "Paquete adquirido previamente";
	            		    paqueteSeleccionado = compraPaquete;
	            		}

	            	 
			         url_imagen = "media/img/imgOfertas/O"+iter+".jpg";
			         
			         iter++;
			         keysList = Arrays.asList(ofertasLaboralesData[12].split("/"));
		                
		             // Convertir la lista keysList a un array de String
		             String[] keysArray = keysList.toArray(new String[0]);
                     
		             //cambio de string al enumerado el estado
		             EnumEstadoOferta estadoEnum = null;
		             if ("Ingresada".equals(estado)) {
		                 estadoEnum = EnumEstadoOferta.INGRESADA;
		             } else if ("Confirmada".equals(estado)) {
		                 estadoEnum = EnumEstadoOferta.CONFIRMADA;
		             } else if ("Rechazada".equals(estado)) {
		                 estadoEnum = EnumEstadoOferta.RECHAZADA;
		             }
		             try {
		                 ICO.altaOfertaWeb(nombre, desc, rem, horario, ciudad, depa, tipo, formaPago, paqueteSeleccionado,estadoEnum , keysArray, url_imagen, empresa);
	            	 }catch(Exception e) {
	            		 e.printStackTrace();
	            	 }
	            } 
	            
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
   
   private void cargarPostulaciones(String csvFile) throws FileNotFoundException, IOException {
	   String line = "";
	   String cvsSplitBy = ";";
	   try (BufferedReader bufferReader = new BufferedReader(new FileReader(csvFile))) {
		   while ((line = bufferReader.readLine()) != null) {
			   //String nombreOfertaLaboral, String nicknamePostulante, String CVReducido, String motivacion,  LocalDateTime fechaPublic 
			   String[] postulacionesData = line.split(cvsSplitBy);
	           String nombre = "";    
	           String motivacion = "";
	           String nickname = ""; 
	           String cv = "";
	           String fecha = "";
	           String urlVideo = "";
	           
	           if(postulacionesData.length > 0) {
	        	   nickname = postulacionesData[0];
	        	   nombre = postulacionesData[4];
	        	   cv = postulacionesData[1];
	        	   motivacion = postulacionesData[2];
	        	   fecha= postulacionesData[3].trim();
	        	   if(postulacionesData.length > 5) {
	        		   urlVideo=postulacionesData[5];
	        	   }
	               String fechaConvertida = convertirFecha(fecha);
	        	   Fabrica factory = Fabrica.getInstance();
	               IControladorOfertas ICO = factory.getIControladorOfertas();
	               try {
	            	   ICO.postularAOferta(nombre, nickname, cv, motivacion, fechaConvertida, urlVideo);
            	   } catch(Exception e) {
            		   e.printStackTrace();
            	   }
	           }

		   }
	   }catch (IOException e) {
	        e.printStackTrace();
	   }
   }
   
   private void cargarPostulantesOfertasLaboralesFavoritas(String csvFile) throws FileNotFoundException, IOException, NicknameNoExisteException {
	   String line = "";
	   String cvsSplitBy = ";";
	   try (BufferedReader bufferReader = new BufferedReader(new FileReader(csvFile))) {
		   while ((line = bufferReader.readLine()) != null) {
			   String[] postulacionesData = line.split(cvsSplitBy);
			   String nickname = "";
	           String nombreOferta = "";
	           
	           if(postulacionesData.length > 0) {
	        	   nickname = postulacionesData[0];
	        	   nombreOferta = postulacionesData[1];
	        	   Fabrica factory = Fabrica.getInstance();
	               IControladorOfertas ICO = factory.getIControladorOfertas();
	               ICO.agregarEliminarFavorito(nickname, nombreOferta);
	           }
		   }
	   } catch (IOException e) {
		   e.printStackTrace();
	   }
   }
   
   public static String convertirFecha(String fechaOriginal) {
       // Parsear la fecha original a un objeto LocalDate
       DateTimeFormatter formatterOriginal = DateTimeFormatter.ofPattern("dd/MM/yyyy");
       LocalDate fechaLocalDate = LocalDate.parse(fechaOriginal, formatterOriginal);

       // Convertir el objeto LocalDate a un objeto LocalDateTime, añadiendo una hora específica (medianoche)
       LocalDateTime fechaLocalDateTime = fechaLocalDate.atStartOfDay();

       // Formatear el objeto LocalDateTime a una cadena en el formato deseado
       DateTimeFormatter formatterConvertido = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
       String fechaConvertida = fechaLocalDateTime.format(formatterConvertido);

       return fechaConvertida;
   }

}
