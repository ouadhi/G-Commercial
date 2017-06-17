package com.gestionCommerciale.Controllers.UserController.Role;

import java.net.URL;
import java.util.ResourceBundle;

import com.gestionCommerciale.Controllers.UserController.UIControle.Notification;
import com.gestionCommerciale.Controllers.UserController.UIControle.ShowPane;
import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddRoleFxmlController implements Initializable {

	@FXML
	private TextField role_name;
	@FXML
	private TextArea role_description;
	@FXML
	private JFXButton save_button;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

	@FXML
	private void save_role(ActionEvent event) {

		String role = role_name.getText();
		String description = role_description.getText();
		if (role.isEmpty() || description.isEmpty()) {
			Notification.errorNotification();

		} else {
			Notification.Addnotification();
			// code to refresh table
			new ShowPane().showRole();
		}

	}

}
