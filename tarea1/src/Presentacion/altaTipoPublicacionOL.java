package Presentacion;

import logica.ControladorPublicaciones;
import logica.IControladorPublicaciones;


import java.awt.EventQueue;
import javax.swing.JInternalFrame;

import excepciones.TipoPublicExisteException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class altaTipoPublicacionOL extends JInternalFrame {
	
	private static final long serialVersionUID = 1L;

	private IControladorPublicaciones controlPub;
	
	private JTextField nombreField;
	private JTextArea descripcionArea; 
    private JSpinner spinner; 
    private JSpinner spinner_1;
    private JSpinner spinner_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IControladorPublicaciones controlPub = new ControladorPublicaciones();
					altaTipoPublicacionOL frame = new altaTipoPublicacionOL(controlPub);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws PropertyVetoException 
	 */
	public altaTipoPublicacionOL(IControladorPublicaciones ipu) throws PropertyVetoException {
		
		controlPub = ipu;
		
		setResizable(false);
		setMaximum(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Alta de Tipo de Publicación de Oferta Laboral");
		setBounds(100, 100, 1017, 585);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(23, 11, 514, 14);
		getContentPane().add(lblNewLabel);
		
		nombreField = new JTextField();
		nombreField.setBounds(23, 27, 514, 20);
		getContentPane().add(nombreField);
		nombreField.setColumns(10);
		
		JLabel lblDescripcin = new JLabel("Descripción:");
		lblDescripcin.setBounds(23, 57, 514, 14);
		getContentPane().add(lblDescripcin);
		
		descripcionArea = new JTextArea();
		descripcionArea.setBounds(23, 76, 514, 85);
		getContentPane().add(descripcionArea);
		
		JLabel lblNewLabel_1 = new JLabel("Exposición:");
		lblNewLabel_1.setBounds(23, 172, 514, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Duración de Publicación (Días):");
		lblNewLabel_1_1.setBounds(23, 221, 514, 14);
		getContentPane().add(lblNewLabel_1_1);
		
		spinner = new JSpinner();
		spinner.setBounds(23, 237, 149, 20);
		getContentPane().add(spinner);
		
		JLabel lblNewLabel_2 = new JLabel("Costo:");
		lblNewLabel_2.setBounds(23, 268, 46, 14);
		getContentPane().add(lblNewLabel_2);
		
		spinner_1 = new JSpinner();
		spinner_1.setBounds(23, 283, 149, 20);
		getContentPane().add(spinner_1);
		
		JButton buttonAceptar = new JButton("Aceptar");
		buttonAceptar.setBounds(106, 368, 89, 23);
		getContentPane().add(buttonAceptar);
		buttonAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                cmdRegistroTipoActionPerformed(arg0);
            }
        });
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(332, 368, 89, 23);
		getContentPane().add(btnCancelar);
		
		spinner_2 = new JSpinner();
		spinner_2.setBounds(23, 190, 149, 20);
		getContentPane().add(spinner_2);

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	
	 protected void cmdRegistroTipoActionPerformed(ActionEvent arg0) {
		 
		 if (esValido()) {
			//Obtengo los datos de las entradas
			 String nombreTipo = this.nombreField.getText();
			 String descripcionTipo = this.descripcionArea.getText();
			 int exposicionTipo = (int) this.spinner_2.getValue();
			 Integer duracionTipo = (int) this.spinner.getValue();
			 Integer costoTipo = (int) this.spinner_1.getValue();
			 
			 try {
				//Obtengo fecha y hora actual
				 LocalDate fecha = LocalDate.now();
				 controlPub.altaTipoPublicacionOL(nombreTipo, descripcionTipo, exposicionTipo, duracionTipo, costoTipo, fecha);
				 //Muestro mensake de exito
				 JOptionPane.showMessageDialog(this, "El Tipo de Publicacion de Oferta Laboral se ha creado con éxito", "Registrar Tipo de Publicacion",
	                        JOptionPane.INFORMATION_MESSAGE);
				 limpiarFormulario();
				 setVisible(false);
			 }
				 catch(TipoPublicExisteException err){
						// Muestro error de registro
		                JOptionPane.showMessageDialog(this, err.getMessage(), "Registrar Tipo de Publicacion", JOptionPane.ERROR_MESSAGE);
					}
			 }
	 }
	 
	 public Boolean esValido() {
		    String nombreTipo = this.nombreField.getText();
		    String descripcionTipo = this.descripcionArea.getText();
		    int exposicionTipo = (int) this.spinner_2.getValue();
		    Integer duracionTipo = (int) this.spinner.getValue();
		    Integer costoTipo = (int) this.spinner_1.getValue();

		    // Verificar si están vacíos
		    if (nombreTipo.isEmpty() || descripcionTipo.isEmpty()) {
		        JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", 
		                "Registrar Tipo de Publicacion de Oferta Laboral",
		                JOptionPane.ERROR_MESSAGE);
		        return false;
		    }

		    // Verificar longitud de los campos
		    if (nombreTipo.length() > 50 || descripcionTipo.length() > 400) {
		        JOptionPane.showMessageDialog(this, "No debe exceder la longitud máxima permitida en los campos", 
		                "Registrar Tipo de Publicacion de Oferta Laboral",
		                JOptionPane.ERROR_MESSAGE);
		        return false;
		    }

		    // Verificar los valores numéricos
		    if (exposicionTipo < 0 || duracionTipo <= 0 || costoTipo < 0) {
		        JOptionPane.showMessageDialog(this, "Los valores numéricos deben ser positivos y adecuados", 
		                "Registrar Tipo de Publicacion de Oferta Laboral",
		                JOptionPane.ERROR_MESSAGE);
		        return false;
		    }

		    return true;
		}
	 
	 private void limpiarFormulario() {
		 nombreField.setText("");
		 descripcionArea.setText("");
		 spinner_2.setValue(0);
		 spinner.setValue(0); 
		 spinner_1.setValue(0);
		   }
}
