package CommercialeControles.Ble;

import UIControle.Methode;
import UIControle.Notification;
import UIControle.ShowPane;
import com.gestionCommerciale.HibernateSchema.Ble;
import com.gestionCommerciale.Models.BleQueries;
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

public class ModifierBleController implements Initializable {

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
    Ble ble;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Methode.setOnlyDouble(quntite, 16);
        Methode.setOnlyDouble(prix, 16);

        Methode.SetUpper(code);
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
            Notification.champVideNotification();
        } else {
            Optional<ButtonType> result = Notification.deleteAlert().showAndWait();
            if (result.get() == ButtonType.OK) {
                ble.setCodeBle((codeval));
                ble.setPrix(Double.parseDouble(prixval));
                ble.setQte(Double.parseDouble(quantiteval));
                Ble ble2 = BleQueries.getBleByCode(codeval);
                if (ble2 != null && ble2.getIdBle() != ble.getIdBle()) {
                    Notification.error("Ce code existe déja, utilisé un autre");
                } else if (BleQueries.SaveOrUpdate(ble)) {
                    Notification.Updatenotification();
                    savelabel.setVisible(true);
                    annuler(event);
                    closestage(event);
                    new ShowPane().showBle();
                } else {
                    Notification.error("Erreur!");
                }
            }
        }
    }

    @FXML
    private void closestage(ActionEvent event) {
        Methode.getStage(event).close();
    }

    private void annuler(ActionEvent event) {
        Stage g = (Stage) ((Node) event.getSource()).getScene().getWindow();
        g.close();
    }

    public void setData(Ble ble) {

        Methode.SetUpper(code);
        this.ble = ble;
        this.code.setText(ble.getCodeBle());
        this.prix.setText(Double.toString(ble.getPrix()));
        this.quntite.setText(Double.toString(ble.getQte()));
    }
}
