package com.gestionCommerciale.Controllers.UserController.AddUser;

import com.gestionCommerciale.Controllers.UserController.UIControle.Notification;
import com.gestionCommerciale.Controllers.UserController.UIControle.ShowPane;
import com.gestionCommerciale.HibernateSchema.User;
import com.gestionCommerciale.Models.UserQueries;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;



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
    
    UserQueries userQueries= new UserQueries();

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void save(ActionEvent event) {
        
        String username_txt = fullname.getText();
        String password_txt = password.getText();
        String password_txt_confirm = password_confirm.getText();
        String role_txt = role.getSelectionModel().toString();
        
        if (username_txt.isEmpty() || password_txt.isEmpty() || password_txt_confirm.isEmpty()) {
            Notification.errorNotification();
           
        } else { 
            //add user
            //User user= new User(username_txt, password_txt, role_txt);
            //userQueries.SaveOrUpdate(user);
            Notification.Addnotification();
            new ShowPane().showUserList();

        }
    }

}
