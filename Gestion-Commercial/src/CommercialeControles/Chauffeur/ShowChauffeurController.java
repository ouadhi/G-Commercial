
package CommercialeControles.Chauffeur;

import CommercialeControles.Camion.ShowdDetailCamionController;
import UIControle.Transition;
import UIControle.ViewUrl;
import com.gestionCommerciale.HibernateSchema.Chauffeur;
import com.gestionCommerciale.Models.ChauffeurQueries;
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


public class ShowChauffeurController implements Initializable {

    @FXML
    private JFXButton precedent;
    @FXML
    private JFXButton suivant;
    @FXML
    private AnchorPane PaneMain;
    private JFXListView<ChauffeurCell> liste;
    private int i = 0;
    
    private static  Chauffeur chauffeur;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }

    @FXML
    private void suivant(ActionEvent event) {
        if (i < liste.getItems().size()) {
          i++;
            Transition transition = new Transition(PaneMain, getChauffeur(i), -2000,0, 2000) ; 
            transition.show();
            
            if (i == liste.getItems().size() - 1) {
                suivant.setDisable(true);
            } else {
                suivant.setDisable(false);
                precedent.setDisable(false);
            }
        }

    }

    @FXML
    private void precedent(ActionEvent event) {
        if (i > 0) {
            --i;
         
            Transition transition = new Transition(PaneMain, getChauffeur(i),2000, 0, -2000) ; 
            transition.show();

            if (i == 0) {
                precedent.setDisable(true);
            } else {
                suivant.setDisable(false);
                precedent.setDisable(false);
            }
        }

    }

    public AnchorPane getChauffeur(int id) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(ViewUrl.modifierChauffeur));
            loader.load();

            ChauffeurCell chauffeurCell = liste.getItems().get(id);

            ModificationChauffeurController modification = loader.getController();
            //modification.setData(chauffeur.nom, "112", chauffeur.telephone, chauffeur.voyage);
            ChauffeurQueries chauffeurQueries= new ChauffeurQueries();
            chauffeur=chauffeurQueries.getChauffeur(chauffeurCell.nom);
            modification.setData(chauffeur.getNomChauffeur(), chauffeur.getPrenomChauffeur()
                    ,String.valueOf(chauffeur.getId()) ,chauffeur.getTelephone() , chauffeur.getType());

            AnchorPane pane = loader.getRoot();

            return pane  ;  
        } catch (IOException ex) {
            Logger.getLogger(ShowdDetailCamionController.class.getName()).log(Level.SEVERE, null, ex);
            return null ; 
        }

    }
    
    
    public void  setListechauffeur (JFXListView<ChauffeurCell> liste , int idItemSelceted){
        this.liste =  liste  ; 
        i = idItemSelceted ; 
        PaneMain.getChildren().setAll(getChauffeur(i)) ;
    }

    public static Chauffeur getChauffeur() {
        return chauffeur;
    }

    
}
