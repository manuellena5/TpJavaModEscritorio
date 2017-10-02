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

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Elementos extends JInternalFrame {
	private JTable table;
	private JTextField txtbuscar;
	private TableRowSorter trsFiltro;

	private ArrayList<Elemento> lista; 
	ElementosLogic elementoLogic = new ElementosLogic();
	private JComboBox comboFiltro;
	private JButton btnAlta;
	private JButton btnEditar;
	private JButton btnBaja;

	public Elementos() {
		
		setTitle("Elementos");
		setClosable(true);
		setBounds(100, 100, 806, 483);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblBuscarPor = new JLabel("Buscar por:");
		
		comboFiltro = new JComboBox();
		comboFiltro.setModel(new DefaultComboBoxModel(new String[] {"Id", "Elemento", "Descripcion", "Autor", "Genero","Stock","Tipo_elemento"}));
		
		
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
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblBuscarPor)
							.addGap(38)
							.addComponent(comboFiltro, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
							.addGap(47)
							.addComponent(txtbuscar, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
							.addGap(293))
						.addComponent(btnSalir)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 736, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(22, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBuscarPor)
						.addComponent(comboFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtbuscar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnSalir)
					.addGap(14))
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
				ShowAbmElementos(modo);
			}

			
		});
		menuBar.add(btnAlta);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String modo = "Editar";
				if (table.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(btnEditar, "Debe seleccionar un elemento");}
				else{
					ShowAbmElementos(modo);
				}
				
			}
		});
		menuBar.add(btnEditar);
		
		btnBaja = new JButton("Baja");
		btnBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String modo = "Eliminar";
				if (table.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(btnEditar, "Debe seleccionar un elemento");}
				else{
					ShowAbmElementos(modo);
				}
			}
		});
		menuBar.add(btnBaja);
		
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
        if (comboFiltro.getSelectedItem().toString() == "Id") {
            columnaABuscar = 0;
        }
        if (comboFiltro.getSelectedItem() == "Elemento") {
            columnaABuscar = 1;
        }
        if (comboFiltro.getSelectedItem() == "Descripcion") {
            columnaABuscar = 2;
        }
        if (comboFiltro.getSelectedItem() == "Autor") {
            columnaABuscar = 3;
        }
        if (comboFiltro.getSelectedItem() == "Genero") {
            columnaABuscar = 4;
        }
        if (comboFiltro.getSelectedItem().toString() == "Stock") {
            columnaABuscar = 5;
        }
        if (comboFiltro.getSelectedItem() == "Tipo_elemento") {
            columnaABuscar = 6;
        }
        trsFiltro.setRowFilter(RowFilter.regexFilter(txtbuscar.getText(), columnaABuscar));
    }
	
	
	protected void initDataBindings() {
		JTableBinding<Elemento, List<Elemento>, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, lista, table);
		//
		BeanProperty<Elemento, Integer> elementoBeanProperty = BeanProperty.create("id_elemento");
		jTableBinding.addColumnBinding(elementoBeanProperty).setColumnName("ID elemento");
		//
		BeanProperty<Elemento, String> elementoBeanProperty_1 = BeanProperty.create("nombre");
		jTableBinding.addColumnBinding(elementoBeanProperty_1).setColumnName("Elemento");
		//
		BeanProperty<Elemento, String> elementoBeanProperty_2 = BeanProperty.create("descripcion");
		jTableBinding.addColumnBinding(elementoBeanProperty_2).setColumnName("Descripcion");
		//
		BeanProperty<Elemento, String> elementoBeanProperty_3 = BeanProperty.create("autor");
		jTableBinding.addColumnBinding(elementoBeanProperty_3).setColumnName("Autor");
		//
		BeanProperty<Elemento, String> elementoBeanProperty_4 = BeanProperty.create("genero");
		jTableBinding.addColumnBinding(elementoBeanProperty_4).setColumnName("Genero");
		//
		BeanProperty<Elemento, Integer> elementoBeanProperty_5 = BeanProperty.create("stock");
		jTableBinding.addColumnBinding(elementoBeanProperty_5).setColumnName("Stock");
		//
		BeanProperty<Elemento, String> elementoBeanProperty_6 = BeanProperty.create("tipo_Elemento.nombre");
		jTableBinding.addColumnBinding(elementoBeanProperty_6).setColumnName("Tipo Elemento");
		//
		
		jTableBinding.bind();
	}
	
	private void ShowAbmElementos(String modo) {
		
		AbmElementos abm = new AbmElementos();
		
		if (modo != "Alta") {
			
			int index = table.convertRowIndexToModel(table.getSelectedRow());
			abm.showAbmElementos(this.lista.get(index),modo);
						
			
		}
		this.getDesktopPane().add(abm);
		abm.setVisible(true);
		this.dispose();
		
		
		
	}
	
	
	private void btnSalirClick() {
		dispose();
		
	}
	
	
}
