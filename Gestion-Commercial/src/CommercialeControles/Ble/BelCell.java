
package CommercialeControles.Ble;

import CommercialeControles.Client.ClienCell;
import UIControle.Methode;
import UIControle.Notification;
import UIControle.ShowPane;
import UIControle.StageDialog;
import UIControle.ViewUrl;
import com.gestionCommerciale.HibernateSchema.Ble;
import com.gestionCommerciale.Models.BleQueries;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class BelCell  extends GridPane{
    
//    private String code  ; 
//    private int id  ; 
//    private float quantite ;  
//    private double  prix  ;  
    private Ble ble;
    private JFXPopup popup;
    private JFXButton bttn;
    
    protected final ColumnConstraints columnConstraints;
    protected final ColumnConstraints columnConstraints0;
    protected final ColumnConstraints columnConstraints1;
    protected final ColumnConstraints columnConstraints2;
    protected final RowConstraints rowConstraints;
    protected final Label label;
    protected final Label label0;
    protected final Label label1;

    public BelCell(Ble ble) {
//        this.id = id;
//        this.code = code;
//        this.quantite = quantite;
//        this.prix = prix;
        this.ble = ble;
        popup = new JFXPopup(); 

        columnConstraints = new ColumnConstraints();
        columnConstraints0 = new ColumnConstraints();
        columnConstraints1 = new ColumnConstraints();
        columnConstraints2 = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        label = new Label();
        label0 = new Label();
        label1 = new Label();
        bttn = new JFXButton()  ; 
        
        bttn = new JFXButton();
        Image imgbtn = new Image(getClass().getResourceAsStream("/icons/more3.png"));
        ImageView imgviewbtn = new ImageView(imgbtn);
        imgviewbtn.prefHeight(50);
        imgviewbtn.prefHeight(50);
        bttn.prefHeight(50);
        bttn.prefWidth(50);
        bttn.setGraphic(imgviewbtn);
       

        setHgap(3.0);
        setPrefHeight(60.0);
        setPrefWidth(1114.0);

        columnConstraints.setHalignment(javafx.geometry.HPos.CENTER);
        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMaxWidth(87.0);
        columnConstraints.setMinWidth(70.0);
        columnConstraints.setPrefWidth(87.0);

        columnConstraints0.setHalignment(javafx.geometry.HPos.CENTER);
        columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints0.setMaxWidth(253.0);
        columnConstraints0.setMinWidth(145.0);
        columnConstraints0.setPrefWidth(251.0);

        columnConstraints1.setHalignment(javafx.geometry.HPos.CENTER);
        columnConstraints1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints1.setMaxWidth(492.0);
        columnConstraints1.setMinWidth(145.0);
        columnConstraints1.setPrefWidth(255.0);

        columnConstraints2.setHalignment(javafx.geometry.HPos.RIGHT);
        columnConstraints2.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints2.setMaxWidth(554.0);
        columnConstraints2.setMinWidth(77.0);
        columnConstraints2.setPrefWidth(522.0);

        rowConstraints.setMinHeight(10.0);
        rowConstraints.setPrefHeight(30.0);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        label.setText(ble.getCodeBle());
        label.setFont(new Font(17.0));

        GridPane.setColumnIndex(label0, 1);
        label0.setText(Double.toString(ble.getQte()));
        label0.setFont(new Font(17.0));

        GridPane.setColumnIndex(label1, 2);
        label1.setText(Double.toString(ble.getPrix()));
        label1.setFont(new Font(17.0));

        GridPane.setColumnIndex(bttn, 3);
        GridPane.setMargin(bttn, new Insets(0.0, 48.0, 0.0, 0.0));

        getColumnConstraints().add(columnConstraints);
        getColumnConstraints().add(columnConstraints0);
        getColumnConstraints().add(columnConstraints1);
        getColumnConstraints().add(columnConstraints2);
        getRowConstraints().add(rowConstraints);
        getChildren().add(label);
        getChildren().add(label0);
        getChildren().add(label1);
        getChildren().add(bttn);

        

        
        
        intpopup();

        bttn.setOnMouseClicked((event) -> {

            popup.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT, event.getX(), event.getY());
        });
        
    }
    
    public Ble getBle(){
    return ble;
    }
//
//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    public float getQuantite() {
//        return quantite;
//    }
//
//    public void setQuantite(float quantite) {
//        this.quantite = quantite;
//    }
//
//    public double getPrix() {
//        return prix;
//    }
//
//    public void setPrix(double prix) {
//        this.prix = prix;
//    }
//    
    
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
                Modifier.setData(ble);
                
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
                
                BleQueries.delete(ble);
                Notification.Deletenotification();
                 new ShowPane().showBle();
            }
            
            popup.close();

        });

    }
    
    
}
