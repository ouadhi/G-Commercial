/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionCommerciale.HibernateSchema;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Hicham
 */
@Entity
@Table(name = "Facture_Produit")
public class Facture_Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_facture_produit", nullable = false)
    int id_facture_produit;
    @Column(name = "qte_fact", nullable = false)
    int qte_fact;
    @Column(name = "prix_fact", nullable = false)
    double prix_fact;
    @ManyToOne
    @JoinColumn(name = "id_fact")
    private Facture facture;

    @ManyToOne
    @JoinColumn(name = "id_produit")
    private Produit produit;
    @Column(name = "deleted", nullable = false)
    boolean deleted;

    public Facture_Produit(int qte_fact, double tva_fact, double prix_fact) {
        this.qte_fact = qte_fact;
        this.prix_fact = prix_fact;
    }

    public Facture_Produit() {

    }

    public int getId_facture_produit() {
        return id_facture_produit;
    }

    public void setId_facture_produit(int id_facture_produit) {
        this.id_facture_produit = id_facture_produit;
    }

    public int getQte_fact() {
        return qte_fact;
    }

    public void setQte_fact(int qte_fact) {
        this.qte_fact = qte_fact;
    }

    public Facture getFacture() {
        return facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public double getPrix_fact() {
        return prix_fact;
    }

    public void setPrix_fact(double prix_fact) {
        this.prix_fact = prix_fact;
    }

}
