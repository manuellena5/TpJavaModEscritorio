package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entidades.Persona;
import negocio.PersonaLogic;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JDesktopPane;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	private JPanel contentPane;
	private PersonaLogic personalogic;
	private Persona persona;
	private JTextField txtusuario;
	private JDesktopPane desktopPane;
	private JLabel lblloginincorrecto;
	private JPasswordField txtpassword;
	public static int id;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 373, 274);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(SystemColor.control);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
		);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(10, 68, 46, 14);
		desktopPane.add(lblUsuario);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 110, 67, 14);
		desktopPane.add(lblPassword);
		
		txtusuario = new JTextField();
		txtusuario.setBounds(81, 65, 142, 20);
		desktopPane.add(txtusuario);
		txtusuario.setColumns(10);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				validarloggin();
			}
		});
		btnIngresar.setBounds(81, 173, 89, 23);
		desktopPane.add(btnIngresar);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLogin.setBounds(10, 11, 61, 20);
		desktopPane.add(lblLogin);
		
		lblloginincorrecto = new JLabel("");
		lblloginincorrecto.setVisible(false);
		lblloginincorrecto.setForeground(Color.RED);
		lblloginincorrecto.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblloginincorrecto.setBounds(30, 138, 285, 14);
		desktopPane.add(lblloginincorrecto);
		
		txtpassword = new JPasswordField();
		txtpassword.setBounds(81, 107, 142, 20);
		desktopPane.add(txtpassword);
		contentPane.setLayout(gl_contentPane);
	}
	
	
	private void validarloggin(){
		
		personalogic = new PersonaLogic();
		persona = new Persona();
		
		
		
		try {
			
			
			String usuario = this.txtusuario.getText();
			String password = this.txtpassword.getText();
			persona.setUsuario(usuario);
			persona.setPassword(password);
			Persona pers = personalogic.login(persona);
			
			if (pers == null) {
				
				lblloginincorrecto.setText("Usuario y/o contraseña incorrecta");
				lblloginincorrecto.setVisible(true);
				
				
				
			}else if (pers.isHabilitado() == false) {
				lblloginincorrecto.setText("El usuario no esta habilitado para ingresar al sistema");
				lblloginincorrecto.setVisible(true);
			}else {
				
				lblloginincorrecto.setVisible(false);
				id = pers.getId_persona();
				
				PrincipalEscritorio frm = new PrincipalEscritorio();
				
				
				frm.setVisible(true);
				this.dispose();
				this.setVisible(false);
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Ha ocurrido un error inesperado, vuelva a intentarlo en un momento");
			System.out.println(e.getMessage());
		}
		
		
	}


}
