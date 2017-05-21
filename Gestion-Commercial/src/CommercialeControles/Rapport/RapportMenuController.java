
package CommercialeControles.Rapport;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class RapportMenuController implements Initializable {

    @FXML
    private JFXButton home;
    @FXML
    private ImageView homeicon;
    @FXML
    private JFXButton Rapport;
    @FXML
    private ImageView facturicon1;
    @FXML
    private JFXButton Facture;
    @FXML
    private ImageView facturicon;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void outHome(MouseEvent event) {
    }

    @FXML
    private void inHome(MouseEvent event) {
    }

    @FXML
    private void showhome(ActionEvent event) {
    }

    @FXML
    private void InFacture(MouseEvent event) {
    }

    @FXML
    private void OutFacture(MouseEvent event) {
    }

    @FXML
    private void OutRapport(MouseEvent event) {
    }

    @FXML
    private void InRapport(MouseEvent event) {
    }

    @FXML
    private void showRapport(ActionEvent event) {
    }

    @FXML
    private void showfacture(ActionEvent event) {
    }
    
}
