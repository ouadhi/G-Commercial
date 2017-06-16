/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conroles;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gestionCommerciale.HibernateSchema.User;
import com.gestionCommerciale.Models.UserQueries;
import com.jfoenix.controls.JFXButton;

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

		public ObjectProperty<EditButton> editButtonProperty() {
			return editButton;
		}

		// Edit buttun
		public EditButton getEditButton() {
			return editButton.get();
		}

		// fulle name
		public String getFullname() {
			return fullname.get();
		}

		public StringProperty getFullPropery() {
			return fullname;
		}

		// picture geter and setter
		public ProfilePicture getPicture() {
			return picture.get();
		}

		public ObjectProperty<ProfilePicture> getPicturePropery() {
			return picture;
		}

		// role
		public String getRole() {
			return role.get();
		}

		public StringProperty getRolePropery() {
			return role;
		}

		// user name
		public String getUsername() {
			return username.get();
		}

		public StringProperty getUsernamePropery() {
			return username;
		}

		public void setEditButton(EditButton editButton) {
			this.editButton.set(editButton);
		}

		public void setFullname(String full) {
			this.fullname.set(full);
		}

		public void setPicture(ProfilePicture pic) {
			this.picture.set(pic);
		}

		public void setrole(String role) {
			this.role.set(role);
		}

		public void setUsername(String user) {
			this.username.set(user);
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
					loader.setLocation(getClass().getResource("/Views/EditUserFXML.fxml"));
					loader.load();

					EditUserController usercoltroler = loader.getController();
					usercoltroler.set_data(row.getFullname(), row.getUsername(), row.getRole());

					AnchorPane root = loader.getRoot();
					Conroles.AdminFXMLController.rootp.getChildren().setAll(root);

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
	public static EditableFileRow row;

	@FXML
	private TableView<EditableFileRow> table;

	@FXML
	private JFXButton Add_user_button;

	public ObservableList<EditableFileRow> data;

	public void addrows(ObservableList<EditableFileRow> data) {

		table.getItems().clear();

		table.setItems(data);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		data = FXCollections.observableArrayList();
		List<User> listOfDbUsers = UserQueries.list();

		for (int i = 0; i < listOfDbUsers.size(); i++) {
			data.add(new EditableFileRow(listOfDbUsers.get(i).getPhotoLien(), listOfDbUsers.get(i).getNom(),
					listOfDbUsers.get(i).getNom(), listOfDbUsers.get(i).getType()));
		}

		table_column();
		addrows(data);
		table.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				row = table.getSelectionModel().getSelectedItem();

			}
		});
	}

	@FXML
	private void show_add_user_form(ActionEvent event) {

		try {
			FXMLLoader loader = new FXMLLoader();

			AnchorPane root = FXMLLoader.load(getClass().getResource("/Views/AddUserFXML.fxml"));

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
		TableColumn picturecolumn = new TableColumn("PICTURE");
		picturecolumn.setCellValueFactory(new PropertyValueFactory<>("picture"));
		picturecolumn.setPrefWidth(213.6);
		// add fullname
		TableColumn fullnameColumn = new TableColumn("FULLNAME");
		fullnameColumn.setCellValueFactory(new PropertyValueFactory<>("fullname"));
		fullnameColumn.setPrefWidth(213.6);
		// add user name
		TableColumn usernameColumn = new TableColumn("USER NAME");
		usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
		usernameColumn.setPrefWidth(213.6);
		// add role
		TableColumn roleColumn = new TableColumn("ROLE");
		roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
		roleColumn.setPrefWidth(213.6);
		// TableView table = new TableView();
		table.getColumns().addAll(picturecolumn, fullnameColumn, usernameColumn, roleColumn, editColumn);

	}

}
