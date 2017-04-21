package CommercialeControles.Ble;
import UIControle.Methode;
import UIControle.Notification;
import com.gestionCommerciale.HibernateSchema.Ble;
import com.gestionCommerciale.Models.BleQueries;
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
public class AjouterBleController implements Initializable {
    @FXML
    private ImageView close;
    @FXML
    private JFXButton addbttn;
    @FXML
    private JFXButton cancelbttn;
    @FXML
    private JFXTextField code;
    @FXML
    private JFXTextField quntite;
    @FXML
    private JFXTextField prix;
    @FXML
    private Label savelabel;
    BleQueries queries = new BleQueries();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Methode.setOnlyInteger(quntite, 16);
        Methode.setOnlyFloat(prix, 16);
    }
    @FXML
    private void close(MouseEvent event) {
        Stage stage = Methode.getStageMouses(event);
        stage.close();
    }
    @FXML
    private void saveble(ActionEvent event) {
        String codeval = this.code.getText();
        String quantiteval = this.quntite.getText();
        String prixval = this.prix.getText();
        if (codeval.isEmpty() || quantiteval.isEmpty() || prixval.isEmpty()) {
            Notification.notif(NotificationType.ERROR, "Vérification", "Vérifier que tout les champs sont remplis!");
        } else {
            if (queries.getBle(codeval) != null) {
                //notification for already exists
                Notification.error("Ce ble est exite déja!");
            } else {
                try {
                    Ble ble = new Ble(Integer.parseInt(codeval), Integer.parseInt(quantiteval), Double.parseDouble(prixval));
                    queries.SaveOrUpdate(ble);
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
    private void closestage(ActionEvent event) {
        Methode.getStage(event).close();
    }
}
