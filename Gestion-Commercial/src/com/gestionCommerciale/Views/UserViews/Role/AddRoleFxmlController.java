package  com.gestionCommerciale.Views.UserViews.Role;

import com.gestionCommerciale.Views.UIControle.Notification;
import com.gestionCommerciale.Views.UserViews.UIControle.ShowPane;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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
            new ShowPane().showRole(); 
        }

    }

}
