package com.gestionCommerciale.HibernateSchema;

import java.util.Date;
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
import javax.persistence.ManyToOne;
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
    @OneToMany(targetEntity=Payment.class, mappedBy="facture"
    		,cascade=CascadeType.ALL,fetch= FetchType.EAGER)
    private List<Payment> payments ;
    @OneToMany(targetEntity=Facture_Produit.class, mappedBy="facture"
    		,cascade=CascadeType.ALL,fetch= FetchType.EAGER)
    private List<Facture_Produit>  qtes;
    @ManyToOne
    @JoinColumn(name="id_chauffeur")
    private Chauffeur chauffeur;

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

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public Chauffeur getChauffeur() {
        return chauffeur;
    }

    public void setChauffeur(Chauffeur chauffeur) {
        this.chauffeur = chauffeur;
    }

    public List<Facture_Produit> getQtes() {
        return qtes;
    }

    public void setQtes(List<Facture_Produit> qtes) {
        this.qtes = qtes;
    }
    
}
