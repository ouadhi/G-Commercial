
package CommercialeControles.Rapport;

import UIControle.ShowPane;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


public class AllReportController implements Initializable {


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void recetteprint(ActionEvent event) {
        new ShowPane().showRecette(event);
    }

    @FXML
    private void etatdeble(ActionEvent event) {
        new ShowPane().EtatdeBle(event);
    }

    @FXML
    private void etatReception(ActionEvent event) {
        new  ShowPane().EtatdeReception(event);
    }

    @FXML
    private void rembourecemebtBle(ActionEvent event) {
         new  ShowPane().EtatRembourcementBle(event);
    }

    @FXML
    private void RembourecementTransport(ActionEvent event) {
        
        new  ShowPane().EtatRembourcementTransport(event);
    }

    @FXML
    private void showExpedition(ActionEvent event) {
        new ShowPane().EtatExpedition(event);
    }
    
}
