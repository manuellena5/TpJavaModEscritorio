package ui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import entidades.Categoria;
import entidades.Elemento;
import entidades.Persona;
import entidades.Tipo_Elemento;
import negocio.ElementosLogic;
import negocio.Tipo_ElementosLogic;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AbmTipoElementos extends JInternalFrame {
	private JTextField txtid;
	private JTextField txtnombre;
	private JTextField txtcantmaxpend;
	private Tipo_ElementosLogic tel;
	private JButton btnAceptar;

	public AbmTipoElementos() {
		
		tel = new Tipo_ElementosLogic();
		
		setClosable(true);
		setTitle("Tipos de elementos");
		setBounds(100, 100, 450, 300);
		
		JLabel lblId = new JLabel("Id");
		
		txtid = new JTextField();
		txtid.setEditable(false);
		txtid.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Tipo de elemento");
		
		txtnombre = new JTextField();
		txtnombre.setColumns(10);
		
		JLabel lblCantidadMaximaDe = new JLabel("Limite de pendientes");
		
		txtcantmaxpend = new JTextField();
		txtcantmaxpend.setColumns(10);
		
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
			btnSalirClick();}

			
		});
		
		JLabel label = new JLabel("");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(label))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(39)
								.addComponent(btnAceptar))
							.addGroup(groupLayout.createSequentialGroup()
								.addContainerGap()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(lblCantidadMaximaDe, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(8)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
											.addComponent(lblId)
											.addComponent(lblNewLabel))))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(txtid, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
									.addComponent(txtnombre, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnSalir)
										.addComponent(txtcantmaxpend, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))))
					.addContainerGap(211, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(49)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblId))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(txtnombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCantidadMaximaDe, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtcantmaxpend, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAceptar)
						.addComponent(btnSalir))
					.addGap(28))
		);
		getContentPane().setLayout(groupLayout);
		
	}
	
	
	private void btnAceptarClick() {
		
		Tipo_Elemento te = this.MapearDesdeform();
		try{
			if (btnAceptar.getText() == "Aceptar") {
				tel.add(te);
				JOptionPane.showMessageDialog(this, "Se ha registrado correctamente");
				Tipo_Elementos frm = new Tipo_Elementos();
				this.getDesktopPane().add(frm);
				frm.setVisible(true);
				this.dispose();
			} else if (btnAceptar.getText() == "Editar") {
				tel.update(te);
				JOptionPane.showMessageDialog(this, "Se ha editado correctamente");
				Tipo_Elementos frm = new Tipo_Elementos();
				this.getDesktopPane().add(frm);
				frm.setVisible(true);
				this.dispose();
			} else{
				
				tel.delete(te);
				JOptionPane.showMessageDialog(this, "Se ha eliminado correctamente");
				Tipo_Elementos frm = new Tipo_Elementos();
				this.getDesktopPane().add(frm);
				frm.setVisible(true);
				this.dispose();
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		this.txtid.setText(String.valueOf(te.getId_tipoelemento()));
		
	}
	
	
	protected void MapearAform(Tipo_Elemento te){
		
		this.txtnombre.setText(te.getNombre());
		this.txtcantmaxpend.setText(String.valueOf(te.getCantMaxReservasPend()));
		this.txtid.setText(String.valueOf(te.getId_tipoelemento()));

	}
	
	protected Tipo_Elemento MapearDesdeform(){
		
		Tipo_Elemento te = new Tipo_Elemento();
		
		if(!this.txtid.getText().isEmpty()){
			te.setId_tipoelemento(Integer.parseInt(this.txtid.getText()));
		}
		te.setNombre(this.txtnombre.getText());
		te.setCantMaxReservasPend(Integer.parseInt(this.txtcantmaxpend.getText()));
		
		
		return te;
		
	}
	
	private void LimpiarControles(){
		this.txtid.setText("");
		this.txtnombre.setText("");
		this.txtcantmaxpend.setText("");
		
	}
	
	private void HabilitarControles(boolean val)
	{
		this.txtnombre.setEditable(val);
		this.txtcantmaxpend.setEditable(val);
	}
	
	public void showAbmTipoElementos(Tipo_Elemento tipoele,String modo){
		if (modo == "Editar") {
			this.MapearAform(tipoele);
			btnAceptar.setText("Editar");
			HabilitarControles(true);
			
		}else {
			this.MapearAform(tipoele);
			btnAceptar.setText("Eliminar");
			HabilitarControles(false);
		}
		
	}
	
	private void btnSalirClick() {
		Tipo_Elementos frm = new Tipo_Elementos();
		this.getDesktopPane().add(frm);
		frm.setVisible(true);
		this.dispose();
		
	}
	
	
}
