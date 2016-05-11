/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comparativa;

import Comparativa.DBComparativa.PartidoPorDelegacion;
import Comparativa.DBComparativa.DBComparativa;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diegoleon
 */
public class AnioConcrete implements Strategy{
    private Comparativa comparativa;
    private String Ano;
    private String strCVE_Mun;
    private List<List<PartidoPorDelegacion>> listTabla;
    private List<PartidoPorDelegacion> listPartidoGanadorPorDelegacion;
    
    public AnioConcrete(String Ano, String strCVE_Mun)
    {
        comparativa = new Comparativa();
        this.Ano = Ano;
        this.strCVE_Mun = strCVE_Mun;
        
        listPartidoGanadorPorDelegacion = new ArrayList();
    }
    
    @Override
    public void iniciarComparacion() {
        listTabla = new DBComparativa().getInfoByYear(Ano);
        
        for(List<PartidoPorDelegacion> listaPartidosPorDelegacion : listTabla)
        {
            PartidoPorDelegacion partidoGanadorPorDelegacion = getPartidoGanadorPorDelegacion(listaPartidosPorDelegacion);
            
            listPartidoGanadorPorDelegacion.add(partidoGanadorPorDelegacion);
            
            if(partidoGanadorPorDelegacion.strCVE_Mun.equals(strCVE_Mun))
            {
                comparativa.strCVE_MUN = partidoGanadorPorDelegacion.strCVE_Mun;
                comparativa.strNombreDelegacion = partidoGanadorPorDelegacion.strMunicipio;
                comparativa.partidoGanador = partidoGanadorPorDelegacion.strPartidoColumna;
                comparativa.votosPartidoGanador = partidoGanadorPorDelegacion.votos;
                
                comparativa.listResultadosDelegacionSeleccionada = listaPartidosPorDelegacion;
                
            }
            
        }
        
        comparativa.ordenarTabla(comparativa.listResultadosDelegacionSeleccionada);
        
        for(PartidoPorDelegacion partido : listPartidoGanadorPorDelegacion)
        {
            if(partido.strPartidoColumna.equals(comparativa.partidoGanador))
            {
                comparativa.listOtrasDelegacionesGanadas.add(partido);
            }
        }
        
        comparativa.ordenarTabla(comparativa.listOtrasDelegacionesGanadas);
        
        float fltPorcentajeVictoria = (float)((comparativa.listOtrasDelegacionesGanadas.size() * 100F) / listPartidoGanadorPorDelegacion.size());
        
        comparativa.intDelegacionesGanadas = comparativa.listOtrasDelegacionesGanadas.size();
        comparativa.intDelegacionesPerdidas = 16 - comparativa.listOtrasDelegacionesGanadas.size();
        comparativa.floatPorcentajeVictoria = fltPorcentajeVictoria;
        
    }

    @Override
    public Comparativa getComparativa() {
        return comparativa;
    }
    
    private PartidoPorDelegacion getPartidoGanadorPorDelegacion(List<PartidoPorDelegacion> listaPartidosPorDelegacion)
    {
        PartidoPorDelegacion menor = listaPartidosPorDelegacion.get(0);
        PartidoPorDelegacion mayor = listaPartidosPorDelegacion.get(1);
        
        if(menor.votos > mayor.votos)
            mayor = menor;
        
        for(int i = 2 ; i < listaPartidosPorDelegacion.size() ; i++ )
        {
            menor = listaPartidosPorDelegacion.get(i);
            
            if(menor.votos > mayor.votos)
                mayor = menor;
        }
        /*
        System.out.println("*********************************************");
        System.out.println("CVE_MUN : " + mayor.strCVE_Mun);
        System.out.println("PArtido columna : " + mayor.strPartidoColumna);
        System.out.println("Municipio nombre : " + mayor.strMunicipio);
        System.out.println("Votos : " + mayor.votos);
        System.out.println("*********************************************");
        */
        return mayor;
    }
    
}
