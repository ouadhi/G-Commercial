
package CommercialeControles.Chauffeur;

import UIControle.StageDialog;
import UIControle.ViewUrl;
import com.gestionCommerciale.HibernateSchema.Chauffeur;
import com.gestionCommerciale.Models.ChauffeurQueries;
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

public class ChauffeurController implements Initializable {

    @FXML
    private JFXListView<ChauffeurCell> listeView;
   
    @FXML
    private JFXButton Ajoute;
    @FXML
    private Label total;
    @FXML
    private MenuButton orderby;
    @FXML
    private MenuItem DateMenuItem;
    @FXML
    private MenuItem VoyageMenuItem;
    @FXML
    private MenuItem NomMenuItem;
    private MenuButton nbvisibel;
    @FXML
    private JFXTextField recherchetxt;
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<Chauffeur> listChauffeursDB= ChauffeurQueries.list();
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
    private void AjouterMethode(ActionEvent event) {
        Stage g = (Stage) ((Node) event.getSource()).getScene().getWindow();
        AjouterChauffeuerDialog dialog = new AjouterChauffeuerDialog(g);
        dialog.show();
        setTotal();

    }

    private void setTotal() {

        String nb = Integer.toString(listeView.getItems().size());
        total.setText(nb);
    }

    @FXML
    private void orderDate(ActionEvent event) {
        orderby.setText("Date");
    }

    @FXML
    private void oredrbyVoyage(ActionEvent event) {
        orderby.setText("Voyage");
    }

    @FXML
    private void orderByNom(ActionEvent event) {
        orderby.setText("Nom et prenom");
    }

    private void show20(ActionEvent event) {
        
        nbvisibel.setText("20");
    }

    private void show50(ActionEvent event) {
         nbvisibel.setText("50");
    }

    private void show100(ActionEvent event) {
         nbvisibel.setText("100");
    }

    private void showAll(ActionEvent event) {
         nbvisibel.setText("All");
    }


    @FXML
    private void showChaffeur(MouseEvent event) {
        
         
            
        try {
            Stage courrentStage  =(Stage) ((Node)(event.getSource())).getScene().getWindow() ; 
            int selectitem =  listeView.getSelectionModel().getSelectedIndex() ;
            
            System.out.println(selectitem);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(ViewUrl.showChaffeur));
            loader.load();
            
            
            ShowChauffeurController showchaf =  loader.getController() ; 
            showchaf.setListechauffeur(this.listeView, selectitem);

            AnchorPane pane  = loader.getRoot();
            
            StageDialog stage  = new StageDialog(courrentStage, pane) ;
            stage.show(); 
            
        } catch (IOException ex) {
            Logger.getLogger(ChauffeurController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }

    @FXML
    private void recherche(KeyEvent event) {
        listeView.getItems().clear();
        List<Chauffeur> listChauffeursDB= ChauffeurQueries.listRecherche(recherchetxt.getText()) ; 
        List<ChauffeurCell> list = new ArrayList<>();
        for (int i = 0; i < listChauffeursDB.size(); i++) {
            list.add(new ChauffeurCell(listChauffeursDB.get(i)));
            
        }

        ObservableList<ChauffeurCell> myObservableList = FXCollections.observableList(list);
        listeView.setItems(myObservableList);
        
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

     
    
      
}
