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
    public List<String>designations;
    public List<String>qtes;

    public BonChargementBean(String num, String date, String nomEtPrenom, String code
            , String address, List<String> designations, List<String> qtes) {
        this.num = num;
        this.date = date;
        this.nomEtPrenom = nomEtPrenom;
        this.code = code;
        this.address = address;
        this.designations = designations;
        this.qtes = qtes;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNomEtPrenom() {
        return nomEtPrenom;
    }

    public void setNomEtPrenom(String nomEtPrenom) {
        this.nomEtPrenom = nomEtPrenom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getDesignations() {
        return designations;
    }

    public void setDesignations(List<String> designations) {
        this.designations = designations;
    }

    public List<String> getQtes() {
        return qtes;
    }

    public void setQtes(List<String> qtes) {
        this.qtes = qtes;
    }
    


    
    
}
