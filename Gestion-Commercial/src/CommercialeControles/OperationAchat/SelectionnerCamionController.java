
package CommercialeControles.OperationAchat;

import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.input.MouseEvent;


public class SelectionnerCamionController implements Initializable {

    @FXML
    private JFXListView<CamionListeH> listeCamion;
    private CamionListeH camion; 

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         List<CamionListeH> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new CamionListeH("JMC", "12345TY78909")) ; 
            
        }
        
        CamionListeH ch  = new CamionListeH() ; 
        list.add(ch) ; 
        ObservableList<CamionListeH> myObservableList = FXCollections.observableList(list);
        listeCamion.setItems(myObservableList);
        listeCamion.setOrientation(Orientation.HORIZONTAL);
        listeCamion.setExpanded(true);
       
    }    

    @FXML
    private void select(MouseEvent event) {
        
        camion =   (CamionListeH) listeCamion.getSelectionModel().getSelectedItem(); 
        FinOperationController.camion =  camion  ; 
    }
    
}
