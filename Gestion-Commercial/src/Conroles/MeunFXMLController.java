package Conroles;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class MeunFXMLController implements Initializable {

	@FXML
	private JFXButton users_button;
	@FXML
	private JFXButton Roles_button;
	@FXML
	private JFXButton notificaton__button;
	@FXML
	private JFXButton profiles__button;
	@FXML
	private JFXButton settings_button;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

	@FXML
	private void show_notifcation_list(ActionEvent event) {

		stage_suivant("/Views/Notification_sceen.fxml");
	}

	@FXML
	void show_role_liste(ActionEvent event) {

		stage_suivant("/Views/RoleFXML.fxml");
	}

	@FXML
	void show_setting(ActionEvent event) {

		stage_suivant("/Views/SettingFXML.fxml");
	}

	@FXML
	private void show_users_list(ActionEvent event) {
		stage_suivant("/Views/Users_List.fxml");
	}

	private void stage_suivant(String file) {

		TranslateTransition trans1 = transitionout(AdminFXMLController.rootp);
		trans1.play();

		trans1.setOnFinished(e -> {

			try {
				AnchorPane root = FXMLLoader.load(getClass().getResource(file));
				AdminFXMLController.rootp.getChildren().setAll(root);
				TranslateTransition tran2 = transitionIn(AdminFXMLController.rootp);
				tran2.play();
			} catch (IOException ex) {
				Logger.getLogger(MeunFXMLController.class.getName()).log(Level.SEVERE, null, ex);
			}

		});

	}

	private TranslateTransition transitionIn(AnchorPane node) {
		TranslateTransition transition = new TranslateTransition();
		transition.setNode(node);
		transition.setFromX(2000);
		transition.setToX(0);
		transition.setDuration(Duration.millis(100));
		transition.setDelay(Duration.millis(100));
		transition.setInterpolator(Interpolator.EASE_BOTH);
		transition.setCycleCount(1);

		return transition;

	}

	private TranslateTransition transitionout(AnchorPane node) {
		TranslateTransition transition = new TranslateTransition();
		transition.setNode(node);
		transition.setFromX(0);
		transition.setToX(2000);
		transition.setDuration(Duration.millis(100));
		transition.setDelay(Duration.millis(100));
		transition.setInterpolator(Interpolator.EASE_BOTH);
		transition.setCycleCount(1);

		return transition;
	}

}
