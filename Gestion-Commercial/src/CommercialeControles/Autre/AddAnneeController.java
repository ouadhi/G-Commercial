
package CommercialeControles.Autre;

import UIControle.Methode;
import UIControle.Notification;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;


public class AddAnneeController implements Initializable {

    @FXML
    private JFXTextField annee;
    @FXML
    private JFXTextField tva;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void close(MouseEvent event) {
        Methode.getStageMouses(event).close();
    }

    @FXML
    private void save(ActionEvent event) {
        if (annee.getText().isEmpty() || tva.getText().isEmpty()) {
            Notification.champVideNotification();
        }else {
           Notification.Addnotification();
        }
    }

    @FXML
    private void quitter(ActionEvent event) {
        Methode.getStage(event).close();
    }
    
}
