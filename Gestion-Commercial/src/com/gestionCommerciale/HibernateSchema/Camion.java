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
@Table(name = "Camion")
public class Camion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_camion", nullable = false)
    int id;
    @Column(name = "codeCamion", nullable = false, unique = true)
    String codeCamion;
    @Column(name = "matricule", nullable = false, unique = true)
    String matricule;
    @Column(name = "marque", nullable = false)
    String marque;
//    @Column(name = "poid", nullable = true)
//    double poid;
    @OneToMany(targetEntity=Facture.class, mappedBy="camion"
    		,cascade=CascadeType.ALL,fetch= FetchType.EAGER)
    private List<Facture> factures;
    @OneToMany(targetEntity=Achat.class, mappedBy="camion"
    		,cascade=CascadeType.ALL,fetch= FetchType.EAGER)
    private List<Achat> achats;
    @Column(name = "deleted", nullable = false)
    boolean deleted;

    public Camion() {
    }

    public Camion(String codeCamion,String matricule, String marque) {
        this.codeCamion = codeCamion;
        this.matricule= matricule;
        this.marque = marque;
        //this.poid= poid;
        this.deleted=false;
    }

//    public double getPoid() {
//        return poid;
//    }
//
//    public void setPoid(double poid) {
//        this.poid = poid;
//    }
//
    public List<Achat> getAchats() {
        return achats;
    }

    public void setAchats(List<Achat> achats) {
        this.achats = achats;
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

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Facture> getFactures() {
        return factures;
    }

    public void setFactures(List<Facture> factures) {
        this.factures = factures;
    }
    
        public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

   

}
