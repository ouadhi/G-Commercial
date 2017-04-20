package CommercialeControles.Client;

import UIControle.Methode;
import UIControle.StageDialog;
import UIControle.ViewUrl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import com.gestionCommerciale.HibernateSchema.Client;
import com.gestionCommerciale.Models.ClientQueries;

public class ClientListController implements Initializable {

    @FXML
    private Label total;
    @FXML
    private MenuButton Order;
    @FXML
    private MenuButton NbShow;
    @FXML
    private JFXButton ajouter;
    @FXML
    private JFXListView<ClienCell> clientLsit;
    @FXML
    private JFXTextField rechreche;
    @FXML
    private MenuItem bycode;
    @FXML
    private MenuItem byname;
    @FXML
    private MenuItem byActivite;
    @FXML
    private MenuItem byregistre;
    @FXML
    private MenuItem btn20;
    @FXML
    private MenuItem btn50;
    @FXML
    private MenuItem btn100;
    @FXML
    private MenuItem btntout;
    private ClientQueries clientQueries = new ClientQueries();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Client> listClientsDB = clientQueries.clientsList();
        List<ClienCell> list = new ArrayList<>();
        for (int i = 0; i < listClientsDB.size(); i++) {
            list.add(new ClienCell(listClientsDB.get(i).getId(),listClientsDB.get(i).getPrenom()+" "+listClientsDB.get(i).getName()
                    , listClientsDB.get(i).getTypeActivity()
                    , listClientsDB.get(i).getNumRegCom()
                   ));            
        }

        ObservableList<ClienCell> myObservableList = FXCollections.observableList(list);
        clientLsit.setItems(myObservableList);

        clientLsit.setExpanded(true);

        setTotal();

    }

    @FXML
    private void setOrder(ActionEvent event) {
    }

    @FXML
    private void showAddStage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            AnchorPane root = FXMLLoader.load(getClass().getResource(ViewUrl.AjouteClient));

            StageDialog dialog = new StageDialog(Methode.getStage(event), root);
            dialog.show();
        } catch (IOException ex) {
            Logger.getLogger(ClientListController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void setTotal() {
        total.setText(Integer.toString(clientLsit.getItems().size()));
    }

    @FXML
    private void oderByCodeClient(ActionEvent event) {
        Order.setText("CodeClient");
    }

    @FXML
    private void orederByName(ActionEvent event) {
        Order.setText("Nom");
    }

    @FXML
    private void OrderByActivity(ActionEvent event) {
        Order.setText("Activité");
    }

    @FXML
    private void oderByRegistre(ActionEvent event) {
        Order.setText("Registre");
    }

    @FXML
    private void show20(ActionEvent event) {
        NbShow.setText("20");
    }

    @FXML
    private void shwo50(ActionEvent event) {
        NbShow.setText("50");
    }

    @FXML
    private void show100(ActionEvent event) {
        NbShow.setText("100");
    }

    @FXML
    private void showtout(ActionEvent event) {
        NbShow.setText("Tout");
    }

    @FXML
    private void showClient(MouseEvent event) {

        try {

            int seletedrow = clientLsit.getSelectionModel().getSelectedIndex();
            Stage stage = Methode.getStageMouses(event);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(ViewUrl.SlideClient));
            loader.load();

            ShowClienSlideController controlClient = loader.getController();
            controlClient.setData(seletedrow, clientLsit);

            AnchorPane root = loader.getRoot();

            StageDialog dialog = new StageDialog(stage, root);
            dialog.show();

        } catch (IOException ex) {
            Logger.getLogger(ClientListController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void rechrecher(ActionEvent event) {
    }

}
