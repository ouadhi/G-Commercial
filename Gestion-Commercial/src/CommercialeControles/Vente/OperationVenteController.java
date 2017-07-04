package CommercialeControles.Vente;

import UIControle.Methode;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.gestionCommerciale.HibernateSchema.Camion;
import com.gestionCommerciale.HibernateSchema.Chauffeur;
import com.gestionCommerciale.HibernateSchema.Client;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import UIControle.Notification;
import UIControle.ViewUrl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class OperationVenteController implements Initializable {

	public static AnchorPane staticpane;
	public static Client client = null;
	public static Camion camion = null;
	public static Chauffeur chauffeur = null;
	public static ArrayList<PorduitH> produitselected = new ArrayList<>();
	public static void ClearVar() {
		client = null;
		chauffeur = null;
		camion = null;
		produitselected.clear();
	}

	public static void setmontantFacture() {
		double prix = 0;
		System.out.println("" + OperationVenteController.produitselected.size());
		for (int i = 0; i < OperationVenteController.produitselected.size(); i++) {
			prix = (OperationVenteController.produitselected.get(i).getProduit().getPrix()
					* OperationVenteController.produitselected.get(i).getQuantite()) + prix;
		}
		FinOperationVenteController.montant_static.setText(Methode.DoubleFormat(prix));
		FinOperationVenteController.calcule();
	}
	@FXML
	private JFXTextField recherchetxt;
	private AnchorPane workespace;
	@FXML
	private AnchorPane space;;

	@FXML
	private JFXButton precedent;

	@FXML
	private JFXButton next;

	private AnchorPane etapeClient, etapeChauffeur, etapeCamion, etapePrdouit, etapeInformation;

	private int etape;

	@FXML
	private void etapprecedent(ActionEvent event) {
		if (etape >= 2) {
			--etape;
			quelleEtape(etape);
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		try {

			staticpane = space;
			etapeClient = FXMLLoader.load(getClass().getResource(ViewUrl.selectClient));
			etapeChauffeur = FXMLLoader.load(getClass().getResource(ViewUrl.selectChauffeurVente));
			etapeCamion = FXMLLoader.load(getClass().getResource(ViewUrl.selectCamionVente));
			etapePrdouit = FXMLLoader.load(getClass().getResource(ViewUrl.selectProduit));
			etapeInformation = FXMLLoader.load(getClass().getResource(ViewUrl.infotmationVente));
                        Methode.moveFocus(etapeInformation);
			space.getChildren().setAll(etapeClient);
			etape = 1;
			precedent.setVisible(false);

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@FXML
	private void nextetape(ActionEvent event) {
		if (this.etape <= 5) {
			etape++;
			quelleEtape(etape);
		}

	}

	private void quelleEtape(int etape) {
		switch (etape) {
		case 1:
			showEtape(etapeClient);
			precedent.setVisible(false);
			break;

		case 2:
			if (client == null) {
				Notification.champVideNotification();
				--this.etape;

			} else {
				showEtape(etapeChauffeur);
				precedent.setVisible(true);
			}

			break;

		case 3:
			if (chauffeur == null) {
				Notification.champVideNotification();
				--this.etape;
			} else {
				showEtape(etapeCamion);
			}

			break;

		case 4:
			if (camion == null) {
				Notification.champVideNotification();
				--this.etape;
			} else {
				showEtape(etapePrdouit);
				next.setVisible(true);
			}
			break;

		default:
			if (OperationVenteController.produitselected.isEmpty()) {
				Notification.champVideNotification();
				--this.etape;
			} else {
				setmontantFacture();
				showEtape(etapeInformation);
				next.setVisible(false);

			}

			break;
		}
	}

	@FXML
	private void rechercher(KeyEvent event) {
	}

	private void showEtape(AnchorPane pane) {
		space.getChildren().setAll(pane);
	}

}
