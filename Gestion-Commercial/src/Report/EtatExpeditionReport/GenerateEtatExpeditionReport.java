/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report.EtatExpeditionReport;

import com.gestionCommerciale.HibernateSchema.Client;
import com.gestionCommerciale.HibernateSchema.Facture;
import com.gestionCommerciale.HibernateSchema.Facture_Produit;
import com.gestionCommerciale.Models.SessionsGenerator;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;

/**
 *
 * @author Hicham
 */
public class GenerateEtatExpeditionReport {

    Map<Facture, Client> map = new HashMap<>();

    public void Facture_ClientParJour(Date jour) {
        List<Facture> list = null;
        Map<Facture, Client> map = new HashMap<>();
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            list = new ArrayList<>();
            list = session.createQuery("from Facture").list();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getDate().equals(jour)) {
                    map.put(list.get(i), list.get(i).getClient());
                }
            }
        } finally {
            session.close();
        }
        this.map = map;
    }

    public Map<Facture, Client> getFacture_ClientParJour() {
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
            for (int i = 0; i < facture.getPayments().size(); i++) {
                total = total + facture.getPayments().get(i).getMontant();
            }
        }
        return total;
    }

    public List<Double> sommes() {
        List<Double> listSommes = null;
        //Map<Facture, Client> map = getFacture_ClientParJour();
        try {
            //somme farine
            listSommes.add(sommeFarineSon("FARINE 50"));
            //somme Son
            listSommes.add(sommeFarineSon("SON"));
            //somme montant
            listSommes.add(sommeMontant());
            //somme versement
            listSommes.add(sommeVersement());
            //difference : versement - versement - montant
            double difference = sommeVersement() - sommeMontant();
            listSommes.add(difference);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSommes;
    }

}
