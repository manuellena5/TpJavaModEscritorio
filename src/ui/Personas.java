package ui;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.TableRowSorter;
import javax.swing.JTable;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.ObjectProperty;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import entidades.Persona;
import negocio.PersonaLogic;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Personas extends JInternalFrame {
	private JTextField txtbuscar;
	private JTable table;
	private ArrayList<Persona> lista; 
	PersonaLogic perl = new PersonaLogic();
	private String modo;
	private JComboBox comboFiltro;
	private TableRowSorter trsFiltro;

	
	public Personas() {
		setClosable(true);
		setTitle("Personas");
		setBounds(100, 100, 729, 483);
		
		JLabel lblBuscarPor = new JLabel("Buscar por:");
		
		comboFiltro = new JComboBox();
		
		
		comboFiltro.setModel(new DefaultComboBoxModel(new String[] {"id","nombre","apellido", "dni", "usuario"}));
		
		txtbuscar = new JTextField();
		txtbuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String cadena = (txtbuscar.getText());
                txtbuscar.setText(cadena);
                repaint();
                filtro();
			}
		});
		txtbuscar.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSalirClick();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 662, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblBuscarPor)
							.addGap(38)
							.addComponent(comboFiltro, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(txtbuscar, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(27, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(593, Short.MAX_VALUE)
					.addComponent(btnSalir, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
					.addGap(48))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBuscarPor)
						.addComponent(comboFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtbuscar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 314, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnSalir)
					.addContainerGap(18, Short.MAX_VALUE))
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JButton btnAlta = new JButton("Alta");
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modo = "Alta";
				ShowAbmPersonas(modo);
			}

			
		});
		menuBar.add(btnAlta);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modo = "Editar";
				if (table.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(btnEditar, "Debe seleccionar una persona");}
				else{
				ShowAbmPersonas(modo);
				}
			}
		});
		menuBar.add(btnEditar);
		
		JButton btnBaja = new JButton("Baja");
		btnBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modo = "Eliminar";
				if (table.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(btnBaja, "Debe seleccionar una persona");}
				else{
				ShowAbmPersonas(modo);
				}
			}
		});
		menuBar.add(btnBaja);
		
		try{
			this.lista = perl.GetAll();
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
        if (comboFiltro.getSelectedItem().toString() == "apellido") {
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
	
	private void ShowAbmPersonas(String modo2) {
		AbmPersonas abm = new AbmPersonas();
		if (modo2 != "Alta") {
			
			
			int index = table.convertRowIndexToModel(table.getSelectedRow());
			abm.showAbmPersonas(this.lista.get(index), modo2);
			
		}
		this.getDesktopPane().add(abm);
		abm.setVisible(true);
		this.dispose();
		
	}
	
	private void btnSalirClick() {
		dispose();
		
	}
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
}
