/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report.BonChargementReport;

import java.util.List;

/**
 *
 * @author Hicham
 */
public class BonChargementBean {
	String num;
	String date;
	String nomEtPrenom;
	String code;
	String address;
	public String rc;
	public String fiscal;
	public String article;
	public List<String> designations;
	public List<String> qtes;

	public BonChargementBean(String num, String date, String nomEtPrenom, String code, String address, String rc,
			String fiscal, String article, List<String> designations, List<String> qtes) {
		this.num = num;
		this.date = date;
		this.nomEtPrenom = nomEtPrenom;
		this.code = code;
		this.address = address;
		this.designations = designations;
		this.qtes = qtes;
		this.rc = rc;
		this.fiscal = fiscal;
		this.article = article;
	}

	public String getAddress() {
		return address;
	}

	public String getArticle() {
		return article;
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

	public String getNomEtPrenom() {
		return nomEtPrenom;
	}

	public String getNum() {
		return num;
	}

	public List<String> getQtes() {
		return qtes;
	}

	public String getRc() {
		return rc;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setArticle(String article) {
		this.article = article;
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

	public void setNomEtPrenom(String nomEtPrenom) {
		this.nomEtPrenom = nomEtPrenom;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public void setQtes(List<String> qtes) {
		this.qtes = qtes;
	}

	public void setRc(String rc) {
		this.rc = rc;
	}

}
