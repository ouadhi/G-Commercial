package CommercialeControles.Autre;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.gestionCommerciale.HibernateSchema.Annee;
import com.jfoenix.controls.JFXTextField;

import UIControle.Methode;
import UIControle.Notification;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;

public class ModifierAnneeController implements Initializable {

	@FXML
	private JFXTextField tva;

	Annee annee;

	@FXML
	private void close(MouseEvent event) {
		Methode.getStageMouses(event).close();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Methode.setOnlyInteger(tva, 2);

	}

	@FXML
	private void quitter(ActionEvent event) {
		Methode.getStage(event).close();
	}

	@FXML
	private void save(ActionEvent event) {

		Optional<ButtonType> result = Notification.updateAlert().showAndWait();
		if (result.get() == ButtonType.OK) {
			if (tva.getText().isEmpty()) {
				Notification.champVideNotification();
			} else {

				Notification.Updatenotification();
				quitter(event);
			}
		}

	}

	public void setData(Annee annee) {
		this.annee = annee;
		tva.setText(this.annee.getTva() + "");
	}

}
