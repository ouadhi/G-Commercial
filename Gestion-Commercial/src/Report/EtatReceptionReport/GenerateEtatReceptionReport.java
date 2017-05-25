/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report.EtatReceptionReport;

import com.gestionCommerciale.HibernateSchema.Achat;
import com.gestionCommerciale.Models.SessionsGenerator;
import com.ibm.icu.text.RuleBasedNumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import org.hibernate.Session;

/**
 *
 * @author Hicham
 */
public class GenerateEtatReceptionReport {

    List<List<Achat>> listAchats = new ArrayList<>();
    List<Date> jour = new ArrayList<>();
    double totalFour=0;
    double totalMoulin=0;
    double totalDif=0;

    public void achatParJour(Date startDate, Date lastDate) {
        List<Date> intervalDate = getDaysBetweenDates(startDate, lastDate);
        List<Achat> list = null;
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            list = new ArrayList<>();
            list = session.createQuery("from Achat").list();
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
                    } else {
                        //fait rien
                    }
                }
                listAchats.add(achatParJour);
            }

        } finally {
            session.close();
        }
    }

    public List<String> getNumList(int n) {
        List<String> nums = new ArrayList<>();
        for (int i = 0; i < listAchats.get(n).size(); i++) {
            String num = listAchats.get(n).get(i).getNumAcqt();
            nums.add(num);
        }
        return nums;
    }

    public List<String> getQteMoulinList(int n) {
        List<String> qteMoulins = new ArrayList<>();
        for (int i = 0; i < listAchats.get(n).size(); i++) {
            totalMoulin= totalMoulin+listAchats.get(n).get(i).getQuantiteAcqt();
            String qteMoulin = String.valueOf(listAchats.get(n).get(i).getQuantiteAcqt());
            qteMoulins.add(qteMoulin);
        }
        return qteMoulins;
    }

    public List<String> getQteFourList(int n) {
        List<String> qteFours = new ArrayList<>();
        for (int i = 0; i < listAchats.get(n).size(); i++) {
            totalFour= totalFour+listAchats.get(n).get(i).getQuantiteFour();
            String qteFour = String.valueOf(listAchats.get(n).get(i).getQuantiteFour());
            qteFours.add(qteFour);
        }
        return qteFours;
    }

    public List<String> getQteDiffList(int n) {
        List<String> qteDiffs = new ArrayList<>();
        for (int i = 0; i < listAchats.get(n).size(); i++) {
            totalDif= totalDif+ listAchats.get(n).get(i).getQuantiteDiff();
            String qteDiff = String.valueOf(listAchats.get(n).get(i).getQuantiteDiff());
            qteDiffs.add(qteDiff);
        }
        return qteDiffs;
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

    public void generateReport(Date startDate, Date endDate, double montantCheque) {
        OperationEtatReceptionReport operationEtatReceptionReport = new OperationEtatReceptionReport();
        achatParJour(startDate, endDate);
        RuleBasedNumberFormat ruleBasedNumberFormat = new RuleBasedNumberFormat(new Locale("fr", "FR"),
                RuleBasedNumberFormat.SPELLOUT);
        String montantlettre = ruleBasedNumberFormat.format(new Double(montantCheque)) + " Dinars Algérien";

        for (int i = 0; i < jour.size(); i++) {
            List<String> nums = getNumList(i);
            List<String> qteFours = getQteFourList(i);
            List<String> qteMoulins = getQteMoulinList(i);
            List<String> qteDifs = getQteDiffList(i);
            
            operationEtatReceptionReport.putReportInfo(startDate.toString(), endDate.toString(), 
                    jour.get(i).toString(), String.valueOf(totalFour), String.valueOf(totalMoulin)
                    , String.valueOf(totalDif), String.valueOf(montantCheque), montantlettre,
                    nums, qteFours, qteMoulins, qteDifs);
        }
        operationEtatReceptionReport.printReport();

    }

}
