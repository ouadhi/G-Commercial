
package CommercialeControles;

import UIControle.ShowPane;
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
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class LeftMenu2Controller implements Initializable {

    @FXML
    private JFXButton home;
    @FXML
    private JFXButton chauffeur;
    @FXML
    private JFXButton camion;
    @FXML
    private JFXButton rapport;
    @FXML
    private ImageView iconHome;
    @FXML
    private ImageView iconCamion;
    @FXML
    private ImageView iconRapport;
    @FXML
    private ImageView iconChauffeur;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void showhome(ActionEvent event) {
       new ShowPane().showHome(event);  
    }

    @FXML
    private void showchauffeur(ActionEvent event) {
         new ShowPane().showChauffeur();
    }

    @FXML
    private void showcamion(ActionEvent event) {
          new ShowPane().showCamion();
    }

    @FXML
    private void showrapport(ActionEvent event) {
      new ShowPane().showUIRapport(event);
    }

    @FXML
    private void OutHome(MouseEvent event) {
        Image img = new Image(getClass().getResourceAsStream("/icons/HomeGry.png"));
        iconHome.setImage(img);
    }

    @FXML
    private void InHome(MouseEvent event) {
        Image img = new Image(getClass().getResourceAsStream("/icons/HomeMagent.png"));
        iconHome.setImage(img);
    }

    @FXML
    private void OutChauffeur(MouseEvent event) {
        Image img = new Image(getClass().getResourceAsStream("/icons/ChauffeurGry.png"));
        iconChauffeur.setImage(img);
    }

    @FXML
    private void InChauffeur(MouseEvent event) {
        Image img = new Image(getClass().getResourceAsStream("/icons/ChauffeurMagent.png"));
        iconChauffeur.setImage(img);
    }

    @FXML
    private void OutCamion(MouseEvent event) {
        Image img = new Image(getClass().getResourceAsStream("/icons/CamionGry.png"));
        iconCamion.setImage(img);
    }

    @FXML
    private void InCamion(MouseEvent event) {
        Image img = new Image(getClass().getResourceAsStream("/icons/CamionMagent.png"));
        iconCamion.setImage(img);
    }

    @FXML
    private void OutRapport(MouseEvent event) {
        Image img = new Image(getClass().getResourceAsStream("/icons/RapportGry.png"));
        iconRapport.setImage(img);
    }

    @FXML
    private void InRapport(MouseEvent event) {
        Image img = new Image(getClass().getResourceAsStream("/icons/RapportMagent.png"));
        iconRapport.setImage(img);
    }
    
}
