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
import javax.persistence.OneToMany;
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
    double prixUnitTrans;
    @OneToMany(targetEntity=Achat.class, mappedBy="dock"
    		,cascade=CascadeType.ALL,fetch= FetchType.EAGER)
    private List<Achat> achats;
    //
    /*@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,targetEntity = Chauffeur.class)
    @JoinTable(name = "Chauffeur_dock", joinColumns = {
    @JoinColumn(name = "id_dock", nullable = false, updatable = false) }
    ,inverseJoinColumns = { @JoinColumn(name = "IdChauffeur", nullable = false, updatable = false) })
    List<Chauffeur> chauffeurs= new ArrayList<Chauffeur>();*/

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

    public double getPrixUnitTrans() {
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

    public void setPrixUnitTrans(double prixUnitTrans) {
        this.prixUnitTrans = prixUnitTrans;
    }

    public List<Achat> getAchats() {
        return achats;
    }

    public void setAchats(List<Achat> achats) {
        this.achats = achats;
    }



}
