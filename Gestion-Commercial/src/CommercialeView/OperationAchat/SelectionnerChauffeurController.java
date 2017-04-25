/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommercialeView.OperationAchat;

import CommercialeControles.Dock.DockCell;
import CommercialeControles.OperationAchat.ChauffeurListH;
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

/**
 * FXML Controller class
 *
 * @author mac
 */
public class SelectionnerChauffeurController implements Initializable {

    @FXML
    private JFXListView<ChauffeurListH> listeChaffeur;

    
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
    
}
