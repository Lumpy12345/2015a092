/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PartidoDAO;

import ConexionBD.ConexionBD;
import Partido.Partido;
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
public class PartidoDAOImpl implements PartidoDAO {
    
    private Connection VMobjConn;
    
    private Partido partido[]=new Partido[7];
    ResultSet a00=null;
    ResultSet a03=null;
    ResultSet a06=null;
    ResultSet a09=null;
    ResultSet a12=null;
    ResultSet a15=null;
    double PRI;
    double PAN;
    double PRD;
    double PT;
    double PVEM;
    double Morena;
    double NuevaAlianza;
    double Movimiento;
    double Validos;
    double DNulos;
    double DTotal;
    double DListaNominal;
    double Participacion;
    
    @Override
    public Partido[] getPartido(String id) {
       
       
       
    try 
    {
        String query="";
        VMobjConn = ConexionBD.getConexion();
        Statement VLsqlST = VMobjConn.createStatement();
        
           
            
           
         
        a15 = VLsqlST.executeQuery("SELECT * FROM ano2015 WHERE clave =\'" + id + "\'");
                while(a15.next())
                {      
                    
                        String Did=a15.getString(1);
                        String DNombre=a15.getString(2);
                        String pan=a15.getString(3);
                        String pripvem=a15.getString(4);
                        String prdpt=a15.getString(5);
                        String prdptnuevaalianza=a15.getString(6);
                        String prd=a15.getString(7);
                        String pt=a15.getString(8);
                        String movimiento=a15.getString(9);
                        String nuevaalianza=a15.getString(10);
                        String morena=a15.getString(11);
                        String ph=a15.getString(12);
                        String pes=a15.getString(13);
                        String inde=a15.getString(14);
                        String validos=a15.getString(15);
                        String nulos=a15.getString(16);
                        String total=a15.getString(17);
                        String lista=a15.getString(18);
                        String participacion=a15.getString(19);
                        
                        Double c=Double.parseDouble(prdpt)/2;
                        Double cc=Double.parseDouble(prdptnuevaalianza)/3;
                        
                        PAN=Double.parseDouble(pan);
                        PRI=Double.parseDouble(pripvem)/2;
                        PVEM=Double.parseDouble(pripvem)/2;
                        Morena=Double.parseDouble(morena);
                        PRD=Double.parseDouble(prd)+c+cc;
                        System.out.println("PRD15:"+PRD);
                        PT=Double.parseDouble(pt)+c+cc;
                        Movimiento=Double.parseDouble(movimiento);
                        NuevaAlianza=Double.parseDouble(nuevaalianza)+cc;
                        
                        DNulos=Double.parseDouble(nulos);
                        DTotal=Double.parseDouble(total);
                        DListaNominal=Double.parseDouble(lista);
                        Validos=Double.parseDouble(validos);
                        Participacion=Double.parseDouble(participacion);
                        
                        partido[0]=new Partido(PRI,PAN,PRD,PT,PVEM,Morena,NuevaAlianza,Movimiento,Validos,DNulos,DTotal,DListaNominal,Participacion);

                 }   
        a12 = VLsqlST.executeQuery("SELECT * FROM ano2012 WHERE clave =\'" + id + "\'");
                while(a12.next())
                {      
                        String Did=a12.getString(1);
                        String DNombre=a12.getString(2);
                        String pan=a12.getString(3);
                        String pri=a12.getString(4);
                        String prd=a12.getString(5);
                        String pt=a12.getString(6);
                        String pvem=a12.getString(7);
                        String mc=a12.getString(8);
                        String na=a12.getString(9);
                        String pripvem=a12.getString(10);
                        String prdptmc=a12.getString(11);
                        String validos=a12.getString(12);
                        String nulos=a12.getString(13);
                        String total=a12.getString(14);
                        String lista=a12.getString(15);
                        String participacion=a12.getString(16);
                        
                        double c=Double.parseDouble(pripvem)/2;
                        double cc=Double.parseDouble(prdptmc)/3;
                        
                        PAN=Double.parseDouble(pan);
                        PRI=Double.parseDouble(pri)+c;
                        PRD=Double.parseDouble(prd)+cc;
                        System.out.println("PRD12:"+PRD);
                        PT=Double.parseDouble(pt)+cc;
                        PVEM=Double.parseDouble(pvem)+c;
                        Movimiento=Double.parseDouble(mc)+cc;
                        NuevaAlianza=Double.parseDouble(na);
                        
                        DNulos=Double.parseDouble(nulos);
                        DTotal=Double.parseDouble(total);
                        DListaNominal=Double.parseDouble(lista);
                        Validos=Double.parseDouble(validos);
                        Participacion=Double.parseDouble(participacion);
                        
                        
                        partido[1]=new Partido(PRI,PAN,PRD,PT,PVEM,0,NuevaAlianza,Movimiento,Validos,DNulos,DTotal,DListaNominal,Participacion);

                 }
                a09 = VLsqlST.executeQuery("SELECT * FROM ano2009 WHERE clave =\'" + id + "\'");
                while(a09.next())
                {      
                        String Did=a09.getString(1);
                        String DNombre=a09.getString(2);
                        String pan=a09.getString(3);
                        String pri=a09.getString(4);
                        String prd=a09.getString(5);
                        String pt=a09.getString(6);
                        String pvem=a09.getString(7);
                        String conver=a09.getString(8);
                        String nuevaalianza=a09.getString(9);
                        String psd=a09.getString(10);
                        String prdptconver=a09.getString(11);
                        String prdpt=a09.getString(12);
                        String prdconver=a09.getString(13);
                        String ptconver=a09.getString(14);
                        String validos=a09.getString(15);
                        String nulos=a09.getString(16);
                        String total=a09.getString(17);
                        String lista=a09.getString(18);
                        String participacion=a09.getString(19);
                        
                        double c=Double.parseDouble(prdptconver)/3;
                        double cc=Double.parseDouble(prdpt)/2;
                        double ccc=Double.parseDouble(prdconver)/2;
                        double cccc=Double.parseDouble(ptconver);
                        
                        
                        PAN=Double.parseDouble(pan);
                        PRI=Double.parseDouble(pri);
                        PRD=Double.parseDouble(prd)+c+cc+ccc;
                        PT=Double.parseDouble(pt)+c+cc+cccc;
                        PVEM=Double.parseDouble(pvem);
                        NuevaAlianza=Double.parseDouble(nuevaalianza);
                        DNulos=Double.parseDouble(nulos);
                        DTotal=Double.parseDouble(total);
                        DListaNominal=Double.parseDouble(lista);
                        Validos=Double.parseDouble(validos);
                        Participacion=Double.parseDouble(participacion);
                        
                        partido[2]=new Partido(PRI,PAN,PRD,PT,PVEM,0,NuevaAlianza,0,Validos,DNulos,DTotal,DListaNominal,Participacion);

                 }
            a06 = VLsqlST.executeQuery("SELECT * FROM ano2006 WHERE clave =\'" + id + "\'");
                while(a06.next())
                {
                        String Did=a06.getString(1);
                        String DNombre=a06.getString(2);
                        String pan=a06.getString(3);
                        String pripvem=a06.getString(4);
                        String prdptconver=a06.getString(5);
                        String nuevaalianza=a06.getString(6);
                        String pasc=a06.getString(7);
                        String pannuevaalianza=a06.getString(8);
                        String validos=a06.getString(9);
                        String nulos=a06.getString(10);
                        String total=a06.getString(11);
                        String lista=a06.getString(12);
                        String participacion=a06.getString(13);
                       
                        double c=Double.parseDouble(prdptconver)/3;
                        double cc=Double.parseDouble(pannuevaalianza)/2;
                        
                        PAN=Double.parseDouble(pan)+cc;
                        PRI=Double.parseDouble(pripvem)/2;
                        PRD=c;
                        PT=c;
                        NuevaAlianza=Double.parseDouble(nuevaalianza)+cc;
                        PVEM=Double.parseDouble(pripvem)/2;
                        DNulos=Double.parseDouble(nulos);
                        DTotal=Double.parseDouble(total);
                        DListaNominal=Double.parseDouble(lista);
                        Validos=Double.parseDouble(validos);
                        Participacion=Double.parseDouble(participacion);
                        
                        partido[3]=new Partido(PRI,PAN,PRD,PT,PVEM,0,NuevaAlianza,0,Validos,DNulos,DTotal,DListaNominal,Participacion);
                        

                }
                
            a03 = VLsqlST.executeQuery("SELECT * FROM ano2003 WHERE clave =\'" + id + "\'");
                while(a03.next())
                {
                        String Did=a03.getString(1);
                        String DNombre=a03.getString(2);
                        String pan=a03.getString(3);
                        String pri=a03.getString(4);
                        String prd=a03.getString(5);
                        String pt=a03.getString(6);
                        String pvem=a03.getString(7);
                        String conver=a03.getString(8);
                        String psn=a03.getString(9);
                        String pas=a03.getString(10);
                        String mp=a03.getString(11);
                        String plm=a03.getString(12);
                        String fc=a03.getString(13);
                        String validos=a03.getString(14);
                        String nulos=a03.getString(15);
                        String total=a03.getString(16);
                        String lista=a03.getString(17);
                        String participacion=a03.getString(18);
                        
                        PAN=Double.parseDouble(pan);
                        PRI=Double.parseDouble(pri);
                        PRD=Double.parseDouble(prd);
                        PT=Double.parseDouble(pt);
                        PVEM=Double.parseDouble(pvem);
                        NuevaAlianza=0;
                        Movimiento=0;
                        DNulos=Double.parseDouble(nulos);
                        DTotal=Double.parseDouble(total);
                        DListaNominal=Double.parseDouble(lista);
                        Validos=Double.parseDouble(validos);
                        Participacion=Double.parseDouble(participacion);
                        
                        partido[4]=new Partido(PRI,PAN,PRD,PT,PVEM,0,NuevaAlianza,0,Validos,DNulos,DTotal,DListaNominal,Participacion);
                        
                        
                   
                }
            a00 = VLsqlST.executeQuery("SELECT * FROM ano2000 WHERE clave =\'" + id + "\'");
         
                while(a00.next())
                {
                        
                        String Did=a00.getString(1);
                        String DNombre=a00.getString(2);
                        String panpvem=a00.getString(3);
                        String pri=a00.getString(4);
                        String prd=a00.getString(5);
                        String pt=a00.getString(6);
                        String conver=a00.getString(7);
                        String pcd=a00.getString(8);
                        String psn=a00.getString(9);
                        String parm=a00.getString(10);
                        String pas=a00.getString(11);
                        String ds=a00.getString(12);
                        String validos=a00.getString(13);
                        String nulos=a00.getString(14);
                        String total=a00.getString(15);
                        String lista=a00.getString(16);
                        String participacion=a00.getString(17);
                        
                        PAN=Double.parseDouble(panpvem)/2;
                        PRI=Double.parseDouble(pri);
                        PRD=Double.parseDouble(prd);
                        PT=Double.parseDouble(pt);
                        PVEM=Double.parseDouble(panpvem)/2;
                        NuevaAlianza=0;
                        Movimiento=0;
                        DNulos=Double.parseDouble(nulos);
                        DTotal=Double.parseDouble(total);
                        DListaNominal=Double.parseDouble(lista);
                        Validos=Double.parseDouble(validos);
                        Participacion=Double.parseDouble(participacion);
                        
                        partido[5]=new Partido(PRI,PAN,PRD,PT,PVEM,0,NuevaAlianza,0,Validos,DNulos,DTotal,DListaNominal,Participacion);
                        
                        

                }
    } 
            catch (SQLException ex) 
            {
                Logger.getLogger(PartidoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
    finally
            {
                    try 
                    {
                        a00.close();
                        a03.close();
                        a06.close();
                        a09.close();
                        a12.close();
                        a15.close();
                        VMobjConn.close();
                    } 
                    catch (SQLException ex) 
                    {
                        Logger.getLogger(PartidoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
             }
    return partido;
    }
    
}
