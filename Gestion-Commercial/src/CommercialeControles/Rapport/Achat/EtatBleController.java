package CommercialeControles.Rapport.Achat;

import Report.EtatBleReport.GenerateEtatBleReport;
import UIControle.Methode;
import com.jfoenix.controls.JFXDatePicker;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import net.sf.jasperreports.engine.JRException;

public class EtatBleController implements Initializable {

    @FXML
    private JFXDatePicker datedebut;
    @FXML
    private JFXDatePicker datefin;

    @FXML
    private HBox Hbox;
    @FXML
    private MenuButton menu;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void quitter(MouseEvent event) {
        Methode.getStageMouses(event).close();
    }

    @FXML
    private void print(ActionEvent event) {
        try {
            GenerateEtatBleReport generateEtatBleReport = new GenerateEtatBleReport();
            generateEtatBleReport.generateReport(new Date());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void close(ActionEvent event) {
        Methode.getStage(event).close();
    }

    @FXML
    private void todaye(ActionEvent event) {
        Hbox.setVisible(false);
        menu.setText("Aujourd'huit");
    }

    @FXML
    private void intervale(ActionEvent event) {
        Hbox.setVisible(true);
        menu.setText("intervale");
    }

}
