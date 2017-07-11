/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report.ReportEtatRemboursement;

import UIControle.Methode;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.hibernate.Session;

import com.gestionCommerciale.HibernateSchema.Achat;
import com.gestionCommerciale.HibernateSchema.Company;
import com.gestionCommerciale.Models.CompanyQueries;
import com.gestionCommerciale.Models.SessionsGenerator;
import com.ibm.icu.text.RuleBasedNumberFormat;

/**
 *
 * @author Hicham
 */
public class GenerateEtatRemboursementReport {

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

    List<List<Achat>> listAchats = new ArrayList<>();

    List<Date> jour = new ArrayList<>();

    double montantTotal = 0;

    public void achatParJour(Date startDate, Date lastDate) {
        List<Date> intervalDate = getDaysBetweenDates(startDate, lastDate);
        List<Achat> list = null;
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        try {

            list = new ArrayList<>();
            list = session.createQuery("from Achat where deleted='" + false + "'").list();
            for (int i = 0; i < intervalDate.size(); i++) {
                boolean notAddedDate = false;
                List<Achat> achatParJour = new ArrayList<>();
                for (int j = 0; j < list.size(); j++) {
                    if (intervalDate.get(i).equals(list.get(j).getDateAcqt())) {
                        // calcule total montant
                        montantTotal = montantTotal
                                + ((list.get(j).getQuantiteAcqt()) * (list.get(j).getDock().getPrixUnitTrans()));
                        achatParJour.add(list.get(j));
                        if (!notAddedDate) {
                            jour.add(intervalDate.get(i));
                            notAddedDate = true;
                        }
                    } else {
                        // fait rien
                    }
                }
                if (!achatParJour.isEmpty()) {
                    listAchats.add(achatParJour);
                }

            }

        } finally {
            session.close();
        }
    }

    public String calculeMantant(double qte, double prix) {
        double montant = qte * prix;
        return Methode.DoubleFormat(round(montant,2));
    }

    public String transformationEnLettre(double montant) {
        //get part before decimal point
        int decimal = (int) Math.floor(montant);
        //get after decimal point 
        int fraction = (int) (round(montant - decimal, 2) * 100);
        //change first part tpo charcater 
        RuleBasedNumberFormat ruleBasedNumberFormat = new RuleBasedNumberFormat(new Locale("fr", "FR"),
                RuleBasedNumberFormat.SPELLOUT);
        String firstPart = ruleBasedNumberFormat.format(new Double(decimal)) + " Dinars";
        String secondPart = " et " + ruleBasedNumberFormat.format(new Double(fraction)) + " Centimes";
        if (fraction == 0) {
            secondPart = "";
        }
        String all = firstPart + secondPart;
        return all;
    }

    public void generateReport(Date startDate, Date endDate, String doit) {
        OperationEtatRemboursementReport operationEtatRemboursementReport = new OperationEtatRemboursementReport();

        achatParJour(startDate, increment_decrementDays(true, endDate, 1));
        String end = new SimpleDateFormat("dd-MM-yyyy").format(endDate);
        String start = new SimpleDateFormat("dd-MM-yyyy").format(startDate);

        String date = "De: " + start + " a " + end;
        String montantlettre = transformationEnLettre(montantTotal);
        Company company= CompanyQueries.getCompany();
      
        for (int i = 0; i < jour.size(); i++) {
            List<String> parcours = getParcourList(i);
            List<String> distances = getDistancesList(i);
            List<String> nums = getnumTiquetList(i);
            List<String> qtes = getQteList(i);
            List<String> prixs = getPrixList(i);
            List<String> montants = getMontantsList(i);
            
            String jourDate = new SimpleDateFormat("dd-MM-yyyy").format(jour.get(i));
            List<String> jours = new ArrayList<>();
            jours.add(jourDate);
//            for (int j = 0; j < montants.size(); j++) {
//                if (montants.size() != 1 & j == ((int) montants.size() / 2)) {
//                    jours.add("");
//                } else {
//                    jours.add(jourDate);
//                }
//            }
            operationEtatRemboursementReport.putReportInfo(doit, date,
                    Methode.DoubleFormat(round(montantTotal, 2)), montantlettre, parcours, distances, nums, qtes,
                    prixs, montants, jours,company.getRegistre(),company.getFiscale(),company.getArticle(),
                    company.getTelephone(),company.getFax(),company.getEmail(),company.getNom(),"");
        }
        operationEtatRemboursementReport.printReport();

    }

    public List<String> getDistancesList(int n) {
        List<String> distances = new ArrayList<>();
        for (int i = 0; i < listAchats.get(n).size(); i++) {
            String distance = Methode.DoubleFormat(listAchats.get(n).get(i).getDock().getDistance());
            distances.add(distance);
        }

        return distances;
    }

    public List<String> getMontantsList(int n) {
        List<String> montants = new ArrayList<>();
        for (int i = 0; i < listAchats.get(n).size(); i++) {
            String montant = calculeMantant(listAchats.get(n).get(i).getQuantiteAcqt(),
                    listAchats.get(n).get(i).getDock().getPrixUnitTrans());

            montants.add(montant);
        }
        return montants;
    }

    public List<String> getnumTiquetList(int n) {
        List<String> nums = new ArrayList<>();
        for (int i = 0; i < listAchats.get(n).size(); i++) {
            String num = listAchats.get(n).get(i).getNumAcqt();
            nums.add(num);
        }

        return nums;
    }

    public List<String> getParcourList(int n) {
        List<String> parcours = new ArrayList<>();
        for (int i = 0; i < listAchats.get(n).size(); i++) {
            String parcour = listAchats.get(n).get(i).getDock().getNom() + " O/Fres";
            parcours.add(parcour);
        }
        return parcours;
    }

    public List<String> getPrixList(int n) {
        List<String> prixs = new ArrayList<>();
        for (int i = 0; i < listAchats.get(n).size(); i++) {
            String prix = String.valueOf(round(listAchats.get(n).get(i).getDock().getPrixUnitTrans(),4));
            prixs.add(prix);
        }
        return prixs;
    }

    public List<String> getQteList(int n) {
        List<String> qtes = new ArrayList<>();
        for (int i = 0; i < listAchats.get(n).size(); i++) {
            String qte = Methode.DoubleFormat(listAchats.get(n).get(i).getQuantiteAcqt());
            qtes.add(qte);
        }
        return qtes;
    }

}
