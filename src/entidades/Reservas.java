package entidades;

import java.sql.Date;
import java.sql.Time;

public class Reservas {

	private int id_persona;
	private int id_clase;
	private Date fecha;
	private Time hora;
	private String descripcion;
	
	public int getId_persona() {
		return id_persona;
	}
	public void setId_persona(int id_persona) {
		this.id_persona = id_persona;
	}
	public int getId_clase() {
		return id_clase;
	}
	public void setId_clase(int id_clase) {
		this.id_clase = id_clase;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Time getHora() {
		return hora;
	}
	public void setHora(Time hora) {
		this.hora = hora;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
