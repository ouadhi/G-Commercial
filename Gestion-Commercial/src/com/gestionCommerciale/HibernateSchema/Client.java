/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Hicham
 */
@Entity
@Table(name = "Client")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_client", nullable = false)
	int id;
	@Column(name = "nom", nullable = false)
	String name;
	@Column(name = "Prenom", nullable = false)
	String prenom;
	@Column(name = "numRegCom", nullable = false, unique = true)
	String numRegCom;
	@Column(name = "numArticle", nullable = false)
	String numArticle;
	@Column(name = "addressClient", nullable = false)
	String addressClient;
	@Column(name = "typeActivity", nullable = false)
	String typeActivity;
	@Column(name = "nCarteFiscale", nullable = false)
	String nCarteFiscale;
	@Column(name = "dateDepotDossier", nullable = false)
	@Temporal(value = TemporalType.DATE)
	Date dateDepotDossier;
	@OneToMany(targetEntity = Facture.class, mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Facture> factures;
	@Column(name = "deleted", nullable = false)
	boolean deleted;
	@OneToMany(targetEntity = Payment.class, mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Payment> payments;

	public Client() {

	}

	public Client(String nom, String prenom, String numRegCom, String numArticle, String addressClient,
			String typeActivity, Date dateDepotDossier, String nCarteFiscale) {
		this.name = nom;
		this.prenom = prenom;
		this.numRegCom = numRegCom;
		this.numArticle = numArticle;
		this.addressClient = addressClient;
		this.typeActivity = typeActivity;
		this.dateDepotDossier = dateDepotDossier;
		this.nCarteFiscale = nCarteFiscale;
	}

	public String getAddressClient() {
		return addressClient;
	}

	public Date getDateDepotDossier() {
		return dateDepotDossier;
	}

	public List<Facture> getFactures() {
		return factures;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getnCarteFiscale() {
		return nCarteFiscale;
	}

	public String getNumArticle() {
		return numArticle;
	}
        
	public String getNumRegCom() {
		return numRegCom;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getTypeActivity() {
		return typeActivity;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setAddressClient(String addressClient) {
		this.addressClient = addressClient;
	}

	public void setDateDepotDossier(Date dateDepotDossier) {
		this.dateDepotDossier = dateDepotDossier;
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

	public void setName(String name) {
		this.name = name;
	}

	public void setnCarteFiscale(String nCarteFiscale) {
		this.nCarteFiscale = nCarteFiscale;
	}

	public void setNumArticle(String numArticle) {
		this.numArticle = numArticle;
	}

	public void setNumRegCom(String numRegCom) {
		this.numRegCom = numRegCom;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setTypeActivity(String typeActivity) {
		this.typeActivity = typeActivity;
	}

}