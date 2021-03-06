package ui;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import entidades.Persona;
import negocio.PersonaLogic;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.RowFilter;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;

public class ListadoPersonas extends JInternalFrame {
	
	private JTable table;
	private ArrayList<Persona> lista; 
	PersonaLogic perl = new PersonaLogic();
	private JTextField txtbuscar;
	DefaultTableModel dm;
	private JComboBox comboFiltro;
	private TableRowSorter trsFiltro;
	
	public Persona persona;
	
	public ListadoPersonas() {
		
		setTitle("Listado de personas");
		this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setClosable(true);
		setBounds(100, 100, 821, 481);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblBuscarPorDni = new JLabel("Buscar por:");
		
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
		comboFiltro.setModel(new DefaultComboBoxModel(new String[] {"id", "nombre", "apellido", "dni", "usuario"}));
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnSalirClick();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(583)
							.addComponent(btnSalir))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(20)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 736, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblBuscarPorDni)
									.addGap(31)
									.addComponent(comboFiltro, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
									.addGap(44)
									.addComponent(txtbuscar, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(49, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBuscarPorDni)
						.addComponent(comboFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtbuscar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(btnSalir)
					.addContainerGap(26, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				if (e.getClickCount() == 2){
					showPersona();
					disposeForm();
					
				}
				
			}
		});
		
		
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);
		
		try{
			this.lista = perl.GetAll();
		} catch (Exception e){
			JOptionPane.showMessageDialog(this,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
	
		}
		initDataBindings();
		trsFiltro = new TableRowSorter(table.getModel());
        table.setRowSorter(trsFiltro);


	}
	

	private void disposeForm(){
		
		//this.dispose();
		
	}
	
	public void filtro() {
        int columnaABuscar = 0;
        if (comboFiltro.getSelectedItem() == "id") {
            columnaABuscar = 0;
        }
        if (comboFiltro.getSelectedItem().toString() == "nombre") {
            columnaABuscar = 1;
        }
        if (comboFiltro.getSelectedItem() == "apellido") {
            columnaABuscar = 2;
        }
        if (comboFiltro.getSelectedItem() == "dni") {
            columnaABuscar = 3;
        }
        if (comboFiltro.getSelectedItem() == "usuario") {
            columnaABuscar = 4;
        }
        trsFiltro.setRowFilter(RowFilter.regexFilter(txtbuscar.getText(), columnaABuscar));
    }
	
	
	
	
	/*private void actualizarListado() throws Exception {
		try{
			this.lista = perl.GetAll();
		} catch (Exception e){
			JOptionPane.showMessageDialog(this,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
	
		}
		initDataBindings();
	}*/
	
	protected void initDataBindings() {
		JTableBinding<Persona, List<Persona>, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, lista, table);
		//
		BeanProperty<Persona, Integer> personaBeanProperty = BeanProperty.create("id_persona");
		jTableBinding.addColumnBinding(personaBeanProperty).setColumnName("ID").setEditable(false);
		//
		BeanProperty<Persona, String> personaBeanProperty_1 = BeanProperty.create("nombre");
		jTableBinding.addColumnBinding(personaBeanProperty_1).setColumnName("Nombre").setEditable(false);
		//
		BeanProperty<Persona, String> personaBeanProperty_2 = BeanProperty.create("apellido");
		jTableBinding.addColumnBinding(personaBeanProperty_2).setColumnName("Apellido").setEditable(false);
		//
		BeanProperty<Persona, String> personaBeanProperty_3 = BeanProperty.create("dni");
		jTableBinding.addColumnBinding(personaBeanProperty_3).setColumnName("DNI").setEditable(false);
		//
		BeanProperty<Persona, String> personaBeanProperty_4 = BeanProperty.create("usuario");
		jTableBinding.addColumnBinding(personaBeanProperty_4).setColumnName("Usuario").setEditable(false);
		//
		BeanProperty<Persona, String> personaBeanProperty_5 = BeanProperty.create("categoria.descripcion");
		jTableBinding.addColumnBinding(personaBeanProperty_5).setColumnName("Categoria").setEditable(false);
		//
		jTableBinding.setEditable(false);
		jTableBinding.bind();
	}
	
	private void showPersona() {

			int indexElemento = table.convertRowIndexToModel(table.getSelectedRow());
			persona = this.lista.get(indexElemento);
			//JOptionPane.showMessageDialog(getContentPane(), "Usted a seleccionado a: " + "\n" + persona.getApellido() + " " + persona.getNombre());
			
	}
	
	public void btnSalirClick(){
		this.dispose();
	}
}
