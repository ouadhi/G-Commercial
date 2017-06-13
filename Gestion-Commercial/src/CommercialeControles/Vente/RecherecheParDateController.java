package CommercialeControles.Vente;

import Report.FactureReport.OperationFactuReport;
import Report.FactureReport.ToutFacture;
import UIControle.Methode;
import com.gestionCommerciale.HibernateSchema.Facture;
import com.gestionCommerciale.Models.FactureQueries;
import com.jfoenix.controls.JFXListView;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;

public class RecherecheParDateController implements Initializable {

    @FXML
    private JFXListView<?> listevente;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void valider(ActionEvent event) {

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
