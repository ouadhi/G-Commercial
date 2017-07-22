package CommercialeControles.Ble;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.gestionCommerciale.HibernateSchema.Ble;
import com.gestionCommerciale.Models.BleQueries;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import UIControle.Methode;
import UIControle.Notification;
import UIControle.ShowPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ModifierBleController implements Initializable {

	@FXML
	private ImageView close;
	@FXML
	private JFXButton addbttn;
	@FXML
	private JFXButton cancelbttn;
	@FXML
	private JFXTextField code;
	@FXML
	private JFXTextField quntite;
	@FXML
	private JFXTextField prix;
	@FXML
	private Label savelabel;
	Ble ble;

	private void annuler(ActionEvent event) {
		Stage g = (Stage) ((Node) event.getSource()).getScene().getWindow();
		g.close();
	}

	@FXML
	private void close(MouseEvent event) {
		Stage stage = Methode.getStageMouses(event);
		stage.close();
	}

	@FXML
	private void closestage(ActionEvent event) {
		Methode.getStage(event).close();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Methode.setOnlyDouble(quntite, 10);
		Methode.setOnlyDouble(prix, 10);
		Methode.SetUpper(code, 8);
                Methode.setSelectedMouseClick(quntite);
                Methode.setZeroRemoved(quntite);
	}

	@FXML
	private void saveble(ActionEvent event) {
		String codeval = this.code.getText();
		String quantiteval = this.quntite.getText();
		String prixval = this.prix.getText();
		if (codeval.isEmpty() || quantiteval.isEmpty() || prixval.isEmpty()) {
			Notification.champVideNotification();
		} else {
			Optional<ButtonType> result = Notification.deleteAlert().showAndWait();
			if (result.get() == ButtonType.OK) {
				ble.setCodeBle((codeval));
				ble.setPrix(Double.parseDouble(prixval));
				ble.setQte(Double.parseDouble(quantiteval));
				Ble ble2 = BleQueries.getBleByCode(codeval);
				if (ble2 != null && ble2.getIdBle() != ble.getIdBle()) {
					Notification.error("Ce code existe d\u00E9ja, utilis\u00E9 un autre");
				} else {
					BleQueries.SaveOrUpdate(ble);
					Notification.Updatenotification();
					savelabel.setVisible(true);
					annuler(event);
					closestage(event);
					BleQueries.refresh();
				}
			}
		}
	}

	public void setData(Ble ble) {

		Methode.setOnlyDouble(quntite, 10);
		Methode.setOnlyDouble(prix, 10);
                
		Methode.SetUpper(code, 8);
		this.ble = ble;
		this.code.setText(ble.getCodeBle());
		this.prix.setText(Methode.DoubleFormat(ble.getPrix()));
		this.quntite.setText(Methode.DoubleFormat(ble.getQte()));
	}
}
