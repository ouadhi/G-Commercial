
package CommercialeControles.Rapport.Vente;

import Report.EtatRecetteDepenseReport.GenerateEtatRecetteDepense;
import UIControle.Methode;
import com.gestionCommerciale.Models.BanqueQueries;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
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
        
        BanqueQueries querie  = new  BanqueQueries() ; 
        List<String>listDB= querie.list();
        for (int i = 0; i < listDB.size(); i++) {
            banque.getItems().add(listDB.get(i)) ; 
        }
        
        Methode.setOnlyDouble(versement, 10);
        
    }    

    @FXML
    private void quitter(MouseEvent event) {
        Methode.getStageMouses(event).close();  
    }

    @FXML
    private void print(ActionEvent event) {
        GenerateEtatRecetteDepense generateEtatRecetteDepense= new GenerateEtatRecetteDepense();
        
        
    }

    @FXML
    private void close(ActionEvent event) {
        Methode.getStage(event).close();
    }
    
}
