
package CommercialeControles.Vente;

import UIControle.Methode;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;


public class RecherecheParDateController implements Initializable {

    @FXML
    private JFXListView<?> listevente;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void valider(ActionEvent event) {
    }

    @FXML
    private void print(ActionEvent event) {
    }

    @FXML
    private void close(MouseEvent event) {
        Methode.getStageMouses(event).close();
    }
    
}
