/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DelegacionDAO;

import Delegacion.Delegacion;

/**
 *
 * @author Lucia
 */
public interface DelegacionDAO {
    
    public Delegacion getDelegacion(String id,String fecha);
    
}
