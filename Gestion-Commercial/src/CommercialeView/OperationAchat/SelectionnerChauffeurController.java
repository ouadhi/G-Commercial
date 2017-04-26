
package CommercialeView.OperationAchat;

import CommercialeControles.Dock.DockCell;
import CommercialeControles.OperationAchat.ChauffeurListH;
import CommercialeControles.OperationAchat.FinOperationController;
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

public class SelectionnerChauffeurController implements Initializable {

    @FXML
    private JFXListView<ChauffeurListH> listeChaffeur;
     ChauffeurListH chauffeurSelected ; 

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       List<ChauffeurListH> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new ChauffeurListH("OUADHI", "Mohammed", "0553485908")) ; 
            
        }
        
        ChauffeurListH ch  = new ChauffeurListH()  ; 
        list.add(ch) ; 
        ObservableList<ChauffeurListH> myObservableList = FXCollections.observableList(list);
        listeChaffeur.setItems(myObservableList);
        listeChaffeur.setOrientation(Orientation.HORIZONTAL);
        listeChaffeur.setExpanded(true);
     
    }    

    @FXML
    private void select(MouseEvent event) {
       
        chauffeurSelected  = listeChaffeur.getSelectionModel().getSelectedItem() ; 
        FinOperationController.chauffeur =  chauffeurSelected ; 
        
    }
    
}
