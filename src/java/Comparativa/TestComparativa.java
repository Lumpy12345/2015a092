/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comparativa;

import Comparativa.DBComparativa.DBComparativa;
import Comparativa.DBComparativa.PartidoPorDelegacion;
import java.util.List;

/**
 *
 * @author diegoleon
 */
public class TestComparativa {
    public static void main(String[] args) {
        
//       List<List<PartidoPorDelegacion>> lista = new DBComparativa().getInfoByYear("2000");
//        
//        
//        
//        for(List<PartidoPorDelegacion> list2 : lista)
//        {
//            for(PartidoPorDelegacion partido : list2)
//            {
//                
//                
//                if(partido.strCVE_Mun.equals("002"))
//                {
//                    System.out.println("************************************");
//                    System.out.println("CVE_MUN : " + partido.strCVE_Mun);
//                    System.out.println("PArtido columna : " + partido.strPartidoColumna);
//                    System.out.println("Municipio nombre : " + partido.strMunicipio);
//                    System.out.println("Votos : " + partido.votos);
//                    System.out.println("************************************");
//                }
//            }
//            
//        }
       
        
        Strategy strategy = new AnioConcrete("2012", "002");
        strategy.iniciarComparacion();
        
        Comparativa comparativa = strategy.getComparativa();
        
        
        System.out.println("cve_mun : " + comparativa.strCVE_MUN);
        System.out.println("Nombre : " + comparativa.strNombreDelegacion);
        System.out.println("Partido Ganador : " + comparativa.partidoGanador);
        System.out.println("Votos : " + comparativa.votosPartidoGanador);
        
        for(PartidoPorDelegacion partido : comparativa.listResultadosDelegacionSeleccionada)
        {
            System.out.println("------------------------------------");
            System.out.println("CVE_MUN : " + partido.strCVE_Mun);
            System.out.println("PArtido columna : " + partido.strNombrePartido);
            System.out.println("Municipio nombre : " + partido.strMunicipio);
            System.out.println("Votos : " + partido.votos);
            System.out.println("------------------------------------");
        }
        
        for(PartidoPorDelegacion partido : comparativa.listOtrasDelegacionesGanadas)
        {
            System.out.println("************************************");
            System.out.println("CVE_MUN : " + partido.strCVE_Mun);
            System.out.println("PArtido columna : " + partido.strNombrePartido);
            System.out.println("Municipio nombre : " + partido.strMunicipio);
            System.out.println("Votos : " + partido.votos);
            System.out.println("************************************");
        }
        
        System.out.println("Delegaciones ganadas : " + comparativa.intDelegacionesGanadas);
        System.out.println("Delegaciones perdidas : " + comparativa.intDelegacionesPerdidas);
        System.out.println("Porcentaje victoria : " + comparativa.floatPorcentajeVictoria);
        
    }
    
}