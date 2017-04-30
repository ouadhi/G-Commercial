
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

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void showhome(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/CommercialeView/HomeFXML.fxml"));
            Scene scene = new Scene(pane) ; 
            Stage  stage = (Stage) ((Node) (event.getSource())).getScene().getWindow() ; 
            
            stage.setScene(scene);
            
            
        } catch (IOException ex) {
            Logger.getLogger(HomeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
      
    }
    
}