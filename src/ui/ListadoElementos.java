package ui;

import java.awt.EventQueue;
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
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import entidades.Elemento;
import negocio.ElementosLogic;

public class ListadoElementos extends JInternalFrame {
	private JTable table;
	private JTextField txtBuscar;
	DefaultTableModel dm;
	private JComboBox comboFiltro;
	private TableRowSorter trsFiltro;
	private ArrayList<Elemento> lista; 
	ElementosLogic elementoLogic = new ElementosLogic();
	
	public ListadoElementos() {
		setTitle("Listado Elementos");
		setClosable(true);
		setBounds(100, 100, 729, 483);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblBuscarPor = new JLabel("Buscar por:");
		
		comboFiltro = new JComboBox();
		comboFiltro.setModel(new DefaultComboBoxModel(new String[] {"id", "nombre", "descripcion", "autor", "genero"}));
		
		txtBuscar = new JTextField();
		
		txtBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				String cadena = (txtBuscar.getText());
                txtBuscar.setText(cadena);
                repaint();
                filtro();
			}
		});
		
		txtBuscar.setColumns(10);
		
		JButton btnSalir = new JButton("Salir");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnSalir)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblBuscarPor)
								.addGap(26)
								.addComponent(comboFiltro, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
								.addGap(47)
								.addComponent(txtBuscar, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 663, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBuscarPor)
						.addComponent(comboFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtBuscar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnSalir)
					.addContainerGap(15, Short.MAX_VALUE))
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);
		
		try{
			this.lista = elementoLogic.GetAll();
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
        if (comboFiltro.getSelectedItem().toString() == "descripcion") {
            columnaABuscar = 3;
        }
        if (comboFiltro.getSelectedItem() == "autor") {
            columnaABuscar = 4;
        }
        if (comboFiltro.getSelectedItem() == "genero") {
            columnaABuscar = 5;
        }
        trsFiltro.setRowFilter(RowFilter.regexFilter(txtBuscar.getText(), columnaABuscar));
    }
	
	protected void initDataBindings() {
		JTableBinding<Elemento, List<Elemento>, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, lista, table);
		//
		BeanProperty<Elemento, String> personaBeanProperty = BeanProperty.create("id_elemento");
		jTableBinding.addColumnBinding(personaBeanProperty).setColumnName("ID").setEditable(false);
		//
		BeanProperty<Elemento, String> personaBeanProperty_1 = BeanProperty.create("nombre");
		jTableBinding.addColumnBinding(personaBeanProperty_1).setColumnName("Nombre").setEditable(false);
		//
		BeanProperty<Elemento, String> personaBeanProperty_2 = BeanProperty.create("stock");
		jTableBinding.addColumnBinding(personaBeanProperty_2).setColumnName("Stock").setEditable(false);
		//
		BeanProperty<Elemento, String> personaBeanProperty_3 = BeanProperty.create("descripcion");
		jTableBinding.addColumnBinding(personaBeanProperty_3).setColumnName("Descripcion").setEditable(false);
		//
		BeanProperty<Elemento, String> personaBeanProperty_4 = BeanProperty.create("autor");
		jTableBinding.addColumnBinding(personaBeanProperty_4).setColumnName("Autor").setEditable(false);
		//
		BeanProperty<Elemento, String> personaBeanProperty_5 = BeanProperty.create("genero");
		jTableBinding.addColumnBinding(personaBeanProperty_5).setColumnName("Genero").setEditable(false);
		//
		BeanProperty<Elemento, String> personaBeanProperty_6 = BeanProperty.create("id_tipoelemento");
		jTableBinding.addColumnBinding(personaBeanProperty_6).setColumnName("ID Tipo Elemento").setEditable(false);
		//
		jTableBinding.setEditable(false);
		jTableBinding.bind();
	}
}
