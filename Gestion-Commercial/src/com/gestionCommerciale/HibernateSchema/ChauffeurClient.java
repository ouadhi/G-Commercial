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
@Table(name = "Chauffeur_client")
public class ChauffeurClient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    int id;
    @Column(name = "nom", nullable = false)
    String nom;
    @Column(name = "prenom", nullable = false)
    String prenom;

    public ChauffeurClient() {
    }

    public ChauffeurClient(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

}
