package Presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;
import java.awt.TextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import excepciones.KeywordExisteException;
import excepciones.NicknameNoExisteException;
import excepciones.NombreExisteException;
import logica.IControladorOfertas;
import logica.IControladorPublicaciones;
import logica.IControladorUsuario;
import servidor.types.DTEmpresa;
import servidor.types.DTTipoPublicacion;
import servidor.types.DTUsuario;

import javax.swing.JSpinner;
import java.awt.Button;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;



@SuppressWarnings("serial")
public class altaOfertaLaboral extends JInternalFrame {
	
	private JTextField horarioField;
	private JComboBox<String> boxEmpresa;
	private JComboBox<String> boxTipoPublicacion;
	private IControladorUsuario ctrlUsuario;
	private IControladorOfertas ctrlOfertas;
	private IControladorPublicaciones ctrlPublicaciones;
	private TextField nombreField;
	private TextArea descripcionTextArea;
	private JSpinner remuneracionSpiner;
	private TextField ciudadField;
	private TextField departamentoField;
	private JList<String> keywordsList;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					altaOfertaLaboral frame = new altaOfertaLaboral(ctrlUsuario);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * @throws PropertyVetoException 
	 */
	public altaOfertaLaboral(IControladorUsuario ICU, IControladorPublicaciones ICP, IControladorOfertas ICO) throws PropertyVetoException {
		ctrlUsuario = ICU;
		ctrlPublicaciones = ICP;
		ctrlOfertas = ICO;
		
		setResizable(false);
		setMaximum(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Alta de Oferta Laboral");
		setBounds(100, 100, 985, 600);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccione una empresa:");
		lblNewLabel.setBounds(25, 21, 878, 14);
		getContentPane().add(lblNewLabel);
		
		boxEmpresa = new JComboBox<String>();
		boxEmpresa.setBounds(25, 46, 721, 22);
		getContentPane().add(boxEmpresa);
		this.cargarUsuarios();
		
		JLabel lblSeleccioneUnTipo = new JLabel("Seleccione un tipo de publicación:");
		lblSeleccioneUnTipo.setBounds(25, 79, 878, 14);
		getContentPane().add(lblSeleccioneUnTipo);
		
		boxTipoPublicacion = new JComboBox<String>();
		boxTipoPublicacion.setBounds(25, 102, 721, 22);
		getContentPane().add(boxTipoPublicacion);
		
		JLabel lblIngreseNombreDe = new JLabel("Nombre:");
		lblIngreseNombreDe.setBounds(25, 135, 878, 14);
		getContentPane().add(lblIngreseNombreDe);
		
		nombreField = new TextField();
		nombreField.setBounds(25, 155, 354, 22);
		getContentPane().add(nombreField);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setBounds(25, 191, 878, 14);
		getContentPane().add(lblDescripcion);
		
		descripcionTextArea = new TextArea();
		descripcionTextArea.setBounds(25, 211, 721, 88);
		getContentPane().add(descripcionTextArea);
		
		JLabel lblNewLabel_1 = new JLabel("Horario:");
		lblNewLabel_1.setBounds(25, 310, 179, 14);
		getContentPane().add(lblNewLabel_1);
		
		horarioField = new JTextField();
		horarioField.setBounds(25, 335, 178, 20);
		getContentPane().add(horarioField);
		horarioField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Remuneración:");
		lblNewLabel_2.setBounds(25, 366, 178, 14);
		getContentPane().add(lblNewLabel_2);
		
		remuneracionSpiner = new JSpinner();
		remuneracionSpiner.setBounds(25, 392, 178, 20);
		getContentPane().add(remuneracionSpiner);
		
		JLabel lblNewLabel_3 = new JLabel("Ciudad:");
		lblNewLabel_3.setBounds(241, 310, 178, 14);
		getContentPane().add(lblNewLabel_3);
		
		ciudadField = new TextField();
		ciudadField.setBounds(241, 333, 178, 22);
		getContentPane().add(ciudadField);
		
		JLabel lblNewLabel_3_1 = new JLabel("Departamento:");
		lblNewLabel_3_1.setBounds(241, 366, 178, 14);
		getContentPane().add(lblNewLabel_3_1);
		
		departamentoField = new TextField();
		departamentoField.setBounds(241, 390, 178, 22);
		getContentPane().add(departamentoField);
		
		Button buttonAceptar = new Button("Aceptar ");
		buttonAceptar.setBackground(new Color(255, 255, 255));
		buttonAceptar.setBounds(24, 542, 70, 22);
		getContentPane().add(buttonAceptar);
		
		Button buttonCancelar = new Button("Cancelar");
		buttonCancelar.setBackground(Color.WHITE);
		buttonCancelar.setBounds(309, 542, 70, 22);
		getContentPane().add(buttonCancelar);
		
		JLabel lblNewLabel_4 = new JLabel("Keywords");
        lblNewLabel_4.setBounds(24, 423, 70, 14);
        getContentPane().add(lblNewLabel_4);

        keywordsList = new JList<String>();
        keywordsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane keywordsScrollPane = new JScrollPane(keywordsList);
        keywordsScrollPane.setBounds(25, 440, 354, 80);
        getContentPane().add(keywordsScrollPane);
		
		buttonCancelar.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 dispose();
	            }
		});
		
		buttonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearOferta(e);
			}
		});
	}
	
	public void cargarUsuarios() {
		boxEmpresa.removeAllItems();
		List<DTEmpresa> datos = new ArrayList<>();
		datos = ctrlUsuario.listarEmpresas();
		for (DTUsuario u : datos) {
			boxEmpresa.addItem(u.getNickname());
		}
    }
	
	public void cargarTipos() {
		boxTipoPublicacion.removeAllItems();
		List<DTTipoPublicacion> datos = new ArrayList<>();
		datos = ctrlPublicaciones.obtenerTipos();
		System.out.print(datos);
		for (DTTipoPublicacion t : datos) {
			boxTipoPublicacion.addItem(t.getNombre());
		}
    }
	
	public void cargarKeywords() {
		List<String> keywordsDisponibles = ctrlOfertas.obtenerKeywords();
        keywordsList.setListData(keywordsDisponibles.toArray(new String[keywordsDisponibles.size()]));

	}
	
	public void crearOferta(ActionEvent e) {
	    String nombre = this.nombreField.getText();
	    String desc = this.descripcionTextArea.getText();
	    String depa = this.departamentoField.getText();
	    String ciudad = this.ciudadField.getText();
	    String horario = this.horarioField.getText();
	    String empresa = (String) this.boxEmpresa.getSelectedItem();
	    String tipo = (String) this.boxTipoPublicacion.getSelectedItem();
	    String rem = this.remuneracionSpiner.getValue().toString();
	    List<String> keys = this.keywordsList.getSelectedValuesList();

	    if (nombre.isEmpty() || desc.isEmpty() || depa.isEmpty() || ciudad.isEmpty() || horario.isEmpty() || empresa.isEmpty() || tipo.isEmpty() || rem.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Alta de Oferta Laboral", JOptionPane.ERROR_MESSAGE);
	    } else if (nombre.length() > 50 || depa.length() > 50 || ciudad.length() > 50 || horario.length() > 50 || empresa.length() > 50 || tipo.length() > 50 || rem.length() > 50 || desc.length() > 400) {
	        JOptionPane.showMessageDialog(this, "Uno o más campos exceden la longitud máxima permitida", "Alta de Oferta Laboral", JOptionPane.ERROR_MESSAGE);
	    } else {
	        try {
	            this.ctrlOfertas.altaOferta(nombre, desc, rem, horario, keys, ciudad, depa, tipo, empresa);
	            // Muestro éxito de la operación
	            JOptionPane.showMessageDialog(this, "La oferta se ha creado con éxito", "Alta de Oferta Laboral", JOptionPane.INFORMATION_MESSAGE);
	            // Limpio el internal frame antes de cerrar la ventana
	            limpiarFormulario();
	            setVisible(false);
	        } catch (NombreExisteException e1) {
	            JOptionPane.showMessageDialog(this, e1.getMessage(), "Alta de Oferta Laboral", JOptionPane.ERROR_MESSAGE);
	        } catch (KeywordExisteException e1) {
	            JOptionPane.showMessageDialog(this, e1.getMessage(), "Alta de Oferta Laboral", JOptionPane.ERROR_MESSAGE);
	        } catch (NicknameNoExisteException e1) {
	            JOptionPane.showMessageDialog(this, e1.getMessage(), "Alta de Oferta Laboral", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}
	
	private void limpiarFormulario() {
		horarioField.setText("");
		boxEmpresa.removeAllItems();
		boxTipoPublicacion.removeAllItems();
		nombreField.setText("");
		descripcionTextArea.setText("");
		remuneracionSpiner.setValue(Integer.valueOf(0));
		ciudadField.setText("");
		departamentoField.setText("");
		/*
		 * falta limpiar lo que tiene adentro la JList "keywordsList"
		keywordsList.limpiarLoQueTieneAdentro();
		*/
	}
	
}
