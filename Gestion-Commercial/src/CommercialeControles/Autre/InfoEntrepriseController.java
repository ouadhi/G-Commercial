
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


public class InfoEntrepriseController implements Initializable {

    @FXML
    private JFXTextField Nregistre;
    @FXML
    private JFXTextField IdFiscal;
    @FXML
    private JFXTextField article;
    @FXML
    private JFXTextField telephone;
    @FXML
    private JFXTextField fax;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField nom;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Methode.setOnlyNumbre(fax);
        Methode.setOnlyNumbre(telephone);
        
    }    

    @FXML
    private void close(MouseEvent event) {
         Methode.getStageMouses(event).close();  
    }

    @FXML
    private void save(ActionEvent event) {
        
        String registre  =  Nregistre.getText()  ; 
        String fiscale  = IdFiscal.getText()  ; 
        String articl   =  article.getText()  ; 
        String tel  =  telephone.getText()   ;  
        String fax =  this.fax.getText()   ; 
        String mail   =  email.getText()   ; 
        String nom  =  this.nom.getText()   ;  
        
        Notification.Addnotification();   
        
    }

    @FXML
    private void quitter(ActionEvent event) {
        Methode.getStage(event).close();  
    }
    
}
