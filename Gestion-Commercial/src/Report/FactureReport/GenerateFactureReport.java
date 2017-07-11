/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report.FactureReport;

import com.gestionCommerciale.HibernateSchema.Company;
import com.gestionCommerciale.Models.CompanyQueries;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRException;

/**
 * @author Hicham
 */
public class GenerateFactureReport {

    public void generateReport(String nom, String code, String address, String rc, String fiscal, String date,
            String numFacture, String article, String montantHT, String tva, String timbre, String ttc,
            String montantlettre, String chauffeur, String matricule, List<String> designationsVente,
            List<String> qtesVente, List<String> prixsVente, List<String> montantsVente, String payement)
            throws IOException, JRException {
        OperationFactuReport operationFactuReport = new OperationFactuReport();
        Company company = CompanyQueries.getCompany();

        for (int i = 0; i < designationsVente.size(); i++) {
            String prix = prixsVente.get(i);
            List<String> prixs = new ArrayList<>();
            prixs.add(prix);
            String montant = montantsVente.get(i);
            List<String> montants = new ArrayList<>();
            montants.add(montant);
            String designation = designationsVente.get(i);
            List<String> designations = new ArrayList<>();
            designations.add(designation);
            String qte = qtesVente.get(i);
            List<String> qtes = new ArrayList<>();
            qtes.add(qte);
            operationFactuReport.putReportInfo(nom, code, address, rc, fiscal, date, numFacture, article, montantHT,
                    tva, timbre, ttc, montantlettre, chauffeur, matricule, designations, qtes, prixs, montants, payement,
                    company.getRegistre(),company.getFiscale(),company.getArticle(),company.getTelephone(),
                    company.getFax(),company.getEmail(),company.getNom(),"");
        }
        operationFactuReport.printReport();

    }
}
