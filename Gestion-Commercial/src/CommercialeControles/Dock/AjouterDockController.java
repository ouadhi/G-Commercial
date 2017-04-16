
package CommercialeControles.Dock;

import UIControle.Methode;
import UIControle.Notification;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class AjouterDockController implements Initializable {

    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField wilaya;
    @FXML
    private JFXTextField distance;
    @FXML
    private JFXTextField prix;
    @FXML
    private JFXButton savebttn;
    @FXML
    private JFXButton cancelbttn;
    @FXML
    private Label savelabel;
    @FXML
    private ImageView close;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Methode.setOnlyNumbre(prix);
        Methode.setOnlyNumbre(distance);
    }    

    @FXML
    private void sauvgarder(ActionEvent event) {
        String nom  = this.nom.getText() ; 
        String wilaya  =  this.wilaya.getText() ; 
        String distance  =  this.distance.getText() ; 
        String prix = this.prix.getText() ; 
        
        if (nom.isEmpty() || wilaya.isEmpty() || distance.isEmpty() || prix.isEmpty() ) {
            
            Notification.champVideNotification();  
            
        } else {
            
            Notification.Addnotification(); 
            savelabel.setVisible(true);
        }
    }

    @FXML
    private void quitter(ActionEvent event) {
        
        Stage currentStage = Methode.getStage(event) ; 
        currentStage.close();
        
    }

    @FXML
    private void close(MouseEvent event) {
        Stage currentStage = Methode.getStageMouses(event) ; 
        currentStage.close();
    }
    
}
