package CommercialeControles.Statistique;

import java.net.URL;
import java.util.ResourceBundle;

import com.gestionCommerciale.HibernateSchema.Client;
import com.gestionCommerciale.HibernateSchema.Facture;
import com.gestionCommerciale.Models.ClientQueries;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

public class MenuViewStatisitqueController implements Initializable {

	@FXML
	private BarChart<String, Double> chartbar;

	NumberAxis xAxis = new NumberAxis();

	CategoryAxis yAxis = new CategoryAxis();
	@FXML
	private Label nbclient;
	@FXML
	private Label sommefactures;

	@FXML
	private void achat(ActionEvent event) {
	}

	@FXML
	private void client(ActionEvent event) {
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		chartbar.setAnimated(true);

		XYChart.Series serie = new XYChart.Series<>();
		double somme = 0;

		for (Client client : ClientQueries.list()) {
			serie.getData()
					.add(new XYChart.Data<>(client.getName() + " " + client.getPrenom(), sommeAchatClient(client)));
			somme += sommeAchatClient(client);
		}

		nbclient.setText("" + ClientQueries.list().size());
		sommefactures.setText("" + somme);
		chartbar.getData().addAll(serie);

	}

	public Double sommeAchatClient(Client c) {
		double somme = 0;
		for (Facture facture : c.getFactures()) {
			somme += facture.getMontantFinal();
		}

		return somme;
	}

}
