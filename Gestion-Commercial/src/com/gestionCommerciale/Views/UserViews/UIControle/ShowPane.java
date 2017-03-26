
package com.gestionCommerciale.Views.UserViews.UIControle;

import com.gestionCommerciale.Views.UserViews.Users_List.Users_ListController;
import com.gestionCommerciale.Views.UserViews.administrateur.AdminFXMLController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;


public class ShowPane {
    
    public  void showRole () {
        try {
                FXMLLoader loader = new FXMLLoader();

                AnchorPane root = FXMLLoader.load(getClass().getResource("/Role/RoleFXML.fxml"));

                AdminFXMLController.rootp.getChildren().setAll(root);
            } catch (IOException ex) {
                Logger.getLogger(Users_ListController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    
    public void showUserList () {
         try {
                FXMLLoader loader = new FXMLLoader();

                AnchorPane root = FXMLLoader.load(getClass().getResource("/Users_List/Users_List.fxml"));

                AdminFXMLController.rootp.getChildren().setAll(root);
            } catch (IOException ex) {
                Logger.getLogger(Users_ListController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
