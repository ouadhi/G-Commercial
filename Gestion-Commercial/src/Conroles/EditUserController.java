package Conroles;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import UIControle.Notification;
import UIControle.ShowPane;
import com.gestionCommerciale.HibernateSchema.User;
import com.gestionCommerciale.Models.UserQueries;
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
    
    String listeItemsRoles[] = {"Administrateur", "Agent"};
    
    UserQueries userQueries = new UserQueries();
    
    User user;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        role.getItems().setAll(listeItemsRoles);
        
    }
    
    public void set_data(String username_t, String password, String role) {
        fullname.setText(username_t);
        username.setText(password);
        this.role.getSelectionModel().select(role);
        //set user to selected user
        user = userQueries.getUserByName(username_t);
        
        role_txt_title.setText(role);
        roletxt.setText(role);
        usernametxt.setText(username_t);
        emailtxt.setText(password);
        
    }
    
    @FXML
    private void deleteUser(ActionEvent event) {
        
        Optional<ButtonType> result = Notification.deleteAlert().showAndWait();
        
        if (result.get() == ButtonType.OK) {
            //delete user from db
            userQueries.delete(user);
            Notification.Deletenotification();
            new ShowPane().showUserList();
        }
    }
    
    @FXML
    private void updateUser(ActionEvent event) {
        
        String password = username.getText();
        
        String username_txt = fullname.getText();
        String role_txt = role.getSelectionModel().getSelectedItem();
        
        if (username_txt.isEmpty() || password.isEmpty()) {
            Notification.errorNotification();
        } else {
            //edit user in database
            user.setNom(password);
            user.setPassword(username_txt);
            user.setType(role_txt);
            userQueries.SaveOrUpdate(user);
            Notification.Updatenotification();
            new ShowPane().showUserList();
        }
        
    }
    
}
