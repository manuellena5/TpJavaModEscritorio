/*package ui;

import java.util.Scanner;



public class PrincipalConsola {

	
	private static Scanner s;


	public PrincipalConsola()
	{

	}
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		String respuesta= "";
		AbmPersonaConsola abmper = new AbmPersonaConsola();
		
		s = new Scanner(System.in);
		
		
		
		
		do {
				abmper.Menu();
				switch (Integer.parseInt(s.nextLine())) {
				
				case 1: abmper.CargarDatos();
				
					break;
				case 2: abmper.Eliminar();
			
					break;
					
				case 3: abmper.ModificarDatos();
				
					break;
					
				case 4: abmper.Consulta();
				
					break;

				case 5: abmper.ListarPersonas();
				
					break;
				
				default:
					break;
				}
				
				System.out.println("Desea salir? Responda si o no");
				//s = new Scanner(System.in);
				respuesta = s.nextLine();
		} while (respuesta.equals("no"));
			
		
		
		
		s.close();
		
		
	}
	
	
		
		
		
		
	

}
*/