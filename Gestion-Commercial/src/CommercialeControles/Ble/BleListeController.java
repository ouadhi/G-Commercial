package CommercialeControles.Ble;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.controlsfx.control.textfield.TextFields;

import com.gestionCommerciale.HibernateSchema.Ble;
import com.gestionCommerciale.Models.BleQueries;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import CommercialeControles.Client.ClientListController;
import UIControle.Methode;
import UIControle.StageDialog;
import UIControle.ViewUrl;
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

public class BleListeController implements Initializable {

    @FXML
    private Label total;
    public static Label totalstatic;
    @FXML
    private MenuButton Order;
    @FXML
    private MenuItem byquantite;
    private MenuButton NbShow;
    @FXML
    private JFXButton ajouter;
    @FXML
    private JFXTextField rechreche;
    @FXML
    private JFXListView<BelCell> listeBle;
    public static JFXListView<BelCell> listeBleStatic;
    @FXML
    private Label label;

    @FXML
    private void Archive(ActionEvent event) {
        Order.setText("Archiv\u00E9");
        List<Ble> listBlesDB = BleQueries.listArchived();
        List<BelCell> list = new ArrayList<>();
        for (int i = 0; i < listBlesDB.size(); i++) {
            list.add(new BelCell(listBlesDB.get(i)));
        }
        ObservableList<BelCell> myObservableList = FXCollections.observableList(list);
        listeBle.setItems(myObservableList);
        listeBle.setExpanded(true);
        setTotale();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listeBleStatic  = listeBle  ;  
        totalstatic = total  ; 
        Methode.showMenuItem(Order, label);

        List<Ble> listBlesDB = BleQueries.list();
        List<BelCell> list = new ArrayList<>();
        for (int i = 0; i < listBlesDB.size(); i++) {
            list.add(new BelCell(listBlesDB.get(i)));
        }
        ObservableList<BelCell> myObservableList = FXCollections.observableList(list);
        listeBle.setItems(myObservableList);
        listeBle.setExpanded(true);
        setTotale();

    }

    @FXML
    private void NonArchiv(ActionEvent event) {
        Order.setText("Non Archiv\u00E9");
        List<Ble> listBlesDB = BleQueries.list();
        List<BelCell> list = new ArrayList<>();
        for (int i = 0; i < listBlesDB.size(); i++) {
            list.add(new BelCell(listBlesDB.get(i)));
        }
        ObservableList<BelCell> myObservableList = FXCollections.observableList(list);
        listeBle.setItems(myObservableList);
        listeBle.setExpanded(true);
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

    @FXML
    private void rechrecheKeyReleased(KeyEvent event) {
        listeBle.getItems().clear();

        List<Ble> listBlesDB = BleQueries.listRecherche(rechreche.getText());
        List<BelCell> list = new ArrayList<>();
        for (int i = 0; i < listBlesDB.size(); i++) {
            list.add(new BelCell(listBlesDB.get(i)));
        }
        ObservableList<BelCell> myObservableList = FXCollections.observableList(list);
        listeBle.setItems(myObservableList);
        listeBle.setExpanded(true);
        setTotale();
    }

    @FXML
    private void setOrder(ActionEvent event) {
    }

    private void setTotale() {
        total.setText(Integer.toString(listeBle.getItems().size()));
    }

    @FXML
    private void showAddStage(ActionEvent event) {
        try {
            Stage stage = Methode.getStage(event);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(ViewUrl.AjouterBle));
            loader.load();
            AjouterBleController control = loader.getController();
            control.setData(listeBle, total);
            AnchorPane root = loader.getRoot();
            Methode.moveFocus(root);
            StageDialog dialog = new StageDialog(stage, root);
            dialog.show();
        } catch (IOException ex) {
            Logger.getLogger(ClientListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showDockSlide(MouseEvent event) {
        try {
            int seletedrow = listeBle.getSelectionModel().getSelectedIndex();
            Stage stage = Methode.getStageMouses(event);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(ViewUrl.slideBle));
            loader.load();
            BleSlideController control = loader.getController();
            control.setData(seletedrow, listeBle);
            AnchorPane root = loader.getRoot();
            StageDialog dialog = new StageDialog(stage, root);
            dialog.show();
        } catch (IOException ex) {
            Logger.getLogger(ClientListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Tout(ActionEvent event) {
        Order.setText("Tout");
        List<Ble> listBlesDB = BleQueries.listAll();
        List<BelCell> list = new ArrayList<>();
        for (int i = 0; i < listBlesDB.size(); i++) {
            list.add(new BelCell(listBlesDB.get(i)));
        }
        ObservableList<BelCell> myObservableList = FXCollections.observableList(list);
        listeBle.setItems(myObservableList);
        listeBle.setExpanded(true);
        setTotale();
    }
}
