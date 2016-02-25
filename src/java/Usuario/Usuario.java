/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Usuario;

import ConexionBD.ConexionBD;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucia
 */
public abstract class Usuario 
{
    protected String VMstrCorreo;
    protected String VMstrPassword;
    protected String VMstrNombre;
    protected String VMstrAPaterno;
    protected String VMstrAMaterno;
    protected String VMstrPerfil;
    
    public String getVMstrCorreo() {
        return VMstrCorreo;
    }

    public String getVMstrPassword() {
        return VMstrPassword;
    }

    public void setVMstrCorreo(String VMstrCorreo) {
        this.VMstrCorreo = VMstrCorreo;
    }

    public void setVMstrPassword(String VMstrPassword) {
        this.VMstrPassword = VMstrPassword;
    }
    
    public String getVMstrNombre() {
        return VMstrNombre;
    }

    public String getVMstrAPaterno() {
        return VMstrAPaterno;
    }

    public String getVMstrAMaterno() {
        return VMstrAMaterno;
    }

    public String getVMstrPerfil() {
        return VMstrPerfil;
    }
    
    ///////////////////////////////////////////
    
    public int iniciarSesion()
    {
        Connection VLobjConn = ConexionBD.getConexion();
        
        int VLintClave = (int)(Math.random()*(100000-1+1)+1);
        
        try 
        {
            Statement VLsqlST = VLobjConn.createStatement();
            VLsqlST.executeUpdate("insert into Sesion (Clave, Correo) values (\'"+ VLintClave + "\',\'" + VMstrCorreo.trim() + "\');");
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return VLintClave;
    }
    
    public void cerrarSesion()
    {
        Connection VLobjConn = ConexionBD.getConexion();
        
        try 
        {
            Statement VLsqlST = VLobjConn.createStatement();
            VLsqlST.executeUpdate("DELETE FROM Sesion WHERE Correo = \'" + VMstrCorreo.trim() +"\'");
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
   
}
