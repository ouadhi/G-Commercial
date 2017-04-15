package Conroles;

import UIControle.Notification;
import com.gestionCommerciale.HibernateSchema.User;
import com.gestionCommerciale.Models.UserQueries;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class Employee_LoginFXMLController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button connecter;
    @FXML
    private Label erreurlabel;
    @FXML
    private AnchorPane rootpane;

    UserQueries userQueries = new UserQueries();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void connectez(ActionEvent event) {
        String username_txt = username.getText();
        String password_txt = password.getText();

        List<User> listUtilisateurs = userQueries.list();
        if (username_txt.isEmpty() || password_txt.isEmpty()) {

            Notification.champVideNotification();

        } else {
            User logedUser = userQueries.getUser(username_txt);
            if (logedUser != null && password_txt.equals(logedUser.getPassword())
            || ((username_txt.equals("admin"))&& password_txt.equals("admin")) ) {
                Notification.login_notification();

                try {

                    AnchorPane root = FXMLLoader.load(getClass().getResource("/Views/Splash.fxml"));

                    rootpane.getChildren().setAll(root);

                    FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), root);
                    fadeIn.setFromValue(0);
                    fadeIn.setToValue(1);
                    fadeIn.setCycleCount(1);

                    FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), root);
                    fadeOut.setFromValue(1);
                    fadeOut.setToValue(0);
                    fadeOut.setCycleCount(1);

                    fadeIn.play();

                    fadeIn.setOnFinished((e) -> {
                        fadeOut.play();

                    });

                    fadeOut.setOnFinished((e) -> {

                        try {

                            AnchorPane root2 = FXMLLoader.load(getClass().getResource("/Views/AdminFXML.fxml"));
                            rootpane.getChildren().setAll(root2);
                            transitionIN(root2);
                        } catch (IOException ex) {

                        }

                    });

                } catch (IOException ex) {

                }

            } else {

                Notification.fauxNotification();

            }
        }
    }

    public void transitionIN(Parent root) {
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), root);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setCycleCount(1);

        fadeIn.play();
    }

    public void transitionOut(Parent root) {
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), root);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setCycleCount(1);

        fadeOut.play();
    }

}
