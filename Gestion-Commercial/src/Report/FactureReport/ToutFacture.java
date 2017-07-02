/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report.FactureReport;

import static Report.FactureRemboursementBleReport.GenerateFactureRemboursementReport.round;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.gestionCommerciale.HibernateSchema.Facture;
import com.gestionCommerciale.HibernateSchema.Facture_Produit;
import com.gestionCommerciale.Models.Facture_ProduitQueries;
import com.ibm.icu.text.RuleBasedNumberFormat;
import java.text.SimpleDateFormat;

import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author Hicham
 */
public class ToutFacture {

    public static String transformationEnLettre(double montant) {
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

    public static JasperPrint ItererJaspoerPrint(Facture facture) {

        double montantTotal = 0;
        // get list produits, qte
        List<String> designationsVente = new ArrayList<>();
        List<String> qtesVente = new ArrayList<>();
        List<String> prixsVente = new ArrayList<>();
        List<String> montantsVente = new ArrayList<>();
        List<Facture_Produit> fpList = Facture_ProduitQueries.list(facture);

        for (int i = 0; i < fpList.size(); i++) {
            designationsVente.add(fpList.get(i).getProduit().getNom());
            qtesVente.add(String.valueOf(fpList.get(i).getQte_fact()));
            prixsVente.add(String.valueOf(fpList.get(i).getProduit().getPrix()));
            montantsVente.add(String.valueOf(fpList.get(i).getQte_fact() * fpList.get(i).getProduit().getPrix()));
            montantTotal = montantTotal + (fpList.get(i).getQte_fact() * fpList.get(i).getProduit().getPrix());
        }
        // double ttc = factureimp.getMontant() * ((factureimp.getTva() / 100) +
        // factureimp.getMontant());
        double ttc = (montantTotal * (facture.getTva() / 100)) + montantTotal;
        
        //String montantlettre = ruleBasedNumberFormat.format(new Double(ttc)) + " Dinars Algérien";
        String montantlettre = transformationEnLettre(ttc);   
        String date = new SimpleDateFormat("dd-MM-yyyy").format(facture.getDate());
        
        OperationFactuReport operationFactuReport = new OperationFactuReport();
        operationFactuReport.putReportInfo( facture.getClient().getName()+" "+ facture.getClient().getPrenom(),
                String.valueOf(facture.getClient().getTypeActivity()), facture.getClient().getAddressClient(),
                facture.getClient().getNumRegCom(), facture.getClient().getnCarteFiscale(),
                date, String.valueOf(facture.getIdFacture()),
                facture.getClient().getNumArticle(), String.valueOf(montantTotal), String.valueOf(facture.getTva()),
                String.valueOf(facture.getTimbre()), new Double(ttc).toString(), montantlettre,
                facture.getChauffeur().getNom() + facture.getChauffeur().getPrenom(),
                facture.getCamion().getMatricule(), designationsVente, qtesVente, prixsVente, montantsVente,
                "A terme");
        return operationFactuReport.getJasperPrint();
    }
}
