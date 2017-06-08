package CommercialeControles.Vente;

import UIControle.Methode;
import UIControle.ShowPane;
import UIControle.StageDialog;
import UIControle.ViewUrl;
import com.gestionCommerciale.HibernateSchema.Facture;
import com.gestionCommerciale.Models.FactureQueries;
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

public class VenteListController implements Initializable {

    @FXML
    private Label total;
    @FXML
    private MenuButton Order;
    @FXML
    private JFXTextField rechreche;
    @FXML
    private JFXButton ajouter;
    @FXML
    private JFXListView<VenteCell> listevente;
    @FXML
    private Label label;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Methode.showMenuItem(Order, label);
        List<Facture> factureList = FactureQueries.list();
        List<VenteCell> list = new ArrayList<>();
        for (int i = 0; i < factureList.size(); i++) {
            list.add(new VenteCell(factureList.get(i)));
        }

        ObservableList<VenteCell> myObservableList = FXCollections.observableList(list);
        listevente.setItems(myObservableList);
        listevente.setExpanded(true);
        setTotale();

    }

    @FXML
    private void setOrder(ActionEvent event) {
    }

    @FXML
    private void showAddStage(ActionEvent event) {
        OperationVenteController.ClearVar();
        new ShowPane().showAjouterVente();
    }

    @FXML
    private void showDockSlide(MouseEvent event) {
       
          try {
                Facture   facture  =  listevente.getSelectionModel().getSelectedItem().getFacture()  ; 
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(ViewUrl.showvente));
                loader.load();
                
                ModifierVenteController Modifier = loader.getController();
                Modifier.setData(facture);
                
                AnchorPane root = loader.getRoot();
                
                StageDialog dialog = new StageDialog(Methode.getStageMouses(event), root);
                dialog.show();
                
                
            } catch (IOException ex) {
                Logger.getLogger(VenteCell.class.getName()).log(Level.SEVERE, null, ex);
            }
      
    }

    public void setTotale() {
        total.setText(listevente.getItems().size() + "");
    }

    @FXML
    private void rechrecher(KeyEvent event) {
        listevente.getItems().clear();

        List<Facture> factureList = FactureQueries.listrechreche(rechreche.getText());
        List<VenteCell> list = new ArrayList<>();

        for (int i = 0; i < factureList.size(); i++) {
            list.add(new VenteCell(factureList.get(i)));
        }

        ObservableList<VenteCell> myObservableList = FXCollections.observableList(list);
        listevente.setItems(myObservableList);

        setTotale();

    }

    @FXML
    private void NonArchive(ActionEvent event) {
        Order.setText("Non Archivé");
        List<Facture> factureList = FactureQueries.list();
        List<VenteCell> list = new ArrayList<>();
        for (int i = 0; i < factureList.size(); i++) {
            list.add(new VenteCell(factureList.get(i)));
        }

        ObservableList<VenteCell> myObservableList = FXCollections.observableList(list);
        listevente.setItems(myObservableList);
        listevente.setExpanded(true);
        setTotale();
    }

    @FXML
    private void Archive(ActionEvent event) {
        Order.setText("Archivé");
        List<Facture> factureList = FactureQueries.listArchived();
        List<VenteCell> list = new ArrayList<>();
        for (int i = 0; i < factureList.size(); i++) {
            list.add(new VenteCell(factureList.get(i)));
        }

        ObservableList<VenteCell> myObservableList = FXCollections.observableList(list);
        listevente.setItems(myObservableList);
        listevente.setExpanded(true);
        setTotale();
    }

    @FXML
    private void tout(ActionEvent event) {
        Order.setText("Tout");
        List<Facture> factureList = FactureQueries.listAll();
        List<VenteCell> list = new ArrayList<>();
        for (int i = 0; i < factureList.size(); i++) {
            list.add(new VenteCell(factureList.get(i)));
        }

        ObservableList<VenteCell> myObservableList = FXCollections.observableList(list);
        listevente.setItems(myObservableList);
        listevente.setExpanded(true);
        setTotale();
    }

}
