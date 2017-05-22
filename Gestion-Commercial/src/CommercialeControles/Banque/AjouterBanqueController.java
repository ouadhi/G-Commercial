
package CommercialeControles.Banque;

import UIControle.Methode;
import UIControle.Notification;
import UIControle.ShowPane;
import UIControle.StageDialog;
import UIControle.ViewUrl;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


public class AjouterBanqueController implements Initializable {

    @FXML
    private JFXTextField nombanque;
    @FXML
    private JFXTextField adresse;
    @FXML
    private JFXTextField NumCompte;
    @FXML
    private JFXTextField telephone;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Methode.setOnlyInteger(NumCompte, 30);
        Methode.setOnlyInteger(NumCompte, 12);
      
    }    

    @FXML
    private void quitter(MouseEvent event) {
        Methode.getStageMouses(event).close(); 
    }

    @FXML
    private void save(ActionEvent event) {
        String nom = nombanque.getText()  ;  
        String adresse  =  this.adresse.getText()  ;  
        String  compte  =  NumCompte.getText()  ; 
        String tele =  telephone.getText()  ; 
        
        if (nom.isEmpty()|| adresse.isEmpty()||compte.isEmpty()|| tele.isEmpty()) {
             Notification.champVideNotification(); 
        } else {
            Notification.Addnotification(); 
            
            new ShowPane().showBanque();
            close(event);
        }
    }

    @FXML
    private void close(ActionEvent event) {
        Methode.getStage(event).close();
    }
    
}
