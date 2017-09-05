package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;


import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entidades.Persona;
import entidades.Categoria;
import negocio.PersonaLogic;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class AbmPersonas extends JInternalFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDni;
	private JCheckBox chkHabilitado;
	private JTextField txtusuario;
	private JPasswordField txtpassword;
	private JTextField txtid;
	private JComboBox cboCategoria;

	PersonaLogic perl = new PersonaLogic();

	
	public AbmPersonas() {
		setTitle("ABM personas");
		setMaximizable(true);
		setClosable(true);
		setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		JLabel lblNombre = new JLabel("Nombre");
		
		JLabel lblApellido = new JLabel("Apellido");
		
		JLabel lblDni = new JLabel("Dni");
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		
		txtDni = new JTextField();
		txtDni.setColumns(10);
		
		chkHabilitado = new JCheckBox("Habilitado");
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				try {
					BuscarPersona();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				AgregarPersona();
				LimpiarControles();
			}
		});
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					EditarPersona();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				LimpiarControles();
			}
		});
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					EliminarPersona();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				LimpiarControles();
			}
		});
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LimpiarControles();
			}
		});
		
		
		JLabel lblUsuario = new JLabel("Usuario");
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		
		txtusuario = new JTextField();
		txtusuario.setColumns(10);
		
		txtpassword = new JPasswordField();
		
		JLabel lblId = new JLabel("Id");
		
		txtid = new JTextField();
		txtid.setEditable(false);
		txtid.setColumns(10);
		
		cboCategoria = new JComboBox();
		
		
		JLabel lblCategoria = new JLabel("Categoria");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblContrasea)
								.addComponent(lblUsuario)
								.addComponent(lblDni)
								.addComponent(lblApellido)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblId)
									.addComponent(lblNombre)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(txtApellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtDni, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtusuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtpassword, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
									.addComponent(lblCategoria))
								.addComponent(txtid, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(36)
							.addComponent(btnNuevo)
							.addGap(18)
							.addComponent(btnEditar)
							.addGap(18)
							.addComponent(btnEliminar)))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnLimpiar)
							.addContainerGap(76, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(cboCategoria, 0, 99, Short.MAX_VALUE)
							.addGap(42))))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(chkHabilitado)
					.addContainerGap(345, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(154)
					.addComponent(btnBuscar)
					.addContainerGap(205, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBuscar)
						.addComponent(txtid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblId))
					.addGap(38)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cboCategoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCategoria))
					.addGap(23)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblApellido)
						.addComponent(txtApellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDni)
						.addComponent(txtDni, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsuario)
						.addComponent(txtusuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblContrasea)
						.addComponent(txtpassword, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
					.addComponent(chkHabilitado)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNuevo)
						.addComponent(btnEditar)
						.addComponent(btnEliminar)
						.addComponent(btnLimpiar))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		cargarListas();
	}
	
	private void cargarListas() {
		try {
			this.cboCategoria.setModel(new DefaultComboBoxModel(perl.getCategorias().toArray()));
			this.cboCategoria.setSelectedIndex(-1);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	
	protected void BuscarPersona() throws Exception{
		
		try {
			this.MapearAform(perl.GetByDni(this.MapearDesdeform()));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	
	}
	
	protected void AgregarPersona(){
		
		Persona p = this.MapearDesdeform();
		try{
			perl.add(p);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		this.txtid.setText(String.valueOf(p.getId_usuario()));
		
		
	}
	
	 protected void EliminarPersona() throws Exception{
		 
		 
		 try{
				perl.delete(this.MapearDesdeform());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage());
			}
	 
	 }
	
	
	protected void EditarPersona() throws Exception{
		
		try{
			perl.update(this.MapearDesdeform());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		
		
	}
	
	protected void MapearAform(Persona per){
		
		this.txtApellido.setText(per.getApellido());
		this.txtNombre.setText(per.getNombre());
		this.txtDni.setText(per.getDni());
		this.chkHabilitado.setSelected(per.isHabilitado());
		this.txtusuario.setText(per.getUsuario());
		this.txtpassword.setText(per.getPassword());
		this.cboCategoria.setSelectedItem(per.getCategoria().getDescripcion());
		this.txtid.setText(String.valueOf(per.getId_usuario()));
		

	}
	
	protected Persona MapearDesdeform(){
		
		Persona per = new Persona();
		
		if(!this.txtid.getText().isEmpty()){
			per.setId_usuario(Integer.parseInt(this.txtid.getText()));
		}
		per.setNombre(this.txtNombre.getText());
		per.setApellido(this.txtApellido.getText());
		per.setDni(this.txtDni.getText());
		per.setHabilitado(this.chkHabilitado.isSelected());
		per.setUsuario(this.txtusuario.getText());
		per.setPassword(this.txtpassword.getText());
		if (cboCategoria.getSelectedIndex() != -1){
			per.setCategoria((Categoria)this.cboCategoria.getSelectedItem());
		}
		
		return per;
		
	}
	
	
	protected void LimpiarControles(){
		this.txtApellido.setText("");
		this.txtDni.setText("");
		this.txtNombre.setText("");
		this.chkHabilitado.setSelected(false);
		this.txtusuario.setText("");
		this.txtpassword.setText("");
		this.txtid.setText("");
		
	}
	
	public void showPersona(Persona p){
		this.MapearAform(p);
		
	}
}
