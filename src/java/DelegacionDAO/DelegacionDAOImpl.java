/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DelegacionDAO;

import Comparativa.DBComparativa.DBComparativa;
import Comparativa.DBComparativa.PartidoPorDelegacion;
import ConexionBD.ConexionBD;
import Delegacion.Delegacion;
import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Lucia
 */
public class DelegacionDAOImpl implements DelegacionDAO {

//private Connection VMobjConn;
private ResultSet VLsqlRS=null;
private Connection VMobjConn;


@Override
public Delegacion getDelegacion(String id,String fecha) 
{
            Delegacion VLobjDelegacion = null;
            List<List<PartidoPorDelegacion>> lista;
           try { 
                        if(fecha.equals("2015")) 
                        {
                            
                            lista = new DBComparativa().getInfoByYear(fecha);
                            VLobjDelegacion= getDelegacion(lista,id,fecha);
                    
                    //                       VLsqlRS = VLsqlST.executeQuery("SELECT * FROM ano2015 WHERE clave =\'" + id + "\'");
                    //                       VLobjDelegacion= getDelegacion(VLsqlRS,fecha);
               
                        }
                        if(fecha.equals("2012"))
                        {
                            lista = new DBComparativa().getInfoByYear(fecha);
                            VLobjDelegacion= getDelegacion(lista,id,fecha);
                    //                       VLsqlRS = VLsqlST.executeQuery("SELECT * FROM ano2012 WHERE clave =\'" + id + "\'");
                    //                       VLobjDelegacion= getDelegacion(VLsqlRS,fecha);
                        }
                        if(fecha.equals("2009"))
                        {
                            lista = new DBComparativa().getInfoByYear(fecha);
                            VLobjDelegacion= getDelegacion(lista,id,fecha);
                    //                       VLsqlRS = VLsqlST.executeQuery("SELECT * FROM ano2009 WHERE clave =\'" + id + "\'");
                    //                       VLobjDelegacion= getDelegacion(VLsqlRS,fecha);
                        }
                        if(fecha.equals("2006"))
                        {
                            lista = new DBComparativa().getInfoByYear(fecha);
                            VLobjDelegacion= getDelegacion(lista,id,fecha);
                    //                       VLsqlRS = VLsqlST.executeQuery("SELECT * FROM ano2006 WHERE clave =\'" + id + "\'");
                    //                       VLobjDelegacion= getDelegacion(VLsqlRS,fecha);
                        }
                        if(fecha.equals("2003"))
                        {
                            lista = new DBComparativa().getInfoByYear(fecha);
                            VLobjDelegacion= getDelegacion(lista,id,fecha);
                    //                       VLsqlRS = VLsqlST.executeQuery("SELECT * FROM ano2003 WHERE clave =\'" + id + "\'");
                    //                       VLobjDelegacion= getDelegacion(VLsqlRS,fecha);
                        }
                        if(fecha.equals("2000"))
                        {
                            lista = new DBComparativa().getInfoByYear(fecha);
                            VLobjDelegacion= getDelegacion(lista,id,fecha);
                    //                       VLsqlRS = VLsqlST.executeQuery("SELECT * FROM ano2000 WHERE clave =\'" + id + "\'");
                    //                       VLobjDelegacion= getDelegacion(VLsqlRS,fecha);
                        }
                        
             } catch (SQLException ex) {
                    Logger.getLogger(DelegacionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                }

             return VLobjDelegacion;
}

public Delegacion getDelegacion(List<List<PartidoPorDelegacion>> lista,String id,String fecha) throws SQLException{

    String nom;
    Delegacion  del=null;
    String  val = null,nulos = null,total=null,li=null,par=null;
    
   ArrayList<String> votes;
    votes = new ArrayList<>();
    PartidoPorDelegacion delegacionTemp = null;
    switch(fecha){
        case "2015":
                   
                    nom="";
                    
                    for(List<PartidoPorDelegacion> list2 : lista)
                    {
                        for(PartidoPorDelegacion partido : list2)
                        {
                            if(partido.strCVE_Mun.equals(id))
                            {
                                if(delegacionTemp == null)
                                {
                                delegacionTemp = partido;
                                }
                                String n=String.valueOf(partido.votos);
                                votes.add(n);
                                nom=partido.strMunicipio;
                            }
                            
                        }
                    }
                        val=String.valueOf(delegacionTemp.intValidos);
                        nulos=String.valueOf(delegacionTemp.intNulo);
                        total=String.valueOf(delegacionTemp.intTotal);
                        li=String.valueOf(delegacionTemp.intLista_nominal);
                        par=String.valueOf(delegacionTemp.intParticipacion);
                        
                        System.out.println("val"+val);
                        System.out.println("nulos"+nulos);
                        System.out.println("total"+total);
                        System.out.println("li"+li);
                        System.out.println("par"+par);
  del = new Delegacion(id,nom,votes.get(0),votes.get(1),votes.get(2),votes.get(3),votes.get(4),votes.get(5),votes.get(6),votes.get(7),votes.get(8),votes.get(9),votes.get(10),votes.get(11),val,nulos,total,li,par,"");
                            votes.clear();
                           
                           
            break;
        case "2012":
                   nom="";
                    
                    for(List<PartidoPorDelegacion> list2 : lista)
                    {
                        for(PartidoPorDelegacion partido : list2)
                        {
                            if(partido.strCVE_Mun.equals(id))
                            {
                                if(delegacionTemp == null)
                                {
                                delegacionTemp = partido;
                                }
                                String n=String.valueOf(partido.votos);
                                votes.add(n);
                                nom=partido.strMunicipio;
                            }
                            
                        }
                    }
                  
                            
                        val=String.valueOf(delegacionTemp.intValidos);
                        nulos=String.valueOf(delegacionTemp.intNulo);
                        total=String.valueOf(delegacionTemp.intTotal);
                        li=String.valueOf(delegacionTemp.intLista_nominal);
                        par=String.valueOf(delegacionTemp.intParticipacion);
        del = new Delegacion(id,nom,votes.get(0),votes.get(1),votes.get(2),votes.get(3),votes.get(4),votes.get(5),votes.get(6),votes.get(7),votes.get(8),val,nulos,total,li,par);
                            votes.clear();
                           
                            
            break;
        case "2009":
                  nom="";
                    
                    for(List<PartidoPorDelegacion> list2 : lista)
                    {
                        for(PartidoPorDelegacion partido : list2)
                        {
                            if(partido.strCVE_Mun.equals(id))
                            {
                                if(delegacionTemp == null)
                                {
                                delegacionTemp = partido;
                                }
                                String n=String.valueOf(partido.votos);
                                votes.add(n);
                                nom=partido.strMunicipio;
                            }
                            
                        }
                    }
                  
                       val=String.valueOf(delegacionTemp.intValidos);
                        nulos=String.valueOf(delegacionTemp.intNulo);
                        total=String.valueOf(delegacionTemp.intTotal);
                        li=String.valueOf(delegacionTemp.intLista_nominal);
                        par=String.valueOf(delegacionTemp.intParticipacion);
       del = new Delegacion(id,nom,votes.get(0),votes.get(1),votes.get(2),votes.get(3),votes.get(4),votes.get(5),votes.get(6),votes.get(7),votes.get(8),votes.get(9),votes.get(10),votes.get(11),val,nulos,total,li,par);
                            votes.clear();
                            
                             
            break;
        case "2006":
            nom="";
                    
                    for(List<PartidoPorDelegacion> list2 : lista)
                    {
                        for(PartidoPorDelegacion partido : list2)
                        {
                            if(partido.strCVE_Mun.equals(id))
                            {
                                if(delegacionTemp == null)
                                {
                                delegacionTemp = partido;
                                }
                                String n=String.valueOf(partido.votos);
                                votes.add(n);
                                nom=partido.strMunicipio;
                            }
                            
                        }
                    }
                  
        
                        val=String.valueOf(delegacionTemp.intValidos);
                        nulos=String.valueOf(delegacionTemp.intNulo);
                        total=String.valueOf(delegacionTemp.intTotal);
                        li=String.valueOf(delegacionTemp.intLista_nominal);
                        par=String.valueOf(delegacionTemp.intParticipacion);
                            del = new Delegacion(id,nom,votes.get(0),votes.get(1),votes.get(2),votes.get(3),votes.get(4),votes.get(5),val,nulos,total,li,par);
                            votes.clear();
                             
                            
            break;
        case "2003":
            nom="";
                    
                    for(List<PartidoPorDelegacion> list2 : lista)
                    {
                        for(PartidoPorDelegacion partido : list2)
                        {
                            if(partido.strCVE_Mun.equals(id))
                            {
                                if(delegacionTemp == null)
                                {
                                delegacionTemp = partido;
                                }
                                String n=String.valueOf(partido.votos);
                                votes.add(n);
                                nom=partido.strMunicipio;
                            }
                            
                        }
                    }
                  
        
                       val=String.valueOf(delegacionTemp.intValidos);
                        nulos=String.valueOf(delegacionTemp.intNulo);
                        total=String.valueOf(delegacionTemp.intTotal);
                        li=String.valueOf(delegacionTemp.intLista_nominal);
                        par=String.valueOf(delegacionTemp.intParticipacion);
                            del = new Delegacion(id,nom,votes.get(0),votes.get(1),votes.get(2),votes.get(3),votes.get(4),votes.get(5),votes.get(6),votes.get(7),votes.get(8),votes.get(9),votes.get(10),val,nulos,total,li,par);
                            votes.clear();
                             
                           
                  
            break;
        case "2000":
                  nom="";
                    
                    for(List<PartidoPorDelegacion> list2 : lista)
                    {
                        for(PartidoPorDelegacion partido : list2)
                        {
                            if(partido.strCVE_Mun.equals(id))
                            {
                                if(delegacionTemp == null)
                                {
                                delegacionTemp = partido;
                                }
                                String n=String.valueOf(partido.votos);
                                votes.add(n);
                                nom=partido.strMunicipio;
                            }
                            
                        }
                    }
                  
        
                       val=String.valueOf(delegacionTemp.intValidos);
                        nulos=String.valueOf(delegacionTemp.intNulo);
                        total=String.valueOf(delegacionTemp.intTotal);
                        li=String.valueOf(delegacionTemp.intLista_nominal);
                        par=String.valueOf(delegacionTemp.intParticipacion);
                    del = new Delegacion(id,nom,votes.get(0),votes.get(1),votes.get(2),votes.get(3),votes.get(4),votes.get(5),votes.get(6),votes.get(7),votes.get(8),votes.get(9),val,nulos,total,li,par);
                    votes.clear();
                     
                    
                  
            break;
    }
   
    return del;
    
}
    
public Delegacion getDelegacion(ResultSet VLsqlRS,String fecha) throws SQLException
{
        Delegacion  del=null;
        if(fecha.equals("ano2015"))
        {
                while(VLsqlRS.next())
                    {
                        
                        String Did=VLsqlRS.getString(1);
                        String DNombre=VLsqlRS.getString(2);
                        String pan=VLsqlRS.getString(3);
                        String pripvem=VLsqlRS.getString(4);
                        String prdpt=VLsqlRS.getString(5);
                        String prdptnuevaalianza=VLsqlRS.getString(6);
                        String prd=VLsqlRS.getString(7);
                        String pt=VLsqlRS.getString(8);
                        String movimiento=VLsqlRS.getString(9);
                        String nuevaalianza=VLsqlRS.getString(10);
                        String morena=VLsqlRS.getString(11);
                        String ph=VLsqlRS.getString(12);
                        String pes=VLsqlRS.getString(13);
                        String inde=VLsqlRS.getString(14);
                        String Validos=VLsqlRS.getString(15);
                        String DNulos=VLsqlRS.getString(16);
                        String DTotal=VLsqlRS.getString(17);
                        String DListaNominal=VLsqlRS.getString(18);
                        String DParticipacion=VLsqlRS.getString(19);
                        String a="a";

                        del = new Delegacion( Did,
                                                DNombre,
                                                pan,
                                                pripvem,
                                                prdpt,
                                                prdptnuevaalianza,
                                                prd,
                                                pt,
                                                movimiento,
                                                nuevaalianza,
                                                morena,
                                                ph,
                                                pes,
                                                inde,
                                                Validos,
                                                DNulos,
                                                DTotal,
                                                DListaNominal,
                                                DParticipacion,
                                                a);

                    }
         }
        if(fecha.equals("ano2012"))
        {
                while(VLsqlRS.next())
                    {

                        String Did=VLsqlRS.getString(1);
                        String DNombre=VLsqlRS.getString(2);
                        String pan=VLsqlRS.getString(3);
                        String pri=VLsqlRS.getString(4);
                        String prd=VLsqlRS.getString(5);
                        String pt=VLsqlRS.getString(6);
                        String pvem=VLsqlRS.getString(7);
                        String mc=VLsqlRS.getString(8);
                        String na=VLsqlRS.getString(9);
                        String pripvem=VLsqlRS.getString(10);
                        String prdptmc=VLsqlRS.getString(11);
                        String Validos=VLsqlRS.getString(12);
                        String DNulos=VLsqlRS.getString(13);
                        String DTotal=VLsqlRS.getString(14);
                        String DListaNominal=VLsqlRS.getString(15);
                        String DParticipacion=VLsqlRS.getString(16);

                        del = new Delegacion( Did,
                                                DNombre,
                                                pan,
                                                pri,
                                                prd,
                                                pt,
                                                pvem,
                                                mc,
                                                na,
                                                pripvem,
                                                prdptmc,
                                                Validos,
                                                DNulos,
                                                DTotal,
                                                DListaNominal,
                                                DParticipacion);

                    }
         }
         if(fecha.equals("ano2009"))
         {
                    while(VLsqlRS.next())
                    {
                        String Did=VLsqlRS.getString(1);
                        String DNombre=VLsqlRS.getString(2);
                        String pan=VLsqlRS.getString(3);
                        String pri=VLsqlRS.getString(4);
                        String prd=VLsqlRS.getString(5);
                        String pt=VLsqlRS.getString(6);
                        String pvem=VLsqlRS.getString(7);
                        String conver=VLsqlRS.getString(8);
                        String nuevaalianza=VLsqlRS.getString(9);
                        String psd=VLsqlRS.getString(10);
                        String prdptconver=VLsqlRS.getString(11);
                        String prdpt=VLsqlRS.getString(12);
                        String prdconver=VLsqlRS.getString(13);
                        String ptconver=VLsqlRS.getString(14);
                        String Validos=VLsqlRS.getString(15);
                        String DNulos=VLsqlRS.getString(16);
                        String DTotal=VLsqlRS.getString(17);
                        String DListaNominal=VLsqlRS.getString(18);
                        String DParticipacion=VLsqlRS.getString(19);
                        

                        del = new Delegacion( Did,
                                                DNombre,
                                                pan,
                                                pri,
                                                prd,
                                                pt,
                                                pvem,
                                                conver,
                                                nuevaalianza,
                                                psd,
                                                prdptconver,
                                                prdpt,
                                                prdconver,
                                                ptconver,
                                                Validos,
                                                DNulos,
                                                DTotal,
                                                DListaNominal,
                                                DParticipacion
                                                );
                    }
         }
        if(fecha.equals("ano2006"))
        {
                while(VLsqlRS.next())
                    {
                        String Did=VLsqlRS.getString(1);
                        String DNombre=VLsqlRS.getString(2);
                        String pan=VLsqlRS.getString(3);
                        String pripvem=VLsqlRS.getString(4);
                        String prdptconver=VLsqlRS.getString(5);
                        String nuevaalianza=VLsqlRS.getString(6);
                        String pasc=VLsqlRS.getString(7);
                        String pannuevaalianza=VLsqlRS.getString(8);
                        String validos=VLsqlRS.getString(9);
                        String DNulos=VLsqlRS.getString(10);
                        String DTotal=VLsqlRS.getString(11);
                        String DListaNominal=VLsqlRS.getString(12);
                        String DParticipacion=VLsqlRS.getString(13);
                        
                        del = new Delegacion( Did,
                                                DNombre,
                                                pan,
                                                pripvem,
                                                prdptconver,
                                                nuevaalianza,
                                                pasc,
                                                pannuevaalianza,
                                                validos,
                                                DNulos,
                                                DTotal,
                                                DListaNominal,
                                                DParticipacion);
                    }
          }
        if(fecha.equals("ano2003"))
          {
                while(VLsqlRS.next())
                    {
                        String Did=VLsqlRS.getString(1);
                        String DNombre=VLsqlRS.getString(2);
                        String pan=VLsqlRS.getString(3);
                        String pri=VLsqlRS.getString(4);
                        String prd=VLsqlRS.getString(5);
                        String pt=VLsqlRS.getString(6);
                        String pvem=VLsqlRS.getString(7);
                        String conver=VLsqlRS.getString(8);
                        String psn=VLsqlRS.getString(9);
                        String pas=VLsqlRS.getString(10);
                        String mp=VLsqlRS.getString(11);
                        String plm=VLsqlRS.getString(12);
                        String fc=VLsqlRS.getString(13);
                        String validos=VLsqlRS.getString(14);
                        String DNulos=VLsqlRS.getString(15);
                        String DTotal=VLsqlRS.getString(16);
                        String DListaNominal=VLsqlRS.getString(17);
                        String DParticipacion=VLsqlRS.getString(18);
                         del = new Delegacion( Did,
                                                DNombre,
                                                pan,
                                                pri,
                                                prd,
                                                pt,
                                                pvem,
                                                conver,
                                                psn,
                                                pas,
                                                mp,
                                                plm,
                                                fc,
                                                validos,
                                                DNulos,
                                                DTotal,
                                                DListaNominal,
                                                DParticipacion);
                    }
          }
          if(fecha.equals("ano2000"))
          {
                while(VLsqlRS.next())
                    {
                        String Did=VLsqlRS.getString(1);
                        String DNombre=VLsqlRS.getString(2);
                        String panpvem=VLsqlRS.getString(3);
                        String pri=VLsqlRS.getString(4);
                        String prd=VLsqlRS.getString(5);
                        String pt=VLsqlRS.getString(6);
                        String conver=VLsqlRS.getString(7);
                        String pcd=VLsqlRS.getString(8);
                        String psn=VLsqlRS.getString(9);
                        String parm=VLsqlRS.getString(10);
                        String pas=VLsqlRS.getString(11);
                        String ds=VLsqlRS.getString(12);
                        String validos=VLsqlRS.getString(13);
                        String DNulos=VLsqlRS.getString(14);
                        String DTotal=VLsqlRS.getString(15);
                        String DListaNominal=VLsqlRS.getString(16);
                        String DParticipacion=VLsqlRS.getString(17);
                         del = new Delegacion( Did,
                                                DNombre,
                                                panpvem,
                                                pri,
                                                prd,
                                                pt,
                                                conver,
                                                pcd,
                                                psn,
                                                parm,
                                                pas,
                                                ds,
                                                validos,
                                                DNulos,
                                                DTotal,
                                                DListaNominal,
                                                DParticipacion);
                    }
                
          }
      
     return del;
}



@Override
public String[][] compararParticipacion(String cve) 
{
        String a[][]=new String[6][5];
        
        int ide;int ano;
        DBComparativa com = new DBComparativa();
        ResultSet a00=null;
        ResultSet a03=null;
        ResultSet a06=null;
        ResultSet a09=null;
        ResultSet a12=null;
        ResultSet a15=null;
        VMobjConn = ConexionBD.getConexion();
          
        String f1="1";
        String f2="2";
        String f3="3";
        String f4="4";
        String f5="5";
        String f6="6";
    try 
    {
        
        Statement VLsqlST = VMobjConn.createStatement();       
        ide=com.getIdDel(cve);
        System.out.println(cve+"CVE->id"+ide+"...");
        
      
        
        
        
        a00 = VLsqlST.executeQuery("SELECT validos,nulo,total,lista_nominal,participacion FROM ResultadoElectoral WHERE id_Anio = \'"+f1+"\' AND Id_Delegacion = \'"+ide+"\'");
         
                while(a00.next())
                {

                    a[0][0]=a00.getString("validos");
                    a[0][1]=a00.getString("nulo");
                    a[0][2]=a00.getString("total");
                    a[0][3]=a00.getString("lista_nominal");
                    a[0][4]=a00.getString("participacion");
                    
                    

                }   
        a03 = VLsqlST.executeQuery("SELECT validos,nulo,total,lista_nominal,participacion FROM ResultadoElectoral WHERE id_Anio = \'"+f2+"\' AND Id_Delegacion = \'"+ide+"\'");
         
                while(a03.next())
                {

                    a[1][0]=a03.getString("validos");
                    a[1][1]=a03.getString("nulo");
                    a[1][2]=a03.getString("total");
                    a[1][3]=a03.getString("lista_nominal");
                    a[1][4]=a03.getString("participacion");

                }
            a06 = VLsqlST.executeQuery("SELECT validos,nulo,total,lista_nominal,participacion FROM ResultadoElectoral WHERE id_Anio = \'"+f3+"\' AND Id_Delegacion = \'"+ide+"\'");
                while(a06.next())
                {

                    a[2][0]=a06.getString("validos");
                    a[2][1]=a06.getString("nulo");
                    a[2][2]=a06.getString("total");
                    a[2][3]=a06.getString("lista_nominal");
                    a[2][4]=a06.getString("participacion");

               }
            a09 = VLsqlST.executeQuery("SELECT validos,nulo,total,lista_nominal,participacion FROM ResultadoElectoral WHERE id_Anio = \'"+f4+"\' AND Id_Delegacion = \'"+ide+"\'");
                while(a09.next())
                {
                    a[3][0]=a09.getString("validos");
                    a[3][1]=a09.getString("nulo");
                    a[3][2]=a09.getString("total");
                    a[3][3]=a09.getString("lista_nominal");
                    a[3][4]=a09.getString("participacion");

               }
         
           a12 = VLsqlST.executeQuery("SELECT validos,nulo,total,lista_nominal,participacion FROM ResultadoElectoral WHERE id_Anio = \'"+f5+"\' AND Id_Delegacion = \'"+ide+"\'");
                while(a12.next())
                {
                    a[4][0]=a12.getString("validos");
                    a[4][1]=a12.getString("nulo");
                    a[4][2]=a12.getString("total");
                    a[4][3]=a12.getString("lista_nominal");
                    a[4][4]=a12.getString("participacion");

                 }
            a15 = VLsqlST.executeQuery("SELECT validos,nulo,total,lista_nominal,participacion FROM ResultadoElectoral WHERE id_Anio = \'"+f6+"\' AND Id_Delegacion = \'"+ide+"\'");
                while(a15.next())
                {
                    
                    //int v=Integer.parseInt(a15.getString("total"))-Integer.parseInt(a15.getString("nulos"));
                    a[5][0]=a15.getString("validos");
                    a[5][1]=a15.getString("nulo");
                    a[5][2]=a15.getString("total");
                    a[5][3]=a15.getString("lista_nominal");
                    a[5][4]=a15.getString("participacion");;

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



 
}

