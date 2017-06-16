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

import com.gestionCommerciale.Models.AnneeQueries;

/**
 *
 * @author CHERABRAB
 */
// class relation avec attribut
@Entity
@Table(name = "Produit")
public class Produit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_produit", nullable = false)
	int idProduit;
	@Column(name = "nom", nullable = false)
	String nom;
	@Column(name = "code_produit", nullable = false, unique = true)
	String codeProduit;
	@Column(name = "Category", nullable = false)
	String category;
	@Column(name = "quantite", nullable = false)
	int quantite;
	@Column(name = "have_tva", nullable = false)
	boolean haveTva;
	@Column(name = "prix", nullable = false)
	double prix;
	@Column(name = "deleted", nullable = false)
	boolean deleted;

	@OneToMany(targetEntity = Facture_Produit.class, mappedBy = "produit", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Facture_Produit> qtes;

	public Produit() {

	}

	public Produit(String codeProduit, String nom, String category, int quantite, double prix, boolean haveTva) {
		this.codeProduit = codeProduit;
		this.nom = nom;
		this.quantite = quantite;
		this.prix = prix;
		this.category = category;
		this.haveTva = haveTva;
	}

	public String getCategory() {
		return category;
	}

	public String getCodeProduit() {
		return codeProduit;
	}

	public int getIdProduit() {
		return idProduit;
	}

	public String getNom() {
		return nom;
	}

	public double getPrix() {
		return prix;
	}

	public List<Facture_Produit> getQtes() {
		return qtes;
	}

	public int getQuantite() {
		return quantite;
	}

	public double getTTC() {
		if (haveTva) {
			return prix + prix * (AnneeQueries.getSelected().getTva() / 100);
		} else {
			return prix;
		}
	}

	public boolean isDeleted() {
		return deleted;
	}

	public boolean isHaveTva() {
		return haveTva;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setCodeProduit(String codeProduit) {
		this.codeProduit = codeProduit;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public void setHaveTva(boolean haveTva) {
		this.haveTva = haveTva;
	}

	public void setIdProduit(int idProduit) {
		this.idProduit = idProduit;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public void setQtes(List<Facture_Produit> qtes) {
		this.qtes = qtes;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

}
