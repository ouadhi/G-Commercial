package CommercialeControles.Vente;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gestionCommerciale.HibernateSchema.Produit;
import com.gestionCommerciale.Models.ProduitQueries;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import UIControle.ViewUrl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class SelectionnerProduitController implements Initializable {

    static Label staticNbselected;
    @FXML
    private JFXListView<PorduitH> listeProduit;

    private ProduitQueries queries = new ProduitQueries();
    @FXML
    private Label nbselected;
    @FXML
    private JFXTextField rechreche;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        staticNbselected = nbselected;
        
        List<Produit> listBlesDB = ProduitQueries.list();
        List<PorduitH> list = new ArrayList<>();

        for (int i = 0; i < listBlesDB.size(); i++) {
            list.add(new PorduitH(listBlesDB.get(i)));
        }
        
        list.add(new PorduitH(listeProduit));
        
        ObservableList<PorduitH> myObservableList = FXCollections.observableList(list);
        listeProduit.setItems(myObservableList);
        listeProduit.setOrientation(Orientation.HORIZONTAL);

    }

    private void nextEtape(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(ViewUrl.infotmationVente));
            OperationVenteController.staticpane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(SelectionnerProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void rechrecher(KeyEvent event) {
        listeProduit.getItems().clear();
        List<Produit> listBlesDB = ProduitQueries.listRechreche(rechreche.getText());
        List<PorduitH> list = new ArrayList<>();

        for (int i = 0; i < listBlesDB.size(); i++) {
            list.add(new PorduitH(listBlesDB.get(i)));
        }
         list.add(new PorduitH(listeProduit)) ;
        ObservableList<PorduitH> myObservableList = FXCollections.observableList(list);
        listeProduit.setItems(myObservableList);
        listeProduit.setOrientation(Orientation.HORIZONTAL);
    }

    @FXML
    private void select(MouseEvent event) {
    }

}
