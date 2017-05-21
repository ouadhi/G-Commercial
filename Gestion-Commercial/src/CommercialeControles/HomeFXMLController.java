
package CommercialeControles;

import UIControle.ShowPane;
import UIControle.ViewUrl;
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
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class HomeFXMLController implements Initializable {

    @FXML
    private Button transport;
    @FXML
    private AnchorPane root;
    @FXML
    private Button achat;
    @FXML
    private Button vente;
    @FXML
    private Button rapport;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    
    @FXML
    private void showtransport(ActionEvent event) {
        try{
            AnchorPane menu2 = FXMLLoader.load(getClass().getResource("/CommercialeView/LeftMenu2FXML.fxml"));

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/CommercialeView/Home2FXML.fxml"));
            loader.load();

            Home2FXMLController control = loader.getController();
            control.setMenu(menu2);

            AnchorPane root = loader.getRoot();
            
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
            stage.setScene(scene);
            
            new  ShowPane().showChauffeur();

        } catch (IOException ex) {
            Logger.getLogger(HomeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void showAchat(ActionEvent event) {
        
      
         try{
            AnchorPane menu2 = FXMLLoader.load(getClass().getResource(ViewUrl.AchatMenu));

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/CommercialeView/Home2FXML.fxml"));
            loader.load();

            Home2FXMLController control = loader.getController();
            control.setMenu(menu2);

            AnchorPane root = loader.getRoot();
            
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
            stage.setScene(scene);
            
            new  ShowPane().showDock();

        } catch (IOException ex) {
            Logger.getLogger(HomeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

   

    @FXML
    private void showvente(ActionEvent event) {
        try{
            AnchorPane menu2 = FXMLLoader.load(getClass().getResource(ViewUrl.VenteMenu));

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/CommercialeView/Home2FXML.fxml"));
            loader.load();

            Home2FXMLController control = loader.getController();
            control.setMenu(menu2);

            AnchorPane root = loader.getRoot();
            
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
            stage.setScene(scene);
            
            new  ShowPane().showClient();

        } catch (IOException ex) {
            Logger.getLogger(HomeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showRapport(ActionEvent event) {
        
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
