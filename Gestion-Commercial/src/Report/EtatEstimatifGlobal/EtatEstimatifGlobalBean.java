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

    public EtatEstimatifGlobalBean(String nomPrenom, String activity, String address
            , String rc, String fiscal, String article, String dateDebut, String dateFin
            , String totalMontant, String totalTva, String totalTtc) {
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
    }

    public String getNomPrenom() {
        return nomPrenom;
    }

    public void setNomPrenom(String nomPrenom) {
        this.nomPrenom = nomPrenom;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRc() {
        return rc;
    }

    public void setRc(String rc) {
        this.rc = rc;
    }

    public String getFiscal() {
        return fiscal;
    }

    public void setFiscal(String fiscal) {
        this.fiscal = fiscal;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
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

    public String getTotalMontant() {
        return totalMontant;
    }

    public void setTotalMontant(String totalMontant) {
        this.totalMontant = totalMontant;
    }

    public String getTotalTva() {
        return totalTva;
    }

    public void setTotalTva(String totalTva) {
        this.totalTva = totalTva;
    }

    public String getTotalTtc() {
        return totalTtc;
    }

    public void setTotalTtc(String totalTtc) {
        this.totalTtc = totalTtc;
    }
    
    
}