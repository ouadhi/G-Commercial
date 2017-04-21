
package CommercialeControles.Client;

import CommercialeControles.Camion.ShowdDetailCamionController;
import UIControle.Transition;
import UIControle.ViewUrl;
import com.gestionCommerciale.HibernateSchema.Chauffeur;
import com.gestionCommerciale.HibernateSchema.Client;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class ShowClienSlideController implements Initializable {

    @FXML
    private JFXButton last;
    @FXML
    private JFXButton nextbutton;
    @FXML
    private AnchorPane panelMain;
        private static  Client client;

    
    private JFXListView<ClienCell> liste;
    private int i = 0;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
          
    }

    @FXML
    private void lastCleint(ActionEvent event) {
        if (i > 0) {
            --i;
         
            Transition transition = new Transition(panelMain, getClient(i),2000, 0, -2000) ; 
            transition.show();

            if (i == 0) {
                last.setDisable(true);
            } else {
                last.setDisable(false);
                nextbutton.setDisable(false);
            }
        }
    }

    @FXML
    private void nextCleint(ActionEvent event) {
        if (i < liste.getItems().size()) {
          i++;
            Transition transition = new Transition(panelMain, getClient(i), -2000,0, 2000) ; 
            transition.show();
            
            if (i == liste.getItems().size() - 1) {
                nextbutton.setDisable(true);
            } else {
                nextbutton.setDisable(false);
                last.setDisable(false);
            }
        }
        
    }
    
    
     public AnchorPane getClient(int index) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(ViewUrl.ModifierClient));
            loader.load();

            ClienCell client  = liste.getItems().get(index);

            ModifierClientController modification = loader.getController();
            modification.SetData(client.getCodeclient());

            AnchorPane pane = loader.getRoot();

            return pane  ;  
        } catch (IOException ex) {
            Logger.getLogger(ShowdDetailCamionController.class.getName()).log(Level.SEVERE, null, ex);
            return null ; 
        }

    }
    
    
    
    public void  setData (int rowselected , JFXListView<ClienCell> liste ){
        i  =  rowselected ; 
        this.liste =   liste   ;
        panelMain.getChildren().setAll(getClient(rowselected)) ; 
    }
        public static Client getClient() {
        return client;
    }

}
