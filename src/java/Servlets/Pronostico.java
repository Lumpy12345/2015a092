/*,
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import Comparativa.DBComparativa.DBComparativa;
import Comparativa.DBComparativa.Historial;
import Delegacion.Delegacion;
import DelegacionDAO.DelegacionDAOImpl;
import Partido.Partido;
import PartidoDAO.PartidoDAOImpl;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lucia
 */
public class Pronostico extends HttpServlet {
    
    int PAN6;
    int PRI6;
    int PRD6;
    int PT6;
    int PVEM6;
    int Morena6;
    int NuevaAlianza6;
    int Movimiento6;
    int Validos6;
    int DNulos6;
    int DTotal6;
    int DListaNominal6;
    int Participacion6;
    int Sumados;
    int Lista;
    double PAN4;
        double PRI4;
        double PRD4;
        double PT4;
        double PVEM4;
        double NuevaAlianza4;
        //double Movimiento4;
        double Validos4;
        double DNulos4;
        double DTotal4;
        double DListaNominal4;
        double Participacion4;
         double PAN5;
        double PRI5;
        double PRD5;
        double PT5;
        double PVEM5;
        double NuevaAlianza5;
        double Movimiento5;
        double Validos5;
        double DNulos5;
        double DTotal5;
        double DListaNominal5;
        double Participacion5;
        double PAN3;
        double PRI3;
        double PRD3;
        double PT3;
        double PVEM3;
        double NuevaAlianza3;
       // double Movimiento3;
        double Validos3;
        double DNulos3;
        double DTotal3;
        double DListaNominal3;
        double Participacion3;
         double PAN;
        double PRI;
        double PRD;
        double PT;
        double PVEM;
        double Morena;
        double NuevaAlianza;
       // double Movimiento;
        double Validos;
        double DNulos;
        double DTotal;
        double DListaNominal;
        double Participacion;
         double PAN2;
        double PRI2;
        double PRD2;
        double PT2;
        double PVEM2;
       double NuevaAlianza2;
        //double Movimiento2;
        double Validos2;
        double DNulos2;
        double DTotal2;
        double DListaNominal2;
        double Participacion2;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String sesion = request.getParameter("sesion");
        
        String id=request.getParameter("CVE");
         //    ***********************************************

        
        try
        {
            int idDel = new DBComparativa().getIdDel(id);
            int idAnio = new DBComparativa().getIdAnio("2000");
            int idResultadoElectoral =  new DBComparativa().getIdResultadoElectoral(idDel, idAnio);
            
            Historial historial = new Historial();

            historial.intSesion = Integer.valueOf(sesion);
            historial.intIdResultadoElectoral = idResultadoElectoral;
            historial.strTipo = "Obtener delegacion";
            historial.setDaemon(true);

            historial.start();
                
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
//    ******************************************************
        PartidoDAOImpl p=new PartidoDAOImpl();
        Partido partido[];
        partido=p.getPartido(id);
        
        // tasa 00-03
       
        PAN=incrementoPAN(partido,5);
        PRI=incrementoPRI(partido, 5);
        PRD=incrementoPRD(partido, 5);
        PT=incrementoPT(partido,5);
        PVEM=incrementoPVEM(partido,5);
        Validos=incrementoValidos(partido, 5);
        DNulos=incrementoNul(partido, 5);
        DTotal=incrementoTot(partido, 5);
        DListaNominal=incrementoList(partido,5);
        Participacion=incrementoPart(partido, 5);
        
        //tasa 03-06
       
        PAN2=incrementoPAN(partido,4);
        PRI2=incrementoPRI(partido, 4);
        PRD2=incrementoPRD(partido, 4);
        PT2=incrementoPT(partido,4);
        PVEM2=incrementoPVEM(partido,4);
        Validos2=incrementoValidos(partido, 4);
        DNulos2=incrementoNul(partido, 4);
        DTotal2=incrementoTot(partido, 4);
        DListaNominal2=incrementoList(partido,4);
        Participacion2=incrementoPart(partido, 4);
         
        //tasa 06-09
        
        PAN3=incrementoPAN(partido,3);
        PRI3=incrementoPRI(partido, 3);
        PRD3=incrementoPRD(partido, 3);
        PT3=incrementoPT(partido,3);
        PVEM3=incrementoPVEM(partido,3);
        NuevaAlianza3=incrementoNuevaAlianza(partido,3);
        Validos3=incrementoValidos(partido, 3);
        DNulos3=incrementoNul(partido, 3);
        DTotal3=incrementoTot(partido, 3);
        DListaNominal3=incrementoList(partido,3);
        Participacion3=incrementoPart(partido, 3);
        
        //tasa 09 12
        
        PAN4=incrementoPAN(partido,2);
        PRI4=incrementoPRI(partido, 2);
        PRD4=incrementoPRD(partido, 2);
        PT4=incrementoPT(partido,2);
        PVEM4=incrementoPVEM(partido,2);
        NuevaAlianza4=incrementoNuevaAlianza(partido,2);
        Validos4=incrementoValidos(partido,2);
        DNulos4=incrementoNul(partido,2);
        DTotal4=incrementoTot(partido,2);
        DListaNominal4=incrementoList(partido,2);
        Participacion4=incrementoPart(partido,2);
        
        //12-15
       
        PAN5=incrementoPAN(partido,1);
        PRI5=incrementoPRI(partido,1);
        PRD5=incrementoPRD(partido,1);
        PT5=incrementoPT(partido,1);
        PVEM5=incrementoPVEM(partido,1);
        Morena=incrementoMorena(partido);
        NuevaAlianza5=incrementoNuevaAlianza(partido,1);
        Movimiento5=incrementoMovimiento(partido,1);
        Validos5=incrementoValidos(partido,1);
        DNulos5=incrementoNul(partido,1);
        DTotal5=incrementoTot(partido,1);
        DListaNominal5=incrementoList(partido,1);
        Participacion5=incrementoPart(partido,1);
       
        Lista=DListaNominal6;
        
        
        PAN6=p18(partido[0].getPAN(),PAN,PAN2,PAN3,PAN4,PAN5,1);
        PRI6=p18(partido[0].getPRI(),PRI,PRI2,PRI3,PRI4,PRI5,1);
        PRD6=p18(partido[0].getPRD(),PRD,PRD2,PRD3,PRD4,PRD5,1);
        Morena6=p18(partido[0].getMorena(),0,0,0,0,Morena,2);
        PT6=p18(partido[0].getPT(),PT,PT2,PT3,PT4,PT5,1);
        PVEM6=p18(partido[0].getPVEM(),PVEM,PVEM2,PVEM3,PVEM4,PVEM5,1);
        NuevaAlianza6=p18(partido[0].getNuevaAlianza(),0,0,NuevaAlianza3,NuevaAlianza4,NuevaAlianza5,3);
        Movimiento6=p18(partido[0].getMovimiento(),0,0,0,0,Movimiento5,2);
        Validos6=p18(partido[0].getValidos(),Validos,Validos2,Validos3,Validos4,Validos5,1);
        DNulos6=p18(partido[0].getDNulos(),DNulos,DNulos2,DNulos3,DNulos4,DNulos5,1);
        DTotal6=p18(partido[0].getDTotal(),DTotal,DTotal2,DTotal3,DTotal4,DTotal5,1);
        DListaNominal6=p18(partido[0].getDListaNominal(),DListaNominal,DListaNominal2,DListaNominal3,DListaNominal4,DListaNominal5,1);
        Participacion6=p18(partido[0].getParticipacion(),Participacion,Participacion2,Participacion3,Participacion4,Participacion5,1);
        
        
        Map map=new HashMap();
      
        map.put("PAN", PAN);
        map.put("PRI", PRI);
        map.put("PRD",PRD);
        map.put("PT", PT);
        map.put("PVEM",PVEM);
        map.put("DValidos",Validos);
        map.put("DNulos",DNulos);
        map.put("DTotal",DTotal);
        map.put("DListaNominal",DListaNominal);
        map.put("Participacion",Participacion);
        
        map.put("PAN2", PAN2);
        map.put("PRI2", PRI2);
        map.put("PRD2",PRD2);
        map.put("PT2", PT2);
        map.put("PVEM2",PVEM2);
        map.put("DValidos2",Validos2);
        map.put("DNulos2",DNulos2);
        map.put("DTotal2",DTotal2);
        map.put("DListaNominal2",DListaNominal2);
        map.put("Participacion2",Participacion2);
        
        map.put("PAN3", PAN3);
        map.put("PRI3", PRI3);
        map.put("PRD3",PRD3);
        map.put("PT3", PT3);
        map.put("PVEM3",PVEM3);
        map.put("NuevaAlianza3",NuevaAlianza3);
        map.put("DValidos3",Validos3);
        map.put("DNulos3",DNulos3);
        map.put("DTotal3",DTotal3);
        map.put("DListaNominal3",DListaNominal3);
        map.put("Participacion3",Participacion3);
        
        map.put("PAN4", PAN4);
        map.put("PRI4", PRI4);
        map.put("PRD4",PRD4);
        map.put("PT4", PT4);
        map.put("PVEM4",PVEM4);
        map.put("NuevaAlianza4",NuevaAlianza4);
        map.put("DValidos4",Validos4);
        map.put("DNulos4",DNulos4);
        map.put("DTotal4",DTotal4);
        map.put("DListaNominal4",DListaNominal4);
        map.put("Participacion4",Participacion4);
        
        map.put("PAN5", PAN5);
        map.put("PRI5", PRI5);
        map.put("PRD5",PRD5);
        map.put("PT5", PT5);
        map.put("PVEM5",PVEM5);
        map.put("NuevaAlianza5",NuevaAlianza5);
        map.put("Movimiento5",Movimiento5);
        map.put("Morena",Morena);
        map.put("DValidos5",Validos5);
        map.put("DNulos5",DNulos5);
        map.put("DTotal5",DTotal5);
        map.put("DListaNominal5",DListaNominal5);
        map.put("Participacion5",Participacion5);
    
        map.put("PAN6", PAN6);
        map.put("PRI6", PRI6);
        map.put("PRD6",PRD6);
        map.put("PT6", PT6);
        map.put("PVEM6",PVEM6);
        map.put("NuevaAlianza6",NuevaAlianza6);
        map.put("Movimiento6",Movimiento6);
        map.put("Morena6",Morena6);
        map.put("DValidos6",Validos6);
        map.put("DNulos6",DNulos6);
        map.put("DTotal6",DTotal6);
        map.put("DListaNominal6",DListaNominal6);
        map.put("Participacion6",Participacion6);
        
        
        
        write(response,map);
        map.clear();
        
      
        
          
    }
    
public int p18(double votos,double v03,double v36,double v69,double v92,double v25,int tipo){
    double valor = 0;
    double r;
    double p;
     double rr;
    switch(tipo){
        case 1:
            
            r=v03+v36+v69+v92+v25;
            r=r/5;
            r=r/100;
            rr=1+r;
            p=Math.pow(rr, 3);
            valor= votos*p;
            
            break;
        case 2:
            r= ((v03+v36+v69+v92+v25)/5);
            r=r/100;
            p=Math.pow((1+r), 3);
            valor=(votos*p);
            break;
            case 3:
          
            r=v03+v36+v69+v92+v25;
            r=r/3;
            r=r/100;
            rr=1+r;
            p=Math.pow(rr, 3);
            valor= votos*p;
            break;
        default:
            break;
    }
    int x=(int) valor;
    return x;
    
}

public double incrementoPAN(Partido partido[],int periodo)
{
    double valor = 0;
    double p;
   
    double n=(double)1/3;
    switch(periodo){
        case 5:
            p=partido[periodo-1].getPAN()/partido[periodo].getPAN();
            valor=Math.pow(p, n);
            valor=valor-1;
            break;
        case 4:
            p=partido[periodo-1].getPAN()/partido[periodo].getPAN();
            valor=Math.pow((p), n);
            valor=valor-1;
            break;
        case 3:
            p=partido[periodo-1].getPAN()/partido[periodo].getPAN();
            valor=Math.pow((p), n);
            valor=valor-1;
           break;
        case 2:
            p=partido[periodo-1].getPAN()/partido[periodo].getPAN();
            valor=Math.pow((p), n);
            valor=valor-1;
            break;
        case 1:
             p=partido[0].getPAN()/partido[periodo].getPAN();
             valor=Math.pow((p), n);
            valor=valor-1;
            break;
        default:
            break;        
    }
    valor=valor*100;
    valor=Math.round(valor * 100);
    valor=valor/100;
    return valor;
}
public double incrementoPRI(Partido partido[],int periodo)
{
    double valor = 0;
     double p;
  double n=(double)1/3;
    switch(periodo){
        case 5:
             p=partido[periodo-1].getPRI()/partido[periodo].getPRI();
            valor=Math.pow(p, n);
            valor=valor-1;
            break;
        case 4:
            p=partido[periodo-1].getPRI()/partido[periodo].getPRI();
            valor=Math.pow(p, n);
            valor=valor-1;
            break;
        case 3:
           p=partido[periodo-1].getPRI()/partido[periodo].getPRI();
            valor=Math.pow(p, n);
            valor=valor-1;
            break;
        case 2:
            p=partido[periodo-1].getPRI()/partido[periodo].getPRI();
           valor=Math.pow(p, n);
            valor=valor-1;
            break;
        case 1:
            p=partido[0].getPRI()/partido[periodo].getPRI();
           valor=Math.pow(p, n);
            valor=valor-1;
            
            break;
        default:
            break;        
    }
    valor=valor*100;
    valor=Math.round(valor * 100);
    valor=valor/100;
    return valor;
}
public double incrementoPRD(Partido partido[],int periodo)
{
    double valor = 0;
    double p;
    double n=(double)1/3;
    switch(periodo){
        case 5:
            p=partido[periodo-1].getPRD()/partido[periodo].getPRD();
            valor=Math.pow(p, n);
            valor=valor-1;
            break;
        case 4:
             p=partido[periodo-1].getPRD()/partido[periodo].getPRD();
            valor=Math.pow(p, n);
            valor=valor-1;
            break;
        case 3:
             p=partido[periodo-1].getPRD()/partido[periodo].getPRD();
            valor=Math.pow(p, n);
            valor=valor-1;
            break;
        case 2:
             p=partido[periodo-1].getPRD()/partido[periodo].getPRD();
            valor=Math.pow(p, n);
            valor=valor-1;
            break;
        case 1:
             p=partido[0].getPRD()/partido[periodo].getPRD();
            valor=Math.pow(p, n);
            valor=valor-1;
            break;
        default:
            break;        
    }
    valor=valor*100;
    valor=Math.round(valor * 100);
    valor=valor/100;
    return valor;
}
public double incrementoMorena(Partido partido[])
{
        double valor;
        double p;
        double n=(double)1/3;
        
        p=partido[0].getMorena()/partido[1].getPRD();
            valor=Math.pow((p), n);
            valor=(valor-1)*-1;
        
            
   valor=valor*100;
    valor=Math.round(valor * 100);
    valor=valor/100;
            
    return valor;
}
public double incrementoPVEM(Partido partido[],int periodo)
{
    double valor = 0;
       double p;
    double n=(double)1/3;
    switch(periodo){
        case 5:
             p=partido[periodo-1].getPVEM()/partido[periodo].getPVEM();
         valor=Math.pow(p, n);
            valor=valor-1;
            break;
        case 4:
            p=partido[periodo-1].getPVEM()/partido[periodo].getPVEM();
            valor=Math.pow(p, n);
            valor=valor-1;
            break;
        case 3:
           p=partido[periodo-1].getPVEM()/partido[periodo].getPVEM();
           valor=Math.pow(p, n);
            valor=valor-1;
            break;
        case 2:
           p=partido[periodo-1].getPVEM()/partido[periodo].getPVEM();
           valor=Math.pow(p, n);
            valor=valor-1;
            break;
        case 1:
            p=partido[0].getPVEM()/partido[periodo].getPVEM();
            valor=Math.pow(p, n);
            valor=valor-1;
          
            break;
        default:
            break;        
    }
    valor=valor*100;
    valor=Math.round(valor * 100);
    valor=valor/100;
    return valor;
}

public double incrementoPT(Partido partido[],int periodo)
{
    double valor = 0;
      double p;
   double n=(double)1/3;
    switch(periodo){
        case 5:
            p=partido[periodo-1].getPT()/partido[periodo].getPT();
            valor=Math.pow(p, n);
            valor=valor-1;
            break;
        case 4:
            p=partido[periodo-1].getPT()/partido[periodo].getPT();
            valor=Math.pow(p, n);
            valor=valor-1;
            break;
        case 3:
            p=partido[periodo-1].getPT()/partido[periodo].getPT();
          valor=Math.pow(p, n);
            valor=valor-1;
            break;
        case 2:
            p=partido[periodo-1].getPT()/partido[periodo].getPT();
           valor=Math.pow(p, n);
            valor=valor-1;
            break;
        case 1:
            p=partido[0].getPT()/partido[periodo].getPT();
            valor=Math.pow(p, n);
            valor=valor-1;
           
            break;
        default:
            break;        
    }
   valor=valor*100;
    valor=Math.round(valor * 100);
    valor=valor/100;
    return valor;
}
public double incrementoNuevaAlianza(Partido partido[],int periodo)
{
    double valor = 0;
     double p;
     double n=(double)1/3;
    switch(periodo){
        case 5:
            p=partido[periodo-1].getNuevaAlianza()/partido[periodo].getNuevaAlianza();
            valor=Math.pow(p, n);
            valor=valor-1;
            break;
        case 4:
            p=partido[periodo-1].getNuevaAlianza()/partido[periodo].getNuevaAlianza();
            valor=Math.pow(p, n);
            valor=valor-1;
            break;
        case 3:
            p=partido[periodo-1].getNuevaAlianza()/partido[periodo].getNuevaAlianza();
            valor=Math.pow(p, n);
            valor=valor-1;
            break;
        case 2:
            p=partido[periodo-1].getNuevaAlianza()/partido[periodo].getNuevaAlianza();
            valor=Math.pow(p, n);
            valor=valor-1;
            break;
        case 1:
            p=partido[0].getNuevaAlianza()/partido[periodo].getNuevaAlianza();
            valor=Math.pow(p, n);
            valor=valor-1;
            break;
        default:
            break;        
    }
    valor=valor*100;
    valor=Math.round(valor * 100);
    valor=valor/100;
    return valor;
}
public double incrementoMovimiento(Partido partido[],int periodo)
{
    double valor;
    double p;
    double n=(double)1/3;
    p=partido[0].getMovimiento()/partido[periodo].getMovimiento();
    valor=Math.pow((p), n);
    valor=valor-1;
    valor=Math.round(valor * 100);
    valor=valor/100;
    return valor;
}
public double incrementoNul(Partido partido[],int periodo)
{
    double valor = 0;
    double p;
     double n=(double)1/3;
    switch(periodo){
        case 5:
            p=partido[periodo-1].getDNulos()/partido[periodo].getDNulos();
            valor=Math.pow(p, n);
            valor=valor-1;
            break;
        case 4:
            p=partido[periodo-1].getDNulos()/partido[periodo].getDNulos();
            valor=Math.pow(p, n);
            valor=valor-1;
            break;
        case 3:
            p=partido[periodo-1].getDNulos()/partido[periodo].getDNulos();
            valor=Math.pow(p, n);
            valor=valor-1;
            break;
        case 2:
            p=partido[periodo-1].getDNulos()/partido[periodo].getDNulos();
            valor=Math.pow(p, n);
            valor=valor-1;
            break;
        case 1:
            p=partido[0].getDNulos()/partido[periodo].getDNulos();
            valor=Math.pow(p, n);
            valor=valor-1;
           
            break;
        default:
            break;        
    }
    valor=valor*100;
    valor=Math.round(valor * 100);
    valor=valor/100;
    return valor;
}
public double incrementoTot(Partido partido[],int periodo)
{
    double valor = 0;
    double p;
    double n=(double)1/3;
    switch(periodo){
        case 5:
            p=partido[periodo-1].getDTotal()/partido[periodo].getDTotal();
            valor=Math.pow((p), n);
            valor=valor-1;
            break;
        case 4:
            p=partido[periodo-1].getDTotal()/partido[periodo].getDTotal();
            valor=Math.pow((p), n);
            valor=valor-1;
            break;
        case 3:
            p=partido[periodo-1].getDTotal()/partido[periodo].getDTotal();
            valor=Math.pow((p), n);
            valor=valor-1;
            break;
        case 2:
            p=partido[periodo-1].getDTotal()/partido[periodo].getDTotal();
            valor=Math.pow((p), n);
            valor=valor-1;
            break;
        case 1:
            p=partido[0].getDTotal()/partido[periodo].getDTotal();
            valor=Math.pow((p), n);
            valor=valor-1;
        
            
            break;
        default:
            break;        
    }
    valor=valor*100;
    valor=Math.round(valor * 100);
    valor=valor/100;
    return valor;
}
public double incrementoValidos(Partido partido[],int periodo)
{
    double valor = 0;
    double p;
     double n=(double)1/3;
    switch(periodo){
        case 5:
            p=partido[periodo-1].getValidos()/partido[periodo].getValidos();
            valor=Math.pow((p), n);
            valor=valor-1;
            break;
        case 4:
            p=partido[periodo-1].getValidos()/partido[periodo].getValidos();
            valor=Math.pow((p), n);
            valor=valor-1;
            break;
        case 3:
            p=partido[periodo-1].getValidos()/partido[periodo].getValidos();
            valor=Math.pow((p), n);
            valor=valor-1;
            break;
        case 2:
            p=partido[periodo-1].getValidos()/partido[periodo].getValidos();
            valor=Math.pow((p), n);
            valor=valor-1;
            break;
        case 1:
            p=partido[0].getValidos()/partido[periodo].getValidos();
            valor=Math.pow((p), n);
            valor=valor-1;
         
            break;
        default:
            break;        
    }
   valor=valor*100;
    valor=Math.round(valor * 100);
    valor=valor/100;
    return valor;
}
public double incrementoList(Partido partido[],int periodo)
{
    double valor = 0;
    double p;
     double n=(double)1/3;
    switch(periodo){
        case 5:
            p=partido[periodo-1].getValidos()/partido[periodo].getValidos();
            valor=Math.pow((p), n);
            valor=valor-1;
            break;
        case 4:
            p=partido[periodo-1].getValidos()/partido[periodo].getValidos();
            valor=Math.pow((p), n);
            valor=valor-1;
            break;
        case 3:
            p=partido[periodo-1].getValidos()/partido[periodo].getValidos();
            valor=Math.pow((p), n);
            valor=valor-1;;
            break;
        case 2:
            p=partido[periodo-1].getValidos()/partido[periodo].getValidos();
            valor=Math.pow((p), n);
            valor=valor-1;
            break;
        case 1:
            p=partido[0].getValidos()/partido[periodo].getValidos();
            valor=Math.pow((p), n);
            valor=valor-1;
         
            break;
        default:
            break;        
    }
    valor=valor*100;
    valor=Math.round(valor * 100);
    valor=valor/100;
    return valor;
}
public double incrementoPart(Partido partido[],int periodo)
{
    double valor = 0;
    double p;
   double n=(double)1/3;
    switch(periodo){
        case 5:
            p=partido[periodo-1].getParticipacion()/partido[periodo].getParticipacion();
            valor=Math.pow((p), n);
            valor=valor-1;
            break;
        case 4:
            p=partido[periodo-1].getParticipacion()/partido[periodo].getParticipacion();
            valor=Math.pow((p), n);
            valor=valor-1;
            break;
        case 3:
            p=partido[periodo-1].getParticipacion()/partido[periodo].getParticipacion();
            valor=Math.pow((p), n);
            valor=valor-1;
            break;
        case 2:
            p=partido[periodo-1].getParticipacion()/partido[periodo].getParticipacion();
            valor=Math.pow((p), n);
            valor=valor-1;
            break;
        case 1:
            p=partido[0].getParticipacion()/partido[periodo].getParticipacion();
            valor=Math.pow((p), n);
            valor=valor-1;
          
           
            break;
        default:
            break;        
    }
    valor=valor*100;
    valor=Math.round(valor * 100);
    valor=valor/100;
    return valor;
}
 void write(HttpServletResponse response, Map<String, Object> map) throws IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(new Gson().toJson(map));
}
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
