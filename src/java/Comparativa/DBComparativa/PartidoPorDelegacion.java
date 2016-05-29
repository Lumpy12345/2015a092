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
    public String strNombrePartido;
    public int votos;
    
    public int intValidos;
    public int intNulo;
    public int intTotal;
    public int intLista_nominal;
    public int intParticipacion;
    
    public String strAnio;
    
    public PartidoPorDelegacion
    (
        String strAnio,
        String strCVE_Mun,
        String strMunicipio,
        int intValidos,
        int intNulo,
        int intTotal,
        int intLista_nominal,
        int intParticipacion,
        String strNombrePartido, 
        int votos
    )
    {
        this.strAnio = strAnio;
        this.strCVE_Mun = strCVE_Mun;
        this.strMunicipio = strMunicipio;
        this.intValidos = intValidos;
        this.intNulo = intNulo;
        this.intTotal = intTotal;
        this.intLista_nominal = intLista_nominal;
        this.intParticipacion = intParticipacion;
        this.strNombrePartido =  strNombrePartido;
        this.votos = votos;
    }
}