package CommercialeControles.OperationAchat;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gestionCommerciale.HibernateSchema.Achat;
import com.gestionCommerciale.Models.AchatQueries;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;

import UIControle.Methode;
import UIControle.Notification;
import UIControle.ShowPane;
import UIControle.StageDialog;
import UIControle.ViewUrl;
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

public class AchatCell extends GridPane {

	protected final ColumnConstraints columnConstraints;
	protected final ColumnConstraints columnConstraints0;
	protected final ColumnConstraints columnConstraints1;
	protected final ColumnConstraints columnConstraints2;
	protected final RowConstraints rowConstraints;
	protected final Label label;
	protected final Label label0;
	protected final Label label1;
	private JFXPopup popup;
	private JFXButton bttn;
	Achat achat;

	// edited by mohammed
	public AchatCell(Achat achat) {
		this.achat = achat;

		columnConstraints = new ColumnConstraints();
		columnConstraints0 = new ColumnConstraints();
		columnConstraints1 = new ColumnConstraints();
		columnConstraints2 = new ColumnConstraints();
		rowConstraints = new RowConstraints();
		label = new Label();
		label0 = new Label();
		label1 = new Label();
		popup = new JFXPopup();

		bttn = new JFXButton();
		Image imgbtn = new Image(getClass().getResourceAsStream("/icons/more3.png"));
		ImageView imgviewbtn = new ImageView(imgbtn);
		imgviewbtn.prefHeight(50);
		imgviewbtn.prefHeight(50);
		bttn.prefHeight(50);
		bttn.prefWidth(50);
		bttn.setGraphic(imgviewbtn);

		setAlignment(javafx.geometry.Pos.CENTER);
		setMaxHeight(USE_PREF_SIZE);
		setMaxWidth(USE_PREF_SIZE);
		setMinHeight(USE_PREF_SIZE);
		setMinWidth(USE_PREF_SIZE);
		setPrefHeight(60.0);
		setPrefWidth(1114.0);

		columnConstraints.setHalignment(javafx.geometry.HPos.CENTER);
		columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
		columnConstraints.setMaxWidth(275.0);
		columnConstraints.setMinWidth(10.0);
		columnConstraints.setPrefWidth(182.0);

		columnConstraints0.setHalignment(javafx.geometry.HPos.CENTER);
		columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
		columnConstraints0.setMaxWidth(341.0);
		columnConstraints0.setMinWidth(10.0);
		columnConstraints0.setPrefWidth(207.0);

		columnConstraints1.setHalignment(javafx.geometry.HPos.CENTER);
		columnConstraints1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
		columnConstraints1.setMaxWidth(349.0);
		columnConstraints1.setMinWidth(10.0);
		columnConstraints1.setPrefWidth(177.0);

		columnConstraints2.setHalignment(javafx.geometry.HPos.RIGHT);
		columnConstraints2.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
		columnConstraints2.setMaxWidth(555.0);
		columnConstraints2.setMinWidth(10.0);
		columnConstraints2.setPrefWidth(547.0);

		rowConstraints.setMaxHeight(171.0);
		rowConstraints.setMinHeight(10.0);
		rowConstraints.setPrefHeight(171.0);
		rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

		label.setAlignment(javafx.geometry.Pos.CENTER);
		// edited
		label.setText(this.achat.getNumAcqt());
		label.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
		label.setFont(new Font(16.0));

		GridPane.setColumnIndex(label0, 1);
		label0.setAlignment(javafx.geometry.Pos.CENTER);
		// edited
		label0.setText(this.achat.getDateAcqt().toString());
		label0.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
		label0.setFont(new Font(16.0));

		GridPane.setColumnIndex(label1, 2);
		label1.setAlignment(javafx.geometry.Pos.CENTER);
		label1.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
		// edited
		label1.setText(Double.toString(achat.getQuantiteAcqt()));
		label1.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
		label1.setFont(new Font(16.0));

		GridPane.setColumnIndex(bttn, 3);
		GridPane.setMargin(bttn, new Insets(0.0, 90.0, 0.0, 0.0));

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

	public void intpopup() {
		JFXButton modifier = new JFXButton("Modifier");
		JFXButton supprimer = new JFXButton("Archiver");

		modifier.setPadding(new Insets(10));
		supprimer.setPadding(new Insets(10));

		VBox box = new VBox(modifier, supprimer);
		box.setStyle("-fx-background-color: #ffffff");

		popup.setContent(box);
		popup.setSource(bttn);

		if (achat.isDeleted()) {
			box.setDisable(true);
		}

		modifier.setOnAction(event -> {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource(ViewUrl.ModifierAchat));
				loader.load();

				ModifierAchatController controler = loader.getController();
				// edited
				controler.setData(this.achat);

				AnchorPane root = loader.getRoot();

				StageDialog dialog = new StageDialog(Methode.getStage(event), root);
				dialog.show();
			} catch (IOException ex) {
				Logger.getLogger(AchatCell.class.getName()).log(Level.SEVERE, null, ex);
			}

		});

		supprimer.setOnAction(event -> {
			Optional<ButtonType> result = Notification.deleteAlert().showAndWait();
			if (result.get() == ButtonType.OK) {

				AchatQueries.archive(achat);
				Notification.Deletenotification();
				new ShowPane().showListAchat();

			}

			popup.close();
		});

	}

}
