/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comparativa.DBComparativa;

import ConexionBD.ConexionBD;
import DelegacionDAO.DelegacionDAOImpl;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diegoleon
 */
public class DBComparativa {
    private Connection VMobjConn = null;
    private ResultSet VLsqlRS=null;
    List<PartidoPorDelegacion> listRow;
    List<List<PartidoPorDelegacion>> listTabla;
            
    public DBComparativa()
    {
        listTabla = new ArrayList<>();
    }
    
    public List<List<PartidoPorDelegacion>> getInfoByYear(String strAnio)
    {
        try
        {
            VMobjConn = ConexionBD.getConexion();
            Statement VLsqlST = VMobjConn.createStatement();
            
            VLsqlRS = VLsqlST.executeQuery("SELECT * FROM ano" + strAnio);
            ResultSetMetaData rsmd = VLsqlRS.getMetaData();
            
            int numeroColumnas = rsmd.getColumnCount();
            
            while(VLsqlRS.next())
            {
                listRow = new ArrayList();
                
                for(int i = 1 ; i <= numeroColumnas ; i++)
                {
                    String nombreColumna = rsmd.getColumnName(i);
                    
                    if( !(nombreColumna.contains("clave") || nombreColumna.contains("municipio") ||
                        nombreColumna.contains("v_lidos") || nombreColumna.contains("nulos") || 
                        nombreColumna.contains("total") || nombreColumna.contains("lista_nominal") || 
                        nombreColumna.contains("participacion") || nombreColumna.contains("validos")) )
                    {
                        
                        listRow.add(new PartidoPorDelegacion(rsmd.getColumnName(i), 
                                VLsqlRS.getInt(i), 
                                VLsqlRS.getString(1),
                                VLsqlRS.getString(2)));
                    }
                }
                
                listTabla.add(listRow);
            }
        }
        catch (SQLException ex) 
        {
             ex.printStackTrace();
        }
        finally
        {
                try 
                {
                    VMobjConn.close();
                    VLsqlRS.close();
                } 
                catch (SQLException ex) 
                {
                    ex.printStackTrace();
                }
         }
        
        return listTabla;
    }
}
