package CommercialeControles.Autre;

import java.net.URL;
import java.util.ResourceBundle;

import com.gestionCommerciale.HibernateSchema.Annee;
import com.gestionCommerciale.Models.AnneeQueries;
import com.jfoenix.controls.JFXTextField;

import UIControle.Methode;
import UIControle.Notification;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

public class AddAnneeController implements Initializable {

	@FXML
	private JFXTextField annee;
	@FXML
	private JFXTextField tva;

	@FXML
	private void close(MouseEvent event) {
		Methode.getStageMouses(event).close();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Methode.setOnlyInteger(annee, 4);
		Methode.setOnlyInteger(tva, 2);
	}

	@FXML
	private void quitter(ActionEvent event) {
		Methode.getStage(event).close();
	}

	@FXML
	private void save(ActionEvent event) {
		if (annee.getText().isEmpty() || tva.getText().isEmpty()) {
			Notification.champVideNotification();
		} else {
			int intAnnee = Integer.parseInt(annee.getText());
			int intTva = Integer.parseInt(tva.getText());
			Annee a = new Annee(intAnnee, intTva);
			if (AnneeQueries.getAnneeById(intAnnee) == null) {
				if (AnneeQueries.SaveOrUpdate(a)) {
					Notification.Addnotification();
					ParamaitreController.AnneeStatic.getItems().add(a.getIdAnnee() + "");
				} else {
					Notification.check("Erreur!");
				}
			} else {
				Notification.check("Cette ann\u00E9e existe d\u00E9ja!");
			}

		}
		quitter(event);
	}

}
