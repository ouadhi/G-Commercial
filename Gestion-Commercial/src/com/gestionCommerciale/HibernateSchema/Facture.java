package com.gestionCommerciale.HibernateSchema;

import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author CHERABRAB
 */
@Entity
@Table(name = "Facture")
public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_facture", nullable = false)
    int idFacture;
    @Column(name = "date", nullable = false)
    @Temporal(value = TemporalType.DATE)
    Date date;
    @Column(name = "montant", nullable = false)
    double montant;
    @Column(name = "tva", nullable = false)
    double tva;
    @Column(name = "timbre", nullable = false)
    double timbre;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "produit_facture",
            joinColumns = {
                @JoinColumn(name = "idFacture")},
            inverseJoinColumns = {
                @JoinColumn(name = "idProduit")})
    public Set<Produit> produits = new HashSet<Produit>();

    public Facture() {
    }

    public Facture(Date date, double montant, double tva, double timbre) {
        this.date = date;
        this.montant = montant;
        this.tva = tva;
        this.timbre = timbre;
    }

    public int getIdFacture() {
        return idFacture;
    }

    public Date getDate() {
        return date;
    }

    public double getMontant() {
        return montant;
    }

    public double getTva() {
        return tva;
    }

    public double getTimbre() {
        return timbre;
    }

    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public void setTva(double tva) {
        this.tva = tva;
    }

    public void setTimbre(double timbre) {
        this.timbre = timbre;
    }

    public Set<Produit> getProduits() {
        return produits;
    }

    public void setProduits(Set<Produit> produits) {
        this.produits = produits;
    }

}
