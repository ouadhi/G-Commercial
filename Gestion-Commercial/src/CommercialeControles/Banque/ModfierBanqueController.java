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

public class ModfierBanqueController implements Initializable {

	@FXML
	private JFXTextField nombanque;
	@FXML
	private JFXTextField adresse;
	@FXML
	private JFXTextField NumCompte;
	@FXML
	private JFXTextField telephone;

	private Banque banque;

	private BanqueQueries querie = new BanqueQueries();

	@FXML
	private void close(ActionEvent event) {
		Methode.getStage(event).close();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		Methode.setOnlyInteger(telephone, 10);
		Methode.SetUpper(nombanque, 32);
		Methode.SetUpper(NumCompte, 32);
		Methode.setsizeString(adresse, 32);

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

			banque.setNom(nom);
			banque.setCompte(compte);
			banque.setAddress(adresse);
			banque.setTelephone(tele);

			querie.SaveOrUpdate(banque);
			Notification.Addnotification();
			BanqueQueries.refresh();
			close(event);
		}
	}

	public void setData(Banque banque) {
		this.banque = banque;
		Methode.SetUpper(nombanque, 32);
		this.NumCompte.setText(banque.getCompte());
		this.adresse.setText(this.banque.getAddress());
		this.nombanque.setText(this.banque.getNom());
		this.telephone.setText(this.banque.getTelephone());

	}

}
