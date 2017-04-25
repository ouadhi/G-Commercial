
package CommercialeControles.OperationAchat;

import UIControle.ViewUrl;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;


public class OperationMainController implements Initializable {

    @FXML
    private JFXTextField recherchetxt;
    @FXML
    private AnchorPane space;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            AnchorPane pane  = FXMLLoader.load(getClass().getResource(ViewUrl.selectChauffeur)) ;
            space.getChildren().setAll(pane) ;
        } catch (IOException ex) {
            Logger.getLogger(OperationMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    

    @FXML
    private void rechercher(KeyEvent event) {
    }
    
}
