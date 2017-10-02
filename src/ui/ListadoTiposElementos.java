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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import entidades.Tipo_Elemento;
import negocio.Tipo_ElementosLogic;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListadoTiposElementos extends JInternalFrame {
	private JTable table;
	private JTextField txtBuscar;
	DefaultTableModel dm;
	private TableRowSorter trsFiltro;
	private ArrayList<Tipo_Elemento> lista; 
	Tipo_ElementosLogic tipoElementoLogic = new Tipo_ElementosLogic();
	private JComboBox comboFiltro;


	public ListadoTiposElementos() {
		setTitle("Listado Tipos de Elementos");
		setClosable(true);
		setBounds(100, 100, 729, 483);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblBuscarPor = new JLabel("Buscar por:");
		
		comboFiltro = new JComboBox();
		comboFiltro.setModel(new DefaultComboBoxModel(new String[] {"id", "nombre", "cantMaxReservasPend"}));
		
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
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblBuscarPor)
							.addGap(38)
							.addComponent(comboFiltro, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
							.addGap(76)
							.addComponent(txtBuscar, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(btnSalir)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 650, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(34, Short.MAX_VALUE))
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
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnSalir)
					.addContainerGap(20, Short.MAX_VALUE))
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);
		
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
        if (comboFiltro.getSelectedItem() == "nombre") {
            columnaABuscar = 1;
        }
        if (comboFiltro.getSelectedItem().toString() == "cantMaxReservasPend") {
            columnaABuscar = 2;
        }
       
        trsFiltro.setRowFilter(RowFilter.regexFilter(txtBuscar.getText(), columnaABuscar));
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
	
	
	public void btnSalirClick(){
	this.dispose();
	}
}
