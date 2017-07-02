/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report.EtatEstimatifParClient;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.gestionCommerciale.HibernateSchema.Client;
import com.gestionCommerciale.HibernateSchema.Facture;
import com.gestionCommerciale.HibernateSchema.Facture_Produit;
import com.gestionCommerciale.Models.ClientQueries;
import com.gestionCommerciale.Models.Facture_ProduitQueries;

import Report.EtatEstimatifGlobal.OperationEtatEstimatifGlobal;

/**
 * @author Hicham
 */
public class GenerationEtatEstimatifClient {

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

    public static Date increment_decrementDays(boolean increment, Date date, int days) {
        Date newdDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (increment) {
            cal.add(Calendar.DATE, days); // minus number would decrement the
            // days
            newdDate = cal.getTime();
        } else {
            cal.add(Calendar.DATE, -days);
            newdDate = cal.getTime();
        }

        return newdDate;
    }

    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();

    }
    List<String> dates = new ArrayList<>();
    List<String> nums = new ArrayList<>();
    List<String> produits = new ArrayList<>();
    List<String> montants = new ArrayList<>();
    List<String> tvas = new ArrayList<>();
    List<String> timbres = new ArrayList<>();
    List<String> qtes = new ArrayList<>();
    List<String> ttcs = new ArrayList<>();

    double ttcTotal = 0;
    double timbreTotal = 0;
    double montantTotal = 0;
    double tvaTotal = 0;
    double farineTotal = 0;
    double sonTotal = 0;
    double qteTotal = 0;

    Client client = new Client();

    public void generateReport(Date dateDebut, Date dateFin, String clientNomPrenom) {
        getFactureParClient(dateDebut, dateFin, clientNomPrenom);
        String start = new SimpleDateFormat("dd-MM-yyyy").format(dateDebut);
        String end = new SimpleDateFormat("dd-MM-yyyy").format(dateFin);
        OperationEtatEstimatifClient operationEtatEstimatifClient = new OperationEtatEstimatifClient();

        operationEtatEstimatifClient.putReportInfo(clientNomPrenom, client.getTypeActivity(), client.getAddressClient(),
                client.getNumRegCom(), client.getnCarteFiscale(), client.getNumArticle(), start, end,
                String.valueOf(montantTotal), String.valueOf(tvaTotal), String.valueOf(ttcTotal), String.valueOf(timbreTotal),
                String.valueOf(farineTotal), String.valueOf(sonTotal), String.valueOf(round(qteTotal, 2)),
                dates, this.nums, this.produits, this.montants, this.tvas, this.ttcs, this.timbres, this.qtes);
        operationEtatEstimatifClient.printReport();
    }

    public void generateReportGeneral(Date dateDebut, Date dateFin, String clientNomPrenom) {
        // add true false
        getFactureParClient(dateDebut, dateFin, clientNomPrenom);
        String start = new SimpleDateFormat("dd-MM-yyyy").format(dateDebut);
        String end = new SimpleDateFormat("dd-MM-yyyy").format(dateFin);
        OperationEtatEstimatifGlobal operationEtatEstimatifGlobal = new OperationEtatEstimatifGlobal();
        operationEtatEstimatifGlobal.putReportInfo(clientNomPrenom, client.getTypeActivity(), client.getAddressClient(),
                client.getNumRegCom(), client.getnCarteFiscale(), client.getNumArticle(), start, end,
                String.valueOf(montantTotal), String.valueOf(tvaTotal), String.valueOf(ttcTotal), String.valueOf(timbreTotal),
                String.valueOf(farineTotal), String.valueOf(sonTotal));
        operationEtatEstimatifGlobal.printReport();
    }

    public void getFactureParClient(Date debut, Date fin, String nomprenom) {
        List<Date> intervalDate = getDaysBetweenDates(debut, increment_decrementDays(true, fin, 1));
        client = ClientQueries.getClientByNom(nomprenom);
        List<Facture> factures = client.getFactures();
        for (int i = 0; i < intervalDate.size(); i++) {
            for (int j = 0; j < factures.size(); j++) {
                if (intervalDate.get(i).equals(factures.get(j).getDate())) {
                    // edited
                    List<Facture_Produit> fpList = Facture_ProduitQueries.list(factures.get(j));

                    for (int k = 0; k < fpList.size(); k++) {
                        String date = new SimpleDateFormat("dd-MM-yyyy").format(intervalDate.get(i));
                        dates.add(date);
                        nums.add(String.valueOf(factures.get(j).getIdFacture()));
                        produits.add(fpList.get(k).getProduit().getNom());
                        montants.add(String.valueOf(factures.get(j).getMontant()));
                        double tva = factures.get(j).getTva() * factures.get(j).getMontant();
                        tvas.add(String.valueOf(tva));
                        ttcs.add(String.valueOf(factures.get(j).getMontantFinal()));
                        timbres.add(String.valueOf(factures.get(j).getTimbre()));
                        ttcTotal = ttcTotal + factures.get(j).getMontantFinal();
                        montantTotal = montantTotal + factures.get(j).getMontant();
                        tvaTotal = tvaTotal + tva;
                        timbreTotal = timbreTotal + factures.get(j).getTimbre();
                        //calcule total farine et son
                        for (int z = 0; z < factures.get(j).getQtes2().size(); z++) {
                            if ("FARINE 50".equals(factures.get(j).getQtes2().get(z).getProduit().getNom())) {
                                farineTotal = farineTotal + (factures.get(j).getQtes2().get(z).getQte_fact()
                                        * factures.get(j).getQtes2().get(z).getProduit().getPrix());
                                qtes.add(String.valueOf(round(factures.get(j).getQtes2().get(z).getQte_fact(), 2)));
                                qteTotal = qteTotal + factures.get(j).getQtes2().get(z).getQte_fact();
                            }
                            if ("SON".equals(factures.get(j).getQtes2().get(z).getProduit().getNom())) {
                                sonTotal = sonTotal + (factures.get(j).getQtes2().get(z).getQte_fact()
                                        * factures.get(j).getQtes2().get(z).getProduit().getPrix());
                                qtes.add(String.valueOf(round(factures.get(j).getQtes2().get(z).getQte_fact(), 2)));
                                qteTotal = qteTotal + factures.get(j).getQtes2().get(z).getQte_fact();
                            }
                        }
                    }
                }

            }
        }

    }

}
