package CommercialeControles.OperationAchat;

import UIControle.Methode;
import UIControle.ShowPane;
import UIControle.StageDialog;
import UIControle.ViewUrl;
import com.gestionCommerciale.HibernateSchema.Achat;
import com.gestionCommerciale.Models.AchatQueries;
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
import sun.plugin.javascript.navig.Anchor;

public class ListeAchatController implements Initializable {

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
    @FXML
    private JFXButton ajouter;
    @FXML
    private JFXListView<AchatCell> listeAchats;
    @FXML
    private JFXTextField rechreche;
    private AchatQueries achatQueries = new AchatQueries();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<Achat> achatList = achatQueries.list2();

        List<AchatCell> list = new ArrayList<>();
        for (int i = 0; i < achatList.size(); i++) {
            list.add(new AchatCell(achatList.get(i)));
        }
        ObservableList<AchatCell> myObservableList = FXCollections.observableList(list);
        listeAchats.setItems(myObservableList);
        listeAchats.setExpanded(true);
        setTotale();

    }

    @FXML
    private void oderByCode(ActionEvent event) {
    }

    @FXML
    private void orederByquantite(ActionEvent event) {
    }

    @FXML
    private void OrderByprix(ActionEvent event) {
    }

    @FXML
    private void setOrder(ActionEvent event) {
    }

    @FXML
    private void showAddStage(ActionEvent event) {
        new ShowPane().showOperationAchat();
    }

    @FXML
    private void showAchatSlide(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(ViewUrl.AchatSlid));
            loader.load();

            AchatSlidController controler = loader.getController();
            controler.setData(listeAchats, listeAchats.getSelectionModel().getSelectedIndex());

            AnchorPane pane = loader.getRoot();

            StageDialog dialog = new StageDialog(Methode.getStageMouses(event), pane);
            dialog.show();

        } catch (IOException ex) {
            Logger.getLogger(ListeAchatController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void rechrecher(ActionEvent event) {
    }

    private void setTotale() {
        total.setText(Integer.toString(listeAchats.getItems().size()));
    }

}
