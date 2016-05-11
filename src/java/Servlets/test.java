/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import ConexionBD.ConexionBD;
import DelegacionDAO.DelegacionDAOImpl;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;





/**
 *
 * @author Lucia
 */
public class test {
   
    static Connection VMobjConn;

    public static String[][] compararParticipacion(String id) 
{
        VMobjConn = ConexionBD.getConexion();
        String a[][]=new String[6][5];
        ResultSet a00=null;
        ResultSet a03=null;
        ResultSet a06=null;
        ResultSet a09=null;
        ResultSet a12=null;
        ResultSet a15=null;
        
    try 
    {
        String query="";
        VMobjConn = ConexionBD.getConexion();
        Statement VLsqlST = VMobjConn.createStatement();
        
        a00 = VLsqlST.executeQuery("SELECT v_lidos,nulos,total,lista_nominal,participacion FROM ano2006 WHERE clave =\'" + id + "\'");
         
                while(a00.next())
                {

                    a[0][0]=a00.getString("v_lidos");
                    a[0][1]=a00.getString("nulos");
                    a[0][2]=a00.getString("total");
                    a[0][3]=a00.getString("lista_nominal");
                    a[0][4]=a00.getString("participacion");
                    
                    

                }   
        a03 = VLsqlST.executeQuery("SELECT v_lidos,nulos,total,lista_nominal,participacion FROM ano2006 WHERE clave =\'" + id + "\'");
         
                while(a03.next())
                {

                    a[1][0]=a03.getString("v_lidos");
                    a[1][1]=a03.getString("nulos");
                    a[1][2]=a03.getString("total");
                    a[1][3]=a03.getString("lista_nominal");
                    a[1][4]=a03.getString("participacion");

                }
            a06 = VLsqlST.executeQuery("SELECT v_lidos,nulos,total,lista_nominal,participacion FROM ano2006 WHERE clave =\'" + id + "\'");
                while(a06.next())
                {

                    a[2][0]=a06.getString("v_lidos");
                    a[2][1]=a06.getString("nulos");
                    a[2][2]=a06.getString("total");
                    a[2][3]=a06.getString("lista_nominal");
                    a[2][4]=a06.getString("participacion");

               }
            a09 = VLsqlST.executeQuery("SELECT v_lidos,nulos,total,lista_nominal,participacion FROM ano2009 WHERE clave =\'" + id + "\'");
                while(a09.next())
                {
                    a[3][0]=a03.getString("v_lidos");
                    a[3][1]=a03.getString("nulos");
                    a[3][2]=a03.getString("total");
                    a[3][3]=a03.getString("lista_nominal");
                    a[3][4]=a03.getString("participacion");

               }
         
           a12 = VLsqlST.executeQuery("SELECT validos,nulos,total,lista_nominal,participacion FROM ano2012 WHERE clave =\'" + id + "\'");
                while(a12.next())
                {
                    a[4][0]=a12.getString("validos");
                    a[4][1]=a12.getString("nulos");
                    a[4][2]=a12.getString("total");
                    a[4][3]=a12.getString("lista_nominal");
                    a[4][4]=a12.getString("participacion");

                 }
            a15 = VLsqlST.executeQuery("SELECT validos,nulos,total,lista_nominal,participacion FROM ano2015 WHERE clave =\'" + id + "\'");
                while(a15.next())
                {
                    
                    //int v=Integer.parseInt(a15.getString("total"))-Integer.parseInt(a15.getString("nulos"));
                    a[5][0]=a15.getString("validos");
                    a[5][1]=a15.getString("nulos");
                    a[5][2]=a15.getString("total");
                    a[5][3]=a15.getString("lista_nominal");
                    a[5][4]=a15.getString("participacion");

                 }
    } 
            catch (SQLException ex) 
            {
                Logger.getLogger(DelegacionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
    finally
            {
                    try 
                    {
                        a00.close();
                        a03.close();
                        a09.close();
                        a09.close();
                        a12.close();
                        VMobjConn.close();
                    } 
                    catch (SQLException ex) 
                    {
                        Logger.getLogger(DelegacionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
             }
    return a;

    }
    
    public static void main(String[] args)
    {   
      String[][] a=new String[6][5];
      a=compararParticipacion("002");
        for(int row = 0; row < a.length; row++)
            {
                    for(int element = 0; element < a[row].length; element++)
                    {
                        System.out.println(""+a[row][element]);
                    }
            }
      
    }
}
