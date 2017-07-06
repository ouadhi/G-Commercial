/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@Table(name = "Ble")
public class Ble {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_Ble", nullable = false)
	int idBle;
	@Column(name = "designation", nullable = false)
	int designation;
	@Column(name = "quantite", nullable = false)
	double qte;
	@Column(name = "prix", nullable = false)
	double prix;
	@Column(name = "code_ble", nullable = false, unique = true)
	String codeBle;
	@OneToMany(targetEntity = Achat.class, mappedBy = "ble", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Achat> achats;
	@Column(name = "deleted", nullable = false)
	boolean deleted;

	public Ble() {
	}

	public Ble(String codeBle, double qte, double prix) {
		this.codeBle = codeBle;
		this.qte = qte;
		this.prix = prix;
	}

	public List<Achat> getAchats() {
		return achats;
	}

	public String getCodeBle() {
		return codeBle;
	}

	public int getIdBle() {
		return idBle;
	}

	public double getPrix() {
		return prix;
	}

	public double getQte() {
		return qte;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setAchats(List<Achat> achats) {
		this.achats = achats;
	}

	public void setCodeBle(String codeBle) {
		this.codeBle = codeBle;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public void setIdBle(int idBle) {
		this.idBle = idBle;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public void setQte(double qte) {
		this.qte = qte;
	}

}
