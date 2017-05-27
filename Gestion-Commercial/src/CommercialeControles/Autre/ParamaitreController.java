package CommercialeControles.Autre;

import UIControle.Methode;
import UIControle.StageDialog;
import UIControle.ViewUrl;
import com.gestionCommerciale.HibernateSchema.Annee;
import com.gestionCommerciale.Models.AnneeQueries;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ParamaitreController implements Initializable {

    @FXML
    private JFXComboBox<String> Annee;
    @FXML
    private Label tva;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (Annee annee : AnneeQueries.list()) {
            Annee.getItems().add(Integer.toString(annee.getIdAnnee())) ; 
        }
        

        
    }

    @FXML
    private void addAnnee(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(ViewUrl.addAnneeEtTva));
            StageDialog dialog = new StageDialog(Methode.getStage(event), pane);
            dialog.show();
        } catch (IOException ex) {
            Logger.getLogger(ParamaitreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modiferAnnee(ActionEvent event) {
    }

    @FXML
    private void close(MouseEvent event) {
        Methode.getStageMouses(event).close();
    }
    
    public void getTVASelected () {
     Annee annee    = AnneeQueries.getAnneeById(Integer.parseInt(Annee.getSelectionModel().getSelectedItem())) ; 
     tva.setText(annee.getTva()+"");
        
    }

    @FXML
    private void select(ActionEvent event) {
        getTVASelected();
    }

}
