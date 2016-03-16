/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DelegacionDAO;

import ConexionBD.ConexionBD;
import Delegacion.Delegacion;
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
public class DelegacionDAOImpl implements DelegacionDAO {

    
 private Connection VMobjConn;
  private ResultSet VLsqlRS=null;
 private Delegacion  del=null;
    @Override
    public Delegacion getDelegacion(String id,String fecha) 
             {
       Delegacion VLobjDelegacion = null;
       
        try 
        {
            String query="";
            VMobjConn = ConexionBD.getConexion();
            Statement VLsqlST = VMobjConn.createStatement();
            if(fecha.equals("ano2012")){
          // ResultSet VLsqlRS = VLsqlST.executeQuery("SELECT * FROM \'"+fecha+"\' WHERE clave =\'" + id + "\'");
            VLsqlRS = VLsqlST.executeQuery("SELECT * FROM año2012 WHERE clave =\'" + id + "\'");
           VLobjDelegacion= getDelegacion(VLsqlRS,fecha);
            }
            if(fecha.equals("ano2006")){
          // ResultSet VLsqlRS = VLsqlST.executeQuery("SELECT * FROM \'"+fecha+"\' WHERE clave =\'" + id + "\'");
            VLsqlRS = VLsqlST.executeQuery("SELECT * FROM año2006 WHERE clave =\'" + id + "\'");
           VLobjDelegacion= getDelegacion(VLsqlRS,fecha);
            }
             if(fecha.equals("ano2000")){
          // ResultSet VLsqlRS = VLsqlST.executeQuery("SELECT * FROM \'"+fecha+"\' WHERE clave =\'" + id + "\'");
           VLsqlRS = VLsqlST.executeQuery("SELECT * FROM año2000 WHERE clave =\'" + id + "\'");
           VLobjDelegacion= getDelegacion(VLsqlRS,fecha);
            }
              if(fecha.equals("ano1994")){
          // ResultSet VLsqlRS = VLsqlST.executeQuery("SELECT * FROM \'"+fecha+"\' WHERE clave =\'" + id + "\'");
           VLsqlRS = VLsqlST.executeQuery("SELECT * FROM año1994 WHERE clave =\'" + id + "\'");
           VLobjDelegacion= getDelegacion(VLsqlRS,fecha);
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
                VMobjConn.close();
                VLsqlRS.close();
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(DelegacionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return VLobjDelegacion;
    }
    
    public Delegacion getDelegacion(ResultSet VLsqlRS,String fecha) throws SQLException{
        
         if(fecha.equals("ano2012")){
        while(VLsqlRS.next())
            {
                
                String Did=VLsqlRS.getString(1);
                String DNombre=VLsqlRS.getString(2);
                String DSecciones=VLsqlRS.getString(3);
                String DCasillas=VLsqlRS.getString(4);
                String pan=VLsqlRS.getString(5);
                String pri=VLsqlRS.getString(6);
                String prd=VLsqlRS.getString(7);
                String pvem=VLsqlRS.getString(8);
                String pt=VLsqlRS.getString(9);
                String movimiento=VLsqlRS.getString(10);
                String nuevaalianza=VLsqlRS.getString(11);
                String pripvem=VLsqlRS.getString(12);
                String prdptmc=VLsqlRS.getString(13);
                String prdpt=VLsqlRS.getString(14);
                String prdmc=VLsqlRS.getString(15);
                String ptmc=VLsqlRS.getString(16);
                String DNoRegistrados=VLsqlRS.getString(17);
                String DNulos=VLsqlRS.getString(18);
                String DTotal=VLsqlRS.getString(19);
                String DListaNominal=VLsqlRS.getString(20);
                String DParticipacion=VLsqlRS.getString(21);
                
                del = new Delegacion( Did,
                                        DNombre,
                                        DSecciones,
                                        DCasillas,
                                        pan,pri,prd,pvem,pt,movimiento,nuevaalianza,pripvem,prdptmc,
                                        prdpt,prdmc,ptmc,
                                        DNoRegistrados,
                                        DNulos,
                                        DTotal,
                                        DListaNominal,
                                        DParticipacion);
                
            }
         }
         if(fecha.equals("ano2006")){
        while(VLsqlRS.next())
            {
                String Did=VLsqlRS.getString(1);
                String DNombre=VLsqlRS.getString(2);
                String DSecciones=VLsqlRS.getString(3);
                String DCasillas=VLsqlRS.getString(4);
                String pan=VLsqlRS.getString(5);
                String alianza=VLsqlRS.getString(6);
                String porelbien=VLsqlRS.getString(7);
                String nuevaalianza=VLsqlRS.getString(8);
                String alternativa=VLsqlRS.getString(9);
                String DNoRegistrados=VLsqlRS.getString(10);
                String validos=VLsqlRS.getString(11);
                String DNulos=VLsqlRS.getString(12);
                String DTotal=VLsqlRS.getString(13);
                String DListaNominal=VLsqlRS.getString(14);
                String DParticipacion=VLsqlRS.getString(15);
                
                del = new Delegacion( Did,
                                        DNombre,
                                        DSecciones,
                                        DCasillas,
                                        pan,alianza,porelbien,nuevaalianza,alternativa,
                                        DNoRegistrados,
                                        validos,
                                        DNulos,
                                        DTotal,
                                        DListaNominal,
                                        DParticipacion);
            }
         }
          if(fecha.equals("ano2000")){
        while(VLsqlRS.next())
            {
                String Did=VLsqlRS.getString(1);
                String DNombre=VLsqlRS.getString(2);
                String DSecciones=VLsqlRS.getString(3);
                String DCasillas=VLsqlRS.getString(4);
                String ac=VLsqlRS.getString(5);
                String pri=VLsqlRS.getString(6);
                String am=VLsqlRS.getString(7);
                String pcd=VLsqlRS.getString(8);
                String parm=VLsqlRS.getString(9);
                String dsppn=VLsqlRS.getString(10);
                String DNoRegistrados=VLsqlRS.getString(11);
                String validos=VLsqlRS.getString(12);
                String DNulos=VLsqlRS.getString(13);
                String DTotal=VLsqlRS.getString(14);
                String DListaNominal=VLsqlRS.getString(15);
                String DParticipacion=VLsqlRS.getString(16);
                del = new Delegacion( Did,
                                        DNombre,
                                        DSecciones,
                                        DCasillas,
                                        ac,pri,am,pcd,parm,dsppn,
                                        DNoRegistrados,
                                        validos,
                                        DNulos,
                                        DTotal,
                                        DListaNominal,
                                        DParticipacion);
            }
          }
          if(fecha.equals("ano1994")){
        while(VLsqlRS.next())
            {
                String Did=VLsqlRS.getString(1);
                String DNombre=VLsqlRS.getString(2);
                String DSecciones=VLsqlRS.getString(3);
                String DCasillas=VLsqlRS.getString(4);
                String pan=VLsqlRS.getString(5);
                String pri=VLsqlRS.getString(6);
                String pps=VLsqlRS.getString(7);
                String prd=VLsqlRS.getString(8);
                String pfcrn=VLsqlRS.getString(9);
                String parm=VLsqlRS.getString(10);
                String uno=VLsqlRS.getString(11);
                String pt=VLsqlRS.getString(12);
                String pvem=VLsqlRS.getString(13);
                String DNoRegistrados=VLsqlRS.getString(14);
                String validos=VLsqlRS.getString(15);
                String DNulos=VLsqlRS.getString(16);
                String DTotal=VLsqlRS.getString(17);
                String DListaNominal=VLsqlRS.getString(18);
                String DParticipacion=VLsqlRS.getString(19);
                 del = new Delegacion( Did,
                                        DNombre,
                                        DSecciones,
                                        DCasillas,
                                        pan,pri,pps,prd,pfcrn,parm,uno,pt,pvem,
                                        DNoRegistrados,
                                        validos,
                                        DNulos,
                                        DTotal,
                                        DListaNominal,
                                        DParticipacion);
            }
          }
     return del;
        
        
    }
    
}
