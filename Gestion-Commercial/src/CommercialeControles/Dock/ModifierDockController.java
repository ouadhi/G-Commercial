package CommercialeControles.Dock;

import UIControle.Methode;
import UIControle.Notification;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ModifierDockController implements Initializable {

    @FXML
    private ImageView close;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField wilaya;
    @FXML
    private JFXTextField distance;
    @FXML
    private JFXTextField prix;
    @FXML
    private JFXButton savebttn;
    @FXML
    private JFXButton cancelbttn;
    @FXML
    private Label savelabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Methode.setOnlyNumbre(prix);
        Methode.setOnlyNumbre(distance);
    }

    @FXML
    private void sauvgarder(ActionEvent event) {
        String nom = this.nom.getText();
        String wilaya = this.wilaya.getText();
        String distance = this.distance.getText();
        String prix = this.prix.getText();

        if (nom.isEmpty() || wilaya.isEmpty() || distance.isEmpty() || prix.isEmpty()) {

            Notification.champVideNotification();

        } else {
            Optional<ButtonType> result = Notification.deleteAlert().showAndWait();
            if (result.get() == ButtonType.OK) {

                // requete DELETE from client  Where  id.client  =  codeclient 
                Notification.Updatenotification();
                savelabel.setVisible(true);
            }

        }
    }

    @FXML
    private void quitter(ActionEvent event) {

        Stage currentStage = Methode.getStage(event);
        currentStage.close();

    }

    @FXML
    private void close(MouseEvent event) {
        Stage currentStage = Methode.getStageMouses(event);
        currentStage.close();
    }

    public void setData(String nom, String wilaya, String distance, String prix) {

        this.nom.setText(nom);
        this.wilaya.setText(wilaya);
        this.distance.setText(distance);
        this.prix.setText(prix);

    }
}
