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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.TableRowSorter;

import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import entidades.Elemento;
import entidades.Reserva;
import negocio.ReservasLogic;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

public class Reservas extends JInternalFrame {
	private JTable table;
	private JTextField txtBuscar;
	private JComboBox comboFiltro;
	private TableRowSorter trsFiltro;
	private ArrayList<Reserva> lista; 
	ReservasLogic reservaLogic = new ReservasLogic();
	
	public Reservas() {
		
		setTitle("Reservas");
		setClosable(true);
		setBounds(100, 100, 729, 483);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblBuscarPor = new JLabel("Buscar por:");
		
		comboFiltro = new JComboBox();
		comboFiltro.setModel(new DefaultComboBoxModel(new String[] {"id_persona", "id_elemento", "fecha_registro", "fecha_inicio", "fecha_fin", "detalle", "estado"}));
		
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
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblBuscarPor)
							.addGap(49)
							.addComponent(comboFiltro, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
							.addGap(55)
							.addComponent(txtBuscar, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(btnSalir)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 658, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(29, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBuscarPor)
						.addComponent(comboFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtBuscar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSalir)
					.addContainerGap(25, Short.MAX_VALUE))
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JButton btnAlta = new JButton("Alta");
		menuBar.add(btnAlta);
		
		JButton btnEditar = new JButton("Editar");
		menuBar.add(btnEditar);
		
		JButton btnBaja = new JButton("Baja");
		menuBar.add(btnBaja);
		
		try{
			this.lista = reservaLogic.GetAll();
		} catch (Exception e){
			JOptionPane.showMessageDialog(this,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
	
		}
		initDataBindings();
		trsFiltro = new TableRowSorter(table.getModel());
        table.setRowSorter(trsFiltro);

	}
	
	public void filtro() {
        int columnaABuscar = 0;
        if (comboFiltro.getSelectedItem() == "id_persona") {
            columnaABuscar = 0;
        }
        if (comboFiltro.getSelectedItem().toString() == "id_elemento") {
            columnaABuscar = 1;
        }
        if (comboFiltro.getSelectedItem().toString() == "fecha_registro") {
            columnaABuscar = 2;
        }
        if (comboFiltro.getSelectedItem() == "fecha_inicio") {
            columnaABuscar = 3;
        }
        if (comboFiltro.getSelectedItem() == "fecha_fin") {
            columnaABuscar = 4;
        }
        if (comboFiltro.getSelectedItem() == "detalle") {
            columnaABuscar = 5;
        }
        if (comboFiltro.getSelectedItem() == "estado") {
            columnaABuscar = 6;
        }
        trsFiltro.setRowFilter(RowFilter.regexFilter(txtBuscar.getText(), columnaABuscar));
    }
	
	protected void initDataBindings() {
		JTableBinding<Reserva, List<Reserva>, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, lista, table);
		//
		BeanProperty<Reserva, String> personaBeanProperty = BeanProperty.create("id_persona");
		jTableBinding.addColumnBinding(personaBeanProperty).setColumnName("ID Persona").setEditable(false);
		//
		BeanProperty<Reserva, String> personaBeanProperty_1 = BeanProperty.create("id_elemento");
		jTableBinding.addColumnBinding(personaBeanProperty_1).setColumnName("ID Elemento").setEditable(false);
		//
		BeanProperty<Reserva, String> personaBeanProperty_2 = BeanProperty.create("fecha_registro");
		jTableBinding.addColumnBinding(personaBeanProperty_2).setColumnName("Fecha Registro").setEditable(false);
		//
		BeanProperty<Reserva, String> personaBeanProperty_3 = BeanProperty.create("fecha_inicio");
		jTableBinding.addColumnBinding(personaBeanProperty_3).setColumnName("Fecha Inicio").setEditable(false);
		//
		BeanProperty<Reserva, String> personaBeanProperty_4 = BeanProperty.create("fecha_fin");
		jTableBinding.addColumnBinding(personaBeanProperty_4).setColumnName("Fecha Fin").setEditable(false);
		//
		BeanProperty<Reserva, String> personaBeanProperty_5 = BeanProperty.create("detalle");
		jTableBinding.addColumnBinding(personaBeanProperty_5).setColumnName("Detalle").setEditable(false);
		//
		BeanProperty<Reserva, String> personaBeanProperty_6 = BeanProperty.create("estado");
		jTableBinding.addColumnBinding(personaBeanProperty_6).setColumnName("Estado").setEditable(false);
		//
		jTableBinding.setEditable(false);
		jTableBinding.bind();
	}
}
