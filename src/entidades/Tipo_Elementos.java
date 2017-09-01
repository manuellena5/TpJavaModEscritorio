package entidades;

public class Tipo_Elementos {

	private int id_tipoelemento;
	private String nombre;
	private int cantMaxReservasPend;
	
	public int getId_tipoelemento() {
		return id_tipoelemento;
	}
	public void setId_tipoelemento(int id_tipoelemento) {
		this.id_tipoelemento = id_tipoelemento;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCantMaxReservasPend() {
		return cantMaxReservasPend;
	}
	public void setCantMaxReservasPend(int cantMaxReservasPend) {
		this.cantMaxReservasPend = cantMaxReservasPend;
	}
	public Tipo_Elementos(int id_tipoelemento, String nombre, int cantMaxReservasPend) {
		super();
		this.id_tipoelemento = id_tipoelemento;
		this.nombre = nombre;
		this.cantMaxReservasPend = cantMaxReservasPend;
	}
	
	public Tipo_Elementos()
	{
		
	}
	
}
