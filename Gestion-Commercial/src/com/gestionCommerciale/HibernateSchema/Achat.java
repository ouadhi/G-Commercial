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
    @Column(name = "num_Tiquet", nullable = false)
    String numTiquet;
    @Column(name = "date_acqt", nullable = false)
    @Temporal(value = TemporalType.DATE)
    Date dateAcqt;
    @Column(name = "quantite_acqt", nullable = false)
    double quantiteAcqt;
    @Column(name = "quantite_four", nullable = false)
    double quantiteFour;
    @Column(name = "quantite_diff", nullable = false)
    double quantiteDiff;
    @Column(name = "num_Bon", nullable = false)
    String numBon;
    @ManyToOne
    @JoinColumn(name = "id_chauffeur")
    private Chauffeur chauffeur;
    @ManyToOne
    @JoinColumn(name = "id_camion")
    private Camion camion;
    @ManyToOne
    @JoinColumn(name = "id_ble")
    private Ble ble;
    @ManyToOne
    @JoinColumn(name = "id_dock")
    private Dock dock;
    @Column(name = "deleted", nullable = false)
    boolean deleted;
    @ManyToOne
    @JoinColumn(name="id_annee")
    private Annee annee;

    //
    public Achat() {

    }

    public Achat(String numTiquet,String numAcqt, double quantiteAcqt, double quantiteFour,
             double quantiteDiff, Date dateAcqt, String numBon,Annee annee) {
        this.numAcqt = numAcqt;
        this.numTiquet = numTiquet;
        this.quantiteAcqt = quantiteAcqt;
        this.quantiteFour = quantiteFour;
        this.quantiteDiff = quantiteDiff;
        this.numBon = numBon;
        this.dateAcqt = dateAcqt;
        this.annee = annee;
    }

    public String getNumTiquet() {
        return numTiquet;
    }

    public void setNumTiquet(String numTiquet) {
        this.numTiquet = numTiquet;
    }
    

    public int getIdAchat() {
        return idAchat;
    }

    public String getNumBon() {
        return numBon;
    }

    public void setNumBon(String numBon) {
        this.numBon = numBon;
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

    public double getQuantiteAcqt() {
        return quantiteAcqt;
    }

    public void setQuantiteAcqt(double quantiteAcqt) {
        this.quantiteAcqt = quantiteAcqt;
    }

    public double getQuantiteFour() {
        return quantiteFour;
    }

    public void setQuantiteFour(int quantiteFour) {
        this.quantiteFour = quantiteFour;
    }

    public double getQuantiteDiff() {
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Annee getAnnee() {
        return annee;
    }

    public void setAnnee(Annee annee) {
        this.annee = annee;
    }
    
}
