package CommercialeControles.Vente;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.gestionCommerciale.HibernateSchema.Facture;
import com.gestionCommerciale.Models.FactureQueries;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;

import Report.FactureReport.ToutFacture;
import UIControle.Methode;
import com.gestionCommerciale.HibernateSchema.Facture_Produit;
import java.io.File;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
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
	@FXML
	private Label nbventetoday;
	@FXML
	private Label quntitetoday;
	@FXML
	private Label montantToday;

	@FXML
	private void close(MouseEvent event) {
		Methode.getStageMouses(event).close();
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

		setinformation(factureList);

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		dtpStart.setValue(LocalDate.now());
		dtpEnd.setValue(LocalDate.now());
		getFactures();
	}

	@FXML
	private void print(ActionEvent event) {
		try {
			DirectoryChooser chooser = new DirectoryChooser();
			chooser.setTitle("Choisir un dossier");
			File defaultDirectory = new File(System.getProperty("user.home"));
			chooser.setInitialDirectory(defaultDirectory);
			File selectedDirectory = chooser.showDialog(((Node) event.getTarget()).getScene().getWindow());
			if (selectedDirectory == null) {
				// do nothing
			} else {
				List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
				for (int i = 0; i < factureList.size(); i++) {
					jasperPrints.add(ToutFacture.ItererJaspoerPrint(factureList.get(i)));

				}
				JRPdfExporter exporter = new JRPdfExporter();
				exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrints));

				//exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(
				//		System.getProperty("user.home") + "/Desktop" + "/out.pdf"));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(selectedDirectory.getAbsolutePath()+"/Factures.pdf"));

				SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
				configuration.setCreatingBatchModeBookmarks(true);

				exporter.setConfiguration(configuration);
				exporter.exportReport();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@FXML
	private void valider(ActionEvent event) {
		getFactures();

	}

	private double montantTotal(List<Facture> list) {
		double total = 0;

		for (Facture facture : list) {
			total += facture.getMontantFinal();

		}
		return total;
	}

	public double qantiteTotalDeVente(List<Facture> list) {
		double total = 0;

		for (Facture facture : list) {

			total += qunatiteDeFacture(facture);

		}
		return total;
	}

	public int NbtotaleFacture(List<Facture> list) {
		return list.size();
	}

	public double qunatiteDeFacture(Facture facture) {
		double somme = 0;

		for (Facture_Produit facture_Produit : facture.getQtes2()) {
			somme += facture_Produit.getQte_fact();
		}
		return somme;
	}

	private void setinformation(List<Facture> list) {
		nbventetoday.setText(Methode.DoubleFormat(NbtotaleFacture(list)));
		quntitetoday.setText(Methode.DoubleFormat(qantiteTotalDeVente(list)));
		montantToday.setText(Methode.DoubleFormat(montantTotal(list)));
	}

}
