/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Comparativa.DBComparativa.DBComparativa;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;


/**
 *
 * @author diegoleon
 */
public class RecuperarContrasena extends HttpServlet {

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
        String strCorreo = request.getParameter("Correo");
        
        String strPassword = new DBComparativa().getContrasena(strCorreo);
        
        if(strPassword != null)
        {
            enviarCorreo(strCorreo, strPassword);
        }
        
    }
    
    private void enviarCorreo(String VPstrCorreo, String VPstrPassword)
    {
        try
        {
            String host = "smtp-mail.outlook.com";
            String username = "ssgpe_ipn@outlook.com";
            String password = "ESCOMipn";
            
            Properties props = new Properties();
            props.put("mail.smtp.port",587);
            props.put("mail.smtp.host","smtp-mail.outlook.com");
            props.put("mail.smtp.starttls.enable", true);
            props.put("mail.smtp.ssl.enable", false);
            
            Session session = Session.getInstance(props);
            MimeMessage msg = new MimeMessage(session);
                        msg.setFrom(new InternetAddress(username));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(VPstrCorreo));
			msg.setSubject("SSGPE : Recuperación de contraseña.");
                        
            String mensajeHTML = "<div id=\"cont1\"><center>SSGPE</center></div>\n" +
"	<div id=\"cont2\"><center>Recuperación de contraseña.</center></div>\n" +
"\n" +
"	<div >Se ha solicitado la contraseña para esta dirección de correo.<br />En caso de no haber solicitado esta operación omita el mensaje.<br /><br/> <strong>Contraseña : </strong> "+VPstrPassword+"</div>\n" +
"\n" +
"\n" +
"<style type=\"text/css\">\n" +
"	\n" +
"	#cont1\n" +
"	{\n" +
"		height: 50px;\n" +
"		padding-top: 3px;\n" +
"		background-color: black;\n" +
"		color : white;\n" +
"		font-size: 2em;\n" +
"	}\n" +
"\n" +
"	#cont2\n" +
"	{\n" +
"		height: 50px;\n" +
"		background-color: #63c6ae;\n" +
"		color : white;\n" +
"		font-size: 2em;\n" +
"		padding-top: 3px;\n" +
"	}\n" +
"\n" +
"	div\n" +
"	{\n" +
"		height :  50px;\n" +
"	}";
            
            
			msg.setText(mensajeHTML,"utf-8", "html");
            Transport.send(msg, username, password);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        

      
    }
    
    /*
    public static void main(String[] args) {
        new RecuperarContrasena().enviarCorreo("dleon_0920@hotmail.com","");
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