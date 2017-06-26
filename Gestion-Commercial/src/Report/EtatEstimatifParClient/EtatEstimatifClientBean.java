/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report.EtatEstimatifParClient;

import java.util.List;

/**
 * @author Hicham
 */
public class EtatEstimatifClientBean {

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

    List<String> dates;
    List<String> nums;
    List<String> produits;
    List<String> montants;
    List<String> tvas;
    List<String> ttcs;
    List<String> timbres;

    public EtatEstimatifClientBean(String nomPrenom, String activity, String address, String rc, String fiscal,
            String article, String dateDebut, String dateFin, String totalMontant, String totalTva, String totalTtc,
            String totalTimbre,String totalFarine,String totalSon,
            List<String> dates, List<String> nums, List<String> produits, List<String> montants, List<String> tvas,
            List<String> ttcs, List<String> timbres) {
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
        this.dates = dates;
        this.nums = nums;
        this.produits = produits;
        this.montants = montants;
        this.tvas = tvas;
        this.ttcs = ttcs;
        this.totalTimbre = totalTimbre;
        this.timbres = timbres;
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

    public List<String> getDates() {
        return dates;
    }

    public String getFiscal() {
        return fiscal;
    }

    public List<String> getMontants() {
        return montants;
    }

    public String getNomPrenom() {
        return nomPrenom;
    }

    public List<String> getNums() {
        return nums;
    }

    public List<String> getProduits() {
        return produits;
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

    public List<String> getTtcs() {
        return ttcs;
    }

    public List<String> getTvas() {
        return tvas;
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

    public void setDates(List<String> dates) {
        this.dates = dates;
    }

    public void setFiscal(String fiscal) {
        this.fiscal = fiscal;
    }

    public void setMontants(List<String> montants) {
        this.montants = montants;
    }

    public void setNomPrenom(String nomPrenom) {
        this.nomPrenom = nomPrenom;
    }

    public void setNums(List<String> nums) {
        this.nums = nums;
    }

    public void setProduits(List<String> produits) {
        this.produits = produits;
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

    public void setTtcs(List<String> ttcs) {
        this.ttcs = ttcs;
    }

    public void setTvas(List<String> tvas) {
        this.tvas = tvas;
    }

    public String getTotalTimbre() {
        return totalTimbre;
    }

    public void setTotalTimbre(String totalTimbre) {
        this.totalTimbre = totalTimbre;
    }

    public List<String> getTimbres() {
        return timbres;
    }

    public void setTimbres(List<String> timbres) {
        this.timbres = timbres;
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
