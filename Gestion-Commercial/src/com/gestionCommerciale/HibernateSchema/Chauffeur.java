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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IdChauffeur", nullable = false)
    private int id;
    @Column(name = "nomChauffeur", nullable = false)
    private String nom;
    @Column(name = "prenomChauffeur", nullable = false)
    private String prenom;
    @Column(name = "telephone", nullable = false)
    private String telephone;
    @Column(name = "type", nullable = false)
    private String type;
    @OneToMany(targetEntity = Facture.class, mappedBy = "chauffeur",
            cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Facture> factures;
    @OneToMany(targetEntity = Achat.class, mappedBy = "chauffeur",
            cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Achat> achats;
    @Column(name = "deleted", nullable = false)
    boolean deleted;

    public Chauffeur(String nom, String prenom, String telephone, String type) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.type = type;
    }

    public Chauffeur() {
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

    public void setNom(String nomChauffeur) {
        this.nom = nomChauffeur;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenomChauffeur) {
        this.prenom = prenomChauffeur;
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

    public List<Facture> getFactures() {
        return factures;
    }

    public void setFactures(List<Facture> factures) {
        this.factures = factures;
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
