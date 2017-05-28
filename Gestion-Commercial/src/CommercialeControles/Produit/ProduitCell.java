package CommercialeControles.Produit;

import CommercialeControles.Client.ClienCell;
import UIControle.Methode;
import UIControle.Notification;
import UIControle.ShowPane;
import UIControle.StageDialog;
import UIControle.ViewUrl;
import com.gestionCommerciale.HibernateSchema.Produit;
import com.gestionCommerciale.Models.AnneeQueries;
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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ProduitCell extends GridPane {

    Produit produit;
    

    protected ColumnConstraints columnConstraints;
    protected ColumnConstraints columnConstraints0;
    protected ColumnConstraints columnConstraints1;
    protected ColumnConstraints columnConstraints2;
    protected ColumnConstraints columnConstraints3;
    protected ColumnConstraints columnConstraints4;
    protected ColumnConstraints columnConstraints5;
    protected RowConstraints rowConstraints;

    protected Label label;
    protected Label label0;
    protected Label label1;
    protected Label label2;
    protected Label label3;
    protected Label label4;
    protected JFXButton bttn;

    private JFXPopup popup;

    public ProduitCell(Produit produit) {
        this.produit = produit;
        popup = new JFXPopup();

        create_box();

    }

    public Produit getProduit() {
        return produit;
    }

    public void setCodeProduit(Produit produit) {
        this.produit = produit;
    }
    private void create_box() {

        columnConstraints = new ColumnConstraints();
        columnConstraints0 = new ColumnConstraints();
        columnConstraints1 = new ColumnConstraints();
        columnConstraints2 = new ColumnConstraints();
        columnConstraints3 = new ColumnConstraints();
        columnConstraints4 = new ColumnConstraints();
        columnConstraints5 = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        label = new Label();
        label0 = new Label();
        label1 = new Label();
        label2 = new Label();
        label3 = new Label();
        label4 = new Label();

        setHgap(3.0);
        setPrefHeight(57.0);
        setPrefWidth(1114.0);

        columnConstraints.setHalignment(javafx.geometry.HPos.CENTER);
        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMaxWidth(70.0);
        columnConstraints.setMinWidth(70.0);
        columnConstraints.setPrefWidth(70.0);

        columnConstraints0.setHalignment(javafx.geometry.HPos.CENTER);
        columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints0.setMaxWidth(170.0);
        columnConstraints0.setMinWidth(170.0);
        columnConstraints0.setPrefWidth(170.0);

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

        columnConstraints4.setHalignment(javafx.geometry.HPos.CENTER);
        columnConstraints4.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints4.setMaxWidth(121.0);
        columnConstraints4.setMinWidth(121.0);
        columnConstraints4.setPrefWidth(121.0);

        columnConstraints5.setHalignment(javafx.geometry.HPos.RIGHT);
        columnConstraints5.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints5.setMaxWidth(300.0);
        columnConstraints5.setMinWidth(100.0);
        columnConstraints5.setPrefWidth(100.0);

        rowConstraints.setMinHeight(10.0);
        rowConstraints.setPrefHeight(30.0);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        label.setText(Integer.toString(produit.getIdProduit()));
        GridPane.setHalignment(label, javafx.geometry.HPos.CENTER);
        label.setFont(new Font(17.0));

        GridPane.setColumnIndex(label0, 1);
        label0.setText(this.produit.getNom());
        GridPane.setHalignment(label0, javafx.geometry.HPos.CENTER);
        label0.setFont(new Font(17.0));

        GridPane.setColumnIndex(label1, 2);
        label1.setText(this.produit.getCategory());
        GridPane.setHalignment(label1, javafx.geometry.HPos.CENTER);
        label1.setFont(new Font(17.0));

        GridPane.setColumnIndex(label2, 3);
        label2.setText(Integer.toString(this.produit.getQuantite()));
        GridPane.setHalignment(label2, javafx.geometry.HPos.CENTER);
        label2.setFont(new Font(17.0));

        GridPane.setColumnIndex(label3, 4);
        GridPane.setHalignment(label3, javafx.geometry.HPos.CENTER);
        label3.setText(Double.toString(this.produit.getPrix()));
        label3.setFont(new Font(17.0));

        GridPane.setColumnIndex(label4, 5);
        GridPane.setHalignment(label4, javafx.geometry.HPos.CENTER);
        if (this.produit.isHaveTva()) {
             label4.setText(""+AnneeQueries.getSelected().getTva());
        } else {
             label4.setText("0");
        }
       
        label4.setFont(new Font(17.0));

        bttn = new JFXButton();
        Image imgbtn = new Image(getClass().getResourceAsStream("/icons/more3.png"));
        ImageView imgviewbtn = new ImageView(imgbtn);
        imgviewbtn.prefHeight(50);
        imgviewbtn.prefHeight(50);
        bttn.prefHeight(50);
        bttn.prefWidth(50);
        bttn.setGraphic(imgviewbtn);

        GridPane.setColumnIndex(bttn, 6);
        GridPane.setHalignment(bttn, javafx.geometry.HPos.CENTER);

        getColumnConstraints().add(columnConstraints);
        getColumnConstraints().add(columnConstraints0);
        getColumnConstraints().add(columnConstraints1);
        getColumnConstraints().add(columnConstraints2);
        getColumnConstraints().add(columnConstraints3);
        getColumnConstraints().add(columnConstraints4);
        getColumnConstraints().add(columnConstraints5);
        getRowConstraints().add(rowConstraints);
        getChildren().add(label);
        getChildren().add(label0);
        getChildren().add(label1);
        getChildren().add(label2);
        getChildren().add(label3);
        getChildren().add(label4);
        getChildren().add(bttn);

        intpopup();

        bttn.setOnMouseClicked((event) -> {

            popup.show(JFXPopup.PopupVPosition.BOTTOM, JFXPopup.PopupHPosition.RIGHT, event.getX(), event.getY());
        });
    }

    public void intpopup() {
        JFXButton modifier = new JFXButton("Modifier");
        JFXButton supprimer = new JFXButton("Archiver");

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

                ModifierProduitController ModifierProduit = loader.getController();
                ModifierProduit.setData(produit);

                AnchorPane root = loader.getRoot();

                StageDialog dialog = new StageDialog(Methode.getStage(event), root);
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
                ProduitQueries.archive(produit);
                Notification.Deletenotification();
                new ShowPane().showProduit();
            }

            popup.close();

        });

    }

}
