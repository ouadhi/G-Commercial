package Conroles;

import java.net.URL;
import java.util.ResourceBundle;

import com.gestionCommerciale.HibernateSchema.Role;
import com.gestionCommerciale.Models.RoleQueries;
import com.jfoenix.controls.JFXButton;

import UIControle.Notification;
import UIControle.ShowPane;
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
	@FXML
	private JFXButton cancel;

	RoleQueries roleQueries = new RoleQueries();

	@FXML
	private void cancel(ActionEvent event) {
		new ShowPane().showRole();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

	@FXML
	private void save_role(ActionEvent event) {

		String roleText = role_name.getText();
		String description = role_description.getText();
		if (roleText.isEmpty() || description.isEmpty()) {
			Notification.errorNotification();
		} else {
			Role role = new Role(roleText, description);
			roleQueries.SaveOrUpdate(role);
			Notification.Addnotification();
			new ShowPane().showRole();
		}

	}

}
