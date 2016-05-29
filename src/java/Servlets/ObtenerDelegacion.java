/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import Comparativa.DBComparativa.DBComparativa;
import Comparativa.DBComparativa.Historial;
import Delegacion.Delegacion;
import DelegacionDAO.DelegacionDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

/**
 *
 * @author Lucia
 */
public class ObtenerDelegacion extends HttpServlet {

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
        String sesion = request.getParameter("sesion");
        response.setContentType("application/map");
        response.setCharacterEncoding("utf-8");
        
        String id;String año;
        id=request.getParameter("Clave").trim();
        año= request.getParameter("ano").trim();
        
        //    ***********************************************

        
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
        
        DelegacionDAOImpl del= new DelegacionDAOImpl();
        Delegacion delegacion=del.getDelegacion(id,año);
        Map map=new HashMap();
       

        if(año.equals("2000"))
        {
                map.put("Delegacion", delegacion.getDNombre());
                map.put("PANPVEM",delegacion.getPANPVEM());
                map.put("PRI",delegacion.getPRI() );
                map.put("PRD",delegacion.getPRD() );
                map.put("PT",delegacion.getPt() );
                map.put("Convergencia",delegacion.getConvergencia());
                map.put("PCD",delegacion.getPCD() );
                map.put("PSN",delegacion.getPSN());
                map.put("PARM",delegacion.getPARM());
                map.put("PAS",delegacion.getPAS());
                map.put("DS",delegacion.getDS());
                map.put("Validos",delegacion.getValidos() );
                map.put("DNulos",delegacion.getDNulos() );
                map.put("Total",delegacion.getDTotal() );
                map.put("Lista",delegacion.getDListaNominal() );
                map.put("Participacion",delegacion.getDParticipacion() );
                
        }
        if(año.equals("2003"))
        {
                map.put("Delegacion", delegacion.getDNombre());
                map.put("PAN",delegacion.getPAN() );
                map.put("PRI",delegacion.getPRI() );
                map.put("PRD",delegacion.getPRD() );
                map.put("PT",delegacion.getPt() );
                map.put("PVEM",delegacion.getPvem() );
                map.put("Convergencia",delegacion.getConvergencia());
                map.put("PSN",delegacion.getPSN());
                map.put("PAS",delegacion.getPAS());
                map.put("MP",delegacion.getMP());
                map.put("PLM",delegacion.getPLM());
                map.put("FC",delegacion.getFC());
                map.put("Validos",delegacion.getValidos() );
                map.put("DNulos",delegacion.getDNulos() );
                map.put("Total",delegacion.getDTotal() );
                map.put("Lista",delegacion.getDListaNominal() );
                map.put("Participacion",delegacion.getDParticipacion() );
                
                
        }
        if(año.equals("2006"))
        {
                map.put("Delegacion",delegacion.getDNombre() );
                map.put("PAN",delegacion.getPAN() );
                map.put("PRIPVEM",delegacion.getPRIPVEM());
                map.put("PRDPTConvergencia",delegacion.getPRDPTConvergencia());
                map.put("NuevaAlianza",delegacion.getNuevaAlianza());
                map.put("PASC",delegacion.getPASC() );
                map.put("PANNuevaAlianza",delegacion.getPANNuevaAlianza() );
                map.put("Validos",delegacion.getValidos() );
                map.put("DNulos",delegacion.getDNulos() );
                map.put("Total",delegacion.getDTotal() );
                map.put("Lista",delegacion.getDListaNominal() );
                map.put("Participacion",delegacion.getDParticipacion() );
                
                
        }
        if(año.equals("2009"))
        {
                map.put("Delegacion",delegacion.getDNombre() );
                map.put("PAN",delegacion.getPAN() );
                map.put("PRI",delegacion.getPRI());
                map.put("PRD", delegacion.getPRD());
                map.put("PT",delegacion.getPt());
                map.put("PVEM",delegacion.getPvem());
                map.put("Convergencia",delegacion.getConvergencia());
                map.put("NuevaAlianza",delegacion.getNuevaAlianza() );
                map.put("PSD",delegacion.getPSD());
                map.put("PRDPTConvergencia",delegacion.getPRDPTConvergencia());
                map.put("PRDPT",delegacion.getPRDPT());
                map.put("PRDConvergencia",delegacion.getPRDConvergencia());
                map.put("PTConvergencia",delegacion.getPTConvergencia());
                map.put("Validos",delegacion.getValidos() );
                map.put("DNulos",delegacion.getDNulos() );
                map.put("Total",delegacion.getDTotal() );
                map.put("Lista",delegacion.getDListaNominal() );
                map.put("Participacion",delegacion.getDParticipacion() );
                
    
        }
        if(año.equals("2012"))
        {
                map.put("Delegacion",delegacion.getDNombre() );
                map.put("PAN",delegacion.getPAN() );
                map.put("PRI",delegacion.getPRI() );
                map.put("PRD",delegacion.getPRD() );
                map.put("PT",delegacion.getPt());
                map.put("PVEM",delegacion.getPvem() );
                map.put("Movimiento",delegacion.getMovimiento() );
                map.put("NuevaAlianza",delegacion.getNuevaAlianza() );
                map.put("PRIPVEM",delegacion.getPRIPVEM() );
                map.put("PRDPTMovimiento",delegacion.getPRDPTMovimiento() );
                map.put("Validos",delegacion.getValidos());
                map.put("DNulos",delegacion.getDNulos() );
                map.put("Total",delegacion.getDTotal() );
                map.put("Lista",delegacion.getDListaNominal() );
                map.put("Participacion",delegacion.getDParticipacion() );
                
        }
        if(año.equals("2015"))
        {
                map.put("Delegacion",delegacion.getDNombre() );
                map.put("PAN",delegacion.getPAN() );
                map.put("PRIPVEM",delegacion.getPRIPVEM() );
                map.put("PRDPT",delegacion.getPRDPT() );
                map.put("PRDPTNuevaAlianza",delegacion.getPRDPTNuevaAlianza());
                map.put("PRD",delegacion.getPRD() );
                map.put("PT",delegacion.getPt());
                map.put("Movimiento",delegacion.getMovimiento() );
                map.put("NuevaAlianza",delegacion.getNuevaAlianza() );
                map.put("Morena",delegacion.getMorena());
                map.put("PH",delegacion.getPH());
                map.put("PES",delegacion.getPES());
                map.put("Inde",delegacion.getIndependiente());
                map.put("Validos",delegacion.getValidos());
                map.put("DNulos",delegacion.getDNulos() );
                map.put("Total",delegacion.getDTotal() );
                map.put("Lista",delegacion.getDListaNominal() );
                map.put("Participacion",delegacion.getDParticipacion() );
              
               
        }
        
    write(response,map);
    map.clear();
}
private void write(HttpServletResponse response, Map<String, Object> map) throws IOException {
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
