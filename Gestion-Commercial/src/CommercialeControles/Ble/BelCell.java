
package CommercialeControles.Ble;

import CommercialeControles.Client.ClienCell;
import CommercialeControles.Dock.ModifierDockController;
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

public class BelCell  extends HBox{
    
    private int code  ; 
    private float quantite ;  
    private double  prix  ;  
    
    private JFXPopup popup;
    private JFXButton bttn;

    public BelCell(int code, float quantite, double prix) {
        this.code = code;
        this.quantite = quantite;
        this.prix = prix;
        
        popup = new JFXPopup(); 

        Label codelabel = new Label(Integer.toString(this.code));
        codelabel.setAlignment(Pos.CENTER_LEFT);

        Label quantitelabel = new Label(Float.toString(this.quantite));

        Label prixlabel = new Label(Double.toString(this.prix));

        bttn = new JFXButton();
        Image imgbtn = new Image(getClass().getResourceAsStream("/icons/more3.png"));
        ImageView imgviewbtn = new ImageView(imgbtn);
        imgviewbtn.prefHeight(50);
        imgviewbtn.prefHeight(50);
        bttn.prefHeight(50);
        bttn.prefWidth(50);
        bttn.setGraphic(imgviewbtn);

        this.getChildren().setAll(codelabel,quantitelabel,prixlabel, bttn);
        this.setPadding(new Insets(0, 0, 0, 50));
        this.setSpacing(120);
        this.setMargin(bttn, new Insets(0, 0, 0, 300));
        this.setAlignment(Pos.CENTER_LEFT);
        this.setPrefHeight(50);
        
        intpopup();

        bttn.setOnMouseClicked((event) -> {

            popup.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT, event.getX(), event.getY());
        });
        
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public float getQuantite() {
        return quantite;
    }

    public void setQuantite(float quantite) {
        this.quantite = quantite;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
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
                loader.setLocation(getClass().getResource(ViewUrl.modifierBle));
                loader.load();
                
                ModifierBleController Modifier =  loader.getController() ;
                Modifier.setData(code, quantite, prix);
                
                AnchorPane root = loader.getRoot();
                
                StageDialog dialog = new StageDialog(Methode.getStage(event), root) ;
                dialog.show();
            } catch (IOException ex) {
                Logger.getLogger(ClienCell.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            popup.close();

        });

        supprimer.setOnAction(event -> {

            Optional<ButtonType> result = Notification.deleteAlert().showAndWait();
            if (result.get() == ButtonType.OK) {
                
                // requete DELETE from client  Where  id.client  =  codeclient 
                
                Notification.Deletenotification();
            }
            
            popup.close();

        });

    }
    
    
}
