
package CommercialeControles.Rapport;

import UIControle.Methode;
import UIControle.ShowPane;
import UIControle.StageDialog;
import UIControle.ViewUrl;
import com.jfoenix.controls.JFXButton;
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

    @FXML
    private void ClientRapports(ActionEvent event) {
         try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(ViewUrl.clientRapport));
            StageDialog dialog = new StageDialog(Methode.getStage(event), pane);
            dialog.show();
        } catch (IOException ex) {
            Logger.getLogger(ShowPane.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
