package CommercialeControles.Chauffeur;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gestionCommerciale.HibernateSchema.Chauffeur;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import CommercialeControles.Camion.ShowdDetailCamionController;
import UIControle.Methode;
import UIControle.Transition;
import UIControle.ViewUrl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class ShowChauffeurController implements Initializable {

    private static Chauffeur chauffeur;

    public static Chauffeur getChauffeur() {
        return chauffeur;
    }

    public static void setChauffeur(Chauffeur chauffeur) {
        ShowChauffeurController.chauffeur = chauffeur;
    }
    @FXML
    private JFXButton precedent;
    @FXML
    private JFXButton suivant;

    @FXML
    private AnchorPane PaneMain;

    private JFXListView<ChauffeurCell> liste;

    private int i = 0;

    public AnchorPane getChauffeur(int id) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(ViewUrl.modifierChauffeur));
            loader.load();

            ChauffeurCell chauffeurCell = liste.getItems().get(id);
            chauffeur = chauffeurCell.getChauffeur();

            ModificationChauffeurController modification = loader.getController();
            try {

                modification.setData(chauffeurCell.getChauffeur());
            } catch (Exception ex) {

                ex.printStackTrace();
            }

            AnchorPane pane = loader.getRoot();
            Methode.moveFocus(pane);

            return pane;
        } catch (IOException ex) {
            Logger.getLogger(ShowdDetailCamionController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void precedent(ActionEvent event) {
        if (i > 0) {
            --i;

            Transition transition = new Transition(PaneMain, getChauffeur(i), 2000, 0, -2000);
            transition.show();

            if (i == 0) {
                precedent.setDisable(true);
            } else {
                suivant.setDisable(false);
                precedent.setDisable(false);
            }
        }

    }

    public void setListechauffeur(JFXListView<ChauffeurCell> liste, int idItemSelceted) {
        this.liste = liste;
        i = idItemSelceted;
        PaneMain.getChildren().setAll(getChauffeur(i));
    }

    @FXML
    private void suivant(ActionEvent event) {
        if (i < liste.getItems().size()) {
            i++;
            Transition transition = new Transition(PaneMain, getChauffeur(i), -2000, 0, 2000);
            transition.show();

            if (i == liste.getItems().size() - 1) {
                suivant.setDisable(true);
            } else {
                suivant.setDisable(false);
                precedent.setDisable(false);
            }
        }

    }

}
