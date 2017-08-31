package entidades;

import java.sql.Date;
import java.sql.Time;

public class Reservas{

	private int id_persona;
	private int id_elemento;
	private Date fecha_registro;
	private String descripcion;
	private Date fecha_inicio;
	private Date fecha_fin;
	private String detalle;
	private String estado;
	
	public Date getFecha_inicio() {
		return fecha_inicio;
	}
	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}
	public Date getFecha_fin() {
		return fecha_fin;
	}
	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getId_persona() {
		return id_persona;
	}
	public void setId_persona(int id_persona) {
		this.id_persona = id_persona;
	}
	public int getId_elemento() {
		return id_elemento;
	}
	public void setId_elemento(int id_elemento) {
		this.id_elemento = id_elemento;
	}
	public Date getFecha_registro() {
		return fecha_registro;
	}
	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Reservas(int id_persona, int id_elemento, Date fecha_registro, String descripcion, Date fecha_inicio,
			Date fecha_fin, String detalle, String estado) {
		super();
		this.id_persona = id_persona;
		this.id_elemento = id_elemento;
		this.fecha_registro = fecha_registro;
		this.descripcion = descripcion;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.detalle = detalle;
		this.estado = estado;
	}
	
	public Reservas(){
		
	}
	
	
	
	
}
