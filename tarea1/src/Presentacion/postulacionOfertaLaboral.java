package Presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.beans.PropertyVetoException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import excepciones.NicknameNoExisteException;
import excepciones.OfertaNoExisteException;
import excepciones.UsuarioNoEsEmpresaException;
import logica.IControladorOfertas;
import logica.IControladorUsuario;
import servidor.types.DTEmpresa;
import servidor.types.DTOferta;
import utilsPresentacion.CentrarColumnas;

import java.awt.GridLayout;
import java.awt.Button;
import java.awt.Component;

public class postulacionOfertaLaboral extends JInternalFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JTable tableOfertas;
	private postularAPostulante postularPostulanteInternalFrame;
	private IControladorOfertas controlOL;
	private IControladorUsuario controlU;
	private JComboBox<String> comboBoxEmpresa;
	
	//datos ofertas Vigentes
		private String nombreOferta;
		private String Descripcion;
		private String Ciudad;
		private String Departamento;
		private String Horario;
		private String Remuneracion;
		private String fechaAlta;
		

	/**
	 * Create the frame.
	 * @throws PropertyVetoException 
	 */

	@SuppressWarnings("serial")
	public postulacionOfertaLaboral(IControladorOfertas iol, IControladorUsuario ICU) throws PropertyVetoException {
		controlOL = iol;
		controlU = ICU;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
		postularPostulanteInternalFrame = new postularAPostulante(controlOL, controlU);
		postularPostulanteInternalFrame.setResizable(false);
		postularPostulanteInternalFrame.setBorder(null);
		postularPostulanteInternalFrame.setVisible(false);
		getContentPane().add(postularPostulanteInternalFrame);
		setMaximum(true);
		setMaximizable(true);
		setResizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Postulacion a Oferta Laboral");
		setBounds(100, 100, 1074, 633);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccione una empresa:");
		lblNewLabel.setBounds(10, 11, 819, 14);
		getContentPane().add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 53, 1028, 2);
		getContentPane().add(separator);
		
		comboBoxEmpresa = new JComboBox<String>();
		comboBoxEmpresa.setBounds(10, 25, 438, 22);
		getContentPane().add(comboBoxEmpresa);
		comboBoxEmpresa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tableModel = (DefaultTableModel) tableOfertas.getModel();
				tableModel.setRowCount(0); // Limpiar filas existentes
				String selectedValue =  (String) comboBoxEmpresa.getSelectedItem();
				if (selectedValue != null) {
					Set<DTOferta> listadoOfertasVigentes = null;
					try {
						listadoOfertasVigentes = controlOL.obtenerOfertasVigentesDeEmpresa(selectedValue);
					} catch (NicknameNoExisteException | UsuarioNoEsEmpresaException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(!listadoOfertasVigentes.isEmpty()) {
						for (DTOferta item : listadoOfertasVigentes) {
							nombreOferta = item.getNombre();
							Descripcion = item.getDescripcion();
							Ciudad = item.getCiudad();
							Departamento = item.getDepartamento();
							Horario = item.getHorario();
							Remuneracion = item.getRemuneracion();
							fechaAlta = item.getFechaAlta().format(formatter);
							tableModel.addRow(new Object[] {nombreOferta, Descripcion, Ciudad, Departamento,
									Horario,Remuneracion,fechaAlta});							
						} 
					}
				}			
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("Ofertas Laborales Vigentes:");
		lblNewLabel_1.setBounds(10, 66, 600, 14);
		getContentPane().add(lblNewLabel_1);
		
		JPanel panelHeadersOfertas = new JPanel();
		panelHeadersOfertas.setBounds(10, 91, 1017, 22);
		getContentPane().add(panelHeadersOfertas);
		
		ScrollPane scrollPaneOfertas = new ScrollPane();
		scrollPaneOfertas.setBounds(10, 121, 1017, 405);
		getContentPane().add(scrollPaneOfertas);
		
		Scrollbar scrollbar = new Scrollbar();
		scrollbar.setBounds(722, 119, 9, 407);
		scrollPaneOfertas.add(scrollbar);
		
		tableOfertas = new JTable();
		tableOfertas.setRowHeight(35);
		tableOfertas.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre", "Descripci\u00F3n", "Ciudad", "Departamento", "Horario", "Remuneraci\u00F3n", "Fecha Alta", "Acciones"
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, String.class, Object.class, String.class, String.class, String.class, Object.class, String.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableOfertas.getColumnModel().getColumn(0).setResizable(false);
		tableOfertas.getColumnModel().getColumn(0).setPreferredWidth(110);
		tableOfertas.getColumnModel().getColumn(1).setResizable(false);
		tableOfertas.getColumnModel().getColumn(1).setPreferredWidth(190);
		tableOfertas.getColumnModel().getColumn(2).setResizable(false);
		tableOfertas.getColumnModel().getColumn(2).setPreferredWidth(90);
		tableOfertas.getColumnModel().getColumn(3).setResizable(false);
		tableOfertas.getColumnModel().getColumn(3).setPreferredWidth(114);
		tableOfertas.getColumnModel().getColumn(4).setResizable(false);
		tableOfertas.getColumnModel().getColumn(4).setPreferredWidth(99);
		tableOfertas.getColumnModel().getColumn(5).setResizable(false);
		tableOfertas.getColumnModel().getColumn(5).setPreferredWidth(118);
		tableOfertas.getColumnModel().getColumn(6).setResizable(false);
		tableOfertas.getColumnModel().getColumn(7).setResizable(false);
		tableOfertas.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer());
		tableOfertas.getColumnModel().getColumn(7).setCellEditor(new ButtonEditor(new JCheckBox(), postularPostulanteInternalFrame));
		tableOfertas.setDefaultRenderer(Object.class, new CentrarColumnas());
		tableOfertas.setBounds(325, 246, 1, 1);
		JTableHeader headerOfertas = tableOfertas.getTableHeader();
		panelHeadersOfertas.add(headerOfertas);
		panelHeadersOfertas.setLayout(new GridLayout(1, 0, 0, 0));
		scrollPaneOfertas.add(tableOfertas);
		Button buttonCancelar = new Button("Cancelar");
		buttonCancelar.setBounds(472, 25, 70, 22);
		getContentPane().add(buttonCancelar);
		buttonCancelar.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 dispose();
	            }
		});

	}
	
	public void comboMostrarEmpresas() {
		JComboBox<String> combobox = this.comboBoxEmpresa;
		combobox.removeAllItems();
		if(controlOL != null) {
		List<DTEmpresa> empresas = controlOL.obtenerEmpresas();
		if (!empresas.isEmpty()) {
			for (DTEmpresa empresa : empresas) {
		        comboBoxEmpresa.addItem(empresa.getNombreEmpresa()); 
		    }
		}
		}
	}
	
	
	class ButtonRenderer extends JButton implements TableCellRenderer {
		private static final long serialVersionUID = 1L;

		public ButtonRenderer() {
	        setOpaque(true);
	    }

	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	        if (isSelected) {
	            setForeground(table.getSelectionForeground());
	            setBackground(table.getSelectionBackground());
	        } else {
	            setForeground(table.getForeground());
	            setBackground(UIManager.getColor("Button.background"));
	        }
	        setText((value == null) ? "" : value.toString());
	        return this;
	    }
	}

	class ButtonEditor extends DefaultCellEditor {
		
		private static final long serialVersionUID = 1L;
		
		protected JButton button;
	    private String label;
	    private boolean isPushed;
	    private MyActionListener myListener;
	    
	    // Nueva clase interna MyActionListener
	    private class MyActionListener implements ActionListener {
	        private int currentRow;

	        @Override
	        public void actionPerformed(ActionEvent e) {
	            String nombreOferta = tableOfertas.getValueAt(currentRow, 0).toString();
	            ((postularAPostulante) postularPostulanteInternalFrame).recibirNombreOferta(nombreOferta);
	            try {
					((postularAPostulante) postularPostulanteInternalFrame).mostrarDatosOferta();
				} catch (OfertaNoExisteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            ((postularAPostulante) postularPostulanteInternalFrame).comboMostrarPostulatntes();
	            
	            postularPostulanteInternalFrame.setVisible(true);
	            fireEditingStopped();
	        }

	        public void setCurrentRow(int row) {
	            this.currentRow = row;
	        }
	    }

	    public ButtonEditor(JCheckBox checkBox, JInternalFrame postularPostulanteInternalFrame) {
	        super(checkBox);
	        button = new JButton();
	        button.setOpaque(true);
	        
	        myListener = new MyActionListener(); // Inicializamos MyActionListener
	        button.addActionListener(myListener); // Agregamos MyActionListener al botón
	    }

	    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
	        if (isSelected) {
	            button.setForeground(table.getSelectionForeground());
	            button.setBackground(table.getSelectionBackground());
	        } else {
	            button.setForeground(table.getForeground());
	            button.setBackground(table.getBackground());
	        }
	        label = (value == null) ? "" : value.toString();
	        button.setText(label);
	        isPushed = true;
	        
	        // Aquí establecemos la fila actual en MyActionListener
	        myListener.setCurrentRow(row);

	        return button;
	    }

	    public Object getCellEditorValue() {
	        if (isPushed) {
	            // Cuando el botón es presionado
	        }
	        isPushed = false;
	        return label;
	    }

	    public boolean stopCellEditing() {
	        isPushed = false;
	        return super.stopCellEditing();
	    }

	    protected void fireEditingStopped() {
	        super.fireEditingStopped();
	    }
	}

	}
		

		

	
