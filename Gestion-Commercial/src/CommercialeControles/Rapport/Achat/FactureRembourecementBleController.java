
package CommercialeControles.Rapport.Achat;

import Report.FactureRemboursementBleReport.GenerateFactureRemboursementReport;
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


public class FactureRembourecementBleController implements Initializable {

    @FXML
    private JFXDatePicker datedebut;
    @FXML
    private JFXDatePicker dateFin;
    @FXML
    private HBox Hbox;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    

    @FXML
    private void quitter(MouseEvent event) {
        Methode.getStageMouses(event).close();
    }

    @FXML
    private void close(ActionEvent event) {
        Methode.getStage(event).close();
    }

    @FXML
    private void print(ActionEvent event) {
        GenerateFactureRemboursementReport generateFactureRemboursementReport= new GenerateFactureRemboursementReport();
        Date dateDebutOb = Date.from(datedebut.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date dateFinOb = Date.from(dateFin.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        generateFactureRemboursementReport.generateReport(dateFinOb, dateFinOb, "", "");
    }
    
}
