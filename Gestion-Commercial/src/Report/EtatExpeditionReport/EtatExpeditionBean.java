/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report.EtatExpeditionReport;

import java.util.List;

/**
 *
 * @author Hicham
 */
public class EtatExpeditionBean {

	String date;
	String totalFarine;
	String totalSon;
	String totalMontant;
	String totalVersement;
	String totalVersemntMoinMontant;
	String totalQuantite;
	String farineTotal;
	String sonTotal;
	String montantTotal;
	String versementTotal;
	String differenceTotal;
	public List<String> clients;
	public List<String> nums;
	public List<String> produits;
	public List<String> qteFarins;
	public List<String> qteSons;
	public List<String> prixs;
	public List<String> montants;
	public List<String> versements;
	public List<String> observations;

	public EtatExpeditionBean(String date, String totalFarine, String totalSon, String totalMontant,
			String totalVersement, String totalVersemntMoinMontant, String totalQuantite, String farineTotal,
			String sonTotal, String montantTotal, String versementTotal, String differenceTotal, List<String> clients,
			List<String> nums, List<String> produits, List<String> qteFarins, List<String> qteSons, List<String> prixs,
			List<String> montants, List<String> versements, List<String> observations) {
		this.date = date;
		this.totalFarine = totalFarine;
		this.totalSon = totalSon;
		this.totalMontant = totalMontant;
		this.totalVersement = totalVersement;
		this.totalVersemntMoinMontant = totalVersemntMoinMontant;
		this.totalQuantite = totalQuantite;
		this.farineTotal = farineTotal;
		this.sonTotal = sonTotal;
		this.montantTotal = montantTotal;
		this.versementTotal = versementTotal;
		this.differenceTotal = differenceTotal;
		this.clients = clients;
		this.nums = nums;
		this.produits = produits;
		this.qteFarins = qteFarins;
		this.qteSons = qteSons;
		this.prixs = prixs;
		this.montants = montants;
		this.versements = versements;
		this.observations = observations;
	}

	public List<String> getClients() {
		return clients;
	}

	public String getDate() {
		return date;
	}

	public String getDifferenceTotal() {
		return differenceTotal;
	}

	public String getFarineTotal() {
		return farineTotal;
	}

	public List<String> getMontants() {
		return montants;
	}

	public String getMontantTotal() {
		return montantTotal;
	}

	public List<String> getNums() {
		return nums;
	}

	public List<String> getObservations() {
		return observations;
	}

	public List<String> getPrixs() {
		return prixs;
	}

	public List<String> getProduits() {
		return produits;
	}

	public List<String> getQteFarins() {
		return qteFarins;
	}

	public List<String> getQteSons() {
		return qteSons;
	}

	public String getSonTotal() {
		return sonTotal;
	}

	public String getTotalFarine() {
		return totalFarine;
	}

	public String getTotalMontant() {
		return totalMontant;
	}

	public String getTotalQuantite() {
		return totalQuantite;
	}

	public String getTotalSon() {
		return totalSon;
	}

	public String getTotalVersement() {
		return totalVersement;
	}

	public String getTotalVersemntMoinMontant() {
		return totalVersemntMoinMontant;
	}

	public List<String> getVersements() {
		return versements;
	}

	public String getVersementTotal() {
		return versementTotal;
	}

	public void setClients(List<String> clients) {
		this.clients = clients;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setDifferenceTotal(String differenceTotal) {
		this.differenceTotal = differenceTotal;
	}

	public void setFarineTotal(String farineTotal) {
		this.farineTotal = farineTotal;
	}

	public void setMontants(List<String> montants) {
		this.montants = montants;
	}

	public void setMontantTotal(String montantTotal) {
		this.montantTotal = montantTotal;
	}

	public void setNums(List<String> nums) {
		this.nums = nums;
	}

	public void setObservations(List<String> observations) {
		this.observations = observations;
	}

	public void setPrixs(List<String> prixs) {
		this.prixs = prixs;
	}

	public void setProduits(List<String> produits) {
		this.produits = produits;
	}

	public void setQteFarins(List<String> qteFarins) {
		this.qteFarins = qteFarins;
	}

	public void setQteSons(List<String> qteSons) {
		this.qteSons = qteSons;
	}

	public void setSonTotal(String sonTotal) {
		this.sonTotal = sonTotal;
	}

	public void setTotalFarine(String totalFarine) {
		this.totalFarine = totalFarine;
	}

	public void setTotalMontant(String totalMontant) {
		this.totalMontant = totalMontant;
	}

	public void setTotalQuantite(String totalQuantite) {
		this.totalQuantite = totalQuantite;
	}

	public void setTotalSon(String totalSon) {
		this.totalSon = totalSon;
	}

	public void setTotalVersement(String totalVersement) {
		this.totalVersement = totalVersement;
	}

	public void setTotalVersemntMoinMontant(String totalVersemntMoinMontant) {
		this.totalVersemntMoinMontant = totalVersemntMoinMontant;
	}

	public void setVersements(List<String> versements) {
		this.versements = versements;
	}

	public void setVersementTotal(String versementTotal) {
		this.versementTotal = versementTotal;
	}

}
