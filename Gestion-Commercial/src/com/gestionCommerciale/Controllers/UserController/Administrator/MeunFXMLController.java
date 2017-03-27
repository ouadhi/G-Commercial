
package com.gestionCommerciale.Controllers.UserController.Administrator;

import static com.gestionCommerciale.Controllers.UserController.Administrator.AdminFXMLController.rootp;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.CacheHint;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author OUADHI
 */
public class MeunFXMLController implements Initializable {
    
    @FXML
    private JFXButton users_button;
    @FXML
    private JFXButton Roles_button;
    @FXML
    private JFXButton notificaton__button;
    @FXML
    private JFXButton profiles__button;
    @FXML
    private JFXButton settings_button;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    @FXML
    private void show_users_list(ActionEvent event) {
        stage_suivant("/Users_List/Users_List.fxml");
    }
    
    private void stage_suivant(String file) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource(file));
            AdminFXMLController.rootp.getChildren().setAll(root);
            
            TranslateTransition transition = new TranslateTransition();
            transition.setNode(rootp);
            transition.setFromX(2000);
            transition.setToX(0);
            transition.setDuration(Duration.seconds(1));
            transition.setDelay(Duration.seconds(1));
            transition.setInterpolator(Interpolator.EASE_BOTH);
            transition.setCycleCount(1);

            // transition.setAutoReverse(true);
            rootp.setCache(true);
            rootp.setCacheHint(CacheHint.SPEED);
            
            transition.play();
            
            transition.setOnFinished(e -> {
                try {

//                    AnchorPane root = FXMLLoader.load(getClass().getResource(file));
//                    AdminFXMLController.rootp.getChildren().setAll(root);
                } catch (Exception ex) {
                    
                }
                
            });
        } catch (IOException ex) {
            Logger.getLogger(MeunFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
    private void show_notifcation_list(ActionEvent event) {
        
        stage_suivant("/Notification/Notification_sceen.fxml");
    }
    
    @FXML
    void show_setting(ActionEvent event) {
        
        stage_suivant("/Setting/SettingFXML.fxml");
    }
    
    @FXML
    void show_role_liste(ActionEvent event) {
        
        stage_suivant("/Role/RoleFXML.fxml");
    }
    
}
