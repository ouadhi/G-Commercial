/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionCommerciale.HibernateSchema;

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
 * @author Hicham
 */
@Entity
@Table(name = "Ble")
public class Ble {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_Ble", nullable = false)
    int idBle;
    @Column(name = "quantite", nullable = false)
    int qte;
    @Column(name = "prix", nullable = false)
    double prix;
    @OneToMany(targetEntity=Achat.class, mappedBy="ble"
    		,cascade=CascadeType.ALL,fetch= FetchType.EAGER)
    private List<Achat>  achats;

    public Ble() {
    }

    public Ble(int idBle, int qte, double prix) {
        this.idBle = idBle;
        this.qte = qte;
        this.prix = prix;
    }

    public int getIdBle() {
        return idBle;
    }

    public void setIdBle(int idBle) {
        this.idBle = idBle;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public List<Achat> getAchats() {
        return achats;
    }

    public void setAchats(List<Achat> achats) {
        this.achats = achats;
    }

   
    
    
}
