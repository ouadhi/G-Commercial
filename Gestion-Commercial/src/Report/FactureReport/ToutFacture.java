/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report.FactureReport;

import com.gestionCommerciale.HibernateSchema.Facture;
import com.ibm.icu.text.RuleBasedNumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author Hicham
 */
public class ToutFacture {

    public static JasperPrint ItererJaspoerPrint(Facture facture) {

        double montantTotal = 0;
        //get list produits, qte 
        List<String> designationsVente = new ArrayList<>();
        List<String> qtesVente = new ArrayList<>();
        List<String> prixsVente = new ArrayList<>();
        List<String> montantsVente = new ArrayList<>();
        List<String> typesVente = new ArrayList<>();

        for (int i = 0; i < facture.getQtes().size(); i++) {
            designationsVente.add(facture.getQtes().get(i).getProduit().getNom());
            qtesVente.add(String.valueOf(facture.getQtes().get(i).getQte_fact()));
            prixsVente.add(String.valueOf(facture.getQtes().get(i).getProduit().getPrix()));
            typesVente.add(String.valueOf(facture.getQtes().get(i).getProduit().getCategory()));
            montantsVente.add(String.valueOf(facture.getQtes().get(i).getQte_fact()
                    * facture.getQtes().get(i).getProduit().getPrix()));
            montantTotal = montantTotal + (facture.getQtes().get(i).getQte_fact()
                    * facture.getQtes().get(i).getProduit().getPrix());
        }
        //double ttc = factureimp.getMontant() * ((factureimp.getTva() / 100) + factureimp.getMontant());
        double ttc = (montantTotal * (facture.getTva() / 100)) + montantTotal;
        RuleBasedNumberFormat ruleBasedNumberFormat = new RuleBasedNumberFormat(new Locale("fr", "FR"),
                RuleBasedNumberFormat.SPELLOUT);
        String montantlettre = ruleBasedNumberFormat.format(new Double(ttc)) + " Dinars Algérien";

        OperationFactuReport operationFactuReport = new OperationFactuReport();
        operationFactuReport.putReportInfo(facture.getClient().getPrenom() + " " + facture.getClient().getName(),
                        String.valueOf(facture.getClient().getTypeActivity()), facture.getClient().getAddressClient(),
                        facture.getClient().getNumRegCom(),
                        facture.getClient().getnCarteFiscale(), facture.getDate().toString(),
                        String.valueOf(facture.getIdFacture()), facture.getClient().getNumArticle(), String.valueOf(montantTotal),
                        String.valueOf(facture.getTva()), "0.00", new Double(ttc).toString(), montantlettre,
                        facture.getChauffeur().getNom() + facture.getChauffeur().getPrenom(),
                        facture.getCamion().getMatricule(), designationsVente,
                        qtesVente, prixsVente, montantsVente, typesVente);
        return operationFactuReport.getJasperPrint();

    }
}
