package Presentacion;


import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JSpinner;

import logica.IControladorPublicaciones;
import servidor.types.DTPaquete;
import servidor.types.DTTipoPublicacion;

public class agregarTipoPubAPaquete extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private IControladorPublicaciones controlPub;
	private JComboBox<DTTipoPublicacion> listaTipoPub;
	private JComboBox<DTPaquete> listaPaquetes;
	private JSpinner cantidadField;

	public agregarTipoPubAPaquete(IControladorPublicaciones icp) {
		controlPub = icp;
		setTitle("Agregar Tipo de Publicación de Oferta Laboral a Paquete");
		setBounds(100, 100, 1014, 582);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccione un tipo de publicación:");
		lblNewLabel.setBounds(69, 110, 451, 14);
		getContentPane().add(lblNewLabel);
		
		listaTipoPub = new JComboBox<DTTipoPublicacion>();
		listaTipoPub.setBounds(69, 128, 456, 22);
		getContentPane().add(listaTipoPub);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(431, 233, 89, 23);
		getContentPane().add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 dispose();
	            }
		});
		
		JLabel lblNewLabel_1 = new JLabel("Seleccione un paquete:");
		lblNewLabel_1.setBounds(69, 54, 451, 14);
		getContentPane().add(lblNewLabel_1);
		
		listaPaquetes = new JComboBox<DTPaquete>();
		listaPaquetes.setBounds(69, 72, 456, 22);
		getContentPane().add(listaPaquetes);
		
		cantidadField = new JSpinner();
		cantidadField.setBounds(69, 184, 185, 22);
		getContentPane().add(cantidadField);
		
		JLabel lblNewLabel_2 = new JLabel("Ingrese una cantidad:");
		lblNewLabel_2.setBounds(69, 161, 185, 14);
		getContentPane().add(lblNewLabel_2);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(69, 233, 89, 23);
		getContentPane().add(btnAceptar);
		btnAceptar.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 DTPaquete p = (DTPaquete) listaPaquetes.getSelectedItem();
				 DTTipoPublicacion t = (DTTipoPublicacion) listaTipoPub.getSelectedItem();
				 int cantidad = (int) cantidadField.getValue();
				 confirmarAlta(p.getNombre(),cantidad ,t.getNombre());
	         }
		});
	}
	
	private void confirmarAlta(String nombre, int cantidad, String nombreTipoPub) {
		if(esValido()) {
		controlPub.agregarTipoPublicacion(nombre,cantidad ,nombreTipoPub);
		 JOptionPane.showMessageDialog(this, "El paquete se ha creado con éxito", "Crear Paquete de Tipo de Publicacion",
                  JOptionPane.INFORMATION_MESSAGE);
		 limpiarFormulario();
		 setVisible(false);
		}
	}

	public void llenar_listaTipos() {
		listaTipoPub.removeAllItems();
		List<DTTipoPublicacion> datos = new ArrayList<>();
		datos = controlPub.obtenerTipos();
		for (DTTipoPublicacion p : datos) {
			listaTipoPub.addItem(p);
		}
	}
	
	public void llenar_listaPaquetes() {
		listaPaquetes.removeAllItems();
		List<DTPaquete> datos = new ArrayList<>();
		datos = controlPub.listarPaquetes();
		for (DTPaquete p : datos) {
			listaPaquetes.addItem(p);
		}
	}
	
	private Boolean esValido() {
		int cantidad = (int) this.cantidadField.getValue();
		if(cantidad <=0) {
			 JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor que 0", "Registrar Usuario", JOptionPane.ERROR_MESSAGE);
			 return false;
		}else{
			return true;
		}
	}
	
	private void limpiarFormulario() {
		this.cantidadField.setValue(0);
	}
	
	
	
}
