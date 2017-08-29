package ui;

import java.util.ArrayList;
import java.util.Scanner;

import entidades.Persona;
import negocio.PersonaLogic;

public class AbmPersonaConsola {

	private PersonaLogic perlog;
	private Scanner s;
	
	
	public PersonaLogic getPerlog() {
		return perlog;
	}


	public void setPerlog(PersonaLogic perlog) {
		this.perlog = perlog;
	}

	
	public AbmPersonaConsola(){
		perlog = new PersonaLogic();
		
	}
	
	
	
	public Scanner getS() {
		return s;
	}


	public void setS(Scanner s) {
		this.s = s;
	}


	public void Menu(){
		
		System.out.println("1- Alta Persona \n"
				+ "2- Baja Persona \n"
				+ "3- Modificar Persona \n"
				+ "4- Consultar \n"
				+ "5- Listar personas \n"
				+ "6- Salir \n");
	}
	
	
	
	

	public void ModificarDatos() throws Exception{

		ListarPersonas();
		s = new Scanner(System.in);
		
		System.out.println("Ingrese el dni de la persona a modificar \n");
		Persona per = perlog.GetOne(s.nextLine());
		
		System.out.println("Ingrese el nombre: \n");
		per.setNombre(s.nextLine());
		System.out.println("Ingrese el apellido: \n");
		per.setApellido(s.nextLine());
		System.out.println("Ingrese el dni \n");
		per.setDni(s.nextLine());
		System.out.println("Habilitado? 1=si 0=no \n");
		if (Integer.parseInt(s.nextLine()) == 1) {
			per.setHabilitado(true);
			
		}else if (Integer.parseInt(s.nextLine()) == 1) {
			per.setHabilitado(false);
		}else{System.out.println("Dato ingresado incorrectamente"); }
		
		perlog.ModificarPersona(per);
	
		//s.close();
	}


	public void CargarDatos(){
		
		s = new Scanner(System.in);
		Persona per = new Persona();
		
		System.out.println("Ingrese el nombre: \n");
		per.setNombre(s.nextLine());
		System.out.println("Ingrese el apellido: \n");
		per.setApellido(s.nextLine());
		System.out.println("Ingrese el dni \n");
		per.setDni(s.nextLine());
		System.out.println("Habilitado? 1=si 0=no \n");
		if (Integer.parseInt(s.nextLine()) == 1) {
			per.setHabilitado(true);
			
		}else if (Integer.parseInt(s.nextLine()) == 1) {
			per.setHabilitado(false);
		}else{System.out.println("Dato ingresado incorrectamente"); }
		
		perlog.Save(per);
	
		//s.close();

	}


	public void Consulta() throws Exception{
	
		ListarPersonas();

		System.out.println("Ingrese el dni de la persona a consultar \n");
		Persona per = perlog.GetOne(s.nextLine());
		
		System.out.println("Nombre: " + per.getNombre() + "\n" + 
							"Apellido: " + per.getApellido() + "\n" + 
							"Dni: " + per.getDni() + "\n");

		//s.close();
	}


	public void Eliminar() throws Exception{
		
		
		ListarPersonas();
		System.out.println("Ingrese el dni de la persona a eliminar \n");
		String doc = s.nextLine();
		perlog.EliminarPersona(perlog.GetByDni(doc));
		
		//s.close();
	}


	public void ListarPersonas() throws Exception{
		
		
		ArrayList<Persona> listado = new ArrayList<>();
		listado.addAll(perlog.GetAll());
		
		for (Persona p : listado) {
			System.out.println("Nombre: " + p.getNombre() + "\n"
					+ "Apellido: " + p.getApellido() + "\n"
							+ "Dni: " + p.getDni() );
			
			}
		}
	
}
