package Presentacion;

import servidor.publicar.ServicioOfertas;
import servidor.publicar.ServicioUsuarios;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JInternalFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import excepciones.CorreoRepetidoException;
import excepciones.KeywordExisteException;
import excepciones.NicknameNoExisteException;
import excepciones.NombreExisteException;
import excepciones.OfertaNoExisteException;
import excepciones.PaqueteExisteException;
import excepciones.TipoPublicExisteException;
import excepciones.UsuarioNoEsPostulanteException;
import excepciones.UsuarioRepetidoException;

//para carga de datos
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import logica.Fabrica;
import logica.IControladorOfertas;
import logica.IControladorPublicaciones;
import logica.IControladorUsuario;
import servidor.types.EnumEstadoOferta;

//import excepciones.OfertaNoExisteException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import excepciones.UsuarioNoEsPostulanteException;

public class Principal {
	
	//Interfaces
	private IControladorUsuario ICU;
	private IControladorOfertas ICO;
	private IControladorPublicaciones ICP;
	
	//Componentes Swing
	
	private JFrame frame;
	private altaPostulante altaPostulanteInternalFrame;
	private altaEmpresa altaEmpresaInternalFrame;
	private consultaUsuario consultaUsuarioInternalFrame;
	private altaOfertaLaboral altaOfertaLaboralInternalFrame;
	private consultaOfertaLaboral consultaOfertaLaboralInternalFrame;
	private altaTipoPublicacionOL altaTipoPublicacionOLInternalFrame;
	private postulacionOfertaLaboral postulacionOfertaLaboralInternalFrame;
	private crearPaqueteTipo crearPaqueteTipoInternalFrame;
	private consultaPaquete consultaPaqueteInternalFrame;
	private agregarTipoPubAPaquete agregarTipoAPaqueteInternalFrame;
	private AceptarRechazarOfertaLaboral aceptarRechazarInternalFrame;
	private JInternalFrame currentInternalFrame = null;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					ServicioOfertas o = new ServicioOfertas();
			        o.publicar();
			        ServicioUsuarios u = new ServicioUsuarios();
			        u.publicar();
			        
					Principal window = new Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws PropertyVetoException 
	 */
	public Principal() throws PropertyVetoException {
		
		initialize();
		//Inicializacion fabrica y controladores
		Fabrica fabrica = Fabrica.getInstance();
		ICU = fabrica.getIControladorUsuario();
		ICP = fabrica.getIControladorPublicaciones();	
		ICO = fabrica.getIControladorOfertas();		
		
		altaPostulanteInternalFrame = new altaPostulante(ICU);
		altaPostulanteInternalFrame.setResizable(false);
		altaPostulanteInternalFrame.setBorder(null);
		altaPostulanteInternalFrame.setVisible(false);
		
		altaEmpresaInternalFrame = new altaEmpresa(ICU);
		altaEmpresaInternalFrame.setResizable(false);
		altaEmpresaInternalFrame.setBorder(null);
		altaEmpresaInternalFrame.setVisible(false);
		
		
		consultaUsuarioInternalFrame = new consultaUsuario(ICU,ICO);
		consultaUsuarioInternalFrame.setResizable(false);
		consultaUsuarioInternalFrame.setBorder(null);
		consultaUsuarioInternalFrame.setVisible(false);
		
		altaOfertaLaboralInternalFrame = new altaOfertaLaboral(ICU, ICP, ICO);
		altaOfertaLaboralInternalFrame.setResizable(false);
		altaOfertaLaboralInternalFrame.setBorder(null);
		altaOfertaLaboralInternalFrame.setVisible(false);
		
		consultaOfertaLaboralInternalFrame = new consultaOfertaLaboral(ICU, ICO);
		consultaOfertaLaboralInternalFrame.setResizable(false);
		consultaOfertaLaboralInternalFrame.setBorder(null);
		consultaOfertaLaboralInternalFrame.setVisible(false);
		
		
		altaTipoPublicacionOLInternalFrame = new altaTipoPublicacionOL(ICP);
		altaTipoPublicacionOLInternalFrame.setResizable(false);
		altaTipoPublicacionOLInternalFrame.setBorder(null);
		altaTipoPublicacionOLInternalFrame.setVisible(false);
		
		postulacionOfertaLaboralInternalFrame = new postulacionOfertaLaboral(ICO, ICU);
		postulacionOfertaLaboralInternalFrame.setResizable(false);
		postulacionOfertaLaboralInternalFrame.setBorder(null);
		postulacionOfertaLaboralInternalFrame.setVisible(false);
		
		crearPaqueteTipoInternalFrame = new crearPaqueteTipo(ICP);
		crearPaqueteTipoInternalFrame.setResizable(false);
		crearPaqueteTipoInternalFrame.setBorder(null);
		crearPaqueteTipoInternalFrame.setVisible(false);
		
		consultaPaqueteInternalFrame = new consultaPaquete(ICP);
		consultaPaqueteInternalFrame.setResizable(false);
		consultaPaqueteInternalFrame.setBorder(null);
		consultaPaqueteInternalFrame.setVisible(false);
		
		agregarTipoAPaqueteInternalFrame = new agregarTipoPubAPaquete(ICP);
		agregarTipoAPaqueteInternalFrame.setResizable(false);
		agregarTipoAPaqueteInternalFrame.setBorder(null);
		agregarTipoAPaqueteInternalFrame.setVisible(false);
		
		aceptarRechazarInternalFrame = new AceptarRechazarOfertaLaboral(ICU,ICO);
		aceptarRechazarInternalFrame.setResizable(false);
		aceptarRechazarInternalFrame.setBorder(null);
		aceptarRechazarInternalFrame.setVisible(false);
		

		frame.getContentPane().add(altaPostulanteInternalFrame);
		frame.getContentPane().add(altaEmpresaInternalFrame);
		frame.getContentPane().add(consultaUsuarioInternalFrame);
		frame.getContentPane().add(altaOfertaLaboralInternalFrame);
		frame.getContentPane().add(consultaOfertaLaboralInternalFrame);
		frame.getContentPane().add(altaTipoPublicacionOLInternalFrame);
		frame.getContentPane().add(postulacionOfertaLaboralInternalFrame);
		frame.getContentPane().add(crearPaqueteTipoInternalFrame);
		frame.getContentPane().add(consultaPaqueteInternalFrame);
		frame.getContentPane().add(agregarTipoAPaqueteInternalFrame);
		frame.getContentPane().add(aceptarRechazarInternalFrame);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1081, 687);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu menuPrincipal = new JMenu("Menú Principal");
		menuBar.add(menuPrincipal);
		
		JMenu menuUsuarios = new JMenu("Usuarios");
		menuBar.add(menuUsuarios);
		
		JMenu menuOfertasLaborales = new JMenu("Ofertas Laborales");
		menuBar.add(menuOfertasLaborales);
		
		JMenuItem mItemAltaPostulante = new JMenuItem("Alta de Postulante");
		mItemAltaPostulante.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 if (currentInternalFrame != null) {
			            currentInternalFrame.setVisible(false);
			        }

	                altaPostulanteInternalFrame.setVisible(true);
	                currentInternalFrame = altaPostulanteInternalFrame;
	            }
		});

		
		JMenuItem mItemAltaEmpresa = new JMenuItem("Alta de Empresa");
		mItemAltaEmpresa.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 if (currentInternalFrame != null) {
			            currentInternalFrame.setVisible(false);
			        }
	                altaEmpresaInternalFrame.setVisible(true);
	                currentInternalFrame = altaEmpresaInternalFrame;
	            }
		});
			
		menuUsuarios.add(mItemAltaPostulante);
		menuUsuarios.add(mItemAltaEmpresa);
		
		
		JMenuItem mItemConsultaUsuario = new JMenuItem("Consulta de Usuario");
		mItemConsultaUsuario.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 if (currentInternalFrame != null) {
			            currentInternalFrame.setVisible(false);
			        }
				 	consultaUsuarioInternalFrame.llenar_comboListaUsuario();
	                consultaUsuarioInternalFrame.setVisible(true);
	                currentInternalFrame = consultaUsuarioInternalFrame;
	            }
		});
		menuUsuarios.add(mItemConsultaUsuario);

		JMenuItem mItemOfertaLaboral = new JMenuItem("Alta de Oferta Laboral");
		mItemOfertaLaboral.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 altaOfertaLaboralInternalFrame.cargarUsuarios();
				 altaOfertaLaboralInternalFrame.cargarTipos();
				 altaOfertaLaboralInternalFrame.cargarKeywords();
				 if (currentInternalFrame != null) {
			            currentInternalFrame.setVisible(false);
			        }
				 altaOfertaLaboralInternalFrame.setVisible(true);
				 currentInternalFrame = altaOfertaLaboralInternalFrame;
	            }
		});	
		menuOfertasLaborales.add(mItemOfertaLaboral);
		
		
		JMenuItem mItemConsultaOfertaLaboral = new JMenuItem("Consulta de Oferta Laboral");
		mItemConsultaOfertaLaboral.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 if (currentInternalFrame != null) {
			            currentInternalFrame.setVisible(false);
				 }
				 consultaOfertaLaboralInternalFrame.llenar_comboListaEmpresa();
				 consultaOfertaLaboralInternalFrame.setVisible(true);
				 currentInternalFrame = consultaOfertaLaboralInternalFrame;
	            }
		});	
		menuOfertasLaborales.add(mItemConsultaOfertaLaboral);
		
		JMenuItem mItemPostulacionOfertaLaboral = new JMenuItem("Postulación a Oferta Laboral");
		mItemPostulacionOfertaLaboral.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 if (currentInternalFrame != null) {
			            currentInternalFrame.setVisible(false);
				 }
				 postulacionOfertaLaboralInternalFrame.comboMostrarEmpresas();
				 postulacionOfertaLaboralInternalFrame.setVisible(true);
				 currentInternalFrame = postulacionOfertaLaboralInternalFrame;
	            }
		});	
		menuOfertasLaborales.add(mItemPostulacionOfertaLaboral);
		
		JMenuItem mItemAltaTipoPublicacion = new JMenuItem("Alta de Tipo de Publicación de Oferta Laboral");
		mItemAltaTipoPublicacion.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 if (currentInternalFrame != null) {
			            currentInternalFrame.setVisible(false);
				 }
				 altaTipoPublicacionOLInternalFrame.setVisible(true);
				 currentInternalFrame = altaTipoPublicacionOLInternalFrame;
	            }
		});	
		menuOfertasLaborales.add(mItemAltaTipoPublicacion);
		
		JMenuItem mItemCrearPaquetes = new JMenuItem("Crear Paquete de Tipos de Publicación");
		menuOfertasLaborales.add(mItemCrearPaquetes);
		mItemCrearPaquetes.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 if (currentInternalFrame != null) {
			            currentInternalFrame.setVisible(false);
				 }
				 crearPaqueteTipoInternalFrame.setVisible(true);
				 currentInternalFrame = crearPaqueteTipoInternalFrame;
	            }
		});	
		
		
		
		JMenuItem mItemAgregarTipoAPaquete = new JMenuItem("Agregar Tipo de Publicación a Paquete");
		menuOfertasLaborales.add(mItemAgregarTipoAPaquete);
		mItemAgregarTipoAPaquete.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 if (currentInternalFrame != null) {
			            currentInternalFrame.setVisible(false);
				 }
				 agregarTipoAPaqueteInternalFrame.llenar_listaTipos();
				 agregarTipoAPaqueteInternalFrame.llenar_listaPaquetes();
				 agregarTipoAPaqueteInternalFrame.setVisible(true);
				 currentInternalFrame = agregarTipoAPaqueteInternalFrame;
	            }
		});
		
		
		JMenuItem mItemConsultaPaquete = new JMenuItem("Consulta de Paquete de Tipos de Publicación");
		menuOfertasLaborales.add(mItemConsultaPaquete);
		
		mItemConsultaPaquete.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 if (currentInternalFrame != null) {
			            currentInternalFrame.setVisible(false);
				 }
				 consultaPaqueteInternalFrame.llenar_listaPaquetes();
				 consultaPaqueteInternalFrame.setVisible(true);
				 currentInternalFrame = consultaPaqueteInternalFrame;
	            }
		});	
		
		JMenuItem mItemAceptar = new JMenuItem("Aceptar/Rechazar Oferta Laboral");
		menuOfertasLaborales.add(mItemAceptar);
		mItemAceptar.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 if (currentInternalFrame != null) {
			            currentInternalFrame.setVisible(false);
				 }
				 aceptarRechazarInternalFrame.llenar_comboListaEmpresa();
				 aceptarRechazarInternalFrame.setVisible(true);
				 currentInternalFrame = aceptarRechazarInternalFrame;
	            }
		});	
		

		JMenuItem mntmNewMenuItem = new JMenuItem("Cargar Datos");
		mntmNewMenuItem.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	try {
		    		String currentDirectory = System.getProperty("user.dir");
		    		
		            String csvFilePathPostulantes = currentDirectory + File.separator + "DatosCSV" + File.separator + "Postulantes.csv";
					cargarDatosPostulantes(csvFilePathPostulantes);

		            String csvFilePathEmpresas = currentDirectory + File.separator + "DatosCSV" + File.separator + "Empresas.csv";
					cargarDatosEmpresas(csvFilePathEmpresas);
					
					String csvFilePathTiposPublicacion = currentDirectory + File.separator + "DatosCSV" + File.separator + "TiposPublicacion.csv";
					cargarDatosTipoPublicacion(csvFilePathTiposPublicacion);
					
					String csvFilePathKeywords = currentDirectory + File.separator + "DatosCSV" + File.separator + "Keywords.csv";
					cargarDatosKeywords(csvFilePathKeywords);
					
					String csvFileOfertaLaboral = currentDirectory + File.separator + "DatosCSV" + File.separator + "OfertasLaboralesPrueba2.csv";
					cargarDatosOfertasLaboralesPrueba(csvFileOfertaLaboral);
					
					String csvFilePostulaciones = currentDirectory + File.separator + "DatosCSV" + File.separator + "Postulaciones.csv";
					cargarPostulaciones(csvFilePostulaciones);
					
					String csvFilePathPaquetes = currentDirectory + File.separator + "DatosCSV" + File.separator + "Paquetes.csv";
					cargarDatosPaquetes(csvFilePathPaquetes);
					
					String csvFilePathTiposPublicacionPaquetes = currentDirectory + File.separator + "DatosCSV" + File.separator + "TiposPublicacionPaquetes.csv";
					cargarDatosTiposPublicacionPaquetes(csvFilePathTiposPublicacionPaquetes);
					
					String csvFilePathPostulantesOfertasLaboralesFavoritas = currentDirectory + File.separator + "DatosCSV" + File.separator + "PostulantesOfertasLaboralesFavoritas.csv";
					cargarPostulantesOfertasLaboralesFavoritas(csvFilePathPostulantesOfertasLaboralesFavoritas);
					
					String csvFilePathResultadoPostulacion = currentDirectory + File.separator + "DatosCSV" + File.separator + "ResultadoPostulacion.csv";
					cargarResultadoPostulacion(csvFilePathResultadoPostulacion);

					
				} catch (UsuarioRepetidoException e1) {
					e1.printStackTrace();
				} catch (TipoPublicExisteException e1) {
					
					e1.printStackTrace();
				} catch (KeywordExisteException e1) {
					
					e1.printStackTrace();
				} catch (NombreExisteException e1) {
					
					e1.printStackTrace();
				} catch (NicknameNoExisteException e1) {
					
					e1.printStackTrace();
				} catch (CorreoRepetidoException e1) {
					
					e1.printStackTrace();
				} catch (PaqueteExisteException e1) {
					
					e1.printStackTrace();
				} catch (FileNotFoundException e1) {
					
					e1.printStackTrace();
				} catch (IOException e1) {
					
					e1.printStackTrace();
				} catch (UsuarioNoEsPostulanteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (OfertaNoExisteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }
		});
		menuPrincipal.add(mntmNewMenuItem);
		
		JMenuItem mItemSalir= new JMenuItem("Salir");
		mItemSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
		menuPrincipal.add(mItemSalir);
		
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
		             else if ("Finalizada".equals(estado)) {
		                 estadoEnum = EnumEstadoOferta.FINALIZADA;
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
   
   private void cargarPostulaciones(String csvFile) throws FileNotFoundException, IOException, NicknameNoExisteException, UsuarioNoEsPostulanteException, OfertaNoExisteException {
	   String line = "";
	   String cvsSplitBy = ";";
	   try (BufferedReader bufferReader = new BufferedReader(new FileReader(csvFile))) {
		   while ((line = bufferReader.readLine()) != null) {
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
	               ICO.postularAOferta(nombre,nickname,cv,motivacion,fechaConvertida, urlVideo); 
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
	   }catch (IOException e) {
	        e.printStackTrace();
	    }
   }
   
   private void cargarResultadoPostulacion(String csvFile) throws FileNotFoundException, IOException {
	   String line = "";
	   String cvsSplitBy = ";";
	   try (BufferedReader bufferReader = new BufferedReader(new FileReader(csvFile))) {
		   while ((line = bufferReader.readLine()) != null) {
			   String[] resultadoData = line.split(cvsSplitBy);
			   String nombreOferta = ""; 
	           String postulaciones = "";    
	           if(resultadoData.length > 1) { // Updated condition
	        	    nombreOferta = resultadoData[0];
	        	    postulaciones = resultadoData[1];
	        	    Fabrica factory = Fabrica.getInstance();
	        	    IControladorOfertas ICO = factory.getIControladorOfertas();
	        	    ICO.seleccionarPostulaciones(nombreOferta, postulaciones);
	        	}

		   }
	   }catch (IOException e) {
	        e.printStackTrace();
	    }
   }

}
