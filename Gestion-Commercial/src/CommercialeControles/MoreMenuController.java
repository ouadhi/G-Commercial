/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommercialeControles;

import UIControle.Methode;
import UIControle.ViewUrl;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mac
 */
public class MoreMenuController implements Initializable {

    @FXML
    private JFXButton utilisateurBttn;
    @FXML
    private JFXButton infobuttn;
    @FXML
    private JFXButton chagerButtn;
    @FXML
    private JFXButton exite;

    
    Stage stage  ; 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void showgestion(ActionEvent event) throws IOException {
        AnchorPane pane  = FXMLLoader.load(getClass().getResource(ViewUrl.GestionUtilisateur)) ; 
        Scene scene  = new Scene(pane) ; 
        
        this.stage.setScene(scene);
    }

    @FXML
    private void showinfo(ActionEvent event) {
    }

    @FXML
    private void showLoginform(ActionEvent event) throws IOException {
        AnchorPane pane  = FXMLLoader.load(getClass().getResource(ViewUrl.Login)) ; 
        Scene scene  = new Scene(pane) ; 
        
        this.stage.setScene(scene);
      
        
    }

    @FXML
    private void quitter(ActionEvent event) {
        System.exit(0);
    }
    
    
    public void setStage  (Stage curent)  {
        
        this.stage = curent ; 
        
    }
}
