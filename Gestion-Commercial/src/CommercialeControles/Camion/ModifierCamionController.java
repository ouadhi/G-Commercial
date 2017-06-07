package CommercialeControles.Camion;

import UIControle.Methode;
import UIControle.Notification;
import UIControle.ShowPane;
import com.gestionCommerciale.HibernateSchema.Camion;
import com.gestionCommerciale.Models.CamionQueries;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ModifierCamionController implements Initializable {

    @FXML
    private JFXTextField nomchauffeur;
    @FXML
    private JFXButton savebttn;
    @FXML
    private JFXButton anullerbttn;
    @FXML
    private Label savelabel;
    @FXML
    private ImageView close;
    @FXML
    private JFXTextField codecamion;
    @FXML
    private JFXTextField matricule;
    @FXML
    private JFXTextField taillecamion;
    @FXML
    private JFXTextField PoisCamion;
    Camion camion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Methode.setOnlyDouble(PoisCamion, 5);
        Methode.SetUpper(codecamion);
        Methode.SetUpper(matricule);
        Methode.setSelectedMouseClick(PoisCamion);
        Methode.setSelectedMouseClick(codecamion);
        Methode.setSelectedMouseClick(matricule);
        //Methode.setSelectedMouseClick(type);
        Methode.setZeroRemoved(PoisCamion);
    }

    @FXML
    private void sauvegarder(ActionEvent event) {
        String code = codecamion.getText();
        String matricule = this.matricule.getText();
        double poid = Double.parseDouble(PoisCamion.getText());
        //String marque = camion.getMarque();
        String marque = taillecamion.getText();
        Optional<ButtonType> result = Notification.updateAlert().showAndWait();
        if (result.get() == ButtonType.OK) {
            if (code.isEmpty() || matricule.isEmpty() || marque.isEmpty() || poid == 0) {
                Notification.champVideNotification();
            } else {
                Camion camion = ShowdDetailCamionController.getCamion();
                camion.setCodeCamion(code);
                camion.setMatricule(matricule);
                camion.setPoid(poid);
                camion.setMarque(marque);
                Camion c = CamionQueries.getCamionByCode(code);
                Camion c2 = CamionQueries.getCamionByMatricule(matricule);
                if (c != null && c.getId() != camion.getId()) {
                    Notification.error("Ce Code existe déja!");
                } else if (c2 != null && c2.getId() != camion.getId()) {
                    Notification.error("Cette matriquelle existe déja!");
                } else if (CamionQueries.SaveOrUpdate(camion)) {
                    CamionQueries.SaveOrUpdate(camion);
                    Notification.Updatenotification();
                    new ShowPane().showCamion();
                    savelabel.setVisible(true);
                    annuler(event);
                }
            }
        }
    }

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

    public void setData(Camion camion) {
        this.camion = camion;
        codecamion.setText(camion.getCodeCamion());
        matricule.setText(camion.getMatricule());
        PoisCamion.setText(Double.toString(camion.getPoid()));
        taillecamion.setText(camion.getMarque());
    }
}
