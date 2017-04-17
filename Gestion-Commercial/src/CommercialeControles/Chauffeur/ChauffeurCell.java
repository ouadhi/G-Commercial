package CommercialeControles.Chauffeur;

import UIControle.Notification;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
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

public class ChauffeurCell extends HBox {

    public String nom;
    public String telephone;
    public String camion;
    public String voyage;
    public JFXPopup popup;

    JFXButton bttn;

    public ChauffeurCell(String nom, String telephone, String camion, String voyage) {
        this.nom = nom;
        this.telephone = telephone;
        this.camion = camion;
        this.voyage = voyage;
        this.popup = new JFXPopup();

        Label nomLabl = new Label(this.nom);
        nomLabl.setAlignment(Pos.CENTER_LEFT);
        Image img = new Image(getClass().getResourceAsStream("/icons/man.png"));
        ImageView icon = new ImageView(img);
        icon.prefHeight(10);
        icon.prefWidth(10);
        nomLabl.setGraphic(icon);

        Label telLabl = new Label(this.telephone);

        Label camionlabl = new Label(this.camion);

        Label voyegeLabl = new Label(this.voyage);

        bttn = new JFXButton();
        Image imgbtn = new Image(getClass().getResourceAsStream("/icons/more3.png"));
        ImageView imgviewbtn = new ImageView(imgbtn);
        imgviewbtn.prefHeight(50) ; 
        imgviewbtn.prefHeight(50);
        bttn.prefHeight(50) ; 
        bttn.prefWidth(50) ; 
        bttn.setGraphic(imgviewbtn);

        this.getChildren().setAll(nomLabl, telLabl, camionlabl, voyegeLabl, bttn);
        this.setSpacing(85);
        this.setMargin(nomLabl, new Insets(0, 0, 0, 45));
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
            Stage g = (Stage) ((Node) event.getSource()).getScene().getWindow();
            EditChauffeurDialog dialog = new EditChauffeurDialog(g, this);
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
