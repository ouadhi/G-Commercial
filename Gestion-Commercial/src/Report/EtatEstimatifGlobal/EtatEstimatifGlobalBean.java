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
    
    String titreRC;
    String titreFiscal;
    String titreArticle;
    String titreTel;
    String titreFax;
    String titreEmail;
    String entrepriseNom;
    String entrepriseAddress;

    public EtatEstimatifGlobalBean(String nomPrenom, String activity, String address, String rc, String fiscal,
            String article, String dateDebut, String dateFin, String totalMontant, String totalTva, String totalTtc,
            String totalTimbre,String totalFarine,String totalSon, String titreRC, String titreFiscal, String titreArticle,
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
        this.totalTimbre= totalTimbre;
        this.totalFarine= totalFarine;
        this.totalSon= totalSon;
        
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
