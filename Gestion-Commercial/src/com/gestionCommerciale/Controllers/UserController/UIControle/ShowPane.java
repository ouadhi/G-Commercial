
package com.gestionCommerciale.Controllers.UserController.UIControle;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gestionCommerciale.Controllers.UserController.Administrator.AdminFXMLController;
import com.gestionCommerciale.Controllers.UserController.Users_List.Users_ListController;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class ShowPane {

	public void showRole() {
		try {
<<<<<<< HEAD
			FXMLLoader loader = new FXMLLoader();

=======
>>>>>>> 48e5978c8ca1edc489606e1fad7288796221534e
			AnchorPane root = FXMLLoader.load(getClass().getResource("/Role/RoleFXML.fxml"));

			AdminFXMLController.rootp.getChildren().setAll(root);
		} catch (IOException ex) {
			Logger.getLogger(Users_ListController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void showUserList() {
		try {
<<<<<<< HEAD
			FXMLLoader loader = new FXMLLoader();

=======
>>>>>>> 48e5978c8ca1edc489606e1fad7288796221534e
			AnchorPane root = FXMLLoader.load(getClass().getResource("/Users_List/Users_List.fxml"));

			AdminFXMLController.rootp.getChildren().setAll(root);
		} catch (IOException ex) {
			Logger.getLogger(Users_ListController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
