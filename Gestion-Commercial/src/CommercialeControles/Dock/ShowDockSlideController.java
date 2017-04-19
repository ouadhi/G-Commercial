
package CommercialeControles.Dock;

import CommercialeControles.Camion.ShowdDetailCamionController;
import UIControle.Transition;
import UIControle.ViewUrl;
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


public class ShowDockSlideController implements Initializable {

    @FXML
    private JFXButton bttnlasu;
    @FXML
    private JFXButton next;
    @FXML
    private AnchorPane PanelMain;
    
    private JFXListView<DockCell> liste;
    private int i = 0;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void precedent(ActionEvent event) {
       if (i > 0) {
            --i;
         
            Transition transition = new Transition(PanelMain, getDock(i),2000, 0, -2000) ; 
            transition.show();

            if (i == 0) {
                bttnlasu.setDisable(true);
            } else {
                bttnlasu.setDisable(false);
                next.setDisable(false);
            }
        }
    }

    @FXML
    private void suivant(ActionEvent event) {
        if (i < liste.getItems().size()) {
          i++;
            Transition transition = new Transition(PanelMain, getDock(i),2000, 0, -2000) ; 
            transition.show();
            
            if (i == liste.getItems().size() - 1) {
                next.setDisable(true);
            } else {
                next.setDisable(false);
                bttnlasu.setDisable(false);
            }
        }
    }
    
    
     public AnchorPane getDock(int index) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(ViewUrl.ModifierDock));
            loader.load();

            DockCell dock = liste.getItems().get(index);

            ModifierDockController modification = loader.getController();
            modification.setData(dock.getNom(), dock.getWilaya() , Float.toString(dock.getDistance()) , Double.toString(dock.getPrix()));

            AnchorPane pane = loader.getRoot();

            return pane  ;  
        } catch (IOException ex) {
            Logger.getLogger(ShowdDetailCamionController.class.getName()).log(Level.SEVERE, null, ex);
            return null ; 
        }

    }
    
    public  void  setData  (int rowselected , JFXListView<DockCell> liste ){
        i  =  rowselected ; 
        this.liste =   liste   ;
        PanelMain.getChildren().setAll(getDock(rowselected)) ; 
    }
    
}