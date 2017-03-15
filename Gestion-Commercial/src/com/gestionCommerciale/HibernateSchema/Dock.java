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
@Table(name = "Dock")
public class Dock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", nullable = false)
    int id;
    @Column(name = "nom", nullable = false)
    String nom;
    @Column(name = "wilaya", nullable = false)
    String wilaya;
    @Column(name = "distance", nullable = false)
    float distance;
    @Column(name = "prixUnitTrans", nullable = false)
    float prixUnitTrans;
    public Dock() {
    }
    
    public Dock(String nom, String wilaya, float distance, float prixUnitTrans) {
        this.nom = nom;
        this.wilaya = wilaya;
        this.distance = distance;
        this.prixUnitTrans = prixUnitTrans;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getWilaya() {
        return wilaya;
    }

    public float getDistance() {
        return distance;
    }

    public float getPrixUnitTrans() {
        return prixUnitTrans;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setWilaya(String wilaya) {
        this.wilaya = wilaya;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public void setPrixUnitTrans(float prixUnitTrans) {
        this.prixUnitTrans = prixUnitTrans;
    }


}
