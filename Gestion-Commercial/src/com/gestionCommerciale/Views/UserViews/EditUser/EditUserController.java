
package com.gestionCommerciale.Views.UserViews.EditUser;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import com.gestionCommerciale.Views.UIControle.Notification;
import com.gestionCommerciale.Views.UserViews.UIControle.ShowPane;
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
    
    String listeItems [] = {"Administrateur" , "Agent" } ; 

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        role.getItems().setAll(listeItems) ; 
      
    }    
    
    
    public void  set_data(String username_t , String password  , String role) {
        fullname.setText(username_t);
        username.setText(password);
        
        role_txt_title.setText(role);
        roletxt.setText(role);
        usernametxt.setText(username_t);
        emailtxt.setText(password);
        
        
    }

    @FXML
    private void deleteUser(ActionEvent event) {
        
        Optional<ButtonType> result = Notification.deleteAlert().showAndWait();
        
        if (result.get() == ButtonType.OK) {
          
            Notification.Deletenotification();
             new ShowPane().showUserList();
        } 
    }

    @FXML
    private void updateUser(ActionEvent event) {
        
      String user  =  fullname.getText() ; 
      String password = username.getText() ; 
      
        if (user.isEmpty() || password.isEmpty()) {
            Notification.errorNotification();
        } else {
            Notification.Updatenotification();
             new ShowPane().showUserList();
        }
        
    }
    
    
    
    
    
    
}
