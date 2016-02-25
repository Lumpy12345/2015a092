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
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lucia
 */
public class ObtenerUsuarios extends HttpServlet {

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

        response.setContentType("text/html");
        String VLstrClave = new String();
        try
        {
            VLstrClave = request.getParameter("Clave").trim();
            System.out.println(""+VLstrClave);
        }
        catch(NullPointerException ex)
        {
            try (PrintWriter out = response.getWriter()) 
            {
                        out.println("<div>");
                            out.println("Campos vacios.");
                        out.println("</div>");
            }
            
            return ;
        }


        try (PrintWriter out = response.getWriter()) 
        {
            VerificarSesion VLobjVerificar = new VerificarSesion();

            String VLstrSesion = VLobjVerificar.getClave(VLstrClave);
            
                
                    if(VLstrSesion.contains("ERROR"))
                    {
                        out.println("<div>Datos de sesion no validos.</div>");
                    }
                    else // si existe una sesion con el correo VLstrSesion
                    {
                            UsuarioDAOImpl VLobjUsuarioDAO = new UsuarioDAOImpl();
                            
                            Usuario VLobjUsuario = VLobjUsuarioDAO.getUsuario(VLstrSesion.trim());
                            out.println("<table class=\"table table-striped table-advance table-hover\">");
                            out.println("<tbody>\n" +
"                              <tr>\n" +
"                                 <th><i class=\"icon_profile\"></i> Correo</th>\n" +
"                                 <th><i class=\"icon_profile\"></i> Nombre</th>\n" +
"                                 <th><i class=\"icon_profile\"></i> Apellido Paterno</th>\n" +
"                                 <th><i class=\"icon_mail_alt\"></i>Apellido Materno</th>\n" +
"                                 <th><i class=\"icon_mobile\"></i> Perfil</th>\n" +
"                                 <th><i class=\"icon_cogs\"></i> Action</th>\n" +
"                              </tr>");
                            if(VLobjUsuario.getVMstrPerfil().equals("Admin"))
                            {
                                ArrayList<Usuario> VLutilUsuarios = VLobjUsuarioDAO.getAllUsuario();
                                
                                for(Usuario VLobjUsuario2 : VLutilUsuarios)
                                {
                                    out.println("<tr>");
                                    out.println("<td>" + VLobjUsuario2.getVMstrCorreo()+ "</td>");
                                    out.println("<td>" + VLobjUsuario2.getVMstrNombre()+ "</td>");
                                    out.println("<td>" + VLobjUsuario2.getVMstrAPaterno()+ "</td>");
                                    out.println("<td>" + VLobjUsuario2.getVMstrAMaterno()+ "</td>");
                                    out.println("<td>" + VLobjUsuario2.getVMstrPerfil()+ "</td>");
                                  /*  out.println(" <td ><button class=\"btn btn-primary\" onclick=editarU("+"\""
                                            + VLobjUsuario2.getVMstrCorreo()+"\"" +");><i class=\"fa fa-street-view fa-lg\"></i>Editar</button>\n" */
                                    out.println(" <td ><a class=\"btn btn-primary\" href=\"/TT/ObtenerUsuario?Correo="+VLobjUsuario2.getVMstrCorreo()+"\"><i class=\"fa fa-street-view fa-lg\"></i>Editar</a>\n" +
"                        \n" +
"                        <button class=\"btn btn-danger\" onclick=eliminarU("+"\""
                                            + VLobjUsuario2.getVMstrCorreo()+"\"" +");><i class=\"fa fa-remove fa-lg\"></i>Eliminar</button>\n" +
"                            </td>");
                                    out.println("</tr>");
                                }
                                
                                out.println("</tbody>");
                                out.println("</table>");
                            }
                            else
                            {
                                out.println("<div>No tienes los privilegios necesarios para realizar esta accion.</div>");
                            }
                        
                    }
                    
                    
        
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
