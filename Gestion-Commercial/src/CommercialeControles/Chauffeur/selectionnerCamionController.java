
package CommercialeControles.Chauffeur;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.gestionCommerciale.HibernateSchema.Camion;
import com.gestionCommerciale.Models.CamionQueries;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;

import CommercialeControles.OperationAchat.CamionListeH;
import UIControle.Methode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.input.MouseEvent;

public class selectionnerCamionController implements Initializable {

	@FXML
	private JFXListView<CamionListeH> listeCamion;
	private CamionListeH camion;

	private CamionQueries camionQueries = new CamionQueries();
	@FXML
	private JFXButton btnnenvoyer;

	ArrayList<Camion> camions_Chauffeur;
	JFXComboBox<String> camionbox;

	@FXML
	private void envoyer(ActionEvent event) {
		camions_Chauffeur.add(camion.getCamion());
		setCamionBox();

		Methode.getStage(event).close();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		List<Camion> listCamionsDB = CamionQueries.list();
		List<CamionListeH> list = new ArrayList<>();
		for (int i = 0; i < listCamionsDB.size(); i++) {
			list.add(new CamionListeH(listCamionsDB.get(i)));

		}

//		CamionListeH ch = new CamionListeH();
//		list.add(ch);
		ObservableList<CamionListeH> myObservableList = FXCollections.observableList(list);
		listeCamion.setItems(myObservableList);
		listeCamion.setOrientation(Orientation.HORIZONTAL);
		listeCamion.setExpanded(true);
		btnnenvoyer.setVisible(false);

	}

	@FXML
	private void quitter(ActionEvent event) {
		Methode.getStage(event).close();
	}

	@FXML
	private void select(MouseEvent event) {

		camion = listeCamion.getSelectionModel().getSelectedItem();
		btnnenvoyer.setVisible(true);

	}

	public void setCamionBox() {
		camionbox.getItems().clear();
		for (Camion cam : camions_Chauffeur) {
			camionbox.getItems().add(cam.getMatricule() + " " + cam.getMarque());
		}

	}

	public void setData(ArrayList<Camion> camions_Chauffeur, JFXComboBox<String> camionbox) {
		this.camions_Chauffeur = camions_Chauffeur;
		this.camionbox = camionbox;

	}
}
