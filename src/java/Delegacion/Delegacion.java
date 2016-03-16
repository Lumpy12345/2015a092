/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Delegacion;

import ConexionBD.ConexionBD;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucia
 */
public class Delegacion {
    
   private String Did;
   private String DNombre;
   private String DSecciones;
   private String DCasillas;
   private String Alianza;
   private String Porelbien;
   private String NuevaAlianza;
   private String Alternativa; 
   private String Movimiento;
   private String PRIPVEM;
   private String PRDPTMovimiento;
   private String PRDPT;
   private String PRDMovimiento;
   private String PTMovimiento;
   private String Ac;
   private String Am;
   private String Pcd;
   private String Dsppn;
   private String Pan;
   private String Pri;
   private String Pps;
   private String PRD;
   private String Pfcrn;
   private String Parm;
   private String Unopdm;
   private String Pt;
   private String Pvem;
  
   private String DNoRegistrados;
   private String Validos;
   private String DNulos;
   private String DTotal;
   private String DListaNominal;
   private String DParticipacion;

 //1994
   public Delegacion(String Did,
                     String DNombre,
                     String DSecciones,
                     String DCasillas,
                     String pan,
                     String pri,
                     String pps,
                     String prd,
                     String pfcrn,
                     String parm,
                     String uno,
                     String pt,
                     String pvem,
                     String DNoRegistrados,
                     String validos,
                     String DNulos,
                     String DTotal,
                     String DListaNominal,
                     String DParticipacion){
       this.Did=Did;
       this.DNombre= DNombre;
       this.DSecciones=DSecciones;
       this.DCasillas=DCasillas;
       this.Pan=pan;
       this.Pri=pri;
       this.Pps=pps;
       this.PRD=prd;
       this.Pfcrn=pfcrn;
       this.Parm=parm;
       this.Unopdm=uno;
       this.Pt=pt;
       this.Pvem=pvem;
       this.DNoRegistrados=DNoRegistrados;
       this.Validos=validos;
       this.DNulos=DNulos;
       this.DTotal=DTotal;
       this.DListaNominal=DListaNominal;
       this.DParticipacion=DParticipacion;
       
   }
   //2000
     public Delegacion(String Did,
                     String DNombre,
                     String DSecciones,
                     String DCasillas,
                     String ac,
                     String pri,
                     String am,
                     String pcd,
                     String parm,
                     String dsppn,
                     String DNoRegistrados,
                     String validos,
                     String DNulos,
                     String DTotal,
                     String DListaNominal,
                     String DParticipacion){
       this.Did=Did;
       this.DNombre= DNombre;
       this.DSecciones=DSecciones;
       this.DCasillas=DCasillas;
       this.Ac=ac;
       this.Pri=pri;
       this.Am=am;
       this.Pcd=pcd;
       this.Parm=parm;
       this.Dsppn=dsppn;
       this.DNoRegistrados=DNoRegistrados;
       this.Validos=validos;
       this.DNulos=DNulos;
       this.DTotal=DTotal;
       this.DListaNominal=DListaNominal;
       this.DParticipacion=DParticipacion;
       
   }
      //2006
     public Delegacion(String Did,
                     String DNombre,
                     String DSecciones,
                     String DCasillas,
                     String pan,
                     String alianza,
                     String porelbien,
                     String nuevaalianza,
                     String alternativa,
                     String DNoRegistrados,
                     String validos,
                     String DNulos,
                     String DTotal,
                     String DListaNominal,
                     String DParticipacion){
       this.Did=Did;
       this.DNombre= DNombre;
       this.DSecciones=DSecciones;
       this.DCasillas=DCasillas;
       this.Pan=pan;
       this.Alianza=alianza;
       this.Porelbien=porelbien;
       this.NuevaAlianza=nuevaalianza;
       this.Alternativa=alternativa;
       this.DNoRegistrados=DNoRegistrados;
       this.Validos=validos;
       this.DNulos=DNulos;
       this.DTotal=DTotal;
       this.DListaNominal=DListaNominal;
       this.DParticipacion=DParticipacion;
       
   }
      //2012
     public Delegacion(String Did,
                     String DNombre,
                     String DSecciones,
                     String DCasillas,
                     String pan,
                     String pri,
                     String prd,
                     String pvem,
                     String pt,
                     String movimiento,
                     String nuevaalianza,
                     String pripvem,
                     String prdptmc,
                     String prdpt,
                     String prdmc,
                     String ptmc,
                     String DNoRegistrados,
                     String DNulos,
                     String DTotal,
                     String DListaNominal,
                     String DParticipacion){
       this.Did=Did;
       this.DNombre= DNombre;
       this.DSecciones=DSecciones;
       this.DCasillas=DCasillas;
       this.Pan=pan;
       this.Pri=pri;
       this.PRD=prd;
       this.Pvem=pvem;
       this.Pt=pt;
       this.Movimiento=movimiento;
       this.NuevaAlianza=nuevaalianza;
       this.PRIPVEM=pripvem;
       this.PRDPTMovimiento=prdptmc;
       this.PRDPT=prdpt;
       this.PRDMovimiento=prdmc;
       this.PTMovimiento=ptmc;
       this.DNoRegistrados=DNoRegistrados;
       this.DNulos=DNulos;
       this.DTotal=DTotal;
       this.DListaNominal=DListaNominal;
       this.DParticipacion=DParticipacion;
       
   }
     

    /**
     * @return the Did
     */
    public String getDid() {
        return Did;
    }

    /**
     * @param Did the Did to set
     */
    public void setDid(String Did) {
        this.Did = Did;
    }

    /**
     * @return the DNombre
     */
    public String getDNombre() {
        return DNombre;
    }

    /**
     * @param DNombre the DNombre to set
     */
    public void setDNombre(String DNombre) {
        this.DNombre = DNombre;
    }

    /**
     * @return the DSecciones
     */
    public String getDSecciones() {
        return DSecciones;
    }

    /**
     * @param DSecciones the DSecciones to set
     */
    public void setDSecciones(String DSecciones) {
        this.DSecciones = DSecciones;
    }

    /**
     * @return the DCasillas
     */
    public String getDCasillas() {
        return DCasillas;
    }

    /**
     * @param DCasillas the DCasillas to set
     */
    public void setDCasillas(String DCasillas) {
        this.DCasillas = DCasillas;
    }

    /**
     * @return the Alianza
     */
    public String getAlianza() {
        return Alianza;
    }

    /**
     * @param Alianza the Alianza to set
     */
    public void setAlianza(String Alianza) {
        this.Alianza = Alianza;
    }

    /**
     * @return the Porelbien
     */
    public String getPorelbien() {
        return Porelbien;
    }

    /**
     * @param Porelbien the Porelbien to set
     */
    public void setPorelbien(String Porelbien) {
        this.Porelbien = Porelbien;
    }

    /**
     * @return the NuevaAlianza
     */
    public String getNuevaAlianza() {
        return NuevaAlianza;
    }

    /**
     * @param NuevaAlianza the NuevaAlianza to set
     */
    public void setNuevaAlianza(String NuevaAlianza) {
        this.NuevaAlianza = NuevaAlianza;
    }

    /**
     * @return the Alternativa
     */
    public String getAlternativa() {
        return Alternativa;
    }

    /**
     * @param Alternativa the Alternativa to set
     */
    public void setAlternativa(String Alternativa) {
        this.Alternativa = Alternativa;
    }

    /**
     * @return the Movimiento
     */
    public String getMovimiento() {
        return Movimiento;
    }

    /**
     * @param Movimiento the Movimiento to set
     */
    public void setMovimiento(String Movimiento) {
        this.Movimiento = Movimiento;
    }

    /**
     * @return the PRIPVEM
     */
    public String getPRIPVEM() {
        return PRIPVEM;
    }

    /**
     * @param PRIPVEM the PRIPVEM to set
     */
    public void setPRIPVEM(String PRIPVEM) {
        this.PRIPVEM = PRIPVEM;
    }

    /**
     * @return the PRDPTMovimiento
     */
    public String getPRDPTMovimiento() {
        return PRDPTMovimiento;
    }

    /**
     * @param PRDPTMovimiento the PRDPTMovimiento to set
     */
    public void setPRDPTMovimiento(String PRDPTMovimiento) {
        this.PRDPTMovimiento = PRDPTMovimiento;
    }

    /**
     * @return the PRDPT
     */
    public String getPRDPT() {
        return PRDPT;
    }

    /**
     * @param PRDPT the PRDPT to set
     */
    public void setPRDPT(String PRDPT) {
        this.PRDPT = PRDPT;
    }

    /**
     * @return the PRDMovimiento
     */
    public String getPRDMovimiento() {
        return PRDMovimiento;
    }

    /**
     * @param PRDMovimiento the PRDMovimiento to set
     */
    public void setPRDMovimiento(String PRDMovimiento) {
        this.PRDMovimiento = PRDMovimiento;
    }

    /**
     * @return the PTMovimiento
     */
    public String getPTMovimiento() {
        return PTMovimiento;
    }

    /**
     * @param PTMovimiento the PTMovimiento to set
     */
    public void setPTMovimiento(String PTMovimiento) {
        this.PTMovimiento = PTMovimiento;
    }

    /**
     * @return the Ac
     */
    public String getAc() {
        return Ac;
    }

    /**
     * @param Ac the Ac to set
     */
    public void setAc(String Ac) {
        this.Ac = Ac;
    }

    /**
     * @return the Am
     */
    public String getAm() {
        return Am;
    }

    /**
     * @param Am the Am to set
     */
    public void setAm(String Am) {
        this.Am = Am;
    }

    /**
     * @return the Pcd
     */
    public String getPcd() {
        return Pcd;
    }

    /**
     * @param Pcd the Pcd to set
     */
    public void setPcd(String Pcd) {
        this.Pcd = Pcd;
    }

    /**
     * @return the Dsppn
     */
    public String getDsppn() {
        return Dsppn;
    }

    /**
     * @param Dsppn the Dsppn to set
     */
    public void setDsppn(String Dsppn) {
        this.Dsppn = Dsppn;
    }

    /**
     * @return the Pan
     */
    public String getPan() {
        return Pan;
    }

    /**
     * @param Pan the Pan to set
     */
    public void setPan(String Pan) {
        this.Pan = Pan;
    }

    /**
     * @return the Pri
     */
    public String getPri() {
        return Pri;
    }

    /**
     * @param Pri the Pri to set
     */
    public void setPri(String Pri) {
        this.Pri = Pri;
    }

    /**
     * @return the Pps
     */
    public String getPps() {
        return Pps;
    }

    /**
     * @param Pps the Pps to set
     */
    public void setPps(String Pps) {
        this.Pps = Pps;
    }

    /**
     * @return the PRD
     */
    public String getPRD() {
        return PRD;
    }

    /**
     * @param PRD the PRD to set
     */
    public void setPRD(String PRD) {
        this.PRD = PRD;
    }

    /**
     * @return the Pfcrn
     */
    public String getPfcrn() {
        return Pfcrn;
    }

    /**
     * @param Pfcrn the Pfcrn to set
     */
    public void setPfcrn(String Pfcrn) {
        this.Pfcrn = Pfcrn;
    }

    /**
     * @return the Parm
     */
    public String getParm() {
        return Parm;
    }

    /**
     * @param Parm the Parm to set
     */
    public void setParm(String Parm) {
        this.Parm = Parm;
    }

    /**
     * @return the Unopdm
     */
    public String getUnopdm() {
        return Unopdm;
    }

    /**
     * @param Unopdm the Unopdm to set
     */
    public void setUnopdm(String Unopdm) {
        this.Unopdm = Unopdm;
    }

    /**
     * @return the Pt
     */
    public String getPt() {
        return Pt;
    }

    /**
     * @param Pt the Pt to set
     */
    public void setPt(String Pt) {
        this.Pt = Pt;
    }

    /**
     * @return the Pvem
     */
    public String getPvem() {
        return Pvem;
    }

    /**
     * @param Pvem the Pvem to set
     */
    public void setPvem(String Pvem) {
        this.Pvem = Pvem;
    }

    /**
     * @return the DNoRegistrados
     */
    public String getDNoRegistrados() {
        return DNoRegistrados;
    }

    /**
     * @param DNoRegistrados the DNoRegistrados to set
     */
    public void setDNoRegistrados(String DNoRegistrados) {
        this.DNoRegistrados = DNoRegistrados;
    }

    /**
     * @return the Validos
     */
    public String getValidos() {
        return Validos;
    }

    /**
     * @param Validos the Validos to set
     */
    public void setValidos(String Validos) {
        this.Validos = Validos;
    }

    /**
     * @return the DNulos
     */
    public String getDNulos() {
        return DNulos;
    }

    /**
     * @param DNulos the DNulos to set
     */
    public void setDNulos(String DNulos) {
        this.DNulos = DNulos;
    }

    /**
     * @return the DTotal
     */
    public String getDTotal() {
        return DTotal;
    }

    /**
     * @param DTotal the DTotal to set
     */
    public void setDTotal(String DTotal) {
        this.DTotal = DTotal;
    }

    /**
     * @return the DListaNominal
     */
    public String getDListaNominal() {
        return DListaNominal;
    }

    /**
     * @param DListaNominal the DListaNominal to set
     */
    public void setDListaNominal(String DListaNominal) {
        this.DListaNominal = DListaNominal;
    }

    /**
     * @return the DParticipacion
     */
    public String getDParticipacion() {
        return DParticipacion;
    }

    /**
     * @param DParticipacion the DParticipacion to set
     */
    public void setDParticipacion(String DParticipacion) {
        this.DParticipacion = DParticipacion;
    }
   
   
    
}
