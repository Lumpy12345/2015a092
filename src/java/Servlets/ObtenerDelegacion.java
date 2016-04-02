/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

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
        
        response.setContentType("application/map");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String id="";String año="";
        id=request.getParameter("Clave").trim();
        año= request.getParameter("ano").trim();
        DelegacionDAOImpl del= new DelegacionDAOImpl();
        Delegacion delegacion=del.getDelegacion(id,año);
        Map map=new HashMap();

        if(año.equals("ano1994"))
        {
                map.put("Delegacion", delegacion.getDNombre());
                map.put("Secciones",delegacion.getDSecciones() );
                map.put("Casillas",delegacion.getDCasillas() );
                map.put("PAN",delegacion.getPan() );
                map.put("PRI",delegacion.getPri() );
                map.put("PPS",delegacion.getPps() );
                map.put("PRD",delegacion.getPRD() );
                map.put("PFCRN",delegacion.getPfcrn() );
                map.put("PARM",delegacion.getParm() );
                map.put("UNO",delegacion.getUnopdm() );
                map.put("PT",delegacion.getPt() );
                map.put("PVEM",delegacion.getPvem() );
                map.put("DNoRegistrados",delegacion.getDNoRegistrados() );
                map.put("Validos",delegacion.getValidos() );
                map.put("DNulos",delegacion.getDNulos() );
                map.put("Total",delegacion.getDTotal() );
                map.put("Lista",delegacion.getDListaNominal() );
                map.put("Participacion",delegacion.getDParticipacion() );
        }
        if(año.equals("ano2000"))
        {
                map.put("Delegacion",delegacion.getDNombre() );
                map.put("Secciones",delegacion.getDSecciones() );
                map.put("Casillas",delegacion.getDCasillas());
                map.put("AC",delegacion.getAc() );
                map.put("PRI",delegacion.getPri() );
                map.put("AM",delegacion.getAm() );
                map.put("PCD",delegacion.getPcd() );
                map.put("PARM",delegacion.getParm() );
                map.put("DSPPN",delegacion.getDsppn() );
                map.put("DNoRegistrados",delegacion.getDNoRegistrados() );
                map.put("Validos",delegacion.getValidos() );
                map.put("DNulos",delegacion.getDNulos() );
                map.put("Total",delegacion.getDTotal() );
                map.put("Lista",delegacion.getDListaNominal() );
                map.put("Participacion",delegacion.getDParticipacion() );
        }
        if(año.equals("ano2006"))
        {
                map.put("Delegacion",delegacion.getDNombre() );
                map.put("Secciones",delegacion.getDSecciones() );
                map.put("Casillas",delegacion.getDCasillas());
                map.put("PAN",delegacion.getPan() );
                map.put("Alianza",delegacion.getAlianza() );
                map.put("Porelbien", delegacion.getPorelbien());
                map.put("NuevaAlianza",delegacion.getNuevaAlianza() );
                map.put("Alternativa",delegacion.getAlternativa() );
                map.put("DNoRegistrados",delegacion.getDNoRegistrados() );
                map.put("Validos",delegacion.getValidos() );
                map.put("DNulos",delegacion.getDNulos() );
                map.put("Total",delegacion.getDTotal() );
                map.put("Lista",delegacion.getDListaNominal() );
                map.put("Participacion",delegacion.getDParticipacion() );
    
        }
        if(año.equals("ano2012"))
        {
                map.put("Delegacion",delegacion.getDNombre() );
                map.put("Secciones",delegacion.getDSecciones() );
                map.put("Casillas",delegacion.getDCasillas());
                map.put("PAN",delegacion.getPan() );
                map.put("PRI",delegacion.getPri() );
                map.put("PRD",delegacion.getPRD() );
                map.put("PVEM",delegacion.getPvem() );
                map.put("PT",delegacion.getPt());
                map.put("Movimiento",delegacion.getMovimiento() );
                map.put("NuevaAlianza",delegacion.getNuevaAlianza() );
                map.put("PRIPVEM",delegacion.getPRIPVEM() );
                map.put("PRDPTMC",delegacion.getPRDPTMovimiento() );
                map.put("PRDPT",delegacion.getPRDPT() );
                map.put("PRDMC",delegacion.getPRDMovimiento() );
                map.put("PTMC",delegacion.getPTMovimiento() );
                map.put("DNoRegistrados",delegacion.getDNoRegistrados() );
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
