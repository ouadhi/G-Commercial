
package CommercialeControles.OperationAchat;

import CommercialeControles.Dock.DockCell;
import com.gestionCommerciale.HibernateSchema.Dock;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


public class SelectionnerChauffeurController implements Initializable {

    @FXML
    private JFXListView<ChauffeurListH> listeChaffeur;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
        List<ChauffeurListH> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(new ChauffeurListH("OUADHI", "Mohamed", "0553485908")); 
            
        }
        ObservableList<ChauffeurListH> myObservableList = FXCollections.observableList(list);
        listeChaffeur.setItems(myObservableList);
        listeChaffeur.setExpanded(true);
       
        
    }    
    
}
