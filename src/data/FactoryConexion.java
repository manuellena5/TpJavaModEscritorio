package data;
import java.sql.*;


public class FactoryConexion {

	private static FactoryConexion instancia;
	
	private Connection conn;
	
	private FactoryConexion(){
		
		try {
			new com.mysql.jdbc.Driver();
			Class.forName("com.mysql.jdbc.Driver()");
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			
			
		}
		
	}
	
	public static FactoryConexion getInstancia(){
		
		if (FactoryConexion.instancia == null) {
		
			FactoryConexion.instancia= new FactoryConexion();
		}
		return FactoryConexion.instancia;
		
	}
	/* Pregunta si ya hay una conexion, si hay la devuelve, sino la crea
	 * solo tiene que haber una unica instancia de la conexion por eso es singleton */
	
	public Connection getConn(){
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:8080/personas?user=root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	
}
