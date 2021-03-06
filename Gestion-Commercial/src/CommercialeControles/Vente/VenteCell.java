package CommercialeControles.Vente;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gestionCommerciale.HibernateSchema.Facture;
import com.gestionCommerciale.Models.ClientQueries;
import com.gestionCommerciale.Models.FactureQueries;
import com.gestionCommerciale.Models.PaymentQueries;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;

import CommercialeControles.Client.ClienCell;
import UIControle.Methode;
import UIControle.Notification;
import UIControle.ShowPane;
import UIControle.StageDialog;
import UIControle.ViewUrl;
import javafx.event.ActionEvent;
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

public class VenteCell extends GridPane {

    private Facture facture;

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
        popup = new JFXPopup();

        setHgap(3.0);
        setMaxHeight(51.0);
        setMaxWidth(1141.0);
        setMinHeight(51.0);
        setMinWidth(1114.0);
        setPrefHeight(51.0);
        setPrefWidth(1114.0);

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

        FactureQueries fq = new FactureQueries();
        ClientQueries cq = new ClientQueries();
        PaymentQueries pq = new PaymentQueries();

        label.setText("" + this.facture.getIdFacture());
        label.setFont(new Font(17.0));

        GridPane.setColumnIndex(label0, 1);
        label0.setText(this.facture.getClient().getName() + " " + this.facture.getClient().getPrenom());
        label0.setFont(new Font(17.0));

        GridPane.setColumnIndex(label1, 2);
        label1.setText(this.facture.getDate().toString());
        label1.setFont(new Font(17.0));

        GridPane.setColumnIndex(label2, 3);
        //double
        label2.setText(Methode.DoubleFormat(this.facture.getMontantFinal())+"");
        label2.setFont(new Font(17.0));

        JFXButton produitdefacture = new JFXButton("Porduit");
        produitdefacture
                .setStyle("-fx-background-color :#96d496 ;  -fx-text-fill : white ; -fx-background-radius :50 ; ");
        double versment = 0;
        for (int i = 0; i < this.facture.getClient().getPayments().size(); i++) {
            System.err.println(versment);

            versment += this.facture.getClient().getPayments().get(i).getMontant();
        }
        GridPane.setColumnIndex(produitdefacture, 4);
        label3.setText(Methode.DoubleFormat((this.facture.getMontant() - versment))+"");
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
        getChildren().add(produitdefacture);
        getChildren().add(bttn);

        intpopup();

        if (this.facture.isDeleted()) {
            this.setStyle("-fx-background-color:eec5c5;");
        }

        bttn.setOnMouseClicked((event) -> {

            popup.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT, event.getX(), event.getY());
        });

        produitdefacture.setOnAction(e -> {
            try {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(ViewUrl.produitfacture));
                loader.load();

                ListeProduitVenteController Modifier = loader.getController();
                Modifier.setData(this.facture);

                AnchorPane root = loader.getRoot();

                StageDialog dialog = new StageDialog(Methode.getStage(e), root);
                dialog.show();

            } catch (IOException ex) {
                Logger.getLogger(VenteCell.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    public Facture getFacture() {
        return facture;
    }

    public void imprimer(Facture f, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(ViewUrl.printvent));
            loader.load();

            PrintViewController print = loader.getController();
            print.setData(f);

            AnchorPane root = loader.getRoot();

            StageDialog dialog = new StageDialog(Methode.getStage(event), root);
            dialog.show();
        } catch (IOException ex) {
            Logger.getLogger(ClienCell.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void intpopup() {
        JFXButton modifier = new JFXButton("D\u00E9tail");
        JFXButton supprimer = new JFXButton("Archiver");
        JFXButton details = new JFXButton("imprimer");

        modifier.setPadding(new Insets(10));
        supprimer.setPadding(new Insets(10));
        details.setPadding(new Insets(10));

        VBox box = new VBox(modifier, supprimer, details);
        box.setStyle("-fx-background-color: #ffffff");

        popup.setContent(box);
        popup.setSource(bttn);

        if (facture.isDeleted()) {
            box.setDisable(true);
        }

        modifier.setOnAction(event -> {
            try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(ViewUrl.showvente));
			loader.load();

			ModifierVenteController Modifier = loader.getController();
			Modifier.setData(facture);

			AnchorPane root = loader.getRoot();

			StageDialog dialog = new StageDialog(Methode.getStage(event), root);
			dialog.show();

		} catch (IOException ex) {
			Logger.getLogger(VenteCell.class.getName()).log(Level.SEVERE, null, ex);
		}
            popup.close();

        });

        supprimer.setOnAction(event -> {

            Optional<ButtonType> result = Notification.deleteAlert().showAndWait();
            if (result.get() == ButtonType.OK) {
                FactureQueries.archive(facture);
                Notification.Deletenotification();
                new ShowPane().showVenteListe();
            }

            popup.close();

        });

        details.setOnAction(evnet -> {
            imprimer(facture, evnet);

        });

    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }

}
