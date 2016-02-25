/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import Usuario.Usuario;
import UsuarioDAO.UsuarioDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lucia
 */
public class EliminarUsuario extends HttpServlet {

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
            throws ServletException, IOException 
    {
        response.setContentType("text/xml");
        
        String VLstrClave = new String();
        String VLstrCorreo = new String();
        
        try
        {
            VLstrClave = request.getParameter("Clave").trim();
        VLstrCorreo = request.getParameter("Correo").trim();
        }
        catch(NullPointerException ex)
        {
            try (PrintWriter out = response.getWriter()) 
            {
                out.println("<Sesion>");
                    out.println("<Estado>");
                        out.println("<Error>");
                            out.println("Campos vacios.");
                        out.println("</Error>");
                    out.println("</Estado>");
                out.println("</Sesion>");
            }
            
            return ;
        }

        try (PrintWriter out = response.getWriter()) 
        {
            VerificarSesion VLobjVerificar = new VerificarSesion();

            String VLstrSesion = VLobjVerificar.getClave(VLstrClave);
            
            out.println("<Sesion>");
            out.println("<Estado>");

            if(VLstrSesion.contains("ERROR"))
            {
                out.println("<Error>Datos de sesion no validos.</Error>");
            }
            else // si existe una sesion con el correo VLstrSesion
            {
                    UsuarioDAOImpl VLobjUsuarioDAO = new UsuarioDAOImpl();

                    Usuario VLobjUsuario = VLobjUsuarioDAO.getUsuario(VLstrSesion.trim());

                    if(VLobjUsuario.getVMstrPerfil().equals("Admin"))
                    {
                        VLobjUsuarioDAO.deleteUsuario(VLstrCorreo);

                        out.println("<Exito>La cuenta ha sido eliminada.</Exito>");
                    }
                    else
                    {
                        out.println("<Error>No tienes los privilegios necesarios para realizar esta accion.</Error>");
                    }
            }

            out.println("</Estado>");
            out.println("</Sesion>");
        
        }
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
