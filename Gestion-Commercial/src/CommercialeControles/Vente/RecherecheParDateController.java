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
            List<Facture> factures = FactureQueries.list();

            List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
            for (int i = 0; i < factures.size(); i++) {
                jasperPrints.add(ToutFacture.ItererJaspoerPrint(factures.get(i)));
                System.out.println("------------- facture: " + factures.get(i).getClient().getPrenom());

            }
// Your code to get Jasperreport objects

            JRPdfExporter exporter = new JRPdfExporter();
//Create new FileOutputStream or you can use Http Servlet Response.getOutputStream() to get Servlet output stream
// Or if you want bytes create ByteArrayOutputStream
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrints);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
            exporter.exportReport();
            //byte[] bytes = out.toByteArray();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    private void close(MouseEvent event) {
        Methode.getStageMouses(event).close();
    }

}
