package ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import entidades.Tipo_Elemento;
import negocio.Tipo_ElementosLogic;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.RowFilter;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

public class Tipo_Elementos extends JInternalFrame {
	private JTable table;
	private JTextField txtbuscar;
	DefaultTableModel dm;
	private TableRowSorter trsFiltro;

	private ArrayList<Tipo_Elemento> lista; 
	Tipo_ElementosLogic tipoElementoLogic = new Tipo_ElementosLogic();
	private JComboBox comboFiltro;
	private JButton btnAlta;
	private JButton btnEditar;
	private JButton btnBaja;
	
	public Tipo_Elementos() {
		
		setTitle("Tipos de Elementos");
		setClosable(true);
		setBounds(100, 100, 729, 483);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblBuscarPor = new JLabel("Buscar por:");
		
		txtbuscar = new JTextField();
		
		txtbuscar.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				String cadena = (txtbuscar.getText());
                txtbuscar.setText(cadena);
                repaint();
                filtro();
			}
		});
		
		txtbuscar.setColumns(10);
		
		comboFiltro = new JComboBox();
		comboFiltro.setModel(new DefaultComboBoxModel(new String[] {"id", "nombre", "cantidad reservas pend"}));
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnSalirClick();	
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(127)
					.addComponent(comboFiltro, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
					.addGap(34)
					.addComponent(txtbuscar, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(258, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(27, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 664, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBuscarPor))
					.addGap(22))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(598, Short.MAX_VALUE)
					.addComponent(btnSalir)
					.addGap(62))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(23, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtbuscar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBuscarPor))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 312, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSalir)
					.addGap(30))
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		btnAlta = new JButton("Alta");
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String modo = "Alta";
			
					ShowAbmTipoElementos(modo);
				
				
			}
		});
		menuBar.add(btnAlta);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String modo = "Editar";
				if (table.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(btnEditar, "Debe seleccionar un tipo de elemento");}
				else{
					ShowAbmTipoElementos(modo);
				}
				
			}
		});
		menuBar.add(btnEditar);
		
		btnBaja = new JButton("Baja");
		btnBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String modo = "Eliminar";
				if (table.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(btnEditar, "Debe seleccionar un tipo de elemento");}
				else{
					ShowAbmTipoElementos(modo);
				}
			}
		});
		menuBar.add(btnBaja);
		
		try{
			this.lista = tipoElementoLogic.GetAll();
		} catch (Exception e){
			JOptionPane.showMessageDialog(this,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
	
		}
		initDataBindings();
		trsFiltro = new TableRowSorter(table.getModel());
        table.setRowSorter(trsFiltro);

	}
	
	public void filtro() {
        int columnaABuscar = 0;
        if (comboFiltro.getSelectedItem() == "id") {
            columnaABuscar = 0;
        }
        if (comboFiltro.getSelectedItem().toString() == "nombre") {
            columnaABuscar = 1;
        }
        if (comboFiltro.getSelectedItem() == "cantidad reservas pend") {
            columnaABuscar = 2;
        }
       
        trsFiltro.setRowFilter(RowFilter.regexFilter(txtbuscar.getText(), columnaABuscar));
    }
	
	protected void initDataBindings() {
		JTableBinding<Tipo_Elemento, List<Tipo_Elemento>, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, lista, table);
		//
		BeanProperty<Tipo_Elemento, String> personaBeanProperty = BeanProperty.create("id_tipoelemento");
		jTableBinding.addColumnBinding(personaBeanProperty).setColumnName("ID").setEditable(false);
		//
		BeanProperty<Tipo_Elemento, String> personaBeanProperty_1 = BeanProperty.create("nombre");
		jTableBinding.addColumnBinding(personaBeanProperty_1).setColumnName("Nombre").setEditable(false);
		//
		BeanProperty<Tipo_Elemento, String> personaBeanProperty_2 = BeanProperty.create("cantMaxReservasPend");
		jTableBinding.addColumnBinding(personaBeanProperty_2).setColumnName("Cantidad Max Reservas Pendientes").setEditable(false);
		//
		jTableBinding.setEditable(false);
		jTableBinding.bind();
	}
	
	private void ShowAbmTipoElementos(String modo) {
		
		AbmTipoElementos abm = new AbmTipoElementos();
		if (modo != "Alta") {
			
			int index = table.convertRowIndexToModel(table.getSelectedRow());
			abm.showAbmTipoElementos(this.lista.get(index),modo);
						
			
		}
		this.getDesktopPane().add(abm);
		abm.setVisible(true);
		this.dispose();
		
		
		
	}
	
	private void actualizarListado() throws Exception {
		try{
			this.lista = tipoElementoLogic.GetAll();
		} catch (Exception e){
			JOptionPane.showMessageDialog(this,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
	
		}
		initDataBindings();
	}
	
	
	private void btnSalirClick() {
		dispose();
		
	}
}
