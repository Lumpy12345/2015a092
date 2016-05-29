/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UsuarioDAO;

import ConexionBD.ConexionBD;
import Usuario.Administrador;
import Usuario.Cliente;
import Usuario.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucia
 */
public class UsuarioDAOImpl implements UsuarioDAO
{

    private Connection VMobjConn;
    private ArrayList<Usuario> VMutilUsuarios = new ArrayList<>();
    
    public UsuarioDAOImpl()
    {
        
        VMutilUsuarios = new ArrayList<Usuario>();
    }
        
    @Override
            public ArrayList<Usuario> getAllUsuario() 
    {   
        try 
        {
            VMobjConn = ConexionBD.getConexion();
            Statement VLsqlST = VMobjConn.createStatement();
            ResultSet VLsqlRS = VLsqlST.executeQuery("SELECT * FROM Usuarios");
            
            while(VLsqlRS.next())
            {
                String VLstrCorreo = VLsqlRS.getString(1);
                String VLstrPassword = VLsqlRS.getString(2);
                String VLstrNombre = VLsqlRS.getString(3);
                String VLstrAPaterno = VLsqlRS.getString(4);
                String VLstrAMaterno = VLsqlRS.getString(5);
                String VLstrPerfil = VLsqlRS.getString(6);
                
                Usuario VLobjUsuario;
                
                if(VLstrPerfil.equals("Administrador"))
                {
                    VLobjUsuario = new Administrador(VLstrCorreo,
                                                    VLstrPassword,
                                                    VLstrNombre,
                                                    VLstrAPaterno,
                                                    VLstrAMaterno,
                                                    VLstrPerfil);
                }
                else
                {
                    VLobjUsuario = new Cliente(VLstrCorreo,
                                                    VLstrPassword,
                                                    VLstrNombre,
                                                    VLstrAPaterno,
                                                    VLstrAMaterno,
                                                    VLstrPerfil);
                }
                
                VMutilUsuarios.add(VLobjUsuario);
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(UsuarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try 
            {
                VMobjConn.close();
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(UsuarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return VMutilUsuarios;
    }

    @Override
    public void updateUsuario(String VPstrCorreo, String VPstrPassword, String VPstrNombre,String VPstrAPaterno, String VPstrAMaterno, String VPstrPerfil,String correo) 
    {
        try 
        {
            VMobjConn = ConexionBD.getConexion();
            PreparedStatement VLsqlPS = VMobjConn.prepareStatement(
                    "UPDATE usuarios SET Correo = ?, Password = ?, Nombre = ?, APaterno = ?, AMaterno = ?, Perfil = ? WHERE Correo = ?");
            
            // set the preparedstatement parameters
            VLsqlPS.setString(1,VPstrCorreo);
            VLsqlPS.setString(2,VPstrPassword);
            VLsqlPS.setString(3,VPstrNombre);
            VLsqlPS.setString(4,VPstrAPaterno);
            VLsqlPS.setString(5,VPstrAMaterno);
            VLsqlPS.setString(6,VPstrPerfil);
            VLsqlPS.setString(7,correo);
            
            
            // call executeUpdate to execute our sql update statement
            VLsqlPS.executeUpdate();
            VLsqlPS.close();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(UsuarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try 
            {
                VMobjConn.close();
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(UsuarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void deleteUsuario(String VPstrCorreo) 
    {
        Statement VLsqlST;
        try 
        {
            VMobjConn = ConexionBD.getConexion();
            VLsqlST = VMobjConn.createStatement();
            VLsqlST.executeUpdate("DELETE FROM Usuarios WHERE Correo = \'" + VPstrCorreo +"\'");
        
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(UsuarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try 
            {
                VMobjConn.close();
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(UsuarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void addUsuario(String VPstrCorreo, String VPstrPassword, String VPstrNombre,String VPstrAPaterno, String VPstrAMaterno, String VPstrPerfil)
    {
        Statement VLsqlST;
        try 
        {
            VMobjConn = ConexionBD.getConexion();
            VLsqlST = VMobjConn.createStatement();
            VLsqlST.executeUpdate("INSERT INTO Usuarios (correo, password, nombre, apaterno, amaterno, perfil) VALUES (\'"+ VPstrCorreo + "\',\'" + VPstrPassword + "\', \'" + VPstrNombre + "\',\'" + VPstrAPaterno + "\',\'" + VPstrAMaterno +"\',\'" + VPstrPerfil + "\');");
        
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(UsuarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try 
            {
                VMobjConn.close();
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(UsuarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    @Override
    public Usuario getUsuario(String VPstrCorreo) 
    {
        Usuario VLobjUsuario = null;
        try 
        {
            VMobjConn = ConexionBD.getConexion();
            Statement VLsqlST = VMobjConn.createStatement();
            ResultSet VLsqlRS = VLsqlST.executeQuery("SELECT * FROM Usuarios WHERE Correo = \'" + VPstrCorreo + "\'");
            
            while(VLsqlRS.next())
            {
                String VLstrCorreo = VLsqlRS.getString(1);
                String VLstrPassword = VLsqlRS.getString(2).trim();
                String VLstrNombre = VLsqlRS.getString(3);
                String VLstrAPaterno = VLsqlRS.getString(4);
                String VLstrAMaterno = VLsqlRS.getString(5);
                String VLstrPerfil = VLsqlRS.getString(6).trim();
                
                if(VLstrPerfil.equals("Administrador"))
                {
                    VLobjUsuario = new Administrador(VLstrCorreo,
                                                    VLstrPassword,
                                                    VLstrNombre,
                                                    VLstrAPaterno,
                                                    VLstrAMaterno,
                                                    VLstrPerfil);
                }
                else
                {
                    VLobjUsuario = new Cliente(VLstrCorreo,
                                                    VLstrPassword,
                                                    VLstrNombre,
                                                    VLstrAPaterno,
                                                    VLstrAMaterno,
                                                    VLstrPerfil);
                }
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(UsuarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try 
            {
                VMobjConn.close();
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(UsuarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return VLobjUsuario;
    }

        
}
