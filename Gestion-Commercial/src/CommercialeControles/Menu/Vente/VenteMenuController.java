
package CommercialeControles.Menu.Vente;

import CommercialeControles.Home2FXMLController;
import CommercialeControles.HomeFXMLController;
import UIControle.ShowPane;
import UIControle.ViewUrl;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.PopOver;


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
    @FXML
    private ImageView facturicon1;
    @FXML
    private JFXButton Rapport;
    @FXML
    private JFXButton Rapport1;
    @FXML
    private ImageView autreicon;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void showhome(ActionEvent event) {
       new ShowPane().showHome(event);
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
         new ShowPane().showVenteListe();
    }

    @FXML
    private void OutProduit(MouseEvent event) {
        Image img = new Image(getClass().getResourceAsStream("/icons/ProduitGry.png"));
        iconProduit.setImage(img);
    }

    @FXML
    private void Inproduit(MouseEvent event) {
        Image img = new Image(getClass().getResourceAsStream("/icons/ProduitOrange.png"));
        iconProduit.setImage(img);
    }

    @FXML
    private void InFacture(MouseEvent event) {
    }

    @FXML
    private void OutFacture(MouseEvent event) {
    }

    @FXML
    private void outHome(MouseEvent event) {
        Image img = new Image(getClass().getResourceAsStream("/icons/HomeGry.png"));
        homeicon.setImage(img);
        
    }

    @FXML
    private void inHome(MouseEvent event) {
        Image img = new Image(getClass().getResourceAsStream("/icons/HomeOrange.png"));
        homeicon.setImage(img);
    }

    @FXML
    private void OutClient(MouseEvent event) {
        Image img = new Image(getClass().getResourceAsStream("/icons/ClientGry.png"));
        iconclien.setImage(img);
    }

    @FXML
    private void InClient(MouseEvent event) {
        Image img = new Image(getClass().getResourceAsStream("/icons/ClientOrange.png"));
        iconclien.setImage(img);
    }

    @FXML
    private void OutRapport(MouseEvent event) {
        Image img = new Image(getClass().getResourceAsStream("/icons/RapportGry.png"));
        facturicon1.setImage(img);
    }

    @FXML
    private void InRapport(MouseEvent event) {
        Image img = new Image(getClass().getResourceAsStream("/icons/RapportOrange.png"));
        facturicon1.setImage(img);
    }

    @FXML
    private void showRapport(ActionEvent event) {
        showUIRapport(event); 
    }

    

    @FXML
    private void showautre(ActionEvent event) {
    }
    
    private void showUIRapport(ActionEvent event) {
        try{
            AnchorPane menu2 = FXMLLoader.load(getClass().getResource(ViewUrl.rapportMenu));

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/CommercialeView/Home2FXML.fxml"));
            loader.load();

            Home2FXMLController control = loader.getController();
            control.setMenu(menu2);

            AnchorPane root = loader.getRoot();
            
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
            stage.setScene(scene);
            
            new  ShowPane().showRapport();

        } catch (IOException ex) {
            Logger.getLogger(HomeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
