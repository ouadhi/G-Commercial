package CommercialeControles.Produit;

import UIControle.Methode;
import UIControle.Notification;
import UIControle.ShowPane;
import com.gestionCommerciale.HibernateSchema.Produit;
import com.gestionCommerciale.Models.ProduitQueries;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
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
    @FXML
    private JFXTextField code;
    @FXML
    private JFXToggleButton haveTVA;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Methode.setOnlyDouble(quantite, 9);
        Methode.setOnlyDouble(prix, 16);
        
         Methode.SetUpper(nom);
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
        String code  = this.code.getText()  ; 
        
        if (nomVal.isEmpty() || categorieVal.isEmpty() || quantiteVal.isEmpty() || prixVal.isEmpty()|| code.isEmpty()) {
            Notification.notif(NotificationType.ERROR, "Vérification", "Vérifier que tout les champs sont remplis!");
            
        } else {
            if (ProduitQueries.getProduitByCode(code) != null) {
                //notification for already exists
                Notification.error("Ce produit exite déja!");
            } else {
            try {
                Produit ob = new Produit(code,nomVal,categorieVal, Integer.parseInt(quantiteVal), Double.parseDouble(prixVal),haveTVA.isSelected());
                ProduitQueries.SaveOrUpdate(ob);
                
                Notification.Addnotification();
                savelabel.setVisible(true);
                new  ShowPane().showProduit();
                quitter(event);
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
        }
        }
    }

    @FXML
    private void quitter(ActionEvent event) {
        Methode.getStage(event).close();
    }
}
