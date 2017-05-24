package CommercialeControles.Dock;

import UIControle.Methode;
import UIControle.StageDialog;
import UIControle.ViewUrl;
import com.gestionCommerciale.HibernateSchema.Dock;
import com.gestionCommerciale.Models.DockQueries;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DockLisController implements Initializable {

    @FXML
    private Label total;
    @FXML
    private MenuButton Order;
    @FXML
    private MenuItem bycode;
    @FXML
    private MenuItem byname;
    @FXML
    private MenuItem byActivite;
    @FXML
    private MenuItem byregistre;
    private MenuButton NbShow;
    @FXML
    private JFXButton ajouter;
    @FXML
    private JFXListView<DockCell> listedock;
    private DockQueries dockQueries = new DockQueries();
    @FXML
    private JFXTextField recherchetxt;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        List<Dock> listDocksDB = dockQueries.list();
        
        List<DockCell> list = new ArrayList<>();
        for (int i = 0; i < listDocksDB.size(); i++) {
            list.add(new DockCell(listDocksDB.get(i).getIdDock(), listDocksDB.get(i).getNom(),
                     listDocksDB.get(i).getWilaya(), listDocksDB.get(i).getDistance(),
                     listDocksDB.get(i).getPrixUnitTrans()
            ));
        }
        ObservableList<DockCell> myObservableList = FXCollections.observableList(list);
        listedock.setItems(myObservableList);
        listedock.setExpanded(true);
        setTotale();

    }

    @FXML
    private void orederByName(ActionEvent event) {
        Order.setText("Nom");
    }

    @FXML
    private void setOrder(ActionEvent event) {
    }

    private void show20(ActionEvent event) {
        NbShow.setText("20");
    }

    private void shwo50(ActionEvent event) {
        NbShow.setText("50");
    }

    private void show100(ActionEvent event) {
        NbShow.setText("100");
    }

    private void showtout(ActionEvent event) {
        NbShow.setText("Tout");
    }

    @FXML
    private void showAddStage(ActionEvent event) {

        try {
             
            Stage stage = Methode.getStage(event) ; 
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(ViewUrl.AjouterDock));
            loader.load();

            AjouterDockController AddDock = loader.getController();
           AddDock.SetData(listedock , total);

            AnchorPane root = loader.getRoot();

            StageDialog dialog = new StageDialog(stage, root);
            dialog.show();

        } catch (IOException ex) {
            Logger.getLogger(DockLisController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    private void setTotale() {
        total.setText(Integer.toString(listedock.getItems().size()));
    }

    @FXML
    private void oderByCode(ActionEvent event) {
        Order.setText("Code");
    }

    @FXML
    private void OrderByWilaya(ActionEvent event) {
        Order.setText("Wilaya");
    }

    @FXML
    private void oderByDistance(ActionEvent event) {
        Order.setText("Distance");
    }

    @FXML
    private void OderByTarif(ActionEvent event) {
        Order.setText("Tarif");
    }

    @FXML
    private void showDockSlide(MouseEvent event) {

        try {

            int seletedrow = listedock.getSelectionModel().getSelectedIndex();
            Stage stage = Methode.getStageMouses(event);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(ViewUrl.ShowDockSlide));
            loader.load();

            ShowDockSlideController controlClient = loader.getController();
            controlClient.setData(seletedrow, listedock);

            AnchorPane root = loader.getRoot();

            StageDialog dialog = new StageDialog(stage, root);
            dialog.show();

        } catch (IOException ex) {
            Logger.getLogger(DockLisController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void rechercher(KeyEvent event) {
    }

}
