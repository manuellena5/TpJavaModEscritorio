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
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

public class ListadoPersonas extends JInternalFrame {
	
	private JTable table;
	private ArrayList<Persona> lista; 
	PersonaLogic perl = new PersonaLogic();
	private JTextField txtbuscar;
	
	public ListadoPersonas() {
		setTitle("Listado de personas");
		setClosable(true);
		setBounds(100, 100, 683, 481);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblBuscarPorDni = new JLabel("Buscar por dni:");
		
		txtbuscar = new JTextField();
		txtbuscar.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnBuscarClick();
			}

			
		});
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnEditarClick();
			}

			
		});
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					actualizarListado();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(48)
					.addComponent(lblBuscarPorDni)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtbuscar, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnBuscar)
					.addGap(18)
					.addComponent(btnActualizar)
					.addPreferredGap(ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
					.addComponent(btnEditar)
					.addGap(53))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBuscarPorDni)
						.addComponent(txtbuscar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBuscar)
						.addComponent(btnEditar)
						.addComponent(btnActualizar))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(36, Short.MAX_VALUE))
		);
		
		table = new JTable();
		
		table.setBackground(Color.LIGHT_GRAY);
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);
		
		try{
			this.lista = perl.GetAll();
		} catch (Exception e){
			JOptionPane.showMessageDialog(this,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
	
		}
		initDataBindings();

	}
	
	private void btnEditarClick() {
		int indexPersona=table.convertRowIndexToModel(table.getSelectedRow());
		
		AbmEscritorio abm= new AbmEscritorio();
		abm.showPersona(this.lista.get(indexPersona));
		
		this.getDesktopPane().add(abm);
		abm.setVisible(true);
		
	}
	
	private void btnBuscarClick() {
		/*try {
			Persona per;
			per = perl.GetByDni(this.txtbuscar.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}*/
		
	}
	
	private void actualizarListado() throws Exception {
		try{
			this.lista = perl.GetAll();
		} catch (Exception e){
			JOptionPane.showMessageDialog(this,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
	
		}
		initDataBindings();
	}
	
	protected void initDataBindings() {
		JTableBinding<Persona, List<Persona>, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, lista, table);
		//
		BeanProperty<Persona, Integer> personaBeanProperty = BeanProperty.create("id_usuario");
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
		jTableBinding.setEditable(false);
		jTableBinding.bind();
	}
}
