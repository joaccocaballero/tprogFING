package Presentacion;

import java.awt.Button;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.beans.PropertyVetoException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import excepciones.CorreoRepetidoException;
import excepciones.UsuarioRepetidoException;
import logica.IControladorUsuario;

import javax.swing.JTextArea;

public class altaEmpresa extends JInternalFrame {
	
	private static final long serialVersionUID = 1L;

	// Controlador de usuarios que se utilizará para las acciones del JFrame
    private IControladorUsuario controlUsr;
	
	private JTextField nicknameField;
	private JTextField apellidoField;
	private JTextField nombreField;
	private JTextField correoField;
	private JPasswordField contraseñaField;
	private JPasswordField confContraseñaField;
	private JTextField linkWebField;
	private JTextArea  descripcionField;
	private JTextField nombreEmpresaField;

	/**
	 * Create the frame.
	 * @throws PropertyVetoException 
	 */
	public altaEmpresa(IControladorUsuario icu) throws PropertyVetoException {
		//Inicializacion internal frame con controlador de usuarios.
		controlUsr = icu;
		
		
		setResizable(false);
		setMaximum(true);
		setClosable(true);
		setTitle("Alta de Empresa");
		setBounds(100, 100, 631, 595);
		getContentPane().setLayout(null);
		
		nicknameField = new JTextField();
		nicknameField.setBounds(185, 25, 191, 20);
		getContentPane().add(nicknameField);
		nicknameField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nickname:");
		lblNewLabel.setBounds(43, 25, 74, 14);
		getContentPane().add(lblNewLabel);
		
		apellidoField = new JTextField();
		apellidoField.setBounds(185, 78, 191, 20);
		getContentPane().add(apellidoField);
		apellidoField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(43, 50, 74, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Apellido:");
		lblNewLabel_2.setBounds(43, 78, 74, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Correo:");
		lblNewLabel_3.setBounds(43, 112, 74, 14);
		getContentPane().add(lblNewLabel_3);
		
		nombreField = new JTextField();
		nombreField.setColumns(10);
		nombreField.setBounds(185, 50, 191, 20);
		getContentPane().add(nombreField);
		
		correoField = new JTextField();
		correoField.setBounds(185, 109, 191, 20);
		getContentPane().add(correoField);
		correoField.setColumns(10);
		
		JLabel lblNewLabel_3_1 = new JLabel("Contraseña:");
		lblNewLabel_3_1.setBounds(43, 137, 136, 14);
		getContentPane().add(lblNewLabel_3_1);
		
		contraseñaField = new JPasswordField();
		contraseñaField.setColumns(10);
		contraseñaField.setBounds(240, 134, 136, 20);
		getContentPane().add(contraseñaField);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Confirmación de Contraseña");
		lblNewLabel_3_1_1.setBounds(43, 162, 191, 14);
		getContentPane().add(lblNewLabel_3_1_1);
				
		confContraseñaField = new JPasswordField();
		confContraseñaField.setColumns(10);
		confContraseñaField.setBounds(240, 159, 136, 20);
		getContentPane().add(confContraseñaField);

		
		Button buttonAceptar = new Button("Aceptar");
		buttonAceptar.setBounds(87, 378, 70, 22);
		getContentPane().add(buttonAceptar);
		
		buttonAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                registrarEmpresa(arg0);
            }
        });

		Button buttonCancelar = new Button("Cancelar");
		buttonCancelar.setBounds(255, 378, 70, 22);
		getContentPane().add(buttonCancelar);
		
		
		buttonCancelar.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 dispose();
	            }
		});
		
		JLabel labelDescripcion = new JLabel("Descripcion General:");
		labelDescripcion.setBounds(43, 212, 126, 14);
		getContentPane().add(labelDescripcion);
		
		JLabel linkField = new JLabel("Link Web (Opcional):");
		linkField.setBounds(43, 320, 126, 14);
		getContentPane().add(linkField);
		
		linkWebField = new JTextField();
		linkWebField.setBounds(185, 317, 191, 20);
		getContentPane().add(linkWebField);
		linkWebField.setColumns(10);
		
		nombreEmpresaField = new JTextField();
		nombreEmpresaField.setColumns(10);
		nombreEmpresaField.setBounds(185, 184, 191, 20);
		getContentPane().add(nombreEmpresaField);
		
		JLabel lblNewLabel_4 = new JLabel("Nombre de Empresa:");
		lblNewLabel_4.setBounds(43, 187, 136, 14);
		getContentPane().add(lblNewLabel_4);
		
		descripcionField = new JTextArea();
		descripcionField.setBounds(215, 215, 161, 91);
		getContentPane().add(descripcionField);
		descripcionField.setLineWrap(true);
		descripcionField.setWrapStyleWord(true);
		

		
		
	}
	
	public void registrarEmpresa(ActionEvent arg0) {    
	    if (esValido()) {
	        String nick = this.nicknameField.getText();
	        String nombre = this.nombreField.getText();
	        String apellido = this.apellidoField.getText();
	        String email = this.correoField.getText();
	        String nomEmpresa = this.nombreEmpresaField.getText();
	        String contraseña = new String(this.contraseñaField.getPassword());
	        String desc = this.descripcionField.getText();
	        String link = this.linkWebField.getText();
	        String imagenDefault = "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png";
	        
	        try {
	            if (isValidEmail(email)) {
	                controlUsr.altaEmpresa(nick, nombre, apellido, email, contraseña, nomEmpresa, desc, link, imagenDefault);
	                JOptionPane.showMessageDialog(this, "El Usuario se ha creado con éxito", "Registrar Usuario",
	                        JOptionPane.INFORMATION_MESSAGE);
	                limpiarFormulario();
	                setVisible(false);
	            } else {
	                JOptionPane.showMessageDialog(this, "El email ingresado no es válido.", "Registrar Usuario", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	        catch(UsuarioRepetidoException | CorreoRepetidoException err){
	            JOptionPane.showMessageDialog(this, err.getMessage(), "Registrar Usuario", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}

	public boolean isValidEmail(String email) {
	    String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(email);
	    return matcher.matches();
	}
	
	public Boolean esValido() {
	    String nick = this.nicknameField.getText();
	    String nombre = this.nombreField.getText();
	    String apellido = this.apellidoField.getText();
	    String email = this.correoField.getText();
	    String contraseña = new String(this.contraseñaField.getPassword());
	    String confContraseña = new String(this.confContraseñaField.getPassword());
	    String nomEmpresa = this.nombreEmpresaField.getText();
	    String desc = this.descripcionField.getText();
	    @SuppressWarnings("unused")
	    String link = this.linkWebField.getText();

	    if (nick.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || contraseña.isEmpty() ||email.isEmpty() || nomEmpresa.isEmpty() || desc.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Registrar Usuario", JOptionPane.ERROR_MESSAGE);
	        return false;
	    } else if (nick.length() > 50 || nombre.length() > 50 || apellido.length() > 50 || email.length() > 50 || nomEmpresa.length() > 50 || desc.length() > 400) {
	        JOptionPane.showMessageDialog(this, "Uno o más campos exceden la longitud máxima permitida", "Registrar Usuario", JOptionPane.ERROR_MESSAGE);
	        return false;
	    } else if(!contraseña.equals(confContraseña)) {
	    	JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden.", "Registrar Usuario", JOptionPane.ERROR_MESSAGE);
	    	return false;
	    } else if(contraseña.length() < 6) {
	    	JOptionPane.showMessageDialog(this, "La contraseña debe tener al menos 6 caracteres.", "Registrar Usuario",
	                JOptionPane.ERROR_MESSAGE);
	        return false;
	    }else {
	        return true;
	    }
	}
	
	private void limpiarFormulario() {
		nicknameField.setText("");
		nombreField.setText("");
		apellidoField.setText("");
		correoField.setText("");
		contraseñaField.setText("");
		confContraseñaField.setText("");
		nombreEmpresaField.setText("");
		descripcionField.setText("");
		linkWebField.setText("");
	   }
}


