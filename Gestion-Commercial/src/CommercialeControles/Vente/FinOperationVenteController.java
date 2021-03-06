package CommercialeControles.Vente;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.controlsfx.control.PopOver;

import com.gestionCommerciale.HibernateSchema.Facture;
import com.gestionCommerciale.HibernateSchema.Facture_Produit;
import com.gestionCommerciale.HibernateSchema.Payment;
import com.gestionCommerciale.HibernateSchema.Produit;
import com.gestionCommerciale.Models.AnneeQueries;
import com.gestionCommerciale.Models.ClientQueries;
import com.gestionCommerciale.Models.FactureQueries;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import CommercialeControles.Client.ClienCell;
import CommercialeControles.OperationAchat.CamionListeH;
import CommercialeControles.OperationAchat.ChauffeurListH;
import UIControle.Methode;
import UIControle.Notification;
import UIControle.ShowPane;
import UIControle.StageDialog;
import UIControle.ViewUrl;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class FinOperationVenteController implements Initializable {

    public static JFXTextField montant_static = new JFXTextField();
    static JFXTextField montantFinal_static = new JFXTextField();
    private static JFXTextField versement_static = new JFXTextField();
    private static JFXTextField solde_static = new JFXTextField();
    private static JFXTextField timbre_static = new JFXTextField();

    public static void calcule() {
        montantFinal_static.setText(Methode.DoubleFormat(getMontantFinale()) + "");
        solde_static.setText(Methode.DoubleFormat(ClientQueries.solde(OperationVenteController.client)) + "");
    }

    public static void calculeOnChange(JFXTextField field) {
        field.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                if (field.getText().isEmpty()) {
                    field.setText("0.00");
                    field.selectAll();
                }

                calcule();
            }
        });

    }

    static double getMontantFinale() {
        double mf = 0;
        for (int i = 0; i < OperationVenteController.produitselected.size(); i++) {
            mf = (OperationVenteController.produitselected.get(i).getProduit().getTTC()
                    * OperationVenteController.produitselected.get(i).getQuantite()) + mf;
        }
        mf += Double.parseDouble(timbre_static.getText());
        return mf;
    }

    @FXML
    private HBox extrat;
    @FXML
    private JFXTextField banque;
    @FXML
    private JFXTextField numeroCmpt;

    @FXML
    private JFXComboBox<String> versemetCombo;

    StageDialog dialog;

    @FXML
    private JFXTextField timbre;
    @FXML
    private JFXTextField montant;
    @FXML
    private JFXDatePicker dateOperation;
    @FXML
    private JFXTextField montantFinal;
    @FXML
    private JFXTextField solde;
    @FXML
    private JFXButton save;
    @FXML
    private JFXButton annuler;
    @FXML
    private ImageView clienticon;
    @FXML
    private ImageView chauffeuricon;
    @FXML
    private ImageView camionIcon;
    @FXML
    private ImageView produitIcon;
    @FXML
    private JFXTextField versement;

    PopOver popup;
    private Image view = new Image(getClass().getResourceAsStream("/icons/preview.png"));
    private Image viewHover = new Image(getClass().getResourceAsStream("/icons/previewGreen.png"));

    public void addFacture(ActionEvent event) {
        Date date = java.sql.Date.valueOf(dateOperation.getValue());
        double montantVal = Double.parseDouble(montant_static.getText());
        double montantFinalVal = Double.parseDouble(montant_static.getText());
        double versmentVal = Double.parseDouble(versement_static.getText());
        String typeValue = versemetCombo.getSelectionModel().getSelectedItem();
        
        Facture f = new Facture(date, montantVal, AnneeQueries.getSelected().getTva(), 0,typeValue);

        f.setMontantFinal(montantFinalVal);
        List<Facture_Produit> fpsList = new ArrayList<Facture_Produit>();
        for (int i = 0; i < OperationVenteController.produitselected.size(); i++) {
            Produit p = OperationVenteController.produitselected.get(i).getProduit();
            int qt = OperationVenteController.produitselected.get(i).getQuantite();
            double prix = OperationVenteController.produitselected.get(i).getProduit().getPrix();
            Facture_Produit fp = new Facture_Produit(qt, prix);
            fp.setProduit(p);
            fp.setFacture(f);
            fpsList.add(fp);
        }
        f.setQtes(fpsList);
        f.setAnnee(AnneeQueries.getSelected());
        // back
        String numCVValue = "";
        String banqueValue = "";
        
        if (typeValue == "Cheque" || typeValue == "virement") {
            numCVValue = numeroCmpt.getText()  ; 
            banqueValue = banque.getText()  ; 
        }

        Payment payment = new Payment(typeValue, numCVValue, banqueValue, versmentVal, date);
        payment.setClient(OperationVenteController.client);
        payment.setAnnee(AnneeQueries.getSelected());
        payment.setClient(OperationVenteController.client);
        // PaymentQueries.SaveOrUpdate(payment);
        f.setClient(OperationVenteController.client);
        f.setChauffeur(OperationVenteController.chauffeur);
        f.setCamion(OperationVenteController.camion);
        f.setMontantFinal(Double.parseDouble(montantFinal_static.getText()));
        f.setTimbre(Double.parseDouble(timbre_static.getText()));
        FactureQueries.insert(f, payment, fpsList);
        imprimer(f, event);
    }

    @FXML
    private void camionIN(MouseEvent event) {
        camionIcon.setImage(viewHover);
        CamionListeH ch = new CamionListeH(OperationVenteController.camion);
        popup.setContentNode(ch);
        popup.show(camionIcon);
    }

    @FXML
    private void camionOUT(MouseEvent event) {
        popup.hide();
        camionIcon.setImage(view);
    }

    @FXML
    private void chauffeurIN(MouseEvent event) {
        chauffeuricon.setImage(viewHover);
        ChauffeurListH ch = new ChauffeurListH(OperationVenteController.chauffeur);
        popup.setContentNode(ch);
        popup.show(chauffeuricon);
    }

    @FXML
    private void chauffeurOUT(MouseEvent event) {
        popup.hide();
        chauffeuricon.setImage(view);
    }

    @FXML
    private void clientIN(MouseEvent event) {
        clienticon.setImage(viewHover);
        ClienCell cell  = new  ClienCell(OperationVenteController.client) ; 
        popup.setContentNode(cell);
        popup.show(clienticon);
    }

    @FXML
    private void clientOUT(MouseEvent event) {
        popup.hide(); 
        clienticon.setImage(view);
    }

    public void imprimer(Facture f, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(ViewUrl.printvent));
            loader.load();
            PrintViewController print = loader.getController();
            print.setData(f);
            AnchorPane root = loader.getRoot();
            StageDialog dialog = new StageDialog(Methode.getStage(event), root);
            dialog.show();
        } catch (IOException ex) {
            Logger.getLogger(ClienCell.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Methode.setOnlyDouble(timbre, 4);
        Methode.setOnlyDouble(versement, 7);
        montantFinal.setEditable(false);
        montant.setEditable(false);
        solde.setEditable(false);
        montant_static = montant;
        setVersement();
        montantFinal_static = montantFinal;
        versement_static = versement;
        solde_static = solde;
        timbre_static = timbre;
        Methode.setSelectedMouseClick(versement);
        Methode.setZeroRemoved(versement);
        Methode.setSelectedMouseClick(timbre);
        Methode.setZeroRemoved(timbre);
        calculeOnChange(timbre);
        versement.setText("0.00");
        timbre.setText("0.00");
        dateOperation.setValue(LocalDate.now());
        montantFinal_static.setEditable(false);
        montant.setEditable(false);
        solde.setEditable(false);
        intpop();
    }

    private void intpop() {
        popup = new PopOver();
        popup.setCornerRadius(4);
        popup.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
    }

    @FXML
    private void produitIN(MouseEvent event) throws IOException {
        produitIcon.setImage(viewHover);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(ViewUrl.produitfacture));
        loader.load();

        ListeProduitVenteController Modifier = loader.getController();
        Modifier.setData2(OperationVenteController.produitselected);

        AnchorPane root = loader.getRoot();
       dialog = new StageDialog(Methode.getStageMouses(event), root) ; 
        dialog.show();
    }

    public void setVersement() {

        versemetCombo.getItems().add("Especes");
        versemetCombo.getItems().add("Cheque");
        versemetCombo.getItems().add("A terme");
        versemetCombo.getItems().add("Virement");

        versemetCombo.getSelectionModel().selectFirst();

    }

    @FXML
    private void produitOUT(MouseEvent event) {
        dialog.close();
        produitIcon.setImage(view);
    }

    @FXML
    private void quitter(ActionEvent event) throws IOException {
        new ShowPane().showVenteListe();
    }

    @FXML
    private void sauvgader(ActionEvent event) throws IOException {
        if (this.montant.getText().isEmpty() || this.montantFinal.getText().isEmpty()
                || this.versement.getText().isEmpty() || dateOperation.getValue().toString().isEmpty()) {
            // || dateOperation.getValue().toString().isEmpty()
            Notification.champVideNotification();
        } else {
            addFacture(event);
            Notification.Addnotification();
            quitter(event);
        }
    }

    @FXML
    private void typeEvent(ActionEvent event) {

        if (versemetCombo.getSelectionModel().getSelectedItem().equals("Cheque") || versemetCombo.getSelectionModel().getSelectedItem().equals("Virement")) {
            extrat.setVisible(true);
        } else {
            extrat.setVisible(false);
        }
    }

}
