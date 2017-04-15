package com.gestionCommerciale.HibernateSchema;

import java.util.ArrayList;
import java.util.List;
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
    @Column(name = "id_dock", nullable = false)
    int idDock;
    @Column(name = "nom", nullable = false)
    String nom;
    @Column(name = "wilaya", nullable = false)
    String wilaya;
    @Column(name = "distance", nullable = false)
    float distance;
    @Column(name = "prixUnitTrans", nullable = false)
    float prixUnitTrans;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,targetEntity = Chauffeur.class)
    @JoinTable(name = "Chauffeur_dock", joinColumns = {
    @JoinColumn(name = "id_dock", nullable = false, updatable = false) }
    ,inverseJoinColumns = { @JoinColumn(name = "IdChauffeur", nullable = false, updatable = false) })
    List<Chauffeur> chauffeurs= new ArrayList<Chauffeur>();
    public Dock() {
    }
    
    public Dock(String nom, String wilaya, float distance, float prixUnitTrans) {
        this.nom = nom;
        this.wilaya = wilaya;
        this.distance = distance;
        this.prixUnitTrans = prixUnitTrans;
    }

    public int getIdDock() {
        return idDock;
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

    public void setIdDock(int idDock) {
        this.idDock = idDock;
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

    public List<Chauffeur> getChauffeurs() {
        return chauffeurs;
    }

    public void setChauffeurs(List<Chauffeur> chauffeurs) {
        this.chauffeurs = chauffeurs;
    }


}