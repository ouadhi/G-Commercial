
package CommercialeControles.Rapport.Achat;

import Report.EtatReceptionReport.GenerateEtatReceptionReport;
import UIControle.Methode;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;


public class EtatDeRecepetionController implements Initializable {

    @FXML
    private JFXDatePicker datedebut;
    @FXML
    private JFXDatePicker dateFin;
    @FXML
    private JFXTextField val;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void quitter(MouseEvent event) {
        Methode.getStageMouses(event).close();
    }    
    @FXML
    private void print(ActionEvent event) {
        GenerateEtatReceptionReport generateEtatReceptionReport= new GenerateEtatReceptionReport();
        Date dateDebutOb = Date.from(datedebut.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date dateFinOb = Date.from(dateFin.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());        
        generateEtatReceptionReport.generateReport(dateDebutOb ,dateFinOb , 0);
    }

    @FXML
    private void close(ActionEvent event) {
        Methode.getStage(event).close();
    }
    
}
