package CommercialeControles.Camion;

import CommercialeControles.Chauffeur.ChauffeurCell;
import UIControle.Transition;
import UIControle.ViewUrl;
import com.gestionCommerciale.HibernateSchema.Camion;
import com.gestionCommerciale.Models.CamionQueries;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class ShowdDetailCamionController implements Initializable {

    @FXML
    private JFXButton precedent;
    @FXML
    private JFXButton suivant;
    @FXML
    private AnchorPane PaneMain;
    private JFXListView<CamionCell> liste;
    private int i = 0;

    private static Camion camion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void suivant(ActionEvent event) {
        if (i < liste.getItems().size()) {
            i++;
            Transition transition = new Transition(PaneMain, getcamion(i), -2000, 0, 2000);
            transition.show();
            if (i == liste.getItems().size() - 1) {
                suivant.setDisable(true);
            } else {
                suivant.setDisable(false);
                precedent.setDisable(false);
            }
        }
    }

    @FXML
    private void precedent(ActionEvent event) {
        if (i > 0) {
            --i;
            Transition transition = new Transition(PaneMain, getcamion(i), 2000, 0, -2000);
            transition.show();
            if (i == 0) {
                precedent.setDisable(true);
            } else {
                suivant.setDisable(false);
                precedent.setDisable(false);
            }
        }
    }

    public AnchorPane getcamion(int id) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(ViewUrl.modifierCamion));
            loader.load();
            CamionCell camionCell = liste.getItems().get(i);
            ModifierCamionController modification = loader.getController();
            //modification.setData(camion.id, camion.marque, camion.matricule, camion.taille);
            modification.setData(camion);
            AnchorPane pane = loader.getRoot();

            return pane;
        } catch (IOException ex) {
            Logger.getLogger(ShowdDetailCamionController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void setListechauffeur(JFXListView<CamionCell> liste, int idItemSelceted) {
        this.liste = liste;
        i = idItemSelceted;
        PaneMain.getChildren().setAll(getcamion(i));
    }

    public static Camion getCamion() {
        return camion;
    }

    public static void setCamion(Camion camion) {
        ShowdDetailCamionController.camion = camion;
    }

}
