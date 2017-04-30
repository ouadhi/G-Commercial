package com.gestionCommerciale.Controllers.UserController.Administrator;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class AdminFXMLController implements Initializable {



    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger humberguerbttn;

    @FXML
    private Label ntifcount;

    @FXML
    private Label msgcount;

    @FXML
    private Label pseudo;


    @FXML
    private JFXButton showprofile;

    @FXML
    private ImageView profileimg;
    @FXML
    private ImageView notificationicon;
    @FXML
    private ImageView msgicon;
    @FXML
    private ImageView searchicon;
    @FXML
    private JFXTextField search;
    @FXML
    private AnchorPane root;
    
    @FXML
    private AnchorPane maipane;

    
    public static AnchorPane rootp , panemain; 

    

    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
              rootp = root ; 
              panemain = maipane ; 
              
        try {
            HBox box = FXMLLoader.load(getClass().getResource("MeunFXML.fxml"));
            drawer.setSidePane(box);
            hummberguer_transaction();

        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

   

   

    public void hummberguer_transaction() {
        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(humberguerbttn);
        transition.setRate(-1);
        humberguerbttn.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (drawer.isShown()) {
                drawer.close();
            } else {
                drawer.open();
            }
        });
    }

    @FXML
    private void showdrawe(ActionEvent event) {
    }

}
