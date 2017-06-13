
package CommercialeControles.Rapport.Vente;

import Report.EtatEstimatifParClient.GenerationEtatEstimatifClient;
import UIControle.Methode;
import com.gestionCommerciale.HibernateSchema.Client;
import com.gestionCommerciale.Models.ClientQueries;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.net.URL;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.textfield.TextFields;


public class ClienntRapportController implements Initializable {

    @FXML
    private JFXDatePicker datedebut;
    @FXML
    private JFXDatePicker datefin;
    @FXML
    private JFXButton rapport2;
    @FXML
    private JFXTextField client;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    

    @FXML
    private void close(MouseEvent event) {
        Methode.getStageMouses(event).close();
    }

    @FXML
    private void rapport1(ActionEvent event) {
        GenerationEtatEstimatifClient generationEtatEstimatifClient = new GenerationEtatEstimatifClient();
        Date dateObDebut = Date.from(datedebut.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date dateObFin = Date.from(datefin.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        String nomEtPrenomClient= client.getText() ; 
        generationEtatEstimatifClient.generateReport(dateObDebut, dateObFin, nomEtPrenomClient);
    }
    
    
     public void possibleMot() {
         ArrayList<String>  liste  =  new ArrayList<>()   ; 
         for (Client client1 : ClientQueries.list()) {
             liste.add(client1.getName()+" "+client1.getPrenom()) ; 
         }
      
        TextFields.bindAutoCompletion(client, liste);
    }
}
