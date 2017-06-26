/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report.EtatEstimatifGlobal;

/**
 *
 * @author Hicham
 */
public class EtatEstimatifGlobalBean {

    String nomPrenom;
    String activity;
    String address;
    String rc;
    String fiscal;
    String article;
    String dateDebut;
    String dateFin;
    String totalMontant;
    String totalTva;
    String totalTtc;
    String totalTimbre;
    String totalFarine;
    String totalSon;

    public EtatEstimatifGlobalBean(String nomPrenom, String activity, String address, String rc, String fiscal,
            String article, String dateDebut, String dateFin, String totalMontant, String totalTva, String totalTtc,
            String totalTimbre,String totalFarine,String totalSon) {
        this.nomPrenom = nomPrenom;
        this.activity = activity;
        this.address = address;
        this.rc = rc;
        this.fiscal = fiscal;
        this.article = article;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.totalMontant = totalMontant;
        this.totalTva = totalTva;
        this.totalTtc = totalTtc;
        this.totalTimbre= totalTimbre;
        this.totalFarine= totalFarine;
        this.totalSon= totalSon;
    }

    public String getActivity() {
        return activity;
    }

    public String getAddress() {
        return address;
    }

    public String getArticle() {
        return article;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public String getFiscal() {
        return fiscal;
    }

    public String getNomPrenom() {
        return nomPrenom;
    }

    public String getRc() {
        return rc;
    }

    public String getTotalMontant() {
        return totalMontant;
    }

    public String getTotalTtc() {
        return totalTtc;
    }

    public String getTotalTva() {
        return totalTva;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public void setFiscal(String fiscal) {
        this.fiscal = fiscal;
    }

    public void setNomPrenom(String nomPrenom) {
        this.nomPrenom = nomPrenom;
    }

    public void setRc(String rc) {
        this.rc = rc;
    }

    public void setTotalMontant(String totalMontant) {
        this.totalMontant = totalMontant;
    }

    public void setTotalTtc(String totalTtc) {
        this.totalTtc = totalTtc;
    }

    public void setTotalTva(String totalTva) {
        this.totalTva = totalTva;
    }

    public String getTotalTimbre() {
        return totalTimbre;
    }

    public void setTotalTimbre(String totalTimbre) {
        this.totalTimbre = totalTimbre;
    }

    public String getTotalFarine() {
        return totalFarine;
    }

    public void setTotalFarine(String totalFarine) {
        this.totalFarine = totalFarine;
    }

    public String getTotalSon() {
        return totalSon;
    }

    public void setTotalSon(String totalSon) {
        this.totalSon = totalSon;
    }
    
}
