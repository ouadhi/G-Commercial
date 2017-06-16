
package CommercialeControles;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import UIControle.ShowPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class LeftMenu2Controller implements Initializable {

	@FXML
	private JFXButton home;
	@FXML
	private JFXButton chauffeur;
	@FXML
	private JFXButton camion;
	@FXML
	private ImageView iconHome;
	@FXML
	private ImageView iconCamion;
	private ImageView iconRapport;
	@FXML
	private ImageView iconChauffeur;

	@FXML
	private void InCamion(MouseEvent event) {
		Image img = new Image(getClass().getResourceAsStream("/icons/CamionMagent.png"));
		iconCamion.setImage(img);
	}

	@FXML
	private void InChauffeur(MouseEvent event) {
		Image img = new Image(getClass().getResourceAsStream("/icons/ChauffeurMagent.png"));
		iconChauffeur.setImage(img);
	}

	@FXML
	private void InHome(MouseEvent event) {
		Image img = new Image(getClass().getResourceAsStream("/icons/HomeMagent.png"));
		iconHome.setImage(img);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

	private void InRapport(MouseEvent event) {
		Image img = new Image(getClass().getResourceAsStream("/icons/RapportMagent.png"));
		iconRapport.setImage(img);
	}

	@FXML
	private void OutCamion(MouseEvent event) {
		Image img = new Image(getClass().getResourceAsStream("/icons/CamionGry.png"));
		iconCamion.setImage(img);
	}

	@FXML
	private void OutChauffeur(MouseEvent event) {
		Image img = new Image(getClass().getResourceAsStream("/icons/ChauffeurGry.png"));
		iconChauffeur.setImage(img);
	}

	@FXML
	private void OutHome(MouseEvent event) {
		Image img = new Image(getClass().getResourceAsStream("/icons/HomeGry.png"));
		iconHome.setImage(img);
	}

	private void OutRapport(MouseEvent event) {
		Image img = new Image(getClass().getResourceAsStream("/icons/RapportGry.png"));
		iconRapport.setImage(img);
	}

	@FXML
	private void showcamion(ActionEvent event) {
		new ShowPane().showCamion();
	}

	@FXML
	private void showchauffeur(ActionEvent event) {
		new ShowPane().showChauffeur();
	}

	@FXML
	private void showhome(ActionEvent event) {
		new ShowPane().showHome(event);
	}

	private void showrapport(ActionEvent event) {
		new ShowPane().showUIRapport(event);
	}

}
