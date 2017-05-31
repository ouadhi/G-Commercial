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

@Entity
@Table(name = "Chauffeur")
public class Chauffeur {
    //back
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IdChauffeur", nullable = false)
    int id;
    @Column(name = "nomChauffeur", nullable = false)
    String nomChauffeur;
    @Column(name = "prenomChauffeur", nullable = false)
    String prenomChauffeur; 
    @Column(name = "telephone", nullable = false)
    String telephone; 
    @Column(name = "type", nullable = false)
    String type; 
    @OneToMany(targetEntity=Facture.class, mappedBy="chauffeur"
    		,cascade=CascadeType.ALL,fetch= FetchType.EAGER)
    private List<Facture> factures;
    @OneToMany(targetEntity=Achat.class, mappedBy="chauffeur"
      ,cascade=CascadeType.ALL,fetch= FetchType.EAGER)
    private List<Achat> achats;
         @Column(name = "deleted", nullable = false)
    boolean deleted;

    //
    /*@ManyToMany(fetch = FetchType.LAZY, mappedBy = "chauffeurs")
    List<Client> clients= new ArrayList<Client>();
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "chauffeurs")
    List<Dock> docks= new ArrayList<Dock>();
    @OneToMany(targetEntity=Camion.class, mappedBy="chauffeur"
    		,cascade=CascadeType.ALL,fetch= FetchType.EAGER)
    private List<Camion> camions;*/


    public Chauffeur(String nomChauffeur, String prenomChauffeur,String telephone, String type) {
        this.nomChauffeur = nomChauffeur;
        this.prenomChauffeur = prenomChauffeur;
        this.telephone= telephone;
        this.type= type;
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

    
    public List<Facture> getFactures() {
        return factures;
    }

    public void setFactures(List<Facture> factures) {
        this.factures = factures;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Achat> getAchats() {
        return achats;
    }

    public void setAchats(List<Achat> achats) {
        this.achats = achats;
    }
        public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    

}
