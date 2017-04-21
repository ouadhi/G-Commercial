package CommercialeControles.Produit;

import CommercialeControles.Client.ClienCell;
import UIControle.Methode;
import UIControle.Notification;
import UIControle.StageDialog;
import UIControle.ViewUrl;
import com.gestionCommerciale.Models.BleQueries;
import com.gestionCommerciale.Models.ProduitQueries;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ProduitCell extends HBox {

    private int codeProduit;
    private String categorie;
    private String nom;
    private float quantite;
    private double prix;

    private JFXPopup popup;
    private JFXButton bttn;  

    public ProduitCell(int codeProduit, String categorie, String nom, float quantite, double prix) {
        this.codeProduit = codeProduit;
        this.categorie = categorie;
        this.nom = nom;
        this.quantite = quantite;
        this.prix = prix;
        
        popup =  new  JFXPopup()  ; 
        
        create_box();
        
    }
    
    

    public int getCodeProduit() {
        return codeProduit;
    }

    public void setCodeProduit(int codeProduit) {
        this.codeProduit = codeProduit;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Float getQuantite() {
        return quantite;
    }

    public void setQuantité(Float quantité) {
        this.quantite = quantité;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
    
    private  void create_box  () {
        
        Label codelabel = new Label(Integer.toString(this.codeProduit));
        codelabel.setAlignment(Pos.CENTER_LEFT);

        Label nomlabel = new Label(this.nom);

        Label categorielabel = new Label(this.categorie);

        Label quantiteLabel = new Label(Float.toString(this.quantite));
        
        Label prixLabel = new Label(Double.toString(this.prix)) ; 

        bttn = new JFXButton();
        Image imgbtn = new Image(getClass().getResourceAsStream("/icons/more3.png"));
        ImageView imgviewbtn = new ImageView(imgbtn);
        imgviewbtn.prefHeight(50);
        imgviewbtn.prefHeight(50);
        bttn.prefHeight(50);
        bttn.prefWidth(50);
        bttn.setGraphic(imgviewbtn);

        this.getChildren().setAll(codelabel, categorielabel ,nomlabel,quantiteLabel,prixLabel, bttn);
        this.setPadding(new Insets(0, 0, 0, 50));
        this.setSpacing(105);
        this.setMargin(bttn, new Insets(0, 0, 0, 240));
        this.setAlignment(Pos.CENTER_LEFT);
        this.setPrefHeight(50);
        
        intpopup();

        bttn.setOnMouseClicked((event) -> {

            popup.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT, event.getX(), event.getY());
        });
    }
    
    public void intpopup() {
        JFXButton modifier = new JFXButton("Modifier");
        JFXButton supprimer = new JFXButton("Supprimer");

        modifier.setPadding(new Insets(10));
        supprimer.setPadding(new Insets(10));

        VBox box = new VBox(modifier, supprimer);
        box.setStyle("-fx-background-color: #ffffff");

        popup.setContent(box);
        popup.setSource(bttn);

        modifier.setOnAction(event -> {
            
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(ViewUrl.ModifierProduit));
                loader.load();
                
                ModifierProduitController ModifierProduit =  loader.getController() ;
                ModifierProduit.setData(this.nom, this.categorie , this.quantite , this.prix );
                
                AnchorPane root = loader.getRoot();
                
                StageDialog dialog = new StageDialog(Methode.getStage(event), root) ;
                dialog.show();
            } catch (IOException ex) {
                Logger.getLogger(ClienCell.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            popup.close();

        });

        supprimer.setOnAction(event -> {

            Optional<ButtonType> result = Notification.deleteAlert().showAndWait() ; 
            if (result.get() == ButtonType.OK) {
                
                // requete DELETE from client  Where  id.client  =  codeclient 
                
                ProduitQueries q = new ProduitQueries();
                q.delete(q.getProduit(codeProduit+""));
                Notification.Deletenotification();
            }
            
            popup.close();

        });

    }
    
    
}
