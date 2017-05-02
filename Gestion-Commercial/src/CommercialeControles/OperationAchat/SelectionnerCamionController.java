
package CommercialeControles.OperationAchat;

import com.gestionCommerciale.HibernateSchema.Camion;
import com.gestionCommerciale.Models.CamionQueries;
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
    
    private CamionQueries camionQueries= new CamionQueries();

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         List<Camion> listCamionsDB= camionQueries.list();
         List<CamionListeH> list = new ArrayList<>();
        for (int i = 0; i < listCamionsDB.size(); i++) {
            list.add(new CamionListeH(listCamionsDB.get(i).getMarque(), listCamionsDB.get(i).getMatricule())) ; 
            
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
