package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entidades.Persona;
import negocio.PersonaLogic;

import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PrincipalEscritorio extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desktopPane;
	private JMenuItem itemabmpersonas;
	private JMenuItem itemlistadopersonas;
	private JMenuItem itemdatospersonales;
	private JMenuItem itemabmreservas;
	private JMenuItem itemlistadoreservas;
	private JMenuItem itemmisreservas;
	private JMenuItem itemnuevareserva;
	private JMenuItem itemabmtipoelementos;
	private JMenuItem itemlistadotipoelementos;
	private JMenuItem itemabmelementos;
	private JMenuItem itemlistadoelementos;
	private JMenu menupersonas;
	private JMenu menureservas;
	private JMenu menutipoelementos;
	private JMenu menuelementos;
	private Persona persona;
	private PersonaLogic personalogic;

	
	/**
	 * Create the frame.
	 */
	public PrincipalEscritorio() {

		setTitle("Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 795, 550);
		setExtendedState(MAXIMIZED_BOTH); /* Para La ventana ocupe toda la pantalla */
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		personalogic = new PersonaLogic();
		
		desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 434, 21);
		desktopPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Archivo");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Salir");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		menupersonas = new JMenu("Personas");
		menuBar.add(menupersonas);
		
		itemabmpersonas = new JMenuItem("ABM personas");
		itemabmpersonas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ShowPersonas();
			}

			
		});
		menupersonas.add(itemabmpersonas);
		
		itemlistadopersonas = new JMenuItem("Listado personas");
		itemlistadopersonas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ShowListadoPersonas();
			}

			
		});
		menupersonas.add(itemlistadopersonas);
		
		itemdatospersonales = new JMenuItem("Datos Personales");
		itemdatospersonales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showdatospersonales();
			}
		});
		/*mntmDatosPersonales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ShowDatosPersonales();
			}

			
		});*/
		menupersonas.add(itemdatospersonales);
		
		
		menureservas = new JMenu("Reservas");
		menuBar.add(menureservas);
		
		itemabmreservas = new JMenuItem("ABM Reservas");
		itemabmreservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ShowReservas();
			}

			
		});
		menureservas.add(itemabmreservas);
		
		itemlistadoreservas = new JMenuItem("Listado Reservas");
		itemlistadoreservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ShowListadoReservas();
			}

			
		});
		menureservas.add(itemlistadoreservas);
		
		itemmisreservas = new JMenuItem("Mis Reservas");
		/*mntmMisReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ShowMisReservas();
			}

			
		});*/
		menureservas.add(itemmisreservas);
		
		itemnuevareserva = new JMenuItem("Nueva reserva");
		itemnuevareserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ShowReservasAbm();
			}

			
		});
		menureservas.add(itemnuevareserva);
		
		
		
		menutipoelementos = new JMenu("Tipos Elementos");
		menuBar.add(menutipoelementos);
		
		itemabmtipoelementos = new JMenuItem("ABM Tipos Elementos");
		itemabmtipoelementos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ShowTipo_Elementos();

			}

		});
		
		
		menutipoelementos.add(itemabmtipoelementos);
		
		itemlistadotipoelementos = new JMenuItem("Listado Tipos Elementos");
		itemlistadotipoelementos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ShowListadoTiposElementos();
			}
			
		});
		menutipoelementos.add(itemlistadotipoelementos);
		
		
		
		menuelementos = new JMenu("Elementos");
		menuBar.add(menuelementos);
		
		itemabmelementos = new JMenuItem("ABM Elementos");
		itemabmelementos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ShowElementos();
			}

			
		});
		menuelementos.add(itemabmelementos);
		
		itemlistadoelementos = new JMenuItem("Listado Elementos");
		itemlistadoelementos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ShowListadoElementos();
			}
			
		});
		menuelementos.add(itemlistadoelementos);
		
		
		validaringreso(Login.id);
	}
	
	//para personas
	private void ShowAbmPersonas(){
		
		AbmPersonas frm = new AbmPersonas();
		desktopPane.add(frm);
		frm.setVisible(true);
	}
	
	private void ShowPersonas(){
		
		Personas frm = new Personas();
		desktopPane.add(frm);
		frm.setVisible(true);
	}
	
	private void ShowListadoPersonas() {
		ListadoPersonas lp = new ListadoPersonas();
		desktopPane.add(lp);
		lp.setVisible(true);
		
	}
	
	private void ShowAbmTipoElementos() {
		AbmTipoElementos abm = new AbmTipoElementos();
		desktopPane.add(abm);
		abm.setVisible(true);
		
	}
	
	/*private void ShowDatosPersonales() {
		DatosPersonales dp = new DatosPersonales();
		desktopPane.add(dp);
		dp.setVisible(true);
		
	}*/
	
	
	
	//para reservas
	private void ShowReservas(){
		
		Reservas frm2 = new Reservas();
		desktopPane.add(frm2);
		frm2.setVisible(true);
		
		
	}
	
	private void ShowListadoReservas() {
		ListadoReservas lr = new ListadoReservas();
		desktopPane.add(lr);
		lr.setVisible(true);
		
	}
	
	/*private void ShowMisReservas() {
		MisReservas mr = new MisReservas();
		desktopPane.add(mr);
		mr.setVisible(true);
		
	}*/
	
	

	private void ShowTipo_Elementos(){
			
			Tipo_Elementos frm3 = new Tipo_Elementos();
			desktopPane.add(frm3);
			frm3.setVisible(true);
		} 

	private void ShowListadoTiposElementos() {
			ListadoTiposElementos lte = new ListadoTiposElementos();
			desktopPane.add(lte);
			lte.setVisible(true);
			
		}
		
		
		//para elementos
		

		private void ShowElementos(){
				
		Elementos frm4 = new Elementos();
		desktopPane.add(frm4);
		frm4.setVisible(true);
		} 

		
		private void ShowListadoElementos() {
		ListadoElementos le = new ListadoElementos();
		desktopPane.add(le);
		le.setVisible(true);
					
		}
		
		private void ShowReservasAbm() {
			ReservasAbm frm = new ReservasAbm();
			desktopPane.add(frm);
			frm.setVisible(true);
			
		}
		
		private void validaringreso(int id){
			
			try {
				persona = new Persona();
				
				
				persona = personalogic.GetById(id);
				
				String categoria =  persona.getCategoria().getDescripcion();
				
				if (categoria.equals("Usuario")) {
					
					this.menupersonas.setText("Mis datos");
					this.itemabmpersonas.setVisible(false);
					this.itemlistadopersonas.setVisible(false);
					this.itemabmreservas.setVisible(false);
					this.itemlistadoreservas.setVisible(false);
					this.menuelementos.setVisible(false);
					this.menutipoelementos.setVisible(false);
					
					
				}else if(categoria.equals("Administrador")){
					this.itemdatospersonales.setVisible(false);
					this.itemnuevareserva.setVisible(false);
					
				}
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(this, "Ha ocurrido un error inesperado");
				
			}
			
		}
		
		private void showdatospersonales(){
				
				
				try {
					String modo = "EditarPersonales";
					AbmPersonas abm = new AbmPersonas();
					persona = personalogic.GetById(Login.id);
					abm.showAbmPersonas(persona,modo);
					desktopPane.add(abm);
					abm.setVisible(true);
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this, "Ha ocurrido un error inesperado");
					System.out.println(e.getMessage());
				}
				
				
				
			
			
		}
		
		
		
		
}
