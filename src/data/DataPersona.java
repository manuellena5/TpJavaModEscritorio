package data;

import java.sql.*;
import java.util.ArrayList;
import entidades.*;

public class DataPersona {
	
	
 public ArrayList<Persona> getAll(){
		
	ArrayList<Persona> pers = new ArrayList<Persona>();
	try {
		
	
	Statement stm = FactoryConexion.getInstancia().getConn()
			.createStatement();
	ResultSet rs = stm.executeQuery("select * from persona");
	if (rs != null) {
		while (rs.next()) {
			Persona p = new Persona();
			p.setNombre(rs.getString("nombre"));
			p.setApellido(rs.getString("apellido"));
			p.setDni(rs.getInt("dni"));
			p.setHabilitado(rs.getBoolean("habilitado"));
			
			pers.add(p);
			
			
		}
	}} catch (SQLException e) {
		e.printStackTrace();
	}
		return pers;
		
		
	}

}
