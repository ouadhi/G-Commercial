
package CommercialeControles.Rapport.Vente;

import UIControle.Methode;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;


public class ExpeditionController implements Initializable {

    @FXML
    private JFXDatePicker date;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void quitter(MouseEvent event) {
        Methode.getStageMouses(event).close();
    }

    @FXML
    private void print(ActionEvent event) {
    }

    @FXML
    private void close(ActionEvent event) {
        Methode.getStage(event).close();
    }
    
}
