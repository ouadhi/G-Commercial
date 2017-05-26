package CommercialeControles.Produit;

import CommercialeControles.Ble.BelCell;
import CommercialeControles.Dock.DockCell;
import CommercialeControles.Dock.ModifierDockController;
import UIControle.Methode;
import UIControle.StageDialog;
import UIControle.ViewUrl;
import com.gestionCommerciale.HibernateSchema.Ble;
import com.gestionCommerciale.HibernateSchema.Produit;
import com.gestionCommerciale.Models.BleQueries;
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
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

public class ProduitListController implements Initializable {

    @FXML
    private Label total;
    @FXML
    private MenuButton Order;
    @FXML
    private MenuItem bycode;
    @FXML
    private MenuItem byname;
    @FXML
    private MenuItem bycategorie;
    @FXML
    private MenuItem byquantite;
    @FXML
    private MenuItem Byprix;
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
        
        possibleMot();

    }

    @FXML
    private void oderByCode(ActionEvent event) {
        Order.setText("Code");
    }

    @FXML
    private void orederByName(ActionEvent event) {
        Order.setText("Nom");
    }

    @FXML
    private void OrderBycaregorie(ActionEvent event) {
        Order.setText("Categrorie");
    }

    @FXML
    private void oderByQantite(ActionEvent event) {
        Order.setText("Quantite");
    }

    @FXML
    private void OderByprix(ActionEvent event) {
        Order.setText("Prix");
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
        System.out.println(rechreche.getText());
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
}
