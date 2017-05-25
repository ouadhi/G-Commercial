package CommercialeControles.Ble;

import CommercialeControles.Client.ClientListController;
import UIControle.Methode;
import UIControle.StageDialog;
import UIControle.ViewUrl;
import com.gestionCommerciale.HibernateSchema.Ble;
import com.gestionCommerciale.Models.BleQueries;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

public class BleListeController implements Initializable {

    @FXML
    private Label total;
    @FXML
    private MenuButton Order;
    @FXML
    private MenuItem bycode;
    @FXML
    private MenuItem byquantite;
    @FXML
    private MenuItem byprix;
    private MenuButton NbShow;
    @FXML
    private JFXButton ajouter;
    @FXML
    private JFXTextField rechreche;
    @FXML
    private JFXListView<BelCell> listeBle;
    private BleQueries dockQueries = new BleQueries();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<Ble> listBlesDB = dockQueries.list();

        List<BelCell> list = new ArrayList<>();
        for (int i = 0; i < listBlesDB.size(); i++) {
            list.add(new BelCell(listBlesDB.get(i).getIdBle(),(float) listBlesDB.get(i).getQte(),
                    listBlesDB.get(i).getPrix()
            ));
        }

        ObservableList<BelCell> myObservableList = FXCollections.observableList(list);
        listeBle.setItems(myObservableList);
        listeBle.setExpanded(true);

        setTotale();

        possibleMot();

    }

    @FXML
    private void oderByCode(ActionEvent event) {
        Order.setText("Code");
    }

    @FXML
    private void orederByquantite(ActionEvent event) {
        Order.setText("Quantité");
    }

    @FXML
    private void OrderByprix(ActionEvent event) {
        Order.setText("Prix");
    }

    @FXML
    private void setOrder(ActionEvent event) {
    }

    private void show20(ActionEvent event) {
        NbShow.setText("20");
    }

    private void shwo50(ActionEvent event) {
        NbShow.setText("50");
    }

    private void show100(ActionEvent event) {
        NbShow.setText("100");
    }

    private void showtout(ActionEvent event) {
        NbShow.setText("Tout");
    }

    @FXML
    private void showAddStage(ActionEvent event) {

        try {

            Stage stage = Methode.getStage(event);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(ViewUrl.AjouterBle));
            loader.load();

            AjouterBleController control = loader.getController();
            control.setData(listeBle, total);

            AnchorPane root = loader.getRoot();

            StageDialog dialog = new StageDialog(stage, root);
            dialog.show();

        } catch (IOException ex) {
            Logger.getLogger(ClientListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showDockSlide(MouseEvent event) {

        try {

            int seletedrow = listeBle.getSelectionModel().getSelectedIndex();
            Stage stage = Methode.getStageMouses(event);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(ViewUrl.slideBle));
            loader.load();

            BleSlideController control = loader.getController();
            control.setData(seletedrow, listeBle);

            AnchorPane root = loader.getRoot();

            StageDialog dialog = new StageDialog(stage, root);
            dialog.show();

        } catch (IOException ex) {
            Logger.getLogger(ClientListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setTotale() {
        total.setText(Integer.toString(listeBle.getItems().size()));
    }

    public void possibleMot() {
      
        ArrayList<String> list = new ArrayList<>();
        list.add("karim");
        list.add("hichem1");
        list.add("hichem2");
        list.add("mohammed ouadhi");
        list.add("mohammed cherberabe");

        TextFields.bindAutoCompletion(rechreche, list);

    }

    @FXML
    private void rechrecheKeyReleased(KeyEvent event) {
        System.out.println(rechreche.getText());
    }

    private void rechrecheKeyTyped(KeyEvent event) {
        System.out.println(rechreche.getText());
    }

}
