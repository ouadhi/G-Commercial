
package CommercialeControles;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import UIControle.ShowPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LeftMenuFXMLController implements Initializable {

	@FXML
	private Button bttnchauffeur;
	@FXML
	private Button bttnCamion;
	@FXML
	private Button home;
	@FXML
	private Button rapport;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

	@FXML
	private void showCamion(ActionEvent event) {
		new ShowPane().showCamion();
	}

	@FXML
	private void showchauffeur(ActionEvent event) {
		new ShowPane().showChauffeur();
	}

	@FXML
	private void showhome(ActionEvent event) {
		try {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/CommercialeView/HomeFXML.fxml"));
			Scene scene = new Scene(pane);
			Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();

			stage.setScene(scene);

		} catch (IOException ex) {
			Logger.getLogger(HomeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@FXML
	private void showrapport(ActionEvent event) {
	}

}
