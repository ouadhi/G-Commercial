
package CommercialeControles.Vente;

import CommercialeControles.Produit.ProduitListController;
import UIControle.Methode;
import UIControle.StageDialog;
import UIControle.ViewUrl;
import com.gestionCommerciale.HibernateSchema.Produit;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;


public class PorduitH extends GridPane{
    
   private  Produit  produit   ; 
   private boolean  selected ; 
   

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public PorduitH(Produit produit) {
        this.produit = produit;
        
        ColumnConstraints columnConstraints;
        RowConstraints rowConstraints;
        RowConstraints rowConstraints0;
        RowConstraints rowConstraints1;
        RowConstraints rowConstraints2;
        ImageView imageView;
        Label label;
        Label label0;
        JFXTextField spinner ; 
        JFXButton Ajouterbttn ;

        columnConstraints = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        rowConstraints0 = new RowConstraints();
        rowConstraints1 = new RowConstraints();
        rowConstraints2 = new RowConstraints();
        imageView = new ImageView();
        label = new Label();
        label0 = new Label();
        spinner = new JFXTextField(); 
        Ajouterbttn = new JFXButton();

        setPrefHeight(275.0);
        setPrefWidth(218.0);
        setStyle("-fx-background-radius: 15;");

        columnConstraints.setHalignment(javafx.geometry.HPos.CENTER);
        columnConstraints.setHalignment(javafx.geometry.HPos.CENTER);
        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMinWidth(10.0);
        columnConstraints.setPrefWidth(100.0);

        rowConstraints.setMaxHeight(273.0);
        rowConstraints.setMinHeight(USE_PREF_SIZE);
        rowConstraints.setPrefHeight(97.0);
        rowConstraints.setValignment(javafx.geometry.VPos.CENTER);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints0.setMaxHeight(273.0);
        rowConstraints0.setMinHeight(USE_PREF_SIZE);
        rowConstraints0.setPrefHeight(70.0);
        rowConstraints0.setValignment(javafx.geometry.VPos.CENTER);
        rowConstraints0.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints1.setMaxHeight(273.0);
        rowConstraints1.setMinHeight(USE_PREF_SIZE);
        rowConstraints1.setPrefHeight(53.0);
        rowConstraints1.setValignment(javafx.geometry.VPos.CENTER);
        rowConstraints1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints2.setMaxHeight(273.0);
        rowConstraints2.setMinHeight(14.0);
        rowConstraints2.setPrefHeight(53.0);
        rowConstraints2.setValignment(javafx.geometry.VPos.CENTER);
        rowConstraints2.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        imageView.setFitHeight(92.0);
        imageView.setFitWidth(134.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(new Image(getClass().getResourceAsStream("/icons/ProduitGreen.png")));

        GridPane.setRowIndex(label, 1);
        label.setAlignment(javafx.geometry.Pos.CENTER);
        label.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        label.setText(produit.getNom());
        label.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        label.setFont(new Font("System Bold", 18.0));
        GridPane.setMargin(label, new Insets(0.0, 0.0, -28.0, 0.0));

        GridPane.setRowIndex(label0, 1);
        label0.setAlignment(javafx.geometry.Pos.CENTER);
        label0.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        label0.setText(Float.toString(this.produit.getPrix()));
        label0.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        label0.setFont(new Font(15.0));
        GridPane.setMargin(label0, new Insets(0.0, 0.0, 31.0, 0.0));
        
        GridPane.setRowIndex(spinner, 2);
        spinner.setMaxSize(75, 40);
        spinner.setPrefSize(75, 40);
        spinner.setMinSize(75, 40);
        spinner.setVisible(false);
        spinner.setAlignment(javafx.geometry.Pos.CENTER);
        spinner.setFont(new Font(14.0));
        spinner.setPromptText("Quantité");
        

        GridPane.setRowIndex(Ajouterbttn, 3);
        Ajouterbttn.setText("Selectionner");
        Ajouterbttn.prefHeight(40) ; 
        Ajouterbttn.prefWidth(40) ; 
        Ajouterbttn.setStyle("-fx-background-color: #74c080; -fx-background-radius: 30; -fx-text-fill: white;");
        Ajouterbttn.setFont(new Font(16.0));
        

        getColumnConstraints().add(columnConstraints);
        getRowConstraints().add(rowConstraints);
        getRowConstraints().add(rowConstraints0);
        getRowConstraints().add(rowConstraints1);
        getRowConstraints().add(rowConstraints2);
        getChildren().add(imageView);
        getChildren().add(label);
        getChildren().add(label0);
        getChildren().add(spinner);
        getChildren().add(Ajouterbttn);
        
        
        Methode.setOnlyInteger(spinner, 5);
        
        
        Ajouterbttn.setOnAction(event ->{
            if (!selected) {
                 Ajouterbttn.setStyle("-fx-background-color: #d64242;-fx-background-radius: 30; -fx-text-fill: white;");
                 spinner.setVisible(true);
                 selected = true ; 
                 SelectionnerProduitController.listeProduitSelected.add(this) ; 
            }else{
                Ajouterbttn.setStyle("-fx-background-color: #74c080;-fx-background-radius: 30; -fx-text-fill: white;");
                spinner.setVisible(false);
                 selected = false ; 
                 SelectionnerProduitController.listeProduitSelected.remove(this) ;
            }
            
        });
        
        
    }

    public PorduitH() {
          ColumnConstraints columnConstraints;
     RowConstraints rowConstraints;
     RowConstraints rowConstraints0;
     JFXButton jFXButton;

        columnConstraints = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        rowConstraints0 = new RowConstraints();
        jFXButton = new JFXButton();

        setPrefHeight(275.0);
        setPrefWidth(217.0);
        setStyle(" -fx-background-radius: 15;");

        columnConstraints.setHalignment(javafx.geometry.HPos.CENTER);
        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMinWidth(10.0);
        columnConstraints.setPrefWidth(100.0);

        rowConstraints.setMaxHeight(273.0);
        rowConstraints.setMinHeight(14.0);
        rowConstraints.setPrefHeight(273.0);
        rowConstraints.setValignment(javafx.geometry.VPos.CENTER);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints0.setMaxHeight(148.0);
        rowConstraints0.setMinHeight(2.0);
        rowConstraints0.setPrefHeight(2.0);
        rowConstraints0.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        jFXButton.setStyle("-fx-background-color: #74c080; -fx-background-radius: 70;-fx-text-fill: white;");
        jFXButton.setText("Ajouter Produit");
        jFXButton.setPrefSize(146, 146);
        jFXButton.setFont(new Font(16.0));

        getColumnConstraints().add(columnConstraints);
        getRowConstraints().add(rowConstraints);
        getRowConstraints().add(rowConstraints0);
        getChildren().add(jFXButton);
        
        jFXButton.setOnAction(event->{
             try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(ViewUrl.AjouterProduit));
            StageDialog stage = new StageDialog(Methode.getStage(event), pane);

            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(ProduitListController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        });
    }
    
    
   
   
    
    
}
