
package CommercialeControles.Ble;

import CommercialeControles.Camion.ShowdDetailCamionController;
import CommercialeControles.Dock.DockCell;
import CommercialeControles.Dock.ModifierDockController;
import UIControle.Transition;
import UIControle.ViewUrl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;


public class BleSlideController implements Initializable {

    @FXML
    private JFXButton last;
    @FXML
    private JFXButton next;
    @FXML
    private AnchorPane MainPane;
    
    private int  i  = 0  ; 
    private  JFXListView<BelCell> liste ; 

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void lastBle(ActionEvent event) {
        if (i > 0) {
            --i;
         
            Transition transition = new Transition(MainPane, getBle(i),2000, 0, -2000) ; 
            transition.show();

            if (i == 0) {
                last.setDisable(true);
            } else {
                last.setDisable(false);
                next.setDisable(false);
            }
        }
    }

    @FXML
    private void nextBle(ActionEvent event) {
        if (i < liste.getItems().size()) {
          i++;
            Transition transition = new Transition(MainPane, getBle(i),2000, 0, -2000) ; 
            transition.show();
            
            if (i == liste.getItems().size() - 1) {
                next.setDisable(true);
            } else {
                next.setDisable(false);
                last.setDisable(false);
            }
        }
    }
    
   
    
     public AnchorPane getBle(int index) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(ViewUrl.modifierBle));
            loader.load();

            BelCell ble = liste.getItems().get(index);

            ModifierBleController Modifier =  loader.getController() ;
            Modifier.setData(ble.getCode(), ble.getQuantite(), ble.getPrix());

            AnchorPane pane = loader.getRoot();

            return pane  ;  
        } catch (IOException ex) {
            Logger.getLogger(ShowdDetailCamionController.class.getName()).log(Level.SEVERE, null, ex);
            return null ; 
        }

    }
    
    public  void  setData  (int rowselected , JFXListView<BelCell> liste ){
        i  =  rowselected ; 
        this.liste =   liste   ;
        MainPane.getChildren().setAll(getBle(rowselected)) ; 
    }
    
    
}
