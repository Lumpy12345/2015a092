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
   //2015
   private String PRDPTNuevaAlianza;
   private String Morena;
   private String PH;
   private String PES;
   private String Independiente;
   //2012
   private String PAN;
   private String PRI;
   private String PRD;
   private String Pt;
   private String Pvem;
   private String Movimiento;
   private String NuevaAlianza;
   private String PRIPVEM;
   private String PRDPTMovimiento;
   //completos
   //2009
   /*
   existentes
   PAN PRI PRD PT PVEM Nueva Alianza
   
   */
   private String Convergencia;
  
   private String PRDPTConvergencia;
   private String PRDPT;
   private String PRDConvergencia;
   private String PTConvergencia;
   private String PSD;
   //completo
   
   //2006
   /*
   existentes 
   PAN PRIPVEM PRDPTCONV NuevaALianza
   */
   private String PASC;
   private String PANNuevaAlianza;
   //completo
   //2003
   /*
   existentes
   PAN PRI PRD PT Convergencia PVEM 
   */
   private String FC;
   private String MP;
   private String PAS;
   private String PSN;
   private String PLM;
   //completo
   //2000
   /*
   existentes 
   PRI PRD PT CONVERGENCIA PSN PAS 
   */
   private String PANPVEM;
   private String PCD;
   private String PARM;
   private String DS;
   
  //completos
   
   
  
   private String Validos;
   private String DNulos;
   private String DTotal;
   private String DListaNominal;
   private String DParticipacion;

 //2000-17
   public Delegacion(String Did,
                     String DNombre,
                     String panpvem,
                     String pri,
                     String prd,
                     String pt,
                     String conver,
                     String pcd,
                     String psn,
                     String parm,
                     String pas,
                     String ds,
                     String validos,
                     String DNulos,
                     String DTotal,
                     String DListaNominal,
                     String DParticipacion){
       this.Did=Did;
       this.DNombre= DNombre;
       this.PANPVEM=panpvem;
       this.PRI=pri;
       this.PRD=prd;
       this.Pt=pt;
       this.Convergencia=conver;
       this.PCD=pcd;
       this.PSN=psn;
       this.PARM=parm;
       this.PAS=pas;
       this.DS=ds;
       this.Validos=validos;
       this.DNulos=DNulos;
       this.DTotal=DTotal;
       this.DListaNominal=DListaNominal;
       this.DParticipacion=DParticipacion;
       
   }
   //2003-18
     public Delegacion(String Did,
                     String DNombre,
                     String pan,
                     String pri,
                     String prd,
                     String pt,
                     String pvem,
                     String conver,
                     String psn,
                     String pas,
                     String mp,
                     String plm,
                     String fc,
                     String validos,
                     String DNulos,
                     String DTotal,
                     String DListaNominal,
                     String DParticipacion){
       this.Did=Did;
       this.DNombre= DNombre;
       this.PAN=pan;
       this.PRI=pri;
       this.PRD=prd;
       this.Pt=pt;
       this.Pvem=pvem;
       this.Convergencia=conver;
       this.PSN=psn;
       this.PAS=pas;
       this.MP=mp;
       this.PLM=plm;
       this.FC=fc;
       this.Validos=validos;
       this.DNulos=DNulos;
       this.DTotal=DTotal;
       this.DListaNominal=DListaNominal;
       this.DParticipacion=DParticipacion;
       
   }
      //2006-13
     public Delegacion(String Did,
                     String DNombre,
                     String pan,
                     String pripvem,
                     String prdptconver,
                     String nuevaalianza,
                     String pasc,
                     String pannuevaalianza,
                     String validos,
                     String DNulos,
                     String DTotal,
                     String DListaNominal,
                     String DParticipacion){
       this.Did=Did;
       this.DNombre= DNombre;
       this.PAN=pan;
       this.PRIPVEM=pripvem;
       this.PRDPTConvergencia=prdptconver;
       this.NuevaAlianza=nuevaalianza;
       this.PASC=pasc;
       this.PANNuevaAlianza=pannuevaalianza;
       this.Validos=validos;
       this.DNulos=DNulos;
       this.DTotal=DTotal;
       this.DListaNominal=DListaNominal;
       this.DParticipacion=DParticipacion;
       
   }
      //2009 19
     public Delegacion(String Did,
                     String DNombre,
                     String pan,
                     String pri,
                     String prd,
                     String pt,
                     String pvem,
                     String conver,
                     String nuevaalianza,
                     String psd,
                     String prdptconver,
                     String prdpt,
                     String prdconver,
                     String ptconver,
                     String validos,
                     String DNulos,
                     String DTotal,
                     String DListaNominal,
                     String DParticipacion){
       this.Did=Did;
       this.DNombre= DNombre;
       this.PAN=pan;
       this.PRI=pri;
       this.PRD=prd;
       this.Pt=pt;
       this.Pvem=pvem;
       this.Convergencia=conver;
       this.NuevaAlianza=nuevaalianza;
       this.PSD=psd;
       this.PRDPTConvergencia=prdptconver;
       this.PRDPT=prdpt;
       this.PRDConvergencia=prdconver;
       this.PTConvergencia=ptconver;
       this.Validos=validos;
       this.DNulos=DNulos;
       this.DTotal=DTotal;
       this.DListaNominal=DListaNominal;
       this.DParticipacion=DParticipacion;
       
   }
     //2012-16
     public Delegacion(String Did,
                     String DNombre,
                     String pan,
                     String pri,
                     String prd,
                     String pt,
                     String pvem,
                     String mc,
                     String na,
                     String pripvem,
                     String prdptmc,
                     String validos,
                     String DNulos,
                     String DTotal,
                     String DListaNominal,
                     String DParticipacion){
       this.Did=Did;
       this.DNombre= DNombre;
       this.PAN=pan;
       this.PRI=pri;
       this.PRD=prd;
       this.Pt=pt;
       this.Pvem=pvem;
       this.Movimiento=mc;
       this.NuevaAlianza=na;
       this.PRIPVEM=pripvem;
       this.PRDPTMovimiento=prdptmc;
       this.Validos=validos;
       this.DNulos=DNulos;
       this.DTotal=DTotal;
       this.DListaNominal=DListaNominal;
       this.DParticipacion=DParticipacion;
       
   }
    //2015-20
     public Delegacion(String Did,
                     String DNombre,
                     String pan,
                     String pripvem,
                     String prdpt,
                     String prdptnuevaalianza,
                     String prd,
                     String pt,
                     String movimiento,
                     String nuevaalianza,
                     String morena,
                     String ph,
                     String pes,
                     String inde,
                     String validos,
                     String DNulos,
                     String DTotal,
                     String DListaNominal,
                     String DParticipacion,String a){
       this.Did=Did;
       this.DNombre= DNombre;
       this.PAN=pan;
       this.PRIPVEM=pripvem;
       this.PRDPT=prdpt;
       this.PRDPTNuevaAlianza=prdptnuevaalianza;
       this.PRD=prd;
       this.Pt=pt;
       this.Movimiento=movimiento;
       this.NuevaAlianza=nuevaalianza;
       this.Morena=morena;
       this.PH=ph;
       this.PES=pes;
       this.Independiente=inde;
       this.Validos=validos;
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
     * @return the PRDPTNuevaAlianza
     */
    public String getPRDPTNuevaAlianza() {
        return PRDPTNuevaAlianza;
    }

    /**
     * @param PRDPTNuevaAlianza the PRDPTNuevaAlianza to set
     */
    public void setPRDPTNuevaAlianza(String PRDPTNuevaAlianza) {
        this.PRDPTNuevaAlianza = PRDPTNuevaAlianza;
    }

    /**
     * @return the Morena
     */
    public String getMorena() {
        return Morena;
    }

    /**
     * @param Morena the Morena to set
     */
    public void setMorena(String Morena) {
        this.Morena = Morena;
    }

    /**
     * @return the PH
     */
    public String getPH() {
        return PH;
    }

    /**
     * @param PH the PH to set
     */
    public void setPH(String PH) {
        this.PH = PH;
    }

    /**
     * @return the PES
     */
    public String getPES() {
        return PES;
    }

    /**
     * @param PES the PES to set
     */
    public void setPES(String PES) {
        this.PES = PES;
    }

    /**
     * @return the Independiente
     */
    public String getIndependiente() {
        return Independiente;
    }

    /**
     * @param Independiente the Independiente to set
     */
    public void setIndependiente(String Independiente) {
        this.Independiente = Independiente;
    }

    /**
     * @return the PAN
     */
    public String getPAN() {
        return PAN;
    }

    /**
     * @param PAN the PAN to set
     */
    public void setPAN(String PAN) {
        this.PAN = PAN;
    }

    /**
     * @return the PRI
     */
    public String getPRI() {
        return PRI;
    }

    /**
     * @param PRI the PRI to set
     */
    public void setPRI(String PRI) {
        this.PRI = PRI;
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
     * @return the Convergencia
     */
    public String getConvergencia() {
        return Convergencia;
    }

    /**
     * @param Convergencia the Convergencia to set
     */
    public void setConvergencia(String Convergencia) {
        this.Convergencia = Convergencia;
    }

    /**
     * @return the PRDPTConvergencia
     */
    public String getPRDPTConvergencia() {
        return PRDPTConvergencia;
    }

    /**
     * @param PRDPTConvergencia the PRDPTConvergencia to set
     */
    public void setPRDPTConvergencia(String PRDPTConvergencia) {
        this.PRDPTConvergencia = PRDPTConvergencia;
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
     * @return the PRDConvergencia
     */
    public String getPRDConvergencia() {
        return PRDConvergencia;
    }

    /**
     * @param PRDConvergencia the PRDConvergencia to set
     */
    public void setPRDConvergencia(String PRDConvergencia) {
        this.PRDConvergencia = PRDConvergencia;
    }

    /**
     * @return the PTConvergencia
     */
    public String getPTConvergencia() {
        return PTConvergencia;
    }

    /**
     * @param PTConvergencia the PTConvergencia to set
     */
    public void setPTConvergencia(String PTConvergencia) {
        this.PTConvergencia = PTConvergencia;
    }

    /**
     * @return the PSD
     */
    public String getPSD() {
        return PSD;
    }

    /**
     * @param PSD the PSD to set
     */
    public void setPSD(String PSD) {
        this.PSD = PSD;
    }

    /**
     * @return the PASC
     */
    public String getPASC() {
        return PASC;
    }

    /**
     * @param PASC the PASC to set
     */
    public void setPASC(String PASC) {
        this.PASC = PASC;
    }

    /**
     * @return the PANNuevaAlianza
     */
    public String getPANNuevaAlianza() {
        return PANNuevaAlianza;
    }

    /**
     * @param PANNuevaAlianza the PANNuevaAlianza to set
     */
    public void setPANNuevaAlianza(String PANNuevaAlianza) {
        this.PANNuevaAlianza = PANNuevaAlianza;
    }

    /**
     * @return the FC
     */
    public String getFC() {
        return FC;
    }

    /**
     * @param FC the FC to set
     */
    public void setFC(String FC) {
        this.FC = FC;
    }

    /**
     * @return the MP
     */
    public String getMP() {
        return MP;
    }

    /**
     * @param MP the MP to set
     */
    public void setMP(String MP) {
        this.MP = MP;
    }

    /**
     * @return the PAS
     */
    public String getPAS() {
        return PAS;
    }

    /**
     * @param PAS the PAS to set
     */
    public void setPAS(String PAS) {
        this.PAS = PAS;
    }

    /**
     * @return the PSN
     */
    public String getPSN() {
        return PSN;
    }

    /**
     * @param PSN the PSN to set
     */
    public void setPSN(String PSN) {
        this.PSN = PSN;
    }

    /**
     * @return the PLM
     */
    public String getPLM() {
        return PLM;
    }

    /**
     * @param PLM the PLM to set
     */
    public void setPLM(String PLM) {
        this.PLM = PLM;
    }

    /**
     * @return the PANPVEM
     */
    public String getPANPVEM() {
        return PANPVEM;
    }

    /**
     * @param PANPVEM the PANPVEM to set
     */
    public void setPANPVEM(String PANPVEM) {
        this.PANPVEM = PANPVEM;
    }

    /**
     * @return the PCD
     */
    public String getPCD() {
        return PCD;
    }

    /**
     * @param PCD the PCD to set
     */
    public void setPCD(String PCD) {
        this.PCD = PCD;
    }

    /**
     * @return the PARM
     */
    public String getPARM() {
        return PARM;
    }

    /**
     * @param PARM the PARM to set
     */
    public void setPARM(String PARM) {
        this.PARM = PARM;
    }

    /**
     * @return the DS
     */
    public String getDS() {
        return DS;
    }

    /**
     * @param DS the DS to set
     */
    public void setDS(String DS) {
        this.DS = DS;
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
