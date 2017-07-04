package CommercialeControles.Vente;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import com.gestionCommerciale.HibernateSchema.Facture;
import com.gestionCommerciale.HibernateSchema.Facture_Produit;
import com.gestionCommerciale.Models.Facture_ProduitQueries;
import com.ibm.icu.text.RuleBasedNumberFormat;
import com.jfoenix.controls.JFXCheckBox;
import Report.BonChargementReport.GenerateBonChargementReport;
import Report.BonLivraisonReport.GenerateBonLivraisonReport;
import static Report.FactureRemboursementBleReport.GenerateFactureRemboursementReport.round;
import Report.FactureReport.GenerateFactureReport;
import UIControle.Methode;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class PrintViewController implements Initializable {

    @FXML
    private JFXCheckBox facture;
    @FXML
    private JFXCheckBox chregement;
    @FXML
    private JFXCheckBox livraison;

    private Facture factureimp;
    @FXML
    private Text numerofacture;

    @FXML
    private void close(ActionEvent event) {
        Methode.getStage(event).close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void print(ActionEvent event) {
        if (facture.isSelected()) {
            // print facture
            try {
                GenerateFactureReport generateFactureReport = new GenerateFactureReport();
                double montantTotal = factureimp.getMontant();
                // get list produits, qte
                List<String> designationsVente = new ArrayList<>();
                List<String> qtesVente = new ArrayList<>();
                List<String> prixsVente = new ArrayList<>();
                List<String> montantsVente = new ArrayList<>();
                List<Facture_Produit> fpList = Facture_ProduitQueries.list(factureimp);

                for (int i = 0; i < fpList.size(); i++) {
                    designationsVente.add(fpList.get(i).getProduit().getNom());
                    qtesVente.add(String.valueOf(fpList.get(i).getQte_fact()));
                    prixsVente.add(String.valueOf(fpList.get(i).getProduit().getPrix()));
                    montantsVente
                            .add(String.valueOf(fpList.get(i).getQte_fact() * fpList.get(i).getProduit().getPrix()));
                    // montantTotal = montantTotal +
                    // (fpList.get(i).getQte_fact()
                    // * fpList.get(i).getProduit().getPrix());
                }
                // double ttc = factureimp.getMontant() * ((factureimp.getTva()
                // / 100) + factureimp.getMontant());
                double ttc = factureimp.getMontantFinal();
                double tva = ttc - (montantTotal+factureimp.getTimbre());

                String montantlettre = transformationEnLettre(ttc);
                String date = new SimpleDateFormat("dd-MM-yyyy").format(factureimp.getDate());

                generateFactureReport.generateReport(
                        factureimp.getClient().getName() + " " + factureimp.getClient().getPrenom(),
                        String.valueOf(factureimp.getClient().getTypeActivity()),
                        factureimp.getClient().getAddressClient(), factureimp.getClient().getNumRegCom(),
                        factureimp.getClient().getnCarteFiscale(), date,
                        String.valueOf(factureimp.getIdFacture()), factureimp.getClient().getNumArticle(),
                        String.valueOf(round(montantTotal,2)), String.valueOf(round(tva,2))
                        ,String.valueOf(round(factureimp.getTimbre(),2)),
                        new Double(ttc).toString(), montantlettre,
                        factureimp.getChauffeur().getNom() +" "+ factureimp.getChauffeur().getPrenom(),
                        factureimp.getCamion().getMatricule(), designationsVente, qtesVente, prixsVente, montantsVente,
                        "A terme");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (chregement.isSelected()) {
            try {
                GenerateBonChargementReport generateBonChargementReport = new GenerateBonChargementReport();

                // get list produits, qte
                List<String> designationsVente = new ArrayList<>();
                List<String> qtesVente = new ArrayList<>();
                List<Facture_Produit> fpList = Facture_ProduitQueries.list(factureimp);
                String date = new SimpleDateFormat("dd-MM-yyyy").format(factureimp.getDate());

                for (int i = 0; i < fpList.size(); i++) {
                    designationsVente.add(fpList.get(i).getProduit().getNom());
                    qtesVente.add(String.valueOf(fpList.get(i).getQte_fact()));
                }
                generateBonChargementReport.generateReport(String.valueOf(factureimp.getIdFacture()),
                        date,
                        factureimp.getClient().getName() + " " + factureimp.getClient().getPrenom(),
                        factureimp.getClient().getTypeActivity(), factureimp.getClient().getAddressClient(),
                        factureimp.getClient().getNumRegCom(), factureimp.getClient().getnCarteFiscale(),
                        factureimp.getClient().getNumArticle(), designationsVente, qtesVente);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

        if (livraison.isSelected()) {
            try {
                GenerateBonLivraisonReport generateBonLivraisonReport = new GenerateBonLivraisonReport();

                // get list produits, qte
                List<String> designationsVente = new ArrayList<>();
                List<String> qtesVente = new ArrayList<>();
                List<String> typesVente = new ArrayList<>();
                List<Facture_Produit> fpList = Facture_ProduitQueries.list(factureimp);
                String date = new SimpleDateFormat("dd-MM-yyyy").format(factureimp.getDate());

                for (int i = 0; i < fpList.size(); i++) {
                    designationsVente.add(fpList.get(i).getProduit().getNom());
                    qtesVente.add(String.valueOf(fpList.get(i).getQte_fact()));
                    typesVente.add(fpList.get(i).getProduit().getCategory());
                }
                generateBonLivraisonReport.generateReport(
                        factureimp.getClient().getName() + " " + factureimp.getClient().getPrenom(),
                        factureimp.getClient().getTypeActivity(), factureimp.getClient().getAddressClient(),
                        factureimp.getClient().getNumRegCom(), factureimp.getClient().getnCarteFiscale(),
                        date, String.valueOf(factureimp.getIdFacture()),
                        factureimp.getClient().getNumArticle(),
                        factureimp.getChauffeur().getNom() + " " + factureimp.getChauffeur().getPrenom(),
                        factureimp.getCamion().getMatricule(), designationsVente, qtesVente, typesVente);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

    }

    @FXML
    private void quitter(MouseEvent event) {
        Methode.getStageMouses(event).close();
    }

    public void setData(Facture factureimp) {

        this.factureimp = factureimp;
        numerofacture.setText("" + factureimp.getIdFacture());

    }

    public String transformationEnLettre(double montant) {
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
    

}
