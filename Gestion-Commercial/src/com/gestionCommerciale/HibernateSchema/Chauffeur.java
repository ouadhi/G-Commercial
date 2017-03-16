/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionCommerciale.HibernateSchema;

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
@Table(name = "Chauffeur")
public class Chauffeur {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", nullable = false)
    int id;
    @Column(name = "nomChauffeur", nullable = false)
    String nomChauffeur;
    @Column(name = "prenomChauffeur", nullable = false)
    String prenomChauffeur;

    public Chauffeur(String nomChauffeur, String prenomChauffeur) {
        this.nomChauffeur = nomChauffeur;
        this.prenomChauffeur = prenomChauffeur;
    }

    public Chauffeur() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomChauffeur() {
        return nomChauffeur;
    }

    public void setNomChauffeur(String nomChauffeur) {
        this.nomChauffeur = nomChauffeur;
    }

    public String getPrenomChauffeur() {
        return prenomChauffeur;
    }

    public void setPrenomChauffeur(String prenomChauffeur) {
        this.prenomChauffeur = prenomChauffeur;
    }

}
