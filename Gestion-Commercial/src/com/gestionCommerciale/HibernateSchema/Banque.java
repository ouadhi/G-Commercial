package com.gestionCommerciale.HibernateSchema;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Banque")
public class Banque {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_dock", nullable = false)
    int idDock;
    @Column(name = "nom", nullable = false)
    String nom;
    @Column(name = "compte", nullable = false)
    String compte;
    @Column(name = "address", nullable = false)
    String address;
    @Column(name = "deleted", nullable = false)
    boolean deleted;
    @Column(name = "telephone", nullable = false)
    String telephone;

    public Banque() {
        
    }

    public Banque(String nom, String compte, String address , String telephone) {
        this.nom = nom;
        this.compte = compte;
        this.address = address;
        this.telephone = telephone ; 
    }

    public int getIdDock() {
        return idDock;
    }

    public void setIdDock(int idDock) {
        this.idDock = idDock;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCompte() {
        return compte;
    }

    public void setCompte(String compte) {
        this.compte = compte;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
       public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }


    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    
   
}
