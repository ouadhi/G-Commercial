
package CommercialeControles.Banque;

import CommercialeControles.Ble.BelCell;
import UIControle.Methode;
import UIControle.StageDialog;
import UIControle.ViewUrl;
import com.gestionCommerciale.HibernateSchema.Banque;
import com.gestionCommerciale.Models.BanqueQueries;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.textfield.TextFields;


public class BanqueListeController implements Initializable {

    @FXML
    private Label total;
    @FXML
    private JFXTextField rechreche;
    @FXML
    private JFXButton ajouter;
    @FXML
    private JFXListView<BanqueCell> listebanque;
    
    private  BanqueQueries querie  = new  BanqueQueries()  ; 
    
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        List<BanqueCell> list = new ArrayList<>();
        
        for (int i = 0; i < querie.list().size() ; i++) {
            //list.add(new BanqueCell( querie.list().get(i))) ; 
            
        }
        ObservableList<BanqueCell> myObservableList = FXCollections.observableList(list);
        listebanque.setItems(myObservableList);
        listebanque.setExpanded(true);
        
        setTotale();
        
      
    }    

    @FXML
    private void showAddStage(ActionEvent event) {
        
        try {
            AnchorPane pane  =  FXMLLoader.load(getClass().getResource(ViewUrl.AjouterBanque)) ;
            StageDialog dialog  =  new StageDialog(Methode.getStage(event), pane) ;  
            dialog.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterBanqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setTotale() {
       total.setText(""+listebanque.getItems().size());
    }

    @FXML
    private void recherche(KeyEvent event) {
       listebanque.getItems().clear();
         List<Banque> listbanque  =    querie.listRechreche(rechreche.getText()) ; 
       List<BanqueCell> list = new ArrayList<>();
        
        for (int i = 0; i < listbanque.size() ; i++) {
            list.add(new BanqueCell(listbanque.get(i))) ; 
            
        }
        ObservableList<BanqueCell> myObservableList = FXCollections.observableList(list);
        listebanque.setItems(myObservableList);
        listebanque.setExpanded(true);
        
        setTotale();
    }
    
     public void possibleMot() {
      
        ArrayList<String> list = new ArrayList<>();
        list.add("karim");
        list.add("hichem1");
        list.add("hichem2");
        list.add("mohammed ouadhi");
        list.add("mohammed cherberabe");

        TextFields.bindAutoCompletion(rechreche, list);

    }

    
}
