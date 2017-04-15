
package UIControle;

import Conroles.Users_ListController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;


public class ShowPane {
    
    public  void showRole () {
        try {
                FXMLLoader loader = new FXMLLoader();

                AnchorPane root = FXMLLoader.load(getClass().getResource("/Views/RoleFXML.fxml"));

                Conroles.AdminFXMLController.rootp.getChildren().setAll(root);
            } catch (IOException ex) {
                Logger.getLogger(Users_ListController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    
    public void showUserList () {
         try {
                FXMLLoader loader = new FXMLLoader();

                AnchorPane root = FXMLLoader.load(getClass().getResource("/Views/Users_List.fxml"));

                Conroles.AdminFXMLController.rootp.getChildren().setAll(root);
            } catch (IOException ex) {
                Logger.getLogger(Users_ListController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
