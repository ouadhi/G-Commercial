/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report.EtatReceptionReport;

import java.util.List;

/**
 *
 * @author Hicham
 */
public class EtatReceptionBean {

    String dateDebut;
    String dateFin;
    String date;
    String totalFour;
    String totalMoulin;
    String totalDif;
    String montantTotal;
    String montantLettre;
    public List<String> nums;
    public List<String> qteFours;
    public List<String> qteMoulins;
    public List<String> qteDifs;

    public EtatReceptionBean(String dateDebut, String dateFin, String date,
             String totalFour, String totalMoulin, String totalDif,
             String montantTotal, String montantLettre, List<String> nums,
             List<String> qteFours, List<String> qteMoulins, List<String> qteDifs) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.date = date;
        this.totalFour = totalFour;
        this.totalMoulin = totalMoulin;
        this.totalDif = totalDif;
        this.montantTotal = montantTotal;
        this.montantLettre = montantLettre;
        this.nums = nums;
        this.qteFours = qteFours;
        this.qteMoulins = qteMoulins;
        this.qteDifs = qteDifs;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotalFour() {
        return totalFour;
    }

    public void setTotalFour(String totalFour) {
        this.totalFour = totalFour;
    }

    public String getTotalMoulin() {
        return totalMoulin;
    }

    public void setTotalMoulin(String totalMoulin) {
        this.totalMoulin = totalMoulin;
    }

    public String getTotalDif() {
        return totalDif;
    }

    public void setTotalDif(String totalDif) {
        this.totalDif = totalDif;
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

    public List<String> getNums() {
        return nums;
    }

    public void setNums(List<String> nums) {
        this.nums = nums;
    }

    public List<String> getQteFours() {
        return qteFours;
    }

    public void setQteFours(List<String> qteFours) {
        this.qteFours = qteFours;
    }

    public List<String> getQteMoulins() {
        return qteMoulins;
    }

    public void setQteMoulins(List<String> qteMoulins) {
        this.qteMoulins = qteMoulins;
    }

    public List<String> getQteDifs() {
        return qteDifs;
    }

    public void setQteDifs(List<String> qteDifs) {
        this.qteDifs = qteDifs;
    }

}
