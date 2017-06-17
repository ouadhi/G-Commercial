
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
			FXMLLoader loader = new FXMLLoader();

			AnchorPane root = FXMLLoader.load(getClass().getResource("/Role/RoleFXML.fxml"));

			AdminFXMLController.rootp.getChildren().setAll(root);
		} catch (IOException ex) {
			Logger.getLogger(Users_ListController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void showUserList() {
		try {
			FXMLLoader loader = new FXMLLoader();

			AnchorPane root = FXMLLoader.load(getClass().getResource("/Users_List/Users_List.fxml"));

			AdminFXMLController.rootp.getChildren().setAll(root);
		} catch (IOException ex) {
			Logger.getLogger(Users_ListController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
