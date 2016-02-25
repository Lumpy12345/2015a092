/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import ConexionBD.ConexionBD;
import Usuario.Usuario;
import UsuarioDAO.UsuarioDAOImpl;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucia
 */
public class test {
    public static void main(String[] args) {
       UsuarioDAOImpl test = new UsuarioDAOImpl();
       Usuario user=test.getUsuario("rrsanmore@gmail.com");
         System.out.println(""+ user.getVMstrNombre());
       
         test.addUsuario("dleon@hotmail.com", "cristal", "Diego", "Leon", "Nolasco", "Administrador");
         
      
                                
                              
        
        
    }
}
