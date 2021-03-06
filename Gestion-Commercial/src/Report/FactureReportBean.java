package Report;

import java.util.List;

public class FactureReportBean {

	public int id;
	public String nom;
	public String code;
	public String address;
	public String rc;
	public String fiscal;
	public String date;
	public String numFacture;
	public String article;
	public String montantHT;
	public String tva;
	public String timbre;
	public String ttc;
	public String montantlettre;
	public String chauffeur;
	public String matricule;
	public List<String> designations;
	public List<String> qtes;
	public List<String> prixs;
	public List<String> montants;

	public FactureReportBean(int id, String nom, String code, String address, String rc, String fiscal, String date,
			String numFacture, String article, String montantHT, String tva, String timbre, String ttc,
			String montantlettre, String chauffeur, String matricule, List<String> designations, List<String> qtes,
			List<String> prixs, List<String> montants) {
		super();
		this.id = id;
		this.nom = nom;
		this.code = code;
		this.address = address;
		this.rc = rc;
		this.fiscal = fiscal;
		this.date = date;
		this.numFacture = numFacture;
		this.article = article;
		this.montantHT = montantHT;
		this.tva = tva;
		this.timbre = timbre;
		this.ttc = ttc;
		this.montantlettre = montantlettre;
		this.chauffeur = chauffeur;
		this.matricule = matricule;
		this.designations = designations;
		this.qtes = qtes;
		this.prixs = prixs;
		this.montants = montants;
	}

	public String getAddress() {
		return address;
	}

	public String getArticle() {
		return article;
	}

	public String getChauffeur() {
		return chauffeur;
	}

	public String getCode() {
		return code;
	}

	public String getDate() {
		return date;
	}

	public List<String> getDesignations() {
		return designations;
	}

	public String getFiscal() {
		return fiscal;
	}

	public int getId() {
		return id;
	}

	public String getMatricule() {
		return matricule;
	}

	public String getMontantHT() {
		return montantHT;
	}

	public String getMontantlettre() {
		return montantlettre;
	}

	public List<String> getMontants() {
		return montants;
	}

	public String getNom() {
		return nom;
	}

	public String getNumFacture() {
		return numFacture;
	}

	public List<String> getPrixs() {
		return prixs;
	}

	public List<String> getQtes() {
		return qtes;
	}

	public String getRc() {
		return rc;
	}

	public String getTimbre() {
		return timbre;
	}

	public String getTtc() {
		return ttc;
	}

	public String getTva() {
		return tva;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public void setChauffeur(String chauffeur) {
		this.chauffeur = chauffeur;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setDesignations(List<String> designations) {
		this.designations = designations;
	}

	public void setFiscal(String fiscal) {
		this.fiscal = fiscal;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public void setMontantHT(String montantHT) {
		this.montantHT = montantHT;
	}

	public void setMontantlettre(String montantlettre) {
		this.montantlettre = montantlettre;
	}

	public void setMontants(List<String> montants) {
		this.montants = montants;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setNumFacture(String numFacture) {
		this.numFacture = numFacture;
	}

	public void setPrixs(List<String> prixs) {
		this.prixs = prixs;
	}

	public void setQtes(List<String> qtes) {
		this.qtes = qtes;
	}

	public void setRc(String rc) {
		this.rc = rc;
	}

	public void setTimbre(String timbre) {
		this.timbre = timbre;
	}

	public void setTtc(String ttc) {
		this.ttc = ttc;
	}

	public void setTva(String tva) {
		this.tva = tva;
	}

}
