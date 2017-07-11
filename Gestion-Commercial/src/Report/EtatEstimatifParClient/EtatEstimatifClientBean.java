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
    String totalQte;

    List<String> dates;
    List<String> nums;
    List<String> produits;
    List<String> montants;
    List<String> tvas;
    List<String> ttcs;
    List<String> timbres;
    List<String> qtes;
    
    String titreRC;
    String titreFiscal;
    String titreArticle;
    String titreTel;
    String titreFax;
    String titreEmail;
    String entrepriseNom;
    String entrepriseAddress;

    public EtatEstimatifClientBean(String nomPrenom, String activity, String address
            , String rc, String fiscal, String article, String dateDebut
            , String dateFin, String totalMontant, String totalTva, String totalTtc
            , String totalTimbre, String totalFarine, String totalSon, String totalQte
            , List<String> dates, List<String> nums, List<String> produits, List<String> montants
            , List<String> tvas, List<String> ttcs, List<String> timbres, List<String> qtes,String titreRC, String titreFiscal, String titreArticle,
            String titreTel, String titreFax, String titreEmail,String entrepriseNom,String entrepriseAddress) {
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
        this.totalTimbre = totalTimbre;
        this.totalFarine = totalFarine;
        this.totalSon = totalSon;
        this.totalQte = totalQte;
        this.dates = dates;
        this.nums = nums;
        this.produits = produits;
        this.montants = montants;
        this.tvas = tvas;
        this.ttcs = ttcs;
        this.timbres = timbres;
        this.qtes = qtes;
        
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

    public String getTotalQte() {
        return totalQte;
    }

    public void setTotalQte(String totalQte) {
        this.totalQte = totalQte;
    }

    public List<String> getDates() {
        return dates;
    }

    public void setDates(List<String> dates) {
        this.dates = dates;
    }

    public List<String> getNums() {
        return nums;
    }

    public void setNums(List<String> nums) {
        this.nums = nums;
    }

    public List<String> getProduits() {
        return produits;
    }

    public void setProduits(List<String> produits) {
        this.produits = produits;
    }

    public List<String> getMontants() {
        return montants;
    }

    public void setMontants(List<String> montants) {
        this.montants = montants;
    }

    public List<String> getTvas() {
        return tvas;
    }

    public void setTvas(List<String> tvas) {
        this.tvas = tvas;
    }

    public List<String> getTtcs() {
        return ttcs;
    }

    public void setTtcs(List<String> ttcs) {
        this.ttcs = ttcs;
    }

    public List<String> getTimbres() {
        return timbres;
    }

    public void setTimbres(List<String> timbres) {
        this.timbres = timbres;
    }

    public List<String> getQtes() {
        return qtes;
    }

    public void setQtes(List<String> qtes) {
        this.qtes = qtes;
    }
    

}
