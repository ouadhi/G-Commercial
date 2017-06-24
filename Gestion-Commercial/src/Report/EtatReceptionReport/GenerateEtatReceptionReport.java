/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report.EtatReceptionReport;

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
import com.gestionCommerciale.Models.SessionsGenerator;
import com.ibm.icu.text.RuleBasedNumberFormat;

/**
 *
 * @author Hicham
 */
public class GenerateEtatReceptionReport {

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

    double totalFour = 0;

    double totalMoulin = 0;

    double totalDif = 0;

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
                        achatParJour.add(list.get(j));
                        if (!notAddedDate) {
                            jour.add(intervalDate.get(i));
                            notAddedDate = true;
                        }
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

    public void generateReport(Date startDate, Date endDate) {
        OperationEtatReceptionReport operationEtatReceptionReport = new OperationEtatReceptionReport();
        achatParJour(increment_decrementDays(false, startDate, 1), increment_decrementDays(true, endDate, 1));
        RuleBasedNumberFormat ruleBasedNumberFormat = new RuleBasedNumberFormat(new Locale("fr", "FR"),
                RuleBasedNumberFormat.SPELLOUT);
        double prixOAIC = 0;
        if (!listAchats.isEmpty()) {
            prixOAIC = listAchats.get(0).get(0).getBle().getPrix();
        }

        String newStartDate = new SimpleDateFormat("dd-MM-yyyy").format(startDate);
        String newEndDate = new SimpleDateFormat("dd-MM-yyyy").format(endDate);
        for (int i = 0; i < jour.size(); i++) {
            List<String> nums = getNumList(i);
            List<String> qteFours = getQteFourList(i);
            List<String> qteMoulins = getQteMoulinList(i);
            List<String> qteDifs = getQteDiffList(i);
            String dateJour = new SimpleDateFormat("dd-MM-yyyy").format(jour.get(i));
            //calcule montant total 
            double montantTotaltttt = prixOAIC * totalFour;
            double montantCheque = round(montantTotaltttt, 2);
            String montantlettre = ruleBasedNumberFormat.format(montantCheque) + " Dinars Algérien";

            operationEtatReceptionReport.putReportInfo(newStartDate, newEndDate, dateJour, String.valueOf(totalFour),
                    String.valueOf(totalMoulin), String.valueOf(totalDif),
                    String.valueOf(montantCheque), montantlettre,
                    nums, qteFours, qteMoulins, qteDifs);
        }
        operationEtatReceptionReport.printReport();

    }

    public List<String> getNumList(int n) {
        List<String> nums = new ArrayList<>();
        for (int i = 0; i < listAchats.get(n).size(); i++) {
            String num = listAchats.get(n).get(i).getNumAcqt();
            nums.add(num);
        }

        return nums;
    }

    public List<String> getQteDiffList(int n) {
        List<String> qteDiffs = new ArrayList<>();
        for (int i = 0; i < listAchats.get(n).size(); i++) {
            totalDif = totalDif + listAchats.get(n).get(i).getQuantiteDiff();
            String qteDiff = String.valueOf(listAchats.get(n).get(i).getQuantiteDiff());
            qteDiffs.add(qteDiff);
        }
        return qteDiffs;
    }

    public List<String> getQteFourList(int n) {
        List<String> qteFours = new ArrayList<>();
        for (int i = 0; i < listAchats.get(n).size(); i++) {
            totalFour = totalFour + listAchats.get(n).get(i).getQuantiteFour();
            String qteFour = String.valueOf(listAchats.get(n).get(i).getQuantiteFour());
            qteFours.add(qteFour);

        }
        return qteFours;
    }


    public List<String> getQteMoulinList(int n) {
        List<String> qteMoulins = new ArrayList<>();
        for (int i = 0; i < listAchats.get(n).size(); i++) {
            totalMoulin = totalMoulin + listAchats.get(n).get(i).getQuantiteAcqt();
            String qteMoulin = String.valueOf(listAchats.get(n).get(i).getQuantiteAcqt());
            qteMoulins.add(qteMoulin);
        }
        return qteMoulins;
    }

}
