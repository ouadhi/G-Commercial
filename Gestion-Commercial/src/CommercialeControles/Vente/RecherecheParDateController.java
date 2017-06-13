package CommercialeControles.Vente;

import UIControle.Methode;
import com.gestionCommerciale.HibernateSchema.Facture;
import com.gestionCommerciale.Models.FactureQueries;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

public class RecherecheParDateController implements Initializable {

    @FXML
    private JFXListView<VenteCell> listevente;
    @FXML
    private JFXDatePicker dtpStart;
    @FXML
    private JFXDatePicker dtpEnd;
    List<Facture> factureList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dtpStart.setValue(LocalDate.now());
        dtpEnd.setValue(LocalDate.now());
        getFactures();
    }

    @FXML
    private void valider(ActionEvent event) {
        getFactures();

    }

    private void getFactures() {
        Date start = Date.from(dtpStart.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(dtpEnd.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        factureList = FactureQueries.getFactureByDates(start, end);
        List<VenteCell> list = new ArrayList<>();
        for (int i = 0; i < factureList.size(); i++) {
            list.add(new VenteCell(factureList.get(i)));
        }

        ObservableList<VenteCell> myObservableList = FXCollections.observableList(list);
        listevente.setItems(myObservableList);
        listevente.setExpanded(true);

    }

    @FXML
    private void print(ActionEvent event) {
    }

    @FXML
    private void close(MouseEvent event) {
        Methode.getStageMouses(event).close();
    }

}
