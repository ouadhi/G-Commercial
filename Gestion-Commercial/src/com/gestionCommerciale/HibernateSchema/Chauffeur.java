/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionCommerciale.HibernateSchema;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
    @Column(name = "IdChauffeur", nullable = false)
    int id;
    @Column(name = "nomChauffeur", nullable = false)
    String nomChauffeur;
    @Column(name = "prenomChauffeur", nullable = false)
    String prenomChauffeur;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "chauffeurs")
    List<Client> clients= new ArrayList<Client>();

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

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
    

}
