package com.gestionCommerciale.HibernateSchema;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Company")
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_company", nullable = false)
	int idCompany;
	@Column(name = "nom", nullable = false)
	String nom;
	@Column(name = "registre", nullable = false)
	String registre;
	@Column(name = "fiscale", nullable = false)
	String fiscale;
	@Column(name = "article", nullable = false)
	String article;
	@Column(name = "email", nullable = false)
	String email;
	@Column(name = "telephone", nullable = false)
	String telephone;
	@Column(name = "fax", nullable = false)
	String fax;

	public Company() {

	}

	public Company(String nom, String registre, String fiscale, String article, String email, String telephone,
			String fax) {
		this.nom = nom;
		this.registre = registre;
		this.fiscale = fiscale;
		this.article = article;
		this.email = email;
		this.telephone = telephone;
		this.fax = fax;
	}

	public String getArticle() {
		return article;
	}

	public String getEmail() {
		return email;
	}

	public String getFax() {
		return fax;
	}

	public String getFiscale() {
		return fiscale;
	}

	public String getNom() {
		return nom;
	}

	public String getRegistre() {
		return registre;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public void setFiscale(String fiscale) {
		this.fiscale = fiscale;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setRegistre(String registre) {
		this.registre = registre;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}
