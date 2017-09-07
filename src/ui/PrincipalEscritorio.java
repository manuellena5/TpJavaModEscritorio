package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PrincipalEscritorio extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desktopPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalEscritorio frame = new PrincipalEscritorio();
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
	public PrincipalEscritorio() {
		setTitle("Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 795, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
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
		
		JMenu mnNewMenu_1 = new JMenu("Personas");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("ABM personas");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ShowPersonas();
			}

			
		});
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenuItem mntmListadoPersonas = new JMenuItem("Listado personas");
		mntmListadoPersonas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ShowListadoPersonas();
			}

			
		});
		mnNewMenu_1.add(mntmListadoPersonas);
		
		JMenuItem mntmDatosPersonales = new JMenuItem("Datos Personales");
		/*mntmDatosPersonales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ShowDatosPersonales();
			}

			
		});*/
		mnNewMenu_1.add(mntmDatosPersonales);
		
		
		JMenu mnReservas = new JMenu("Reservas");
		menuBar.add(mnReservas);
		
		JMenuItem mntmABMReservas = new JMenuItem("ABM Reservas");
		/*mntmABMReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ShowAbmReservas();
			}

			
		});*/
		mnReservas.add(mntmABMReservas);
		
		JMenuItem mntmListadoReservas = new JMenuItem("Listado Reservas");
		/*mntmListadoReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ShowListadoReservas();
			}

			
		});*/
		mnReservas.add(mntmListadoReservas);
		
		JMenuItem mntmMisReservas = new JMenuItem("Mis Reservas");
		/*mntmMisReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ShowMisReservas();
			}

			
		});*/
		mnReservas.add(mntmMisReservas);
		
		
		
		JMenu mnTiposElementos = new JMenu("Tipos Elementos");
		menuBar.add(mnTiposElementos);
		
		JMenuItem mntmAbmTiposElementos = new JMenuItem("ABM Tipos Elementos");
		mntmAbmTiposElementos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ShowTipo_Elementos();

			}

		});
		
		
		mnTiposElementos.add(mntmAbmTiposElementos);
		
		JMenuItem mntmListadoTiposElementos = new JMenuItem("Listado Tipos Elementos");
		/*mntmListadoTiposElementos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ShowListadoTiposElementos();
			}
			
		});*/
		mnTiposElementos.add(mntmListadoTiposElementos);
		
		
		
		JMenu mnElementos = new JMenu("Elementos");
		menuBar.add(mnElementos);
		
		JMenuItem mntmAbmElementos = new JMenuItem("ABM Elementos");
		mntmAbmElementos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ShowElementos();
			}

			
		});
		mnElementos.add(mntmAbmElementos);
		
		JMenuItem mntmListadoElementos = new JMenuItem("Listado Elementos");
		/*mntmListadoElementos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ShowListadoElementos();
			}
			
		});*/
		mnElementos.add(mntmListadoElementos);
		
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
	/*private void ShowAbmReservas(){
		
		AbmReservas frm2 = new AbmReservas();
		desktopPane.add(frm2);
		frm2.setVisible(true);
	}
	
	private void ShowListadoReservas() {
		ListadoReservas lr = new ListadoReservas();
		desktopPane.add(lr);
		lr.setVisible(true);
		
	}
	
	private void ShowMisReservas() {
		MisReservas mr = new MisReservas();
		desktopPane.add(mr);
		mr.setVisible(true);
		
	}*/
	
	

	private void ShowTipo_Elementos(){
			
			Tipo_Elementos frm3 = new Tipo_Elementos();
			desktopPane.add(frm3);
			frm3.setVisible(true);
		} 

	/*	private void ShowListadoTiposElementos() {
>>>>>>> branch 'master' of https://github.com/manuellena5/proyectojava.git
			ListadoTiposElementos lte = new ListadoTiposElementos();
			desktopPane.add(lte);
			lte.setVisible(true);
			
		}*/
		
		
		//para elementos
		

		private void ShowElementos(){
				
		Elementos frm4 = new Elementos();
		desktopPane.add(frm4);
		frm4.setVisible(true);
		} 

		
		/*
		private void ShowListadoElementos() {
=======
				
		/* private void ShowListadoElementos() {
>>>>>>> branch 'master' of https://github.com/manuellena5/proyectojava.git
		ListadoElementos le = new ListadoElementos();
		desktopPane.add(le);
		le.setVisible(true);
					
		}*/
}
