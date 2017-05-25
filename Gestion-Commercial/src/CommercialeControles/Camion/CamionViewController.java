
package CommercialeControles.Camion;

import CommercialeControles.Chauffeur.ChauffeurController;
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
    private MenuItem DateMenuItem;
    @FXML
    private MenuItem VoyageMenuItem;
    @FXML
    private MenuItem NomMenuItem;
    @FXML
    private JFXListView<CamionCell> listeView;
   
    private MenuButton nbvisibel;

    CamionQueries camionQueries= new CamionQueries();
    @FXML
    private JFXTextField rechreche1;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        List<Camion> listCamionsDB= camionQueries.list();
        List<CamionCell> list = new ArrayList<>();
        for (int i = 0; i < listCamionsDB.size(); i++) {
            list.add(new CamionCell(listCamionsDB.get(i).getId()
                    ,listCamionsDB.get(i).getType()
                    ,listCamionsDB.get(i).getMatricule()
                    ,listCamionsDB.get(i).getCodeCamion()
                 , 1000));
            
        }
         
          ObservableList<CamionCell> myObservableList = FXCollections.observableList(list);
          listeView.setItems(myObservableList);
         
          setTotal();
          
          possibleMot();
          
    }    

    @FXML
    private void AjouterMethode(ActionEvent event) {
        
       Stage stage  = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
       
        AjouterCamionDialog dialog  = new AjouterCamionDialog(stage) ; 
        dialog.show();
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
        orderby.setText("Nom");
    }
    
    public void setTotal () {
       String  total  =  Integer.toString(listeView.getItems().size() ) ; 
       this.total.setText(total);
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
    private void showCamions(MouseEvent event) {
        try {
            Stage courrentStage  =(Stage) ((Node)(event.getSource())).getScene().getWindow() ; 
            int selectitem =  listeView.getSelectionModel().getSelectedIndex() ;
            
            System.out.println(selectitem);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(ViewUrl.detailcamion));
            loader.load();
            
            
            ShowdDetailCamionController showcamion =  loader.getController() ; 
            showcamion.setListechauffeur(this.listeView, selectitem);

            AnchorPane pane  = loader.getRoot();
            
            StageDialog stage  = new StageDialog(courrentStage, pane) ;
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
        System.out.println(rechreche1.getText());
    }
    
}
