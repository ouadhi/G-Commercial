package com.gestionCommerciale.HibernateSchema;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author CHERABRAB
 */
@Entity
@Table(name = "Payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_payment", nullable = false)
    int idPayment;
    @Column(name = "type", nullable = false)
    String type;
    @Column(name = "montant", nullable = false)
    double montant;
    @Column(name = "date", nullable = false)
    @Temporal(value = TemporalType.DATE)
    Date date;

    public Payment() {
    }

    public Payment(String type, double montant, Date date) {
        this.type = type;
        this.montant = montant;
        this.date = date;
    }

    public int getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(int idPayment) {
        this.idPayment = idPayment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
