package CommercialeControles.Chauffeur;

import UIControle.Notification;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AjouterChauffeurViewController implements Initializable {

    @FXML
    private JFXTextField nomchauffeur;
    @FXML
    private JFXTextField prenomchauffeur;
    @FXML
    private Label camion;
    @FXML
    private JFXTextField codechauffeur;
    @FXML
    private JFXTextField telchauffeur;
    @FXML
    private JFXTextField typechauffeur;
    @FXML
    private JFXButton savebttn;
    @FXML
    private JFXButton anullerbttn;
    @FXML
    private Label savelabel;
    @FXML
    private ImageView close;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void sauvegarder(ActionEvent event) {

        String nom = nomchauffeur.getText();
        String prenom = prenomchauffeur.getText();
        String codech = codechauffeur.getText();
        String tel = telchauffeur.getText();
        String type = typechauffeur.getText();

        if (nom.isEmpty() || prenom.isEmpty() || codech.isEmpty() || tel.isEmpty() || type.isEmpty()) {

            Notification.champVideNotification();
        } else {

            Notification.Addnotification();
            savelabel.setVisible(true);
        }

    }

    @FXML
    private void annuler(ActionEvent event) {
        Stage g = (Stage) ((Node) event.getSource()).getScene().getWindow();
        g.close();
    }

    @FXML
    private void closewindow(MouseEvent event) {

        Stage g = (Stage) ((Node) event.getSource()).getScene().getWindow();
        g.close();

    }

}
