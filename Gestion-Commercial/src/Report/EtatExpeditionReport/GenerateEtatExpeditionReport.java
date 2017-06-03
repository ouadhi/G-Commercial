/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report.EtatExpeditionReport;

import com.gestionCommerciale.HibernateSchema.Facture;
import com.gestionCommerciale.Models.SessionsGenerator;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
            Calendar cal = Calendar.getInstance();
            Date yearStart = new GregorianCalendar(cal.get(Calendar.YEAR), 0, 1).getTime();
            List<Date> dates = getDaysBetweenDates(increment_decrementDays(false, yearStart, 1),
                    increment_decrementDays(true, jour, 1));
            List<Facture> listFactures = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < dates.size(); j++) {
                    if (dates.get(j).equals(list.get(i).getDate())) {
                        listFactures.add(list.get(i));
                    }
                }
            }

            for (int i = 0; i < listFactures.size(); i++) {
                if (listFactures.get(i).getDate().equals(jour)) {
                    map.put(listFactures.get(i), listFactures.get(i).getClient().getName());
                }
            }

            // Total  a ce jour  
            for (int i = 0; i < listFactures.size(); i++) {
                for (int j = 0; j < listFactures.get(i).getQtes().size(); j++) {
                    if (listFactures.get(i).getQtes().get(j).getProduit().getNom().equals("FARINE 50")) {
                        farineTotal = farineTotal + listFactures.get(i).getQtes().get(j).getQte_fact();
                    } else {
                        sonTotal = sonTotal + listFactures.get(i).getQtes().get(j).getQte_fact();
                    }
                }
                for (int j = 0; j < listFactures.get(i).getClient().getPayments().size(); j++) {
                    versementTotal = versementTotal + listFactures.get(i).getClient().getPayments().get(j).getMontant();
                }
                montantTotal = montantTotal + listFactures.get(i).getMontant();

            }
            differenceTotal = versementTotal - montantTotal;
        } finally {
            session.close();
        }
        this.map = map;
        System.out.println("---------------map: " + map);
    }

    public Map<Facture, String> getFacture_ClientParJour() {
        return map;

    }

    public static Date increment_decrementDays(boolean increment, Date date, int days) {
        Date newdDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (increment) {
            cal.add(Calendar.DATE, days); //minus number would decrement the days
            newdDate = cal.getTime();
        } else {
            cal.add(Calendar.DATE, -days);
            newdDate = cal.getTime();
        }

        return newdDate;
    }

    public static List<Date> getDaysBetweenDates(Date startdate, Date enddate) {
        List<Date> dates = new ArrayList<Date>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startdate);

        while (calendar.getTime().before(enddate)) {
            Date result = calendar.getTime();
            dates.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        return dates;
    }

    //somme qte farine et son
    public double sommeFarineSon(String designationProduit) {
        double total = 0;
        for (Facture facture : map.keySet()) {
            for (int i = 0; i < facture.getQtes().size(); i++) {
                if (facture.getQtes().get(i).getProduit().getNom().equals(designationProduit)) {
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
        List<Double> listSommes = new ArrayList<>();
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
                expedition.add(facture.getQtes().get(i).getProduit().getNom().toString());
                if (facture.getQtes().get(i).getProduit().getNom().equals("FARINE 50")) {
                    expedition.add(String.valueOf(facture.getQtes().get(i).getQte_fact()));
                    expedition.add("0");
                } else {
                    expedition.add("0");
                    expedition.add(String.valueOf(facture.getQtes().get(i).getQte_fact()));
                }
                expedition.add(String.valueOf(facture.getQtes().get(i).getProduit().getPrix()));
                //check if empty 
                if (facture.getClient().getPayments().isEmpty()) {
                    expedition.add(String.valueOf(0));
                } else {
                    //iterate over payement checking date
                    double totalVersement = 0;
                    for (int k = 0; k < facture.getClient().getPayments().size(); k++) {
                        if (facture.getDate().equals(facture.getClient().getPayments().get(i).getDate())) {
                            totalVersement = totalVersement + facture.getClient().getPayments().get(i).getMontant();
                        }
                    }
                    expedition.add(String.valueOf(totalVersement));

                }
                expedition.add(String.valueOf(facture.getMontant()));
                observations.add("");

            }
            System.out.println("list expidition ------------------" + expedition);
            expeditions.add(expedition);
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

    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
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
        /*
        System.out.println(clients);
        System.out.println(nums);
        System.out.println(produits);
        System.out.println(qteFarins);
        System.out.println(qteSons);
        System.out.println(prixs);
        System.out.println(montants);
        System.out.println(versements);
        System.out.println(sommes);*/
        double totalFarine = round(sommes.get(0), 2);
        double totalSon = round(sommes.get(1), 2);
        double totalMontant = round(sommes.get(2), 2);
        double totalVersement = round(sommes.get(3), 2);
        double totalVersemntMoinMontant = round(sommes.get(4), 2);
        double totalQuantite = round(sommes.get(5), 2);
        operationEtatExpedition.putReportInfo(jour.toString(), String.valueOf(totalFarine), String.valueOf(totalSon),
                String.valueOf(totalMontant), String.valueOf(totalVersement),
                String.valueOf(totalVersemntMoinMontant), String.valueOf(totalQuantite), String.valueOf(round(farineTotal, 2)),
                String.valueOf(round(sonTotal, 2)), String.valueOf(round(montantTotal, 2)), String.valueOf(round(versementTotal, 2)),
                String.valueOf(round(differenceTotal, 2)), clients, nums,
                produits, qteFarins, qteSons, prixs, montants, versements, observations);
        operationEtatExpedition.printReport();

    }

}
