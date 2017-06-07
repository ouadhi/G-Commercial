/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report.FactureRemboursementBleReport;

import com.gestionCommerciale.HibernateSchema.Achat;
import com.gestionCommerciale.HibernateSchema.Dock;
import com.gestionCommerciale.Models.SessionsGenerator;
import com.ibm.icu.text.RuleBasedNumberFormat;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.hibernate.Session;

/**
 *
 * @author Hicham
 */
public class GenerateFactureRemboursementReport {

    double montantTotal = 0;
    double totalQte = 0;
    List<Achat> listAchats = new ArrayList<>();
    List<String> dockNomList = new ArrayList<>();
    List<String> prixUnitair = new ArrayList<>();
    List<String> references = new ArrayList<>();

    public void achatParJour(Date startDate, Date lastDate) {
        List<Date> intervalDate = getDaysBetweenDates(startDate, lastDate);
        List<Achat> list = null;
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {
            list = new ArrayList<>();
            list = session.createQuery("from Achat").list();
            Set<String> dockNomSet = new HashSet<>();
            /*for (int i = 0; i < list.size(); i++) {
                dockNomSet.add(list.get(i).getDock().getNom());
            }*/
            System.out.println("----------interval dates" + intervalDate);
            for (int i = 0; i < intervalDate.size(); i++) {
                for (int j = 0; j < list.size(); j++) {
                    if (intervalDate.get(i).equals(list.get(j).getDateAcqt())) {
                        //calcule total montant
                        dockNomSet.add(list.get(j).getDock().getNom());
                        // montantTotal = montantTotal
                        //   + ((list.get(j).getQuantiteAcqt()) * (list.get(j).getDock().getPrixUnitTrans()));
                        this.listAchats.add(list.get(j));
                    }
                }
            }
            int counter = 1;
            for (String dockName : dockNomSet) {
                dockNomList.add(dockName);
                references.add("Ble " + counter);
                counter++;
            }
        } finally {
            session.close();
        }
        System.out.println("dock name list -----------" + dockNomList);
        System.out.println("--------------list achat" + listAchats);

    }

    public List<String> getQteParDock() {
        List<String> listTotalQte = new ArrayList<>();
        for (int i = 0; i < dockNomList.size(); i++) {
            double total = 0;
            for (int j = 0; j < listAchats.size(); j++) {
                if (listAchats.get(j).getDock().getNom().equals(dockNomList.get(i))) {
                    total = total + listAchats.get(j).getQuantiteAcqt();
                }
            }
            totalQte = totalQte + total;
            listTotalQte.add(new Double(total).toString());
        }
        return listTotalQte;
    }

    public List<String> getMontantParDock(List<String> listTotalQte) {

        List<String> listMontant = new ArrayList<>();
        for (int i = 0; i < dockNomList.size(); i++) {
            SessionsGenerator FactoryObject = new SessionsGenerator();
            Session session = FactoryObject.getFactory().openSession();
            Dock dock;
            try {
                dock = (Dock) session.createQuery("from Dock where nom='" + dockNomList.get(i) + "'").uniqueResult();
            } finally {
                session.close();
            }
            double prix = dock.getPrixUnitTrans();
            this.prixUnitair.add(new Double(prix).toString());
            double montantqtePrix = 0;
            //for (int j = 0; j < listTotalQte.size(); j++) {
               // montantqtePrix = round(montantqtePrix + (Double.parseDouble(listTotalQte.get(j)) * prix), 2);
            //}
            montantqtePrix = round((Double.parseDouble(listTotalQte.get(i)) * prix), 2);
            montantTotal = montantTotal + montantqtePrix;
            listMontant.add(new Double(montantqtePrix).toString());
        }
        return listMontant;
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

    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public void generateReport(Date startDate, Date endDate, String doit, String num) {
        //Date newStartDate = increment_decrementDays(false, startDate, 1);
        Date newFinDate = increment_decrementDays(true, endDate, 1);
        String start = new SimpleDateFormat("dd-MM-yyyy").format(startDate);
        String end = new SimpleDateFormat("dd-MM-yyyy").format(endDate);

        OperationFactureRemboursementReport operation = new OperationFactureRemboursementReport();
        achatParJour(startDate, newFinDate);
        List<String> qtes = getQteParDock();
        List<String> montants = getMontantParDock(qtes);

        RuleBasedNumberFormat ruleBasedNumberFormat = new RuleBasedNumberFormat(new Locale("fr", "FR"),
                RuleBasedNumberFormat.SPELLOUT);
        String montantlettre = ruleBasedNumberFormat.format(new Double(montantTotal)) + " Dinars Algérien";

        operation.putReportInfo(doit, num, start, end, String.valueOf(totalQte),
                String.valueOf(round(montantTotal, 2)), montantlettre, references, qtes, dockNomList,
                prixUnitair, montants);
        operation.printReport();

    }

}
