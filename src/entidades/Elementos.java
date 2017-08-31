package entidades;

public class Elementos {
	
	private int id_elemento;
	private String nombre;
	private int stock;
	private String descripcion;
	private String autor;
	private int id_tipoelemento;
	
	
	public int getStock() {
		return stock;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public int getId_tipoelemento() {
		return id_tipoelemento;
	}
	public void setId_tipoelemento(int id_tipoelemento) {
		this.id_tipoelemento = id_tipoelemento;
	}
	
	
	public int getId_elemento() {
		return id_elemento;
	}
	public void setId_elemento(int id_clase) {
		this.id_elemento = id_clase;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Elementos(int id_elemento, String nombre, int stock, String descripcion, String autor, int id_tipoelemento) {
		super();
		this.id_elemento = id_elemento;
		this.nombre = nombre;
		this.stock = stock;
		this.descripcion = descripcion;
		this.autor = autor;
		this.id_tipoelemento = id_tipoelemento;
	}
	
	public Elementos(){
		
	}
	
	

}