package CommercialeControles.Vente;

import Report.FactureReport.ToutFacture;
import UIControle.Methode;
import com.gestionCommerciale.HibernateSchema.Facture;
import com.gestionCommerciale.Models.FactureQueries;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

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
        try {
            System.out.println("facture part executes");
            //List<Facture> factures = FactureQueries.list();

            List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
            for (int i = 0; i < factureList.size(); i++) {
                jasperPrints.add(ToutFacture.ItererJaspoerPrint(factureList.get(i)));
                System.out.println("------------- facture: " + factureList.get(i).getClient().getPrenom());

            }
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrints)); //Set as export input my list with JasperPrint s
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(System.getProperty("user.home") + "/Desktop"+"/out.pdf" )); //or any other out streaam
            SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
            configuration.setCreatingBatchModeBookmarks(true); //add this so your bookmarks work, you may set other parameters
            exporter.setConfiguration(configuration);
            exporter.exportReport();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    private void close(MouseEvent event) {
        Methode.getStageMouses(event).close();
    }

}
