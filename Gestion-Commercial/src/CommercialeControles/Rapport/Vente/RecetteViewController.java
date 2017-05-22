
package CommercialeControles.Rapport.Vente;

import UIControle.Methode;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;


public class RecetteViewController implements Initializable {

    @FXML
    private JFXDatePicker debut;
    @FXML
    private JFXComboBox<String> banque;
    @FXML
    private JFXTextField versement;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        for (int i = 0; i < 10; i++) {
            banque.getItems().add("Banque"+i) ; 
        }
        
        Methode.setOnlyFloat(versement, 10);
        
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
