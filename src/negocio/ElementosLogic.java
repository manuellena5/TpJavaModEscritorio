package negocio;

import java.util.ArrayList;

import data.DataElementos;
import entidades.Elementos;

public class ElementosLogic {

	
	private Elementos elementos;
	private DataElementos elementosD;
	ArrayList<Elementos> lista = new ArrayList<Elementos>();
	
	
	public ElementosLogic(){
	
		elementos = new Elementos(); 
		elementosD = new DataElementos();
		
	}


	public void add(Elementos el) throws Exception{
	
		
		elementosD.add(el);
	
	}
	
	public void delete(Elementos el)throws Exception{
		//this.pers.remove(this.getByDni(el));
		this.elementosD.delete(el);
	}
	
	public void update(Elementos el)throws Exception{
		
		this.elementosD.update(el);
	}

	public Elementos GetOne (String doc){
	
		for (Elementos elementos : lista) {
			if (elementos.getNombre() == doc ) {
				return elementos;
			}
			
		}
		return null;
	}	

	
	public Elementos GetByNombre(Elementos el) throws Exception{
		
		
		return elementosD.getByNombre(el);
		
		//return this.lista.get(this.lista.indexOf(el));
		
		/*if(elementos.Equals(el)){
	  return this.GetByNombre(el.getNombre());
		}
		return null;*/
	
	}
	
	
	public Elementos GetByNombre (String nombre) throws Exception{
		
		Elementos el=new Elementos();
		el.setNombre(nombre);
		return GetByNombre(el);
		
		/*for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getNombre() == nombre) {
				return lista.get(i);
			}
			
		}
		return null;*/
		
	}
	
	
	
	
	public void EliminarElemento(Elementos el) throws Exception{
	
	 lista.remove(this.GetByNombre(el));
		
	
	}	




		public ArrayList<Elementos> GetAll() throws Exception{
	
			return elementosD.getAll();
			
		}


		public void ModificarElemento(Elementos el) throws Exception {
			
			this.EliminarElemento(el);
			this.add(el);
			
		}
	
}
