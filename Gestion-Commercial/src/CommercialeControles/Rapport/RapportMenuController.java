
package CommercialeControles.Rapport;

import UIControle.ShowPane;
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
    private JFXButton Facture;
    @FXML
    private ImageView rapporticon;
    @FXML
    private ImageView staticon;
    @FXML
    private ImageView banqueicon;

    
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
        new  ShowPane().showHome(event);
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
        new ShowPane().showRapport();
    }


    @FXML
    private void showbanque(ActionEvent event) {
        new ShowPane().showBanque();
    }

    @FXML
    private void showStatistique(ActionEvent event) {
        new ShowPane().showStatistique();
    }
    
}
