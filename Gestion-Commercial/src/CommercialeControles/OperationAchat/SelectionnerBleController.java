
package CommercialeControles.OperationAchat;

import CommercialeControles.Ble.BelCell;
import com.gestionCommerciale.HibernateSchema.Ble;
import com.gestionCommerciale.Models.BleQueries;
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


public class SelectionnerBleController implements Initializable {

    @FXML
    private JFXListView<BleListeH> listeBle;
    private BleListeH  bleselected ;
    private BleQueries dockQueries = new BleQueries();

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         List<Ble> listBlesDB = dockQueries.list();
        List<BleListeH> list = new ArrayList<>();
        
        for (int i = 0; i < listBlesDB.size(); i++) {
           list.add(new BleListeH(listBlesDB.get(i), listBlesDB.get(i).getQte())) ; 
         
            
        }
        
        BleListeH ch  = new BleListeH() ; 
        list.add(ch) ; 
        ObservableList<BleListeH> myObservableList = FXCollections.observableList(list);
        listeBle.setItems(myObservableList);
        listeBle.setOrientation(Orientation.HORIZONTAL);
        listeBle.setExpanded(true);
    }    

    @FXML
    private void select(MouseEvent event) {
        
        bleselected =  (BleListeH) listeBle.getSelectionModel().getSelectedItem(); 
        FinOperationController.ble = bleselected  ; 
    }
    
}
