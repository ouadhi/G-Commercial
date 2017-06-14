/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report.EtatBleReport;

import com.gestionCommerciale.HibernateSchema.Achat;
import com.gestionCommerciale.Models.SessionsGenerator;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import org.hibernate.Session;

/**
 *
 * @author Hicham
 */
public class GenerateEtatBleReport {

    List<Achat> achatDuJour = new ArrayList<>();
    double totalPoidTiquet = 0;
    double totalPoid;
    double totalNet;
    double totalEcart;

    public void getAchatDuJour(Date jour) {

        List<Achat> list = new ArrayList<>();
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            list = new ArrayList<>();
            list = session.createQuery("from Achat").list();
            for (int i = 0; i < list.size(); i++) {
                if (jour.equals(list.get(i).getDateAcqt())) {
                    achatDuJour.add(list.get(i));
                }
            }
        } finally {
            session.close();
        }
    }

    public List<String> getPoidTiquet() {
        List<String> poidTiquet = new ArrayList<>();

        for (int i = 0; i < achatDuJour.size(); i++) {
            poidTiquet.add(String.valueOf(achatDuJour.get(i).getQuantiteAcqt()));
            totalPoidTiquet = totalPoidTiquet + achatDuJour.get(i).getQuantiteAcqt();
        }
        return poidTiquet;
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
        OperationEtatBleReport operationEtatBleReport = new OperationEtatBleReport();
        getAchatDuJour(jour);
        String jourOB = new SimpleDateFormat("dd-MM-yyyy").format(jour);
        
        for (int i = 0; i < achatDuJour.size(); i++) {
            List<String> poidTiquets = new ArrayList<>();
            totalPoid = totalPoid + achatDuJour.get(i).getQuantiteAcqt();
            String poidTiquet = String.valueOf(achatDuJour.get(i).getQuantiteAcqt());
            poidTiquets.add(poidTiquet);
            
            List<String> chauffeurs = new ArrayList<>();
            String chauffeur = String.valueOf(achatDuJour.get(i).getChauffeur().getNom() + " "
                    + achatDuJour.get(i).getChauffeur().getPrenom());
            chauffeurs.add(chauffeur);
            
            List<String> matricules = new ArrayList<>();
            String matricule = achatDuJour.get(i).getCamion().getMatricule();
            matricules.add(matricule);
            
            List<String> ptcs = new ArrayList<>();
            String ptc = String.valueOf(round(achatDuJour.get(i).getQuantiteFour()
                    + achatDuJour.get(i).getPoidCamion(), 2));
            ptcs.add(ptc);
            
            List<String> tares = new ArrayList<>();
            String tare = String.valueOf(round(achatDuJour.get(i).getPoidCamion(), 2));
            tares.add(tare);
            
            List<String> nets = new ArrayList<>();
            totalNet = totalNet + achatDuJour.get(i).getQuantiteFour();
            String net = String.valueOf(round(achatDuJour.get(i).getQuantiteFour(), 2));
            nets.add(net);
            
            List<String> ecarts = new ArrayList<>();
            double ecrt = achatDuJour.get(i).getQuantiteDiff();//
            totalEcart = totalEcart + ecrt;
            String ecart = String.valueOf(round(ecrt, 2));
            ecarts.add(ecart);
            
            List<String> numTiquets = new ArrayList<>();
            String numTiquet = String.valueOf(achatDuJour.get(i).getNumTiquet());
            numTiquets.add(numTiquet);
            
            List<String> numBls = new ArrayList<>();
            String numBl = String.valueOf(achatDuJour.get(i).getNumAcqt());
            numBls.add(numTiquet);
            
            operationEtatBleReport.putReportInfo(jourOB, String.valueOf(totalPoid),
                    String.valueOf(totalNet), String.valueOf(totalEcart), numBls,
                    numTiquets, poidTiquets, chauffeurs, matricules, ptcs, tares, nets, ecarts);
        }
        operationEtatBleReport.printReport();

    }

}
