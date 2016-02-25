/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ConexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Lucia
 */
public class ConexionBD 
{
	private static ConexionBD VGobjConexion = null; // Patron de dise;o Singleton
	
	private String VMstrUsuario;
	private String VMstrPassword;
	private String VMstrURL;
	private String VMstrDriver;
	
	private ConexionBD()
	{
		this.VMstrUsuario = "postgres";
		this.VMstrPassword = "tt";
		this.VMstrURL = "jdbc:postgresql://127.0.0.1:5432/ssgpe";
		this.VMstrDriver = "org.postgresql.Driver";
	}
	
	public static Connection getConexion()
	{
		if(VGobjConexion == null)
			VGobjConexion = new ConexionBD();
		
		Connection VLsqlConnection = VGobjConexion.createConexion();
		
		return VLsqlConnection;
	}
	
	private Connection createConexion()
	{
		Connection VLobjConn = null;
		
		try 
		{
			Class.forName(VGobjConexion.VMstrDriver);
			
			VLobjConn = DriverManager.getConnection(VGobjConexion.VMstrURL,
													VGobjConexion.VMstrUsuario,
													VGobjConexion.VMstrPassword);
                        System.out.println("conecto");
		} 
		catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		if(VLobjConn != null)
			System.out.println("Conexion realizada.");
		else
			System.out.println("Conexion no realizada.");
		*/
		
		return VLobjConn;
	}
	/*
	public static void main(String[] args)
	{
		Connection conn = ConexionBD.getConexion();
		
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Usuarios");
			
			
			while(rs.next())
			{
				System.out.println(rs.getString(1));
				System.out.println(rs.getString(2));
				System.out.println(rs.getString(3));
				System.out.println(rs.getString(4));
				System.out.println(rs.getString(5));
				System.out.println(rs.getString(6));
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
        */
}