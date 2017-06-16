package CommercialeControles.Chauffeur;

import java.util.Optional;

import com.gestionCommerciale.HibernateSchema.Chauffeur;
import com.gestionCommerciale.Models.ChauffeurQueries;
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

public class ChauffeurCell extends GridPane {

	Chauffeur chauffeur;

	protected final ColumnConstraints columnConstraints;
	protected final ColumnConstraints columnConstraints0;
	protected final ColumnConstraints columnConstraints1;
	protected final ColumnConstraints columnConstraints2;
	protected final RowConstraints rowConstraints;
	protected final Label label;
	protected final Label label0;
	protected final Label label1;

	public JFXPopup popup;
	JFXButton bttn;

	public ChauffeurCell(Chauffeur chauffeur) {
		this.chauffeur = chauffeur;
		this.popup = new JFXPopup();

		columnConstraints = new ColumnConstraints();
		columnConstraints0 = new ColumnConstraints();
		columnConstraints1 = new ColumnConstraints();
		columnConstraints2 = new ColumnConstraints();
		rowConstraints = new RowConstraints();
		label = new Label();
		label0 = new Label();
		label1 = new Label();

		bttn = new JFXButton();
		Image imgbtn = new Image(getClass().getResourceAsStream("/icons/more3.png"));
		ImageView imgviewbtn = new ImageView(imgbtn);
		imgviewbtn.prefHeight(50);
		imgviewbtn.prefHeight(50);
		bttn.prefHeight(50);
		bttn.prefWidth(50);
		bttn.setGraphic(imgviewbtn);

		setHgap(3.0);
		setPrefHeight(50.0);
		setPrefWidth(1117.0);

		columnConstraints.setHalignment(javafx.geometry.HPos.CENTER);
		columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
		columnConstraints.setMaxWidth(186.0);
		columnConstraints.setMinWidth(36.0);
		columnConstraints.setPrefWidth(151.0);

		columnConstraints0.setHalignment(javafx.geometry.HPos.CENTER);
		columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
		columnConstraints0.setMaxWidth(293.0);
		columnConstraints0.setMinWidth(100.0);
		columnConstraints0.setPrefWidth(277.0);

		columnConstraints1.setHalignment(javafx.geometry.HPos.CENTER);
		columnConstraints1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
		columnConstraints1.setMaxWidth(492.0);
		columnConstraints1.setMinWidth(126.0);
		columnConstraints1.setPrefWidth(228.0);

		columnConstraints2.setHalignment(javafx.geometry.HPos.RIGHT);
		columnConstraints2.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
		columnConstraints2.setMaxWidth(554.0);
		columnConstraints2.setMinWidth(77.0);
		columnConstraints2.setPrefWidth(406.0);

		rowConstraints.setMinHeight(10.0);
		rowConstraints.setPrefHeight(30.0);
		rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

		label.setPrefHeight(22.0);
		label.setPrefWidth(46.0);
		label.setText(this.chauffeur.getId() + "");
		label.setFont(new Font(17.0));

		GridPane.setColumnIndex(label0, 1);
		label0.setText(this.chauffeur.getNom() + " " + this.chauffeur.getPrenom());
		label0.setFont(new Font(17.0));

		GridPane.setColumnIndex(label1, 2);
		label1.setText(this.chauffeur.getTelephone());
		label1.setFont(new Font(17.0));

		GridPane.setColumnIndex(bttn, 3);
		GridPane.setMargin(bttn, new Insets(0.0, 50.0, 0.0, 0.0));

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

	public Chauffeur getChauffeur() {
		return chauffeur;
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

		if (chauffeur.isDeleted()) {
			box.setDisable(true);
		}

		modifier.setOnAction(event -> {
			Stage g = (Stage) ((Node) event.getSource()).getScene().getWindow();
			EditChauffeurDialog dialog = new EditChauffeurDialog(g, this);
			dialog.show();

		});

		supprimer.setOnAction(event -> {

			Optional<ButtonType> result = Notification.deleteAlert().showAndWait();
			if (result.get() == ButtonType.OK) {
				// get cell info
				ChauffeurQueries.archive(chauffeur);
				new ShowPane().showChauffeur();
				Notification.Deletenotification();
			}

		});

	}

}
