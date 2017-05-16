/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report.ReportEtatRemboursement;

import java.util.List;

/**
 *
 * @author Hicham
 */
public class EtatRemboursementBean {
    
    String doit;
    String date;
    String jour;
    String montantTotal;
    String montantLettre;
    public List<String>parcours;
    public List<String>distances;
    public List<String>nums;
    public List<String>qtes;
    public List<String>prixs;
    public List<String>montants;

    public EtatRemboursementBean(String doit, String date, String jour, String montantTotal
            , String montantLettre, List<String> parcours, List<String> distances
            , List<String> nums, List<String> qtes, List<String> prixs, List<String> montants) {
        this.doit = doit;
        this.date = date;
        this.jour = jour;
        this.montantTotal = montantTotal;
        this.montantLettre = montantLettre;
        this.parcours = parcours;
        this.distances = distances;
        this.nums = nums;
        this.qtes = qtes;
        this.prixs = prixs;
        this.montants = montants;
    }

    public String getDoit() {
        return doit;
    }

    public void setDoit(String doit) {
        this.doit = doit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
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

    public List<String> getParcours() {
        return parcours;
    }

    public void setParcours(List<String> parcours) {
        this.parcours = parcours;
    }

    public List<String> getDistances() {
        return distances;
    }

    public void setDistances(List<String> distances) {
        this.distances = distances;
    }

    public List<String> getNums() {
        return nums;
    }

    public void setNums(List<String> nums) {
        this.nums = nums;
    }

    public List<String> getQtes() {
        return qtes;
    }

    public void setQtes(List<String> qtes) {
        this.qtes = qtes;
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
