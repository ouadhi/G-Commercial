
package CommercialeControles.Ble;


import CommercialeControles.Client.ClientListController;
import CommercialeControles.Dock.ShowDockSlideController;
import UIControle.Methode;
import UIControle.StageDialog;
import UIControle.ViewUrl;
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
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class BleListeController implements Initializable {

    @FXML
    private Label total;
    @FXML
    private MenuButton Order;
    @FXML
    private MenuItem bycode;
    @FXML
    private MenuItem byquantite;
    @FXML
    private MenuItem byprix;
    @FXML
    private MenuButton NbShow;
    @FXML
    private MenuItem btn20;
    @FXML
    private MenuItem btn50;
    @FXML
    private MenuItem btn100;
    @FXML
    private MenuItem btntout;
    @FXML
    private JFXButton ajouter;
    @FXML
    private JFXTextField rechreche;
    @FXML
    private JFXListView<BelCell> listeBle;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        List<BelCell> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new BelCell(i, 50000, 50));
        }

        ObservableList<BelCell> myObservableList = FXCollections.observableList(list);
        listeBle.setItems(myObservableList);
        
        listeBle.setExpanded(true);
        
        
        setTotale();

    }    

    @FXML
    private void oderByCode(ActionEvent event) {
        Order.setText("Code");
    }

    @FXML
    private void orederByquantite(ActionEvent event) {
        Order.setText("Quantité");
    }

    @FXML
    private void OrderByprix(ActionEvent event) {
        Order.setText("Prix");
    }

    @FXML
    private void setOrder(ActionEvent event) {
    }

    @FXML
    private void show20(ActionEvent event) {
        NbShow.setText("20");
    }

    @FXML
    private void shwo50(ActionEvent event) {
        NbShow.setText("50");
    }

    @FXML
    private void show100(ActionEvent event) {
        NbShow.setText("100");
    }

    @FXML
    private void showtout(ActionEvent event) {
        NbShow.setText("Tout");
    }

    @FXML
    private void showAddStage(ActionEvent event) {
        
          try {
            Stage stage =  Methode.getStage(event) ; 
            
            AnchorPane pane  = FXMLLoader.load(getClass().getResource(ViewUrl.AjouterBle)) ;
            
            StageDialog dialog  =  new StageDialog(stage, pane) ; 
            dialog.show();
            
        } catch (IOException ex) {
            
        }
    }

    @FXML
    private void rechrecher(ActionEvent event) {
    }

    @FXML
    private void showDockSlide(MouseEvent event) {
        
        try {
            
            int seletedrow  = listeBle.getSelectionModel().getSelectedIndex()  ; 
            Stage stage =  Methode.getStageMouses(event) ; 
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(ViewUrl.slideBle));
            loader.load();
            
            BleSlideController control =  loader.getController() ;
            control.setData(seletedrow, listeBle);
            
            AnchorPane root = loader.getRoot();
            
            StageDialog dialog = new StageDialog(stage, root) ; 
            dialog.show();
            
        } catch (IOException ex) {
            Logger.getLogger(ClientListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private  void setTotale  () {
        total.setText(Integer.toString(listeBle.getItems().size()));
    }

    
}
