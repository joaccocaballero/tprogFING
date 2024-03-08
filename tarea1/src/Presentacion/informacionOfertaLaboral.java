package Presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Panel;
import javax.swing.JSeparator;
import java.awt.ScrollPane;
import java.beans.PropertyVetoException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import excepciones.OfertaNoExisteException;
import logica.IControladorOfertas;
import servidor.types.DTOferta;
import servidor.types.DTPostulacion;
import utilsPresentacion.MultiLineCellRenderer;

import java.awt.GridLayout;
import java.awt.Scrollbar;
import javax.swing.JPanel;

public class informacionOfertaLaboral extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	
	private IControladorOfertas controlOL;

	
	
	//datos ofertas
	private String ofertaNombre;
	private String nombre;
	private String descripcion;
	private String ciudad;
	private String departamento;
	private String horario;
	private String remuneracion;
	private String fechaAlta;
	private JTable tableDatosOferta = new JTable();
	
	//datos postulaciones
	private String FechaPostulacion;
	private String CV;
	private String Motivacion;
	private String Nickname;
	private JTable tablePostulaciones = new JTable();
	

	/**
	 * Create the frame.
	 * @throws PropertyVetoException 
	 */
	@SuppressWarnings("serial")
	public informacionOfertaLaboral(IControladorOfertas ico) throws PropertyVetoException {
		controlOL = ico;
		setMaximizable(true);
		setClosable(true);
		setTitle("Información de Oferta Laboral");
		setBounds(100, 100, 1000, 643);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Datos:");
		lblNewLabel.setBounds(21, 21, 764, 14);
		getContentPane().add(lblNewLabel);
		
		JPanel panelHeaderDatos = new JPanel();
		panelHeaderDatos.setBounds(21, 35, 830, 26);
		getContentPane().add(panelHeaderDatos);
		
		Panel tablePaneDatosOferta = new Panel();
		tablePaneDatosOferta.setBounds(21, 72, 830, 105);
		getContentPane().add(tablePaneDatosOferta);

		JTableHeader headerDatos = tableDatosOferta.getTableHeader();
		panelHeaderDatos.add(headerDatos);
		panelHeaderDatos.setLayout(new GridLayout(1, 0, 0, 0));
		
		tableDatosOferta.setModel(new DefaultTableModel(
			new Object[][] {
				{nombre, descripcion, ciudad, departamento, horario, remuneracion, fechaAlta},
			},
			new String[] {
				"Nombre", "Descripción", "Ciudad", "Departamento", "Horario", "Remuneraci\u00F3n", "Fecha Alta"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, true, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableDatosOferta.getColumnModel().getColumn(0).setCellRenderer(new MultiLineCellRenderer());
		tableDatosOferta.getColumnModel().getColumn(1).setCellRenderer(new MultiLineCellRenderer());
		tableDatosOferta.getColumnModel().getColumn(2).setCellRenderer(new MultiLineCellRenderer());
		tableDatosOferta.getColumnModel().getColumn(3).setCellRenderer(new MultiLineCellRenderer());
		tableDatosOferta.getColumnModel().getColumn(4).setCellRenderer(new MultiLineCellRenderer());
		tableDatosOferta.getColumnModel().getColumn(5).setCellRenderer(new MultiLineCellRenderer());
		tableDatosOferta.getColumnModel().getColumn(6).setCellRenderer(new MultiLineCellRenderer());
		tableDatosOferta.setRowHeight(140);
		tablePaneDatosOferta.setLayout(new GridLayout(0, 1, 0, 0));
		tablePaneDatosOferta.add(tableDatosOferta);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(21, 183, 830, 2);
		getContentPane().add(separator);
		
		JLabel lblNewLabel_1 = new JLabel("Postulaciones:");
		lblNewLabel_1.setBounds(21, 193, 764, 14);
		getContentPane().add(lblNewLabel_1);
		
		Panel headersPostulacionesPane = new Panel();
		headersPostulacionesPane.setBounds(21, 213, 764, 26);
		getContentPane().add(headersPostulacionesPane);
		
		ScrollPane tablePanePostulaciones = new ScrollPane();
		tablePanePostulaciones.setBounds(21, 245, 764, 340);
		getContentPane().add(tablePanePostulaciones);
		
		Scrollbar scrollbar = new Scrollbar();
		scrollbar.setBounds(774, 182, 11, 378);
		tablePanePostulaciones.add(scrollbar);
		
		JTableHeader headerPostulaciones = tablePostulaciones.getTableHeader();
		headersPostulacionesPane.add(headerPostulaciones);
		headersPostulacionesPane.setLayout(new GridLayout(1, 0, 0, 0));
		tablePostulaciones.setFillsViewportHeight(true);
		tablePostulaciones.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Nickname","Fecha", "CV", "Motivaci\u00F3n" 
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tablePostulaciones.setBounds(220, 342, 1, 1);
		tablePostulaciones.setRowHeight(150);
		tablePostulaciones.getColumnModel().getColumn(0).setCellRenderer(new MultiLineCellRenderer());
		tablePostulaciones.getColumnModel().getColumn(1).setCellRenderer(new MultiLineCellRenderer());
		tablePostulaciones.getColumnModel().getColumn(2).setCellRenderer(new MultiLineCellRenderer());
		tablePostulaciones.getColumnModel().getColumn(3).setCellRenderer(new MultiLineCellRenderer());
		tablePanePostulaciones.add(tablePostulaciones);
		
		
	}
	
	 	public void recibirNombreOferta(String nombre) {
	        this.ofertaNombre = nombre;
	    }
	 	
	 	public void mostrarDatosOferta() throws OfertaNoExisteException {
	 		
			String oferta = this.ofertaNombre;
			//Obtengo datos oferta 
			DTOferta datosOferta = controlOL.obtenerDatosOferta(oferta);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		    nombre = datosOferta.getNombre();
		    descripcion = datosOferta.getDescripcion();
		    ciudad = datosOferta.getCiudad();
		    departamento = datosOferta.getDepartamento();
		    horario = datosOferta.getHorario();
		    remuneracion = String.valueOf(datosOferta.getRemuneracion()); // suponiendo que Remuneracion es un número, lo convertimos a String
		    fechaAlta = datosOferta.getFechaAlta().format(formatter);
			
		    DefaultTableModel tableModelDatos = (DefaultTableModel) this.tableDatosOferta.getModel();
		    tableModelDatos.setRowCount(0); // Limpiar filas existentes
		    tableModelDatos.addRow(new Object[] {nombre, descripcion, ciudad, departamento,
		    horario,remuneracion,fechaAlta});
		    
		    DefaultTableModel tableModelPostulaciones =  (DefaultTableModel) this.tablePostulaciones.getModel();
		    tableModelPostulaciones.setRowCount(0);
		    if (datosOferta.getPostulaciones() != null) {
		    	List<DTPostulacion> listadoPostulaciones = datosOferta.getPostulaciones();
		    	for (DTPostulacion elemento : listadoPostulaciones) {
		    	    FechaPostulacion = elemento.getFecha().format(formatter);;
					CV = elemento.getCvReducido();
					Motivacion = elemento.getMotivacion();
					Nickname = elemento.getNicknamePostulante();
					tableModelPostulaciones.addRow(new Object[] {Nickname,FechaPostulacion, CV, Motivacion});
		    	}	    	
		    }
		}
}
