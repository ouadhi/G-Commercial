package CommercialeControles.Vente;

import CommercialeControles.OperationAchat.CamionListeH;
import CommercialeControles.OperationAchat.ChauffeurListH;
import UIControle.Methode;
import UIControle.Notification;
import UIControle.ViewUrl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.PopOver;

public class FinOperationVenteController implements Initializable {

    @FXML
    private JFXTextField montant;
    private static JFXTextField montant_static;
    @FXML
    private JFXDatePicker dateOperation;
    @FXML
    private JFXTextField tva;
    @FXML
    private JFXTextField montantFinal;
    @FXML
    private JFXTextField versement;
    @FXML
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
    PopOver popup   ; 
    private Image view = new Image(getClass().getResourceAsStream("/icons/preview.png"));
    private Image viewHover = new Image(getClass().getResourceAsStream("/icons/previewGreen.png"));
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Methode.setOnlyFloat(montant, 10);
        Methode.setOnlyFloat(montantFinal, 10);
        Methode.setOnlyFloat(versement, 10);
        Methode.setOnlyFloat(reste, 10);
        Methode.setOnlyFloat(tva, 2);

        dateOperation.setTime(LocalTime.now());
        montant_static = montant;
        intpop();

    }

    @FXML
    private void sauvgader(ActionEvent event) throws IOException {

        if (this.montant.getText().isEmpty() || this.montantFinal.getText().isEmpty() || this.versement.getText().isEmpty() || dateOperation.getTime().toString().isEmpty()) {
            Notification.champVideNotification();
        } else {
            float montant_val = Float.parseFloat(this.montant.getText());
            float montantFinal_val = Float.parseFloat(this.montantFinal.getText());
            float versement_val = Float.parseFloat(this.versement.getText());
            float reste_val = Float.parseFloat(this.reste.getText());
            float tva_val = Float.parseFloat(this.tva.getText());

            Notification.Addnotification();
            quitter(event);
        }
    }

    @FXML
    private void quitter(ActionEvent event) throws IOException {
        AnchorPane etapeClient = FXMLLoader.load(getClass().getResource(ViewUrl.selectClient));
        OperationVenteController.staticpane.getChildren().setAll(etapeClient);
    }

    public static void setmontantFacture() {
        float prix = 0;

        System.out.println("" + OperationVenteController.produitselected.size());

        for (int i = 0; i < OperationVenteController.produitselected.size(); i++) {

            prix = (OperationVenteController.produitselected.get(i).getProduit().getPrix() * OperationVenteController.produitselected.get(i).getQuantite()) + prix;

        }

        montant_static.setText(Float.toString(prix));
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
        ChauffeurListH ch = new ChauffeurListH(OperationVenteController.chauffeur) ; 
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
        CamionListeH ch = new CamionListeH(OperationVenteController.camion) ; 
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
    
    
    private  void intpop ()  {
        popup  = new  PopOver()  ; 
        popup.setCornerRadius(4);
        popup.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
    }

}
