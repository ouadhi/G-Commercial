package CommercialeControles.Chauffeur;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.controlsfx.control.textfield.TextFields;

import com.gestionCommerciale.HibernateSchema.Chauffeur;
import com.gestionCommerciale.Models.ChauffeurQueries;
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
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ChauffeurController implements Initializable {

    @FXML
    private JFXListView<ChauffeurCell> listeView;
    public static  JFXListView<ChauffeurCell> listeChauffeur;

    @FXML
    private JFXButton Ajoute;
    @FXML
    private Label total;
     public static Label totalstatic;
    @FXML
    private MenuButton orderby;
    @FXML
    private MenuItem DateMenuItem;
    @FXML
    private MenuItem VoyageMenuItem;
    @FXML
    private MenuItem NomMenuItem;
    @FXML
    private JFXTextField recherchetxt;
    @FXML
    private Label label;
    @FXML
    private MenuItem FiltreInterne;
    @FXML
    private MenuItem FiltreExterne;

    @FXML
    private void AjouterMethode(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/CommercialeView/Chauffeur/AjouterChauffeurView.fxml"));
            loader.load();
            
            AjouterChauffeurViewController add = loader.getController();
            add.setData(listeView, total);
            AnchorPane pane = loader.getRoot();
             
            StageDialog stage  =  new StageDialog(Methode.getStage(event), pane) ;
            stage.show() ; 
        } catch (IOException ex) {
            Logger.getLogger(ChauffeurController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void archive(ActionEvent event) {
        orderby.setText("Archiv\u00E9");
        listeView.getItems().clear();

        List<Chauffeur> listChauffeursDB = ChauffeurQueries.listArchived();
        System.out.println("°°°°°°°°°°" + listChauffeursDB.size());
        List<ChauffeurCell> list = new ArrayList<>();
        for (int i = 0; i < listChauffeursDB.size(); i++) {
            list.add(new ChauffeurCell(listChauffeursDB.get(i)));
        }

        ObservableList<ChauffeurCell> myObservableList = FXCollections.observableList(list);
        listeView.setItems(myObservableList);
        listeView.setExpanded(true);

        setTotal();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listeChauffeur  =  listeView  ; 
        totalstatic  = total  ; 
        Methode.showMenuItem(orderby, label);
        List<Chauffeur> listChauffeursDB = ChauffeurQueries.list();
        List<ChauffeurCell> list = new ArrayList<>();
        for (int i = 0; i < listChauffeursDB.size(); i++) {
            list.add(new ChauffeurCell(listChauffeursDB.get(i)));

        }

        ObservableList<ChauffeurCell> myObservableList = FXCollections.observableList(list);
        listeView.setItems(myObservableList);
        listeView.setExpanded(true);

        setTotal();

    }

    @FXML
    private void nonArchive(ActionEvent event) {
        orderby.setText("Non Archiv\u00E9");
        listeView.getItems().clear();
        List<Chauffeur> listChauffeursDB = ChauffeurQueries.list();
        List<ChauffeurCell> list = new ArrayList<>();
        for (int i = 0; i < listChauffeursDB.size(); i++) {
            list.add(new ChauffeurCell(listChauffeursDB.get(i)));

        }

        ObservableList<ChauffeurCell> myObservableList = FXCollections.observableList(list);
        listeView.setItems(myObservableList);
        listeView.setExpanded(true);

        setTotal();
    }

    public void possibleMot() {

        ArrayList<String> list = new ArrayList<>();
        list.add("karim");
        list.add("hichem1");
        list.add("hichem2");
        list.add("mohammed ouadhi");
        list.add("mohammed cherberabe");

        TextFields.bindAutoCompletion(recherchetxt, list);

    }

    @FXML
    private void recherche(KeyEvent event) {
        listeView.getItems().clear();
        List<Chauffeur> listChauffeursDB = ChauffeurQueries.listRecherche(recherchetxt.getText());
        List<ChauffeurCell> list = new ArrayList<>();
        for (int i = 0; i < listChauffeursDB.size(); i++) {
            list.add(new ChauffeurCell(listChauffeursDB.get(i)));

        }

        ObservableList<ChauffeurCell> myObservableList = FXCollections.observableList(list);
        listeView.setItems(myObservableList);

        setTotal();
    }

    private void setTotal() {

        String nb = Integer.toString(listeView.getItems().size());
        total.setText(nb);
    }

    @FXML
    private void showChaffeur(MouseEvent event) {

        try {
            Stage courrentStage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
            int selectitem = listeView.getSelectionModel().getSelectedIndex();

            System.out.println(selectitem);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(ViewUrl.showChaffeur));
            loader.load();

            ShowChauffeurController showchaf = loader.getController();
            showchaf.setListechauffeur(this.listeView, selectitem);

            AnchorPane pane = loader.getRoot();

            StageDialog stage = new StageDialog(courrentStage, pane);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(ChauffeurController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void tout(ActionEvent event) {
        orderby.setText("Tout");
        listeView.getItems().clear();
        List<Chauffeur> listChauffeursDB = ChauffeurQueries.listAll();
        List<ChauffeurCell> list = new ArrayList<>();
        for (int i = 0; i < listChauffeursDB.size(); i++) {
            list.add(new ChauffeurCell(listChauffeursDB.get(i)));

        }

        ObservableList<ChauffeurCell> myObservableList = FXCollections.observableList(list);
        listeView.setItems(myObservableList);
        listeView.setExpanded(true);

        setTotal();
    }

    @FXML
    private void interne(ActionEvent event) {

        orderby.setText("Interne");
        listeView.getItems().clear();
        List<Chauffeur> listChauffeursDB = ChauffeurQueries.listInterne();
        List<ChauffeurCell> list = new ArrayList<>();
        for (int i = 0; i < listChauffeursDB.size(); i++) {
            list.add(new ChauffeurCell(listChauffeursDB.get(i)));

        }

        ObservableList<ChauffeurCell> myObservableList = FXCollections.observableList(list);
        listeView.setItems(myObservableList);
        listeView.setExpanded(true);

        setTotal();
    }

    @FXML
    private void Externe(ActionEvent event) {
        orderby.setText("Externe");
        listeView.getItems().clear();
        List<Chauffeur> listChauffeursDB = ChauffeurQueries.listExterne();
        List<ChauffeurCell> list = new ArrayList<>();
        for (int i = 0; i < listChauffeursDB.size(); i++) {
            list.add(new ChauffeurCell(listChauffeursDB.get(i)));

        }

        ObservableList<ChauffeurCell> myObservableList = FXCollections.observableList(list);
        listeView.setItems(myObservableList);
        listeView.setExpanded(true);

        setTotal();
    }

}
