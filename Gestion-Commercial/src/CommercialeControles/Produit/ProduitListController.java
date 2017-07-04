package CommercialeControles.Produit;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.controlsfx.control.textfield.TextFields;

import com.gestionCommerciale.HibernateSchema.Produit;
import com.gestionCommerciale.Models.ProduitQueries;
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

public class ProduitListController implements Initializable {

	@FXML
	private Label total;
	@FXML
	private MenuButton Order;
	private MenuButton NbShow;
	@FXML
	private JFXButton ajouter;
	@FXML
	private JFXTextField rechreche;

	private ProduitQueries queries = new ProduitQueries();
	@FXML
	private JFXListView<ProduitCell> listeproduit;
	@FXML
	private Label label;

	@FXML
	private void Archive(ActionEvent event) {
		Order.setText("Archiv\u00E9");
		List<Produit> listBlesDB = ProduitQueries.listArchived();
		List<ProduitCell> list = new ArrayList<>();

		for (int i = 0; i < listBlesDB.size(); i++) {
			list.add(new ProduitCell(listBlesDB.get(i)));
		}

		ObservableList<ProduitCell> myObservableList = FXCollections.observableList(list);
		listeproduit.setItems(myObservableList);
		listeproduit.setExpanded(true);

		setTotale();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Methode.showMenuItem(Order, label);
		List<Produit> listBlesDB = ProduitQueries.list();
		List<ProduitCell> list = new ArrayList<>();

		for (int i = 0; i < listBlesDB.size(); i++) {
			list.add(new ProduitCell(listBlesDB.get(i)));
		}

		ObservableList<ProduitCell> myObservableList = FXCollections.observableList(list);
		listeproduit.setItems(myObservableList);
		listeproduit.setExpanded(true);

		setTotale();

	}

	@FXML
	private void NonArchive(ActionEvent event) {
		Order.setText("Non Archiv\u00E9");
		List<Produit> listBlesDB = ProduitQueries.list();
		List<ProduitCell> list = new ArrayList<>();

		for (int i = 0; i < listBlesDB.size(); i++) {
			list.add(new ProduitCell(listBlesDB.get(i)));
		}

		ObservableList<ProduitCell> myObservableList = FXCollections.observableList(list);
		listeproduit.setItems(myObservableList);
		listeproduit.setExpanded(true);

		setTotale();
	}

	public void possibleMot() {

		ArrayList<String> list = new ArrayList<>();
		list.add("karim");
		list.add("hichem1");
		list.add("hichem2");
		list.add("mohammed ouadhi");
		list.add("mohammed cherberabe");

		TextFields.bindAutoCompletion(rechreche, list);
	}

	@FXML
	private void rechrecher(KeyEvent event) {

		listeproduit.getItems().clear();
		List<Produit> listBlesDB = ProduitQueries.listRechreche(rechreche.getText());
		List<ProduitCell> list = new ArrayList<>();

		for (int i = 0; i < listBlesDB.size(); i++) {
			list.add(new ProduitCell(listBlesDB.get(i)));
		}

		ObservableList<ProduitCell> myObservableList = FXCollections.observableList(list);
		listeproduit.setItems(myObservableList);

		setTotale();

	}

	@FXML
	private void setOrder(ActionEvent event) {
	}

	public void setTotale() {

		total.setText(Integer.toString(listeproduit.getItems().size()));

	}

	@FXML
	private void showAddStage(ActionEvent event) {

		try {
			AnchorPane pane = FXMLLoader.load(getClass().getResource(ViewUrl.AjouterProduit));
                        Methode.moveFocus(pane);
			StageDialog stage = new StageDialog(Methode.getStage(event), pane);

			stage.showAndWait();
		} catch (IOException ex) {
			Logger.getLogger(ProduitListController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@FXML
	private void showDockSlide(MouseEvent event) {
		try {
			int selectedcell = listeproduit.getSelectionModel().getSelectedIndex();

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(ViewUrl.SlideProduit));
			loader.load();

			SlidProduitController slidProduit = loader.getController();
			slidProduit.setData(listeproduit, selectedcell);

			AnchorPane pane = loader.getRoot();

			StageDialog stage = new StageDialog(Methode.getStageMouses(event), pane);
			stage.show();
		} catch (IOException ex) {
			Logger.getLogger(ProduitListController.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	@FXML
	private void tout(ActionEvent event) {
		Order.setText("Tout");
		List<Produit> listBlesDB = ProduitQueries.listAll();
		List<ProduitCell> list = new ArrayList<>();

		for (int i = 0; i < listBlesDB.size(); i++) {
			list.add(new ProduitCell(listBlesDB.get(i)));
		}

		ObservableList<ProduitCell> myObservableList = FXCollections.observableList(list);
		listeproduit.setItems(myObservableList);
		listeproduit.setExpanded(true);

		setTotale();
	}

}
