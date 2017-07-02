
package CommercialeControles.Ble;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class AjouterBleExtratController implements Initializable {

    @FXML
    private ImageView close;
    @FXML
    private JFXButton addbttn;
    @FXML
    private JFXButton cancelbttn;
    @FXML
    private JFXTextField code;
    @FXML
    private JFXTextField quntite;
    @FXML
    private JFXTextField prix;
    @FXML
    private Label savelabel;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void close(MouseEvent event) {
    }

    @FXML
    private void saveble(ActionEvent event) {
    }

    @FXML
    private void closestage(ActionEvent event) {
    }
    
}
