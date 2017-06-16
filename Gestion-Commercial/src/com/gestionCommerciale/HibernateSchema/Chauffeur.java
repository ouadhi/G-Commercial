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
	@OneToMany(targetEntity = Facture.class, mappedBy = "chauffeur", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Facture> factures;
	@OneToMany(targetEntity = Achat.class, mappedBy = "chauffeur", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Achat> achats;
	@Column(name = "deleted", nullable = false)
	boolean deleted;

	public Chauffeur() {
	}

	public Chauffeur(String nom, String prenom, String telephone, String type) {
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.type = type;
		this.deleted = false;
	}

	public List<Achat> getAchats() {
		return achats;
	}

	public List<Facture> getFactures() {
		return factures;
	}

	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getTelephone() {
		return telephone;
	}

	public String getType() {
		return type;
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

	public void setFactures(List<Facture> factures) {
		this.factures = factures;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNom(String nomChauffeur) {
		this.nom = nomChauffeur;
	}

	public void setPrenom(String prenomChauffeur) {
		this.prenom = prenomChauffeur;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setType(String type) {
		this.type = type;
	}
}
