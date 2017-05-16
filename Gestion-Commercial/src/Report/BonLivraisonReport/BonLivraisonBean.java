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
    public List<String>designations;
    public List<String>qtes;

    public BonLivraisonBean(int id, String nom, String code, String address
            , String rc, String fiscal, String date, String numFacture
            , String article, String chauffeur
            , String matricule, List<String> designations, List<String> qtes) {
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
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNumFacture() {
        return numFacture;
    }

    public void setNumFacture(String numFacture) {
        this.numFacture = numFacture;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getChauffeur() {
        return chauffeur;
    }

    public void setChauffeur(String chauffeur) {
        this.chauffeur = chauffeur;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public List<String> getDesignations() {
        return designations;
    }

    public void setDesignations(List<String> designations) {
        this.designations = designations;
    }

    public List<String> getQtes() {
        return qtes;
    }

    public void setQtes(List<String> qtes) {
        this.qtes = qtes;
    }
        
}
