package com.gestionCommerciale.Controllers.UserController.Notification;

import com.gestionCommerciale.Controllers.UserController.UIControle.Notification;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class NotificationController implements Initializable {

    @FXML
    private TableView<table_notification> table_notification;

    @FXML
    private TextField search_inpuut;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table_column();

    }

    private void table_column() {

        // add clumn buttton
        TableColumn editColumn = new TableColumn("Action");
        editColumn.setCellValueFactory(new PropertyValueFactory<>("action"));
        editColumn.setPrefWidth(213.6);

        TableColumn picturecolumn = new TableColumn("Agent");
        picturecolumn.setCellValueFactory(new PropertyValueFactory<>("utilisateur"));
        picturecolumn.setPrefWidth(213.6);

        TableColumn dateColumn = new TableColumn("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateColumn.setPrefWidth(213.6);

        TableColumn sujetColumn = new TableColumn("Sujet");
        sujetColumn.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        sujetColumn.setPrefWidth(213.6);

        TableColumn msgColumn = new TableColumn("Message");
        msgColumn.setCellValueFactory(new PropertyValueFactory<>("message"));
        msgColumn.setPrefWidth(213.6);

        table_notification.getColumns().addAll(picturecolumn, dateColumn, sujetColumn, msgColumn, editColumn);

        // add  data to  tabel view 
        ObservableList<table_notification> data = FXCollections.observableArrayList(
                new table_notification("mohammed", "111", "panne", "bonjou")
        );
        table_notification.setItems(data);

    }

    // spicifier  les  columns 
    public static class table_notification {

        private final SimpleObjectProperty<Profile> utilisateur;
        private final SimpleStringProperty date;
        private final SimpleStringProperty sujet;
        private final SimpleStringProperty message;
        private final SimpleObjectProperty<Box_button> action;

        // remplacer le paramaitre 
        public table_notification(String utilisateur, String date, String sujet, String message) {
            this.utilisateur = new SimpleObjectProperty(new Profile(utilisateur));
            this.date = new SimpleStringProperty(date);
            this.sujet = new SimpleStringProperty(sujet);
            this.message = new SimpleStringProperty(message);
            this.action = new SimpleObjectProperty(new Box_button());
        }

        // picture geter and setter 
        public Profile getUtilisateur() {
            return utilisateur.get();
        }

        public void setUtilisateur(Profile pic) {
            this.utilisateur.set(pic);
        }

        public ObjectProperty<Profile> getPicturePropery() {
            return utilisateur;
        }

        public String getDate() {
            return date.get();
        }

        public void setDate(String full) {
            this.date.set(full);
        }

        public StringProperty getDatePropery() {
            return date;
        }

        public String getSujet() {
            return sujet.get();
        }

        public void setSujet(String suj) {
            this.sujet.set(suj);
        }

        public StringProperty getSujetPropery() {
            return sujet;
        }

        public String getMessage() {
            return message.get();
        }

        public void setMessage(String msg) {
            this.message.set(msg);
        }

        public StringProperty getMessagePropery() {
            return message;
        }

        // Edit buttun
        public Box_button getAction() {
            return action.get();
        }

        public void setAction(Box_button editButton) {
            this.action.set(editButton);
        }

        public SimpleObjectProperty<Box_button> editActionProperty() {
            return action;
        }
    }

    public static class EditButton extends Button {

        public EditButton() {

            super("");
            Image img = new Image(getClass().getResourceAsStream("ok.png"));
            ImageView icon = new ImageView(img) ; 
            icon.prefHeight(20) ; 
            icon.prefWidth(20) ; 
            this.setGraphic(new ImageView(img));
            getStyleClass().add("ok_button");
            this.setPrefSize(20, 20);
            setAlignment(Pos.CENTER);
            setOnAction((event) -> {

            });
        }
    }

    public static class SuppButton extends Button {

        public SuppButton() {

            super("");
            Image img = new Image(getClass().getResourceAsStream("delete.png"));
            ImageView icon = new ImageView(img) ; 
            icon.prefHeight(20) ; 
            icon.prefWidth(20) ; 
            this.setGraphic(icon);
            getStyleClass().add("supp_button");
            this.setPrefSize(20, 20);
            
            setAlignment(Pos.CENTER);

            setOnAction((event) -> {
                
                Notification.Deletenotification();
            });
        }

        private void setGraphic(Image img) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    public static class Box_button extends HBox {

        public Box_button() {
            
            SuppButton supp_btn = new SuppButton();
            EditButton edit_bttn = new EditButton();
            this.getChildren().setAll(supp_btn, edit_bttn);
            this.setAlignment(Pos.CENTER);
            this.setPadding(new Insets(5, 5, 5, 5));

        }

    }

    public static class Profile extends ImageView {

        public Profile(String name) {
            Image img = new Image("icons/man.png");
            setImage(img);

            Label user_name = new Label(name);
        }

    }

}
