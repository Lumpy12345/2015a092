/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import ConexionBD.ConexionBD;
import Usuario.Usuario;
import UsuarioDAO.UsuarioDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lucia
 */
public class Logout extends HttpServlet {

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
        String VLstrClave = request.getParameter("Clave");
        System.out.println("cerrar:"+VLstrClave);
        
        response.setContentType("text/xml");
        try (PrintWriter out = response.getWriter()) 
        {
            cerrarSesion(VLstrClave);
            /* TODO output your page here. You may use following sample code. */
            out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            
            out.println("<Sesion>");
            out.println("<Estado>Sesion terminada.</Estado>");
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

    ////////////////// Metodos ////////////////////
    
    public void cerrarSesion(String VPstrClave)
    {
        Connection VLobjConn = ConexionBD.getConexion();
        String VLstrCorreo = "";
        try 
        {
            Statement VLsqlST = VLobjConn.createStatement();
            ResultSet VLsqlRS = VLsqlST.executeQuery("SELECT * FROM Sesion where Clave = '"+ VPstrClave.trim() + "\'");
            
            while(VLsqlRS.next())
            {
                VLstrCorreo = VLsqlRS.getString("Correo");

                UsuarioDAOImpl VLobjUsuarioDAO = new UsuarioDAOImpl();
                Usuario VLobjUsuario = VLobjUsuarioDAO.getUsuario(VLstrCorreo);
                VLobjUsuario.cerrarSesion();
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(VerificarSesion.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try 
            {
                VLobjConn.close();
            } 
            catch (SQLException ex) 
            {
                ex.printStackTrace();
            }
        }
        
    }
}
