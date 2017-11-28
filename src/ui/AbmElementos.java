package ui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import entidades.Categoria;
import entidades.Elemento;
import entidades.Persona;
import entidades.Tipo_Elemento;
import negocio.ElementosLogic;

import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AbmElementos extends JInternalFrame {
	private JTextField txtidelementos;
	private JTextField txtcantmaxpend;
	private JTextField txtnombre;
	private JTextField txtautor;
	private JTextField txtgenero;
	private JTextField txtstock;
	private JComboBox cbotipoelementos;
	private ElementosLogic el;
	private JTextArea txtdescripcion;
	private JButton btnAceptar;


	public AbmElementos() {
		
		el = new ElementosLogic();
		
		setClosable(true);
		
		setTitle("Elementos");
		setBounds(100, 100, 497, 429);
		
		JLabel lblId = new JLabel("ID");
		
		txtidelementos = new JTextField();
		txtidelementos.setEditable(false);
		txtidelementos.setColumns(10);
		
		JLabel lblTipoElemento = new JLabel("Tipo elemento");
		
		JLabel lblLimiteDePendientes = new JLabel("Limite de pendientes");
		
		txtcantmaxpend = new JTextField();
		txtcantmaxpend.setEditable(false);
		txtcantmaxpend.setColumns(10);
		
		JLabel lblNombreElemento = new JLabel("Nombre elemento");
		
		txtnombre = new JTextField();
		txtnombre.setColumns(10);
		
		JLabel lblAutor = new JLabel("Autor");
		
		txtautor = new JTextField();
		txtautor.setColumns(10);
		
		JLabel lblGenero = new JLabel("Genero");
		
		txtgenero = new JTextField();
		txtgenero.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		
		JLabel lblStock = new JLabel("Stock");
		
		txtstock = new JTextField();
		txtstock.setColumns(10);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			btnAceptarClick();
			LimpiarControles();
			}

			
		});
		
		JButton btnCancelar = new JButton("Salir");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			btnCancelarClick();
			}

			
		});
		
		
		cbotipoelementos = new JComboBox();
		cbotipoelementos.setBackground(Color.WHITE);
		cbotipoelementos.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				Elemento ele = new Elemento();
				if (event.getStateChange() == ItemEvent.SELECTED) {
			          Object item = event.getItem();
			          
			          ele.setTipo_Elemento((Tipo_Elemento) item);
			          txtcantmaxpend.setText(String.valueOf(ele.getTipo_Elemento().getCantMaxReservasPend()));
			          
			       }
			}

			
		});
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(60)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblId, Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblLimiteDePendientes)
										.addComponent(lblAutor)
										.addComponent(lblNombreElemento)
										.addComponent(lblGenero)
										.addComponent(lblDescripcion, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblTipoElemento)
									.addGap(38)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
											.addComponent(txtgenero)
											.addComponent(txtautor)
											.addComponent(txtnombre)
											.addComponent(txtcantmaxpend)
											.addGroup(groupLayout.createSequentialGroup()
												.addGap(15)
												.addComponent(btnAceptar)
												.addGap(18)
												.addComponent(btnCancelar)))
										.addPreferredGap(ComponentPlacement.RELATED, 98, Short.MAX_VALUE))
									.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(txtidelementos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(cbotipoelementos, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))
										.addGap(106)))
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE))))
					.addGap(61))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(119)
					.addComponent(lblStock)
					.addGap(18)
					.addComponent(txtstock, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(232, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblId)
						.addComponent(txtidelementos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTipoElemento)
						.addComponent(cbotipoelementos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLimiteDePendientes)
						.addComponent(txtcantmaxpend, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombreElemento)
						.addComponent(txtnombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAutor)
						.addComponent(txtautor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtgenero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblGenero))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDescripcion)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnAceptar)
										.addComponent(btnCancelar))
									.addGap(25))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(63)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblStock)
										.addComponent(txtstock, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(88)))))
					.addGap(25))
		);
		
		txtdescripcion = new JTextArea();
		scrollPane.setViewportView(txtdescripcion);
		getContentPane().setLayout(groupLayout);
		cargarListas();

	}
	
	private void cargarListas() {
		try {
			this.cbotipoelementos.setModel(new DefaultComboBoxModel(el.getTipo_Elementos().toArray()));
			this.cbotipoelementos.setSelectedIndex(-1);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	
	private void btnAceptarClick() {
		
		boolean validaCampos = this.validarVacios();
		if(validaCampos==false){
		
		Elemento ele = this.MapearDesdeform();
		try{
			if (btnAceptar.getText() == "Aceptar") {
				el.add(ele);
				JOptionPane.showMessageDialog(this, "Se ha registrado correctamente");
			} else if (btnAceptar.getText() == "Editar") {
				
				el.update(ele);
				JOptionPane.showMessageDialog(this, "Se ha editado correctamente");
				Elementos frm = new Elementos();
				this.getDesktopPane().add(frm);
				frm.setVisible(true);
				this.dispose();
			} else{
				
				el.delete(ele);
				JOptionPane.showMessageDialog(this, "Se ha eliminado correctamente");
				Elementos frm = new Elementos();
				this.getDesktopPane().add(frm);
				frm.setVisible(true);
				this.dispose();
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		this.txtidelementos.setText(String.valueOf(ele.getId_elemento()));
		} else {
			JOptionPane.showMessageDialog(this, "Hay campos obligatorios que no han sido completados");
			Elementos frm = new Elementos();
			this.getDesktopPane().add(frm);
			frm.setVisible(true);
			this.dispose();
		}
	}
	
	
	
	protected void MapearAform(Elemento ele){
		
		this.txtnombre.setText(ele.getNombre());
		this.txtautor.setText(ele.getAutor());
		this.txtdescripcion.setText(ele.getDescripcion());
		this.txtstock.setText(String.valueOf(ele.getStock()));
		this.txtgenero.setText(ele.getGenero());
		this.cbotipoelementos.setSelectedItem(ele.getTipo_Elemento());
		this.txtcantmaxpend.setText(String.valueOf(ele.getTipo_Elemento().getCantMaxReservasPend()));
		this.txtidelementos.setText(String.valueOf(ele.getId_elemento()));
		

	}
	
	protected Elemento MapearDesdeform(){
		
		Elemento ele = new Elemento();
		
		
		if(!this.txtidelementos.getText().isEmpty()){
			ele.setId_elemento(Integer.parseInt(this.txtidelementos.getText()));
		}
		ele.setNombre(this.txtnombre.getText());
		ele.setAutor(this.txtautor.getText());
		ele.setGenero(this.txtgenero.getText());
		ele.setDescripcion(this.txtdescripcion.getText());
		ele.setStock(Integer.parseInt(this.txtstock.getText()));
		if (cbotipoelementos.getSelectedIndex() != -1){
			ele.setTipo_Elemento((Tipo_Elemento)this.cbotipoelementos.getSelectedItem());
		}
		
		return ele;
		
	}
	
	private void LimpiarControles(){
		
		this.txtautor.setText("");
		this.txtcantmaxpend.setText("");
		this.txtdescripcion.setText("");
		this.txtgenero.setText("");
		this.txtidelementos.setText("");
		this.txtnombre.setText("");
		this.txtstock.setText("");
		this.cbotipoelementos.setSelectedIndex(-1);
		
		
	}
	
	private void HabilitarControles(boolean val)
	{
		this.txtautor.setEditable(val);
		this.txtdescripcion.setEnabled(val);
		this.txtgenero.setEditable(val);
		this.txtnombre.setEditable(val);
		this.txtstock.setEditable(val);

		
	}
	
	
	public void showAbmElementos(Elemento ele,String modo){
		if (modo == "Editar") {
			this.MapearAform(ele);
			btnAceptar.setText("Editar");
			this.cbotipoelementos.setEnabled(false);
			HabilitarControles(true);
			
		}else {
			this.MapearAform(ele);
			btnAceptar.setText("Eliminar");
			this.cbotipoelementos.setEnabled(false);
			HabilitarControles(false);
		}
			
		
		
	}
	
	private void btnCancelarClick() {
		
		
		Elementos frm = new Elementos();
		this.getDesktopPane().add(frm);
		frm.setVisible(true);
		this.dispose();
		
		
	}
	
	
	
	private  boolean validarVacios(){
	     boolean error=false;
	      //validando que no esten vacios los campos
	        if(this.txtnombre.getText().isEmpty())
	           error= true;
	        if(this.cbotipoelementos.getSelectedIndex()==-1)
		           error= true;
	        if(this.txtstock.getText().isEmpty())
	           error= true;
	        
	      return error;
	}
	
	
}
