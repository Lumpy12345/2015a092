/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comparativa;

import Comparativa.DBComparativa.PartidoPorDelegacion;
import java.util.*;

/**
 *
 * @author diegoleon
 */
public class Comparativa 
{
    public String strCVE_MUN;
    public String strNombreDelegacion;
    public String partidoGanador;
    public int votosPartidoGanador;
    public List<PartidoPorDelegacion> listResultadosDelegacionSeleccionada;
    public List<PartidoPorDelegacion> listOtrasDelegacionesGanadas;
    public int intDelegacionesGanadas;
    public int intDelegacionesPerdidas;
    public float floatPorcentajeVictoria;
    
    public Comparativa()
    {
        listOtrasDelegacionesGanadas = new ArrayList<PartidoPorDelegacion>();
    }
    
    public void ordenarTabla(List<PartidoPorDelegacion> lista)
    {
        Object[] arrPartidos = lista.toArray();
        
        for (int i = 1 ; i < arrPartidos.length ; i++)
        {
            for(int j = 0 ; j < arrPartidos.length - 1; j++)
            {
                  if (((PartidoPorDelegacion)arrPartidos[j]).votos < ((PartidoPorDelegacion)arrPartidos[j+1]).votos)
                  {
                       Object temp = arrPartidos[j];
                       arrPartidos[j] = arrPartidos[j+1];
                       arrPartidos[j+1] = temp;
                  }
            }
        }
        
        lista.clear();
        
        for(Object objeto : arrPartidos)
        {
            lista.add((PartidoPorDelegacion)objeto);
        }
        /*
        for(PartidoPorDelegacion mayor : lista )
        {
            System.out.println("------------------------------------");
            System.out.println("CVE_MUN : " + mayor.strCVE_Mun);
            System.out.println("PArtido columna : " + mayor.strPartidoColumna);
            System.out.println("Municipio nombre : " + mayor.strMunicipio);
            System.out.println("Votos : " + mayor.votos);
            System.out.println("------------------------------------");
        
        }*/
    }
}
