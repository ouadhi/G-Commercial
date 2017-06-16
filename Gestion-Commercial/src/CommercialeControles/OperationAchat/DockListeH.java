
package CommercialeControles.OperationAchat;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gestionCommerciale.HibernateSchema.Dock;

import UIControle.Methode;
import UIControle.StageDialog;
import UIControle.ViewUrl;
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

public class DockListeH extends GridPane {

	protected ColumnConstraints columnConstraints;
	protected RowConstraints rowConstraints;
	protected RowConstraints rowConstraints0;
	protected RowConstraints rowConstraints1;
	protected ImageView imageView;
	protected Label label;
	protected Label label0;

	private Dock dock;

	public DockListeH() {

		columnConstraints = new ColumnConstraints();
		rowConstraints = new RowConstraints();
		rowConstraints0 = new RowConstraints();
		rowConstraints1 = new RowConstraints();
		imageView = new ImageView();
		label = new Label();
		label0 = new Label();

		setAlignment(javafx.geometry.Pos.CENTER);
		setMaxHeight(USE_PREF_SIZE);
		setMaxWidth(USE_PREF_SIZE);
		setMinHeight(USE_PREF_SIZE);
		setMinWidth(USE_PREF_SIZE);
		setPrefHeight(270.0);
		setPrefWidth(218.0);

		columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
		columnConstraints.setMinWidth(10.0);
		columnConstraints.setPrefWidth(100.0);

		rowConstraints.setMaxHeight(171.0);
		rowConstraints.setMinHeight(10.0);
		rowConstraints.setPrefHeight(171.0);
		rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

		rowConstraints0.setMaxHeight(149.0);
		rowConstraints0.setMinHeight(10.0);
		rowConstraints0.setPrefHeight(39.0);
		rowConstraints0.setValignment(javafx.geometry.VPos.CENTER);
		rowConstraints0.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

		rowConstraints1.setMaxHeight(109.0);
		rowConstraints1.setMinHeight(10.0);
		rowConstraints1.setPrefHeight(34.0);
		rowConstraints1.setValignment(javafx.geometry.VPos.CENTER);
		rowConstraints1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

		imageView.setFitHeight(109.0);
		imageView.setFitWidth(140.0);
		imageView.setPickOnBounds(true);
		imageView.setPreserveRatio(true);
		GridPane.setMargin(imageView, new Insets(0.0, 0.0, 0.0, 39.0));
		Image img = new Image(getClass().getResourceAsStream("/icons/plus.png"));
		imageView.setImage(img);

		GridPane.setHalignment(label, javafx.geometry.HPos.CENTER);
		GridPane.setRowIndex(label, 1);
		GridPane.setValignment(label, javafx.geometry.VPos.CENTER);
		label.setAlignment(javafx.geometry.Pos.CENTER);
		label.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
		label.setText("Ajouter Noveau");
		label.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
		label.setTextFill(javafx.scene.paint.Color.valueOf("#2c537a"));
		GridPane.setMargin(label, new Insets(0.0, 0.0, 0.0, -2.0));
		label.setFont(new Font("System Bold", 16.0));

		GridPane.setHalignment(label0, javafx.geometry.HPos.CENTER);
		GridPane.setRowIndex(label0, 2);
		GridPane.setValignment(label0, javafx.geometry.VPos.CENTER);
		label0.setAlignment(javafx.geometry.Pos.CENTER);
		label0.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
		label0.setText("Dock");
		label0.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
		label0.setTextFill(javafx.scene.paint.Color.valueOf("#2c537a"));
		GridPane.setMargin(label0, new Insets(0.0, 0.0, 0.0, -1.0));
		label0.setFont(new Font(16.0));

		getColumnConstraints().add(columnConstraints);
		getRowConstraints().add(rowConstraints);
		getRowConstraints().add(rowConstraints0);
		getRowConstraints().add(rowConstraints1);
		getChildren().add(imageView);
		getChildren().add(label);
		getChildren().add(label0);

		this.setOnMouseClicked(event -> {

			try {
				AnchorPane pane = FXMLLoader.load(getClass().getResource(ViewUrl.AjouterDock));

				StageDialog dialog = new StageDialog(Methode.getStageMouses(event), pane);
				dialog.show();
			} catch (IOException ex) {
				Logger.getLogger(DockListeH.class.getName()).log(Level.SEVERE, null, ex);
			}

		});
	}

	public DockListeH(Dock dock) {

		columnConstraints = new ColumnConstraints();
		rowConstraints = new RowConstraints();
		rowConstraints0 = new RowConstraints();
		rowConstraints1 = new RowConstraints();
		imageView = new ImageView();
		label = new Label();
		label0 = new Label();
		this.dock = dock;

		setAlignment(javafx.geometry.Pos.CENTER);
		setMaxHeight(USE_PREF_SIZE);
		setMaxWidth(USE_PREF_SIZE);
		setMinHeight(USE_PREF_SIZE);
		setMinWidth(USE_PREF_SIZE);
		setPrefHeight(270.0);
		setPrefWidth(218.0);
		setStyle("-fx-backgrounde-color:#f4f8f9;");
		setStyle("-fx-border-color: #39e18c;");

		columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
		columnConstraints.setMinWidth(10.0);
		columnConstraints.setPrefWidth(100.0);

		rowConstraints.setMaxHeight(171.0);
		rowConstraints.setMinHeight(10.0);
		rowConstraints.setPrefHeight(171.0);
		rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

		rowConstraints0.setMaxHeight(149.0);
		rowConstraints0.setMinHeight(10.0);
		rowConstraints0.setPrefHeight(39.0);
		rowConstraints0.setValignment(javafx.geometry.VPos.CENTER);
		rowConstraints0.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

		rowConstraints1.setMaxHeight(109.0);
		rowConstraints1.setMinHeight(10.0);
		rowConstraints1.setPrefHeight(34.0);
		rowConstraints1.setValignment(javafx.geometry.VPos.CENTER);
		rowConstraints1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

		imageView.setFitHeight(109.0);
		imageView.setFitWidth(140.0);
		imageView.setPickOnBounds(true);
		imageView.setPreserveRatio(true);
		GridPane.setMargin(imageView, new Insets(0.0, 0.0, 0.0, 39.0));
		Image img = new Image(getClass().getResourceAsStream("/icons/Dockgreen.png"));
		imageView.setImage(img);

		GridPane.setHalignment(label, javafx.geometry.HPos.CENTER);
		GridPane.setRowIndex(label, 1);
		GridPane.setValignment(label, javafx.geometry.VPos.CENTER);
		label.setAlignment(javafx.geometry.Pos.CENTER);
		label.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
		label.setText(this.dock.getNom());
		label.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
		label.setTextFill(javafx.scene.paint.Color.valueOf("#2c537a"));
		GridPane.setMargin(label, new Insets(0.0, 0.0, 0.0, -2.0));
		label.setFont(new Font("System Bold", 16.0));

		GridPane.setHalignment(label0, javafx.geometry.HPos.CENTER);
		GridPane.setRowIndex(label0, 2);
		GridPane.setValignment(label0, javafx.geometry.VPos.CENTER);
		label0.setAlignment(javafx.geometry.Pos.CENTER);
		label0.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
		label0.setText(this.dock.getWilaya() + "-" + this.dock.getDistance() + " KM");
		label0.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
		label0.setTextFill(javafx.scene.paint.Color.valueOf("#2c537a"));
		GridPane.setMargin(label0, new Insets(0.0, 0.0, 0.0, -1.0));
		label0.setFont(new Font(16.0));

		getColumnConstraints().add(columnConstraints);
		getRowConstraints().add(rowConstraints);
		getRowConstraints().add(rowConstraints0);
		getRowConstraints().add(rowConstraints1);
		getChildren().add(imageView);
		getChildren().add(label);
		getChildren().add(label0);

	}

	public Dock getDock() {
		return dock;
	}

	public void setDock(Dock dock) {
		this.dock = dock;
	}
}
