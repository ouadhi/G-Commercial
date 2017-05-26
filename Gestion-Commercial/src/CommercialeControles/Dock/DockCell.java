
package CommercialeControles.Dock;

import CommercialeControles.Client.ClienCell;
import UIControle.Methode;
import UIControle.Notification;
import UIControle.ShowPane;
import UIControle.StageDialog;
import UIControle.ViewUrl;
import com.gestionCommerciale.Models.DockQueries;
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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class DockCell  extends GridPane{
    
    private int code  ; 
    private String nom  ; 
    private String wilaya ; 
    private double distance ; 
    private double prix  ; 
    
    
    protected final ColumnConstraints columnConstraints;
    protected final ColumnConstraints columnConstraints0;
    protected final ColumnConstraints columnConstraints1;
    protected final ColumnConstraints columnConstraints2;
    protected final ColumnConstraints columnConstraints3;
    protected final ColumnConstraints columnConstraints4;
    protected final RowConstraints rowConstraints;
    protected final Label label;
    protected final Label label0;
    protected final Label label1;
    protected final Label label2;
    protected final Label label3 ; 
    private JFXPopup popup;
    private JFXButton bttn;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getWilaya() {
        return wilaya;
    }

    public void setWilaya(String wilaya) {
        this.wilaya = wilaya;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public DockCell(int code, String nom, String wilaya, double distance, double prix) {
        this.code = code;
        this.nom = nom;
        this.wilaya = wilaya;
        this.distance = distance;
        this.prix = prix;
        
        popup = new JFXPopup(); 
        
        bttn = new JFXButton();
        Image imgbtn = new Image(getClass().getResourceAsStream("/icons/more3.png"));
        ImageView imgviewbtn = new ImageView(imgbtn);
        imgviewbtn.prefHeight(50);
        imgviewbtn.prefHeight(50);
        bttn.prefHeight(50);
        bttn.prefWidth(50);
        bttn.setGraphic(imgviewbtn);
        
        columnConstraints = new ColumnConstraints();
        columnConstraints0 = new ColumnConstraints();
        columnConstraints1 = new ColumnConstraints();
        columnConstraints2 = new ColumnConstraints();
        columnConstraints3 = new ColumnConstraints();
        columnConstraints4 = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        label = new Label();
        label0 = new Label();
        label1 = new Label();
        label2 = new Label();
        label3 = new Label();
        

        setHgap(3.0);
        setPrefHeight(55.0);
        setPrefWidth(1117.0);
        

        columnConstraints.setHalignment(javafx.geometry.HPos.CENTER);
        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMaxWidth(186.0);
        columnConstraints.setMinWidth(36.0);
        columnConstraints.setPrefWidth(112.0);

        columnConstraints0.setHalignment(javafx.geometry.HPos.CENTER);
        columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints0.setMaxWidth(254.0);
        columnConstraints0.setMinWidth(100.0);
        columnConstraints0.setPrefWidth(206.0);

        columnConstraints1.setHalignment(javafx.geometry.HPos.CENTER);
        columnConstraints1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints1.setMaxWidth(492.0);
        columnConstraints1.setMinWidth(126.0);
        columnConstraints1.setPrefWidth(172.0);

        columnConstraints2.setHalignment(javafx.geometry.HPos.CENTER);
        columnConstraints2.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints2.setMaxWidth(492.0);
        columnConstraints2.setMinWidth(102.0);
        columnConstraints2.setPrefWidth(177.0);

        columnConstraints3.setHalignment(javafx.geometry.HPos.CENTER);
        columnConstraints3.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints3.setMaxWidth(492.0);
        columnConstraints3.setMinWidth(102.0);
        columnConstraints3.setPrefWidth(177.0);

        columnConstraints4.setHalignment(javafx.geometry.HPos.RIGHT);
        columnConstraints4.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints4.setMaxWidth(554.0);
        columnConstraints4.setMinWidth(77.0);
        columnConstraints4.setPrefWidth(318.0);

        rowConstraints.setMinHeight(10.0);
        rowConstraints.setPrefHeight(30.0);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        label.setPrefHeight(22.0);
        label.setPrefWidth(46.0);
        label.setText(Integer.toString(code));
        label.setFont(new Font(17.0));

        GridPane.setColumnIndex(label0, 1);
        label0.setText(this.nom);
        label0.setFont(new Font(17.0));

        GridPane.setColumnIndex(label1, 2);
        label1.setText(this.wilaya);
        label1.setFont(new Font(17.0));

        GridPane.setColumnIndex(label2, 3);
        label2.setLayoutX(333.0);
        label2.setLayoutY(35.0);
        label2.setText(Double.toString(distance));
        label2.setFont(new Font(17.0));

        GridPane.setColumnIndex(label3, 4);
        label3.setLayoutX(545.0);
        label3.setLayoutY(27.0);
        label3.setText(Double.toString(prix));
        label3.setFont(new Font(17.0));

        GridPane.setColumnIndex(bttn, 5);
        GridPane.setMargin(bttn, new Insets(0.0, 40.0, 0.0, 0.0));

        getColumnConstraints().add(columnConstraints);
        getColumnConstraints().add(columnConstraints0);
        getColumnConstraints().add(columnConstraints1);
        getColumnConstraints().add(columnConstraints2);
        getColumnConstraints().add(columnConstraints3);
        getColumnConstraints().add(columnConstraints4);
        getRowConstraints().add(rowConstraints);
        getChildren().add(label);
        getChildren().add(label0);
        getChildren().add(label1);
        getChildren().add(label2);
        getChildren().add(label3);
        getChildren().add(bttn);
        
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
                loader.setLocation(getClass().getResource(ViewUrl.ModifierDock));
                loader.load();
                
                ModifierDockController ModifierDock =  loader.getController() ;
                ModifierDock.setData( Integer.toString(this.code), nom, wilaya, Double.toString(distance), Double.toString(prix));
                
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
                DockQueries.archive(DockQueries.getDock(code + ""));
                Notification.Deletenotification();
                new  ShowPane().showDock();
            }
            
            popup.close();

        });

    }
    
    
    
    
    
    
    
}
