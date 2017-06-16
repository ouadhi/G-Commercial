package com.gestionCommerciale.HibernateSchema;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author CHERABRAB
 */
@Entity
@Table(name = "Annee")
public class Annee {
	@Id
	@Column(name = "id_annee", nullable = false)
	int idAnnee;
	@Column(name = "tva", nullable = false)
	double tva;
	@Column(name = "deleted", nullable = false)
	boolean deleted;
	@Column(name = "selected", nullable = false)
	boolean selected;
	@OneToMany(targetEntity = Facture.class, mappedBy = "annee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Facture> factures;
	@OneToMany(targetEntity = Achat.class, mappedBy = "annee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Achat> achats;
	@OneToMany(targetEntity = Payment.class, mappedBy = "annee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Payment> payments;

	public Annee() {
	}

	public Annee(int idAnnee, double tva) {
		this.idAnnee = idAnnee;
		this.tva = tva;
		this.deleted = false;
	}

	public List<Achat> getAchats() {
		return achats;
	}

	public List<Facture> getFactures() {
		return factures;
	}

	public int getIdAnnee() {
		return idAnnee;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public double getTva() {
		return tva;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public boolean isSelected() {
		return selected;
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

	public void setIdAnnee(int idAnnee) {
		this.idAnnee = idAnnee;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public void setTva(double tva) {
		this.tva = tva;
	}

}
