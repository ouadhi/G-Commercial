
package CommercialeControles.Ble;

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


public class AjouterBleController implements Initializable {

    @FXML
    private ImageView close;
    @FXML
    private JFXButton addbttn;
    @FXML
    private JFXButton cancelbttn;
    @FXML
    private JFXTextField code;
    @FXML
    private JFXTextField quntite;
    @FXML
    private JFXTextField prix;
    @FXML
    private Label savelabel;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         Methode.setOnlyNumbre(quntite);
         Methode.setOnlyNumbre(prix);
       
    }    

    @FXML
    private void close(MouseEvent event) {
        Stage  stage  = Methode.getStageMouses(event) ; 
        stage.close();
    }

    @FXML
    private void saveble(ActionEvent event) {
        
        String  codeval  =  this.code.getText() ; 
        String  quantiteval   =  this.quntite.getText()   ; 
        String  prixval  =  this.prix.getText()  ; 
        
        if (codeval.isEmpty() || quantiteval.isEmpty() || prixval.isEmpty()) {
                
                  Notification.champVideNotification();
            
        } else {
            
              // requete `insertINto Ble
              
               Notification.Addnotification();
               savelabel.setVisible(true);
        }
        
        
    }

    @FXML
    private void closestage(ActionEvent event) {
        
        Methode.getStage(event).close();
    }
    
}
