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
    @Column(name = "matricule", nullable = false)
    int matricule;
    @Column(name = "type", nullable = false)
    String type;
    @Column(name = "marque", nullable = false)
    String marque;

    public Camion() {
    }

    public Camion(int matricule, String type, String marque) {
        this.matricule = matricule;
        this.type = type;
        this.marque = marque;
    }

    public int getMatricule() {
        return matricule;
    }

    public void setMatricule(int matricule) {
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

}
