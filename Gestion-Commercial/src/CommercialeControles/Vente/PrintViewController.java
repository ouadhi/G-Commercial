package CommercialeControles.Vente;

import Report.BonLivraisonReport.GenerateBonLivraisonReport;
import Report.FactureReport.GenerateFactureReport;
import UIControle.Methode;
import com.gestionCommerciale.HibernateSchema.Facture;
import com.ibm.icu.text.RuleBasedNumberFormat;
import com.jfoenix.controls.JFXCheckBox;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
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
                double ttc = factureimp.getMontant() * factureimp.getTva() + factureimp.getMontant();
                RuleBasedNumberFormat ruleBasedNumberFormat = new RuleBasedNumberFormat(new Locale("fr", "FR"),
                        RuleBasedNumberFormat.SPELLOUT);
                String montantlettre = ruleBasedNumberFormat.format(new Double(ttc)) + " Dinars Algérien";

                //get list produits, qte 
                List<String> designationsVente = new ArrayList<>();
                List<String> qtesVente = new ArrayList<>();
                List<String> prixsVente = new ArrayList<>();
                List<String> montantsVente = new ArrayList<>();
                for (int i = 0; i < factureimp.getQtes().size(); i++) {
                    designationsVente.add(factureimp.getQtes().get(i).getProduit().getNom());
                    qtesVente.add(String.valueOf(factureimp.getQtes().get(i).getQte_fact()));
                    prixsVente.add(String.valueOf(factureimp.getQtes().get(i).getProduit().getPrix()));
                    montantsVente.add(String.valueOf(factureimp.getQtes().get(i).getQte_fact()
                            * factureimp.getQtes().get(i).getProduit().getPrix()));
                }
                generateFactureReport.generateReport(factureimp.getClient().getName() + factureimp.getClient().getPrenom(),
                        String.valueOf(factureimp.getClient().getId()), factureimp.getClient().getAddressClient(), factureimp.getClient().getNumRegCom(),
                        factureimp.getClient().getnCarteFiscale(), new Date().toString(),
                        String.valueOf(factureimp.getIdFacture()), factureimp.getClient().getNumArticle(), String.valueOf(factureimp.getMontant()),
                        String.valueOf(factureimp.getTva()), "0.00", new Double(ttc).toString(), montantlettre,
                        factureimp.getChauffeur().getNomChauffeur() + factureimp.getChauffeur().getPrenomChauffeur(),
                        factureimp.getCamion().getMatricule(), designationsVente,
                        qtesVente, prixsVente, montantsVente);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (chregement.isSelected()) {

        }

        if (livraison.isSelected()) {
            try {
                GenerateBonLivraisonReport generateBonLivraisonReport= new GenerateBonLivraisonReport();
                

                //get list produits, qte 
                List<String> designationsVente = new ArrayList<>();
                List<String> qtesVente = new ArrayList<>();
                for (int i = 0; i < factureimp.getQtes().size(); i++) {
                    designationsVente.add(factureimp.getQtes().get(i).getProduit().getNom());
                    qtesVente.add(String.valueOf(factureimp.getQtes().get(i).getQte_fact()));
                }
               generateBonLivraisonReport.generateReport(
                        factureimp.getClient().getName() + factureimp.getClient().getPrenom(),
                        String.valueOf(factureimp.getClient().getId()), factureimp.getClient().getAddressClient()
                        , factureimp.getClient().getNumRegCom(),
                        factureimp.getClient().getnCarteFiscale(), new Date().toString(),
                        String.valueOf(factureimp.getIdFacture()), factureimp.getClient().getNumArticle()
                        , String.valueOf(factureimp.getMontant())
                        ,factureimp.getChauffeur().getNomChauffeur()+" "+factureimp.getChauffeur().getPrenomChauffeur()
                        , factureimp.getCamion().getMatricule(), designationsVente,
                        qtesVente);
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
