package CommercialeControles.Dock;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.gestionCommerciale.HibernateSchema.Dock;
import com.gestionCommerciale.Models.DockQueries;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import CommercialeControles.OperationAchat.DockListeH;
import CommercialeControles.OperationAchat.SelectionnerDockController;
import UIControle.Methode;
import UIControle.Notification;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tray.notification.NotificationType;

public class AjouterDockController implements Initializable {

	@FXML
	private JFXTextField nom;
	@FXML
	private JFXTextField wilaya;
	@FXML
	private JFXTextField distance;
	@FXML
	private JFXTextField prix;
	@FXML
	private JFXButton savebttn;
	@FXML
	private JFXButton cancelbttn;
	@FXML
	private Label savelabel;
	@FXML
	private ImageView close;
	JFXListView<DockCell> listedock = null;
        JFXListView<DockListeH> listeDockh =  null  ; 
	Label totale;
        

	@FXML
	private void close(MouseEvent event) {
		Stage currentStage = Methode.getStageMouses(event);
		currentStage.close();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Methode.setOnlyDouble4(prix, 10);
		Methode.setOnlyDouble(distance, 10);
		Methode.setsizeString(wilaya, 22);
		Methode.SetUpper(nom, 30);
	}

	@FXML
	private void quitter(ActionEvent event) {
		Stage currentStage = Methode.getStage(event);
		currentStage.close();
	}

	public void refreshHliste() {
		SelectionnerDockController.listeDocks.getItems().clear();
		List<Dock> listDocksDB = DockQueries.list();
		List<DockListeH> list = new ArrayList<>();
		for (int i = 0; i < listDocksDB.size(); i++) {
			list.add(new DockListeH(listDocksDB.get(i)));
		}
		list.add(new DockListeH(listeDockh));
		ObservableList<DockListeH> myObservableList = FXCollections.observableList(list);
		SelectionnerDockController.listeDocks.setItems(myObservableList);
		
	}

	public void refreshVliste() {
		List<Dock> listDocksDB = DockQueries.list();
		List<DockCell> list = new ArrayList<>();
		for (int i = 0; i < listDocksDB.size(); i++) {
			list.add(new DockCell(listDocksDB.get(i)));
		}
		ObservableList<DockCell> myObservableList = FXCollections.observableList(list);
		listedock.setItems(myObservableList);
		listedock.setExpanded(true);
		totale.setText("" + listedock.getItems().size());
	}

	@FXML
	private void sauvgarder(ActionEvent event) {
		String nom = this.nom.getText();
		String wilaya = this.wilaya.getText();
		String prix = this.prix.getText();
		String distance = this.distance.getText();
		if (nom.isEmpty() || wilaya.isEmpty() || distance.isEmpty() || prix.isEmpty()) {
			Notification.notif(NotificationType.ERROR, "V\u00E9rification", "V\u00E9rifier que tout les champs sont remplis!");
		} else {
			if (DockQueries.getDockByNameAndWilaya(nom, wilaya) != null) {
				Notification.error("Ce dock est exite d\u00E9ja!");
			} else {
				try {
					Dock dock = new Dock(nom, wilaya, Double.parseDouble(distance), Double.parseDouble(prix));
					if (DockQueries.SaveOrUpdate(dock)) {
						Notification.Addnotification();
					} else {
						Notification.error("Erreur!");
					}
					savelabel.setVisible(true);
					quitter(event);
					if (listedock == null) {
						refreshHliste();
					} else {
						refreshVliste();
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	public void SetData(JFXListView<DockCell> listedock, Label totale) {
		this.listedock = listedock;
		this.totale = totale;
	}
          
        public void setData (JFXListView<DockListeH> listeDockh) {
            this.listeDockh  =  listeDockh  ; 
            
        }
}
