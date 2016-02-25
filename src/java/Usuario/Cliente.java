/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Usuario;

/**
 *
 * @author Lucia
 */
public class Cliente extends Usuario
{
    public Cliente(String VPstrCorreo,
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
    
    
}
