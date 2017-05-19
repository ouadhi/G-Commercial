/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report.EtatBleReport;

import java.util.List;

/**
 *
 * @author Hicham
 */
public class EtatBleBean {

    String jour;
    int num;
    String totalPoid;
    String totalNet;
    String totalEcart;
    public List<String> numBls;
    public List<String> numTiquets;
    public List<String> poidTiquets;
    public List<String> chauffeurs;
    public List<String> matricules;
    public List<String> ptcs;
    public List<String> tares;
    public List<String> nets;
    public List<String> ecarts;

    public EtatBleBean(String jour, int num, String totalPoid, String totalNet, String totalEcart,
            List<String> numBls, List<String> numTiquets, List<String> poidTiquets, List<String> chauffeurs,
             List<String> matricules, List<String> ptcs, List<String> tares,
             List<String> nets, List<String> ecarts) {
        this.jour = jour;
        this.num = num;
        this.totalPoid = totalPoid;
        this.totalNet = totalNet;
        this.totalEcart = totalEcart;
        this.numBls = numBls;
        this.numTiquets = numTiquets;
        this.poidTiquets = poidTiquets;
        this.chauffeurs = chauffeurs;
        this.matricules = matricules;
        this.ptcs = ptcs;
        this.tares = tares;
        this.nets = nets;
        this.ecarts = ecarts;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getTotalPoid() {
        return totalPoid;
    }

    public void setTotalPoid(String totalPoid) {
        this.totalPoid = totalPoid;
    }

    public String getTotalNet() {
        return totalNet;
    }

    public void setTotalNet(String totalNet) {
        this.totalNet = totalNet;
    }

    public String getTotalEcart() {
        return totalEcart;
    }

    public void setTotalEcart(String totalEcart) {
        this.totalEcart = totalEcart;
    }

    public List<String> getNumBls() {
        return numBls;
    }

    public void setNumBls(List<String> numBls) {
        this.numBls = numBls;
    }

    public List<String> getNumTiquets() {
        return numTiquets;
    }

    public void setNumTiquets(List<String> numTiquets) {
        this.numTiquets = numTiquets;
    }

    public List<String> getPoidTiquets() {
        return poidTiquets;
    }

    public void setPoidTiquets(List<String> poidTiquets) {
        this.poidTiquets = poidTiquets;
    }

    public List<String> getChauffeurs() {
        return chauffeurs;
    }

    public void setChauffeurs(List<String> chauffeurs) {
        this.chauffeurs = chauffeurs;
    }

    public List<String> getMatricules() {
        return matricules;
    }

    public void setMatricules(List<String> matricules) {
        this.matricules = matricules;
    }

    public List<String> getPtcs() {
        return ptcs;
    }

    public void setPtcs(List<String> ptcs) {
        this.ptcs = ptcs;
    }

    public List<String> getTares() {
        return tares;
    }

    public void setTares(List<String> tares) {
        this.tares = tares;
    }

    public List<String> getNets() {
        return nets;
    }

    public void setNets(List<String> nets) {
        this.nets = nets;
    }

    public List<String> getEcarts() {
        return ecarts;
    }

    public void setEcarts(List<String> ecarts) {
        this.ecarts = ecarts;
    }

}
