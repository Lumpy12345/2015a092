/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Partido;


public class Partido {
    private double PAN;
    private double PRI;
    private double PRD;
    private double PT;
    private double PVEM;
    private double Morena;
    private double NuevaAlianza;
    private double Movimiento;
    private double Validos;
    private double DNulos;
    private double DTotal;
    private double DListaNominal;
    private double Participacion;
    
public Partido(double PRI,double PAN,double PRD,double PT,double PVEM,double m,double NuevaAlianza,double Movimiento,double v,double nul,double to,double lis,double par){
    this.PAN=PAN;
    this.PRI=PRI;
    this.PRD=PRD;
    this.PT=PT;
    this.PVEM=PVEM;
    this.Morena=m;
    this.NuevaAlianza=NuevaAlianza;
    this.Movimiento=Movimiento;
    this.Validos=v;
    this.DNulos=nul;
    this.DTotal=to;
    this.DListaNominal=lis;
    this.Participacion=par;
    
}


    /**
     * @return the PAN
     */
    public double getPAN() {
        return PAN;
    }

    /**
     * @param PAN the PAN to set
     */
    public void setPAN(double PAN) {
        this.PAN = PAN;
    }

    /**
     * @return the PRI
     */
    public double getPRI() {
        return PRI;
    }

    /**
     * @param PRI the PRI to set
     */
    public void setPRI(double PRI) {
        this.PRI = PRI;
    }

    /**
     * @return the PRD
     */
    public double getPRD() {
        return PRD;
    }

    /**
     * @param PRD the PRD to set
     */
    public void setPRD(double PRD) {
        this.PRD = PRD;
    }

    /**
     * @return the PT
     */
    public double getPT() {
        return PT;
    }

    /**
     * @param PT the PT to set
     */
    public void setPT(double PT) {
        this.PT = PT;
    }

    /**
     * @return the PVEM
     */
    public double getPVEM() {
        return PVEM;
    }

    /**
     * @param PVEM the PVEM to set
     */
    public void setPVEM(double PVEM) {
        this.PVEM = PVEM;
    }

    /**
     * @return the Morena
     */
    public double getMorena() {
        return Morena;
    }

    /**
     * @param Morena the Morena to set
     */
    public void setMorena(double Morena) {
        this.Morena = Morena;
    }

    /**
     * @return the NuevaAlianza
     */
    public double getNuevaAlianza() {
        return NuevaAlianza;
    }

    /**
     * @param NuevaAlianza the NuevaAlianza to set
     */
    public void setNuevaAlianza(double NuevaAlianza) {
        this.NuevaAlianza = NuevaAlianza;
    }

    /**
     * @return the Movimiento
     */
    public double getMovimiento() {
        return Movimiento;
    }

    /**
     * @param Movimiento the Movimiento to set
     */
    public void setMovimiento(double Movimiento) {
        this.Movimiento = Movimiento;
    }

    /**
     * @return the Validos
     */
    public double getValidos() {
        return Validos;
    }

    /**
     * @param Validos the Validos to set
     */
    public void setValidos(double Validos) {
        this.Validos = Validos;
    }

    /**
     * @return the DNulos
     */
    public double getDNulos() {
        return DNulos;
    }

    /**
     * @param DNulos the DNulos to set
     */
    public void setDNulos(double DNulos) {
        this.DNulos = DNulos;
    }

    /**
     * @return the DTotal
     */
    public double getDTotal() {
        return DTotal;
    }

    /**
     * @param DTotal the DTotal to set
     */
    public void setDTotal(double DTotal) {
        this.DTotal = DTotal;
    }

    /**
     * @return the DListaNominal
     */
    public double getDListaNominal() {
        return DListaNominal;
    }

    /**
     * @param DListaNominal the DListaNominal to set
     */
    public void setDListaNominal(double DListaNominal) {
        this.DListaNominal = DListaNominal;
    }

    /**
     * @return the Participacion
     */
    public double getParticipacion() {
        return Participacion;
    }

    /**
     * @param Participacion the Participacion to set
     */
    public void setParticipacion(double Participacion) {
        this.Participacion = Participacion;
    }




    }
