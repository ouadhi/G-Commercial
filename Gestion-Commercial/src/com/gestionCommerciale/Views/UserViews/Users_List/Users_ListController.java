/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionCommerciale.Views.UserViews.Users_List;

import com.gestionCommerciale.Views.UserViews.EditUser.EditUserController;
import com.gestionCommerciale.Views.UserViews.administrateur.AdminFXMLController;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class Users_ListController implements Initializable {

    @FXML
    private TableView<EditableFileRow> table;
    @FXML
    private JFXButton Add_user_button;

    public static EditableFileRow row;
    public ObservableList<EditableFileRow> data ;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data = FXCollections.observableArrayList(
                new EditableFileRow("photo", "kada", "mohammed", "admin")
        );
        table_column();
        table.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                row = table.getSelectionModel().getSelectedItem();

            }
        });
    }

    private void table_column() {

        
        // add clumn buttton
        TableColumn editColumn = new TableColumn("Action");
        editColumn.setCellValueFactory(new PropertyValueFactory<>("editButton"));
        editColumn.setPrefWidth(213.6);

        // add picture 
        TableColumn picturecolumn = new TableColumn("PICTURE");
        picturecolumn.setCellValueFactory(new PropertyValueFactory<>("picture"));
        picturecolumn.setPrefWidth(213.6);
        // add fullname
        TableColumn fullnameColumn = new TableColumn("FULLNAME");
        fullnameColumn.setCellValueFactory(new PropertyValueFactory<>("fullname"));
        fullnameColumn.setPrefWidth(213.6);
        // add  user name 
        TableColumn usernameColumn = new TableColumn("USER NAME");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        usernameColumn.setPrefWidth(213.6);
        // add role 
        TableColumn roleColumn = new TableColumn("ROLE");
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        roleColumn.setPrefWidth(213.6);
        // TableView table = new TableView();
        table.getColumns().addAll(picturecolumn, fullnameColumn, usernameColumn, roleColumn, editColumn);
        table.setItems(data);

    }

    @FXML
    private void show_add_user_form(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader();

            AnchorPane root = FXMLLoader.load(getClass().getResource("/AddUser/AddUserFXML.fxml"));

            AdminFXMLController.rootp.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(Users_ListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public static class EditableFileRow {

        private final SimpleObjectProperty<ProfilePicture> picture;
        private final SimpleStringProperty fullname;
        private final SimpleStringProperty username;
        private final SimpleStringProperty role;
        private final SimpleObjectProperty<EditButton> editButton;

        public EditableFileRow(String picture, String fullname, String username, String role) {
            this.picture = new SimpleObjectProperty(new ProfilePicture(picture));
            this.fullname = new SimpleStringProperty(fullname);
            this.username = new SimpleStringProperty(username);
            this.role = new SimpleStringProperty(role);
            this.editButton = new SimpleObjectProperty(new EditButton("update", fullname));
        }

        // picture geter and setter 
        public ProfilePicture getPicture() {
            return picture.get();
        }

        public void setPicture(ProfilePicture pic) {
            this.picture.set(pic);
        }

        public ObjectProperty<ProfilePicture> getPicturePropery() {
            return picture;
        }

        // fulle name
        public String getFullname() {
            return fullname.get();
        }

        public void setFullname(String full) {
            this.fullname.set(full);
        }

        public StringProperty getFullPropery() {
            return fullname;
        }

        // user name 
        public String getUsername() {
            return username.get();
        }

        public void setUsername(String user) {
            this.username.set(user);
        }

        public StringProperty getUsernamePropery() {
            return username;
        }

        // role
        public String getRole() {
            return role.get();
        }

        public void setrole(String role) {
            this.role.set(role);
        }

        public StringProperty getRolePropery() {
            return role;
        }

        // Edit buttun
        public EditButton getEditButton() {
            return editButton.get();
        }

        public void setEditButton(EditButton editButton) {
            this.editButton.set(editButton);
        }

        public ObjectProperty<EditButton> editButtonProperty() {
            return editButton;
        }
    }

    public static class EditButton extends Button {

        public EditButton(String fileName, String fullname) {

            super("Edit");
            getStyleClass().add("add_button");
            setPrefWidth(128);
            setAlignment(Pos.CENTER);
            setOnAction((event) -> {

                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/EditUser/EditUserFXML.fxml"));
                    loader.load();

                    EditUserController usercoltroler = loader.getController();
                    usercoltroler.set_data(row.getFullname(), row.getUsername(), row.getRole());

                     AnchorPane root = loader.getRoot()  ; 
                    AdminFXMLController.rootp.getChildren().setAll(root);

                } catch (Exception e) {
                    System.out.println(e);
                }

            });
        }
    }

    public static class ProfilePicture extends ImageView {

        public ProfilePicture(String url) {
            Image img = new Image("icons/man.png");
            setImage(img);
        }

    }
    
    public void addrows (ObservableList<EditableFileRow> data ){
        
          table.getItems().clear();  
          
          table.setItems(data);
    }

}
