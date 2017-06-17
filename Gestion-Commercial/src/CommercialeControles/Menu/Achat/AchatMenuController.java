
package CommercialeControles.Menu.Achat;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;

import CommercialeControles.Home2FXMLController;
import CommercialeControles.HomeFXMLController;
import UIControle.ShowPane;
import UIControle.ViewUrl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AchatMenuController implements Initializable {

	@FXML
	private JFXButton home;
	@FXML
	private JFXButton Dock;
	@FXML
	private JFXButton Ble;
	@FXML
	private ImageView homeimage;
	@FXML
	private ImageView dockicon;
	@FXML
	private ImageView bleicon;
	private ImageView rapporticon;
	@FXML
	private JFXButton achat;
	@FXML
	private ImageView achaticon;

	@FXML
	private void bleIN(MouseEvent event) {
		Image img = new Image(getClass().getResourceAsStream("/icons/Blegreen.png"));
		bleicon.setImage(img);
	}

	@FXML
	private void BleOut(MouseEvent event) {
		Image img = new Image(getClass().getResourceAsStream("/icons/BleGry.png"));
		bleicon.setImage(img);
	}

	@FXML
	private void dockentered(MouseEvent event) {
		Image img = new Image(getClass().getResourceAsStream("/icons/Dockgreen.png"));
		dockicon.setImage(img);
	}

	@FXML
	private void dockexited(MouseEvent event) {
		Image img = new Image(getClass().getResourceAsStream("/icons/DockGry.png"));
		dockicon.setImage(img);
	}

	@FXML
	private void entered(MouseEvent event) {
		Image img = new Image(getClass().getResourceAsStream("/icons/homegreen.png"));
		homeimage.setImage(img);
	}

	@FXML
	private void exited(MouseEvent event) {
		Image img = new Image(getClass().getResourceAsStream("/icons/HomeGry.png"));
		homeimage.setImage(img);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

	@FXML
	private void rapportIN(MouseEvent event) {
		Image img = new Image(getClass().getResourceAsStream("/icons/achat2.png"));
		achaticon.setImage(img);
	}

	@FXML
	private void rapportOut(MouseEvent event) {
		Image img = new Image(getClass().getResourceAsStream("/icons/achat1.png"));
		achaticon.setImage(img);
	}

	@FXML
	private void showAchat(ActionEvent event) {
		new ShowPane().showListAchat();
	}

	@FXML
	private void showBle(ActionEvent event) {

		new ShowPane().showBle();
	}

	@FXML
	private void showDock(ActionEvent event) {

		new ShowPane().showDock();
	}

	@FXML
	private void showhome(ActionEvent event) throws IOException {

		new ShowPane().showHome(event);
	}

	private void showRapport(ActionEvent event) {
		showUIRapport(event);
	}

	private void showUIRapport(ActionEvent event) {
		try {
			AnchorPane menu2 = FXMLLoader.load(getClass().getResource(ViewUrl.rapportMenu));

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/CommercialeView/Home2FXML.fxml"));
			loader.load();

			Home2FXMLController control = loader.getController();
			control.setMenu(menu2);

			AnchorPane root = loader.getRoot();

			Scene scene = new Scene(root);
			Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
			stage.setScene(scene);

			new ShowPane().showRapport();

		} catch (IOException ex) {
			Logger.getLogger(HomeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
