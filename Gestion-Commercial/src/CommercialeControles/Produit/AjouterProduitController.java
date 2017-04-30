package CommercialeControles.Produit;

import UIControle.Methode;
import UIControle.Notification;
import UIControle.ShowPane;
import com.gestionCommerciale.HibernateSchema.Ble;
import com.gestionCommerciale.HibernateSchema.Produit;
import com.gestionCommerciale.Models.BleQueries;
import com.gestionCommerciale.Models.ProduitQueries;
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
import tray.notification.NotificationType;

public class AjouterProduitController implements Initializable {

    @FXML
    private ImageView close;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField categorie;
    @FXML
    private JFXTextField quantite;
    @FXML
    private JFXTextField prix;
    @FXML
    private JFXButton savebttn;
    @FXML
    private JFXButton cancelbttn;
    @FXML
    private Label savelabel;
    ProduitQueries queries = new ProduitQueries();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Methode.setOnlyInteger(quantite, 9);
        Methode.setOnlyFloat(prix, 16);
    }

    @FXML
    private void close(MouseEvent event) {
        Methode.getStageMouses(event).close();
    }

    @FXML
    private void sauvgarder(ActionEvent event) {
        String nomVal = nom.getText();
        String categorieVal = categorie.getText();
        String quantiteVal = quantite.getText();
        String prixVal = prix.getText();
        if (nomVal.isEmpty() || categorieVal.isEmpty() || quantiteVal.isEmpty() || prixVal.isEmpty()) {
            Notification.notif(NotificationType.ERROR, "Vérification", "Vérifier que tout les champs sont remplis!");
        } else {
            try {
                Produit ob = new Produit(nomVal,categorieVal, Integer.parseInt(quantiteVal), Float.parseFloat(prixVal));
                queries.SaveOrUpdate(ob);
                
                Notification.Addnotification();
                savelabel.setVisible(true);
                new  ShowPane().showClient();
                quitter(event);
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    private void quitter(ActionEvent event) {
        Methode.getStage(event).close();
    }
}
