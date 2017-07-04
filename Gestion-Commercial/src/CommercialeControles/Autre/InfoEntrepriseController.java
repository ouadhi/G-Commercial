package CommercialeControles.Autre;

import java.net.URL;
import java.util.ResourceBundle;

import com.gestionCommerciale.HibernateSchema.Company;
import com.gestionCommerciale.Models.CompanyQueries;
import com.jfoenix.controls.JFXTextField;

import UIControle.Methode;
import UIControle.Notification;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import tray.notification.NotificationType;

public class InfoEntrepriseController implements Initializable {

	@FXML
	private JFXTextField Nregistre;
	@FXML
	private JFXTextField IdFiscal;
	@FXML
	private JFXTextField article;
	@FXML
	private JFXTextField telephone;
	@FXML
	private JFXTextField fax;
	@FXML
	private JFXTextField email;
	@FXML
	private JFXTextField nom;

	@FXML
	private void close(MouseEvent event) {
		Methode.getStageMouses(event).close();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Company c = CompanyQueries.getCompany();
		if (c != null) {
			Methode.setOnlyNumbre(fax);
			Methode.setOnlyNumbre(telephone);
			Nregistre.setText(c.getRegistre());
			IdFiscal.setText(c.getFiscale());
			article.setText(c.getArticle());
			telephone.setText(c.getTelephone());
			this.fax.setText(c.getFax());
			email.setText(c.getEmail());
			this.nom.setText(c.getNom());
		}

	}

	@FXML
	private void quitter(ActionEvent event) {
		Methode.getStage(event).close();
	}

	@FXML
	private void save(ActionEvent event) {
		String registreVal = Nregistre.getText();
		String fiscaleVal = IdFiscal.getText();
		String articlVal = article.getText();
		String telVal = telephone.getText();
		String faxVal = this.fax.getText();
		String emailVal = email.getText();
		String nomVal = this.nom.getText();

		Company com = CompanyQueries.getCompany();
		if (!(registreVal.isEmpty() || fiscaleVal.isEmpty() || articlVal.isEmpty() || telVal.isEmpty()
				|| faxVal.isEmpty() || emailVal.isEmpty() || nomVal.isEmpty())) {
			if (com == null) {
				Company company = new Company(nomVal, registreVal, fiscaleVal, articlVal, emailVal, telVal, faxVal);
				CompanyQueries.SaveOrUpdate(company);

			} else {
				com.setNom(nomVal);
				com.setRegistre(registreVal);
				com.setFiscale(fiscaleVal);
				com.setArticle(articlVal);
				com.setEmail(emailVal);
				com.setTelephone(telVal);
				com.setFax(faxVal);
				CompanyQueries.SaveOrUpdate(com);

			}

			Notification.Addnotification();
		} else {
			Notification.notif(NotificationType.ERROR, "V\u00E9rification", "V\u00E9rifier que tout les champs sont remplis!");

		}

	}

}
