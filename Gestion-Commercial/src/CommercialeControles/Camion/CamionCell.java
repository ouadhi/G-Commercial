package CommercialeControles.Camion;

import UIControle.Notification;
import UIControle.StageDialog;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import java.util.HashSet;
import java.util.Optional;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CamionCell extends HBox {

    public int id;
    public String marque;
    public String matricule;
    public int code_chauffeur;
    public float taille;
    public JFXPopup popup;
    
    
    JFXButton bttn  ; 

    public CamionCell(int id, String marque, String matricule, int  code_chauffeur, float taille) {
        this.id = id;
        this.marque = marque;
        this.matricule = matricule;
        this.code_chauffeur = code_chauffeur;
        this.taille = taille;
        this.popup = new JFXPopup();

        Label idLabel = new Label(Integer.toString(id));
        idLabel.setAlignment(Pos.CENTER_LEFT);

        Label marqueLabl = new Label(this.marque);
        marqueLabl.setAlignment(Pos.CENTER);

        Label matriculelabl = new Label(this.matricule);
        matriculelabl.setAlignment(Pos.CENTER);

        Label codechauffeurLabl = new Label(Integer.toString(this.code_chauffeur));
        codechauffeurLabl.setAlignment(Pos.CENTER);
        

        Label tailleLabel = new Label(Float.toString(this.taille));
        tailleLabel.setAlignment(Pos.CENTER);
        
        bttn = new JFXButton();
        Image imgbtn = new Image(getClass().getResourceAsStream("/icons/more3.png"));
        ImageView imgviewbtn = new ImageView(imgbtn);
        imgviewbtn.prefHeight(50) ; 
        imgviewbtn.prefHeight(50);
        bttn.prefHeight(50) ; 
        bttn.prefWidth(50) ; 
        bttn.setGraphic(imgviewbtn);

        

        this.getChildren().setAll(idLabel, marqueLabl, matriculelabl, codechauffeurLabl, tailleLabel ,bttn);
        this.setSpacing(85);
        this.setMargin(idLabel, new Insets(0,0,0,45));
        this.setMargin(bttn, new Insets(0, 0, 0, 220));
        this.setAlignment(Pos.CENTER_LEFT);
        this.setPrefHeight(50);

        intpopup();

        bttn.setOnMouseClicked((event) -> {

            popup.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT, event.getX() , event.getY());
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
           
            Stage stage  = (Stage)((Node)event.getSource()).getScene().getWindow() ; 
            
            ModifierCamionDialog dialog  =  new ModifierCamionDialog(stage, this)  ; 
            dialog.show();

        });

        supprimer.setOnAction(event -> {

            Optional<ButtonType> result = Notification.deleteAlert().showAndWait();
            if (result.get() == ButtonType.OK) {
                Notification.Deletenotification();
            }

        });

    }

}
