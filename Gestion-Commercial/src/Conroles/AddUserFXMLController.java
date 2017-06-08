package Conroles;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import UIControle.Notification;
import UIControle.ShowPane;
import com.gestionCommerciale.HibernateSchema.User;
import com.gestionCommerciale.Models.UserQueries;

public class AddUserFXMLController implements Initializable {

    @FXML
    private TextField fullname;
    @FXML
    private TextField password;
    @FXML
    private TextField password_confirm;
    @FXML
    private ComboBox<String> role;
    @FXML
    private JFXButton save_button;
    @FXML
    private JFXButton cancel_button;

    String listeItemsRoles[] = {"Administrateur", "Agent"};


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        role.getItems().setAll(listeItemsRoles);

    }

    @FXML
    private void save(ActionEvent event) {

        String username_txt = fullname.getText();
        String password_txt = password.getText();
        String password_txt_confirm = password_confirm.getText();
        String role_txt = role.getSelectionModel().getSelectedItem();

        if (username_txt.isEmpty() || password_txt.isEmpty() || password_txt_confirm.isEmpty()
                || !password_txt.equals(password_txt_confirm)) {

            Notification.errorNotification();
        } else {
            if ((UserQueries.getUserByName(username_txt)!=null) ) {
                //notification for user already exists

                Notification.errorNotificationUserExists();
            } else {
                // add user to database
                try {
                    User user = new User(username_txt, password_txt, role_txt);
                    UserQueries.SaveOrUpdate(user);
                    Notification.Addnotification();
                    new ShowPane().showUserList();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @FXML
    private void cancel(ActionEvent event) {

        new ShowPane().showUserList();
    }

}
