/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report.BonLivraisonReport;

import com.gestionCommerciale.HibernateSchema.Company;
import com.gestionCommerciale.Models.CompanyQueries;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author Hicham
 */
public class GenerateBonLivraisonReport {

    public void generateReport(String nom, String code, String address, String rc, String fiscal, String date,
            String numFacture, String article, String chauffeur, String matricule, List<String> designationsVente,
            List<String> qtesVente, List<String> typesVente) throws IOException, JRException {
        OperationBonLivraisonReport operationBonLivraisonReport = new OperationBonLivraisonReport();
        Company company = CompanyQueries.getCompany();

        for (int i = 0; i < designationsVente.size(); i++) {
            String designation = designationsVente.get(i);
            List<String> designations = new ArrayList<>();
            designations.add(designation);
            String qte = qtesVente.get(i);
            List<String> qtes = new ArrayList<>();
            qtes.add(qte);
            String type = typesVente.get(i);
            List<String> types = new ArrayList<>();
            types.add(type);
            operationBonLivraisonReport.putReportInfo(nom, code, address, rc, fiscal, date, numFacture, article,
                    chauffeur, matricule, designations, qtes, types,company.getRegistre(),company.getFiscale(),company.getArticle(),
                    company.getTelephone(),company.getFax(),company.getEmail(),company.getNom(),company.getAddress());

        }
        operationBonLivraisonReport.printReport();
    }
}
