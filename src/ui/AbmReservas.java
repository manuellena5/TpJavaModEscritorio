package ui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import com.toedter.calendar.JCalendar;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.SwingConstants;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;


import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;
import com.toedter.calendar.JDateChooser;

import entidades.Elemento;
import entidades.Persona;
import entidades.Reserva;
import entidades.Tipo_Elemento;
import negocio.ReservasLogic;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.UIManager;

public class AbmReservas extends JInternalFrame {
	private JTextField txtnombrepersona;
	private JTextField txtapellido;
	private JTextField txtdni;
	private JTextField txtusuario;
	private JTextField txtautor;
	private JTextField txtgenero;
	private JTextField txttipoelemento;
	private JDateChooser cfechainicio;
	private JDateChooser cfechafin;
	private JDateChooser cfecharegistro;
	private JButton btnAceptar;
	private JTextField txtidpersona;
	private JTextField txtidelemento;
	private JComboBox comboestado;
	private JTextArea txtdetalle;
	private JButton btnBuscarPersona;
	private JButton btnBuscarElemento;
	private Reservas frmreservas;
	 private ReservasLogic reservaslogic;
	 private JTextField txtcantmaxreservaspend;
	 private JTextField txtnombreelemento;
	 
	 
	
	
	

	
	
	
	public AbmReservas() {
		
		
		
		setClosable(true);
		setTitle("Reservas");
		getContentPane().setBackground(SystemColor.menu);
		
		JLabel lblDatosDeLa = new JLabel("Datos de la persona");
		lblDatosDeLa.setFont(new Font("Verdana", Font.BOLD, 15));
		
		JLabel lblNombre = new JLabel("Nombre");
		
		JLabel lblApellido = new JLabel("Apellido");
		
		JLabel lblDni = new JLabel("Dni");
		
		JLabel lblUsuario = new JLabel("Usuario");
		
		reservaslogic = new ReservasLogic();
		
		txtnombrepersona = new JTextField();
		txtnombrepersona.setEditable(false);
		txtnombrepersona.setColumns(10);
		
		txtapellido = new JTextField();
		txtapellido.setEditable(false);
		txtapellido.setColumns(10);
		
		txtdni = new JTextField();
		txtdni.setEditable(false);
		txtdni.setColumns(10);
		
		txtusuario = new JTextField();
		txtusuario.setEditable(false);
		txtusuario.setColumns(10);
		
		btnBuscarPersona = new JButton("Buscar persona");
		btnBuscarPersona.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				showListadoPersonas();
			}

			
		});
		
		JLabel lblDatosDelElemento = new JLabel("Datos del elemento");
		lblDatosDelElemento.setFont(new Font("Verdana", Font.BOLD, 15));
		
		JLabel lblNombre_1 = new JLabel("Nombre");
		
		JLabel lblAutor = new JLabel("Autor");
		
		JLabel lblGenero = new JLabel("Genero");
		
		JLabel lblTipoDeElemento = new JLabel("Tipo de elemento");
		
		txtautor = new JTextField();
		txtautor.setEditable(false);
		txtautor.setColumns(10);
		
		txtgenero = new JTextField();
		txtgenero.setEditable(false);
		txtgenero.setColumns(10);
		
		txttipoelemento = new JTextField();
		txttipoelemento.setEditable(false);
		txttipoelemento.setColumns(10);
		
		btnBuscarElemento = new JButton("Buscar elemento");
		btnBuscarElemento.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showListadoElementos();
			}

			
		});
		
		JLabel lblDatosDeLa_1 = new JLabel("Datos de la reserva");
		lblDatosDeLa_1.setFont(new Font("Verdana", Font.BOLD, 15));
		
		JLabel lblFechaInicio = new JLabel("Fecha Inicio");
		
		JLabel lblFechaFin = new JLabel("Fecha Fin");
		
		JLabel lblDetalle = new JLabel("Detalle");
		
		JScrollPane scrollPane = new JScrollPane();
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnAceptarClick();
				LimpiarControles();
				
			}

			
		});
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnSalirClick();
			}
		});
		
		cfechainicio = new JDateChooser();
		cfechainicio.setDateFormatString("yyyy-MM-dd");
		
		cfechafin = new JDateChooser();
		cfechafin.setDateFormatString("yyyy-MM-dd");

		
		JLabel lblFechaDeRegistro = new JLabel("Fecha de registro de la reserva");
		
		cfecharegistro = new JDateChooser();
		cfecharegistro.setDateFormatString("yyyy-MM-dd");
		
		
		JLabel lblId = new JLabel("ID");
		
		txtidpersona = new JTextField();
		txtidpersona.setEditable(false);
		txtidpersona.setColumns(10);
		
		JLabel lblId_1 = new JLabel("ID");
		
		txtidelemento = new JTextField();
		txtidelemento.setEditable(false);
		txtidelemento.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado");
		
		comboestado = new JComboBox();
		comboestado.setModel(new DefaultComboBoxModel(new String[] {"Activa", "Cancelada", "Terminada", "Sin devolver"}));
		
		txtcantmaxreservaspend = new JTextField();
		txtcantmaxreservaspend.setEnabled(false);
		txtcantmaxreservaspend.setEditable(false);
		txtcantmaxreservaspend.setColumns(10);
		txtcantmaxreservaspend.setVisible(false);
		
		txtnombreelemento = new JTextField();
		txtnombreelemento.setEditable(false);
		txtnombreelemento.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblFechaInicio)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblDetalle)
									.addComponent(lblFechaFin)))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(cfechafin, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(cfechainicio, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
									.addGap(101)
									.addComponent(lblEstado)
									.addGap(18)
									.addComponent(comboestado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnAceptar)
							.addGap(18)
							.addComponent(btnSalir)
							.addGap(103)
							.addComponent(lblFechaDeRegistro)
							.addGap(18)
							.addComponent(cfecharegistro, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblUsuario)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtusuario))
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNombre)
										.addComponent(lblId))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(txtnombrepersona, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtidpersona, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)))
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblApellido)
										.addComponent(lblDni))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(txtdni)
										.addComponent(txtapellido, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)))
								.addComponent(lblDatosDeLa, Alignment.LEADING)
								.addComponent(lblDatosDeLa_1, Alignment.LEADING))
							.addGap(10)
							.addComponent(btnBuscarPersona)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblDatosDelElemento)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblTipoDeElemento)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(txttipoelemento, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
												.addComponent(txtcantmaxreservaspend, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
									.addGap(112))
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblGenero, Alignment.LEADING)
									.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(lblNombre_1)
											.addComponent(lblId_1)
											.addComponent(lblAutor))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(txtidelemento, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
											.addComponent(txtnombreelemento, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
											.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
													.addComponent(txtgenero, Alignment.LEADING)
													.addComponent(txtautor, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE))
												.addGap(18)
												.addComponent(btnBuscarElemento))))))))
					.addGap(58))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDatosDeLa)
						.addComponent(lblDatosDelElemento))
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblId)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtidpersona, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblId_1)
							.addComponent(txtidelemento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(txtnombrepersona, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNombre_1)
						.addComponent(txtnombreelemento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(16)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblApellido)
										.addComponent(txtapellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnBuscarPersona, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblDni)
										.addComponent(txtdni, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblUsuario)
										.addComponent(txtusuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addComponent(lblDatosDeLa_1))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblAutor)
										.addComponent(txtautor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblGenero)
										.addComponent(txtgenero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblTipoDeElemento)
										.addComponent(txttipoelemento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtcantmaxreservaspend, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblFechaInicio)
										.addComponent(cfechainicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblFechaFin)
										.addComponent(cfechafin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblDetalle)))
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblEstado)
									.addComponent(comboestado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(btnAceptar)
									.addComponent(btnSalir))
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addComponent(cfecharegistro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblFechaDeRegistro))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(btnBuscarElemento, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(32, Short.MAX_VALUE))
		);
		
		txtdetalle = new JTextArea();
		scrollPane.setViewportView(txtdetalle);
		getContentPane().setLayout(groupLayout);
		setBounds(100, 100, 800, 493);

	}
	
	
	public void showAbmReservas(Reserva res,String modo){
		if (modo == "Editar") {
			this.MapearAform(res);
			btnAceptar.setText("Editar");
			HabilitarControles(true);
			this.btnBuscarElemento.setVisible(false);
			this.btnBuscarPersona.setVisible(false);
			
			
		}else {
			this.MapearAform(res);
			btnAceptar.setText("Eliminar");
			HabilitarControles(false);
		}
			
		
	}
	
	
	private void HabilitarControles(boolean val)
	{
		this.cfechafin.setEnabled(val);
		this.cfechainicio.setEnabled(val);
		this.cfecharegistro.setEnabled(val);
		this.txtdetalle.setEnabled(val);
		this.comboestado.setEnabled(val);
		this.btnBuscarPersona.setVisible(val);
		this.btnBuscarElemento.setVisible(val);
		
		
	}
	
	private void LimpiarControles(){
		
		
		this.cfechafin.setDefaultLocale(getLocale());
		this.cfechainicio.setDefaultLocale(getLocale());
		this.cfecharegistro.setDefaultLocale(getLocale());
		this.txtdetalle.setText("");
		this.comboestado.setSelectedIndex(-1);
		this.txtapellido.setText("");
		this.txtautor.setText("");
		this.txtdetalle.setText("");
		this.txtdni.setText("");
		this.txtgenero.setText("");
		this.txtidelemento.setText("");
		this.txtidpersona.setText("");
		this.txtnombreelemento.setText("");
		this.txtnombrepersona.setText("");
		this.txtusuario.setText("");
		this.txttipoelemento.setText("");
		
		
		
	}
	
	
	protected  void MapearAform(Reserva res){
		
		this.txtidelemento.setText(String.valueOf(res.getElemento().getId_elemento()));
		this.txtnombreelemento.setText(res.getElemento().getNombre());
		this.txtautor.setText(res.getElemento().getAutor());
		this.txtgenero.setText(res.getElemento().getGenero());
		this.txttipoelemento.setText(String.valueOf(res.getElemento().getTipo_Elemento().getId_tipoelemento()));
		this.txtcantmaxreservaspend.setText(String.valueOf(res.getElemento().getTipo_Elemento().getCantMaxReservasPend()));
		
		this.txtidpersona.setText(String.valueOf(res.getPersona().getId_persona()));
		this.txtnombrepersona.setText(res.getPersona().getNombre());
		this.txtapellido.setText(res.getPersona().getApellido());
		this.txtdni.setText(res.getPersona().getDni());
		this.txtusuario.setText(res.getPersona().getUsuario());
		
		this.cfechafin.setDate(res.getFecha_fin());
		this.cfechainicio.setDate(res.getFecha_inicio());
		this.cfecharegistro.setDate(res.getFecha_registro());
		this.txtdetalle.setText(res.getDetalle());
		this.comboestado.setSelectedItem(res.getEstado());

	}
	
			
	protected Reserva MapearDesdeform(){
		
		Reserva res = new Reserva();
		Elemento el = new Elemento();
		Persona per = new Persona();
		Tipo_Elemento te = new Tipo_Elemento();
		
		java.util.Date date = this.cfechainicio.getDate(); /*Esto se hace porque habia no dejaba guardar un tipo java.util.data en un java.sql.data, de esta forma se corrige */
		java.sql.Date sqlDate = new java.sql.Date(date.getTime()); 
		res.setFecha_inicio(sqlDate);
		
		date = this.cfechafin.getDate();
		sqlDate = new java.sql.Date(date.getTime());
		res.setFecha_fin(sqlDate);
		
		date=this.cfecharegistro.getDate();
		sqlDate = new java.sql.Date(date.getTime());
		res.setFecha_registro(sqlDate);
		
		res.setEstado(this.comboestado.getSelectedItem().toString());
		res.setDetalle(this.txtdetalle.getText());
		
		te.setId_tipoelemento(Integer.parseInt(this.txttipoelemento.getText()));
		te.setCantMaxReservasPend(Integer.parseInt(this.txtcantmaxreservaspend.getText()));
		
		el.setId_elemento(Integer.parseInt(this.txtidelemento.getText()));
		el.setAutor(this.txtautor.getText());
		el.setGenero(this.txtgenero.getText());
		el.setNombre(this.txtnombreelemento.getText());
		
		el.setTipo_Elemento(te);
		
		per.setId_persona(Integer.parseInt(this.txtidpersona.getText()));
		per.setNombre(this.txtnombrepersona.getText());
		per.setApellido(this.txtapellido.getText());
		per.setUsuario(this.txtusuario.getText());
		per.setDni(this.txtdni.getText());
		
		res.setElemento(el);
		res.setPersona(per);
		
		
		return res;
		
	}
	
	
	
	private void btnSalirClick() {
		
		
		frmreservas = new Reservas();
		this.getDesktopPane().add(frmreservas);
		frmreservas.setVisible(true);
		this.dispose();
		
		
	}
	
	
	private void showListadoPersonas() {
		
		ListadoPersonas frm = new ListadoPersonas();
		JDialog dialog = new JDialog();
		dialog.setContentPane(frm.getContentPane());
		dialog.setAlwaysOnTop(true);
		//dialog.setLocationRelativeTo(null);
		dialog.pack(); /* Le da un tamaño a la ventana */
		dialog.setModal(true);
		dialog.setVisible(true);
		
		showPersona(frm.persona);
	
		frm.dispose();
		
	}
	
	
	private void showListadoElementos() {
		ListadoElementos frm = new ListadoElementos();
		JDialog dialog = new JDialog();
		dialog.setContentPane(frm.getContentPane());
		dialog.setAlwaysOnTop( true );
		dialog.pack(); /* Le da un tamaño a la ventana */
		dialog.setModal(true);
		dialog.setVisible(true);
		

		
		showElemento(frm.el);
		
		
		
		
	}
	
	public void showPersona(Persona per){
		
		Reserva res = new Reserva();
		res.setPersona(per);
		
		
		this.txtidpersona.setText(String.valueOf(res.getPersona().getId_persona()));
		this.txtnombrepersona.setText(res.getPersona().getNombre());
		this.txtapellido.setText(res.getPersona().getApellido());
		this.txtdni.setText(res.getPersona().getDni());
		this.txtusuario.setText(res.getPersona().getUsuario());
	}
	
	public void showElemento(Elemento el){
		
		Reserva res = new Reserva();
		res.setElemento(el);
		
		this.txtidelemento.setText(String.valueOf(res.getElemento().getId_elemento()));
		this.txtnombreelemento.setText(res.getElemento().getNombre());
		this.txtautor.setText(res.getElemento().getAutor());
		this.txtgenero.setText(res.getElemento().getGenero());
		this.txttipoelemento.setText(String.valueOf(el.getTipo_Elemento().getId_tipoelemento()));
		this.txtcantmaxreservaspend.setText(String.valueOf(el.getTipo_Elemento().getCantMaxReservasPend()));
		
		
	}
	
	
	private void btnAceptarClick() {
		
		boolean validaCampos = this.validarVacios();
		if(validaCampos==false){
			
		Reserva res = this.MapearDesdeform();
		
		try{
			
			if (btnAceptar.getText() == "Aceptar" && reservaslogic.ValidarCantidadReservasPendientes(res)==true) {
				try{
					reservaslogic.add(res);
					
					JOptionPane.showMessageDialog(this, "Se ha registrado la reserva correctamente");
					frmreservas = new Reservas();
					this.getDesktopPane().add(frmreservas);
					frmreservas.setVisible(true);
					this.dispose();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this, e.getMessage());
				}
				
			}	else if (btnAceptar.getText() == "Aceptar" && reservaslogic.ValidarCantidadReservasPendientes(res)==false) {
					
				try{
					JOptionPane.showMessageDialog(this, "Ha superado la cantidad de reservas pendientes de ese tipo");
					frmreservas = new Reservas();
					this.getDesktopPane().add(frmreservas);
					frmreservas.setVisible(true);
					this.dispose();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this, e.getMessage());
				}
				
			}	else if (btnAceptar.getText() == "Editar") {
				try{
					reservaslogic.update(res);
					
					JOptionPane.showMessageDialog(this, "Se ha editado la reserva correctamente");
					frmreservas = new Reservas();
					this.getDesktopPane().add(frmreservas);
					frmreservas.setVisible(true);
					this.dispose();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this, e.getMessage());
				}
			} else{
				try{
					reservaslogic.delete(res);
					frmreservas = new Reservas();
					JOptionPane.showMessageDialog(this, "Se ha eliminado la reserva correctamente");
					this.getDesktopPane().add(frmreservas);
					frmreservas.setVisible(true);
					this.dispose();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this, e.getMessage());
				}
				
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		this.txtidelemento.setText(String.valueOf(res.getElemento().getId_elemento()));
		this.txtidpersona.setText(String.valueOf(res.getPersona().getId_persona()));
		
	
	} else {
		JOptionPane.showMessageDialog(this, "Hay campos obligatorios que no han sido completados");
		Reservas frmreservas = new Reservas();
		this.getDesktopPane().add(frmreservas);
		frmreservas.setVisible(true);
		this.dispose();
	}
}
	private  boolean validarVacios(){
	     boolean error=false;
	      //validando que no esten vacios los campos
	        if(this.txtidpersona.getText().isEmpty())
	           error= true;
	        if(this.txtidelemento.getText().isEmpty())
	           error= true;
	        if(this.cfechainicio.getDate()==null)
		           error= true;
	        if(this.cfechafin.getDate()==null)
		           error= true;
	        if(this.cfecharegistro.getDate()==null)
		           error= true;
	      return error;
	}
}