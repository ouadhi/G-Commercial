package com.gestionCommerciale.Controllers.UserController.Login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;

import com.gestionCommerciale.Views.UserViews.loginform.Erreurmsg;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import UIControle.ViewUrl;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 *
 * @author OUADHI
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private JFXPasswordField password;

    @FXML
    private ImageView passwordicon;
    
    @FXML
    private ImageView usericon;

    @FXML
    private Label erreurlabel;

    @FXML
    private ImageView logo;

    @FXML
    private JFXTextField username;

    @FXML
    private AnchorPane rootpane;

    String txt_password;
    String txt_username;

    @FXML
    void connecter(ActionEvent event) {

        txt_username = username.getText();
        txt_password = password.getText();

        if (txt_password.isEmpty() || txt_username.isEmpty()) {
            erreurlabel.setText(Erreurmsg.getChamps_vide());
        } else {
            if (txt_password.equals("admin") && txt_username.equals("admin")) {

                try {
                    // AnchorPane root =
                    // FXMLLoader.load(getClass().getResource("Splash.fxml"));
                    Parent root = FXMLLoader.load(getClass().getResource(ViewUrl.Home1));

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

                            AnchorPane root2 = FXMLLoader
                                    .load(getClass().getResource("/administrateur/AdminFXML.fxml"));
                            rootpane.getChildren().setAll(root2);
                            transitionIN(root2);
                        } catch (IOException ex) {
                            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    });

                    showNotification();
                } catch (IOException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                erreurlabel.setText(Erreurmsg.getPassword_user());
            }

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // possible_utilisateur();
    }

    private void possible_utilisateur() {

        String[] possiblewords = {"karim", "hichem1", "hichem2", "mohammed ouadhi", "mohammed cherberabe"};

        TextFields.bindAutoCompletion(username, possiblewords);

    }

    private void showNotification() {

        Notifications notification = Notifications.create().title("Bonjour").text("Bonjour")
                .hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_RIGHT);
        notification.showConfirm();

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
