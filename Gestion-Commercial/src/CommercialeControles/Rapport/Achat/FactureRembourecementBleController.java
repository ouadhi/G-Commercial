package CommercialeControles.Rapport.Achat;

import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;

import Report.FactureRemboursementBleReport.GenerateFactureRemboursementReport;
import UIControle.Methode;
import com.gestionCommerciale.HibernateSchema.Dock;
import com.gestionCommerciale.Models.DockQueries;
import com.jfoenix.controls.JFXComboBox;
import java.util.List;
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
    @FXML
    private JFXComboBox<String> listedock;

    @FXML
    private void close(ActionEvent event) {
        Methode.getStage(event).close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setDocklist();

    }

    @FXML
    private void print(ActionEvent event) {
        
        GenerateFactureRemboursementReport generateFactureRemboursementReport = new GenerateFactureRemboursementReport();
        Date dateDebutOb = Date.from(datedebut.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date dateFinOb = Date.from(dateFin.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        String dock = listedock.getSelectionModel().getSelectedItem();
        generateFactureRemboursementReport.generateReport(dateDebutOb, dateFinOb, dock);
        close(event);
    }

    @FXML
    private void quitter(MouseEvent event) {
        Methode.getStageMouses(event).close();
    }

    public void setDocklist() {
        List<Dock> liste = DockQueries.list();
        for (Dock dock : liste) {
            listedock.getItems().add(dock.getNom());
        }

        listedock.getSelectionModel().selectFirst();

    }

}
