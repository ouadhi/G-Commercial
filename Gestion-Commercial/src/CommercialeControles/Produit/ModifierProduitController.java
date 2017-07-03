package CommercialeControles.Produit;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.gestionCommerciale.HibernateSchema.Produit;
import com.gestionCommerciale.Models.ProduitQueries;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;

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

public class ModifierProduitController implements Initializable {

	@FXML
	private ImageView close;
	@FXML
	private JFXTextField nom;
	@FXML
	private JFXTextField categorie;
	@FXML
	private JFXTextField quantite;
	@FXML
	private JFXTextField prix;
	@FXML
	private JFXButton savebttn;
	@FXML
	private JFXButton cancelbttn;
	@FXML
	private Label savelabel;
	@FXML
	private JFXTextField code;
	@FXML
	private JFXToggleButton TVA;
	Produit produit;

	@FXML
	private void close(MouseEvent event) {
		Methode.getStageMouses(event).close();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Methode.setOnlyDouble(quantite, 9);
		Methode.setOnlyDouble(prix, 16);

		Methode.setsizeString(categorie, 30);
		Methode.setsizeString(nom, 30);

	}

	@FXML
	private void quitter(ActionEvent event) {
		Methode.getStage(event).close();
	}

	@FXML
	private void sauvgarder(ActionEvent event) {
		String nomVal = nom.getText();
		String categorieVal = categorie.getText();
		String quantiteVal = quantite.getText();
		String prixVal = prix.getText();
		String code = this.code.getText();

		if (nomVal.isEmpty() || categorieVal.isEmpty() || quantiteVal.isEmpty() || prixVal.isEmpty()) {

			Notification.champVideNotification();

		} else {

			Optional<ButtonType> result = Notification.updateAlert().showAndWait();
			if (result.get() == ButtonType.OK) {
				// back
				Produit p = ProduitQueries.getProduitByCode(code);
				if (p != null && p.getIdProduit() != produit.getIdProduit()) {
					// notification for already exists
					Notification.error("Ce produit exite déja!");
				} else {
					produit.setCodeProduit(code);
					produit.setNom(nomVal);
					produit.setCategory(categorieVal);
					produit.setQuantite(Integer.parseInt(quantiteVal));
					produit.setPrix(Double.parseDouble(prixVal));
					produit.setHaveTva(TVA.isSelected());

					ProduitQueries.SaveOrUpdate(produit);

					Notification.Updatenotification();
					savelabel.setVisible(true);
					new ShowPane().showProduit();
					quitter(event);
				}
			}
		}

	}

	public void setData(Produit produit) {
		Methode.setOnlyDouble(quantite, 9);
		Methode.setOnlyDouble(prix, 16);
		Methode.setsizeString(categorie, 30);
		Methode.setsizeString(nom, 30);

		this.produit = produit;
		this.nom.setText(produit.getNom());
		this.categorie.setText(produit.getCategory());
		this.quantite.setText(Integer.toString(produit.getQuantite()));
		this.prix.setText(Methode.DoubleFormatS(produit.getPrix())+"");
		this.code.setText(this.produit.getCodeProduit());
		if (this.produit.isHaveTva()) {
			TVA.setSelected(true);
		} else {
			TVA.setSelected(false);
		}

	}

}
