/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UsuarioDAO;

import Usuario.Usuario;
import java.util.ArrayList;

/**
 *
 * @author Lucia
 */
public interface UsuarioDAO 
{
    public ArrayList<Usuario> getAllUsuario();
    public void updateUsuario(String VPstrCorreo, String VPstrPassword, String VPstrNombre,String VPstrAPaterno, String VPstrAMaterno, String VPstrPerfil,String correo);
    public void deleteUsuario(String VPstrCorreo);
    public void addUsuario(String VPstrCorreo, String VPstrPassword, String VPstrNombre,String VPstrAPaterno, String VPstrAMaterno, String VPstrPerfil);
    public Usuario getUsuario(String VPstrCorreo);
}
