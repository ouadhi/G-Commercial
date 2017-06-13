package CommercialeControles.Autre;

import UIControle.Methode;
import UIControle.Notification;
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
import javafx.scene.text.Text;

public class ParamaitreController implements Initializable {

    @FXML
    private JFXComboBox<String> Annee;
    static JFXComboBox<String> AnneeStatic;
    @FXML
    private Label tva;
    @FXML
    private Text anneeselected;
    Annee annee = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Annee selected = AnneeQueries.getSelected();
        AnneeStatic = Annee;
        for (Annee annee : AnneeQueries.list()) {
            Annee.getItems().add(Integer.toString(annee.getIdAnnee()));
        }
        try {
            if (selected != null) {
                Annee.getSelectionModel().select(selected.getIdAnnee() + "");
                tva.setText(selected.getTva() + "");
                anneeselected.setText(selected.getIdAnnee() + "");
            }
        } catch (Exception e) {

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
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(ViewUrl.ModifierAnnee));
            loader.load();
            ModifierAnneeController Modifier = loader.getController();
            Modifier.setData(annee);
            AnchorPane root = loader.getRoot();
            StageDialog dialog = new StageDialog(Methode.getStage(event), root);
            dialog.show();
        } catch (IOException ex) {
            Notification.errorNotification("Selectionner une année SVP !!");
        }
    }

    @FXML
    private void close(MouseEvent event) {
        Methode.getStageMouses(event).close();
    }

    public void getTVASelected() {
        annee = AnneeQueries.getAnneeById(Integer.parseInt(Annee.getSelectionModel().getSelectedItem()));
        tva.setText(annee.getTva() + "");

    }

    @FXML
    private void select(ActionEvent event) {
        getTVASelected();
    }

    @FXML
    private void selectAnnee(ActionEvent event) {

        if (annee == null) {
            Notification.errorNotification("Selectionner une année SVP !!");
        } else {
            anneeselected.setText(annee.getIdAnnee() + "");
            Annee aa = AnneeQueries.getSelected();
            if (aa != null) {
                aa.setSelected(false);
                AnneeQueries.SaveOrUpdate(aa);
            }
            annee.setSelected(true);
            AnneeQueries.SaveOrUpdate(annee);
            Notification.Addnotification();

        }
    }

}
