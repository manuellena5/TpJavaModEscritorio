package entidades;

public class Tipo_Elementos {

	private int id_tipoelemento;
	private String nombre;
	private int cantMaxClasesAAsistir;
	
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
	public int getCantMaxClasesAAsistir() {
		return cantMaxClasesAAsistir;
	}
	public void setCantMaxClasesAAsistir(int cantMaxClasesAAsistir) {
		this.cantMaxClasesAAsistir = cantMaxClasesAAsistir;
	}
	public Tipo_Elementos(int id_tipoelemento, String nombre, int cantMaxClasesAAsistir) {
		super();
		this.id_tipoelemento = id_tipoelemento;
		this.nombre = nombre;
		this.cantMaxClasesAAsistir = cantMaxClasesAAsistir;
	}
	
	public Tipo_Elementos()
	{
		
	}
	
}
