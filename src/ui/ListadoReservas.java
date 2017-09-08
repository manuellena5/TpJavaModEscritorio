package ui;

import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
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
import javax.swing.table.TableRowSorter;

import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import entidades.Reserva;
import negocio.ReservasLogic;

public class ListadoReservas extends JInternalFrame {
	private JTable table;
	private JTextField txtBuscar;
	private JComboBox comboFiltro;
	private TableRowSorter trsFiltro;
	private ArrayList<Reserva> lista; 
	ReservasLogic reservaLogic = new ReservasLogic();

	
	public ListadoReservas() {
		setTitle("Listado Reservas");
		setClosable(true);
		setBounds(100, 100, 992, 483);
		
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
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(145)
							.addComponent(lblBuscarPor)
							.addGap(18)
							.addComponent(comboFiltro, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
							.addGap(27)
							.addComponent(txtBuscar, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 933, Short.MAX_VALUE))
					.addContainerGap())
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(887, Short.MAX_VALUE)
					.addComponent(btnSalir)
					.addGap(36))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtBuscar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBuscarPor))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSalir)
					.addContainerGap(25, Short.MAX_VALUE))
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);
		
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
		BeanProperty<Reserva, Integer> reservaBeanProperty = BeanProperty.create("elemento.id_elemento");
		jTableBinding.addColumnBinding(reservaBeanProperty).setColumnName("ID Elemento").setEditable(false);
		//
		BeanProperty<Reserva, Integer> reservaBeanProperty_1 = BeanProperty.create("persona.id_persona");
		jTableBinding.addColumnBinding(reservaBeanProperty_1).setColumnName("ID Persona").setEditable(false);
		//
		BeanProperty<Reserva, Date> reservaBeanProperty_2 = BeanProperty.create("fecha_inicio");
		jTableBinding.addColumnBinding(reservaBeanProperty_2).setColumnName("Fecha Inicio").setEditable(false);
		//
		BeanProperty<Reserva, Date> reservaBeanProperty_3 = BeanProperty.create("fecha_fin");
		jTableBinding.addColumnBinding(reservaBeanProperty_3).setColumnName("Fecha Fin").setEditable(false);
		//
		BeanProperty<Reserva, Date> reservaBeanProperty_4 = BeanProperty.create("fecha_registro");
		jTableBinding.addColumnBinding(reservaBeanProperty_4).setColumnName("Fecha Registro").setEditable(false);
		//
		BeanProperty<Reserva, String> reservaBeanProperty_5 = BeanProperty.create("estado");
		jTableBinding.addColumnBinding(reservaBeanProperty_5).setColumnName("Estado").setEditable(false);
		//
		BeanProperty<Reserva, String> reservaBeanProperty_6 = BeanProperty.create("detalle");
		jTableBinding.addColumnBinding(reservaBeanProperty_6).setColumnName("Detalle").setEditable(false);
		//
		BeanProperty<Reserva, String> reservaBeanProperty_7 = BeanProperty.create("persona.apellido");
		jTableBinding.addColumnBinding(reservaBeanProperty_7).setColumnName("Apellido").setEditable(false);
		//
		BeanProperty<Reserva, String> reservaBeanProperty_8 = BeanProperty.create("persona.nombre");
		jTableBinding.addColumnBinding(reservaBeanProperty_8).setColumnName("Nombre P.").setEditable(false);
		//
		BeanProperty<Reserva, String> reservaBeanProperty_9 = BeanProperty.create("persona.dni");
		jTableBinding.addColumnBinding(reservaBeanProperty_9).setColumnName("Dni").setEditable(false);
		//
		BeanProperty<Reserva, String> reservaBeanProperty_10 = BeanProperty.create("elemento.nombre");
		jTableBinding.addColumnBinding(reservaBeanProperty_10).setColumnName("Elemento").setEditable(false);
		//
		BeanProperty<Reserva, String> reservaBeanProperty_11 = BeanProperty.create("elemento.autor");
		jTableBinding.addColumnBinding(reservaBeanProperty_11).setColumnName("Autor").setEditable(false);
		//
		BeanProperty<Reserva, String> reservaBeanProperty_12 = BeanProperty.create("elemento.genero");
		jTableBinding.addColumnBinding(reservaBeanProperty_12).setColumnName("Genero").setEditable(false);
		//
		jTableBinding.bind();
	}
}
