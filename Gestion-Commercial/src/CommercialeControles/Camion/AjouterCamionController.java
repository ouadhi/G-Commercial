package CommercialeControles.Camion;

import java.net.URL;
import java.util.ResourceBundle;

import com.gestionCommerciale.HibernateSchema.Camion;
import com.gestionCommerciale.Models.CamionQueries;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import UIControle.Methode;
import UIControle.Notification;
import UIControle.ShowPane;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AjouterCamionController implements Initializable {

    @FXML
    private JFXButton savebttn;
    @FXML
    private JFXButton anullerbttn;
    @FXML
    private ImageView close;
    @FXML
    private JFXTextField codecamion;
    @FXML
    private JFXTextField matricule;
    @FXML
    private JFXTextField typecamion;
    @FXML
    private JFXTextField poisCamion;
    @FXML
    private Label labelsave;
    @FXML
    private JFXComboBox<String> typec;

    @FXML
    private void annuler(ActionEvent event) {

        Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void closewindow(MouseEvent event) {
        Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Methode.setOnlyDouble(poisCamion, 5);
        Methode.SetUpper(codecamion, 10);
        Methode.SetUpper(matricule, 12);
        Methode.setSelectedMouseClick(poisCamion);
        Methode.setSelectedMouseClick(codecamion);
        Methode.setSelectedMouseClick(matricule);
        Methode.setSelectedMouseClick(typecamion);
        Methode.setZeroRemoved(poisCamion);

        poisCamion.setText("0.00");
        Methode.setsizeString(typecamion, 32);
        setType();

    }

    public void setType() {
        typec.getItems().add("INTERNE");
        typec.getItems().add("EXTERNE");
        typec.getSelectionModel().selectFirst(); 
    }

    @FXML
    private void sauvegarder(ActionEvent event) {
        String code = codecamion.getText();
        String matricule = this.matricule.getText();
        String marque = typecamion.getText();
        String type = typec.getSelectionModel().getSelectedItem();

        // Double poid = Double.parseDouble(poisCamion.getText());
        if (code.isEmpty() || matricule.isEmpty() || marque.isEmpty()) {
            Notification.champVideNotification();
        } else {
            if (CamionQueries.getCamionByMatricule(matricule) != null) {
                Notification.error("Cette matricule exist deja!");
            } else if (CamionQueries.getCamionByCode(code) != null) {
                Notification.error("Ce code exist deja!");
            } else {
                try {
                   
                    Camion camion = new Camion(code, matricule, marque, type);
                    camion.setDeleted(false);
                    CamionQueries.SaveOrUpdate(camion);
                    new ShowPane().showCamion();
                    Notification.Addnotification();
                    Methode.getStage(event).close();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

}
