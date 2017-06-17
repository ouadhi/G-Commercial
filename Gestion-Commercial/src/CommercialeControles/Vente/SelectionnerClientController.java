
package CommercialeControles.Vente;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.gestionCommerciale.HibernateSchema.Client;
import com.gestionCommerciale.Models.ClientQueries;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import CommercialeControles.Client.ClienCell;
import UIControle.ViewUrl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class SelectionnerClientController implements Initializable {

	@FXML
	private JFXListView<ClienCell> ClientListe;
	private ClientQueries clientQueries = new ClientQueries();
	@FXML
	private JFXTextField rechreche;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		List<Client> listClientsDB = ClientQueries.list();
		List<ClienCell> list = new ArrayList<>();
		for (int i = 0; i < listClientsDB.size(); i++) {
			list.add(new ClienCell(listClientsDB.get(i)));
		}
		ObservableList<ClienCell> myObservableList = FXCollections.observableList(list);
		ClientListe.setItems(myObservableList);
		ClientListe.setExpanded(true);

	}

	private void nextEtape(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource(ViewUrl.selectChauffeurVente));
		OperationVenteController.staticpane.getChildren().setAll(pane);
	}

	@FXML
	private void rechrecher(KeyEvent event) {
		ClientListe.getItems().clear();
		List<Client> listClientsDB = ClientQueries.listRechereche(rechreche.getText());
		List<ClienCell> list = new ArrayList<>();
		for (int i = 0; i < listClientsDB.size(); i++) {
			list.add(new ClienCell(listClientsDB.get(i)));
		}
		ObservableList<ClienCell> myObservableList = FXCollections.observableList(list);
		ClientListe.setItems(myObservableList);
	}

	@FXML
	private void select(MouseEvent event) {
		OperationVenteController.client = ClientListe.getSelectionModel().getSelectedItem().getClient();
	}

}
