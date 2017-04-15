/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionCommerciale.HibernateSchema;

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.
        Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Hicham
 */
@Entity
@Table(name = "Client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", nullable = false)
    int id;
    @Column(name = "codeClient", nullable = false)
    String codeClient;
    @Column(name = "nom", nullable = false)
    String name;
    @Column(name = "Prenom", nullable = false)
    String prenom;
    @Column(name = "numRegCom", nullable = false)
    String numRegCom;
    @Column(name = "numArticle", nullable = false)
    String numArticle;
    @Column(name = "addressClient", nullable = false)
    String addressClient;
    @Column(name = "typeActivity", nullable = false)
    String typeActivity;
    @Column(name = "dateDepotDossier", nullable = false)
    @Temporal(value=TemporalType.DATE)
    Date dateDepotDossier;
    
    //@OneToMany(targetEntity=.class, mappedBy="client"
    //		,cascade=CascadeType.ALL,fetch= FetchType.EAGER)
    //private List<> ;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,targetEntity = Chauffeur.class)
    @JoinTable(name = "Chauffeur_client", joinColumns = {
    @JoinColumn(name = "Id", nullable = false, updatable = false) }
    ,inverseJoinColumns = { @JoinColumn(name = "IdChauffeur", nullable = false, updatable = false) })
    List<Chauffeur> chauffeurs= new ArrayList<Chauffeur>();
    
    public Client( String nom, String prenom, String codeClient,String numRegCom, String numArticle
                   , String addressClient, String typeActivity, Date dateDepotDossier) {
        this.name = nom;
        this.prenom = prenom;
        this.codeClient= codeClient;
        this.numRegCom=numRegCom;
        this.numArticle=numArticle;
        this.addressClient= addressClient;
        this.typeActivity= typeActivity;
        this.dateDepotDossier= dateDepotDossier;
    }

    public Client() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCodeClient() {
        return codeClient;
    }

    public void setCodeClient(String codeClient) {
        this.codeClient = codeClient;
    }

    public String getNumRegCom() {
        return numRegCom;
    }

    public void setNumRegCom(String numRegCom) {
        this.numRegCom = numRegCom;
    }

    public String getNumArticle() {
        return numArticle;
    }

    public void setNumArticle(String numArticle) {
        this.numArticle = numArticle;
    }

    public String getAddressClient() {
        return addressClient;
    }

    public void setAddressClient(String addressClient) {
        this.addressClient = addressClient;
    }

    public String getTypeActivity() {
        return typeActivity;
    }

    public void setTypeActivity(String typeActivity) {
        this.typeActivity = typeActivity;
    }

    public Date getDateDepotDossier() {
        return dateDepotDossier;
    }

    public void setDateDepotDossier(Date dateDepotDossier) {
        this.dateDepotDossier = dateDepotDossier;
    }

    public List<Chauffeur> getChauffeurs() {
        return chauffeurs;
    }

    public void setChauffeurs(List<Chauffeur> chauffeurs) {
        this.chauffeurs = chauffeurs;
    }
    
}
 
