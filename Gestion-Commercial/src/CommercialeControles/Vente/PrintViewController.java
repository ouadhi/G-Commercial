package CommercialeControles.Vente;

import Report.BonChargementReport.GenerateBonChargementReport;
import Report.BonLivraisonReport.GenerateBonLivraisonReport;
import Report.FactureReport.GenerateFactureReport;
import UIControle.Methode;
import com.gestionCommerciale.HibernateSchema.Facture;
import com.gestionCommerciale.HibernateSchema.Facture_Produit;
import com.gestionCommerciale.Models.Facture_ProduitQueries;
import com.ibm.icu.text.RuleBasedNumberFormat;
import com.jfoenix.controls.JFXCheckBox;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void quitter(MouseEvent event) {
        Methode.getStageMouses(event).close();
    }

    @FXML
    private void print(ActionEvent event) {
        if (facture.isSelected()) {
            //print facture
            try {
                GenerateFactureReport generateFactureReport = new GenerateFactureReport();
                double montantTotal = 0;
                //get list produits, qte 
                List<String> designationsVente = new ArrayList<>();
                List<String> qtesVente = new ArrayList<>();
                List<String> prixsVente = new ArrayList<>();
                List<String> montantsVente = new ArrayList<>();
                List<String> typesVente = new ArrayList<>();
        List<Facture_Produit> fpList = Facture_ProduitQueries.list(factureimp);

                for (int i = 0; i < fpList.size(); i++) {
                    designationsVente.add(fpList.get(i).getProduit().getNom());
                    qtesVente.add(String.valueOf(fpList.get(i).getQte_fact()));
                    prixsVente.add(String.valueOf(fpList.get(i).getProduit().getPrix()));
                    typesVente.add(String.valueOf(fpList.get(i).getProduit().getCategory()));
                    montantsVente.add(String.valueOf(fpList.get(i).getQte_fact()
                            * fpList.get(i).getProduit().getPrix()));
                    montantTotal = montantTotal + (fpList.get(i).getQte_fact()
                            * fpList.get(i).getProduit().getPrix());
                }
                //double ttc = factureimp.getMontant() * ((factureimp.getTva() / 100) + factureimp.getMontant());
                double ttc = (montantTotal * (factureimp.getTva() / 100)) + montantTotal;
                RuleBasedNumberFormat ruleBasedNumberFormat = new RuleBasedNumberFormat(new Locale("fr", "FR"),
                        RuleBasedNumberFormat.SPELLOUT);
                String montantlettre = ruleBasedNumberFormat.format(new Double(ttc)) + " Dinars Algérien";

                generateFactureReport.generateReport(factureimp.getClient().getPrenom() + " " + factureimp.getClient().getName(),
                        String.valueOf(factureimp.getClient().getTypeActivity()), factureimp.getClient().getAddressClient(), factureimp.getClient().getNumRegCom(),
                        factureimp.getClient().getnCarteFiscale(), factureimp.getDate().toString(),
                        String.valueOf(factureimp.getIdFacture()), factureimp.getClient().getNumArticle(), String.valueOf(montantTotal),
                        String.valueOf(factureimp.getTva()), "0.00", new Double(ttc).toString(), montantlettre,
                        factureimp.getChauffeur().getNom() + factureimp.getChauffeur().getPrenom(),
                        factureimp.getCamion().getMatricule(), designationsVente,
                        qtesVente, prixsVente, montantsVente, typesVente);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (chregement.isSelected()) {
            try {
                GenerateBonChargementReport generateBonChargementReport = new GenerateBonChargementReport();

                //get list produits, qte 
                List<String> designationsVente = new ArrayList<>();
                List<String> qtesVente = new ArrayList<>();
        List<Facture_Produit> fpList = Facture_ProduitQueries.list(factureimp);
                
                for (int i = 0; i < fpList.size(); i++) {
                    designationsVente.add(fpList.get(i).getProduit().getNom());
                    qtesVente.add(String.valueOf(fpList.get(i).getQte_fact()));
                }
                generateBonChargementReport.generateReport(String.valueOf(factureimp.getClient().getId()),
                        factureimp.getDate().toString(), factureimp.getClient().getPrenom() + " " + factureimp.getClient().getName(),
                        factureimp.getClient().getTypeActivity(), factureimp.getClient().getAddressClient(), factureimp.getClient().getNumRegCom(),
                        factureimp.getClient().getnCarteFiscale(), factureimp.getClient().getNumArticle(),
                        designationsVente, qtesVente);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

        if (livraison.isSelected()) {
            try {
                GenerateBonLivraisonReport generateBonLivraisonReport = new GenerateBonLivraisonReport();

                //get list produits, qte 
                List<String> designationsVente = new ArrayList<>();
                List<String> qtesVente = new ArrayList<>();
                List<String> typesVente = new ArrayList<>();
        List<Facture_Produit> fpList = Facture_ProduitQueries.list(factureimp);

                for (int i = 0; i < fpList.size(); i++) {
                    designationsVente.add(fpList.get(i).getProduit().getNom());
                    qtesVente.add(String.valueOf(fpList.get(i).getQte_fact()));
                    typesVente.add(fpList.get(i).getProduit().getCategory());
                }
                generateBonLivraisonReport.generateReport(
                        factureimp.getClient().getPrenom() + " " + factureimp.getClient().getName(),
                        factureimp.getClient().getTypeActivity(), factureimp.getClient().getAddressClient(),
                        factureimp.getClient().getNumRegCom(),
                        factureimp.getClient().getnCarteFiscale(), factureimp.getDate().toString(),
                        String.valueOf(factureimp.getIdFacture()), factureimp.getClient().getNumArticle(),
                        factureimp.getChauffeur().getNom() + " " + factureimp.getChauffeur().getPrenom(),
                        factureimp.getCamion().getMatricule(), designationsVente,
                        qtesVente, typesVente);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

    }

    @FXML
    private void close(ActionEvent event) {
        Methode.getStage(event).close();
    }

    public void setData(Facture factureimp) {

        this.factureimp = factureimp;
        numerofacture.setText("" + factureimp.getIdFacture());

    }

}
