package Conroles;

import UIControle.Notification;
import UIControle.ShowPane;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class RoleFXMLController implements Initializable {

    @FXML
    private JFXButton Add_role_button;

    @FXML
    private TableView<EditableRow> table_roles;
    
    ObservableList<EditableRow> data ; 

    @FXML
    void show_add_role_form(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader();

            AnchorPane root = FXMLLoader.load(getClass().getResource("/Views/AddRoleFxml.fxml"));

            Conroles.AdminFXMLController.rootp.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(Users_ListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         data = FXCollections.observableArrayList(
                new EditableRow("1", "kada", "mohammed")
        );
        table_column();
    }

    private void table_column() {

        

        // add clumn buttton
        TableColumn editColumn = new TableColumn("Action");
        editColumn.setCellValueFactory(new PropertyValueFactory<>("editButton"));
        editColumn.setPrefWidth(213.6);

        // add picture 
        TableColumn picturecolumn = new TableColumn("ID");
        picturecolumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        picturecolumn.setPrefWidth(213.6);
        // add fullname
        TableColumn fullnameColumn = new TableColumn("Role");
        fullnameColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        fullnameColumn.setPrefWidth(213.6);
        // add  user name 
        TableColumn usernameColumn = new TableColumn("Description");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        usernameColumn.setPrefWidth(427.2);

        table_roles.getColumns().addAll(picturecolumn, fullnameColumn, usernameColumn, editColumn);
        table_roles.setItems(data);

    }

    public static class EditableRow {

        private final SimpleStringProperty id;
        private final SimpleStringProperty role;
        private final SimpleStringProperty description;
        private final SimpleObjectProperty<EditButton> editButton;

        public EditableRow(String id, String role, String description) {
            this.id = new SimpleStringProperty(id);
            this.role = new SimpleStringProperty(role);
            this.description = new SimpleStringProperty(description);
            this.editButton = new SimpleObjectProperty<>(new EditButton());

        }

        public String getId() {
            return id.get();
        }

        public void setId(String id) {
            this.id.set(id);
        }

        public StringProperty getIdPropery() {
            return id;
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

        // description
        public String getDescription() {
            return description.get();
        }

        public void setDescription(String role) {
            this.role.set(role);
        }

        public StringProperty getDescriptionPropery() {
            return description;
        }

        //  action button 
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

        public EditButton() {

            super("Supprimer");
            getStyleClass().add("delete-button");
            setPrefWidth(128);
            setAlignment(Pos.CENTER);
            setOnAction((event) -> {

                try {
                    Optional<ButtonType> result = Notification.deleteAlert().showAndWait();

                    if (result.get() == ButtonType.OK) {

                        Notification.Deletenotification();
                        ShowPane show = new ShowPane() ; 
                        show.showRole();
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }

            });
        }
    }
    
    
    public void addrows (ObservableList<EditableRow> data) {
        table_roles.getItems().clear();
        
        table_roles.setItems(data);
    }

}
