
package CommercialeControles.Rapport.Vente;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;

import Report.EtatExpeditionReport.GenerateEtatExpeditionReport;
import UIControle.Methode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import net.sf.jasperreports.engine.JRException;

public class ExpeditionController implements Initializable {

	@FXML
	private JFXDatePicker date;

	@FXML
	private void close(ActionEvent event) {
		Methode.getStage(event).close();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

	@FXML
	private void print(ActionEvent event) throws IOException, JRException {
		// code
		GenerateEtatExpeditionReport generateEtatExpeditionReport = new GenerateEtatExpeditionReport();
		Date dateOb = Date.from(date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
		generateEtatExpeditionReport.generateReport(dateOb);

	}

	@FXML
	private void quitter(MouseEvent event) {
		Methode.getStageMouses(event).close();
	}

}
