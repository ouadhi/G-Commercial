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

/**
 *
 * @author CHERABRAB
 */
@Entity
@Table(name = "Dock")
public class Dock {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_dock", nullable = false)
	int idDock;
	@Column(name = "nom", nullable = false)
	String nom;
	@Column(name = "wilaya", nullable = false)
	String wilaya;
	@Column(name = "distance", nullable = false)
	double distance;
	@Column(name = "prixUnitTrans", nullable = false)
	double prixUnitTrans;
	@OneToMany(targetEntity = Achat.class, mappedBy = "dock", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Achat> achats;
	@Column(name = "deleted", nullable = false)
	boolean deleted;

	public Dock() {
	}

	public Dock(String nom, String wilaya, double distance, double prixUnitTrans) {
		this.nom = nom;
		this.wilaya = wilaya;
		this.distance = distance;
		this.prixUnitTrans = prixUnitTrans;
		deleted = false;
	}

	public List<Achat> getAchats() {
		return achats;
	}

	public double getDistance() {
		return distance;
	}

	public int getIdDock() {
		return idDock;
	}

	public String getNom() {
		return nom;
	}

	public double getPrixUnitTrans() {
		return prixUnitTrans;
	}

	public String getWilaya() {
		return wilaya;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setAchats(List<Achat> achats) {
		this.achats = achats;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public void setIdDock(int idDock) {
		this.idDock = idDock;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrixUnitTrans(double prixUnitTrans) {
		this.prixUnitTrans = prixUnitTrans;
	}

	public void setWilaya(String wilaya) {
		this.wilaya = wilaya;
	}
}
