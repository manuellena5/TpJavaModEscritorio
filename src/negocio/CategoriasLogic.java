package negocio;

import java.util.ArrayList;

import data.DataCategorias;
import entidades.Categorias;

public class CategoriasLogic {

	
	private Categorias categorias;
	private DataCategorias categoriasD;
	ArrayList<Categorias> lista = new ArrayList<Categorias>();
	
	
	public CategoriasLogic(){
	
		categorias = new Categorias(); 
		categoriasD = new DataCategorias();
		
	}


	public void add(Categorias cat) throws Exception{
	
		
		categoriasD.add(cat);
	
	}
	
	public void delete(Categorias cat)throws Exception{
		//this.pers.remove(this.getByDescripcion(el));
		this.categoriasD.delete(cat);
	}
	
	public void update(Categorias cat)throws Exception{
		
		this.categoriasD.update(cat);
	}

	public Categorias GetOne (String doc){
	
		for (Categorias categorias : lista) {
			if (categorias.getDescripcion() == doc ) {
				return categorias;
			}
			
		}
		return null;
	}	

	
	public Categorias GetByDescripcion(Categorias cat) throws Exception{
		
		
		return categoriasD.getByDescripcion(cat);
		
		//return this.lista.get(this.lista.indexOf(cat));
		
		/*if(categorias.Equals(cat)){
	  return this.GetByDescripcion(cat.getDescripcion());
		}
		return null;*/
	
	}
	
	
	public Categorias GetByDescripcion (String descripcion) throws Exception{
		
		Categorias cat=new Categorias();
		cat.setDescripcion(descripcion);
		return GetByDescripcion(cat);
		
		/*for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getDescripcion() == descripcion) {
				return lista.get(i);
			}
			
		}
		return null;*/
		
	}
	
	
	
	
	public void EliminarCategoria(Categorias cat) throws Exception{
	
	 lista.remove(this.GetByDescripcion(cat));
		
	
	}	




		public ArrayList<Categorias> GetAll() throws Exception{
	
			return categoriasD.getAll();
			
		}


		public void ModificarCategoria(Categorias cat) throws Exception {
			
			this.EliminarCategoria(cat);
			this.add(cat);
			
		}
	
}
