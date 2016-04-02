/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import ConexionBD.ConexionBD;
import DelegacionDAO.DelegacionDAOImpl;
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
      /* UsuarioDAOImpl test = new UsuarioDAOImpl();
       Usuario user=test.getUsuario("rrsanmore@gmail.com");
         System.out.println(""+ user.getVMstrNombre());
       
         test.addUsuario("dleon@hotmail.com", "cristal", "Diego", "Leon", "Nolasco", "Administrador");
         
      */
                          DelegacionDAOImpl del=new DelegacionDAOImpl();
    String a[][]=new String[4][7];
    
    a=del.compararParticipacion("002");       
    
    for(int row = 0; row < a.length; row++){
                for(int element = 0; element < a[row].length; element++){
               //  System.out.printf("Row: %d Element: %d Value: %s\n", row, element, a[row][element]);
                    System.out.println(row+","+element+":"+ a[row][element]);
                }
    }      
    
    
        
        
    }
}
