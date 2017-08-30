package entidades;
import java.sql.Time;

public class Turnos {

	private int id_turno;
	private int numero_turno;
	private Time hora_desde;
	private Time hora_hasta;
	private int id_clase;
	
	public int getId_turno() {
		return id_turno;
	}
	public void setId_turno(int id_turno) {
		this.id_turno = id_turno;
	}
	public int getNumero_turno() {
		return numero_turno;
	}
	public void setNumero_turno(int numero_turno) {
		this.numero_turno = numero_turno;
	}
	public Time getHora_desde() {
		return hora_desde;
	}
	public void setHora_desde(Time hora_desde) {
		this.hora_desde = hora_desde;
	}
	public Time getHora_hasta() {
		return hora_hasta;
	}
	public void setHora_hasta(Time hora_hasta) {
		this.hora_hasta = hora_hasta;
	}
	public int getId_clase() {
		return id_clase;
	}
	public void setId_clase(int id_clase) {
		this.id_clase = id_clase;
	}
}
