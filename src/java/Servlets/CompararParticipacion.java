/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import Comparativa.DBComparativa.DBComparativa;
import Comparativa.DBComparativa.Historial;
import DelegacionDAO.DelegacionDAOImpl;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
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
public class CompararParticipacion extends HttpServlet {

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
      PrintWriter out = response.getWriter();
    
            
    DelegacionDAOImpl del=new DelegacionDAOImpl();
    String a[][]=new String[6][5];
    String id="";
    id=request.getParameter("Clave");
    System.out.println("CVE:"+id);
    a=del.compararParticipacion(id);
    
//    ***********************************************
    String sesion = request.getParameter("sesion");
    String año =request.getParameter("anio");
        
        try
        {
            int idDel = new DBComparativa().getIdDel(id);
            int idAnio = new DBComparativa().getIdAnio(año);
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
    
    String vali = null,nulos = null,total = null,lista = null,par = null;
    
           
            for(int row = 0; row < a.length; row++)
            {
                    for(int element = 0; element < a[row].length; element++)
                    {
                          switch(element)
                          {
                                case 0:
                                   if(row==0)
                                    {   vali=a[row][element]+",";   }
                                    else if(row==5)
                                    {   vali=vali+a[row][element];  }
                                    else{   vali=vali+a[row][element]+",";  }
                                break;
                                case 1:
                                    if(row==0)
                                    {   nulos=a[row][element]+",";  }
                                    else if(row==5)
                                    {   nulos=nulos+a[row][element];    }
                                    else
                                    {   nulos=nulos+a[row][element]+",";    }
                                break;
                                case 2:
                                    if(row==0)
                                    {   total=a[row][element]+",";  }
                                    else if(row==5)
                                    {   total=total+a[row][element];    }
                                    else
                                    {   total=total+a[row][element]+",";    }
                                break;
                                case 3:
                                     if(row==0)
                                    {   lista=a[row][element]+",";  }
                                    else if(row==5)
                                    {   lista=lista+a[row][element];    }
                                    else
                                    {   lista=lista+a[row][element]+",";    }
                                break;
                                case 4:
                                     if(row==0)
                                    {   par=a[row][element]+",";    }
                                    else if(row==5)
                                    {   par=par+a[row][element];    }
                                    else
                                    {   par=par+a[row][element]+",";    }
                                break;
                               
                                default:
                                break;
                          }

                    }
             }
          
    Map map=new HashMap();
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("Comparar Participacion");
            System.out.println("validos:"+vali);
            System.out.println("nul:"+nulos);
            System.out.println("total"+total);
            System.out.println("Lista"+lista);
            System.out.println("Part"+par);
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
    map.put("Vali",vali);
    map.put("Nul",nulos);
    map.put("Total",total);
    map.put("Lista",lista);
    map.put("Part",par);
    
     
    write(response,map);
    map.clear();
       
    }
    
private void write(HttpServletResponse response, Map<String, Object> map) throws IOException{
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(new Gson().toJson(map)); //this is how simple GSON works
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
