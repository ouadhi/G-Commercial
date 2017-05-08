
package CommercialeControles.Vente;

import UIControle.Notification;
import com.gestionCommerciale.HibernateSchema.Facture;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import java.util.Optional;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class VenteCell extends GridPane{
    
  private  Facture facture  ; 
  
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
    protected final Label label3;
    protected final JFXButton bttn;
      private JFXPopup popup;

    public VenteCell(Facture facture) {
        this.facture = facture;
        
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
        bttn = new JFXButton();
        popup = new JFXPopup() ;

        setHgap(3.0);
        setMaxHeight(51.0);
        setMaxWidth(1141.0);
        setMinHeight(51.0);
        setMinWidth(1114.0);
        setPrefHeight(51.0);
        setPrefWidth(1114.0);
        setStyle(" -fx-border-color: #F3F3F3; -fx-border-insets: 0 0.0 1 0;");

        columnConstraints.setHalignment(javafx.geometry.HPos.CENTER);
        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMaxWidth(134.0);
        columnConstraints.setMinWidth(70.0);
        columnConstraints.setPrefWidth(134.0);

        columnConstraints0.setHalignment(javafx.geometry.HPos.CENTER);
        columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints0.setMaxWidth(170.0);
        columnConstraints0.setMinWidth(106.0);
        columnConstraints0.setPrefWidth(106.0);

        columnConstraints1.setHalignment(javafx.geometry.HPos.CENTER);
        columnConstraints1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints1.setMaxWidth(170.0);
        columnConstraints1.setMinWidth(170.0);
        columnConstraints1.setPrefWidth(170.0);

        columnConstraints2.setHalignment(javafx.geometry.HPos.CENTER);
        columnConstraints2.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints2.setMaxWidth(145.0);
        columnConstraints2.setMinWidth(145.0);
        columnConstraints2.setPrefWidth(145.0);

        columnConstraints3.setHalignment(javafx.geometry.HPos.CENTER);
        columnConstraints3.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints3.setMaxWidth(145.0);
        columnConstraints3.setMinWidth(145.0);
        columnConstraints3.setPrefWidth(145.0);

        columnConstraints4.setHalignment(javafx.geometry.HPos.RIGHT);
        columnConstraints4.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints4.setMaxWidth(300.0);
        columnConstraints4.setMinWidth(121.0);
        columnConstraints4.setPrefWidth(121.0);

        rowConstraints.setMinHeight(10.0);
        rowConstraints.setPrefHeight(30.0);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        label.setText(""+this.facture.getIdFacture());
        label.setFont(new Font(17.0));

        GridPane.setColumnIndex(label0, 1);
        label0.setText("Client");
        label0.setFont(new Font(17.0));

        GridPane.setColumnIndex(label1, 2);
        label1.setText(this.facture.getDate().toString());
        label1.setFont(new Font(17.0));

        GridPane.setColumnIndex(label2, 3);
        label2.setText(""+this.facture.getMontant());
        label2.setFont(new Font(17.0));

        GridPane.setColumnIndex(label3, 4);
        label3.setText(Double.toString(this.facture.getMontant()));
        label3.setFont(new Font(17.0));
        

        GridPane.setColumnIndex(bttn, 5);
        Image imgbtn = new Image(getClass().getResourceAsStream("/icons/more3.png"));
        ImageView imgviewbtn = new ImageView(imgbtn);
        imgviewbtn.prefHeight(50);
        imgviewbtn.prefHeight(50);
        bttn.prefHeight(50);
        bttn.prefWidth(50);
        bttn.setGraphic(imgviewbtn);

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
        JFXButton details =  new JFXButton("détails");

        modifier.setPadding(new Insets(10));
        supprimer.setPadding(new Insets(10));

        VBox box = new VBox(modifier, supprimer,details);
        box.setStyle("-fx-background-color: #ffffff");

        popup.setContent(box);
        popup.setSource(bttn);

        modifier.setOnAction(event -> {
            
            
            popup.close();

        });
        supprimer.setOnAction(event -> {

            Optional<ButtonType> result = Notification.deleteAlert().showAndWait() ; 
            if (result.get() == ButtonType.OK) {
                
                
            }
            
            popup.close();

        });

    }
  
  
    
}
