package entidades;

public class Persona {

	private String nombre;
	private String apellido;
	private int dni;
	private boolean habilitado;
	
	
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public boolean isHabilitado() {
		return habilitado;
	}
	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
	
	public Persona(){
		
	}
	
	
	public Persona(String nombre, String apellido, int dni, boolean habilitado) {
		super();
		this.setNombre(nombre);
		this.setApellido(apellido);
		this.setDni(dni);
		this.setHabilitado(habilitado);
		}
	
	public boolean Equals(Object o){
		return (o instanceof Persona) &&
				(((Persona)o).getDni() == (this.dni));

		
	}
	
}
