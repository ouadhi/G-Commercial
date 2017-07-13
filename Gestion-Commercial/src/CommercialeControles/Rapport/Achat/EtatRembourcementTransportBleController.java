package CommercialeControles.Rapport.Achat;

import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;

import Report.ReportEtatRemboursement.GenerateEtatRemboursementReport;
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

public class EtatRembourcementTransportBleController implements Initializable {

    @FXML
    private HBox Hbox;
    @FXML
    private JFXDatePicker datedebut;
    @FXML
    private JFXDatePicker datefin;
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

        GenerateEtatRemboursementReport generateEtatRemboursementReport = new GenerateEtatRemboursementReport();
        Date dateDebutOb = Date.from(datedebut.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date dateFinOb = Date.from(datefin.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        String dock = listedock.getSelectionModel().getSelectedItem();
        generateEtatRemboursementReport.generateReport(dateDebutOb, dateFinOb, dock);
        System.gc();

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
