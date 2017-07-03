package CommercialeControles.Banque;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.controlsfx.control.textfield.TextFields;

import com.gestionCommerciale.HibernateSchema.Banque;
import com.gestionCommerciale.Models.BanqueQueries;
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
import javafx.scene.control.MenuItem;
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

    private BanqueQueries querie = new BanqueQueries();
    @FXML
    private MenuButton Order;
    @FXML
    private MenuItem byquantite;
    @FXML
    private Label label;

    @FXML
    private void Archive(ActionEvent event) {
        Order.setText("Non Archivé");
        List<Banque> listBanque = querie.listArchived();
        List<BanqueCell> list = new ArrayList<>();

        for (int i = 0; i < listBanque.size(); i++) {
            list.add(new BanqueCell(listBanque.get(i)));
        }
        ObservableList<BanqueCell> myObservableList = FXCollections.observableList(list);
        listebanque.setItems(myObservableList);
        listebanque.setExpanded(true);
        setTotale();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Methode.showMenuItem(Order, label);
        List<BanqueCell> list = new ArrayList<>();

        for (int i = 0; i < querie.list().size(); i++) {
            list.add(new BanqueCell(querie.list().get(i)));
        }
        ObservableList<BanqueCell> myObservableList = FXCollections.observableList(list);
        listebanque.setItems(myObservableList);
        listebanque.setExpanded(true);

        setTotale();

    }

    @FXML
    private void NonArchiv(ActionEvent event) {
        Order.setText("Non Archivé");
        List<Banque> listBanque = querie.list();
        List<BanqueCell> list = new ArrayList<>();

        for (int i = 0; i < listBanque.size(); i++) {
            list.add(new BanqueCell(listBanque.get(i)));
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

    @FXML
    private void recherche(KeyEvent event) {
        listebanque.getItems().clear();
        List<Banque> listbanque = querie.listRechreche(rechreche.getText());
        List<BanqueCell> list = new ArrayList<>();

        for (int i = 0; i < listbanque.size(); i++) {
            list.add(new BanqueCell(listbanque.get(i)));

        }
        ObservableList<BanqueCell> myObservableList = FXCollections.observableList(list);
        listebanque.setItems(myObservableList);
        listebanque.setExpanded(true);

        setTotale();
    }

    @FXML
    private void setOrder(ActionEvent event) {
    }

    private void setTotale() {
        total.setText("" + listebanque.getItems().size());
    }

    @FXML
    private void showAddStage(ActionEvent event) {

        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(ViewUrl.AjouterBanque));
            Methode.moveFocus(pane);
            StageDialog dialog = new StageDialog(Methode.getStage(event), pane);
            dialog.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterBanqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Tout(ActionEvent event) {
        Order.setText("Non Archivé");
        List<Banque> listBanque = querie.listAll();
        List<BanqueCell> list = new ArrayList<>();

        for (int i = 0; i < listBanque.size(); i++) {
            list.add(new BanqueCell(listBanque.get(i)));
        }
        ObservableList<BanqueCell> myObservableList = FXCollections.observableList(list);
        listebanque.setItems(myObservableList);
        listebanque.setExpanded(true);

    }

}
