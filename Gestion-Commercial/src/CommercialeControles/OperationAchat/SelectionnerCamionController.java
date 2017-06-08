
package CommercialeControles.OperationAchat;

import com.gestionCommerciale.HibernateSchema.Camion;
import com.gestionCommerciale.Models.CamionQueries;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


public class SelectionnerCamionController implements Initializable {

    @FXML
    private JFXListView<CamionListeH> listeCamion;
    private CamionListeH camion; 
    
    private CamionQueries camionQueries= new CamionQueries();
    @FXML
    private JFXTextField rechreche;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         List<Camion> listCamionsDB= camionQueries.list();
         List<CamionListeH> list = new ArrayList<>();
        for (int i = 0; i < listCamionsDB.size(); i++) {
            list.add(new CamionListeH(listCamionsDB.get(i))) ; 
            
        }
        
        CamionListeH ch  = new CamionListeH() ; 
        //list.add(ch) ; 
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

    @FXML
    private void recherche(KeyEvent event) {
        listeCamion.getItems().clear();
        
        List<Camion> listCamionsDB= camionQueries.listRechreche(rechreche.getText()) ; 
         List<CamionListeH> list = new ArrayList<>();
        for (int i = 0; i < listCamionsDB.size(); i++) {
            list.add(new CamionListeH(listCamionsDB.get(i))) ; 
            
        }
        
        CamionListeH ch  = new CamionListeH() ; 
        //list.add(ch) ; 
        ObservableList<CamionListeH> myObservableList = FXCollections.observableList(list);
        listeCamion.setItems(myObservableList);
        listeCamion.setOrientation(Orientation.HORIZONTAL);
        listeCamion.setExpanded(true);
    }
    
}
