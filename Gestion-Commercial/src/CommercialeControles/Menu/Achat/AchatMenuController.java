
package CommercialeControles.Menu.Achat;

import UIControle.ShowPane;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class AchatMenuController implements Initializable {

    @FXML
    private JFXButton home;
    @FXML
    private JFXButton Dock;
    @FXML
    private JFXButton Ble;
    @FXML
    private JFXButton Rapport;
    @FXML
    private ImageView homeimage;
    @FXML
    private ImageView dockicon;
    @FXML
    private ImageView bleicon;
    @FXML
    private ImageView rapporticon;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void showhome(ActionEvent event) throws IOException {


         new ShowPane().showListAchat();
         
    }

    @FXML
    private void showDock(ActionEvent event) {
        
        new  ShowPane().showDock();
    }

    @FXML
    private void showBle(ActionEvent event) {
        
        new  ShowPane().showBle();
    }

    @FXML
    private void showRapport(ActionEvent event) {
    }

    @FXML
    private void exited(MouseEvent event) {
        Image img = new Image(getClass().getResourceAsStream("/icons/HomeGry.png"));
        homeimage.setImage(img);
    }

    @FXML
    private void entered(MouseEvent event) {
        Image img = new Image(getClass().getResourceAsStream("/icons/homegreen.png"));
        homeimage.setImage(img);
    }

    @FXML
    private void dockexited(MouseEvent event) {
         Image img = new Image(getClass().getResourceAsStream("/icons/DockGry.png"));
        dockicon.setImage(img);
    }

    @FXML
    private void dockentered(MouseEvent event) {
         Image img = new Image(getClass().getResourceAsStream("/icons/Dockgreen.png"));
        dockicon.setImage(img);
    }

    @FXML
    private void BleOut(MouseEvent event) {
         Image img = new Image(getClass().getResourceAsStream("/icons/BleGry.png"));
        bleicon.setImage(img);
    }

    @FXML
    private void bleIN(MouseEvent event) {
         Image img = new Image(getClass().getResourceAsStream("/icons/Blegreen.png"));
        bleicon.setImage(img);
    }

    @FXML
    private void rapportOut(MouseEvent event) {
         Image img = new Image(getClass().getResourceAsStream("/icons/RapportGry.png"));
        rapporticon.setImage(img);
    }

    @FXML
    private void rapportIN(MouseEvent event) {
         Image img = new Image(getClass().getResourceAsStream("/icons/statgreen.png"));
        rapporticon.setImage(img);
    }
    
}
