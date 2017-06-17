package CommercialeControles.Camion;

import java.util.Optional;

import com.gestionCommerciale.HibernateSchema.Camion;
import com.gestionCommerciale.Models.CamionQueries;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;

import UIControle.Notification;
import UIControle.ShowPane;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CamionCell extends GridPane {

	Camion camion;
	protected final ColumnConstraints columnConstraints;
	protected final ColumnConstraints columnConstraints0;
	protected final ColumnConstraints columnConstraints1;
	protected final ColumnConstraints columnConstraints2;
	protected final RowConstraints rowConstraints;
	protected final Label label;
	protected final Label label0;
	protected final Label label1;

	protected final JFXButton bttn;
	public JFXPopup popup;

	public CamionCell(Camion camion) {
		this.camion = camion;
		this.popup = new JFXPopup();

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
		rowConstraints = new RowConstraints();
		label = new Label();
		label0 = new Label();
		label1 = new Label();

		setHgap(3.0);
		setPrefHeight(55.0);
		setPrefWidth(1116.0);

		columnConstraints.setHalignment(javafx.geometry.HPos.CENTER);
		columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
		columnConstraints.setMaxWidth(191.0);
		columnConstraints.setMinWidth(67.0);
		columnConstraints.setPrefWidth(191.0);

		columnConstraints0.setHalignment(javafx.geometry.HPos.CENTER);
		columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
		columnConstraints0.setMaxWidth(260.0);
		columnConstraints0.setMinWidth(137.0);
		columnConstraints0.setPrefWidth(252.0);

		columnConstraints1.setHalignment(javafx.geometry.HPos.CENTER);
		columnConstraints1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
		columnConstraints1.setMaxWidth(492.0);
		columnConstraints1.setMinWidth(126.0);
		columnConstraints1.setPrefWidth(244.0);

		columnConstraints2.setHalignment(javafx.geometry.HPos.RIGHT);
		columnConstraints2.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
		columnConstraints2.setMaxWidth(554.0);
		columnConstraints2.setMinWidth(77.0);
		columnConstraints2.setPrefWidth(418.0);

		rowConstraints.setMinHeight(10.0);
		rowConstraints.setPrefHeight(30.0);
		rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

		GridPane.setHalignment(label, javafx.geometry.HPos.CENTER);
		GridPane.setValignment(label, javafx.geometry.VPos.CENTER);
		label.setAlignment(javafx.geometry.Pos.CENTER);
		label.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
		label.setText("" + this.camion.getCodeCamion());
		label.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
		label.setFont(new Font(17.0));
		label.setPadding(new Insets(1.0));

		GridPane.setColumnIndex(label0, 1);
		label0.setText(this.camion.getMarque());
		label0.setFont(new Font(17.0));

		GridPane.setColumnIndex(label1, 2);
		label1.setText(this.camion.getMatricule());
		label1.setFont(new Font(17.0));

		GridPane.setColumnIndex(bttn, 3);
		GridPane.setMargin(bttn, new Insets(0.0, 60.0, 0.0, 0.0));

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

	public Camion getCamion() {
		return camion;
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

		if (camion.isDeleted()) {
			box.setDisable(true);
		}

		modifier.setOnAction(event -> {

			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

			ModifierCamionDialog dialog = new ModifierCamionDialog(stage, this);
			dialog.show();
			popup.close();
		});
		supprimer.setOnAction(event -> {
			Optional<ButtonType> result = Notification.deleteAlert().showAndWait();
			if (result.get() == ButtonType.OK) {
				CamionQueries.archive(camion);
				new ShowPane().showCamion();
				Notification.Deletenotification();
			}
		});
	}

}
