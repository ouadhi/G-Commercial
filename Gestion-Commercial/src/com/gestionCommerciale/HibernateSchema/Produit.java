package com.gestionCommerciale.HibernateSchema;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author CHERABRAB
 */
@Entity
@Table(name = "produit")
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    int id;
    @Column(name = "nom", nullable = false)
    String nom;
    @Column(name = "quantite", nullable = false)
    int quantite;
    @Column(name = "prix", nullable = false)
    float prix;

    public Produit() {

    }

    public Produit(String nom, int quantite, float prix) {
        this.nom = nom;
        this.quantite = quantite;
        this.prix = prix;
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

}
