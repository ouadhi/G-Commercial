package CommercialeControles.Dock;

import UIControle.Methode;
import UIControle.Notification;
import UIControle.ShowPane;
import com.gestionCommerciale.HibernateSchema.Dock;
import com.gestionCommerciale.Models.DockQueries;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tray.notification.NotificationType;

public class ModifierDockController implements Initializable {

    @FXML
    private ImageView close;
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
        private String id;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Methode.setOnlyNumbre(prix);
        Methode.setOnlyNumbre(distance);
    }

    @FXML
    private void sauvgarder(ActionEvent event) {
        String nom = this.nom.getText();
        String wilaya = this.wilaya.getText();
        String distance = this.distance.getText();
        String prix = this.prix.getText();

        if (nom.isEmpty() || wilaya.isEmpty() || distance.isEmpty() || prix.isEmpty()) {

            Notification.champVideNotification();

        } else {
            Optional<ButtonType> result = Notification.updateAlert().showAndWait();
            if (result.get() == ButtonType.OK) {
                if (nom.isEmpty() || wilaya.isEmpty() || distance.isEmpty() || prix.isEmpty()) {

                    Notification.notif(NotificationType.ERROR,"Vérification", "Vérifier que tout les champs sont remplis!");

                } else {
                    DockQueries dq = new DockQueries();
                    Dock dock = dq.getDock(id);
                    dock.setNom(nom);
                    dock.setWilaya(wilaya);
                    dock.setPrixUnitTrans(Double.parseDouble(prix));
                    dock.setDistance(Float.parseFloat(distance));
                    dq.SaveOrUpdate(dock);
                    
                    Notification.Updatenotification();
                    new ShowPane().showDock();
                    savelabel.setVisible(true);
                    quitter(event);
                }
            }

        }
    }

   

    @FXML
    private void quitter(ActionEvent event) {

        Stage currentStage = Methode.getStage(event);
        currentStage.close();

    }

    @FXML
    private void close(MouseEvent event) {
        Stage currentStage = Methode.getStageMouses(event);
        currentStage.close();
    }

    public void setData(String id,String nom, String wilaya, String distance, String prix) {

        this.nom.setText(nom);
        this.wilaya.setText(wilaya);
        this.distance.setText(distance);
        this.prix.setText(prix);
        this.id = id;
    }
}
