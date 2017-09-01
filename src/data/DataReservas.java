package data;

import java.sql.*;
import java.util.ArrayList;
import entidades.*;
import util.AppDataException;
import javax.swing.JOptionPane;
import java.security.KeyStore.ProtectionParameter;

public class DataReservas {
	
	
	public ArrayList<Reservas> getAll() throws Exception{
				
				Statement stmt=null;
				ResultSet rs=null;
				ArrayList<Reservas> reservas = new ArrayList<Reservas>();
				try {
					  stmt = FactoryConexion.getInstancia().getConn().createStatement();
					  rs = stmt.executeQuery("select * from reservas");
					  
				if (rs != null) {
						while (rs.next()) {
								Reservas res = new Reservas();
								res.setId_persona(rs.getInt("id_persona"));
								res.setId_elemento(rs.getInt("id_elemento"));
								res.setFecha_registro(rs.getDate("fecha_registro"));
								res.setFecha_inicio(rs.getDate("fecha_inicio"));
								res.setFecha_fin(rs.getDate("fecha_fin"));
								res.setDetalle(rs.getString("detalle"));
								res.setEstado(rs.getString("estado"));
								
								reservas.add(res);
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
				
					return reservas;
					
					
				}
 
	
	public Reservas getByIdPersona(Reservas reservas) throws Exception{
	
			Reservas res = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				 /*al poner el signo de pregunta el driver se da cuenta que en ese lugar va a ir un parametro*/
				stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
						"select p.id_persona, e.id_elemento, r.fecha_registro, r.fecha_inicio, r.fecha_fin, r.detalle, r.estado from reservas r "
						+ "inner join elementos e on e.id_elemento=r.id_elemento inner join personas p on p.id_persona=r.id_persona where r.id_persona=?");
						
				stmt.setInt(1, reservas.getId_persona());
				rs = stmt.executeQuery();
				
				if (rs!=null && rs.next()) {
					res = new Reservas();
					res.setId_elemento(rs.getInt("id_persona"));   /* el dato que va como argumento tiene que ser igual al que esta en la base? */
					res.setId_elemento(rs.getInt("id_elemento"));
					res.setFecha_registro(rs.getDate("fecha_registro"));
					res.setFecha_inicio(rs.getDate("fecha_inicio"));
					res.setFecha_fin(rs.getDate("fecha_fin"));
					res.setDetalle(rs.getString("detalle"));
					res.setEstado(rs.getString("estado"));
				
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
			
			return res;
		}
	
	
		
		public void add(Reservas res) throws Exception{
			PreparedStatement stmt=null;
			ResultSet keyResultSet=null;
			try {
				stmt=FactoryConexion.getInstancia().getConn()
						.prepareStatement(
						"insert into reservas(id_persona, id_elemento, fecha_registro, fecha_inicio, fecha_fin, detalle, estado) values (?,?,?,?,?,?,?)",
						PreparedStatement.RETURN_GENERATED_KEYS
						);
				
				stmt.setDate(1, res.getFecha_registro());
				stmt.setDate(2, res.getFecha_inicio());
				stmt.setDate(3, res.getFecha_fin());
				stmt.setString(4, res.getDetalle());
				stmt.setString(5, res.getEstado());
				
				stmt.executeUpdate();
				
				keyResultSet=stmt.getGeneratedKeys();
				if(keyResultSet!=null && keyResultSet.next()){
					res.setId_persona(keyResultSet.getInt(1));
					res.setId_elemento(keyResultSet.getInt(1));
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
		
		public void update(Reservas res) throws Exception{
			PreparedStatement stmt=null;
			
			try {
				stmt= FactoryConexion.getInstancia().getConn().prepareStatement(
						"update reservas set fecha_registro=?, fecha_inicio=?, fecha_fin=?, detalle=?, estado=? where id_persona=? and id_elemento=?");
				
				stmt.setDate(1, res.getFecha_registro());
				stmt.setDate(2, res.getFecha_inicio());
				stmt.setDate(3, res.getFecha_fin());
				stmt.setString(4, res.getDetalle());
				stmt.setString(5, res.getEstado());
			
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
		
		public void delete(Reservas res) throws Exception{
			PreparedStatement stmt=null;
			
			try {
				stmt= FactoryConexion.getInstancia().getConn().prepareStatement(
						"delete from reservas where id_persona=? and id_elemento=?");
				
				stmt.setInt(1, res.getId_persona());
				stmt.setInt(1, res.getId_elemento());
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

