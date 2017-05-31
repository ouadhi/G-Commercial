
package CommercialeControles.OperationAchat;

import com.gestionCommerciale.HibernateSchema.Chauffeur;
import com.gestionCommerciale.Models.ChauffeurQueries;
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

public class SelectionnerChauffeurController implements Initializable {

    @FXML
    private JFXListView<ChauffeurListH> listeChaffeur;
     ChauffeurListH chauffeurSelected ; 
     private ChauffeurQueries chauffeurQueries= new ChauffeurQueries();
    @FXML
    private JFXTextField rechreche;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<ChauffeurListH> list = new ArrayList<>();
        List<Chauffeur> listChauffeursDB= chauffeurQueries.list();
        for (int i = 0; i < listChauffeursDB.size(); i++) {
            list.add(new ChauffeurListH(listChauffeursDB.get(i))) ; 
            
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

    @FXML
    private void recherche(KeyEvent event) {
        
         listeChaffeur.getItems().clear();
         List<ChauffeurListH> list = new ArrayList<>();
        List<Chauffeur> listChauffeursDB= chauffeurQueries.listRecherche(rechreche.getText()) ; 
        for (int i = 0; i < listChauffeursDB.size(); i++) {
            list.add(new ChauffeurListH(listChauffeursDB.get(i))) ; 
            
        }
        
        ChauffeurListH ch  = new ChauffeurListH()  ; 
        list.add(ch) ; 
        ObservableList<ChauffeurListH> myObservableList = FXCollections.observableList(list);
        listeChaffeur.setItems(myObservableList);
    }
    
}
