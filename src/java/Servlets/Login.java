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
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lucia
 */
public class Login extends HttpServlet {

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
        String VLstrCorreo = new String();
        String VLstrPassword = new String();
        
        response.setContentType("text/xml");
        
        try
        {
            System.out.println("Servlet");
            VLstrCorreo = request.getParameter("Correo").trim();
            System.out.println("SCorreo:"+VLstrCorreo);
            VLstrPassword = request.getParameter("Password").trim();
            System.out.println("SPassword:"+VLstrPassword);
        }
        catch(NullPointerException ex)
        {
            try (PrintWriter out = response.getWriter()) 
            {
                System.out.println("entro campos vacios");
                out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
                out.println("<Sesion>");
                        out.println("<Error>");
                            out.println("Campos vacios.");
                        out.println("</Error>");
                out.println("</Sesion>");
            }
            
            return ;
        }
        
        
        
        UsuarioDAOImpl VLobjUsuarioDAO = new UsuarioDAOImpl();
        
        Usuario VLobjUsuario = VLobjUsuarioDAO.getUsuario(VLstrCorreo);
        
        //processRequest(request, response);
        response.setContentType("text/xml");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            out.println("<login>");
            
            if(VLobjUsuario == null)
            {
                System.out.println("entro no existe correo");
                out.println("<Error>No existe el correo.</Error>");
            }
            else
            {
                
                
                if((VLobjUsuario.getVMstrPassword()).equals(VLstrPassword))
                {
                    System.out.println("entro a login");
                    eliminarClave(VLstrCorreo.trim());
                    out.println("<Clave>"+VLobjUsuario.iniciarSesion()+"</Clave>");
                    out.println("<Perfil>"+VLobjUsuario.getVMstrPerfil()+"</Perfil>");
                }
                else
                {
                    out.println("<Error>Password incorrecto.</Error>");
                }
            }
            out.println("</login>");
           
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
            throws ServletException, IOException 
    {
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
    
    
    //////////// Metodos ////////////
    
    public void eliminarClave(String VPstrCorreo)
    {
        Connection VLobjConn = ConexionBD.getConexion();
        
        try 
        {
            Statement VLsqlST = VLobjConn.createStatement();
            VLsqlST.executeUpdate("DELETE FROM Sesion WHERE Correo = \'" + VPstrCorreo +"\'");
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
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
