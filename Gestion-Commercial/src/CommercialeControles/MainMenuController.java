package CommercialeControles;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainMenuController implements Initializable {

    @FXML
    private JFXButton achat_button;
    @FXML
    private JFXButton vente_button;
    @FXML
    private JFXButton import_button;
    @FXML
    private JFXButton export_button;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image img = new Image(getClass().getResourceAsStream("/icons/select1.png"));
        ImageView icon = new ImageView(img);
        icon.prefHeight(35);
        icon.prefWidth(50);
        Home2FXMLController.bttn_menu.setGraphic(icon);
    }

    @FXML
    private void show_achat(ActionEvent event) {
        Image img = new Image(getClass().getResourceAsStream("/icons/select1.png"));
        ImageView icon = new ImageView(img);
        icon.prefHeight(35);
        icon.prefWidth(50);
        Home2FXMLController.bttn_menu.setGraphic(icon);
    }

    @FXML
    private void show_vente(ActionEvent event) {
        Image img = new Image(getClass().getResourceAsStream("/icons/select2.png"));
        ImageView icon = new ImageView(img);
        icon.prefHeight(35);
        icon.prefWidth(50);
        Home2FXMLController.bttn_menu.setGraphic(icon);
    }

    @FXML
    private void show_import(ActionEvent event) {
        Image img = new Image(getClass().getResourceAsStream("/icons/select3.png"));
        ImageView icon = new ImageView(img);
        icon.prefHeight(35);
        icon.prefWidth(50);
        Home2FXMLController.bttn_menu.setGraphic(icon);
    }

    @FXML
    private void show_export(ActionEvent event) {
        Image img = new Image(getClass().getResourceAsStream("/icons/select4.png"));
        ImageView icon = new ImageView(img);
        icon.prefHeight(35);
        icon.prefWidth(50);
        Home2FXMLController.bttn_menu.setGraphic(icon);
    }

}
