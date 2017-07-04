package CommercialeControles.Dock;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.controlsfx.control.textfield.TextFields;

import com.gestionCommerciale.HibernateSchema.Dock;
import com.gestionCommerciale.Models.DockQueries;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import UIControle.Methode;
import UIControle.StageDialog;
import UIControle.ViewUrl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DockLisController implements Initializable {

	@FXML
	private Label total;
	@FXML
	private MenuButton Order;
	private MenuButton NbShow;
	@FXML
	private JFXButton ajouter;
	@FXML
	private JFXListView<DockCell> listedock;
	@FXML
	private JFXTextField recherchetxt;
	@FXML
	private Label label;

	@FXML
	private void Archive(ActionEvent event) {
		Order.setText("Archiv\u00E9");
		List<Dock> listDocksDB = DockQueries.listArchived();

		List<DockCell> list = new ArrayList<>();
		for (int i = 0; i < listDocksDB.size(); i++) {
			list.add(new DockCell(listDocksDB.get(i)));
		}

		ObservableList<DockCell> myObservableList = FXCollections.observableList(list);
		listedock.setItems(myObservableList);
		listedock.setExpanded(true);

		setTotale();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Methode.showMenuItem(Order, label);
		List<Dock> listDocksDB = DockQueries.list();

		List<DockCell> list = new ArrayList<>();
		for (int i = 0; i < listDocksDB.size(); i++) {
			list.add(new DockCell(listDocksDB.get(i)));
		}

		ObservableList<DockCell> myObservableList = FXCollections.observableList(list);
		listedock.setItems(myObservableList);
		listedock.setExpanded(true);

		setTotale();

	}

	@FXML
	private void nonArchive(ActionEvent event) {
		Order.setText("Non Archiv\u00E9");
		List<Dock> listDocksDB = DockQueries.list();

		List<DockCell> list = new ArrayList<>();
		for (int i = 0; i < listDocksDB.size(); i++) {
			list.add(new DockCell(listDocksDB.get(i)));
		}

		ObservableList<DockCell> myObservableList = FXCollections.observableList(list);
		listedock.setItems(myObservableList);
		listedock.setExpanded(true);

		setTotale();
	}

	public void possibleMot() {

		ArrayList<String> list = new ArrayList<>();
		list.add("karim");
		list.add("hichem1");
		list.add("hichem2");
		list.add("mohammed ouadhi");
		list.add("mohammed cherberabe");

		TextFields.bindAutoCompletion(recherchetxt, list);

	}

	@FXML
	private void rechrecher(KeyEvent event) {
		listedock.getItems().clear();
		List<Dock> listDocksDB = DockQueries.listrechreche(recherchetxt.getText());

		List<DockCell> list = new ArrayList<>();
		for (int i = 0; i < listDocksDB.size(); i++) {
			list.add(new DockCell(listDocksDB.get(i)));
		}

		ObservableList<DockCell> myObservableList = FXCollections.observableList(list);
		listedock.setItems(myObservableList);
		listedock.setExpanded(true);

		setTotale();
	}

	@FXML
	private void setOrder(ActionEvent event) {
	}

	private void setTotale() {
		total.setText(Integer.toString(listedock.getItems().size()));
	}

	@FXML
	private void showAddStage(ActionEvent event) {

		try {

			Stage stage = Methode.getStage(event);
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(ViewUrl.AjouterDock));
			loader.load();

			AjouterDockController AddDock = loader.getController();
			AddDock.SetData(listedock, total);

			AnchorPane root = loader.getRoot();
                         Methode.moveFocus(root);

			StageDialog dialog = new StageDialog(stage, root);
			dialog.show();

		} catch (IOException ex) {
			Logger.getLogger(DockLisController.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	@FXML
	private void showDockSlide(MouseEvent event) {

		try {

			int seletedrow = listedock.getSelectionModel().getSelectedIndex();
			Stage stage = Methode.getStageMouses(event);
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(ViewUrl.ShowDockSlide));
			loader.load();

			ShowDockSlideController controlClient = loader.getController();
			controlClient.setData(seletedrow, listedock);

			AnchorPane root = loader.getRoot();
                         Methode.moveFocus(root);

			StageDialog dialog = new StageDialog(stage, root);
			dialog.show();

		} catch (IOException ex) {
			Logger.getLogger(DockLisController.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	@FXML
	private void tout(ActionEvent event) {
		Order.setText("Tout");
		List<Dock> listDocksDB = DockQueries.listAll();

		List<DockCell> list = new ArrayList<>();
		for (int i = 0; i < listDocksDB.size(); i++) {
			list.add(new DockCell(listDocksDB.get(i)));
		}

		ObservableList<DockCell> myObservableList = FXCollections.observableList(list);
		listedock.setItems(myObservableList);
		listedock.setExpanded(true);

		setTotale();
	}

}
