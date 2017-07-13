/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report.EtatRecetteDepenseReport;

import UIControle.Methode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.gestionCommerciale.HibernateSchema.Client;
import com.gestionCommerciale.HibernateSchema.Company;
import com.gestionCommerciale.HibernateSchema.Payment;
import com.gestionCommerciale.Models.BanqueQueries;
import com.gestionCommerciale.Models.CompanyQueries;
import com.gestionCommerciale.Models.SessionsGenerator;

/**
 *
 * @author Hicham
 */
public class GenerateEtatRecetteDepense {

    List<Double> listPayement = new ArrayList<>();
    List<Client> listClients = new ArrayList<>();
    List<Double> listSoldes = new ArrayList<>();
    BanqueQueries banqueQueries = new BanqueQueries();

    public void generateReport(Date jour, String nomBanque, double versementBanque) {
        OperationEtatRecetteDepense operationEtatRecetteDepense = new OperationEtatRecetteDepense();
        getVersementParJour(jour);
        String reste = Methode.DoubleFormat(listSoldes.get(listSoldes.size() - 1) - versementBanque);
        String numBanquaire = banqueQueries.getCompteBancaire(nomBanque);
        Company company = CompanyQueries.getCompany();

        int counter = 1;
        for (int i = 0; i < listClients.size(); i++) {
            List<String> clients = new ArrayList<>();
            clients.add(String.valueOf(listClients.get(i).getName() + " " + listClients.get(i).getPrenom()));
            List<String> montants = new ArrayList<>();
            montants.add(Methode.DoubleFormat(listPayement.get(i)));
            List<String> soldes = new ArrayList<>();
            soldes.add(Methode.DoubleFormat(listSoldes.get(i)));
            List<String> depenses = new ArrayList<>();
            depenses.add("");
            List<String> nums = new ArrayList<>();
            nums.add(String.valueOf(counter));
            String newDate = new SimpleDateFormat("dd-MM-yyyy").format(jour);
            operationEtatRecetteDepense.putReportInfo(newDate, Methode.DoubleFormat(listSoldes.get(listSoldes.size() - 1)),
                    "", nomBanque, reste, numBanquaire, Methode.DoubleFormat(versementBanque), nums,
                    clients, montants, depenses, soldes, company.getRegistre(), company.getFiscale(),
                    company.getArticle(),
                    company.getTelephone(), company.getFax(), company.getEmail(), company.getNom(),company.getAddress());
            counter++;

        }
        operationEtatRecetteDepense.printReport();

    }

    public void getVersementParJour(Date jour) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        try {

            List<Payment> list = session.createQuery("from Payment where deleted='" + false + "'").list();
            double solde = 0;
            for (int i = 0; i < list.size(); i++) {
                if (jour.equals(list.get(i).getDate())) {
                    System.out.println("solde----------:" + solde);
                    solde = solde + list.get(i).getMontant();
                    listPayement.add(list.get(i).getMontant());
                    listClients.add(list.get(i).getClient());
                    listSoldes.add(solde);
                }
            }

        } finally {
            session.close();
        }
    }

}
