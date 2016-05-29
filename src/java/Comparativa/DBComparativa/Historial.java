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
public class Historial extends Thread
{
    public String strDelegacion;
    public int intAnio;
    public String strNombreCompleto;
    public String strTipo;
    
    public int intSesion;
    public int intIdResultadoElectoral;
    public String strCorreo;
    public String strFechaCreacion;
    
    public void run()
    {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
        new DBComparativa().insertNewHistorial(intSesion, intIdResultadoElectoral, strTipo);
    }
}