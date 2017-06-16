package CommercialeControles.Payement;

import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.gestionCommerciale.HibernateSchema.Payment;
import com.gestionCommerciale.Models.PaymentQueries;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import UIControle.Methode;
import UIControle.Notification;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class ModifierPaymentController implements Initializable {

	@FXML
	private JFXDatePicker datepayment;
	@FXML
	private JFXComboBox<String> type;
	@FXML
	private JFXTextField montont;
	private Text Npayement;

	private Payment payment;
	private JFXListView<PayementCell> listepayement;
	@FXML
	private JFXTextField Timbre;

	ArrayList<String> Types = new ArrayList<>();

	private void AfficheListePayement() {

		PayementListeController.listepay.getItems().clear();
		List<Payment> listDB = PaymentQueries.list();

		List<PayementCell> list = new ArrayList<>();
		for (int i = 0; i < listDB.size(); i++) {
			list.add(new PayementCell(listDB.get(i)));
		}
		ObservableList<PayementCell> myObservableList = FXCollections.observableList(list);
		PayementListeController.listepay.setItems(myObservableList);
		PayementListeController.listepay.setExpanded(true);

	}

	@FXML
	private void close(ActionEvent event) {
		Methode.getStage(event).close();
	}

	@FXML
	private void close(MouseEvent event) {
		Methode.getStageMouses(event).close();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

	@FXML
	private void save(ActionEvent event) {

		Optional<ButtonType> result = Notification.updateAlert().showAndWait();
		if (result.get() == ButtonType.OK) {
			Notification.Updatenotification();
			AfficheListePayement();
			close(event);
		}

	}

	public void setData(Payment payement, JFXListView<PayementCell> listepayement) {
		this.payment = payement;
		this.listepayement = listepayement;
		Methode.setOnlyDouble(montont, 8);
		Methode.setOnlyDouble(Timbre, 3);

		Npayement.setText(Integer.toString(this.payment.getIdPayment()));

		Types.add("Especes");
		Types.add("Cheque");
		Types.add("A terme");

		ObservableList<String> liste = FXCollections.observableList(Types);
		type.setItems(liste);

		datepayment.setTime(LocalTime.now());

		Methode.setOnlyDouble(montont, 8);

	}

}
