package Presentacion;

import java.awt.Component;
import javax.swing.JInternalFrame;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.JTable;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import excepciones.OfertaNoExisteException;
import logica.IControladorOfertas;
import logica.IControladorUsuario;
import servidor.types.DTEmpresa;
import servidor.types.DTOferta;
import utilsPresentacion.CentrarColumnas;
import servidor.types.EnumEstadoOferta;
import utilsPresentacion.MultiLineCellRenderer;

import javax.swing.JScrollBar;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class consultaOfertaLaboral extends JInternalFrame {
	
	private static final long serialVersionUID = 1L;
	
	//Controlador
	private IControladorUsuario controlUsr;
	@SuppressWarnings("unused")
	private IControladorOfertas controlOL;
	//Componentes Swing
	private JTable tablaOfertaLaboral;
	private informacionOfertaLaboral informacionOfertaLaboralInternalFrame;
	private JComboBox<DTEmpresa> listaEmpresasCombobox;
	
	//datos ofertas
	private String nombreOferta;
	private String Descripcion;
	private String Ciudad;
	private String Departamento;
	private String Horario;
	private String Remuneracion;
	private String Estado;
	private String fechaAlta;
	
	/**
	 * Create the frame.
	 * @throws PropertyVetoException 
	 */
	@SuppressWarnings("serial")
	public consultaOfertaLaboral(IControladorUsuario icu, IControladorOfertas ico) throws PropertyVetoException {
		//Inicializacion internal frame con controlador de usuarios.
		controlUsr = icu;
		controlOL = ico;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
		informacionOfertaLaboralInternalFrame = new informacionOfertaLaboral(ico);
		informacionOfertaLaboralInternalFrame.setLocation(31, 230);
		informacionOfertaLaboralInternalFrame.setResizable(false);
		informacionOfertaLaboralInternalFrame.setBorder(null);
		informacionOfertaLaboralInternalFrame.setVisible(false);
		getContentPane().add(informacionOfertaLaboralInternalFrame);
		
		setResizable(false);
		setMaximum(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Consulta de Oferta Laboral");
		setBounds(100, 100, 1229, 736);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccione una empresa:");
		lblNewLabel.setBounds(21, 22, 736, 14);
		getContentPane().add(lblNewLabel);
		
		listaEmpresasCombobox = new JComboBox<DTEmpresa>();
		listaEmpresasCombobox.setBounds(21, 38, 483, 22);
		getContentPane().add(listaEmpresasCombobox);
		listaEmpresasCombobox.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {  
				 
                DefaultTableModel tableModel = (DefaultTableModel) tablaOfertaLaboral.getModel();
                tableModel.setRowCount(0); // Limpiar filas existentes
				DTEmpresa selectedValue = (DTEmpresa) listaEmpresasCombobox.getSelectedItem();
				if (selectedValue != null) {
					Set<DTOferta> listadoOfertas = selectedValue.getOfertas();			
					if(!listadoOfertas.isEmpty()) {
						for (DTOferta item : listadoOfertas) {
							try {
								DTOferta datosOferta = controlOL.obtenerDatosOferta(item.getNombre());
								switch(datosOferta.getEstado()) {
								case CONFIRMADA:
									Estado = "Confirmada";
									break;
								case INGRESADA :
									Estado = "Ingresada";
									break;
								case RECHAZADA :
									Estado = "Rechazada";
									break;
								case FINALIZADA :
									Estado = "Finalizada";
									break;
								}			

							nombreOferta = datosOferta.getNombre();
							Descripcion = datosOferta.getDescripcion();
							Ciudad = datosOferta.getCiudad();
							Departamento = datosOferta.getDepartamento();
							Horario = datosOferta.getHorario();
							Remuneracion = datosOferta.getRemuneracion();
							fechaAlta = datosOferta.getFechaAlta().format(formatter);
							tableModel.addRow(new Object[] {nombreOferta, Descripcion, Ciudad, Departamento,
									Horario,Remuneracion,Estado,fechaAlta});
						} 
						catch (OfertaNoExisteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				}		
			}
			}
		});
		
		JSeparator separator = new JSeparator();
		separator.setBounds(21, 71, 1086, 2);
		getContentPane().add(separator);
		
		JLabel lblNewLabel_1 = new JLabel("Ofertas Laborales Asociadas:");
		lblNewLabel_1.setBounds(21, 84, 450, 14);
		getContentPane().add(lblNewLabel_1);
		
		ScrollPane panelHeaders = new ScrollPane();
		panelHeaders.setBounds(21, 111, 1151, 22);
		getContentPane().add(panelHeaders);
		
		ScrollPane tablePane = new ScrollPane();
		tablePane.setBounds(21, 139, 1151, 529);
		getContentPane().add(tablePane);
		
		JScrollBar scrollBarTabla = new JScrollBar();
		scrollBarTabla.setBounds(963, 139, 17, 508);
		tablePane.add(scrollBarTabla);
	
		tablaOfertaLaboral = new JTable();
		tablaOfertaLaboral.setRowHeight(35);
		tablaOfertaLaboral.setModel(new DefaultTableModel(
			new Object[][] {
				
			},
			new String[] {
				"Nombre", "Descripci\u00F3n", "Ciudad", "Departamento", "Horario", "Remuneraci\u00F3n","Estado", "Fecha de Alta", "Acciones"
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class,String.class, String.class, String.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tablePane.add(tablaOfertaLaboral);
		tablaOfertaLaboral.getColumnModel().getColumn(0).setPreferredWidth(72);
		tablaOfertaLaboral.getColumnModel().getColumn(3).setPreferredWidth(100);
		tablaOfertaLaboral.getColumnModel().getColumn(5).setPreferredWidth(120);
		tablaOfertaLaboral.getColumnModel().getColumn(6).setPreferredWidth(105);
		tablaOfertaLaboral.setDefaultRenderer(Object.class, new CentrarColumnas());
		tablaOfertaLaboral.setRowHeight(140);
		tablaOfertaLaboral.getColumnModel().getColumn(0).setCellRenderer(new MultiLineCellRenderer());
		tablaOfertaLaboral.getColumnModel().getColumn(1).setCellRenderer(new MultiLineCellRenderer());
		tablaOfertaLaboral.getColumnModel().getColumn(2).setCellRenderer(new MultiLineCellRenderer());
		tablaOfertaLaboral.getColumnModel().getColumn(3).setCellRenderer(new MultiLineCellRenderer());
		tablaOfertaLaboral.getColumnModel().getColumn(4).setCellRenderer(new MultiLineCellRenderer());
		tablaOfertaLaboral.getColumnModel().getColumn(5).setCellRenderer(new MultiLineCellRenderer());
		tablaOfertaLaboral.getColumnModel().getColumn(6).setCellRenderer(new MultiLineCellRenderer());
		tablaOfertaLaboral.getColumnModel().getColumn(7).setCellRenderer(new MultiLineCellRenderer());
		tablaOfertaLaboral.getColumnModel().getColumn(8).setCellRenderer(new ButtonRenderer());
		tablaOfertaLaboral.getColumnModel().getColumn(8).setCellEditor(new ButtonEditor(new JCheckBox(), informacionOfertaLaboralInternalFrame));
		JTableHeader headerOfertas = tablaOfertaLaboral.getTableHeader();
		panelHeaders.add(headerOfertas);
		tablaOfertaLaboral.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tablaOfertaLaboral.setBounds(21, 112, 908, 510);
		
		JButton buttonCancelar = new JButton("Cancelar");
		buttonCancelar.setBounds(526, 38, 89, 23);
		getContentPane().add(buttonCancelar);
		
		buttonCancelar.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 DefaultTableModel tableModel = (DefaultTableModel) tablaOfertaLaboral.getModel();
				 tableModel.setRowCount(0); // Limpiar filas existentes
				 dispose();
	            }
		});
			
	}
	public void llenar_comboListaEmpresa(){
		listaEmpresasCombobox.removeAllItems();
		List<DTEmpresa> datos = new ArrayList<>();
		datos = controlUsr.listarEmpresas();
		for (DTEmpresa u : datos) {
			listaEmpresasCombobox.addItem(u);
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
	    @SuppressWarnings("unused")
		private boolean isPushed;
	    private MyActionListener myListener;
	    
	    // Nueva clase interna MyActionListener
	    private class MyActionListener implements ActionListener {
	        private int currentRow;
	        @Override
	        public void actionPerformed(ActionEvent e) {	        	
	            String nombreOferta = tablaOfertaLaboral.getValueAt(currentRow, 0).toString();
	            ((informacionOfertaLaboral) informacionOfertaLaboralInternalFrame).recibirNombreOferta(nombreOferta);
	            try {
					((informacionOfertaLaboral) informacionOfertaLaboralInternalFrame).mostrarDatosOferta();
				} catch (OfertaNoExisteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            informacionOfertaLaboralInternalFrame.setVisible(true);
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

