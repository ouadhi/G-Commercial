/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionCommerciale.HibernateSchema;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Hicham
 */
@Entity
@Table(name = "Inovices")
public class Inovice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", nullable = false)
    int id;
    @Column(name = "date", nullable = false)
    Date date;
    @Column(name = "amount", nullable = false)
    double amount;
    @Column(name = "tva", nullable = false)
    double tva;
    @Column(name = "timbre", nullable = false)
    double timbre;

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
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

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setTva(double tva) {
        this.tva = tva;
    }

    public void setTimbre(double timbre) {
        this.timbre = timbre;
    }

    public Inovice(Date date, double amount, double tva, double timbre) {
        this.date = date;
        this.amount = amount;
        this.tva = tva;
        this.timbre = timbre;
    }

    public Inovice() {
    }




}
