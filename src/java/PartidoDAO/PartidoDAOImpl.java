/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PartidoDAO;

import Comparativa.DBComparativa.DBComparativa;
import Comparativa.DBComparativa.PartidoPorDelegacion;
import Partido.Partido;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucia
 */
public class PartidoDAOImpl implements PartidoDAO {
 
    private Partido partido[]=new Partido[7];
    
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
    
    
    DBComparativa com = new DBComparativa();
    
    @Override
    public Partido[] getPartido(String id) {
    try 
    {
                List<List<PartidoPorDelegacion>> par;
                par = new DBComparativa().getInfoByYear("2015");
                partido[0]=tendencias(par,id,"2015");
                par.clear();
                par = new DBComparativa().getInfoByYear("2012");
                partido[1]=tendencias(par,id,"2012");
                par.clear();
                par = new DBComparativa().getInfoByYear("2009");
                partido[2]=tendencias(par,id,"2009");
                par.clear();
                par = new DBComparativa().getInfoByYear("2006");
                partido[3]=tendencias(par,id,"2006");
                par.clear();
                par = new DBComparativa().getInfoByYear("2003");
                partido[4]=tendencias(par,id,"2003");
                par.clear();
                par = new DBComparativa().getInfoByYear("2000");
                partido[5]=tendencias(par,id,"2000");
                par.clear();
        
        
     } 
            catch (SQLException ex) 
            {
                Logger.getLogger(PartidoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
    
    return partido;
    }

   public Partido tendencias(List<List<PartidoPorDelegacion>> lista,String id,String fecha) throws SQLException{
        Partido pp = null;
        String pan;
        String pripvem;
        String prdpt;
        String prdptnuevaalianza;
        String prd;
        String pt;
        String movimiento;
        String nuevaalianza;
        String morena;
        String ph;
        String pes;
        
        String  val ,nulos ,total,li,pa;
        ArrayList<String> votes;
        votes = new ArrayList<>();
        Double c;
        Double cc;
        String pri;
        String pvem;
        String mc;
        String na;
        String prdptmc;
        String conver;
        String prdptconver;
        String prdconver;
        String ptconver;
        PartidoPorDelegacion delegacionTemp = null;
       switch(fecha){
           case "2015":
           //2015
               
                for(List<PartidoPorDelegacion> list2 : lista)
                    {
                        for(PartidoPorDelegacion p : list2)
                        {
                            if(p.strCVE_Mun.equals(id))
                            {
                                if(delegacionTemp == null)
                                {
                                delegacionTemp = p;
                                }
                                String n=String.valueOf(p.votos);
                                votes.add(n);
                            }
                            
                        }
                    }
                        pan=votes.get(0);
                        pripvem=votes.get(1);
                        prdpt=votes.get(2);
                        prdptnuevaalianza=votes.get(3);
                        prd=votes.get(4);
                        pt=votes.get(5);
                        movimiento=votes.get(6);
                        nuevaalianza=votes.get(7);
                        morena=votes.get(8);
                        ph=votes.get(9);
                        pes=votes.get(10);
                        val=String.valueOf(delegacionTemp.intValidos);
                        nulos=String.valueOf(delegacionTemp.intNulo);
                        total=String.valueOf(delegacionTemp.intTotal);
                        li=String.valueOf(delegacionTemp.intLista_nominal);
                        pa=String.valueOf(delegacionTemp.intParticipacion);
                       
                        
                        c=Double.parseDouble(prdpt)/2;
                        cc=Double.parseDouble(prdptnuevaalianza)/3;
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
                        DListaNominal=Double.parseDouble(li);
                        Validos=Double.parseDouble(val);
                        Participacion=Double.parseDouble(pa);
                        pp=new Partido(PRI,PAN,PRD,PT,PVEM,Morena,NuevaAlianza,Movimiento,Validos,DNulos,DTotal,DListaNominal,Participacion);
                       
               break;
           case "2012":
              for(List<PartidoPorDelegacion> list2 : lista)
                    {
                        for(PartidoPorDelegacion p : list2)
                        {
                            if(p.strCVE_Mun.equals(id))
                            {
                                if(delegacionTemp == null)
                                {
                                delegacionTemp = p;
                                }
                                String n=String.valueOf(p.votos);
                                votes.add(n);
                                
                            }
                            
                        }
                    }
                        pan=votes.get(0);
                        pri=votes.get(1);
                        prd=votes.get(2);
                        pt=votes.get(3);
                        pvem=votes.get(4);
                        mc=votes.get(5);
                        na=votes.get(6);
                        pripvem=votes.get(7);
                        prdptmc=votes.get(8);
                        
                        val=String.valueOf(delegacionTemp.intValidos);
                        nulos=String.valueOf(delegacionTemp.intNulo);
                        total=String.valueOf(delegacionTemp.intTotal);
                        li=String.valueOf(delegacionTemp.intLista_nominal);
                        pa=String.valueOf(delegacionTemp.intParticipacion);
                        
                        c=Double.parseDouble(pripvem)/2;
                        cc=Double.parseDouble(prdptmc)/3;
                        
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
                        DListaNominal=Double.parseDouble(li);
                        Validos=Double.parseDouble(val);
                        Participacion=Double.parseDouble(pa);
                        
                        
                       pp=new Partido(PRI,PAN,PRD,PT,PVEM,0,NuevaAlianza,Movimiento,Validos,DNulos,DTotal,DListaNominal,Participacion);

                 
               break;
           case "2009":
                    for(List<PartidoPorDelegacion> list2 : lista)
                    {
                        for(PartidoPorDelegacion p : list2)
                        {
                            if(p.strCVE_Mun.equals(id))
                            {
                                if(delegacionTemp == null)
                                {
                                delegacionTemp = p;
                                }
                                String n=String.valueOf(p.votos);
                                votes.add(n);
                                
                            }
                            
                        }
                    }
                        pan=votes.get(0);
                        pri=votes.get(1);
                        prd=votes.get(2);
                        pt=votes.get(3);
                        pvem=votes.get(4);
                        conver=votes.get(5);
                        nuevaalianza=votes.get(6);
                        String psd=votes.get(7);
                        prdptconver=votes.get(8);
                        prdpt=votes.get(9);
                        prdconver=votes.get(10);
                        ptconver=votes.get(11);
                        
                        val=String.valueOf(delegacionTemp.intValidos);
                        nulos=String.valueOf(delegacionTemp.intNulo);
                        total=String.valueOf(delegacionTemp.intTotal);
                        li=String.valueOf(delegacionTemp.intLista_nominal);
                        pa=String.valueOf(delegacionTemp.intParticipacion);
                        
                        c=Double.parseDouble(prdptconver)/3;
                        cc=Double.parseDouble(prdpt)/2;
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
                        DListaNominal=Double.parseDouble(li);
                        Validos=Double.parseDouble(val);
                        Participacion=Double.parseDouble(pa);
                        
                       pp=new Partido(PRI,PAN,PRD,PT,PVEM,0,NuevaAlianza,0,Validos,DNulos,DTotal,DListaNominal,Participacion);
                           
            
               break;
           case "2006":
                for(List<PartidoPorDelegacion> list2 : lista)
                    {
                        for(PartidoPorDelegacion p : list2)
                        {
                            if(p.strCVE_Mun.equals(id))
                            {
                                if(delegacionTemp == null)
                                {
                                delegacionTemp = p;
                                }
                                String n=String.valueOf(p.votos);
                                votes.add(n);
                                
                            }
                            
                        }
                    }
                        pan=votes.get(0);
                        pripvem=votes.get(1);
                        prdptconver=votes.get(2);
                        nuevaalianza=votes.get(3);
                        String pasc=votes.get(4);
                        String pannuevaalianza=votes.get(5);
                        
                        val=String.valueOf(delegacionTemp.intValidos);
                        nulos=String.valueOf(delegacionTemp.intNulo);
                        total=String.valueOf(delegacionTemp.intTotal);
                        li=String.valueOf(delegacionTemp.intLista_nominal);
                        pa=String.valueOf(delegacionTemp.intParticipacion);
                       
                        c=Double.parseDouble(prdptconver)/3;
                        cc=Double.parseDouble(pannuevaalianza)/2;
                        
                        PAN=Double.parseDouble(pan)+cc;
                        PRI=Double.parseDouble(pripvem)/2;
                        PRD=c;
                        PT=c;
                        NuevaAlianza=Double.parseDouble(nuevaalianza)+cc;
                        PVEM=Double.parseDouble(pripvem)/2;
                        DNulos=Double.parseDouble(nulos);
                        DTotal=Double.parseDouble(total);
                        DListaNominal=Double.parseDouble(li);
                        Validos=Double.parseDouble(val);
                        Participacion=Double.parseDouble(pa);
                        
                        pp=new Partido(PRI,PAN,PRD,PT,PVEM,0,NuevaAlianza,0,Validos,DNulos,DTotal,DListaNominal,Participacion);
                      
            
               break;
           case "2003":
               for(List<PartidoPorDelegacion> list2 : lista)
                    {
                        for(PartidoPorDelegacion p : list2)
                        {
                            if(p.strCVE_Mun.equals(id))
                            {
                                if(delegacionTemp == null)
                                {
                                delegacionTemp = p;
                                }
                                String n=String.valueOf(p.votos);
                                votes.add(n);
                                
                            }
                            
                        }
                    }
                        pan=votes.get(0);
                        pri=votes.get(1);
                        prd=votes.get(2);
                        pt=votes.get(3);
                        pvem=votes.get(4);
                        conver=votes.get(5);
                        String psn=votes.get(6);
                        String pas=votes.get(7);
                        String mp=votes.get(8);
                        String plm=votes.get(9);
                        String fc=votes.get(10);
                        
                        val=String.valueOf(delegacionTemp.intValidos);
                        nulos=String.valueOf(delegacionTemp.intNulo);
                        total=String.valueOf(delegacionTemp.intTotal);
                        li=String.valueOf(delegacionTemp.intLista_nominal);
                        pa=String.valueOf(delegacionTemp.intParticipacion);
                         
                        PAN=Double.parseDouble(pan);
                        PRI=Double.parseDouble(pri);
                        PRD=Double.parseDouble(prd);
                        PT=Double.parseDouble(pt);
                        PVEM=Double.parseDouble(pvem);
                        NuevaAlianza=0;
                        Movimiento=0;
                        DNulos=Double.parseDouble(nulos);
                        DTotal=Double.parseDouble(total);
                        DListaNominal=Double.parseDouble(li);
                        Validos=Double.parseDouble(val);
                        Participacion=Double.parseDouble(pa);
                        
                       pp=new Partido(PRI,PAN,PRD,PT,PVEM,0,NuevaAlianza,0,Validos,DNulos,DTotal,DListaNominal,Participacion);
                     
            
               break;
           case "2000":
               for(List<PartidoPorDelegacion> list2 : lista)
                    {
                        for(PartidoPorDelegacion p : list2)
                        {
                            if(p.strCVE_Mun.equals(id))
                            {
                                if(delegacionTemp == null)
                                {
                                delegacionTemp = p;
                                }
                                String n=String.valueOf(p.votos);
                                votes.add(n);
                                
                            }
                            
                        }
                    } 
                        String panpvem=votes.get(0);
                        pri=votes.get(1);
                        prd=votes.get(2);
                        pt=votes.get(3);
                        conver=votes.get(4);
                        String pcd=votes.get(5);
                        psn=votes.get(6);
                        String parm=votes.get(7);
                        pas=votes.get(8);
                        String ds=votes.get(9);
                        
                        val=String.valueOf(delegacionTemp.intValidos);
                        nulos=String.valueOf(delegacionTemp.intNulo);
                        total=String.valueOf(delegacionTemp.intTotal);
                        li=String.valueOf(delegacionTemp.intLista_nominal);
                        pa=String.valueOf(delegacionTemp.intParticipacion);
                        
                        
                        PAN=Double.parseDouble(panpvem)/2;
                        PRI=Double.parseDouble(pri);
                        PRD=Double.parseDouble(prd);
                        PT=Double.parseDouble(pt);
                        PVEM=Double.parseDouble(panpvem)/2;
                        NuevaAlianza=0;
                        Movimiento=0;
                        DNulos=Double.parseDouble(nulos);
                        DTotal=Double.parseDouble(total);
                        DListaNominal=Double.parseDouble(li);
                        Validos=Double.parseDouble(val);
                        Participacion=Double.parseDouble(pa);
                        
                        pp=new Partido(PRI,PAN,PRD,PT,PVEM,0,NuevaAlianza,0,Validos,DNulos,DTotal,DListaNominal,Participacion);
                       
               break;
           default:
               break;
       }
       
        votes.clear();
        lista.clear();
       
        return pp;
       
   }
    
}
