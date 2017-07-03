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
    public List<String> references;
    public List<String> qtes;
    public List<String> designations;
    public List<String> prixs;
    public List<String> montants;
    
    String titreRC;
    String titreFiscal;
    String titreArticle;
    String titreTel;
    String titreFax;
    String titreEmail;

    public FactureRemboursementBean(String doit, String numFacture, String dateDebut, String dateFin, String qteTotal,
            String montantTotal, String montantLettre, List<String> references, List<String> qtes,
            List<String> designations, List<String> prixs, List<String> montants,String titreRC, String titreFiscal,String titreArticle,
           String titreTel,String titreFax,String titreEmail) {
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
        
        this.titreRC = titreRC;
        this.titreFiscal = titreFiscal;
        this.titreArticle = titreArticle;
        this.titreTel = titreTel;
        this.titreFax = titreFax;
        this.titreEmail = titreEmail;
    }

    public String getTitreRC() {
        return titreRC;
    }

    public void setTitreRC(String titreRC) {
        this.titreRC = titreRC;
    }

    public String getTitreFiscal() {
        return titreFiscal;
    }

    public void setTitreFiscal(String titreFiscal) {
        this.titreFiscal = titreFiscal;
    }

    public String getTitreArticle() {
        return titreArticle;
    }

    public void setTitreArticle(String titreArticle) {
        this.titreArticle = titreArticle;
    }

    public String getTitreTel() {
        return titreTel;
    }

    public void setTitreTel(String titreTel) {
        this.titreTel = titreTel;
    }

    public String getTitreFax() {
        return titreFax;
    }

    public void setTitreFax(String titreFax) {
        this.titreFax = titreFax;
    }

    public String getTitreEmail() {
        return titreEmail;
    }

    public void setTitreEmail(String titreEmail) {
        this.titreEmail = titreEmail;
    }
    
    public String getDateDebut() {
        return dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public List<String> getDesignations() {
        return designations;
    }

    public String getDoit() {
        return doit;
    }

    public String getMontantLettre() {
        return montantLettre;
    }

    public List<String> getMontants() {
        return montants;
    }

    public String getMontantTotal() {
        return montantTotal;
    }

    public String getNumFacture() {
        return numFacture;
    }

    public List<String> getPrixs() {
        return prixs;
    }

    public List<String> getQtes() {
        return qtes;
    }

    public String getQteTotal() {
        return qteTotal;
    }

    public List<String> getReferences() {
        return references;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public void setDesignations(List<String> designations) {
        this.designations = designations;
    }

    public void setDoit(String doit) {
        this.doit = doit;
    }

    public void setMontantLettre(String montantLettre) {
        this.montantLettre = montantLettre;
    }

    public void setMontants(List<String> montants) {
        this.montants = montants;
    }

    public void setMontantTotal(String montantTotal) {
        this.montantTotal = montantTotal;
    }

    public void setNumFacture(String numFacture) {
        this.numFacture = numFacture;
    }

    public void setPrixs(List<String> prixs) {
        this.prixs = prixs;
    }

    public void setQtes(List<String> qtes) {
        this.qtes = qtes;
    }

    public void setQteTotal(String qteTotal) {
        this.qteTotal = qteTotal;
    }

    public void setReferences(List<String> references) {
        this.references = references;
    }

}
