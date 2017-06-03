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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ModifierProduitController implements Initializable {

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
    private JFXToggleButton TVA;
    Produit produit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Methode.setOnlyNumbre(quantite);
        Methode.setOnlyNumbre(prix);

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
        String code = this.code.getText();

        if (nomVal.isEmpty() || categorieVal.isEmpty() || quantiteVal.isEmpty() || prixVal.isEmpty()) {

            Notification.champVideNotification();

        } else {

            Optional<ButtonType> result = Notification.updateAlert().showAndWait();
            if (result.get() == ButtonType.OK) {
                //back
                produit.setCodeProduit(code);
                produit.setNom(nomVal);
                produit.setCategory(categorieVal);
                produit.setQuantite(Integer.parseInt(quantiteVal));
                produit.setPrix(Double.parseDouble(prixVal));
                produit.setHaveTva(TVA.isSelected());

                ProduitQueries.SaveOrUpdate(produit);

                Notification.Updatenotification();
                savelabel.setVisible(true);
                new ShowPane().showProduit();
                quitter(event);
            }
        }

    }

    @FXML
    private void quitter(ActionEvent event) {
        Methode.getStage(event).close();
    }

    public void setData(Produit produit) {
        Methode.SetUpper(nom);

        this.produit = produit;
        this.nom.setText(produit.getNom());
        this.categorie.setText(produit.getCategory());
        this.quantite.setText(Integer.toString(produit.getQuantite()));
        this.prix.setText(Double.toString(produit.getPrix()));
        this.code.setText(this.produit.getCodeProduit());
        if (this.produit.isHaveTva()) {
            TVA.setSelected(true);
        } else {
            TVA.setSelected(false);
        }

    }

}
