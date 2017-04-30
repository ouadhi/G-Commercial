package com.gestionCommerciale.HibernateSchema;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
    @Column(name = "Category", nullable = false)
    String category;
    @Column(name = "quantite", nullable = false)
    int quantite;
    @Column(name = "prix", nullable = false)
    float prix;
    
    @OneToMany(targetEntity=Facture_Produit.class, mappedBy="produit"
    		,cascade=CascadeType.ALL,fetch= FetchType.EAGER)
    private List<Facture_Produit> qtes;

    public Produit() {

    }

    public Produit(String nom,String category, int quantite, float prix) {
        this.nom = nom;
        this.quantite = quantite;
        this.prix = prix;
        this.category = category;
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

    public float getPrix() {
        return prix;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setPrix(float prix) {
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
    
   
}
