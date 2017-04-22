
package CommercialeControles.Menu.Vente;

import CommercialeControles.HomeFXMLController;
import UIControle.ShowPane;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class VenteMenuController implements Initializable {

    @FXML
    private JFXButton home;
    @FXML
    private JFXButton Client;
    @FXML
    private JFXButton Produit;
    @FXML
    private JFXButton Facture;
    @FXML
    private ImageView homeicon;
    @FXML
    private ImageView iconclien;
    @FXML
    private ImageView iconProduit;
    @FXML
    private ImageView facturicon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void showhome(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/CommercialeView/HomeFXML.fxml"));
            Scene scene = new Scene(pane) ; 
            Stage  stage = (Stage) ((Node) (event.getSource())).getScene().getWindow() ; 
            
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(HomeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @FXML
    private void showClient(ActionEvent event) {
        new ShowPane().showClient();
    }

    @FXML
    private void showProduit(ActionEvent event) {
         new ShowPane().showProduit();
    }

    @FXML
    private void showfacture(ActionEvent event) {
         
    }

    @FXML
    private void inhome(DragEvent event) {
    }

    @FXML
    private void outhome(DragEvent event) {
    }

    @FXML
    private void outclient(MouseEvent event) {
    }

    @FXML
    private void incllient(MouseEvent event) {
    }

    @FXML
    private void OutProduit(MouseEvent event) {
    }

    @FXML
    private void Inproduit(MouseEvent event) {
    }

    @FXML
    private void InFacture(MouseEvent event) {
    }

    @FXML
    private void OutFacture(MouseEvent event) {
    }
    
}
