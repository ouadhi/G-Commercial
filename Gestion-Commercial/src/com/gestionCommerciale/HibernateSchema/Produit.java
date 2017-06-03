package com.gestionCommerciale.HibernateSchema;

import com.gestionCommerciale.Models.AnneeQueries;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author CHERABRAB
 */
//class relation avec attribut
@Entity
@Table(name = "Produit")
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_produit", nullable = false)
    int idProduit;
    @Column(name = "nom", nullable = false)
    String nom;
    @Column(name = "code_produit", nullable = false, unique = true)
    String codeProduit;
    @Column(name = "Category", nullable = false)
    String category;
    @Column(name = "quantite", nullable = false)
    int quantite;
    @Column(name = "have_tva", nullable = false)
    boolean haveTva;
    @Column(name = "prix", nullable = false)
    double prix;
    @Column(name = "deleted", nullable = false)
    boolean deleted;

    @OneToMany(targetEntity = Facture_Produit.class, mappedBy = "produit",
            cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Facture_Produit> qtes;

    public Produit() {

    }

    public Produit(String codeProduit, String nom, String category, int quantite, double prix, boolean haveTva) {
        this.codeProduit = codeProduit;
        this.nom = nom;
        this.quantite = quantite;
        this.prix = prix;
        this.category = category;
        this.haveTva = haveTva;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public String getNom() {
        return nom;
    }

    public int getQuantite() {
        return quantite;
    }

    public double getPrix() {
        return prix;
    }

    public double getTTC() {
        if (haveTva) {
            return prix+prix*(AnneeQueries.getSelected().getTva()/100);
        } else {
            return  prix;
        }
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public List<Facture_Produit> getQtes() {
        return qtes;
    }

    public void setQtes(List<Facture_Produit> qtes) {
        this.qtes = qtes;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isHaveTva() {
        return haveTva;
    }

    public void setHaveTva(boolean haveTva) {
        this.haveTva = haveTva;
    }

    public String getCodeProduit() {
        return codeProduit;
    }

    public void setCodeProduit(String codeProduit) {
        this.codeProduit = codeProduit;
    }

}
