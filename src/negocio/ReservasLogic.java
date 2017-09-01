package negocio;

import java.util.ArrayList;

import data.DataReservas;
import entidades.Reservas;

public class ReservasLogic {

	
	private Reservas reservas;
	private DataReservas reservasD;
	ArrayList<Reservas> lista = new ArrayList<Reservas>();
	
	
	public ReservasLogic(){
	
		reservas = new Reservas(); 
		reservasD = new DataReservas();
		
	}


	public void add(Reservas res) throws Exception{
	
		
		reservasD.add(res);
	
	}
	
	public void delete(Reservas res)throws Exception{
		//this.pers.remove(this.getByDni(el));
		this.reservasD.delete(res);
	}
	
	public void update(Reservas res)throws Exception{
		
		this.reservasD.update(res);
	}

	public Reservas GetOne (int id_persona, int id_elemento){
	
		for (Reservas reservas : lista) {
			if (reservas.getId_persona() == id_persona && reservas.getId_elemento() == id_elemento) {
				return reservas;
			}
			
		}
		return null;
	}	

	
	public Reservas GetByIdPersona(Reservas res) throws Exception{
		
		
		return reservasD.getByIdPersona(res);
		
		//return this.lista.get(this.lista.indexOf(el));
		
		/*if(reservas.Equals(el)){
	  return this.GetByIdPersona(el.getId_Persona());
		}
		return null;*/
	
	}
	
	
	public Reservas GetByNombre (int id_persona, int id_elemento) throws Exception{
		
		Reservas res=new Reservas();
		res.setId_persona(id_persona);
		res.setId_elemento(id_elemento);
		return GetByIdPersona(res);
		
		/*for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getNombre() == nombre) {
				return lista.get(i);
			}
			
		}
		return null;*/
		
	}
	
	
	
	
	public void EliminarReservas(Reservas res) throws Exception{
	
	 lista.remove(this.GetByIdPersona(res));
		
	
	}	




		public ArrayList<Reservas> GetAll() throws Exception{
	
			return reservasD.getAll();
			
		}


		public void ModificarReservas(Reservas res) throws Exception {
			
			this.EliminarReservas(res);
			this.add(res);
			
		}
	
}
