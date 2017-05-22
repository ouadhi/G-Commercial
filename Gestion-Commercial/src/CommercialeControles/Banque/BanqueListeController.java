
package CommercialeControles.Banque;

import CommercialeControles.Ble.BelCell;
import UIControle.Methode;
import UIControle.StageDialog;
import UIControle.ViewUrl;
import com.gestionCommerciale.HibernateSchema.Banque;
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


public class BanqueListeController implements Initializable {

    @FXML
    private Label total;
    @FXML
    private JFXTextField rechreche;
    @FXML
    private JFXButton ajouter;
    @FXML
    private JFXListView<BanqueCell> listebanque;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<BanqueCell> list = new ArrayList<>();
        for (int i = 0; i < 10 ; i++) {
            list.add(new BanqueCell(new Banque())) ; 
            
        }
        ObservableList<BanqueCell> myObservableList = FXCollections.observableList(list);
        listebanque.setItems(myObservableList);
        listebanque.setExpanded(true);
        setTotale();
    }    

   

    @FXML
    private void rechercher(KeyEvent event) {
        System.out.println(rechreche.getText());
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

    
}
