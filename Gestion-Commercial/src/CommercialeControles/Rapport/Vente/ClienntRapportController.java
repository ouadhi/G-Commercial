package CommercialeControles.Rapport.Vente;

import Report.EtatEstimatifParClient.GenerationEtatEstimatifClient;
import UIControle.Methode;
import com.gestionCommerciale.HibernateSchema.Client;
import com.gestionCommerciale.Models.ClientQueries;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

public class ClienntRapportController implements Initializable {

    @FXML
    private JFXDatePicker datedebut;
    @FXML
    private JFXDatePicker datefin;
    @FXML
    private JFXButton rapport2;
    @FXML
    private JFXComboBox<String> client;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (Client c : ClientQueries.list()) {
            this.client.getItems().add(c.getName() + " " + c.getPrenom());

        }

        rapport2.setOnAction(event -> {
            GenerationEtatEstimatifClient generationEtatEstimatifClient = new GenerationEtatEstimatifClient();
            Date dateObDebut = Date.from(datedebut.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date dateObFin = Date.from(datefin.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            String nomEtPrenomClient = client.getSelectionModel().getSelectedItem();
            generationEtatEstimatifClient.generateReportGeneral(dateObDebut, dateObFin, nomEtPrenomClient);

        });

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
        String nomEtPrenomClient = client.getSelectionModel().getSelectedItem();
        generationEtatEstimatifClient.generateReport(dateObDebut, dateObFin, nomEtPrenomClient);
    }

}
