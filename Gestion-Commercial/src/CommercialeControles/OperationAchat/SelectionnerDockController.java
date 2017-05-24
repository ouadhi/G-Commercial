
package CommercialeControles.OperationAchat;

import com.gestionCommerciale.HibernateSchema.Dock;
import com.gestionCommerciale.Models.DockQueries;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;


public class SelectionnerDockController implements Initializable {

    
     private DockQueries dockQueries = new DockQueries();
    @FXML
    private JFXListView<DockListeH> listeDock;
    public  static JFXListView<DockListeH> listeDocks;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listeDocks = listeDock ; 
        
        List<Dock> listDocksDB = dockQueries.list();
        
        List<DockListeH> list = new ArrayList<>();
        for (int i = 0; i < listDocksDB.size(); i++) {
            list.add(new DockListeH(listDocksDB.get(i))) ; 
        }
       list.add(new DockListeH()) ; 
        ObservableList<DockListeH> myObservableList = FXCollections.observableList(list);
        listeDock.setItems(myObservableList);
        listeDock.setExpanded(true);
       
    }    

    @FXML
    private void select(MouseEvent event) {
        FinOperationController.dock = listeDock.getSelectionModel().getSelectedItem()  ; 
    }
    
}
