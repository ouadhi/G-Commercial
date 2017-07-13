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

    String titreRC;
    String titreFiscal;
    String titreArticle;
    String titreTel;
    String titreFax;
    String titreEmail;
    String entrepriseNom;
    String entrepriseAddress;

    public EtatReceptionBean(String dateDebut, String dateFin, String date, String totalFour, String totalMoulin,
            String totalDif, String montantTotal, String montantLettre, List<String> nums, List<String> qteFours,
            List<String> qteMoulins, List<String> qteDifs, String titreRC, String titreFiscal, String titreArticle,
            String titreTel, String titreFax, String titreEmail, String entrepriseNom, String entrepriseAddress) {
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
        
        this.titreRC = titreRC;
        this.titreFiscal = titreFiscal;
        this.titreArticle = titreArticle;
        this.titreTel = titreTel;
        this.titreFax = titreFax;
        this.titreEmail = titreEmail; 
        this.entrepriseNom=entrepriseNom;
        this.entrepriseAddress=entrepriseAddress;
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

    public String getEntrepriseNom() {
        return entrepriseNom;
    }

    public void setEntrepriseNom(String entrepriseNom) {
        this.entrepriseNom = entrepriseNom;
    }

    public String getEntrepriseAddress() {
        return entrepriseAddress;
    }

    public void setEntrepriseAddress(String entrepriseAddress) {
        this.entrepriseAddress = entrepriseAddress;
    }
    

    public String getDate() {
        return date;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public String getMontantLettre() {
        return montantLettre;
    }

    public String getMontantTotal() {
        return montantTotal;
    }

    public List<String> getNums() {
        return nums;
    }

    public List<String> getQteDifs() {
        return qteDifs;
    }

    public List<String> getQteFours() {
        return qteFours;
    }

    public List<String> getQteMoulins() {
        return qteMoulins;
    }

    public String getTotalDif() {
        return totalDif;
    }

    public String getTotalFour() {
        return totalFour;
    }

    public String getTotalMoulin() {
        return totalMoulin;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public void setMontantLettre(String montantLettre) {
        this.montantLettre = montantLettre;
    }

    public void setMontantTotal(String montantTotal) {
        this.montantTotal = montantTotal;
    }

    public void setNums(List<String> nums) {
        this.nums = nums;
    }

    public void setQteDifs(List<String> qteDifs) {
        this.qteDifs = qteDifs;
    }

    public void setQteFours(List<String> qteFours) {
        this.qteFours = qteFours;
    }

    public void setQteMoulins(List<String> qteMoulins) {
        this.qteMoulins = qteMoulins;
    }

    public void setTotalDif(String totalDif) {
        this.totalDif = totalDif;
    }

    public void setTotalFour(String totalFour) {
        this.totalFour = totalFour;
    }

    public void setTotalMoulin(String totalMoulin) {
        this.totalMoulin = totalMoulin;
    }

}
