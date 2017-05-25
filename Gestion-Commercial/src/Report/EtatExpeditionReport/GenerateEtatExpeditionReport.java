/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report.EtatExpeditionReport;

import com.gestionCommerciale.HibernateSchema.Facture;
import com.gestionCommerciale.Models.SessionsGenerator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import org.hibernate.Session;

/**
 *
 * @author Hicham
 */
public class GenerateEtatExpeditionReport {

    Map<Facture, String> map = new HashMap<>();
    List<String> observations = new ArrayList<>();

    double farineTotal = 0;
    double sonTotal = 0;
    double montantTotal = 0;
    double versementTotal = 0;
    double differenceTotal = 0;

    public void Facture_ClientParJour(Date jour) {
        List<Facture> list = null;
        Map<Facture, String> map = new HashMap<>();
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            list = new ArrayList<>();
            list = session.createQuery("from Facture").list();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getDate().equals(jour)) {
                    map.put(list.get(i), list.get(i).getClient().getName());
                }
            }
            // Total 
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < list.get(i).getQtes().size(); j++) {
                    if (list.get(i).getQtes().get(i).getProduit().toString().equals("FARINE 50")) {
                        farineTotal = farineTotal + list.get(i).getQtes().get(j).getQte_fact();
                    } else {
                        sonTotal = sonTotal + list.get(i).getQtes().get(j).getQte_fact();
                    }
                }
                for (int j = 0; j < list.get(i).getClient().getPayments().size(); j++) {
                    versementTotal = versementTotal + list.get(i).getClient().getPayments().get(j).getMontant();
                }
                montantTotal = montantTotal + list.get(i).getMontant();
                
            }
            differenceTotal=versementTotal-montantTotal;
        } finally {
            session.close();
        }
        this.map = map;
    }

    public Map<Facture, String> getFacture_ClientParJour() {
        return map;

    }

    //somme qte farine et son
    public double sommeFarineSon(String designationProduit) {
        double total = 0;
        for (Facture facture : map.keySet()) {
            for (int i = 0; i < facture.getQtes().size(); i++) {
                if (facture.getQtes().get(i).getProduit().equals(designationProduit)) {
                    total = total + facture.getQtes().get(i).getQte_fact();
                }
            }

            facture.getQtes();
        }
        return total;
    }

    public double sommeMontant() {
        double total = 0;
        for (Facture facture : map.keySet()) {
            total = total + facture.getMontant();
        }
        return total;
    }

    public double sommeVersement() {
        double total = 0;
        for (Facture facture : map.keySet()) {
            for (int i = 0; i < facture.getClient().getPayments().size(); i++) {
                total = total + facture.getClient().getPayments().get(i).getMontant();
            }
        }
        return total;
    }

    public List<Double> sommes() {
        List<Double> listSommes = null;
        //Map<Facture, Client> map = getFacture_ClientParJour();
        try {
            //somme farine
            double sommeFarine = sommeFarineSon("FARINE 50");
            listSommes.add(sommeFarine);
            //somme Son
            double sommeSon = sommeFarineSon("SON");
            listSommes.add(sommeSon);
            //somme montant
            listSommes.add(sommeMontant());
            //somme versement
            listSommes.add(sommeVersement());
            //difference : versement - montant
            double difference = sommeVersement() - sommeMontant();
            //somme qte son + qte Farine
            double someQte = sommeFarine + sommeSon;
            listSommes.add(someQte);
            listSommes.add(difference);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSommes;
    }

    public List<List<String>> getExpedition() {
        List<List<String>> expeditions = new ArrayList<>();
        for (Facture facture : map.keySet()) {
            List<String> expedition = new ArrayList<>();
            for (int i = 0; i < facture.getQtes().size(); i++) {
                expedition.add(facture.getClient().getName());
                expedition.add(String.valueOf(facture.getIdFacture()));
                expedition.add(facture.getQtes().get(i).getProduit().toString());
                if (facture.getQtes().get(i).getProduit().toString().equals("FARINE 50")) {
                    expedition.add(String.valueOf(facture.getQtes().get(i).getQte_fact()));
                    expedition.add("0");
                } else {
                    expedition.add("0");
                    expedition.add(String.valueOf(facture.getQtes().get(i).getQte_fact()));
                }
                expedition.add(String.valueOf(facture.getQtes().get(i).getProduit().getPrix()));
                expedition.add(String.valueOf(facture.getMontant()));
                observations.add("");

            }

        }

        return expeditions;
    }

    public List<String> getList(List<List<String>> expeditions, int n) {
        List<String> List = new ArrayList<>();
        for (int i = 0; i < expeditions.size(); i++) {
            List.add(expeditions.get(i).get(n));
        }
        return List;
    }

    public List<String> getClients(List<List<String>> expeditions) {
        return getList(expeditions, 0);
    }

    public List<String> getNums(List<List<String>> expeditions) {
        return getList(expeditions, 1);
    }

    public List<String> getProduits(List<List<String>> expeditions) {
        return getList(expeditions, 2);
    }

    public List<String> getQteFarine(List<List<String>> expeditions) {
        return getList(expeditions, 3);
    }

    public List<String> getQteSon(List<List<String>> expeditions) {
        return getList(expeditions, 4);
    }

    public List<String> getPrix(List<List<String>> expeditions) {
        return getList(expeditions, 5);
    }

    public List<String> getMontant(List<List<String>> expeditions) {
        return getList(expeditions, 6);
    }

    public List<String> getVersement(List<List<String>> expeditions) {
        return getList(expeditions, 7);
    }

    public void generateReport(Date jour) throws IOException, JRException {

        OperationEtatExpedition operationEtatExpedition = new OperationEtatExpedition();
        Facture_ClientParJour(jour);
        List<List<String>> expeditions = getExpedition();
        List<String> clients = getClients(expeditions);
        List<String> nums = getNums(expeditions);
        List<String> produits = getProduits(expeditions);
        List<String> qteFarins = getQteFarine(expeditions);
        List<String> qteSons = getQteSon(expeditions);
        List<String> prixs = getPrix(expeditions);
        List<String> montants = getMontant(expeditions);
        List<String> versements = getVersement(expeditions);
        List<Double> sommes = sommes();
        double totalFarine = sommes.get(0);
        double totalSon = sommes.get(1);
        double totalMontant = sommes.get(2);
        double totalVersement = sommes.get(3);
        double totalVersemntMoinMontant = sommes.get(4);
        double totalQuantite = sommes.get(5);
        operationEtatExpedition.putReportInfo(jour.toString(), String.valueOf(totalFarine), String.valueOf(totalSon)
                , String.valueOf(totalMontant), String.valueOf(totalVersement),
                String.valueOf(totalVersemntMoinMontant), String.valueOf(totalQuantite), String.valueOf(farineTotal)
                , String.valueOf(sonTotal),String.valueOf(montantTotal),String.valueOf(versementTotal), 
                String.valueOf(differenceTotal), clients, nums,
                produits, qteFarins, qteSons, prixs, montants, versements, observations);
        operationEtatExpedition.printReport();

    }

}
