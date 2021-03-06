package CommercialeControles.OperationAchat;

import UIControle.Methode;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;

import UIControle.Notification;
import UIControle.ViewUrl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class OperationMainController implements Initializable {

	@FXML
	private AnchorPane space;
	@FXML
	private JFXButton suivant;
	@FXML
	private JFXButton precedent;

	private int etape;
	private AnchorPane chauffeur, camion, Ble, information, dock;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		try {
			dock = FXMLLoader.load(getClass().getResource(ViewUrl.selectDock));
			showEtape(dock);
			chauffeur = FXMLLoader.load(getClass().getResource(ViewUrl.selectChauffeur));
			camion = FXMLLoader.load(getClass().getResource(ViewUrl.selectCamion));
			Ble = FXMLLoader.load(getClass().getResource(ViewUrl.selectBle));
			information = FXMLLoader.load(getClass().getResource(ViewUrl.informationAcaht));
			etape = 1;
                        Methode.moveFocus(chauffeur);
                        Methode.moveFocus(dock);
                        Methode.moveFocus(camion);
                        Methode.moveFocus(Ble);
                        Methode.moveFocus(information);

			precedent.setVisible(false);
		} catch (IOException ex) {
			Logger.getLogger(OperationMainController.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	@FXML
	private void methodeprecedent(ActionEvent event) {
		if (etape >= 2) {
			--etape;
			quelleEtape(etape);
		}

	}

	@FXML
	private void methodesuivant(ActionEvent event) {

		if (this.etape <= 5) {
			etape++;
			quelleEtape(etape);
		}

	}

	public void quelleEtape(int etape) {
		switch (etape) {
		case 1:
			showEtape(dock);
			precedent.setVisible(false);
			break;

		case 2:
			if (FinOperationController.dock == null) {
				Notification.champVideNotification();
				--this.etape;
			} else {
				showEtape(chauffeur);
				precedent.setVisible(true);

			}

			break;

		case 3:
			if (FinOperationController.chauffeur == null) {
				Notification.champVideNotification();
				--this.etape;
			} else {
				showEtape(camion);
			}

			break;

		case 4:
			if (FinOperationController.camion == null) {
				Notification.champVideNotification();
				--this.etape;
			} else {
				showEtape(Ble);
				suivant.setVisible(true);
			}
			break;

		default:
			if (FinOperationController.ble == null) {
				Notification.champVideNotification();
				--this.etape;
				suivant.setVisible(true);
			} else {
				showEtape(information);
				FinOperationController.stateicon();
				suivant.setVisible(false);
			}

			break;
		}
	}

	public void showEtape(AnchorPane pane) {

		space.getChildren().setAll(pane);

	}

}
