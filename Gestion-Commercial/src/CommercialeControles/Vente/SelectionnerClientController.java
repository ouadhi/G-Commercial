package CommercialeControles.Vente;

import CommercialeControles.Client.AjouterClientController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.gestionCommerciale.HibernateSchema.Client;
import com.gestionCommerciale.Models.ClientQueries;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import CommercialeControles.Client.ClienCell;
import UIControle.Methode;
import UIControle.StageDialog;
import UIControle.ViewUrl;
import com.sun.javafx.scene.KeyboardShortcutsHandler;
import com.sun.javafx.scene.traversal.Direction;
import java.awt.AWTException;
import java.awt.Robot;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class SelectionnerClientController implements Initializable {

    @FXML
    private JFXListView<ClienCell> ClientListe;
    private ClientQueries clientQueries = new ClientQueries();
    @FXML
    private JFXTextField rechreche;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Client> listClientsDB = ClientQueries.list();
        List<ClienCell> list = new ArrayList<>();
        for (int i = 0; i < listClientsDB.size(); i++) {
            list.add(new ClienCell(listClientsDB.get(i)));
        }
        ObservableList<ClienCell> myObservableList = FXCollections.observableList(list);
        ClientListe.setItems(myObservableList);
        ClientListe.setExpanded(true);

    }

    private void nextEtape(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource(ViewUrl.selectChauffeurVente));
        OperationVenteController.staticpane.getChildren().setAll(pane);
    }

    @FXML
    private void rechrecher(KeyEvent event) {
        ClientListe.getItems().clear();
        List<Client> listClientsDB = ClientQueries.listRechereche(rechreche.getText());
        List<ClienCell> list = new ArrayList<>();
        for (int i = 0; i < listClientsDB.size(); i++) {
            list.add(new ClienCell(listClientsDB.get(i)));
        }
        ObservableList<ClienCell> myObservableList = FXCollections.observableList(list);
        ClientListe.setItems(myObservableList);
    }

    @FXML
    private void select(MouseEvent event) {
        OperationVenteController.client = ClientListe.getSelectionModel().getSelectedItem().getClient();
    }

    @FXML
    private void addClient(ActionEvent event) throws AWTException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(ViewUrl.AjouteClient));
            loader.load();

            AjouterClientController controler = loader.getController();
            controler.setData(ClientListe);

            AnchorPane root = loader.getRoot();
            Methode.moveFocus(root); 

            StageDialog dialog = new StageDialog(Methode.getStage(event), root);
            dialog.show();
        } catch (IOException ex) {
            Logger.getLogger(ClienCell.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

}
