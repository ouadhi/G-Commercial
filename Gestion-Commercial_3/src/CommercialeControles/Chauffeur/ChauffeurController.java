
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
    @FXML
    private MenuButton nbvisibel;
    @FXML
    private MenuItem v20;
    @FXML
    private MenuItem V50;
    @FXML
    private MenuItem V100;
    @FXML
    private MenuItem All;
    @FXML
    private JFXTextField recherchetxt;
    
    private ChauffeurQueries chauffeurQueries= new ChauffeurQueries();
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<Chauffeur> listChauffeursDB= chauffeurQueries.chauffeursList();
        List<ChauffeurCell> list = new ArrayList<>();
        for (int i = 0; i < listChauffeursDB.size(); i++) {
            list.add(new ChauffeurCell(listChauffeursDB.get(i).getPrenomChauffeur()+" "+listChauffeursDB.get(i).getNomChauffeur()
                    , listChauffeursDB.get(i).getTelephone()
                    , ""
                    , "13"));
            
        }

        ObservableList<ChauffeurCell> myObservableList = FXCollections.observableList(list);
        listeView.setItems(myObservableList);
        
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

    @FXML
    private void show20(ActionEvent event) {
        
        nbvisibel.setText("20");
    }

    @FXML
    private void show50(ActionEvent event) {
         nbvisibel.setText("50");
    }

    @FXML
    private void show100(ActionEvent event) {
         nbvisibel.setText("100");
    }

    @FXML
    private void showAll(ActionEvent event) {
         nbvisibel.setText("All");
    }

    @FXML
    private void rechercher(KeyEvent event) {
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

     
    
      
}
