package CommercialeControles.Dock;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.gestionCommerciale.HibernateSchema.Dock;
import com.gestionCommerciale.Models.DockQueries;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import UIControle.Methode;
import UIControle.Notification;
import UIControle.ShowPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tray.notification.NotificationType;

public class ModifierDockController implements Initializable {

	@FXML
	private ImageView close;
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
	private Dock dock;

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

	@FXML
	private void sauvgarder(ActionEvent event) {
		String nom = this.nom.getText();
		String wilaya = this.wilaya.getText();
		String distance = this.distance.getText();
		String prix = this.prix.getText();

		if (nom.isEmpty() || wilaya.isEmpty() || distance.isEmpty() || prix.isEmpty()) {

			Notification.champVideNotification();

		} else {
			Optional<ButtonType> result = Notification.updateAlert().showAndWait();
			if (result.get() == ButtonType.OK) {
				if (nom.isEmpty() || wilaya.isEmpty() || distance.isEmpty() || prix.isEmpty()) {
					Notification.notif(NotificationType.ERROR, "V\u00E9rification",
							"V\u00E9rifier que tout les champs sont remplis!");
				} else {
					dock.setNom(nom);
					dock.setWilaya(wilaya);
					dock.setPrixUnitTrans(Double.parseDouble(prix));
					dock.setDistance(Float.parseFloat(distance));
					DockQueries.SaveOrUpdate(dock);
					Notification.Updatenotification();
                                        
					DockQueries.refreshListe();
					quitter(event);
				}
			}
		}
	}

	public void setData(Dock dock) {
		Methode.setOnlyDouble4(prix, 10);
		Methode.setOnlyDouble(distance, 10);
		Methode.setsizeString(wilaya, 22);
		Methode.SetUpper(nom, 30);
		this.dock = dock;
		this.nom.setText(dock.getNom());
		this.wilaya.setText(dock.getWilaya());
		this.distance.setText(Methode.DoubleFormat(dock.getDistance()) + "");
		this.prix.setText(Methode.DoubleFormat4(dock.getPrixUnitTrans()) + "");
	}
}
