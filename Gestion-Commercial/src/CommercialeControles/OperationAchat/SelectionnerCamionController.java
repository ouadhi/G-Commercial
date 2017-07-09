package CommercialeControles.OperationAchat;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.gestionCommerciale.HibernateSchema.Camion;
import com.gestionCommerciale.Models.CamionQueries;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class SelectionnerCamionController implements Initializable {

    @FXML
    private JFXListView<CamionListeH> listeCamion;
    private CamionListeH camion;

    private CamionQueries camionQueries = new CamionQueries();
    @FXML
    private JFXTextField rechreche;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //kada kada!! commenti lwla w decommenti zawja
        //List<Camion> listCamionsDB = CamionQueries.list();
        List<Camion> listCamionsDB = CamionQueries.listInterne();
        List<CamionListeH> list = new ArrayList<>();
        for (int i = 0; i < listCamionsDB.size(); i++) {
            list.add(new CamionListeH(listCamionsDB.get(i)));

        }

        CamionListeH ch = new CamionListeH(listeCamion,"EXTERNE");
        list.add(ch);
        ObservableList<CamionListeH> myObservableList = FXCollections.observableList(list);
        listeCamion.setItems(myObservableList);
        listeCamion.setOrientation(Orientation.HORIZONTAL);
        listeCamion.setExpanded(true);

    }

    @FXML
    private void recherche(KeyEvent event) {
        listeCamion.getItems().clear();

        List<Camion> listCamionsDB = CamionQueries.listRechreche(rechreche.getText());
        List<CamionListeH> list = new ArrayList<>();
        for (int i = 0; i < listCamionsDB.size(); i++) {
            list.add(new CamionListeH(listCamionsDB.get(i)));

        }

        CamionListeH ch = new CamionListeH(listeCamion,"EXTERNE");
        list.add(ch);
        ObservableList<CamionListeH> myObservableList = FXCollections.observableList(list);
        listeCamion.setItems(myObservableList);
        listeCamion.setOrientation(Orientation.HORIZONTAL);
        
    }

    @FXML
    private void select(MouseEvent event) {

        camion = listeCamion.getSelectionModel().getSelectedItem();
        FinOperationController.camion = camion;
    }

}
