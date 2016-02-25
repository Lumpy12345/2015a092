/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Usuario;

import UsuarioDAO.UsuarioDAOImpl;
import java.util.ArrayList;

/**
 *
 * @author Lucia
 */
public class Administrador extends Usuario 
{
    public Administrador(String VPstrCorreo,
                    String VPstrPassword,
                    String VPstrNombre,
                    String VPstrAPaterno,
                    String VPstrAMaterno,
                    String VPstrPerfil)
    {
        this.VMstrCorreo = VPstrCorreo;
        this.VMstrPassword = VPstrPassword;
        this.VMstrNombre = VPstrNombre;
        this.VMstrAPaterno = VPstrAPaterno;
        this.VMstrAMaterno = VPstrAMaterno;
        this.VMstrPerfil = VPstrPerfil;
    }
    
    public void updateUsuario(String VPstrCorreo, String VPstrPassword, String VPstrNombre, String VPstrAPaterno, String VPstrAMaterno, String VPstrPerfil,String correo)
    {
        UsuarioDAOImpl VLobjUsuarioDAOImpl = new UsuarioDAOImpl();
        
        VLobjUsuarioDAOImpl.updateUsuario(VPstrCorreo, VPstrPassword, VPstrNombre, VPstrAPaterno, VPstrAMaterno, VPstrPerfil,correo);
    }
    
    public void deleteUsuario(String VPstrCorreo)
    {
        UsuarioDAOImpl VLobjUsuarioDAOImpl = new UsuarioDAOImpl();
        
        VLobjUsuarioDAOImpl.deleteUsuario(VPstrCorreo);
    }
    
    public void addUsuario(String VPstrCorreo, String VPstrPassword, String VPstrNombre, String VPstrAPaterno, String VPstrAMaterno, String VPstrPerfil)
    {
        UsuarioDAOImpl VLobjUsuarioDAOImpl = new UsuarioDAOImpl();
        
        VLobjUsuarioDAOImpl.addUsuario(VPstrCorreo, VPstrPassword, VPstrNombre, VPstrAPaterno, VPstrAMaterno, VPstrPerfil);
    }
    
    public ArrayList<Usuario> getUsuarios()
    {
        UsuarioDAOImpl VLobjUsuarioDAOImpl = new UsuarioDAOImpl();
        
        return VLobjUsuarioDAOImpl.getAllUsuario();
    }
    
}
