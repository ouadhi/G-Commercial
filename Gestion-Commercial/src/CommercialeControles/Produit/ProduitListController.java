package CommercialeControles.Produit;

import UIControle.Methode;
import UIControle.StageDialog;
import UIControle.ViewUrl;
import com.gestionCommerciale.HibernateSchema.Produit;
import com.gestionCommerciale.Models.ProduitQueries;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.textfield.TextFields;

public class ProduitListController implements Initializable {

    @FXML
    private Label total;
    @FXML
    private MenuButton Order;
    private MenuButton NbShow;
    @FXML
    private JFXButton ajouter;
    @FXML
    private JFXTextField rechreche;
   
    private ProduitQueries queries = new ProduitQueries();
    @FXML
    private JFXListView<ProduitCell> listeproduit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<Produit> listBlesDB = queries.list();
        List<ProduitCell> list = new ArrayList<>();
        
        for (int i = 0; i < listBlesDB.size(); i++) {
            list.add(new ProduitCell(listBlesDB.get(i)));
        }
        
        ObservableList<ProduitCell> myObservableList = FXCollections.observableList(list);
        listeproduit.setItems(myObservableList);
        listeproduit.setExpanded(true);
        
        setTotale();
        
      
    }

   

    @FXML
    private void setOrder(ActionEvent event) {
    }

    

    @FXML
    private void showAddStage(ActionEvent event) {

        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(ViewUrl.AjouterProduit));
            StageDialog stage = new StageDialog(Methode.getStage(event), pane);

            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(ProduitListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    private void showDockSlide(MouseEvent event) {
        try {
            int selectedcell = listeproduit.getSelectionModel().getSelectedIndex();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(ViewUrl.SlideProduit));
            loader.load();

            SlidProduitController slidProduit = loader.getController();
            slidProduit.setData(listeproduit, selectedcell);

            AnchorPane pane = loader.getRoot();

            StageDialog stage = new StageDialog(Methode.getStageMouses(event), pane);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ProduitListController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setTotale() {

        total.setText(Integer.toString(listeproduit.getItems().size()));

    }

    @FXML
    private void rechrecher(KeyEvent event) {
        
         listeproduit.getItems().clear();
        List<Produit> listBlesDB = queries.listRechreche(rechreche.getText()) ; 
        List<ProduitCell> list = new ArrayList<>();
        
        for (int i = 0; i < listBlesDB.size(); i++) {
            list.add(new ProduitCell(listBlesDB.get(i)));
        }
        
        ObservableList<ProduitCell> myObservableList = FXCollections.observableList(list);
        listeproduit.setItems(myObservableList);
        
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
    private void NonArchive(ActionEvent event) {
        Order.setText("Non Archivé");
         List<Produit> listBlesDB = queries.list();
        List<ProduitCell> list = new ArrayList<>();
        
        for (int i = 0; i < listBlesDB.size(); i++) {
            list.add(new ProduitCell(listBlesDB.get(i)));
        }
        
        ObservableList<ProduitCell> myObservableList = FXCollections.observableList(list);
        listeproduit.setItems(myObservableList);
        listeproduit.setExpanded(true);
        
        setTotale();
    }

    @FXML
    private void Archive(ActionEvent event) {
        Order.setText("Archivé");
         List<Produit> listBlesDB = queries.listArchived() ; 
        List<ProduitCell> list = new ArrayList<>();
        
        for (int i = 0; i < listBlesDB.size(); i++) {
            list.add(new ProduitCell(listBlesDB.get(i)));
        }
        
        ObservableList<ProduitCell> myObservableList = FXCollections.observableList(list);
        listeproduit.setItems(myObservableList);
        listeproduit.setExpanded(true);
        
        setTotale();
    }

    @FXML
    private void tout(ActionEvent event) {
        Order.setText("Tout");
         List<Produit> listBlesDB = queries.listAll()  ; 
        List<ProduitCell> list = new ArrayList<>();
        
        for (int i = 0; i < listBlesDB.size(); i++) {
            list.add(new ProduitCell(listBlesDB.get(i)));
        }
        
        ObservableList<ProduitCell> myObservableList = FXCollections.observableList(list);
        listeproduit.setItems(myObservableList);
        listeproduit.setExpanded(true);
        
        setTotale();
    }
    
    
}
