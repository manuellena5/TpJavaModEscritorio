package negocio;

import java.util.ArrayList;

import data.DataPersona;
import entidades.Persona;

public class PersonaLogic {

	
	private Persona per;
	private DataPersona perD;
	ArrayList<Persona> lista = new ArrayList<Persona>();
	
	
	public PersonaLogic(){
	
		per = new Persona(); 
		perD = new DataPersona();
		
	}


	public void Save(Persona p){
	
		
		lista.add(p);
		
		/*if (!lista.contains(p)) {
			lista.add(p);
		}else {
			
			EliminarPersona(p);
			lista.add(p);
		}*/
	
	
	
	//perD.GuardarSocio(p);
	
	}

	public Persona GetOne (int doc){
	
		for (Persona per : lista) {
			if (per.getDni() == doc ) {
				return per;
			}
			
		}
		return null;
	}	

	
	public Persona GetByDni(Persona p){
		
		
		return GetByDni(p.getDni());
		
		//return this.lista.get(this.lista.indexOf(p));
		
		/*if(per.Equals(p)){
	  return this.GetByDni(p.getDni());
		}
		return null;*/
	
	}
	
	
	public Persona GetByDni (int doc){
		
		/*Persona p=new Persona();
		p.setDni(doc);
		return GetByDni(p);*/
		
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getDni() == doc) {
				return lista.get(i);
			}
			
		}
		return null;
		
	}
	
	
	
	
	public void EliminarPersona(Persona p){
	
	 lista.remove(this.GetByDni(p));
		
	
	}	




		public ArrayList<Persona> GetAll(){
	
			return perD.getAll();
			
		}


		public void ModificarPersona(Persona p) {
			
			this.EliminarPersona(p);
			this.Save(p);
			
		}
	
}
