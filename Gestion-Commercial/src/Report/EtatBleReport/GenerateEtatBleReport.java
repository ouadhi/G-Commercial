/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report.EtatBleReport;

import com.gestionCommerciale.HibernateSchema.Achat;
import com.gestionCommerciale.Models.SessionsGenerator;
import java.io.IOException;
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
            poidTiquet.add(String.valueOf(achatDuJour.get(i).getQuantiteFour()));
            totalPoidTiquet = totalPoidTiquet + achatDuJour.get(i).getQuantiteFour();
        }
        return poidTiquet;
    }

    public void generateReport(Date jour) throws IOException, JRException {
        OperationEtatBleReport operationEtatBleReport = new OperationEtatBleReport();
        getAchatDuJour(jour);
        System.out.println(achatDuJour.get(0).getQuantiteAcqt());
        for (int i = 0; i < achatDuJour.size(); i++) {
            List<String> poidTiquets = new ArrayList<>();
            totalPoid = totalPoid + achatDuJour.get(i).getQuantiteFour();
            String poidTiquet = String.valueOf(achatDuJour.get(i).getQuantiteFour());
            poidTiquets.add(poidTiquet);
            List<String> chauffeurs = new ArrayList<>();
            String chauffeur = String.valueOf(achatDuJour.get(i).getChauffeur());
            chauffeurs.add(chauffeur);
            List<String> matricules = new ArrayList<>();
            String matricule = achatDuJour.get(i).getCamion().getMatricule();
            matricules.add(matricule);
            List<String> ptcs = new ArrayList<>();
            String ptc = String.valueOf(achatDuJour.get(i).getQuantiteAcqt() 
                    + achatDuJour.get(i).getCamion().getPoid());
            ptcs.add(ptc);
            List<String> tares = new ArrayList<>();
            String tare = String.valueOf(achatDuJour.get(i).getCamion().getPoid());
            tares.add(tare);
            List<String> nets = new ArrayList<>();
            totalNet = totalNet + achatDuJour.get(i).getQuantiteAcqt();
            String net = String.valueOf(achatDuJour.get(i).getQuantiteAcqt());
            nets.add(net);
            List<String> ecarts = new ArrayList<>();
            double ecrt = achatDuJour.get(i).getQuantiteAcqt() - achatDuJour.get(i).getQuantiteFour();
            totalEcart = totalEcart + ecrt;
            String ecart = String.valueOf(ecrt);
            ecarts.add(ecart);
            List<String> numTiquets = new ArrayList<>();
            String numTiquet = String.valueOf(achatDuJour.get(i).getNumTiquet());
            numTiquets.add(numTiquet);
            List<String> numBls = new ArrayList<>();
            String numBl = String.valueOf(achatDuJour.get(i).getNumBon());
            numBls.add(numTiquet);
            operationEtatBleReport.putReportInfo(jour.toString(), String.valueOf(totalPoid),
                     String.valueOf(totalNet), String.valueOf(totalEcart), numBls,
                    numTiquets, poidTiquets, chauffeurs, matricules, ptcs, tares, nets, ecarts);
        }
        operationEtatBleReport.printReport();
     
    }

}
