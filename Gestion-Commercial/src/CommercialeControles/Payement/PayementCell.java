package CommercialeControles.Payement;

import CommercialeControles.Client.ClienCell;
import UIControle.Methode;
import UIControle.Notification;
import UIControle.StageDialog;
import UIControle.ViewUrl;
import com.gestionCommerciale.HibernateSchema.Payment;
import com.gestionCommerciale.Models.PaymentQueries;
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
import static javafx.scene.layout.Region.USE_PREF_SIZE;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class PayementCell extends GridPane {

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

    Payment payment;

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public PayementCell(Payment payment) {
        this.payment = payment;

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
        rowConstraints = new RowConstraints();
        label = new Label();
        label0 = new Label();
        label1 = new Label();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(50.0);
        setPrefWidth(664.0);

        columnConstraints.setHalignment(javafx.geometry.HPos.CENTER);
        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMaxWidth(218.0);
        columnConstraints.setMinWidth(10.0);
        columnConstraints.setPrefWidth(165.0);

        columnConstraints0.setHalignment(javafx.geometry.HPos.CENTER);
        columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints0.setMaxWidth(251.0);
        columnConstraints0.setMinWidth(10.0);
        columnConstraints0.setPrefWidth(162.0);

        columnConstraints1.setHalignment(javafx.geometry.HPos.CENTER);
        columnConstraints1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints1.setMaxWidth(308.0);
        columnConstraints1.setMinWidth(10.0);
        columnConstraints1.setPrefWidth(173.0);

        columnConstraints2.setHalignment(javafx.geometry.HPos.CENTER);
        columnConstraints2.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints2.setMaxWidth(308.0);
        columnConstraints2.setMinWidth(10.0);
        columnConstraints2.setPrefWidth(164.0);

        rowConstraints.setMinHeight(10.0);
        rowConstraints.setPrefHeight(30.0);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        GridPane.setHalignment(label, javafx.geometry.HPos.CENTER);
        GridPane.setValignment(label, javafx.geometry.VPos.CENTER);
        label.setAlignment(javafx.geometry.Pos.CENTER);
        label.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        label.setText(this.payment.getDate().toString());
        label.setFont(new Font(16.0));

        GridPane.setColumnIndex(label0, 1);
        GridPane.setHalignment(label0, javafx.geometry.HPos.CENTER);
        GridPane.setValignment(label0, javafx.geometry.VPos.CENTER);
        label0.setAlignment(javafx.geometry.Pos.CENTER);
        label0.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        label0.setText("" + this.payment.getMontant());
        label0.setFont(new Font(16.0));

        GridPane.setColumnIndex(label1, 2);
        GridPane.setHalignment(label1, javafx.geometry.HPos.CENTER);
        GridPane.setValignment(label1, javafx.geometry.VPos.CENTER);
        label1.setAlignment(javafx.geometry.Pos.CENTER);
        label1.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        label1.setText("" + this.payment.getType());
        label1.setFont(new Font(16.0));

        GridPane.setColumnIndex(bttn, 3);

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

        modifier.setOnAction(event -> {

            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(ViewUrl.modifierpayement));
                loader.load();

                ModifierPaymentController controlClient = loader.getController();
                controlClient.setData(this.payment, PayementListeController.listepay);

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
                PaymentQueries.archive(payment);
                Notification.Deletenotification();
            }

            popup.close();
        });

    }

}
