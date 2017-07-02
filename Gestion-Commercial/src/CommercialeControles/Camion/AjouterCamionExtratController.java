package CommercialeControles.Camion;

import CommercialeControles.OperationAchat.CamionListeH;
import UIControle.Methode;
import UIControle.Notification;
import UIControle.ShowPane;
import com.gestionCommerciale.HibernateSchema.Camion;
import com.gestionCommerciale.Models.CamionQueries;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AjouterCamionExtratController implements Initializable {

    @FXML
    private ImageView close;
    @FXML
    private JFXTextField codecamion;
    @FXML
    private JFXTextField matricule;
    @FXML
    private JFXTextField typecamion;
    @FXML
    private JFXTextField poisCamion;
    @FXML
    private JFXButton savebttn;
    @FXML
    private JFXButton anullerbttn;
    @FXML
    private Label labelsave;

    JFXListView<CamionListeH> listeCamion;

    @FXML
    private void annuler(ActionEvent event) {

        Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void closewindow(MouseEvent event) {
        Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Methode.setOnlyDouble(poisCamion, 5);
        Methode.SetUpper(codecamion, 10);
        Methode.SetUpper(matricule, 12);
        Methode.setSelectedMouseClick(poisCamion);
        Methode.setSelectedMouseClick(codecamion);
        Methode.setSelectedMouseClick(matricule);
        Methode.setSelectedMouseClick(typecamion);
        Methode.setZeroRemoved(poisCamion);

        poisCamion.setText("0.00");
        Methode.setsizeString(typecamion, 32);

    }

    @FXML
    private void sauvegarder(ActionEvent event) {
        String code = codecamion.getText();
        String matricule = this.matricule.getText();
        String marque = typecamion.getText();
        // Double poid = Double.parseDouble(poisCamion.getText());

        if (code.isEmpty() || matricule.isEmpty() || marque.isEmpty()) {
            Notification.champVideNotification();
        } else {
            if (CamionQueries.getCamionByMatricule(matricule) != null) {
                Notification.error("Cette matricule exist deja!");
            } else if (CamionQueries.getCamionByCode(code) != null) {
                Notification.error("Ce code exist deja!");
            } else {
                try {
                    Camion camion = new Camion(code, matricule, marque);
                    camion.setDeleted(false);
                    CamionQueries.SaveOrUpdate(camion);

                    Notification.Addnotification();
                    refresh();
                    Methode.getStage(event).close();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void setData(JFXListView<CamionListeH> listeCamion) {
        this.listeCamion = listeCamion;
    }

    public void refresh() {

        List<Camion> listCamionsDB = CamionQueries.list();
        List<CamionListeH> list = new ArrayList<>();
        for (int i = 0; i < listCamionsDB.size(); i++) {
            list.add(new CamionListeH(listCamionsDB.get(i)));

        }

        CamionListeH ch = new CamionListeH(listeCamion);
        list.add(ch);
        ObservableList<CamionListeH> myObservableList = FXCollections.observableList(list);
        listeCamion.setItems(myObservableList);
        listeCamion.setOrientation(Orientation.HORIZONTAL);
       
    }
}
