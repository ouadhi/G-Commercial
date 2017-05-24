package CommercialeControles.Vente;

import CommercialeControles.Client.ClienCell;
import CommercialeControles.OperationAchat.CamionListeH;
import CommercialeControles.OperationAchat.ChauffeurListH;
import UIControle.Methode;
import UIControle.Notification;
import UIControle.ShowPane;
import UIControle.StageDialog;
import UIControle.ViewUrl;
import com.gestionCommerciale.HibernateSchema.Facture;
import com.gestionCommerciale.HibernateSchema.Produit;
import com.gestionCommerciale.HibernateSchema.Facture_Produit;
import com.gestionCommerciale.HibernateSchema.Payment;
import com.gestionCommerciale.Models.FactureQueries;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.PopOver;

public class FinOperationVenteController implements Initializable {

    @FXML
    private JFXTextField montant;
    private static JFXTextField montant_static;
    @FXML
    private JFXDatePicker dateOperation;
    private JFXTextField tva;
    @FXML
    private JFXTextField montantFinal;
    @FXML
    private JFXTextField versement;
    private JFXTextField reste;
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
    PopOver popup;
    private Image view = new Image(getClass().getResourceAsStream("/icons/preview.png"));
    private Image viewHover = new Image(getClass().getResourceAsStream("/icons/previewGreen.png"));
    private static JFXTextField tva_static;
    private static JFXTextField montantFinal_static;
    private static JFXTextField versement_static;
    private static JFXTextField reste_static;
    @FXML
    private JFXTextField solde;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Methode.setOnlyFloat(montant, 10);
        Methode.setOnlyFloat(montantFinal, 10);
          Methode.setOnlyFloat(solde, 10);
        Methode.setOnlyFloat(versement, 10);
        Methode.setOnlyFloat(reste, 10);
        Methode.setOnlyFloat(tva, 2);
        tva.setText(17 + "");
        versement.setText("0.00");
        dateOperation.setValue(LocalDate.now());
        montant_static = montant;
        tva_static = tva;
        montantFinal_static = montantFinal;
        reste_static = reste;
        versement_static = versement;
        reste_static.setEditable(false);
        montantFinal_static.setEditable(false);
        montant_static.setEditable(false);
        intpop();
        versement_static.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                if (versement.getText().isEmpty()) {
                    versement.setText("0.00");
                    versement.selectAll();
                }
                calculeReste();
            }
        });
        versement_static.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                versement.selectAll();
            }
        });
        tva_static.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                calculeMontantFinal();
            }
        });
        tva_static.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                tva_static.selectAll();
            }
        });
    }

    public static void calculeMontantFinal() {
        DecimalFormat f = new DecimalFormat("##.00");
        Float montant_val = Float.parseFloat(montant_static.getText());
        Float tva_val = Float.parseFloat(tva_static.getText());
        Float montantFinal_val = montant_val + (montant_val * tva_val / 100);
        montantFinal_static.setText(floatFormat(montantFinal_val));
        versement_static.setText(floatFormat(montantFinal_val));
        reste_static.setText(floatFormat(0));
    }

    public static void calculeReste() {
        Float montantFinal_val = Float.parseFloat(montantFinal_static.getText());
        Float versement_val = Float.parseFloat(versement_static.getText());
        Float rest_val = montantFinal_val - versement_val;
        if (rest_val < 0) {
            reste_static.setText(floatFormat(0));
        } else {
            reste_static.setText(floatFormat(rest_val));
        }
    }

    public static String floatFormat(float f) {
        DecimalFormat df = new DecimalFormat("##.00");
        String value = df.format(f);
        if (value.startsWith(".")) {
            value = "0" + value;
        }
        return value;
    }

    @FXML
    private void sauvgader(ActionEvent event) throws IOException {
        if (this.montant.getText().isEmpty()
                || this.montantFinal.getText().isEmpty()
                || this.versement.getText().isEmpty()
                || dateOperation.getValue().toString().isEmpty()) {
            Notification.champVideNotification();
        } else {
            float montant_val = Float.parseFloat(this.montant.getText());
            float montantFinal_val = Float.parseFloat(this.montantFinal.getText());
            float versement_val = Float.parseFloat(this.versement_static.getText());
            float reste_val = Float.parseFloat(this.reste.getText());
            float tva_val = Float.parseFloat(this.tva.getText());
            System.err.println(montantFinal_val + "," + versement_val);
            if (versement_val > montantFinal_val) {
                Notification.error("Le versement est supérieur au montant final");
            } else {
                addFacture(event);
                Notification.Addnotification();
                quitter(event);
            }
        }
    }

    @FXML
    private void quitter(ActionEvent event) throws IOException {
        new ShowPane().showVenteListe();
    }

    public static void setmontantFacture() {
        float prix = 0;
        System.out.println("" + OperationVenteController.produitselected.size());
        for (int i = 0; i < OperationVenteController.produitselected.size(); i++) {
            prix = (OperationVenteController.produitselected.get(i).getProduit().getPrix() * OperationVenteController.produitselected.get(i).getQuantite()) + prix;
        }
        montant_static.setText(Float.toString(prix));
        calculeMontantFinal();
    }

    @FXML
    private void clientOUT(MouseEvent event) {
        clienticon.setImage(view);
    }

    @FXML
    private void clientIN(MouseEvent event) {
        clienticon.setImage(viewHover);
        popup.setContentNode(montant);
    }

    @FXML
    private void chauffeurOUT(MouseEvent event) {

        popup.hide();
        chauffeuricon.setImage(view);

    }

    @FXML
    private void chauffeurIN(MouseEvent event) {
        chauffeuricon.setImage(viewHover);
        ChauffeurListH ch = new ChauffeurListH(OperationVenteController.chauffeur);
        popup.setContentNode(ch);
        popup.show(chauffeuricon);
    }

    @FXML
    private void camionOUT(MouseEvent event) {
        popup.hide();
        camionIcon.setImage(view);
    }

    @FXML
    private void camionIN(MouseEvent event) {
        camionIcon.setImage(viewHover);
        CamionListeH ch = new CamionListeH(OperationVenteController.camion);
        popup.setContentNode(ch);
        popup.show(camionIcon);
    }

    @FXML
    private void produitOUT(MouseEvent event) {
        popup.hide();
        produitIcon.setImage(view);
    }

    @FXML
    private void produitIN(MouseEvent event) {
        produitIcon.setImage(viewHover);
        popup.setContentNode(OperationVenteController.produitselected.get(0));
        popup.show(produitIcon);
    }

    private void intpop() {
        popup = new PopOver();
        popup.setCornerRadius(4);
        popup.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
    }

    public void addFacture(ActionEvent event) {
        Date date = java.sql.Date.valueOf(dateOperation.getValue());
        double tva = Double.parseDouble(tva_static.getText());
        double montant = Double.parseDouble(montantFinal_static.getText());
        double versment = Double.parseDouble(versement_static.getText());
        Facture f = new Facture(date, montant, tva, 0);
        java.util.List<Facture_Produit> fpsList = new ArrayList<Facture_Produit>();
        for (int i = 0; i < OperationVenteController.produitselected.size(); i++) {
            Produit p = OperationVenteController.produitselected.get(i).getProduit();
            int qt = OperationVenteController.produitselected.get(i).getQuantite();
            Facture_Produit fp = new Facture_Produit(qt);
            fp.setProduit(p);
            fp.setFacture(f);
            fpsList.add(fp);
        }
        f.setQtes(fpsList);
        Payment payment = new Payment("", versment, date);
        payment.setFacture(f);
        java.util.List<Payment> PaymentsList = new ArrayList<Payment>();
        PaymentsList.add(payment);
        f.setPayments(PaymentsList);
        f.setClient(OperationVenteController.client);
        f.setChauffeur(OperationVenteController.chauffeur);
        f.setCamion(OperationVenteController.camion);
        FactureQueries fq = new FactureQueries();
        fq.SaveOrUpdate(f);
        imprimer(f, event);
        
        

    }
    
    
    public void imprimer(Facture f ,ActionEvent event ) {
        try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(ViewUrl.printvent));
                loader.load();
                
                PrintViewController print  =  loader.getController()  ; 
               print.setData(f);
                
                AnchorPane root = loader.getRoot();
                
                StageDialog dialog = new StageDialog(Methode.getStage(event), root) ;
                dialog.show();
            } catch (IOException ex) {
                Logger.getLogger(ClienCell.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
