
package CommercialeControles.Camion;

import UIControle.Notification;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class AjouterCamionController implements Initializable {

    @FXML
    private JFXButton savebttn;
    @FXML
    private JFXButton anullerbttn;
    @FXML
    private Label savelabel;
    @FXML
    private ImageView close;
    @FXML
    private JFXTextField codecamion;
    @FXML
    private JFXTextField matricule;
    @FXML
    private JFXTextField typecamion;
    @FXML
    private JFXComboBox<String> chauffeur;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void sauvegarder(ActionEvent event) {
        String code  = codecamion.getText()  ; 
        String matricule  = this.matricule.getText() ; 
        String type  = typecamion.getText()  ;  
        
        if (code.isEmpty()|| matricule.isEmpty()|| type.isEmpty()) {
            Notification.champVideNotification();
            
        } else {
            
            Notification.Addnotification();
            savelabel.setVisible(true);
        }
    }

    @FXML
    private void annuler(ActionEvent event) {
        
       Stage stage = (Stage) ((Node)(event.getSource())).getScene().getWindow() ; 
       stage.close();
    }

    @FXML
    private void closewindow(MouseEvent event) {
        Stage stage = (Stage) ((Node)(event.getSource())).getScene().getWindow() ; 
       stage.close();
    }
    
}
