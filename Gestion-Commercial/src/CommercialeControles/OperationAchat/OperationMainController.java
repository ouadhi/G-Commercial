
package CommercialeControles.OperationAchat;

import UIControle.ViewUrl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
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
    @FXML
    private JFXButton suivant;
    @FXML
    private JFXButton precedent;
    
    private  int etape ; 
    private AnchorPane chauffeur  , camion , Ble , information  ; 

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            chauffeur = FXMLLoader.load(getClass().getResource(ViewUrl.selectChauffeur)) ;
            camion = FXMLLoader.load(getClass().getResource(ViewUrl.selectCamion)) ;
            Ble = FXMLLoader.load(getClass().getResource(ViewUrl.selectBle)) ;
            information = FXMLLoader.load(getClass().getResource(ViewUrl.informationAcaht)) ;
        } catch (Exception e) {
        }
        
        etape = 1 ; 
        
        quelleEtape(etape);
        
    }    

    @FXML
    private void rechercher(KeyEvent event) {
    }

    @FXML
    private void methodesuivant(ActionEvent event) {
        if (this.etape<=4) {
            etape++ ; 
            quelleEtape(etape);
        }
    }

    @FXML
    private void methodeprecedent(ActionEvent event) {
        if (etape>=2) {
            --etape ; 
            quelleEtape(etape);
        }
          
    }
    
    
    public void showEtape( AnchorPane pane){
      
            space.getChildren().setAll(pane) ;
         
    }
    
    public void quelleEtape ( int etape ){
        switch(etape) {
            case 1 : 
                showEtape(chauffeur) ; 
                break;
            case 2 :
                showEtape(camion) ; 
                break;
            case 3 :
                showEtape(Ble) ;
                break;
            default:
                showEtape(information) ;
                FinOperationController.stateicon();
                break;
        }
    }
    
}
