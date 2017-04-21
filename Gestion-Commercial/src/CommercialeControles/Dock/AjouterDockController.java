package CommercialeControles.Dock;
import UIControle.Methode;
import UIControle.Notification;
import com.gestionCommerciale.HibernateSchema.Dock;
import com.gestionCommerciale.Models.DockQueries;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tray.notification.NotificationType;

public class AjouterDockController implements Initializable {

    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField wilaya;
    @FXML
    private JFXTextField distance;
    @FXML
    private JFXTextField prix;
    @FXML
    private JFXButton savebttn;
    @FXML
    private JFXButton cancelbttn;
    @FXML
    private Label savelabel;
    @FXML
    private ImageView close;
    DockQueries q = new DockQueries();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Methode.setOnlyNumbre(prix);
        Methode.setOnlyNumbre(distance);
    }

    @FXML
    private void sauvgarder(ActionEvent event) {
        String nom = this.nom.getText();
        String wilaya = this.wilaya.getText();
        String prix = this.prix.getText();
        String distance = this.distance.getText();

        if (nom.isEmpty() || wilaya.isEmpty() || distance.isEmpty() || prix.isEmpty()) {
            Notification.notif(NotificationType.ERROR,"Vérification", "Vérifier que tout les champs sont remplis!");
        } else {
            if (q.getDockByNameAndWilaya(nom, wilaya) != null) {
                //notification for already exists
                Notification.error("Ce dock est exite déja!");
            } else {
                // add to database
                try {
                    Dock dock = new Dock(nom, wilaya, Float.parseFloat(distance), Float.parseFloat(prix));
                    q.SaveOrUpdate(dock);
                    Notification.Addnotification();
                    //new ShowPane().showClientList();
                    savelabel.setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @FXML
    private void quitter(ActionEvent event
    ) {

        Stage currentStage = Methode.getStage(event);
        currentStage.close();

    }

    @FXML
    private void close(MouseEvent event
    ) {
        Stage currentStage = Methode.getStageMouses(event);
        currentStage.close();
    }

}
