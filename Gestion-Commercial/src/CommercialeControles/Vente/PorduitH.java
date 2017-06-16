package CommercialeControles.Vente;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gestionCommerciale.HibernateSchema.Produit;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import CommercialeControles.Produit.ProduitListController;
import UIControle.Methode;
import UIControle.Notification;
import UIControle.StageDialog;
import UIControle.ViewUrl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

public class PorduitH extends GridPane {

	private Produit produit;
	private boolean selected;
	private JFXTextField quantite;

	ColumnConstraints columnConstraints;

	RowConstraints rowConstraints;

	RowConstraints rowConstraints0;

	RowConstraints rowConstraints1;
	RowConstraints rowConstraints2;
	ImageView imageView;
	Label qantitproduit;
	Label nomproduit;
	JFXButton Ajouterbttn;
	Label prix;
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

		jFXButton.setOnAction(event -> {
			try {
				AnchorPane pane = FXMLLoader.load(getClass().getResource(ViewUrl.AjouterProduit));
				StageDialog stage = new StageDialog(Methode.getStage(event), pane);

				stage.showAndWait();
			} catch (IOException ex) {
				Logger.getLogger(ProduitListController.class.getName()).log(Level.SEVERE, null, ex);
			}

		});
	}
	public PorduitH(Produit produit) {
		this.produit = produit;

		columnConstraints = new ColumnConstraints();
		rowConstraints = new RowConstraints();
		rowConstraints0 = new RowConstraints();
		rowConstraints1 = new RowConstraints();
		rowConstraints2 = new RowConstraints();
		imageView = new ImageView();
		qantitproduit = new Label();
		nomproduit = new Label();
		Ajouterbttn = new JFXButton();
		quantite = new JFXTextField();
		prix = new Label();

		setPrefHeight(275.0);
		setPrefWidth(218.0);

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

		imageView.setFitHeight(90.0);
		imageView.setFitWidth(150.0);
		imageView.setPickOnBounds(true);
		imageView.setPreserveRatio(true);
		imageView.setImage(new Image(getClass().getResourceAsStream("/icons/ProduitGreen.png")));

		GridPane.setRowIndex(qantitproduit, 1);
		qantitproduit.setAlignment(javafx.geometry.Pos.CENTER);
		qantitproduit.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
		qantitproduit.setPrefHeight(17.0);
		qantitproduit.setPrefWidth(106.0);
		qantitproduit.setText(this.produit.getQuantite() + " unité");
		qantitproduit.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
		GridPane.setMargin(qantitproduit, new Insets(0.0, 0.0, -41.0, 0.0));

		GridPane.setRowIndex(nomproduit, 1);
		nomproduit.setText(this.produit.getNom());
		GridPane.setMargin(nomproduit, new Insets(0.0, 0.0, 49.0, 0.0));

		GridPane.setRowIndex(Ajouterbttn, 4);
		Ajouterbttn.setText("Selectionner");
		Ajouterbttn.prefHeight(40);
		Ajouterbttn.prefWidth(40);
		Ajouterbttn.setStyle("-fx-background-color: #74c080; -fx-background-radius: 30; -fx-text-fill: white;");
		Ajouterbttn.setFont(new Font(16.0));

		GridPane.setRowIndex(quantite, 2);
		quantite.setMaxSize(75, 40);
		quantite.setPrefSize(75, 40);
		quantite.setMinSize(75, 40);
		quantite.setVisible(false);
		quantite.setAlignment(javafx.geometry.Pos.CENTER);
		quantite.setFont(new Font(14.0));
		quantite.setPromptText("Quantité");

		GridPane.setRowIndex(prix, 1);
		prix.setAlignment(javafx.geometry.Pos.CENTER);
		prix.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
		prix.setLayoutX(96.0);
		prix.setLayoutY(149.0);
		prix.setPrefHeight(17.0);
		prix.setPrefWidth(97.0);
		prix.setText(this.produit.getPrix() + " DA");
		prix.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
		GridPane.setMargin(prix, new Insets(0.0, 0.0, 4.0, 0.0));

		getColumnConstraints().add(columnConstraints);
		getRowConstraints().add(rowConstraints);
		getRowConstraints().add(rowConstraints0);
		getRowConstraints().add(rowConstraints1);
		getRowConstraints().add(rowConstraints2);
		getChildren().add(imageView);
		getChildren().add(qantitproduit);
		getChildren().add(nomproduit);
		getChildren().add(Ajouterbttn);
		getChildren().add(quantite);
		getChildren().add(prix);

		Methode.setOnlyInteger(quantite, 5);
		quantite.setVisible(false);

		Ajouterbttn.setOnAction(event -> {
			if (!selected) {
				Ajouterbttn.setStyle("-fx-background-color: #d64242;-fx-background-radius: 30; -fx-text-fill: white;");
				quantite.setVisible(true);
				selected = true;
				OperationVenteController.produitselected.add(this);
				SelectionnerProduitController.staticNbselected
						.setText(Integer.toString(OperationVenteController.produitselected.size()));
			} else {
				Ajouterbttn.setStyle("-fx-background-color: #74c080;-fx-background-radius: 30; -fx-text-fill: white;");
				quantite.setVisible(false);
				selected = false;
				OperationVenteController.produitselected.remove(this);
				SelectionnerProduitController.staticNbselected
						.setText(Integer.toString(OperationVenteController.produitselected.size()));
			}

		});

		quantite.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				int quan = Integer.parseInt(newValue);
				if (quan > produit.getQuantite()) {
					quantite.setText(oldValue);
					Notification.errorNotification("Vous dépasser quantité disponible au stocke");
				}

			}
		});

	}
	public Produit getProduit() {
		return produit;
	}

	public int getQuantite() {
		return Integer.parseInt(quantite.getText());
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

}
