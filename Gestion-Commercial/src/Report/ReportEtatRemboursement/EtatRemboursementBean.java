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
    String montantTotal;
    String montantLettre;
    public List<String> parcours;
    public List<String> distances;
    public List<String> nums;
    public List<String> qtes;
    public List<String> prixs;
    public List<String> montants;
    public List<String> jours;

    public EtatRemboursementBean(String doit, String date, String montantTotal, String montantLettre,
            List<String> parcours, List<String> distances, List<String> nums, List<String> qtes, List<String> prixs,
            List<String> montants, List<String> jours) {
        this.doit = doit;
        this.date = date;
        this.jours = jours;
        this.montantTotal = montantTotal;
        this.montantLettre = montantLettre;
        this.parcours = parcours;
        this.distances = distances;
        this.nums = nums;
        this.qtes = qtes;
        this.prixs = prixs;
        this.montants = montants;
    }

    public String getDate() {
        return date;
    }

    public List<String> getDistances() {
        return distances;
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

    public List<String> getNums() {
        return nums;
    }

    public List<String> getParcours() {
        return parcours;
    }

    public List<String> getPrixs() {
        return prixs;
    }

    public List<String> getQtes() {
        return qtes;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDistances(List<String> distances) {
        this.distances = distances;
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

    public void setNums(List<String> nums) {
        this.nums = nums;
    }

    public void setParcours(List<String> parcours) {
        this.parcours = parcours;
    }

    public void setPrixs(List<String> prixs) {
        this.prixs = prixs;
    }

    public void setQtes(List<String> qtes) {
        this.qtes = qtes;
    }

    public List<String> getJours() {
        return jours;
    }

    public void setJours(List<String> jours) {
        this.jours = jours;
    }

}
