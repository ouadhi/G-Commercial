/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report.EtatRecetteDepenseReport;

import java.util.List;

/**
 *
 * @author Hicham
 */
public class EtatRecetteDepenseBean {

	String date;
	String montantTotal;
	String depenseTotal;
	String banque;
	String reste;
	public List<String> nums;
	public List<String> clients;
	public List<String> montants;
	public List<String> depenses;
	public List<String> soldes;

	public EtatRecetteDepenseBean(String date, String montantTotal, String depenseTotal, String banque, String reste,
			List<String> nums, List<String> clients, List<String> montants, List<String> depenses,
			List<String> soldes) {
		this.date = date;
		this.montantTotal = montantTotal;
		this.depenseTotal = depenseTotal;
		this.banque = banque;
		this.reste = reste;
		this.nums = nums;
		this.clients = clients;
		this.montants = montants;
		this.depenses = depenses;
		this.soldes = soldes;
	}

	public String getBanque() {
		return banque;
	}

	public List<String> getClients() {
		return clients;
	}

	public String getDate() {
		return date;
	}

	public List<String> getDepenses() {
		return depenses;
	}

	public String getDepenseTotal() {
		return depenseTotal;
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

	public String getReste() {
		return reste;
	}

	public List<String> getSoldes() {
		return soldes;
	}

	public void setBanque(String banque) {
		this.banque = banque;
	}

	public void setClients(List<String> clients) {
		this.clients = clients;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setDepenses(List<String> depenses) {
		this.depenses = depenses;
	}

	public void setDepenseTotal(String depenseTotal) {
		this.depenseTotal = depenseTotal;
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

	public void setReste(String reste) {
		this.reste = reste;
	}

	public void setSoldes(List<String> soldes) {
		this.soldes = soldes;
	}

}
