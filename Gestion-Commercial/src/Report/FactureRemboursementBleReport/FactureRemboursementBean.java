/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report.FactureRemboursementBleReport;

import java.util.List;

/**
 *
 * @author Hicham
 */
public class FactureRemboursementBean {
    String doit;
    String numFacture;
    String dateDebut;
    String dateFin;
    String qteTotal;
    String montantTotal;
    String montantLettre;
    public List<String>references;
    public List<String>qtes;
    public List<String>designations;
    public List<String>prixs;
    public List<String>montants;

    public FactureRemboursementBean(String doit, String numFacture, String dateDebut, String dateFin
            , String qteTotal, String montantTotal, String montantLettre
            , List<String> references, List<String> qtes, List<String> designations
            , List<String> prixs, List<String> montants) {
        this.doit = doit;
        this.numFacture = numFacture;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.qteTotal = qteTotal;
        this.montantTotal = montantTotal;
        this.montantLettre = montantLettre;
        this.references = references;
        this.qtes = qtes;
        this.designations = designations;
        this.prixs = prixs;
        this.montants = montants;
    }
    
    
    

    public String getDoit() {
        return doit;
    }

    public void setDoit(String doit) {
        this.doit = doit;
    }

    public String getNumFacture() {
        return numFacture;
    }

    public void setNumFacture(String numFacture) {
        this.numFacture = numFacture;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getQteTotal() {
        return qteTotal;
    }

    public void setQteTotal(String qteTotal) {
        this.qteTotal = qteTotal;
    }

    public String getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(String montantTotal) {
        this.montantTotal = montantTotal;
    }

    public String getMontantLettre() {
        return montantLettre;
    }

    public void setMontantLettre(String montantLettre) {
        this.montantLettre = montantLettre;
    }

    public List<String> getReferences() {
        return references;
    }

    public void setReferences(List<String> references) {
        this.references = references;
    }

    public List<String> getQtes() {
        return qtes;
    }

    public void setQtes(List<String> qtes) {
        this.qtes = qtes;
    }

    public List<String> getDesignations() {
        return designations;
    }

    public void setDesignations(List<String> designations) {
        this.designations = designations;
    }

    public List<String> getPrixs() {
        return prixs;
    }

    public void setPrixs(List<String> prixs) {
        this.prixs = prixs;
    }

    public List<String> getMontants() {
        return montants;
    }

    public void setMontants(List<String> montants) {
        this.montants = montants;
    }
    



    
    
}
