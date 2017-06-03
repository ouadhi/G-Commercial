
package CommercialeControles;

import UIControle.Methode;
import UIControle.StageDialog;
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
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


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
         
        try {
            AnchorPane pane  = FXMLLoader.load(getClass().getResource(ViewUrl.infoEntreprise))  ;
            StageDialog dialog  =  new StageDialog(stage, pane) ;
            dialog.show();
        } catch (IOException ex) {
            Logger.getLogger(MoreMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    @FXML
    private void showparametre(ActionEvent event) {
        
        try {
            AnchorPane pane  = FXMLLoader.load(getClass().getResource(ViewUrl.parametreView))  ;
            StageDialog dialog  =  new StageDialog(stage, pane) ;
            dialog.show();
        } catch (IOException ex) {
            Logger.getLogger(MoreMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
