package CommercialeControles.Rapport.Vente;

import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.gestionCommerciale.Models.BanqueQueries;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import Report.EtatRecetteDepenseReport.GenerateEtatRecetteDepense;
import UIControle.Methode;
import UIControle.Notification;
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

    @FXML
    private void close(ActionEvent event) {
        Methode.getStage(event).close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        BanqueQueries querie = new BanqueQueries();
        List<String> listDB = querie.listNomBanque();
        for (int i = 0; i < listDB.size(); i++) {
            banque.getItems().add(listDB.get(i));
        }

        Methode.setOnlyDouble(versement, 10);

    }

    @FXML
    private void print(ActionEvent event) {
        try {

            GenerateEtatRecetteDepense generateEtatRecetteDepense = new GenerateEtatRecetteDepense();
            Date dateOb = Date.from(debut.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            generateEtatRecetteDepense.generateReport(dateOb, banque.getSelectionModel().getSelectedItem(),
                    Double.parseDouble(versement.getText()));
            System.gc(); 

        } catch (Exception ex) {
            if (banque.getSelectionModel().getSelectedItem() == null) {
                Notification.error("Choisir une banque");
            } else {
                Notification.error("Il n ya pas de versement a ce jour.");
                ex.printStackTrace();
            }
        }

    }

    @FXML
    private void quitter(MouseEvent event) {
        Methode.getStageMouses(event).close();
    }

}
