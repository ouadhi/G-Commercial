package CommercialeControles.Vente;

import CommercialeControles.OperationAchat.FinOperationController;
import UIControle.Notification;
import UIControle.ViewUrl;
import com.gestionCommerciale.HibernateSchema.Camion;
import com.gestionCommerciale.HibernateSchema.Chauffeur;
import com.gestionCommerciale.HibernateSchema.Client;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class OperationVenteController implements Initializable {

    @FXML
    private JFXTextField recherchetxt;
    private AnchorPane workespace;
    @FXML
    private AnchorPane space;
    public static AnchorPane staticpane;
    @FXML
    private JFXButton precedent;
    @FXML
    private JFXButton next;

    public static Client client = null;
    public static Camion camion = null;
    public static Chauffeur chauffeur = null;
    public static ArrayList<PorduitH> produitselected =new ArrayList<>()  ;  ;
    

    private AnchorPane etapeClient, etapeChauffeur, etapeCamion, etapePrdouit, etapeInformation;

    private int etape;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            staticpane = space;
            etapeClient = FXMLLoader.load(getClass().getResource(ViewUrl.selectClient));
            etapeChauffeur = FXMLLoader.load(getClass().getResource(ViewUrl.selectChauffeurVente));
            etapeCamion = FXMLLoader.load(getClass().getResource(ViewUrl.selectCamionVente));
            etapePrdouit = FXMLLoader.load(getClass().getResource(ViewUrl.selectProduit));
            etapeInformation = FXMLLoader.load(getClass().getResource(ViewUrl.infotmationVente));
            space.getChildren().setAll(etapeClient);
            etape = 1;
        } catch (Exception e) {
        }

    }

    @FXML
    private void rechercher(KeyEvent event) {
    }

    @FXML
    private void etapprecedent(ActionEvent event) {
        if (etape >= 2) {
            --etape;
            quelleEtape(etape);
        }
    }

    @FXML
    private void nextetape(ActionEvent event) {
        if (this.etape <= 5) {
            etape++;
            quelleEtape(etape);
        }

    }

    private void quelleEtape(int etape) {
        switch (etape) {
            case 1:
                showEtape(etapeClient);
                break;

            case 2:
                if (client == null) {
                    Notification.champVideNotification();
                    --this.etape;
                } else {
                    showEtape(etapeChauffeur);
                }

                break;

            case 3:
                if (chauffeur == null) {
                    Notification.champVideNotification();
                    --this.etape;
                } else {
                    showEtape(etapeCamion);
                }

                break;

            case 4:
                if (camion == null) {
                    Notification.champVideNotification();
                    --this.etape;
                } else {
                    showEtape(etapePrdouit);
                }
                break;

            default:
                if (OperationVenteController.produitselected.isEmpty()) {
                    Notification.champVideNotification();
                    --this.etape;
                } else {
                    FinOperationVenteController.setmontantFacture();
                    showEtape(etapeInformation);
                   
                }

                break;
        }
    }

    private void showEtape(AnchorPane pane) {
        space.getChildren().setAll(pane);
    }

}
