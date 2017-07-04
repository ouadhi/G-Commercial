package CommercialeControles.Produit;

import CommercialeControles.Vente.PorduitH;
import java.net.URL;
import java.util.ResourceBundle;

import com.gestionCommerciale.HibernateSchema.Produit;
import com.gestionCommerciale.Models.ProduitQueries;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;

import UIControle.Methode;
import UIControle.Notification;
import UIControle.ShowPane;
import com.jfoenix.controls.JFXListView;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
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

    JFXListView<PorduitH> listeProduit =  null ;

    @FXML
    private void close(MouseEvent event) {
        Methode.getStageMouses(event).close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Methode.setOnlyDouble(quantite, 9);
        Methode.setOnlyDouble(prix, 16);

        Methode.setsizeString(nom, 30);
        Methode.setsizeString(categorie, 30);
        // Methode.setOnlyDouble(nom, 3);
    }

    @FXML
    private void quitter(ActionEvent event) {
        Methode.getStage(event).close();
    }

    @FXML
    private void sauvgarder(ActionEvent event) {

        String nomVal = nom.getText();
        String categorieVal = categorie.getText();
        String quantiteVal = quantite.getText();
        String prixVal = prix.getText();
        String code = this.code.getText();

        if (nomVal.isEmpty() || categorieVal.isEmpty() || quantiteVal.isEmpty() || prixVal.isEmpty()
                || code.isEmpty()) {
            Notification.notif(NotificationType.ERROR, "V\u00E9rification", "V\u00E9rifier que tout les champs sont remplis!");

        } else {
            if (ProduitQueries.getProduitByCode(code) != null) {
                // notification for already exists
                Notification.error("Ce produit exite d\u00E9ja!");
            } else {
                try {
                    Produit ob = new Produit(code, nomVal, categorieVal, Integer.parseInt(quantiteVal),
                            Double.parseDouble(prixVal), haveTVA.isSelected());
                    ProduitQueries.SaveOrUpdate(ob);

                    Notification.Addnotification();
                    //savelabel.setVisible(true);
                    if (listeProduit==null) {
                      new ShowPane().showProduit();  
                    }else{
                        refreshListeProduitH();
                    }
                    
                    quitter(event);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        }
    }

    public void refreshListeProduitH() {
        List<Produit> listBlesDB = ProduitQueries.list();
        List<PorduitH> list = new ArrayList<>();

        for (int i = 0; i < listBlesDB.size(); i++) {
            list.add(new PorduitH(listBlesDB.get(i)));
        }
         list.add(new PorduitH(listeProduit)) ;
        ObservableList<PorduitH> myObservableList = FXCollections.observableList(list);
        
        listeProduit.setItems(myObservableList);
        listeProduit.setOrientation(Orientation.HORIZONTAL);
    }

    public void setData(JFXListView<PorduitH> listeProduit) {
        this.listeProduit = listeProduit;

    }
}
