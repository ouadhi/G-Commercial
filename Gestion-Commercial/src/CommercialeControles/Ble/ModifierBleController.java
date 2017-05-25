/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 * FXML Controller class
 *
 * @author mac
 */
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
    //old id
    int oldCode;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Methode.setOnlyNumbre(quntite);
        Methode.setOnlyNumbre(prix);

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
                
                    BleQueries queries = new BleQueries();
                    Ble ble = queries.getBle(oldCode);
                    ble.setIdBle(Integer.parseInt(codeval));
                    ble.setPrix(Double.parseDouble(prixval));
                    ble.setQte(Integer.parseInt(quantiteval));
                    queries.SaveOrUpdate(ble);
                    Notification.Updatenotification();
                    
                    savelabel.setVisible(true);
                 
                    annuler(event);
                    closestage(event);
                    new ShowPane().showBle();
                
                // requete `Update

               

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

    public void setData(int code, float quantite, double prix) {

        this.code.setText(Integer.toString(code));
        this.prix.setText(Double.toString(prix));
        this.quntite.setText(Float.toString(quantite));
        oldCode=code;
        

    }

}
