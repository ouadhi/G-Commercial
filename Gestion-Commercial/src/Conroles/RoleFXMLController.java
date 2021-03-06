package Conroles;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gestionCommerciale.HibernateSchema.Role;
import com.gestionCommerciale.Models.RoleQueries;
import com.jfoenix.controls.JFXButton;

import UIControle.Notification;
import UIControle.ShowPane;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class RoleFXMLController implements Initializable {

	public class EditableRow {

		private final SimpleIntegerProperty id;
		private final SimpleStringProperty role;
		private final SimpleStringProperty description;
		private final SimpleObjectProperty<EditButton> editButton;

		public EditableRow(int id, String role, String description) {
			this.id = new SimpleIntegerProperty(id);
			this.role = new SimpleStringProperty(role);
			this.description = new SimpleStringProperty(description);
			this.editButton = new SimpleObjectProperty<>(new EditButton());

		}

		public ObjectProperty<EditButton> editButtonProperty() {
			return editButton;
		}

		// description
		public String getDescription() {
			return description.get();
		}

		public StringProperty getDescriptionPropery() {
			return description;
		}

		// action button
		public EditButton getEditButton() {
			return editButton.get();
		}

		public int getId() {
			return id.get();
		}

		public IntegerProperty getIdPropery() {
			return id;
		}

		// role
		public String getRole() {
			return role.get();
		}

		public StringProperty getRolePropery() {
			return role;
		}

		public void setDescription(String role) {
			this.role.set(role);
		}

		public void setEditButton(EditButton editButton) {
			this.editButton.set(editButton);
		}

		public void setId(int id) {
			this.id.set(id);
		}

		public void setrole(String role) {
			this.role.set(role);
		}
	}

	public class EditButton extends Button {

		public EditButton() {

			super("Supprimer");
			getStyleClass().add("delete-button");
			setPrefWidth(128);
			setAlignment(Pos.CENTER);
			setOnAction((event) -> {

				try {
					Optional<ButtonType> result = Notification.deleteAlert().showAndWait();

					if (result.get() == ButtonType.OK) {
						// Role must be selected before deleting
						// either let notify user when the row is not selected
						// or auomatically select row
						int id = table_roles.getSelectionModel().getSelectedItem().getId();
						Role role = roleQueries.getRole(id);
						roleQueries.delete(role);
						Notification.Deletenotification();
						ShowPane show = new ShowPane();
						show.showRole();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			});
		}
	}

	@FXML
	private JFXButton Add_role_button;

	@FXML
	private TableView<EditableRow> table_roles;

	ObservableList<EditableRow> data;

	RoleQueries roleQueries = new RoleQueries();

	public void addrows(ObservableList<EditableRow> data) {
		table_roles.getItems().clear();

		table_roles.setItems(data);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		/*
		 * data = FXCollections.observableArrayList( new EditableRow(1, "kada",
		 * "mohammed") );
		 */

		data = FXCollections.observableArrayList();
		List<Role> listOfDbRoles = roleQueries.list();
		for (int i = 0; i < listOfDbRoles.size(); i++) {
			data.add(new EditableRow(listOfDbRoles.get(i).getIdRole(), listOfDbRoles.get(i).getRole(),
					listOfDbRoles.get(i).getDescription()));
		}

		table_column();
	}

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
		// add user name
		TableColumn usernameColumn = new TableColumn("Description");
		usernameColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
		usernameColumn.setPrefWidth(427.2);

		table_roles.getColumns().addAll(picturecolumn, fullnameColumn, usernameColumn, editColumn);
		table_roles.setItems(data);

	}

}
