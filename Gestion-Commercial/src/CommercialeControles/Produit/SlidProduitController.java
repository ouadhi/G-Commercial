package CommercialeControles.Produit;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import CommercialeControles.Camion.ShowdDetailCamionController;
import UIControle.Transition;
import UIControle.ViewUrl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class SlidProduitController implements Initializable {

    @FXML
    private JFXButton last;
    @FXML
    private JFXButton next;
    @FXML
    private AnchorPane panemain;

    private JFXListView<ProduitCell> liste;
    private int i = 0;

    private AnchorPane getProduit(int index) {
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(ViewUrl.ModifierProduit));
            loader.load();
            ProduitCell produit = liste.getItems().get(index);
            ModifierProduitController produitController = loader.getController();
            produitController.setData(produit.getProduit());
            AnchorPane pane = loader.getRoot();
            return pane;
            
        } catch (IOException ex) {
            Logger.getLogger(ShowdDetailCamionController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void lastslid(ActionEvent event) {

        if (i > 0) {
            --i;

            Transition transition = new Transition(panemain, getProduit(i), 2000, 0, -2000);
            transition.show();

            if (i == 0) {
                last.setDisable(true);
            } else {
                last.setDisable(false);
                next.setDisable(false);
            }
        }
    }

    @FXML
    private void nextslid(ActionEvent event) {
        if (i < liste.getItems().size()) {
            i++;
            Transition transition = new Transition(panemain, getProduit(i), 2000, 0, -2000);
            transition.show();

            if (i == liste.getItems().size() - 1) {
                next.setDisable(true);
            } else {
                next.setDisable(false);
                last.setDisable(false);
            }
        }

    }

    public void setData(JFXListView<ProduitCell> liste, int index) {

        this.liste = liste;
        this.i = index;
        panemain.getChildren().setAll(getProduit(index));

    }

}
