package Presentacion;



import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


import logica.IControladorPublicaciones;
import servidor.types.DTTipoPublicacion;

import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;

public class informacionTipoPublicacion extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private IControladorPublicaciones controlPub;
	private JTextField nombreField;
	private JTextField duracionField;
	private JTextArea descripcionField;
	private JTextField costoField;
	private JTextField fechaAltaField;
	
	
	private String nombreTipo;


	/**
	 * Create the frame.
	 */
	public informacionTipoPublicacion(IControladorPublicaciones icp) {
		setResizable(true);
		setMaximizable(true);
		setClosable(true);
		controlPub = icp;

		setTitle("Información Tipo Publicación");
		setBounds(100, 100, 1042, 698);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(21, 28, 199, 14);
		getContentPane().add(lblNewLabel);
		
		nombreField = new JTextField();
		nombreField.setEditable(false);
		nombreField.setBounds(21, 46, 199, 20);
		getContentPane().add(nombreField);
		nombreField.setColumns(10);
		
		JLabel lblDuraqcin = new JLabel("Duración de Publicación:");
		lblDuraqcin.setBounds(230, 28, 199, 14);
		getContentPane().add(lblDuraqcin);
		
		duracionField = new JTextField();
		duracionField.setEditable(false);
		duracionField.setColumns(10);
		duracionField.setBounds(230, 46, 199, 20);
		getContentPane().add(duracionField);
		
		JLabel lblCosto = new JLabel("Costo:");
		lblCosto.setBounds(453, 28, 199, 14);
		getContentPane().add(lblCosto);
		
		costoField = new JTextField();
		costoField.setEditable(false);
		costoField.setColumns(10);
		costoField.setBounds(453, 46, 199, 20);
		getContentPane().add(costoField);
		
		JLabel lblFechaDeAlta = new JLabel("Fecha de Alta:");
		lblFechaDeAlta.setBounds(453, 79, 199, 14);
		getContentPane().add(lblFechaDeAlta);
		
		fechaAltaField = new JTextField();
		fechaAltaField.setEditable(false);
		fechaAltaField.setColumns(10);
		fechaAltaField.setBounds(453, 97, 199, 20);
		getContentPane().add(fechaAltaField);
		
		descripcionField = new JTextArea();
		descripcionField.setWrapStyleWord(true);
		descripcionField.setLineWrap(true);
		descripcionField.setEditable(false);
		descripcionField.setBounds(21, 95, 423, 250);
		getContentPane().add(descripcionField);
		
		JLabel lblNewLabel_4 = new JLabel("Descripción:");
		lblNewLabel_4.setBounds(21, 77, 405, 14);
		getContentPane().add(lblNewLabel_4);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(21, 368, 423, 23);
		getContentPane().add(btnSalir);

		btnSalir.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 dispose();
	          }
		});

	}

	public void recibirNombreTipo(String nombre) {
		this.nombreTipo = nombre;
	}
	
	public void mostrarDatosTipo() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		DTTipoPublicacion datosTipo = controlPub.obtenerDatosTipoPublicacion(nombreTipo);
		nombreField.setText(datosTipo.getNombre());
		duracionField.setText(datosTipo.getDuracion().toString());
		descripcionField.setText(datosTipo.getDescripcion());
		costoField.setText(datosTipo.getCosto().toString());
		fechaAltaField.setText(datosTipo.getAlta().format(formatter));		
	}

}
