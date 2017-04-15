
package CommercialeControles.Client;

import UIControle.Methode;
import UIControle.Notification;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class ModifierClientController implements Initializable {

    @FXML
    private ImageView close;
    @FXML
    private JFXTextField nomtxt;
    @FXML
    private JFXTextField prenomtxt;
    @FXML
    private JFXTextField NRtxt;
    @FXML
    private JFXTextField NAtxt;
    @FXML
    private JFXTextField adressetxt;
    @FXML
    private JFXTextField activitetxt;
    @FXML
    private JFXTextField NCarteF;
    @FXML
    private DatePicker datedept;
    @FXML
    private JFXButton annuler;
    @FXML
    private Label savelabel;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void Sauvgarder(ActionEvent event) {
        
        String nom  = nomtxt.getText()  ; 
        String prenom  = prenomtxt.getText() ; 
        String NR  =  NRtxt.getText()  ; 
        String NA = NAtxt.getText()  ; 
        String adresse  = adressetxt.getText()  ; 
        String activite = activitetxt.getText() ; 
        String Ncarte =  NCarteF.getText() ; 
        
        if (nom.isEmpty()|| prenom.isEmpty() || NR.isEmpty() || NA.isEmpty() || adresse.isEmpty() || activite.isEmpty() ||Ncarte.isEmpty() ) {
            
            Notification.champVideNotification();
            
        } else {
            
            // requet  InsertInto Client 
            
            Notification.Addnotification();
            savelabel.setVisible(true);
            
        }
        
    }

    @FXML
    private void quitter(ActionEvent event) {
        
        Stage currentSatge = Methode.getStage(event) ; 
        
        currentSatge.close();
    }

    @FXML
    private void close(MouseEvent event) {
        
        Stage currentSatge = Methode.getStageMouses(event) ; 
        
        currentSatge.close();
    }
    
    
    public void SetData  (int id  , String nom , String prenom , String activite , String  Nregistre )  {
        
          
          // requete sql pour remplir  les  champs  vide  
          
          nomtxt.setText(nom);
          prenomtxt.setText(prenom);
          activitetxt.setText(activite);
          NRtxt.setText(Nregistre);
          
          
    }
    
}
