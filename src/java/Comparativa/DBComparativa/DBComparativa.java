/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comparativa.DBComparativa;

import ConexionBD.ConexionBD;
import DelegacionDAO.DelegacionDAOImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diegoleon
 */
public class DBComparativa {
    
    List<PartidoPorDelegacion> listRow;
    List<List<PartidoPorDelegacion>> listTabla;
            
    public DBComparativa()
    {
        listTabla = new ArrayList<>();
    }
    
    public List<List<PartidoPorDelegacion>> getInfoByYear(String strAnio)
    {
        List<PartidoPorDelegacion> listaPartidosTemp = getResultadoElectoralInfo(strAnio);
        
        int contadorListTabla = 0;
        
        listTabla.add(new ArrayList<PartidoPorDelegacion>());
        
        String strDelegacionComparacion = listaPartidosTemp.get(0).strMunicipio;
        
        for(PartidoPorDelegacion partido : listaPartidosTemp)
        {
            if(partido.strMunicipio.equals(strDelegacionComparacion))
            {
                listTabla.get(contadorListTabla).add(partido);
            }
            else
            {
                strDelegacionComparacion = partido.strMunicipio;
                listTabla.add(new ArrayList<PartidoPorDelegacion>());
                contadorListTabla = contadorListTabla + 1;
                listTabla.get(contadorListTabla).add(partido);
            }
        }
        
        return listTabla;
    }
    
    public List<PartidoPorDelegacion> getResultadoElectoralInfo(String VPstrAnio)
    {
        Connection VMobjConn = null;
        ResultSet VLsqlRS=null;
        
        List<PartidoPorDelegacion> listaPartidos = new ArrayList();
        
        try
        {
            VMobjConn = ConexionBD.getConexion();
            Statement VLsqlST = VMobjConn.createStatement();
            
            VLsqlRS = VLsqlST.executeQuery
            (
                "SELECT A.Anio,\"DF\".cve_mun, \"DF\".nom_mun,RE.Validos, RE.Nulo, RE.Total, RE.Lista_Nominal, RE.Participacion, PC.Nombre, PC.Votos, A.Id_Anio, \"DF\".gid, PC.Id_Partido, RE.Id_ResultadoElectoral FROM ResultadoElectoral AS RE INNER JOIN Anio AS A ON RE.Id_Anio = A.Id_Anio INNER JOIN bdqgis.\"DF\" ON RE.Id_Delegacion = bdqgis.\"DF\".gid INNER JOIN Partido_Coalicion AS PC ON RE.Id_ResultadoElectoral = PC.Id_ResultadoElectoral WHERE A.Anio like '" +VPstrAnio + "'"
            );
            
            while(VLsqlRS.next())
            {
                listaPartidos.add(new PartidoPorDelegacion
                                        (VLsqlRS.getString(1),
                                        VLsqlRS.getString(2),
                                        VLsqlRS.getString(3),
                                        VLsqlRS.getInt(4), 
                                        VLsqlRS.getInt(5), 
                                        VLsqlRS.getInt(6), 
                                        VLsqlRS.getInt(7), 
                                        VLsqlRS.getInt(8), 
                                        VLsqlRS.getString(9).toLowerCase(), 
                                        VLsqlRS.getInt(10)));
            }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
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
        
        return listaPartidos;
    }
    
    public ResultSet getResultadoElectoral(int id_Delegacion ,int VPint_idAnio) throws SQLException
    {
        Connection VMobjConn = null;
        ResultSet VLsqlRS=null;
         VMobjConn = ConexionBD.getConexion();
        Statement VLsqlST = VMobjConn.createStatement();
        
        try
        {
           
            
            VLsqlRS = VLsqlST.executeQuery("SELECT * FROM ResultadoElectoral WHERE id_Anio = " + VPint_idAnio + " AND Id_Delegacion = " + id_Delegacion);
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
                try 
                {
                    VMobjConn.close();
                   VLsqlST.close();
                    //VLsqlRS.close();
                } 
                catch (SQLException ex) 
                {
                    ex.printStackTrace();
                }
         }
        
        return VLsqlRS;
    }
    
     public int getIdAnio( String VPstrAnio)
    {
        Connection VMobjConn = null;
        ResultSet VLsqlRS=null;
        int VLintAnio = 0;
        
        try
        {
            VMobjConn = ConexionBD.getConexion();
            Statement VLsqlST = VMobjConn.createStatement();
            
            VLsqlRS = VLsqlST.executeQuery("SELECT id_Anio FROM Anio WHERE Anio LIKE '" + VPstrAnio + "'");
            
            while(VLsqlRS.next())
            {
                VLintAnio = VLsqlRS.getInt(1);
            }
            
            VLsqlRS.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
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
        
        return VLintAnio;
    }
    public int getIdDel(String CVE)
    {
        Connection VMobjConn = null;
        ResultSet VLsqlRS=null;
        int VLintAnio = 0;
        
        try
        {
            VMobjConn = ConexionBD.getConexion();
            Statement VLsqlST = VMobjConn.createStatement();
            
            VLsqlRS = VLsqlST.executeQuery("SELECT gid FROM bdqgis.\"DF\" WHERE cve_mun='" + CVE + "'");
            
            while(VLsqlRS.next())
            {
                System.out.println("CVE_IDE:"+VLsqlRS.getInt(1));
                VLintAnio = VLsqlRS.getInt(1);
            }
            
            VLsqlRS.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
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
        
        return VLintAnio;
    }
    
    public String getContrasena(String strCorreo)
    {
        Connection VMobjConn = null;
        ResultSet VLsqlRS=null;
        
        String VlstrCorreo = null;
        try
        {
            VMobjConn = ConexionBD.getConexion();
            Statement VLsqlST = VMobjConn.createStatement();
            
            VLsqlRS = VLsqlST.executeQuery("Select Password From Usuarios Where Correo like  '" + strCorreo + "'");
            
            while(VLsqlRS.next())
            {
                VlstrCorreo = VLsqlRS.getString(1);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
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
        
        return VlstrCorreo;
    }
    
    public void insertNewHistorial(int intCveSesion, int intIdResultadoElectoral, String strTipo)
    {
        String strCorreo = getCorreoByCveSesion(intCveSesion);
        Connection VMobjConn = null;
        
        try
        {
          VMobjConn = ConexionBD.getConexion();

          // create a sql date object so we can use it in our INSERT statement
            Calendar calendar = Calendar.getInstance();
          java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

          // the mysql insert statement
          String query = " insert into Historial (Id_ResultadoElectoral, Correo, Fecha_creacion, Tipo)"
            + " values (?, ?, ?, ?)";

          // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = VMobjConn.prepareStatement(query);
          preparedStmt.setInt (1, intIdResultadoElectoral);
          preparedStmt.setString (2, strCorreo);
          preparedStmt.setDate   (3, startDate);
          preparedStmt.setString (4, strTipo);
          // execute the preparedstatement
          preparedStmt.execute();

          VMobjConn.close();
        }
        catch (Exception e)
        {
          System.err.println("Got an exception!");
          System.err.println(e.getMessage());
        }
    }
    
    public String getCorreoByCveSesion(int intCveSesion)
    {
        Connection VMobjConn = null;
        ResultSet VLsqlRS=null;
        
        String VlstrCorreo = null;
        try
        {
            VMobjConn = ConexionBD.getConexion();
            Statement VLsqlST = VMobjConn.createStatement();
            
            VLsqlRS = VLsqlST.executeQuery("SELECT usr.Correo, sesn.Correo FROM Usuarios usr INNER JOIN Sesion sesn ON usr.Correo like sesn.Correo WHERE sesn.clave = " + intCveSesion);
            
            while(VLsqlRS.next())
            {
                VlstrCorreo = VLsqlRS.getString(1);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
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
        
        return VlstrCorreo;
    }
    
    public int getIdResultadoElectoral(int id_Delegacion ,int VPint_idAnio) throws SQLException
    {
        Connection VMobjConn = null;
        ResultSet VLsqlRS=null;
         VMobjConn = ConexionBD.getConexion();
        Statement VLsqlST = VMobjConn.createStatement();
        int idResultadoElectoral = 0;
        try
        {
           
            
            VLsqlRS = VLsqlST.executeQuery("SELECT * FROM ResultadoElectoral WHERE id_Anio = " + VPint_idAnio + " AND Id_Delegacion = " + id_Delegacion);
            while(VLsqlRS.next())
            {
                idResultadoElectoral = VLsqlRS.getInt(1);
            }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
                try 
                {
                    VMobjConn.close();
                   VLsqlST.close();
                    VLsqlRS.close(); 
                } 
                catch (SQLException ex) 
                {
                    ex.printStackTrace();
                }
         }
        
        return idResultadoElectoral;
    }
    
    public List<Historial> getHistorial()
    {
        List<Historial> historiales = new ArrayList<>();
        
        Connection VMobjConn = null;
        ResultSet VLsqlRS=null;
        
        try
        {
            VMobjConn = ConexionBD.getConexion();
            Statement VLsqlST = VMobjConn.createStatement();
            
            VLsqlRS = VLsqlST.executeQuery("SELECT usr.Nombre, usr.Apaterno, usr.Amaterno, usr.Correo, hist.Fecha_creacion, hist.Tipo FROM Historial hist INNER JOIN Usuarios usr ON usr.Correo like hist.Correo");
            
            while(VLsqlRS.next())
            {
                Historial historial = new Historial();
                
                historial.strNombreCompleto = VLsqlRS.getString(1) + " " + VLsqlRS.getString(2) + " " + VLsqlRS.getString(3);
                historial.strCorreo = VLsqlRS.getString(4);
                historial.strFechaCreacion = VLsqlRS.getString(5);
                historial.strTipo = VLsqlRS.getString(6);
                
                historiales.add(historial);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
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
        
        
                
        return historiales;
    }
    /*
    
    SELECT 
A.Anio,
DF.nom_mun,
RE.Validos, RE.Nulo, RE.Total, RE.Lista_Nominal, RE.Participacion,

PC.Nombre, PC.Votos,

A.Id_Anio, 
DF.gid,
PC.Id_Partido, 
RE.Id_ResultadoElectoral

FROM ResultadoElectoral RE INNER JOIN Anio A ON RE.Id_Anio = A.Id_Anio 
INNER JOIN bdqgis.DF ON RE.Id_Delegacion = bdqgis.DF.gid 
INNER JOIN Partido_Coalicion PC ON RE.Id_ResultadoElectoral = PC.Id_ResultadoElectoral
WHERE A.Anio like '2000';
    */
    
}