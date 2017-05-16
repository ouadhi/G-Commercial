package com.gestionCommerciale.HibernateSchema;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 *
 * @author CHERABRAB
 */
@Entity
@Table(name = "Achat")
public class Achat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_achat", nullable = false)
    int idAchat;
    @Column(name = "num_acqt", nullable = false)
    String numAcqt;
    @Column(name = "date_acqt", nullable = false)
    @Temporal(value = TemporalType.DATE)            
    Date dateAcqt;
    @Column(name = "quantite_acqt", nullable = false)
    int quantiteAcqt;
    @Column(name = "quantite_four", nullable = false)
    int quantiteFour;
    @Column(name = "quantite_diff", nullable = false)
    int quantiteDiff;
    @ManyToOne
    @JoinColumn(name="id_chauffeur")
    private Chauffeur chauffeur;
    @ManyToOne
    @JoinColumn(name="id_camion")
    private Camion camion;   
    @ManyToOne
    @JoinColumn(name="id_ble")
    private Ble ble;
    @ManyToOne
    @JoinColumn(name="id_dock")
    private Dock dock;
    
    //
    

    public Achat() {

    }

    public Achat(String numAcqt, int quantiteAcqt, int quantiteFour, int quantiteDiff) {
        this.numAcqt = numAcqt;
        this.quantiteAcqt = quantiteAcqt;
        this.quantiteFour = quantiteFour;
        this.quantiteDiff = quantiteDiff;
    }

    public int getIdAchat() {
        return idAchat;
    }

    public void setIdAchat(int idExpedition) {
        this.idAchat = idExpedition;
    }

    public String getNumAcqt() {
        return numAcqt;
    }

    public void setNumAcqt(String numAcqt) {
        this.numAcqt = numAcqt;
    }

    public Date getDateAcqt() {
        return dateAcqt;
    }

    public void setDateAcqt(Date dateAcqt) {
        this.dateAcqt = dateAcqt;
    }

    public int getQuantiteAcqt() {
        return quantiteAcqt;
    }

    public void setQuantiteAcqt(int quantiteAcqt) {
        this.quantiteAcqt = quantiteAcqt;
    }

    public int getQuantiteFour() {
        return quantiteFour;
    }

    public void setQuantiteFour(int quantiteFour) {
        this.quantiteFour = quantiteFour;
    }

    public int getQuantiteDiff() {
        return quantiteDiff;
    }

    public void setQuantiteDiff(int quantiteDiff) {
        this.quantiteDiff = quantiteDiff;
    }

    public Chauffeur getChauffeur() {
        return chauffeur;
    }

    public void setChauffeur(Chauffeur chauffeur) {
        this.chauffeur = chauffeur;
    }

    public Ble getBle() {
        return ble;
    }

    public void setBle(Ble ble) {
        this.ble = ble;
    }

    public Camion getCamion() {
        return camion;
    }

    public void setCamion(Camion camion) {
        this.camion = camion;
    }

    public Dock getDock() {
        return dock;
    }

    public void setDock(Dock dock) {
        this.dock = dock;
    }
  
}
