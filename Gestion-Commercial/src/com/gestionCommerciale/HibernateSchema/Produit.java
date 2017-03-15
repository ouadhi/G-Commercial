package com.gestionCommerciale.HibernateSchema;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author CHERABRAB
 */
@Entity
@Table(name = "Produit")
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_produit", nullable = false)
    int idProduit;
    @Column(name = "nom", nullable = false)
    String nom;
    @Column(name = "quantite", nullable = false)
    int quantite;
    @Column(name = "prix", nullable = false)
    float prix;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "produit_facture",
            joinColumns = {
                @JoinColumn(name = "idProduit")},
            inverseJoinColumns = {
                @JoinColumn(name = "idFacture")})
    public Set<Facture> factures = new HashSet<Facture>();

    public Produit() {

    }

    public Produit(String nom, int quantite, float prix) {
        this.nom = nom;
        this.quantite = quantite;
        this.prix = prix;
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

    public Set<Facture> getFactures() {
        return factures;
    }

    public void setFactures(Set<Facture> factures) {
        this.factures = factures;
    }

}
