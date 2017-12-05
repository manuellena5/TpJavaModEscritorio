package ui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import com.toedter.calendar.JDateChooser;

import entidades.Elemento;
import entidades.Persona;
import entidades.Reserva;
import entidades.Tipo_Elemento;
import negocio.ElementosLogic;
import negocio.PersonaLogic;
import negocio.ReservasLogic;
import negocio.Tipo_ElementosLogic;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.beans.PropertyChangeEvent;
import java.awt.Font;

public class ReservasAbm extends JInternalFrame {
	private JTextField txtnombrepersona;
	private JTextField txtapellido;
	private JTextField txtdni;
	private JTextField txtidpersona;
	private JTextField txtusuario;
	private JTextField txtnombretipoelemento;
	private JTextField txtcantreservaspendientes;
	private JTextField txtnombreelemento;
	private JTextField txtautor;
	private JTextField txtgenero;
	private JComboBox cbbuscarpersona;
	private PersonaLogic personalogic;
	private Tipo_ElementosLogic tipoelementoslogic;
	private JTextField txtidtipoelemento;
	private JComboBox cbbuscartipoelementos;
	private JDateChooser dcfecharegistro;
	private JDateChooser dcfechainicio;
	private JDateChooser dcfechafin;
	private JComboBox cbestado;
	private JTextArea txtdetalle;
	private JTextField txtidelemento;
	private JButton btnAceptar;
	private ReservasLogic reservaslogic;
	private Reservas frmreservas;
	private JLabel lblTipoDeElemento;
	private JLabel lblSeleccioneUnTipo;
	private JLabel lblIdtipoelemento;
	private JLabel lblNombretipoelemento;
	private JLabel lblCantMaxReservas;
	private JComboBox cbelementos;
	private ElementosLogic elementoslogic;
	private JRadioButton radiobuttonsi;
	private JRadioButton radiobuttonno;
	private JLabel lblestaSeguroQue;
	private JLabel lblElementos;
	private JLabel lblNombreelemento;
	private JLabel lblAutor;
	private JLabel lblGenero;
	private JLabel lblIdelemento;
	private JLabel lblElementotitulo;
	private JLabel lblpaso3;
	private JLabel lblpaso2;
	private JLabel lblpaso4;
	private Persona persona;

	/**
	 * Create the frame.
	 */
	public ReservasAbm() {
		setClosable(true);
		setBounds(100, 100, 801, 566);
		
		JLabel lblPersona = new JLabel("Persona (*PASO 1)");
		lblPersona.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		personalogic = new PersonaLogic();
		tipoelementoslogic = new Tipo_ElementosLogic();
		reservaslogic = new ReservasLogic();
		elementoslogic = new ElementosLogic();
		
		JLabel lblNombre = new JLabel("Nombre");
		
		txtnombrepersona = new JTextField();
		txtnombrepersona.setEditable(false);
		txtnombrepersona.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido");
		
		txtapellido = new JTextField();
		txtapellido.setEditable(false);
		txtapellido.setColumns(10);
		
		JLabel lblDni = new JLabel("Dni");
		
		txtdni = new JTextField();
		txtdni.setEditable(false);
		txtdni.setColumns(10);
		
		JLabel lblId = new JLabel("ID");
		
		txtidpersona = new JTextField();
		txtidpersona.setEditable(false);
		txtidpersona.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario");
		
		txtusuario = new JTextField();
		txtusuario.setEditable(false);
		txtusuario.setColumns(10);
		
		cbbuscarpersona = new JComboBox();
		cbbuscarpersona.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				Persona per = new Persona();
				Reserva res = new Reserva();
				
				if (event.getStateChange() == ItemEvent.SELECTED) {
			          Object item = event.getItem();
			          
			          try {
						per = personalogic.GetByDni((Persona) item);
						
						  txtapellido.setText(per.getApellido());
				          txtnombrepersona.setText(per.getNombre());
				          txtdni.setText(per.getDni());
				          txtusuario.setText(per.getUsuario());
				          txtidpersona.setText(String.valueOf(per.getId_persona()));
				          limpiarcontrolestipoelementos();
				          habilitarcontrolestipoelemento();
				          lblpaso2.setVisible(true);
						
					} catch (Exception e) {
						JOptionPane.showMessageDialog(cbbuscarpersona, e.getMessage());
					}
			          res.setPersona(per);
			          
			          
			       
			          
			       }
			}
		});
		
		JLabel lblSeleccioneUnaPersona = new JLabel("Seleccione una persona");
		
		lblTipoDeElemento = new JLabel("Tipo de elemento ");
		lblTipoDeElemento.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTipoDeElemento.setVisible(false);
		
		lblSeleccioneUnTipo = new JLabel("Seleccione un tipo de elemento a reservar:");
		lblSeleccioneUnTipo.setVisible(false);
		
		cbbuscartipoelementos = new JComboBox();
		cbbuscartipoelementos.setVisible(false);
		cbbuscartipoelementos.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				Tipo_Elemento te = new Tipo_Elemento();
				Reserva res = new Reserva();
				boolean val;
				
				
				
				if (event.getStateChange() == ItemEvent.SELECTED) {
			          Object item = event.getItem();
			          
			          try {
			        	  int idpersona = Integer.parseInt(txtidpersona.getText());
			        	  if (idpersona != 0) {
			        		  te = tipoelementoslogic.GetByNombre((Tipo_Elemento) item);
								val = tipoelementoslogic.ValidarCantidadReservasPendientes(idpersona, te);
								
								if (val) {
									 txtnombretipoelemento.setText(te.getNombre());
							         txtidtipoelemento.setText(String.valueOf(te.getId_tipoelemento()));
							         txtcantreservaspendientes.setText(String.valueOf(te.getCantMaxReservasPend()));
							         lblpaso3.setVisible(true);
								}else{
									JOptionPane.showMessageDialog(cbbuscartipoelementos,"Ha superado el limite de pendientes de devolver de este tipo");
									limpiarcontrolestipoelementos();
								}
						}else{
							JOptionPane.showMessageDialog(cbbuscarpersona,"debe seleccionar una persona primero");
						}
						
						
						
							} catch (Exception e) {
						JOptionPane.showMessageDialog(cbbuscarpersona, e.getMessage());
						System.out.println(e);
						
					}
			          
			        
			         
			         			       
			          
			       }
			}
		});
		
		lblNombretipoelemento = new JLabel("Nombre");
		lblNombretipoelemento.setVisible(false);
		
		txtnombretipoelemento = new JTextField();
		txtnombretipoelemento.setEditable(false);
		txtnombretipoelemento.setColumns(10);
		txtnombretipoelemento.setVisible(false);
		
		lblCantMaxReservas = new JLabel("Cant Max reservas pendientes");
		lblCantMaxReservas.setVisible(false);
		
		txtcantreservaspendientes = new JTextField();
		txtcantreservaspendientes.setEditable(false);
		txtcantreservaspendientes.setColumns(10);
		txtcantreservaspendientes.setVisible(false);
		
		JLabel lblSeleccioneLasFechas = new JLabel("Seleccione las fechas de reserva");
		lblSeleccioneLasFechas.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		dcfecharegistro = new JDateChooser("yyyy/MM/dd", "####/##/##", '_');
		dcfecharegistro.setDateFormatString("yyyy/MM/dd");
		
		
		dcfechainicio = new JDateChooser("yyyy/MM/dd", "####/##/##", '_');
		dcfechainicio.setDateFormatString("yyyy/MM/dd");
		
		dcfechafin = new JDateChooser("yyyy/MM/dd", "####/##/##", '_');
		dcfechafin.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent event) {
				
				
				
			}
		});
		dcfechafin.setDateFormatString("yyyy/MM/dd");
		
		JLabel lblFechaInicio = new JLabel("Fecha de registro");
		
		JLabel lblFechaDeInicio = new JLabel("Fecha de inicio");
		
		JLabel lblFechaDeFin = new JLabel("Fecha de fin");
		
		JLabel lblEstado = new JLabel("Estado");
		
		cbestado = new JComboBox();
		cbestado.setModel(new DefaultComboBoxModel(new String[] {"Activa", "Cancelada", "Terminada", "Sin devolver"}));
		
		JLabel lblDetalle = new JLabel("Detalle");
		
		JScrollPane scrollPane = new JScrollPane();
		
		lblElementos = new JLabel("Seleccione un elemento");
		lblElementos.setVisible(false);
		
		lblNombreelemento = new JLabel("Nombre");
		lblNombreelemento.setVisible(false);
		
		
		txtnombreelemento = new JTextField();
		txtnombreelemento.setEditable(false);
		txtnombreelemento.setColumns(10);
		txtnombreelemento.setVisible(false);
		
		lblAutor = new JLabel("Autor");
		lblAutor.setVisible(false);
		
		txtautor = new JTextField();
		txtautor.setEditable(false);
		txtautor.setColumns(10);
		txtautor.setVisible(false);
		
		txtgenero = new JTextField();
		txtgenero.setEditable(false);
		txtgenero.setColumns(10);
		txtgenero.setVisible(false);
		
		lblGenero = new JLabel("Genero");
		lblGenero.setVisible(false);
		
		txtidtipoelemento = new JTextField();
		txtidtipoelemento.setEditable(false);
		txtidtipoelemento.setColumns(10);
		txtidtipoelemento.setVisible(false);
		
		lblIdtipoelemento = new JLabel("ID");
		lblIdtipoelemento.setVisible(false);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnAceptarClick();
			}
		});
		
		lblIdelemento = new JLabel("ID");
		lblIdelemento.setVisible(false);
		
		txtidelemento = new JTextField();
		txtidelemento.setEditable(false);
		txtidelemento.setColumns(10);
		txtidelemento.setVisible(false);
		
		cbelementos = new JComboBox();
		cbelementos.setVisible(false);
		cbelementos.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				Elemento ele = new Elemento();
				
				if (event.getStateChange() == ItemEvent.SELECTED) {
			          Object item = event.getItem();
			          
			          try {
						ele = elementoslogic.GetByNombre((Elemento) item);
						
							} catch (Exception e) {
						JOptionPane.showMessageDialog(cbelementos, e.getMessage());
					}
			          
			         txtnombreelemento.setText(ele.getNombre());
			         txtidelemento.setText(String.valueOf(ele.getId_elemento()));
			         txtgenero.setText(ele.getGenero());
			         txtautor.setText(ele.getAutor());
			     
			         			       
			          
			       }
			}
		});
		 
		
		radiobuttonsi = new JRadioButton("SI");
		radiobuttonsi.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				
				
				try {
					boolean val = validarfechas();
					if (val) {
						habilitarcontroleselementos(true,"Alta");
						habilitarcontrolesdatosreserva(false);
						lblpaso4.setVisible(true);
					}
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(rootPane, e.getMessage());
				}
				
			}
		});
		
		radiobuttonno = new JRadioButton("NO");
		radiobuttonno.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				try {
					habilitarcontroleselementos(false,"Alta");
					habilitarcontrolesdatosreserva(true);
					lblpaso4.setVisible(false);
				} catch (Exception e) {

					JOptionPane.showMessageDialog(rootPane, e.getMessage());
				}
				
			}
		});
		
		ButtonGroup group = new ButtonGroup();
	    group.add(radiobuttonsi);
	    group.add(radiobuttonno);
		
	
		lblestaSeguroQue = new JLabel("\u00BFEsta seguro que desea reservar para esas fechas?");
		lblestaSeguroQue.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnCancelarClick();
			}
		});
		
		lblElementotitulo = new JLabel("Elemento");
		lblElementotitulo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblElementotitulo.setVisible(false);
		
		lblpaso3 = new JLabel("(*PASO 3)");
		lblpaso3.setVisible(false);
		lblpaso3.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		lblpaso2 = new JLabel("(*PASO 2)");
		lblpaso2.setVisible(false);
		lblpaso2.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		lblpaso4 = new JLabel(" (*PASO 4)");
		lblpaso4.setVisible(false);
		lblpaso4.setFont(new Font("Tahoma", Font.BOLD, 13));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnAceptar, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
							.addGap(35)
							.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPersona)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblSeleccioneUnaPersona)
										.addComponent(lblId)
										.addComponent(lblNombre)
										.addComponent(lblApellido)
										.addComponent(lblDni)
										.addComponent(lblUsuario))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(txtusuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtdni, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtapellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtnombrepersona, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtidpersona, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
										.addComponent(cbbuscarpersona, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblSeleccioneLasFechas)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblpaso3))
								.addComponent(lblFechaDeFin)
								.addComponent(lblDetalle)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblFechaInicio)
										.addComponent(lblFechaDeInicio)
										.addComponent(lblEstado))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(dcfecharegistro, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
										.addComponent(dcfechainicio, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(3)
											.addComponent(cbestado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
												.addComponent(dcfechafin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(47)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
													.addGroup(groupLayout.createSequentialGroup()
														.addGap(0)
														.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
															.addGroup(groupLayout.createSequentialGroup()
																.addGap(12)
																.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																	.addGroup(groupLayout.createSequentialGroup()
																		.addComponent(lblSeleccioneUnTipo, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(ComponentPlacement.RELATED)
																		.addComponent(cbbuscartipoelementos, 0, 117, Short.MAX_VALUE))
																	.addGroup(groupLayout.createSequentialGroup()
																		.addComponent(lblTipoDeElemento)
																		.addPreferredGap(ComponentPlacement.RELATED)
																		.addComponent(lblpaso2))
																	.addGroup(groupLayout.createSequentialGroup()
																		.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
																			.addComponent(lblCantMaxReservas)
																			.addComponent(lblNombretipoelemento)
																			.addComponent(lblIdtipoelemento))
																		.addGap(18)
																		.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																			.addComponent(txtidtipoelemento, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
																			.addComponent(txtnombretipoelemento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																			.addComponent(txtcantreservaspendientes, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)))))
															.addGroup(groupLayout.createSequentialGroup()
																.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
																	.addGroup(groupLayout.createSequentialGroup()
																		.addGap(18)
																		.addComponent(lblIdelemento)
																		.addGap(18)
																		.addComponent(txtidelemento, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
																	.addGroup(groupLayout.createSequentialGroup()
																		.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
																			.addComponent(lblNombreelemento)
																			.addComponent(lblGenero)
																			.addComponent(lblAutor))
																		.addPreferredGap(ComponentPlacement.UNRELATED)
																		.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																			.addComponent(txtgenero)
																			.addComponent(txtautor, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
																			.addComponent(txtnombreelemento, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))))
																.addPreferredGap(ComponentPlacement.RELATED))))
													.addGroup(groupLayout.createSequentialGroup()
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(lblestaSeguroQue)))
												.addGap(84))
											.addGroup(groupLayout.createSequentialGroup()
												.addGap(3)
												.addComponent(radiobuttonsi)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(radiobuttonno)
												.addContainerGap())))
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(174)
										.addComponent(cbelementos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addContainerGap()))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(20)
									.addComponent(lblElementotitulo)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblpaso4)
									.addGap(346))))))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(333)
					.addComponent(lblElementos)
					.addContainerGap(340, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPersona)
						.addComponent(lblTipoDeElemento)
						.addComponent(lblpaso2))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSeleccioneUnaPersona)
						.addComponent(cbbuscarpersona, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSeleccioneUnTipo)
						.addComponent(cbbuscartipoelementos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblId)
						.addComponent(txtidpersona, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtidtipoelemento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblIdtipoelemento))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(txtnombrepersona, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtnombretipoelemento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNombretipoelemento))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblApellido)
						.addComponent(txtapellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtcantreservaspendientes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCantMaxReservas))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblDni)
								.addComponent(txtdni, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblUsuario)
								.addComponent(txtusuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSeleccioneLasFechas)
								.addComponent(lblpaso3)))
						.addComponent(lblestaSeguroQue))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(radiobuttonno)
								.addComponent(radiobuttonsi))
							.addPreferredGap(ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblElementotitulo)
								.addComponent(lblpaso4))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblElementos)
								.addComponent(cbelementos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtidelemento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblIdelemento))
							.addGap(17)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtnombreelemento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNombreelemento))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblGenero)
								.addComponent(txtgenero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblAutor)
								.addComponent(txtautor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(12)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblFechaInicio)
								.addComponent(dcfecharegistro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(dcfechainicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblFechaDeInicio))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblFechaDeFin)
								.addComponent(dcfechafin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(13)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(cbestado, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEstado, Alignment.TRAILING))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDetalle))))
					.addGap(9)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnCancelar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnAceptar, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
					.addGap(4))
		);
		
		txtdetalle = new JTextArea();
		scrollPane.setViewportView(txtdetalle);
		getContentPane().setLayout(groupLayout);
		
		cargarlistadopersonas();
		validaringreso(Login.id);
		

	}

	private void cargarlistadopersonas() {
		try {
			
			this.cbbuscarpersona.setModel(new DefaultComboBoxModel(personalogic.GetAll().toArray()));
			this.cbbuscarpersona.setSelectedIndex(-1);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		
	}
	
	
	public void showAbmReservas(Reserva res,String modo) throws Exception{
		if (modo.equals("Editar")) {
			this.MapearAform(res);
			//habilitarcontrolesdatosreserva(true);
			habilitarcontroleselementos(true,modo);
			habilitarcontrolestipoelemento();
			habilitarradiobutton(false);
			btnAceptar.setText("Editar");
			HabilitarControles(true);
			
			
		}else  {
			
			this.MapearAform(res);
			habilitarcontrolesdatosreserva(true);
			habilitarcontroleselementos(true,modo);
			habilitarcontrolestipoelemento();
			habilitarradiobutton(false);
			btnAceptar.setText("Cancelar");
			HabilitarControles(true);
			this.cbestado.setEnabled(false);
		}
			
		
	}
	
	
	private  void MapearAform(Reserva res){
		
		this.txtidpersona.setText(String.valueOf(res.getPersona().getId_persona()));
		this.txtnombrepersona.setText(res.getPersona().getNombre());
		this.txtapellido.setText(res.getPersona().getApellido());
		this.txtdni.setText(res.getPersona().getDni());
		this.txtusuario.setText(res.getPersona().getUsuario());
		
		this.txtidtipoelemento.setText(String.valueOf(res.getElemento().getTipo_Elemento().getId_tipoelemento()));
		this.txtcantreservaspendientes.setText(String.valueOf(res.getElemento().getTipo_Elemento().getCantMaxReservasPend()));
		this.txtnombretipoelemento.setText(res.getElemento().getTipo_Elemento().getNombre());
		
		this.dcfechafin.setDate(res.getFecha_fin());
		this.dcfechainicio.setDate(res.getFecha_inicio());
		this.dcfecharegistro.setDate(res.getFecha_registro());
		this.txtdetalle.setText(res.getDetalle());
		this.cbestado.setSelectedItem(res.getEstado());
		
		this.txtidelemento.setText(String.valueOf(res.getElemento().getId_elemento()));
		this.txtnombreelemento.setText(res.getElemento().getNombre());
		this.txtautor.setText(res.getElemento().getAutor());
		this.txtgenero.setText(res.getElemento().getGenero());
		
		
	}
	
	private void HabilitarControles(boolean val){
		
		this.cbestado.setEnabled(val);
		this.cbbuscarpersona.setVisible(!val);
		this.cbbuscartipoelementos.setVisible(!val);
		this.cbelementos.setVisible(!val);
		this.dcfechafin.setEnabled(!val);
		this.dcfechainicio.setEnabled(!val);
		this.dcfecharegistro.setEnabled(!val);
		this.txtdetalle.setEnabled(!val);
		
	}
	
	
	
	private void cargarlistadotipoelementos(){
		try {
			
			this.cbbuscartipoelementos.setModel(new DefaultComboBoxModel(tipoelementoslogic.GetAll().toArray()));
			this.cbbuscartipoelementos.setSelectedIndex(-1);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	
	private Reserva MapearDesdeform(){
		
		Reserva res = new Reserva();
		Elemento el = new Elemento();
		Persona per = new Persona();
		Tipo_Elemento te = new Tipo_Elemento();
		
		java.util.Date date = this.dcfechainicio.getDate(); /*Esto se hace porque no dejaba guardar un tipo java.util.data en un java.sql.data, de esta forma se corrige */
		java.sql.Date sqlDate = new java.sql.Date(date.getTime()); 
		res.setFecha_inicio(sqlDate);
		
		date = this.dcfechafin.getDate();
		sqlDate = new java.sql.Date(date.getTime());
		res.setFecha_fin(sqlDate);
		
		date=this.dcfecharegistro.getDate();
		sqlDate = new java.sql.Date(date.getTime());
		res.setFecha_registro(sqlDate);
		
		res.setEstado(this.cbestado.getSelectedItem().toString());
		res.setDetalle(this.txtdetalle.getText());
		
		te.setId_tipoelemento(Integer.parseInt(this.txtidtipoelemento.getText()));
		te.setCantMaxReservasPend(Integer.parseInt(this.txtcantreservaspendientes.getText()));
		
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
	
	
	private void btnAceptarClick() {
		Reserva res = this.MapearDesdeform();
		
		
		try{
			if (btnAceptar.getText().equals("Registrar")) {
				
				try {
					reservaslogic.add(res);
					JOptionPane.showMessageDialog(this, "Se ha registrado la reserva correctamente");
					this.dispose();
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this, "Ha ocurrido un error inesperado");
					System.out.println(e.getMessage());
				}
				
			}
			else if (btnAceptar.getText().equals("Aceptar") && reservaslogic.ValidarCantidadReservasPendientes(res)==true) {
				try{
					reservaslogic.add(res);
					
					JOptionPane.showMessageDialog(this, "Se ha registrado la reserva correctamente");
					frmreservas = new Reservas();
					this.getDesktopPane().add(frmreservas);
					frmreservas.setVisible(true);
					this.dispose();
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this, "Ha ocurrido un error inesperado");
					System.out.println(e.getMessage());
				}
				
			}	else if (btnAceptar.getText().equals("Aceptar") && reservaslogic.ValidarCantidadReservasPendientes(res)==false) {
					
				try{
					JOptionPane.showMessageDialog(this, "Ha superado la cantidad de reservas pendientes de ese tipo");
					frmreservas = new Reservas();
					this.getDesktopPane().add(frmreservas);
					frmreservas.setVisible(true);
					this.dispose();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this, "Ha ocurrido un error inesperado");
					System.out.println(e.getMessage());
				}
				
			}	else if (btnAceptar.getText().equals("Editar")) {
				try{
					reservaslogic.update(res);
					
					JOptionPane.showMessageDialog(this, "Se ha editado la reserva correctamente");
					frmreservas = new Reservas();
					this.getDesktopPane().add(frmreservas);
					frmreservas.setVisible(true);
					this.dispose();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this, "Ha ocurrido un error inesperado");
					System.out.println(e.getMessage());
				}
			} else if (btnAceptar.getText().equals("Cancelar")) {
				
		
				try{
					
					
					reservaslogic.update(res);
					JOptionPane.showMessageDialog(this, "Se ha cancelado la reserva correctamente");
					
					ListadoReservasUsuario frm = new ListadoReservasUsuario();
					this.getDesktopPane().add(frm);
					frm.setVisible(true);
					this.dispose();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this, "Ha ocurrido un error inesperado");
					System.out.println(e.getMessage());
				}
				
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Ha ocurrido un error inesperado");
			System.out.println(e.getMessage());
		}
		this.txtidelemento.setText(String.valueOf(res.getElemento().getId_elemento()));
		this.txtidpersona.setText(String.valueOf(res.getPersona().getId_persona()));
		
	}
	
	public void showElemento(Elemento el){
		
		Reserva res = new Reserva();
		res.setElemento(el);
		
		this.txtidelemento.setText(String.valueOf(res.getElemento().getId_elemento()));
		this.txtnombreelemento.setText(res.getElemento().getNombre());
		this.txtautor.setText(res.getElemento().getAutor());
		this.txtgenero.setText(res.getElemento().getGenero());
		this.txtidtipoelemento.setText(String.valueOf(el.getTipo_Elemento().getId_tipoelemento()));
		this.txtcantreservaspendientes.setText(String.valueOf(el.getTipo_Elemento().getCantMaxReservasPend()));
		
		
	}
	
	private void habilitarcontrolestipoelemento() throws Exception{
		
		
			cargarlistadotipoelementos();
			
			
			this.lblTipoDeElemento.setVisible(true);
			this.lblSeleccioneUnTipo.setVisible(true);
			this.cbbuscartipoelementos.setVisible(true);
			this.lblNombretipoelemento.setVisible(true);
			this.lblIdtipoelemento.setVisible(true);
			this.lblCantMaxReservas.setVisible(true);
			this.txtidtipoelemento.setVisible(true);
			this.txtnombretipoelemento.setVisible(true);
			this.txtcantreservaspendientes.setVisible(true);
		
		
		
		
		
		
	}
	
	private void cargarlistadoelementos(ArrayList<Elemento> listado){
		
		try {
			
			this.cbelementos.setModel(new DefaultComboBoxModel(listado.toArray()));
			this.cbelementos.setSelectedIndex(-1);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		
		
	}
	
	
	private void habilitarcontroleselementos(boolean val,String modo) throws Exception{
		
		if(modo.equals("Editar") || modo.equals("Cancelar")){
			
			this.lblAutor.setVisible(val);
			this.lblGenero.setVisible(val);
			this.lblIdelemento.setVisible(val);
			this.lblElementos.setVisible(val);
			this.lblElementotitulo.setVisible(val);
			this.lblNombreelemento.setVisible(val);
			this.cbelementos.setVisible(val);
			this.txtnombreelemento.setVisible(val);
			this.txtidelemento.setVisible(val);
			this.txtautor.setVisible(val);
			this.txtgenero.setVisible(val);
			
		}else
		{if (val) {
			
			limpiarcontroleselementos();
			
			ArrayList<Elemento> listadoelementos = new ArrayList<Elemento>();
			
			java.util.Date date = this.dcfechainicio.getDate(); /*Esto se hace porque no dejaba guardar un tipo java.util.data en un java.sql.data, de esta forma se corrige */
			java.sql.Date fechainicio = new java.sql.Date(date.getTime()); 
			
			date = this.dcfechafin.getDate();
			java.sql.Date fechafin = new java.sql.Date(date.getTime());

			
			date=this.dcfecharegistro.getDate();
			java.sql.Date fecharegistro = new java.sql.Date(date.getTime());

			int idtipoelementos = Integer.parseInt((this.txtidtipoelemento.getText()));
			int idpersona = Integer.parseInt((this.txtidpersona.getText()));
			
			if (fechainicio != null && fecharegistro != null && fechafin != null && idtipoelementos>0) {
				listadoelementos = reservaslogic.getElementosSinReserva(fechainicio, fechafin, fecharegistro, idtipoelementos,idpersona);
				
				if (listadoelementos.isEmpty()) {
					JOptionPane.showMessageDialog(this, "No hay elementos disponibles para esas fechas");
				}else{
					cargarlistadoelementos(listadoelementos);
					this.lblAutor.setVisible(val);
					this.lblGenero.setVisible(val);
					this.lblIdelemento.setVisible(val);
					this.lblElementotitulo.setVisible(val);
					this.lblElementos.setVisible(val);
					this.lblNombreelemento.setVisible(val);
					this.cbelementos.setVisible(val);
					this.txtnombreelemento.setVisible(val);
					this.txtidelemento.setVisible(val);
					this.txtautor.setVisible(val);
					this.txtgenero.setVisible(val);
				}}else{
					JOptionPane.showMessageDialog(this, "Debe seleccionar las fechas y el tipo de elemento");
					
				}
				
			}else{
			this.lblAutor.setVisible(val);
			this.lblGenero.setVisible(val);
			this.lblIdelemento.setVisible(val);
			this.lblElementos.setVisible(val);
			this.lblElementotitulo.setVisible(val);
			this.lblNombreelemento.setVisible(val);
			this.cbelementos.setVisible(val);
			this.txtnombreelemento.setVisible(val);
			this.txtidelemento.setVisible(val);
			this.txtautor.setVisible(val);
			this.txtgenero.setVisible(val);
			}
		}
		}
			
	private void limpiarcontroleselementos(){
			
		this.cbelementos.setSelectedIndex(-1);
		this.txtnombreelemento.setText("");
		this.txtidelemento.setText("");
		this.txtautor.setText("");
		this.txtgenero.setText("");
	}
			
			
		
		
		
	
	
	private void habilitarcontrolesdatosreserva(boolean val){
		
		this.dcfecharegistro.setEnabled(val);
		this.dcfechainicio.setEnabled(val);
		this.dcfechafin.setEnabled(val);
		this.txtdetalle.setEnabled(val);
		this.txtdetalle.setEditable(val);
		this.cbestado.setEnabled(val);
		
	}
	
	private void limpiarcontrolestipoelementos(){
		
		this.txtcantreservaspendientes.setText("");
		this.txtnombretipoelemento.setText("");
		this.txtidtipoelemento.setText("");
	}
	
	private void habilitarradiobutton(boolean val){
		
		this.lblestaSeguroQue.setVisible(val);
		this.radiobuttonno.setVisible(val);
		this.radiobuttonsi.setVisible(val);
		
	}
	
	public boolean validarfechas(){
		
		java.util.Date date = this.dcfechainicio.getDate(); /*Esto se hace porque no dejaba guardar un tipo java.util.data en un java.sql.data, de esta forma se corrige */
		java.sql.Date fechainicio = new java.sql.Date(date.getTime()); 
		
		date = this.dcfechafin.getDate();
		java.sql.Date fechafin = new java.sql.Date(date.getTime());

		
		date=this.dcfecharegistro.getDate();
		java.sql.Date fecharegistro = new java.sql.Date(date.getTime());
		
		if (fecharegistro.after(fechainicio)) {
			JOptionPane.showMessageDialog(this, "La fecha de registro no puede ser mayor a la fecha de inicio");
			
			radiobuttonno.setSelected(true);
			return false;
			
		}else if(fechainicio.after(fechafin)){
			
			JOptionPane.showMessageDialog(this, "La fecha de inicio no puede ser mayor a la fecha de fin");
			
			radiobuttonno.setSelected(true);
			return false;
		}
		return true;
		
		
	}
	
	
	private void btnCancelarClick(){
		frmreservas = new Reservas();
		this.getDesktopPane().add(frmreservas);
		frmreservas.setVisible(true);
		this.dispose();
	}
	
	private void validaringreso(int id){
		
		try {
			persona = new Persona();
			
			
			persona = personalogic.GetById(id);
			
			String categoria =  persona.getCategoria().getDescripcion();
			
			if (categoria.equals("Usuario")) {
				
				mapearPersona(persona);
				limpiarcontrolestipoelementos();
		        habilitarcontrolestipoelemento();
		        lblpaso2.setVisible(true);
							
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(this, "Ha ocurrido un error inesperado");
			
		}
		
	}
	
	private void mapearPersona(Persona per){
		
		this.txtidpersona.setText(String.valueOf(per.getId_persona()));
		this.txtnombrepersona.setText(per.getNombre());
		this.txtapellido.setText(per.getApellido());
		this.txtdni.setText(per.getDni());
		this.txtusuario.setText(per.getUsuario());
		this.cbbuscarpersona.setVisible(false);
		this.btnAceptar.setText("Registrar");
		
	}
	
	
	
	
	
}
