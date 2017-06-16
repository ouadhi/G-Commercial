package CommercialeControles.Payement;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gestionCommerciale.HibernateSchema.Client;
import com.gestionCommerciale.HibernateSchema.Payment;
import com.gestionCommerciale.Models.ClientQueries;
import com.gestionCommerciale.Models.PaymentQueries;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import CommercialeControles.Client.ClienCell;
import UIControle.Methode;
import UIControle.StageDialog;
import UIControle.ViewUrl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class PayementListeController implements Initializable {

	public static JFXListView<PayementCell> listepay;
	static JFXTextField STtotalefacture;

	static JFXTextField STtotlepaye;
	static JFXTextField STreste;

	@FXML
	private JFXListView<PayementCell> listepayement;
	@FXML
	private JFXTextField totalefacture;

	@FXML
	private JFXTextField totlepaye;
	@FXML
	private JFXTextField reste;

	Client client;

	@FXML
	private MenuButton Order;
	@FXML
	private MenuItem byquantite;
	@FXML
	private Label label;
	double totalFactured;
	double totalVersed;
	double solde;
	@FXML
	private Text nomclient;

	@FXML
	private void addpayment(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(ViewUrl.Addpayement));
			loader.load();

			AjouterPayementController pay = loader.getController();
			pay.setdata(client, listepayement);

			AnchorPane root = loader.getRoot();

			StageDialog dialog = new StageDialog(Methode.getStage(event), root);
			dialog.show();
		} catch (IOException ex) {
			Logger.getLogger(ClienCell.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	// private void printFactureDetails() {
	// FactureQueries fq = new FactureQueries();
	// Facture f = fq.getFacture(numero_facture);
	// System.err.println(numero_facture + "++++++++++++");
	//
	// double totale = f.getMontant();
	// double totalePaye = f.getPaymentsMontant();
	// double reste = totale - totalePaye;
	// totalefacture.setText(floatFormat(totale));
	// totlepaye.setText(floatFormat(totalePaye));
	// this.reste.setText(floatFormat(reste));
	// }
	private void AfficheListePayement() {
		try {
			totalFactured = ClientQueries.totalFactured(client);
			totalVersed = ClientQueries.totalVersed(client);
			solde = ClientQueries.solde(client);
			totalefacture.setText(Methode.DoubleFormat(totalFactured) + "");
			totlepaye.setText(Methode.DoubleFormat(totalVersed) + "");
			reste.setText(Methode.DoubleFormat(solde) + "");
			List<Payment> listDB = PaymentQueries.getPaymentsListByClientId(client.getId());
			List<PayementCell> list = new ArrayList<>();
			for (int i = 0; i < listDB.size(); i++) {
				list.add(new PayementCell(listDB.get(i)));
			}
			ObservableList<PayementCell> myObservableList = FXCollections.observableList(list);
			listepayement.setItems(myObservableList);
			listepayement.setExpanded(true);

		} catch (Exception e) {
		}

	}

	@FXML
	private void Archive(ActionEvent event) {
		Order.setText("Archivé");
		List<Payment> listDB = PaymentQueries.listArchived();

		List<PayementCell> list = new ArrayList<>();
		for (int i = 0; i < listDB.size(); i++) {
			list.add(new PayementCell(listDB.get(i)));
		}
		ObservableList<PayementCell> myObservableList = FXCollections.observableList(list);
		listepayement.setItems(myObservableList);
		listepayement.setExpanded(true);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Methode.showMenuItem(Order, label);
		AfficheListePayement();
		// printFactureDetails();
		listepayement.setExpanded(true);
		STreste = reste;
		STtotalefacture = totalefacture;
		STtotlepaye = totlepaye;

	}

	@FXML
	private void NonArchiv(ActionEvent event) {
		Order.setText("Non Archivé");
		List<Payment> listDB = PaymentQueries.list();

		List<PayementCell> list = new ArrayList<>();
		for (int i = 0; i < listDB.size(); i++) {
			list.add(new PayementCell(listDB.get(i)));
		}
		ObservableList<PayementCell> myObservableList = FXCollections.observableList(list);
		listepayement.setItems(myObservableList);
		listepayement.setExpanded(true);
	}

	@FXML
	private void quitter(MouseEvent event) {
		Methode.getStageMouses(event).close();
	}

	public void setDate(Client client) {
		this.client = client;
		AfficheListePayement();
		listepayement.setExpanded(true);
		listepay = this.listepayement;
		nomclient.setText(client.getName() + " " + client.getPrenom());

	}

	@FXML
	private void setOrder(ActionEvent event) {
	}

	@FXML
	private void Tout(ActionEvent event) {
		Order.setText("Tout");
		List<Payment> listDB = PaymentQueries.listAll();

		List<PayementCell> list = new ArrayList<>();
		for (int i = 0; i < listDB.size(); i++) {
			list.add(new PayementCell(listDB.get(i)));
		}
		ObservableList<PayementCell> myObservableList = FXCollections.observableList(list);
		listepayement.setItems(myObservableList);
		listepayement.setExpanded(true);
	}

}
