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
@Table(name = "Payment")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_payment", nullable = false)
	int idPayment;
	@Column(name = "type", nullable = false)
	String type;
	@Column(name = "montant", nullable = false)
	double montant;
	@Column(name = "date", nullable = false)
	@Temporal(value = TemporalType.DATE)
	Date date;
	@ManyToOne
	@JoinColumn(name = "id_client")
	private Client client;
	@Column(name = "deleted", nullable = false)
	boolean deleted;
	@ManyToOne
	@JoinColumn(name = "id_annee")
	private Annee annee;

	public Payment() {
	}

	public Payment(String type, double montant, Date date) {
		this.type = type;
		this.montant = montant;
		this.date = date;
	}

	public Annee getAnnee() {
		return annee;
	}

	public Client getClient() {
		return client;
	}

	public Date getDate() {
		return date;
	}

	public int getIdPayment() {
		return idPayment;
	}

	public double getMontant() {
		return montant;
	}

	public String getType() {
		return type;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setAnnee(Annee annee) {
		this.annee = annee;
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

	public void setIdPayment(int idPayment) {
		this.idPayment = idPayment;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public void setType(String type) {
		this.type = type;
	}

}
