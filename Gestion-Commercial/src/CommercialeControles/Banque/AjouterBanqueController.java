package CommercialeControles.Banque;

import java.net.URL;
import java.util.ResourceBundle;

import com.gestionCommerciale.HibernateSchema.Banque;
import com.gestionCommerciale.Models.BanqueQueries;
import com.jfoenix.controls.JFXTextField;

import UIControle.Methode;
import UIControle.Notification;
import UIControle.ShowPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

public class AjouterBanqueController implements Initializable {

	@FXML
	private JFXTextField nombanque;
	@FXML
	private JFXTextField adresse;
	@FXML
	private JFXTextField NumCompte;
	@FXML
	private JFXTextField telephone;

	private BanqueQueries querie = new BanqueQueries();

	@FXML
	private void close(ActionEvent event) {
		Methode.getStage(event).close();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Methode.setOnlyInteger(telephone, 10);
		Methode.SetUpper(nombanque, 30);
		Methode.SetUpper(NumCompte, 30);
		Methode.setsizeString(adresse, 32);

	}

	private Banque makeBanque() {
		Banque banque = new Banque(nombanque.getText(), this.NumCompte.getText(), adresse.getText(),
				telephone.getText());

		return banque;

	}

	@FXML
	private void quitter(MouseEvent event) {
		Methode.getStageMouses(event).close();
	}

	@FXML
	private void save(ActionEvent event) {
		String nom = nombanque.getText();
		String adresse = this.adresse.getText();
		String compte = NumCompte.getText();
		String tele = telephone.getText();

		if (nom.isEmpty() || adresse.isEmpty() || compte.isEmpty() || tele.isEmpty()) {
			Notification.champVideNotification();
		} else {
			querie.SaveOrUpdate(makeBanque());
			Notification.Addnotification();
			BanqueQueries.refresh();
			close(event);
		}
	}

}
