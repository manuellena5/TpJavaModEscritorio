package ui;

import java.awt.EventQueue;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import entidades.Persona;
import entidades.Reserva;
import negocio.PersonaLogic;
import negocio.ReservasLogic;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListadoReservasUsuario extends JInternalFrame {
	private JTable table;
	private JButton btnCancelar;
	private ReservasLogic reservalogic;
	private Persona persona;
	private PersonaLogic personalogic;
	private ArrayList<Reserva> lista;
	private JButton btnSalir;
	private Reserva reserva;

	
	public ListadoReservasUsuario() {
		setClosable(true);
		setBounds(100, 100, 923, 526);
		
		reservalogic = new ReservasLogic();
		persona = new Persona();
		personalogic = new PersonaLogic();
		lista = new ArrayList<Reserva>();
		
		JScrollPane scrollPane = new JScrollPane();
		
		btnCancelar = new JButton("Cancelar reserva");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				cancelarreserva();
				
				
			}
		});
		
		btnSalir = new JButton("Salir");
		btnSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnCancelarClick();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 879, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(35)
							.addComponent(btnSalir, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnCancelar)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSalir)
						.addComponent(btnCancelar))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(87, Short.MAX_VALUE))
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);
		
		try{
			
			persona = personalogic.GetById(Login.id);
			lista = reservalogic.getByUsuario(persona);
		
		} catch (Exception e){
			JOptionPane.showMessageDialog(this,"Ha ocurrido un error inesperado");
			System.out.println(e.getMessage());
	
		}
		initDataBindings();
		
		
	}
	
	
	protected void initDataBindings() {
		JTableBinding<Reserva, List<Reserva>, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, lista, table);
		//
		BeanProperty<Reserva, java.util.Date> reservaBeanProperty = BeanProperty.create("fecha_registro");
		jTableBinding.addColumnBinding(reservaBeanProperty).setColumnName("Fecha Registro").setEditable(false);
		//
		BeanProperty<Reserva, java.util.Date> reservaBeanProperty_1 = BeanProperty.create("fecha_inicio");
		jTableBinding.addColumnBinding(reservaBeanProperty_1).setColumnName("Fecha Inicio").setEditable(false);
		//
		BeanProperty<Reserva, java.util.Date> reservaBeanProperty_2 = BeanProperty.create("fecha_fin");
		jTableBinding.addColumnBinding(reservaBeanProperty_2).setColumnName("Fecha Fin").setEditable(false);
		//
		BeanProperty<Reserva, String> reservaBeanProperty_3 = BeanProperty.create("estado");
		jTableBinding.addColumnBinding(reservaBeanProperty_3).setColumnName("Estado").setEditable(false);
		//
		BeanProperty<Reserva, String> reservaBeanProperty_4 = BeanProperty.create("persona.apellido");
		jTableBinding.addColumnBinding(reservaBeanProperty_4).setColumnName("Apellido").setEditable(false);
		//
		BeanProperty<Reserva, String> reservaBeanProperty_5 = BeanProperty.create("persona.nombre");
		jTableBinding.addColumnBinding(reservaBeanProperty_5).setColumnName("Nombre P.").setEditable(false);
		//
		BeanProperty<Reserva, String> reservaBeanProperty_6 = BeanProperty.create("elemento.nombre");
		jTableBinding.addColumnBinding(reservaBeanProperty_6).setColumnName("Elemento").setEditable(false);
		//
		BeanProperty<Reserva, String> reservaBeanProperty_7 = BeanProperty.create("elemento.autor");
		jTableBinding.addColumnBinding(reservaBeanProperty_7).setColumnName("Autor").setEditable(false);
		//
		BeanProperty<Reserva, String> reservaBeanProperty_8 = BeanProperty.create("elemento.genero");
		jTableBinding.addColumnBinding(reservaBeanProperty_8).setColumnName("Genero").setEditable(false);
		//
		BeanProperty<Reserva, String> reservaBeanProperty_9 = BeanProperty.create("detalle");
		jTableBinding.addColumnBinding(reservaBeanProperty_9).setColumnName("Detalle").setEditable(false);
		//
		jTableBinding.bind();
	}
	
	
	private void cancelarreserva(){
		
		java.util.Date FechaDelSistema = new java.util.Date(); /*Tomo la hora del sistema*/
		
		java.sql.Date fechaActual = new java.sql.Date(FechaDelSistema.getTime()); /* A la hora del sistema la convierto en el formato que trae la base */
		
		
		reserva = new Reserva(); 
		int index = table.convertRowIndexToModel(table.getSelectedRow());
		reserva = lista.get(index);
		
		java.sql.Date fechainicio = new java.sql.Date(reserva.getFecha_inicio().getTime());
		
		
			if (reserva.getEstado().equals("Cancelada")) {
				JOptionPane.showMessageDialog(btnCancelar, "Debe seleccionar una reserva que esté activa");
		}else
			if (reserva.getEstado().equals("Terminada")) {
				JOptionPane.showMessageDialog(btnCancelar, "Debe seleccionar una reserva que esté activa");
		}else
			if(fechainicio.before(fechaActual)){
					JOptionPane.showMessageDialog(btnCancelar, "Debe seleccionar una reserva que no haya iniciado");
		}else{
			
			
			try {
/*				java.sql.Date fecharegistro = new java.sql.Date(reserva.getFecha_registro().getTime());
				reserva = reservalogic.GetOne(reserva.getPersona().getId_persona(), reserva.getElemento().getId_elemento(),fecharegistro );*/
			
				reserva.setEstado("Cancelada");
				ReservasAbm frm = new ReservasAbm();
				frm.showAbmReservas(reserva, "Cancelar");
				this.getDesktopPane().add(frm);
				frm.setVisible(true);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "No se pudo cancelar la reserva, ");
			}
			
		}
		
	}
	
	private void btnCancelarClick(){
		this.dispose();
	}
		
		
		
	
	
	
}
