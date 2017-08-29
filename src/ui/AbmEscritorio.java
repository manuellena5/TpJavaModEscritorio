package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entidades.Persona;
import negocio.PersonaLogic;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AbmEscritorio extends JInternalFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDni;
	private PersonaLogic perlog;
	private JCheckBox chkHabilitado;

	public PersonaLogic getPerlog() {
		return perlog;
	}

	public void setPerlog(PersonaLogic perlog) {
		this.perlog = perlog;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AbmEscritorio frame = new AbmEscritorio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AbmEscritorio() {
		setTitle("ABM personas");
		setMaximizable(true);
		setClosable(true);
		perlog = new PersonaLogic();
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(chkHabilitado)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNombre)
										.addComponent(lblApellido)
										.addComponent(lblDni))
									.addGap(29)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(txtDni, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtApellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addGap(46)
											.addComponent(btnBuscar))))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(36)
							.addComponent(btnNuevo)
							.addGap(18)
							.addComponent(btnEditar)
							.addGap(18)
							.addComponent(btnEliminar)
							.addGap(18)
							.addComponent(btnLimpiar)))
					.addContainerGap(52, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBuscar))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblApellido)
							.addGap(18)
							.addComponent(lblDni))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(txtApellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(txtDni, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(chkHabilitado)
					.addPreferredGap(ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNuevo)
						.addComponent(btnEditar)
						.addComponent(btnEliminar)
						.addComponent(btnLimpiar))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	protected void BuscarPersona() throws Exception{
		
		MapearAform(perlog.GetByDni(MapearDesdeform()));
	
	}
	
	protected void AgregarPersona(){
		
		
		perlog.Save(MapearDesdeform());
		
	}
	
	 protected void EliminarPersona() throws Exception{
		 
		 
		 perlog.EliminarPersona(MapearDesdeform());
	 
	 }
	
	
	protected void EditarPersona() throws Exception{
		
		
		perlog.ModificarPersona(MapearDesdeform());
		
		
	}
	
	protected void MapearAform(Persona per){
		
		this.txtApellido.setText(per.getApellido());
		this.txtNombre.setText(per.getNombre());
		this.txtDni.setText(per.getDni());
		this.chkHabilitado.setSelected(per.isHabilitado());

	}
	
	protected Persona MapearDesdeform(){
		
		Persona per = new Persona();
		
		per.setNombre(this.txtNombre.getText());
		per.setApellido(this.txtApellido.getText());
		per.setDni(this.txtDni.getText());
		per.setHabilitado(this.chkHabilitado.isSelected());
		
		return per;
		
	}
	
	
	protected void LimpiarControles(){
		this.txtApellido.setText("");
		this.txtDni.setText("");
		this.txtNombre.setText("");
		this.chkHabilitado.setSelected(false);
		
		
	}
	
}
