package CommercialeControles.Client;

import UIControle.Methode;
import UIControle.Notification;
import UIControle.StageDialog;
import UIControle.ViewUrl;
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

public class ClienCell extends HBox {

    private int codeclient;
    private String nom;
    private String activity;
    private String registre;
    private JFXPopup popup;
    private JFXButton bttn;

    public ClienCell(int codeclient, String nom, String activity, String registre) {
        this.codeclient = codeclient;
        this.nom = nom;
        this.activity = activity;
        this.registre = registre;
        
        popup = new JFXPopup(); 

        Label codeClientlabel = new Label(Integer.toString(this.codeclient));
        codeClientlabel.setAlignment(Pos.CENTER_LEFT);

        Label nomlabel = new Label(this.nom);

        Label activitylabel = new Label(this.activity);

        Label registrelabel = new Label(this.registre);

        bttn = new JFXButton();
        Image imgbtn = new Image(getClass().getResourceAsStream("/icons/more3.png"));
        ImageView imgviewbtn = new ImageView(imgbtn);
        imgviewbtn.prefHeight(50);
        imgviewbtn.prefHeight(50);
        bttn.prefHeight(50);
        bttn.prefWidth(50);
        bttn.setGraphic(imgviewbtn);

        this.getChildren().setAll(codeClientlabel, nomlabel, activitylabel, registrelabel, bttn);
        this.setPadding(new Insets(0, 0, 0, 50));
        this.setSpacing(85);
        this.setMargin(bttn, new Insets(0, 0, 0, 240));
        this.setAlignment(Pos.CENTER_LEFT);
        this.setPrefHeight(50);
        
        intpopup();

        bttn.setOnMouseClicked((event) -> {

            popup.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT, event.getX(), event.getY());
        });
        

    }

    public int getCodeclient() {
        return codeclient;
    }

    public void setCodeclient(int codeclient) {
        this.codeclient = codeclient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getRegistre() {
        return registre;
    }

    public void setRegistre(String registre) {
        this.registre = registre;
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
                loader.setLocation(getClass().getResource(ViewUrl.ModifierClient));
                loader.load();
                
                ModifierClientController controlClient =  loader.getController() ;
                controlClient.SetData(codeclient, nom, nom, activity, registre);
                
                AnchorPane root = loader.getRoot();
                
                StageDialog dialog = new StageDialog(Methode.getStage(event), root) ;
                dialog.show();
            } catch (IOException ex) {
                Logger.getLogger(ClienCell.class.getName()).log(Level.SEVERE, null, ex);
            }


        });

        supprimer.setOnAction(event -> {

            Optional<ButtonType> result = Notification.deleteAlert().showAndWait();
            if (result.get() == ButtonType.OK) {
                
                // requete DELETE from client  Where  id.client  =  codeclient 
                
                Notification.Deletenotification();
            }

        });

    }
    
    
}
