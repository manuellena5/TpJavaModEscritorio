package negocio;

import java.util.ArrayList;

import data.DataTipo_Elementos;
import entidades.Tipo_Elementos;

public class Tipo_ElementosLogic {

	
	private Tipo_Elementos tipoElementos;
	private DataTipo_Elementos tipoElementosD;
	ArrayList<Tipo_Elementos> lista = new ArrayList<Tipo_Elementos>();
	
	
	public Tipo_ElementosLogic(){
	
		tipoElementos = new Tipo_Elementos(); 
		tipoElementosD = new DataTipo_Elementos();
		
	}


	public void add(Tipo_Elementos te) throws Exception{
	
		
		tipoElementosD.add(te);
	
	}
	
	public void delete(Tipo_Elementos te)throws Exception{
		//this.pers.remove(this.getByDni(te));
		this.tipoElementosD.delete(te);
	}
	
	public void update(Tipo_Elementos te)throws Exception{
		
		this.tipoElementosD.update(te);
	}

	public Tipo_Elementos GetOne (String doc){
	
		for (Tipo_Elementos tipoElementos : lista) {
			if (tipoElementos.getNombre() == doc ) {
				return tipoElementos;
			}
			
		}
		return null;
	}	

	
	public Tipo_Elementos GetByNombre(Tipo_Elementos te) throws Exception{
		
		
		return tipoElementosD.getByNombre(te);
		
		//return this.lista.get(this.lista.indexOf(te));
		
		/*if(tipoElementos.Equals(te)){
	  return this.GetByNombre(te.getNombre());
		}
		return null;*/
	
	}
	
	
	public Tipo_Elementos GetByNombre (String nombre) throws Exception{
		
		Tipo_Elementos te=new Tipo_Elementos();
		te.setNombre(nombre);
		return GetByNombre(te);
		
		/*for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getNombre() == nombre) {
				return lista.get(i);
			}
			
		}
		return null;*/
		
	}
	
	
	
	
	public void EliminarTipoElemento(Tipo_Elementos te) throws Exception{
	
	 lista.remove(this.GetByNombre(te));
		
	
	}	




		public ArrayList<Tipo_Elementos> GetAll() throws Exception{
	
			return tipoElementosD.getAll();
			
		}


		public void ModificarTipoElemento(Tipo_Elementos te) throws Exception {
			
			this.EliminarTipoElemento(te);
			this.add(te);
			
		}
	
}
