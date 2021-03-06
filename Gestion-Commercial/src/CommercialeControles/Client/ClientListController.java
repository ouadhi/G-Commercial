package CommercialeControles.Client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.controlsfx.control.textfield.TextFields;

import com.gestionCommerciale.HibernateSchema.Client;
import com.gestionCommerciale.Models.ClientQueries;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ClientListController implements Initializable {

    @FXML
    private Label total;
    public static Label totalstatic  ; 
    @FXML
    private MenuButton Order;
    private MenuButton NbShow;

    @FXML
    private JFXButton ajouter;
    @FXML
    private JFXListView<ClienCell> clientLsit;
    public  static JFXListView<ClienCell> ListeClient;
    @FXML
    private JFXTextField rechreche;
    @FXML
    private Label lable;

    @FXML
    private void Archive(ActionEvent event) {
        Order.setText("Archiv\u00E9");
        List<Client> listClientsDB = ClientQueries.listArchived();

        List<ClienCell> list = new ArrayList<>();
        for (int i = 0; i < listClientsDB.size(); i++) {
            list.add(new ClienCell(listClientsDB.get(i)));
        }

        ObservableList<ClienCell> myObservableList = FXCollections.observableList(list);
        clientLsit.setItems(myObservableList);
        clientLsit.setExpanded(true);

        setTotal();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        ListeClient =  clientLsit  ; 
        totalstatic  =  total  ; 
        Methode.showMenuItem(Order, lable);

        List<Client> listClientsDB = ClientQueries.list();

        List<ClienCell> list = new ArrayList<>();
        for (int i = 0; i < listClientsDB.size(); i++) {
            list.add(new ClienCell(listClientsDB.get(i)));
        }

        ObservableList<ClienCell> myObservableList = FXCollections.observableList(list);
        clientLsit.setItems(myObservableList);
        clientLsit.setExpanded(true);

        setTotal();

    }

    @FXML
    private void NonArchive(ActionEvent event) {
        Order.setText("Non Archiv\u00E9");
        List<Client> listClientsDB = ClientQueries.list();

        List<ClienCell> list = new ArrayList<>();
        for (int i = 0; i < listClientsDB.size(); i++) {
            list.add(new ClienCell(listClientsDB.get(i)));
        }

        ObservableList<ClienCell> myObservableList = FXCollections.observableList(list);
        clientLsit.setItems(myObservableList);
        clientLsit.setExpanded(true);

        setTotal();
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
    private void rechreche(KeyEvent event) {
        clientLsit.getItems().clear();
        List<Client> listClientsDB = ClientQueries.listRechereche(rechreche.getText());

        List<ClienCell> list = new ArrayList<>();
        for (int i = 0; i < listClientsDB.size(); i++) {
            list.add(new ClienCell(listClientsDB.get(i)));
        }

        ObservableList<ClienCell> myObservableList = FXCollections.observableList(list);
        clientLsit.setItems(myObservableList);
        clientLsit.setExpanded(true);

        setTotal();
    }

    @FXML
    private void setOrder(ActionEvent event) {
    }

    private void setTotal() {
        total.setText(Integer.toString(clientLsit.getItems().size()));
    }

    @FXML
    private void showAddStage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            AnchorPane root = FXMLLoader.load(getClass().getResource(ViewUrl.AjouteClient));
            StageDialog dialog = new StageDialog(Methode.getStage(event), root);
             Methode.moveFocus(root);
            dialog.show();
        } catch (IOException ex) {
            Logger.getLogger(ClientListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showClient(MouseEvent event) {
        try {
            int seletedrow = clientLsit.getSelectionModel().getSelectedIndex();

            Stage stage = Methode.getStageMouses(event);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(ViewUrl.SlideClient));
            loader.load();

            ShowClienSlideController controlClient = loader.getController();
            controlClient.setData(seletedrow, clientLsit);

            AnchorPane root = loader.getRoot();
            Methode.moveFocus(root);
            StageDialog dialog = new StageDialog(stage, root);
            dialog.show();

        } catch (IOException ex) {
            Logger.getLogger(ClientListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void tout(ActionEvent event) {
        Order.setText("Tout");
        List<Client> listClientsDB = ClientQueries.listAll();

        List<ClienCell> list = new ArrayList<>();
        for (int i = 0; i < listClientsDB.size(); i++) {
            list.add(new ClienCell(listClientsDB.get(i)));
        }

        ObservableList<ClienCell> myObservableList = FXCollections.observableList(list);
        clientLsit.setItems(myObservableList);
        clientLsit.setExpanded(true);

        setTotal();
    }

}
