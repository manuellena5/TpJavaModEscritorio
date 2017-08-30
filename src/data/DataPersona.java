package data;

import java.sql.*;
import java.util.ArrayList;
import entidades.*;
import util.AppDataException;
import javax.swing.JOptionPane;
import java.security.KeyStore.ProtectionParameter;

public class DataPersona {
	
	
	public ArrayList<Persona> getAll() throws Exception{
				
				Statement stmt=null;
				ResultSet rs=null;
				ArrayList<Persona> pers = new ArrayList<Persona>();
				try {
					  stmt = FactoryConexion.getInstancia().getConn().createStatement();
					  rs = stmt.executeQuery("select * from persona");
					  
				if (rs != null) {
						while (rs.next()) {
								Persona p = new Persona();
								p.setId(rs.getInt("id"));
								p.setNombre(rs.getString("nombre"));
								p.setApellido(rs.getString("apellido"));
								p.setDni(rs.getString("dni"));
								p.setHabilitado(rs.getBoolean("habilitado"));
								p.setUsuario(rs.getString("usuario"));
								p.setPassword(rs.getString("password"));
								
								pers.add(p);
										}
					}
				
				}catch (SQLException e) {
					
					throw e;
				}
				catch (AppDataException ade){
					throw ade;
				}
				
				try {
					if(rs!=null) rs.close();
					if(stmt!=null) stmt.close();
					FactoryConexion.getInstancia().releaseConn();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				
					return pers;
					
					
				}
 
	
	public Persona getByDni(Persona per) throws Exception{
	
			Persona p = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				 /*al poner el signo de pregunta el driver se da cuenta que en ese lugar va a ir un parametro*/
				stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
						"select id_persona, nombre, apellido, dni, estado,usuario,password from personas where dni=?");
						
				stmt.setString(1, per.getDni());
				rs = stmt.executeQuery();
				
				if (rs!=null && rs.next()) {
					p = new Persona();
					p.setId(rs.getInt("id_persona"));   /* el dato que va como argumento tiene que ser igual al que esta en la base? */
					p.setNombre(rs.getString("nombre"));
					p.setApellido(rs.getString("apellido"));
					p.setDni(rs.getString("dni"));
					p.setHabilitado(rs.getBoolean("estado"));
					p.setUsuario(rs.getString("usuario"));
					p.setPassword(rs.getString("password"));
				
				}
				
				
			
			
			} catch (Exception e) {
				
				throw e;
			}
			
				try {
					if (rs != null) {rs.close();}
					if (stmt != null) {stmt.close();}
					FactoryConexion.getInstancia().releaseConn();	
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
			return p;
		}
	
	
		
		public void add(Persona p) throws Exception{
			PreparedStatement stmt=null;
			ResultSet keyResultSet=null;
			try {
				stmt=FactoryConexion.getInstancia().getConn()
						.prepareStatement(
						"insert into personas(nombre, apellido,dni,usuario,password, estado) values (?,?,?,?,?,?)",
						PreparedStatement.RETURN_GENERATED_KEYS
						);
				
				stmt.setString(1, p.getNombre());
				stmt.setString(2, p.getApellido());
				stmt.setString(3, p.getDni());
				stmt.setString(4, p.getUsuario());
				stmt.setString(5, p.getPassword());
				stmt.setBoolean(6, p.isHabilitado());
				stmt.executeUpdate();
				keyResultSet=stmt.getGeneratedKeys();
				if(keyResultSet!=null && keyResultSet.next()){
					p.setId(keyResultSet.getInt(1));
				}
			} catch (SQLException | AppDataException e) {
				throw e;
			}
			try {
				if(keyResultSet!=null)keyResultSet.close();  /* preguntar que hace esta linea */ 
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public void update(Persona p) throws Exception{
			PreparedStatement stmt=null;
			
			try {
				stmt= FactoryConexion.getInstancia().getConn().prepareStatement(
						"update personas set nombre=?, apellido=?,dni=?,usuario=?,password=?,estado=? where id_persona=?");
				
				stmt.setString(1, p.getNombre());
				stmt.setString(2, p.getApellido());
				stmt.setString(3, p.getDni());
				stmt.setString(4, p.getUsuario());
				stmt.setString(5, p.getPassword());
				stmt.setBoolean(6, p.isHabilitado());
				stmt.setInt(7, p.getId());
				stmt.execute();
				
				
			} catch (SQLException | AppDataException e) {
				
				throw e;
			} 
			try {
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
		
		public void delete(Persona p) throws Exception{
			PreparedStatement stmt=null;
			
			try {
				stmt= FactoryConexion.getInstancia().getConn().prepareStatement(
						"delete from personas where id_persona=?");
				
				stmt.setInt(1, p.getId());
				stmt.execute();
				
				
			} catch (SQLException | AppDataException e) {
				
				throw e;
			} 
			try {
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
		 
}
