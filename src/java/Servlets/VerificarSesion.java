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
 * @author Rodolfo
 */
public class VerificarSesion extends HttpServlet {
 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        String VLstrClave = request.getParameter("Clave");
        
        response.setContentType("text/xml");
        try (PrintWriter out = response.getWriter()) 
        {
            /* TODO output your page here. You may use following sample code. */
            out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            
            out.println("<Sesion>");
            String VLstrEstado;
            VLstrEstado=getClave(VLstrClave);
            
            if(VLstrEstado.contains("ERROR"))
            {
                out.println("<Error>"+VLstrEstado+"</Error>");
            }
            else
            {
                UsuarioDAOImpl VLobjUsuarioDAO = new UsuarioDAOImpl();
                
                Usuario VLobjUsuario = VLobjUsuarioDAO.getUsuario(VLstrEstado);
                
                out.println("<Perfil>"+VLobjUsuario.getVMstrPerfil()+"</Perfil>");
            }
            
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
    
    
    ////////////////////// Metodos //////////////////////
    
    
    public String getClave(String VPstrClave)
    {
        String VLstrEstado = "";
        Connection VLsqlConn = ConexionBD.getConexion();
        try 
        {
            Statement VLsqlST = VLsqlConn.createStatement();
            ResultSet VLsqlRS = VLsqlST.executeQuery("SELECT * FROM Sesion where Clave = "+ VPstrClave);
            
            if(VLsqlRS == null)
            {
                VLstrEstado = "ERROR:Datos de sesion no validos.";
            }
            else
            {
                while(VLsqlRS.next())
                {
                    
                    VLstrEstado = VLsqlRS.getString("Correo");
                }
                
                if(VLstrEstado.length() == 0)
                    VLstrEstado = "ERROR:Datos de sesion no validos.";
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(VerificarSesion.class.getName()).log(Level.SEVERE, null, ex);
            VLstrEstado = "ERROR:Error interno.";
        }
        finally
        {
            try 
            {VLsqlConn.close();} 
            catch (SQLException ex) 
            { ex.printStackTrace();
                VLstrEstado = "ERROR:Error interno.";}
        }
            return VLstrEstado;
    }

}
