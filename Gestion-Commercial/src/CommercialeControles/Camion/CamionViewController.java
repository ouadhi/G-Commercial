package CommercialeControles.Camion;

import CommercialeControles.Chauffeur.ChauffeurController;
import UIControle.Methode;
import UIControle.StageDialog;
import UIControle.ViewUrl;
import com.gestionCommerciale.HibernateSchema.Camion;
import com.gestionCommerciale.Models.CamionQueries;
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
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

public class CamionViewController implements Initializable {

    @FXML
    private Label total;
    @FXML
    private JFXButton Ajoute;
    @FXML
    private MenuButton orderby;
    @FXML
    private JFXListView<CamionCell> listeView;

    private MenuButton nbvisibel;
    CamionQueries camionQueries = new CamionQueries();
    @FXML
    private JFXTextField rechreche1;
    @FXML
    private Label label;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Methode.showMenuItem(orderby , label);
        List<Camion> listCamionsDB = camionQueries.list();
        List<CamionCell> list = new ArrayList<>();
        for (int i = 0; i < listCamionsDB.size(); i++) {
            list.add(new CamionCell(listCamionsDB.get(i)));

        }

        ObservableList<CamionCell> myObservableList = FXCollections.observableList(list);
        listeView.setItems(myObservableList);

        setTotal();

    }

    @FXML
    private void AjouterMethode(ActionEvent event) {

        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/CommercialeView/Camion/AjouterCamionView.fxml"));

            StageDialog dialog = new StageDialog(stage, pane);
            dialog.show();

        } catch (IOException ex) {
            Logger.getLogger(CamionViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setTotal() {
        String total = Integer.toString(listeView.getItems().size());
        this.total.setText(total);
    }

    @FXML
    private void showCamions(MouseEvent event) {
        try {
            Stage courrentStage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
            int selectitem = listeView.getSelectionModel().getSelectedIndex();

//            System.out.println("_________________________"+selectitem);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(ViewUrl.detailcamion));
            loader.load();

            ShowdDetailCamionController showcamion = loader.getController();
            showcamion.setListechauffeur(this.listeView, selectitem);
            AnchorPane pane = loader.getRoot();

            StageDialog stage = new StageDialog(courrentStage, pane);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(ChauffeurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void possibleMot() {

        ArrayList<String> list = new ArrayList<>();
        list.add("karim");
        list.add("hichem1");
        list.add("hichem2");
        list.add("mohammed ouadhi");
        list.add("mohammed cherberabe");
        TextFields.bindAutoCompletion(rechreche1, list);
    }

    @FXML
    private void rechrecher(KeyEvent event) {
        listeView.getItems().clear();

        List<Camion> listCamionsDB = camionQueries.listRechreche(rechreche1.getText());
        List<CamionCell> list = new ArrayList<>();
        for (int i = 0; i < listCamionsDB.size(); i++) {
            list.add(new CamionCell(listCamionsDB.get(i)));

        }

        ObservableList<CamionCell> myObservableList = FXCollections.observableList(list);
        listeView.setItems(myObservableList);

        setTotal();
    }

    @FXML
    private void NonArchive(ActionEvent event) {
        orderby.setText("Non Archivé");

        List<Camion> listCamionsDB = camionQueries.list();
        List<CamionCell> list = new ArrayList<>();
        for (int i = 0; i < listCamionsDB.size(); i++) {
            list.add(new CamionCell(listCamionsDB.get(i)));

        }

        ObservableList<CamionCell> myObservableList = FXCollections.observableList(list);
        listeView.setItems(myObservableList);

        setTotal();

    }

    @FXML
    private void Archive(ActionEvent event) {
        orderby.setText("Archivé");

        List<Camion> listCamionsDB = camionQueries.listArchived();
        List<CamionCell> list = new ArrayList<>();
        for (int i = 0; i < listCamionsDB.size(); i++) {
            list.add(new CamionCell(listCamionsDB.get(i)));

        }

        ObservableList<CamionCell> myObservableList = FXCollections.observableList(list);
        listeView.setItems(myObservableList);

        setTotal();

    }

    @FXML
    private void tout(ActionEvent event) {
        orderby.setText("Tout");

        List<Camion> listCamionsDB = camionQueries.listAll() ; 
        List<CamionCell> list = new ArrayList<>();
        for (int i = 0; i < listCamionsDB.size(); i++) {
            list.add(new CamionCell(listCamionsDB.get(i)));

        }

        ObservableList<CamionCell> myObservableList = FXCollections.observableList(list);
        listeView.setItems(myObservableList);

        setTotal();
    }

}
