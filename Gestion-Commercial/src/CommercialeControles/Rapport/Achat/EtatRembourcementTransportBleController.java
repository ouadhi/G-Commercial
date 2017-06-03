
package CommercialeControles.Rapport.Achat;

import Report.ReportEtatRemboursement.GenerateEtatRemboursementReport;
import UIControle.Methode;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;


public class EtatRembourcementTransportBleController implements Initializable {

    @FXML
    private HBox Hbox;
    @FXML
    private JFXDatePicker datedebut;
    @FXML
    private JFXDatePicker datefin;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void quitter(MouseEvent event) {
        Methode.getStageMouses(event).close();
    }

    @FXML
    private void print(ActionEvent event) {
        
        GenerateEtatRemboursementReport generateEtatRemboursementReport = new GenerateEtatRemboursementReport();
        Date dateDebutOb = Date.from(datedebut.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date dateFinOb = Date.from(datefin.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        generateEtatRemboursementReport.generateReport(dateDebutOb, dateFinOb, "doit");
        
    }
    
    @FXML
    private void close(ActionEvent event) {
        Methode.getStage(event).close();
    }
    
}
