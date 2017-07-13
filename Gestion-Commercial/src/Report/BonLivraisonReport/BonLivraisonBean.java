/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report.BonLivraisonReport;

import java.util.List;

/**
 *
 * @author Hicham
 */
public class BonLivraisonBean {

    int id;
    String nom;
    String code;
    String address;
    String rc;
    String fiscal;
    String date;
    String numFacture;
    String article;
    String chauffeur;
    String matricule;
    public List<String> designations;
    public List<String> qtes;
    public List<String> types;

    String titreRC;
    String titreFiscal;
    String titreArticle;
    String titreTel;
    String titreFax;
    String titreEmail;
    String entrepriseNom;
    String entrepriseAddress;

    public BonLivraisonBean(int id, String nom, String code, String address, String rc, String fiscal, String date,
            String numFacture, String article, String chauffeur, String matricule, List<String> designations,
            List<String> qtes, List<String> types, String titreRC, String titreFiscal, String titreArticle,
            String titreTel, String titreFax, String titreEmail,String entrepriseNom,String entrepriseAddress) {
        this.id = id;
        this.nom = nom;
        this.code = code;
        this.address = address;
        this.rc = rc;
        this.fiscal = fiscal;
        this.date = date;
        this.numFacture = numFacture;
        this.article = article;
        this.chauffeur = chauffeur;
        this.matricule = matricule;
        this.designations = designations;
        this.qtes = qtes;
        this.types = types;
        this.titreRC = titreRC;
        this.titreFiscal = titreFiscal;
        this.titreArticle = titreArticle;
        this.titreTel = titreTel;
        this.titreFax = titreFax;
        this.titreEmail = titreEmail;
        this.entrepriseNom=entrepriseNom;
        this.entrepriseAddress=entrepriseAddress;
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
    

    public String getAddress() {
        return address;
    }

    public String getArticle() {
        return article;
    }

    public String getChauffeur() {
        return chauffeur;
    }

    public String getCode() {
        return code;
    }

    public String getDate() {
        return date;
    }

    public List<String> getDesignations() {
        return designations;
    }

    public String getFiscal() {
        return fiscal;
    }

    public int getId() {
        return id;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getNom() {
        return nom;
    }

    public String getNumFacture() {
        return numFacture;
    }

    public List<String> getQtes() {
        return qtes;
    }

    public String getRc() {
        return rc;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public void setChauffeur(String chauffeur) {
        this.chauffeur = chauffeur;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDesignations(List<String> designations) {
        this.designations = designations;
    }

    public void setFiscal(String fiscal) {
        this.fiscal = fiscal;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNumFacture(String numFacture) {
        this.numFacture = numFacture;
    }

    public void setQtes(List<String> qtes) {
        this.qtes = qtes;
    }

    public void setRc(String rc) {
        this.rc = rc;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

}
