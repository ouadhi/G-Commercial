
package CommercialeControles.Vente;

import CommercialeControles.OperationAchat.ChauffeurListH;
import UIControle.ViewUrl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


public class SelectionnerCahffeurController implements Initializable {

    @FXML
    private JFXListView<ChauffeurListH> listeChaffeur;
    @FXML
    private JFXButton suivant;

    
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
    private void select(MouseEvent event)  {
        
    }

    @FXML
    private void nextEtape(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(ViewUrl.selectProduit)); 
            OperationVenteController.staticpane.getChildren().setAll(pane) ;
        } catch (IOException ex) {
            Logger.getLogger(SelectionnerCahffeurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
