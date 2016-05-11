/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PartidoDAO;

import Partido.Partido;

/**
 *
 * @author Lucia
 */
public interface PartidoDAO {
    
    public Partido[] getPartido(String id);
    
}
