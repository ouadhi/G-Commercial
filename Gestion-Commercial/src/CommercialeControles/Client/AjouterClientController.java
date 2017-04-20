
package CommercialeControles.Client;

import UIControle.Methode;
import UIControle.Notification;
import UIControle.ShowPane;
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
import com.gestionCommerciale.HibernateSchema.Client;
import com.gestionCommerciale.HibernateSchema.User;
import com.gestionCommerciale.Models.ClientQueries;
import java.time.ZoneId;
import java.util.Date;



public class AjouterClientController implements Initializable {

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
    @FXML
    private ImageView close;
    ClientQueries clientQueries = new ClientQueries();

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
    }    

    @FXML
    private void Sauvgarder(ActionEvent event) {
        
        String nom  = nomtxt.getText()  ; 
        String codeClient  = "11"; 
        String prenom  = prenomtxt.getText() ; 
        String NR  =  NRtxt.getText()  ; 
        String NA = NAtxt.getText()  ; 
        String adresse  = adressetxt.getText()  ; 
        String activite = activitetxt.getText() ; 
        String Ncarte =  NCarteF.getText() ; 
        Date dateDepotDossier =  Date.from(datedept.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());  
        
        if (nom.isEmpty()|| prenom.isEmpty() || NR.isEmpty() || NA.isEmpty() || adresse.isEmpty() || activite.isEmpty() ||Ncarte.isEmpty() ) {
            
            Notification.champVideNotification();
            
        } else {
            
            if (false ) {
                //notification for user already exists
                Notification.errorNotificationUserExists();
            } else {
                // add user to database
                try {
                    Client client = new Client(nom, prenom,  NR, NA, adresse, activite, dateDepotDossier);
                    clientQueries.insererOuModifieClient(client);
                    Notification.Addnotification();
                    //new ShowPane().showClientList();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
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
    
}
