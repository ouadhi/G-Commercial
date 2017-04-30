
package CommercialeControles.Vente;

import UIControle.ViewUrl;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;


public class OperationVenteController implements Initializable {

    @FXML
    private JFXTextField recherchetxt;
    @FXML
    private AnchorPane workespace;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            AnchorPane pane  =  FXMLLoader.load(getClass().getResource(ViewUrl.selectClient)) ;
            workespace.getChildren().setAll(pane);
        } catch (Exception e) {
        }
        
    }    

    @FXML
    private void rechercher(KeyEvent event) {
    }
    
}
