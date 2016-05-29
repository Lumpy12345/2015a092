/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import Comparativa.AnioConcrete;
import Comparativa.Comparativa;
import Comparativa.DBComparativa.DBComparativa;
import Comparativa.DBComparativa.Historial;
import Comparativa.DBComparativa.PartidoPorDelegacion;
import Comparativa.Strategy;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author diegoleon
 */
public class SComparativa extends HttpServlet {

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
        String strAnio = request.getParameter("anio");
        String strCVE = request.getParameter("cve");
        //    ***********************************************
    String sesion = request.getParameter("sesion");

        
        try
        {
            int idDel = new DBComparativa().getIdDel(strCVE);
            int idAnio = new DBComparativa().getIdAnio(strAnio);
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
        Strategy strategy = new AnioConcrete(strAnio, strCVE);
        strategy.iniciarComparacion();
        Comparativa comparativa = strategy.getComparativa();
        
        PrintWriter out = response.getWriter();
        
        response.setContentType("text/xml");
        response.setCharacterEncoding("utf-8");
        
        out.println("<root>");
        out.println("<cve_mun>" + comparativa.strCVE_MUN +"</cve_mun>");
        out.println("<nombre>" + comparativa.strNombreDelegacion + "</nombre>");
        out.println("<partidoganador>" + comparativa.partidoGanador + "</partidoganador>");
        
        out.println("<tabla1>");
        for(PartidoPorDelegacion partido : comparativa.listResultadosDelegacionSeleccionada )
        {
            out.println("<tr>");
                out.println("<td>" + partido.strNombrePartido +"</td>");
                out.println("<td>" + partido.votos + "</td>");
            out.println("</tr>");
        }
        out.println("</tabla1>");
        
        out.println("<tabla2>");
        for(PartidoPorDelegacion partido : comparativa.listOtrasDelegacionesGanadas )
        {
            out.println("<tr>");
                out.println("<td>" +  partido.strMunicipio + "</td>");
                out.println("<td>" + partido.strNombrePartido +"</td>");
                out.println("<td>" + partido.votos + "</td>");
            out.println("</tr>");
        }
        out.println("</tabla2>");
        
        out.println("<delegacionesganadas>" + comparativa.intDelegacionesGanadas + "</delegacionesganadas>");
        out.println("<delegacionesperdidas>" + comparativa.intDelegacionesPerdidas + "</delegacionesperdidas>");
        out.println("<porcentaje>" + comparativa.floatPorcentajeVictoria + "</porcentaje>");
        
        out.println("<votos>" + comparativa.votosPartidoGanador + "</votos>");
        out.println("</root>");
        
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