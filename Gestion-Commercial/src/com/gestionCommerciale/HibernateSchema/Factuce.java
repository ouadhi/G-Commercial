package com.gestionCommerciale.HibernateSchema;

import java.sql.Date;
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
@Table(name = "factures")
public class Factuce {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    int id;
    @Column(name = "date", nullable = false)
    Date date;
    @Column(name = "montant", nullable = false)
    double montant;
    @Column(name = "tva", nullable = false)
    double tva;
    @Column(name = "timbre", nullable = false)
    double timbre;
    
    public Factuce() {
    }
    
    public Factuce(Date date, double montant, double tva, double timbre) {
        this.date = date;
        this.montant = montant;
        this.tva = tva;
        this.timbre = timbre;
    }

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
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


}
