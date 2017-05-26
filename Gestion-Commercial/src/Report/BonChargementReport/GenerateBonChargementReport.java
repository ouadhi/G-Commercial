/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report.BonChargementReport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author Hicham
 */
public class GenerateBonChargementReport {

    public void generateReport(String nom, String code, String address,
            String date, String numFacture, List<String> designationsVente,
             List<String> qtesVente) throws IOException, JRException {
        OperationBonChargementReport operationBonChargementReport = new OperationBonChargementReport();
        for (int i = 0; i < designationsVente.size(); i++) {
            String designation = designationsVente.get(i);
            List<String> designations = new ArrayList<>();
            designations.add(designation);
            String qte = qtesVente.get(i);
            List<String> qtes = new ArrayList<>();
            qtes.add(qte);
            operationBonChargementReport.putReportInfo(nom, date, numFacture, code, address, designations, qtes);

        }
        operationBonChargementReport.printReport();
    }

}
