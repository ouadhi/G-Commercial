package CommercialeControles;

import UIControle.ShowPane;
import UIControle.ViewUrl;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainMenuController implements Initializable {

    @FXML
    private JFXButton achat_button;
    @FXML
    private JFXButton vente_button;
    @FXML
    private JFXButton import_button;
    @FXML
    private JFXButton export_button;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image img = new Image(getClass().getResourceAsStream("/icons/select1.png"));
        ImageView icon = new ImageView(img);
        icon.prefHeight(35);
        icon.prefWidth(50);
        Home2FXMLController.bttn_menu.setGraphic(icon);
    }

    @FXML
    private void show_achat(ActionEvent event) throws IOException {
        Image img = new Image(getClass().getResourceAsStream("/icons/select1.png"));
        ImageView icon = new ImageView(img);
        icon.prefHeight(35);
        icon.prefWidth(50);
       
        Home2FXMLController.bttn_menu.setGraphic(icon);
        
        AnchorPane  menu   = FXMLLoader.load(getClass().getResource(ViewUrl.AchatMenu)) ; 
        Home2FXMLController.menup.getChildren().setAll(menu) ;
          changeMenutoSmall();
      
        new ShowPane().showListAchat();
        
    }

    @FXML
    private void show_vente(ActionEvent event) throws IOException {
        Image img = new Image(getClass().getResourceAsStream("/icons/select2.png"));
        ImageView icon = new ImageView(img);
        icon.prefHeight(35);
        icon.prefWidth(50);
        
        Home2FXMLController.bttn_menu.setGraphic(icon);
        
        AnchorPane  menu   = FXMLLoader.load(getClass().getResource(ViewUrl.VenteMenu)) ; 
        Home2FXMLController.menup.getChildren().setAll(menu) ; 
          changeMenutoSmall();
        new ShowPane().showClient();
        
    }

    @FXML
    private void show_import(ActionEvent event) {
         try {
            Image img = new Image(getClass().getResourceAsStream("/icons/select4.png"));
            ImageView icon = new ImageView(img);
            icon.prefHeight(35);
            icon.prefWidth(50);
            Home2FXMLController.bttn_menu.setGraphic(icon);
            
            AnchorPane  menu   = FXMLLoader.load(getClass().getResource(ViewUrl.TransportMenu)) ;
            Home2FXMLController.menup.getChildren().setAll(menu) ; 
            new ShowPane().showCamion();
              changeMenutoSmall();
        } catch (IOException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void show_export(ActionEvent event) {
        
        try {
            Image img = new Image(getClass().getResourceAsStream("/icons/select3.png"));
            ImageView icon = new ImageView(img);
            icon.prefHeight(35);
            icon.prefWidth(50);
            Home2FXMLController.bttn_menu.setGraphic(icon);
            
            
            AnchorPane  menu   = FXMLLoader.load(getClass().getResource(ViewUrl.rapportMenu)) ;
            Home2FXMLController.menup.getChildren().setAll(menu) ;
            new ShowPane().showUIRapport(event);
            changeMenutoSmall();
        } catch (IOException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    private void changeMenutoSmall() {
        AnchorPane pane = (AnchorPane) Home2FXMLController.menup.getChildren().get(0);
        VBox box = (VBox) pane.getChildren().get(0);

        for (Node node : box.getChildren()) {
            if (node instanceof JFXButton) {
                ((JFXButton) node).setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                ((JFXButton) node).setAlignment(Pos.CENTER_RIGHT);
            }
        }
     }

}
