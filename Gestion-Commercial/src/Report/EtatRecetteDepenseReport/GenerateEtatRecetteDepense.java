/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report.EtatRecetteDepenseReport;

import com.gestionCommerciale.HibernateSchema.Client;
import com.gestionCommerciale.HibernateSchema.Payment;
import com.gestionCommerciale.Models.SessionsGenerator;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Hicham
 */
public class GenerateEtatRecetteDepense {

    List<Double> listPayement = new ArrayList<>();
    List<Client> listClients = new ArrayList<>();
    List<Double> listSoldes = new ArrayList<>();

    public void getVersementParJour(Date jour) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            List<Payment> list = session.createQuery("from Payment").list();
            double solde = 0;
            for (int i = 0; i < list.size(); i++) {
                if (jour.equals(list.get(i).getDate())) {
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

    public void generateReport(Date jour, String nomBanque, double versementBanque) {
        OperationEtatRecetteDepense operationEtatRecetteDepense = new OperationEtatRecetteDepense();
        getVersementParJour(jour);
        String reste = String.valueOf(listSoldes.get(listSoldes.size() - 1) - versementBanque);
        for (int i = 0; i < listClients.size(); i++) {
            List<String> clients = new ArrayList<>();
            clients.add(String.valueOf(listClients.get(i)));
            List<String> montants = new ArrayList<>();
            montants.add(String.valueOf(listPayement.get(i)));
            List<String> soldes = new ArrayList<>();
            soldes.add(String.valueOf(listSoldes.get(i)));
            List<String> depenses = new ArrayList<>();
            depenses.add("");
            List<String> nums = new ArrayList<>();
            nums.add("");

            operationEtatRecetteDepense.putReportInfo(
                    jour.toString(), String.valueOf(listSoldes.get(listSoldes.size() - 1)),
                    "", nomBanque, reste, nums, clients, montants, depenses, soldes);

        }
        operationEtatRecetteDepense.printReport();

    }

}
