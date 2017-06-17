package com.gestionCommerciale.HibernateSchema;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author CHERABRAB
 */
@Entity
@Table(name = "Facture")
public class Facture {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_facture", nullable = false)
	int idFacture;
	@Column(name = "date", nullable = false)
	@Temporal(value = TemporalType.DATE)
	Date date;
	@Column(name = "montant", nullable = false)
	double montant;
	@Column(name = "montant_final", nullable = false)
	double montantFinal;
	@Column(name = "tva", nullable = false)
	double tva;
	@Column(name = "timbre", nullable = false)
	double timbre;
	@ManyToOne
	@JoinColumn(name = "id_client")
	private Client client;
	@ManyToOne
	@JoinColumn(name = "id_chauffeur")
	private Chauffeur chauffeur;
	@ManyToOne
	@JoinColumn(name = "id_camion")
	private Camion camion;
	@OneToMany(targetEntity = Facture_Produit.class, mappedBy = "facture", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Facture_Produit> qtes;
	@Column(name = "deleted", nullable = false)
	boolean deleted;
	@ManyToOne
	@JoinColumn(name = "id_annee")
	private Annee annee;

	//
	public Facture() {
	}

<<<<<<< HEAD
	public Facture(Date date, double montant, double tva) {
=======
	public Facture(Date date, double montant, double tva, double timbre) {
>>>>>>> 48e5978c8ca1edc489606e1fad7288796221534e
		this.date = date;
		this.montant = montant;
		// this.tva = tva;
		this.timbre = timbre;
	}

	public Annee getAnnee() {
		return annee;
	}

	public Camion getCamion() {
		return camion;
	}

	public Chauffeur getChauffeur() {
		return chauffeur;
	}

	public Client getClient() {
		return client;
	}

	public Date getDate() {
		return date;
	}

	public int getIdFacture() {
		return idFacture;
	}

	public double getMontant() {
		return montant;
	}

	public double getMontantFinal() {
		return montantFinal;
	}

	public List<Facture_Produit> getQtes2() {
		return qtes;
	}

	public double getTimbre() {
		return timbre;
	}

	public double getTva() {
		return tva;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setAnnee(Annee annee) {
		this.annee = annee;
	}

	public void setCamion(Camion camion) {
		this.camion = camion;
	}

	public void setChauffeur(Chauffeur chauffeur) {
		this.chauffeur = chauffeur;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public void setIdFacture(int idFacture) {
		this.idFacture = idFacture;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public void setMontantFinal(double montantFinal) {
		this.montantFinal = montantFinal;
	}

	public void setQtes(List<Facture_Produit> qtes) {
		this.qtes = qtes;
	}

	public void setTimbre(double timbre) {
		this.timbre = timbre;
	}

	public void setTva(double tva) {
		this.tva = tva;
	}

}
