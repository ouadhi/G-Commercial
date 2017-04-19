package CommercialeControles.Camion;

import UIControle.Notification;
import UIControle.ShowPane;
import com.gestionCommerciale.HibernateSchema.Camion;
import com.gestionCommerciale.Models.CamionQueries;
import com.gestionCommerciale.Models.ChauffeurQueries;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
    private JFXComboBox<String> chauffeur;
    @FXML
    private JFXTextField taillecamion;
    
    private CamionQueries camionQueries= new CamionQueries();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void sauvegarder(ActionEvent event) {
        String code = codecamion.getText();
        String matricule = this.matricule.getText();
        String taille = taillecamion.getText();

        Optional<ButtonType> result = Notification.updateAlert().showAndWait();
        if (result.get() == ButtonType.OK) {
            if (code.isEmpty() || matricule.isEmpty() || taille.isEmpty()) {
                Notification.champVideNotification();

            } else {
                Camion camion= ShowdDetailCamionController.getCamion();
                camion.setCodeCamion(code);
                camion.setMatricule(matricule);
                //missing marque and type
                
                camionQueries.SaveOrUpdate(camion);
                Notification.Updatenotification();
                new ShowPane().showCamion();
                savelabel.setVisible(true);
                annuler(event);
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

    public void setData(String code, String matricule, float taille) {
        codecamion.setText(code);
        this.matricule.setText(matricule);
        taillecamion.setText(Float.toString(taille));
    }

}
