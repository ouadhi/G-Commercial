package com.gestionCommerciale.Controllers.UserController.EditUser;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.gestionCommerciale.Controllers.UserController.UIControle.Notification;
import com.gestionCommerciale.Controllers.UserController.UIControle.ShowPane;
import com.gestionCommerciale.HibernateSchema.User;
import com.gestionCommerciale.Models.UserQueries;
import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EditUserController implements Initializable {
	@FXML
	private Label roletxt;
	@FXML
	private Label fullnametxt;
	@FXML
	private Label role_txt_title;
	@FXML
	private Label usernametxt;
	@FXML
	private Label emailtxt;
	@FXML
	private JFXButton delete_button;
	@FXML
	private TextField fullname;
	@FXML
	private TextField username;
	@FXML
	private ComboBox<String> role;
	@FXML
	private JFXButton update_button;

	String listeItems[] = { "Administrateur", "Agent" };

	@FXML
	private void deleteUser(ActionEvent event) {

		Optional<ButtonType> result = Notification.deleteAlert().showAndWait();
		String user = fullname.getText();

		if (result.get() == ButtonType.OK) {
			User deletedUser = UserQueries.getUserByName(user);
			UserQueries.delete(deletedUser);
			Notification.Deletenotification();
			new ShowPane().showUserList();
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		role.getItems().setAll(listeItems);

	}

	public void set_data(String username_t, String password, String role) {
		fullname.setText(username_t);
		username.setText(password);

		role_txt_title.setText(role);
		roletxt.setText(role);
		usernametxt.setText(username_t);
		emailtxt.setText(password);

	}

	@FXML
	private void updateUser(ActionEvent event) {

		String user = fullname.getText();
		String password = username.getText();

		if (user.isEmpty() || password.isEmpty()) {
			Notification.errorNotification();
		} else {
			Notification.Updatenotification();
			new ShowPane().showUserList();
		}

	}

}
