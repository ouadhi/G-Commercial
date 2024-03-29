package CommercialeControles.OperationAchat;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gestionCommerciale.HibernateSchema.Achat;
import com.gestionCommerciale.Models.AchatQueries;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import UIControle.Methode;
import UIControle.ShowPane;
import UIControle.StageDialog;
import UIControle.ViewUrl;
import java.util.Date;
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

public class ListeAchatController implements Initializable {

    @FXML
    private Label total;
    @FXML
    private MenuButton Order;
    @FXML
    private MenuItem bycode;
    @FXML
    private JFXButton ajouter;
    @FXML
    private JFXListView<AchatCell> listeAchats;
    @FXML
    private JFXTextField rechreche;
    @FXML
    private Label label;
    @FXML
    private Label totalquantite;
    @FXML
    private Label todayAchat;
    @FXML
    private Label todayquantite;
    @FXML
    private Label totalAcaht;
    Stage  stage  =  null  ; 

    @FXML
    private void Archive(ActionEvent event) {
        Order.setText("Archiv\u00E9");
        List<Achat> achatList = AchatQueries.listArchived();

        List<AchatCell> list = new ArrayList<>();
        for (int i = 0; i < achatList.size(); i++) {
            list.add(new AchatCell(achatList.get(i)));
        }
        ObservableList<AchatCell> myObservableList = FXCollections.observableList(list);
        listeAchats.setItems(myObservableList);
        listeAchats.setExpanded(true);

        setTotale();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Methode.showMenuItem(Order, label);
        List<Achat> achatList = AchatQueries.list();

        List<AchatCell> list = new ArrayList<>();
        for (int i = 0; i < achatList.size(); i++) {
            list.add(new AchatCell(achatList.get(i)));
        }

        ObservableList<AchatCell> myObservableList = FXCollections.observableList(list);
        listeAchats.setItems(myObservableList);
        listeAchats.setExpanded(true);

        todayquantite.setText(Methode.DoubleFormat(AchatQueries.totaleAchatByDate(new Date())));
//        todayAchat.setText(Methode.DoubleFormat(AchatQueries.nbtotaleAchatByDate(new Date())));
        todayAchat.setText(AchatQueries.nbtotaleAchatByDate(new Date())+"");

//        totalAcaht.setText(Methode.DoubleFormat(AchatQueries.nbtotaleAchat()));
        totalAcaht.setText(AchatQueries.nbtotaleAchat()+"");
        totalquantite.setText(Methode.DoubleFormat(AchatQueries.totaleAchat()));
        setTotale();

    }

    @FXML
    private void NonArchive(ActionEvent event) {
        Order.setText("Non Archiv\u00E9");
        List<Achat> achatList = AchatQueries.list();

        List<AchatCell> list = new ArrayList<>();
        for (int i = 0; i < achatList.size(); i++) {
            list.add(new AchatCell(achatList.get(i)));
        }
        ObservableList<AchatCell> myObservableList = FXCollections.observableList(list);
        listeAchats.setItems(myObservableList);
        listeAchats.setExpanded(true);
        setTotale();
    }

    @FXML
    private void rechrecher(KeyEvent event) {
        List<Achat> achatList = AchatQueries.listRecherche(rechreche.getText());

        List<AchatCell> list = new ArrayList<>();
        for (int i = 0; i < achatList.size(); i++) {
            list.add(new AchatCell(achatList.get(i)));
        }

        ObservableList<AchatCell> myObservableList = FXCollections.observableList(list);
        listeAchats.setItems(myObservableList);
        listeAchats.setExpanded(true);

        setTotale();
    }

    @FXML
    private void setOrder(ActionEvent event) {
        stage = Methode.getStage(event);
    }

    private void setTotale() {
        total.setText(Integer.toString(listeAchats.getItems().size()));
    }

    @FXML
    private void showAchatSlide(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(ViewUrl.AchatSlid));
            loader.load();

            AchatSlidController controler = loader.getController();
            controler.setData(listeAchats, listeAchats.getSelectionModel().getSelectedIndex());

            AnchorPane pane = loader.getRoot();

            StageDialog dialog = new StageDialog(Methode.getStageMouses(event), pane);
            dialog.show();

        } catch (IOException ex) {
            Logger.getLogger(ListeAchatController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void showAddStage(ActionEvent event) {
        FinOperationController.ClearVar();
        new ShowPane().showOperationAchat();
    }

    @FXML
    private void Tout(ActionEvent event) {
        Order.setText("Tout");
        List<Achat> achatList = AchatQueries.listAll();

        List<AchatCell> list = new ArrayList<>();
        for (int i = 0; i < achatList.size(); i++) {
            list.add(new AchatCell(achatList.get(i)));
        }
        ObservableList<AchatCell> myObservableList = FXCollections.observableList(list);
        listeAchats.setItems(myObservableList);
        listeAchats.setExpanded(true);
        setTotale();
    }

    @FXML
    private void RechDate(ActionEvent event) {
        try {
            AnchorPane pane  =  FXMLLoader.load(getClass().getResource(ViewUrl.AchatDate))  ;
            StageDialog stageD  =  new StageDialog(stage, pane) ;  
            stageD.show();
        } catch (IOException ex) {
            Logger.getLogger(ListeAchatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
