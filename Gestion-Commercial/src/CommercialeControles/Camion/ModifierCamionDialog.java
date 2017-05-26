package CommercialeControles.Camion;

import CommercialeControles.Chauffeur.AjouterChauffeuerDialog;
import CommercialeControles.Chauffeur.ModificationChauffeurController;
import com.gestionCommerciale.HibernateSchema.Camion;
import com.gestionCommerciale.Models.CamionQueries;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ModifierCamionDialog extends Stage {

    public ModifierCamionDialog(Stage owner, CamionCell camionCell) {
        try {
            initOwner(owner);
            initModality(Modality.APPLICATION_MODAL);
            setResizable(false);
            initStyle(StageStyle.UNDECORATED);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/CommercialeView/Camion/ModifierCamionView.fxml"));
            loader.load();
            ModifierCamionController modification = loader.getController();
            // modification.setData(camion.id , camion.matricule , camion.taille );
            ShowdDetailCamionController.setCamion(camionCell.getCamion());
            modification.setData(camionCell.getCamion());
            AnchorPane pane = loader.getRoot();
            Scene scene = new Scene(pane, 614, 475);
            setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(AjouterChauffeuerDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
