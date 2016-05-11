/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comparativa.DBComparativa;

/**
 *
 * @author diegoleon
 */
public class PartidoPorDelegacion {
    public String strCVE_Mun;
    public String strMunicipio;
    public String strPartidoColumna;
    public int votos;
    
    public PartidoPorDelegacion(String strPartidoColumna, int votos, String strCVE_Mun, String strMunicipio)
    {
        this.strCVE_Mun = strCVE_Mun;
        this.strPartidoColumna = strPartidoColumna;
        this.votos = votos;
        this.strMunicipio = strMunicipio;
    }
}
