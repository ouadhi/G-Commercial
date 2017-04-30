package CommercialeControles;

import UIControle.ShowPane;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class Home2FXMLController implements Initializable {

    private JFXDrawer left_menu;

    public static JFXDrawer draw;
     public static JFXButton bttn_menu;
     private boolean smalShow ; 

    @FXML
    private JFXDrawer main_menu;

    @FXML
    private JFXButton menu_button;
   
    @FXML
    private AnchorPane panel_menu;
    @FXML
    private JFXHamburger hamburguerbutton;
    @FXML
    private AnchorPane menu;
    @FXML
    private AnchorPane workespace;
    public static AnchorPane workespacepane ;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            workespacepane = workespace ; 
            draw = left_menu;
            bttn_menu = menu_button;
            smalShow =  true  ; 

            AnchorPane menu_draw = FXMLLoader.load(getClass().getResource("/CommercialeView/MainMenu.fxml"));
            main_menu.setSidePane(menu_draw);
            
            AnchorPane menu2 = FXMLLoader.load(getClass().getResource("/CommercialeView/LeftMenuFXML.fxml"));
            menu2.setLayoutX(64);
            menu2.setLayoutY(0);
            menu.getChildren().setAll(menu2);
            

            hummberguer_transaction();
            new ShowPane().showChauffeur();

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void hummberguer_transaction() {
        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburguerbutton);
        transition.setRate(-1);
        hamburguerbutton.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();
            
            if (smalShow) {
                showStrongMenu();
            } else {
                showSmallMenu();
            }

        });
    }

    @FXML
    private void showmenu(ActionEvent event) {

        if (main_menu.isShown()) {
            main_menu.close();
        } else {
            main_menu.open();
        }

    }

    @FXML
    private void nextmenu(MouseEvent event) {
//        TranslateTransition translate = transitionIn(panel_menu);
//        translate.play();
    }

    private TranslateTransition transitionout(AnchorPane node) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(node);
        transition.setFromX(30);
        transition.setToX(0);
        transition.setDuration(Duration.millis(100));
        transition.setDelay(Duration.millis(100));
        transition.setInterpolator(Interpolator.EASE_BOTH);
        transition.setCycleCount(1);

        return transition;
    }

    private TranslateTransition transitionIn(AnchorPane node) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(node);
        transition.setFromX(-64);
        transition.setToX(64);
        transition.setDuration(Duration.millis(100));
        transition.setDelay(Duration.millis(100));
        transition.setInterpolator(Interpolator.EASE_BOTH);
        transition.setCycleCount(1);

        return transition;

    }
    
    
    public  void  showSmallMenu ()  {
        try {
            AnchorPane menu2 = FXMLLoader.load(getClass().getResource("/CommercialeView/LeftMenuFXML.fxml"));
            menu2.setLayoutX(64);
            menu2.setLayoutY(0);
            menu.getChildren().setAll(menu2);
            smalShow =  true  ; 
            transitionout(panel_menu).play();
        } catch (IOException ex) {
            Logger.getLogger(Home2FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public  void  showStrongMenu  () {
        
        try {
            AnchorPane menu2 = FXMLLoader.load(getClass().getResource("/CommercialeView/LeftMenu2FXML.fxml"));
            menu.getChildren().setAll(menu2);
            smalShow =  false ; 
            transitionIn(panel_menu).play();       
        } catch (IOException ex) {
            Logger.getLogger(Home2FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
