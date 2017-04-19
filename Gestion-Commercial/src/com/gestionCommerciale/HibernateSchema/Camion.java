package com.gestionCommerciale.HibernateSchema;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author CHERABRAB
 */
@Entity
@Table(name = "Camion")
public class Camion {

    @Id
    @Column(name = "codeCamion", nullable = false)
    String codeCamion;
    @Column(name = "matricule", nullable = false)
    String matricule;
    @Column(name = "type", nullable = false)
    String type;
    @Column(name = "marque", nullable = true)
    String marque;
    @ManyToOne
    @JoinColumn(name="id_chauffeur")
    private Chauffeur chauffeur;

    public Camion() {
    }

    public Camion(String codeCamion,String matricule, String type) {
        this.codeCamion = codeCamion;
        this.matricule= matricule;
        this.type = type;
        //this.marque = marque;
    }

    public String getCodeCamion() {
        return codeCamion;
    }

    public void setCodeCamion(String codeCamion) {
        this.codeCamion = codeCamion;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public Chauffeur getChauffeur() {
        return chauffeur;
    }

    public void setChauffeur(Chauffeur chauffeur) {
        this.chauffeur = chauffeur;
    }
    
   

}
